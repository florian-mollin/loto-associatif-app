package com.mollin.loto.logic.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mollin.loto.gui.main.utils.SeparatorsParameters;
import com.mollin.loto.logic.main.utils.Constants;
import com.mollin.loto.logic.utils.FileUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Gestion du chargement/sauvegarde des séparateurs
 *
 * @author MOLLIN Florian
 */
public class SeparatorsStorage {
    private static final Gson GSON = new GsonBuilder().create();

    /**
     * Constructeur privé car classe utilitaire
     */
    private SeparatorsStorage() {
    }

    /**
     * Chargement des séparateurs
     *
     * @param profileName le nom du profil
     * @return Les séparateurs chargés
     */
    public static SeparatorsParameters loadSeparatorsParameters(String profileName) {
        try {
            Path filePath = Paths.get(Constants.File.PROFILES_FOLDER_PATH, profileName, Constants.File.SEPARATOR_FILE_NAME);
            if (filePath == null) {
                return SeparatorsParameters.getDefault();
            }
            List<String> fileContent = FileUtils.read(filePath);
            if (fileContent == null) {
                return SeparatorsParameters.getDefault();
            }
            String json = String.join("", fileContent);
            return GSON.fromJson(json, SeparatorsParameters.class);
        } catch (Exception e) {
            return SeparatorsParameters.getDefault();
        }
    }

    /**
     * Sauvegarde des séparateurs
     *
     * @param param       Parametres des séparateurs à sauvegarder
     * @param profileName Le nom du profil
     */
    public static void saveSeparatorsParameters(SeparatorsParameters param, String profileName) {
        Path filePath = Paths.get(Constants.File.PROFILES_FOLDER_PATH, profileName, Constants.File.SEPARATOR_FILE_NAME);
        String json = GSON.toJson(param, SeparatorsParameters.class);
        FileUtils.write(filePath, json);
    }
}
