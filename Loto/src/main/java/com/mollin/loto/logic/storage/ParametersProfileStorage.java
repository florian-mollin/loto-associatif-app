package com.mollin.loto.logic.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mollin.loto.logic.main.utils.Constants;
import com.mollin.loto.logic.parameters.model.ParametersModel;
import com.mollin.loto.logic.utils.FileUtils;
import javafx.scene.image.Image;
import org.javatuples.Pair;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Gestion du chargement/sauvegarde des profils (et des parametres)
 *
 * @author MOLLIN Florian
 */
public class ParametersProfileStorage {
    private static final Gson GSON = new GsonBuilder().create();

    /**
     * Constructeur privé car classe utilitaire
     */
    private ParametersProfileStorage() {
    }

    /**
     * Chargement des parametres du profil donné
     *
     * @param profileName Le nom du profil
     * @return Les paramètres associés au profil
     */
    public static ParametersModel loadParametersProfile(String profileName) {
        try {
            Path parametersProfilePath = Paths.get(Constants.File.PROFILES_FOLDER_PATH, profileName, Constants.File.PARAMETERS_PROFILE_FILE_NAME);
            if (parametersProfilePath == null) {
                return null;
            }
            List<String> paramFileContent = FileUtils.read(parametersProfilePath);
            if (paramFileContent == null) {
                return null;
            }
            String json = String.join("", paramFileContent);
            return GSON.fromJson(json, ParametersModel.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Récupération de tous les profils de l'application
     *
     * @return La liste des profils
     */
    public static List<String> loadAllProfiles() {
        try {
            Path profilesDirectoryPath = Paths.get(Constants.File.PROFILES_FOLDER_PATH);
            if (profilesDirectoryPath == null) {
                return new ArrayList<>();
            }
            List<String> profiles = new ArrayList<>();
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(profilesDirectoryPath, Files::isDirectory)) {
                for (Path p : stream) {
                    profiles.add(p.getFileName().toString());
                }
            }
            return profiles;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Chargement de tous les couples (nom_du_profil, parametres)
     *
     * @return La liste des couples (nom_du_profil, parametres)
     */
    public static List<Pair<String, ParametersModel>> loadAllParametersProfile() {
        List<String> profiles = loadAllProfiles();
        return profiles
                .stream()
                .map(profile -> Pair.with(profile, loadParametersProfile(profile)))
                .filter(pair -> pair.getValue1() != null)
                .collect(Collectors.toList());
    }

    /**
     * Sauvegarde des parametres pour le profil donné
     *
     * @param profileName Nom du profil
     * @param param       Parametres
     * @return Vrai si la sauvegarde s'est correctement déroulée (faux sinon)
     */
    public static boolean saveParametersProfile(String profileName, ParametersModel param) {
        Path paramPath = Paths.get(Constants.File.PROFILES_FOLDER_PATH, profileName, Constants.File.PARAMETERS_PROFILE_FILE_NAME);
        if (paramPath == null) {
            return false;
        }
        String json = GSON.toJson(param, ParametersModel.class);
        return FileUtils.write(paramPath, json);
    }

    /**
     * Supprime le profil donné
     *
     * @param profileName Le nom du profil a supprimer
     * @return Vrai si pas d'erreur
     */
    public static boolean deleteParametersProfile(String profileName) {
        Path folderPath = Paths.get(Constants.File.PROFILES_FOLDER_PATH, profileName);
        if (folderPath == null) {
            return false;
        }
        if (!Files.exists(folderPath)) {
            return false;
        }
        if (Files.isDirectory(folderPath)) {
            return FileUtils.deleteDirectory(folderPath);
        }
        return false;
    }

    /**
     * Sauvegarde d'une image dans le profil donné
     *
     * @param image       L'image à sauvegarder
     * @param profileName Le nom du profil
     * @return Le nouveau nom de l'image
     */
    public static String saveImage(File image, String profileName) {
        if (image != null) {
            Path fileToCopy = image.toPath();
            String newFileName = String.valueOf(System.currentTimeMillis()) + "_" + image.getName();
            Path directoryOfCopy = Paths.get(Constants.File.PROFILES_FOLDER_PATH, profileName, newFileName);
            if (fileToCopy != null && directoryOfCopy != null) {
                boolean copySuccess = FileUtils.copyFile(fileToCopy, directoryOfCopy);
                if (copySuccess) {
                    return newFileName;
                } else {
                    return null;
                }
            }
            return null;
        } else {
            return null;
        }
    }

    /**
     * Récupération d'une image pour le profil donné
     *
     * @param imageName   Le nom de l'image
     * @param profileName Le nom du profil
     * @return L'image récupérée (null si erreur)
     */
    public static Image getImage(String imageName, String profileName) {
        if (imageName != null && profileName != null && !imageName.isEmpty() && !profileName.isEmpty()) {
            Path imagePath = Paths.get(Constants.File.PROFILES_FOLDER_PATH, profileName, imageName);
            if (imagePath != null) {
                return new Image(imagePath.toFile().toURI().toString());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Exportation du profil (sous forme de ZIP)
     *
     * @param profileName le nom du profil
     * @param target      La destination du profil
     * @return Vrai si pas d'erreur
     */
    public static boolean exportProfile(String profileName, File target) {
        if (target != null && target.isDirectory()) {
            Path profilePath = Paths.get(Constants.File.PROFILES_FOLDER_PATH, profileName);
            if (profilePath != null && FileUtils.isDirectory(profilePath)) {
                ZipUtil.pack(profilePath.toFile(),
                        new File(target, profileName + Constants.File.PROFILE_FILE_EXTENSION),
                        name -> profileName + Constants.File.FILE_SEPARATOR + name);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Importation d'un profil
     *
     * @param profileFile Le profil à importer
     * @return Vrai si pas d'erreur
     */
    public static boolean importProfile(File profileFile) {
        if (profileFile != null && profileFile.isFile()) {
            ZipUtil.unpack(profileFile, new File(Constants.File.PROFILES_FOLDER_PATH));
            return true;
        } else {
            return false;
        }
    }
}
