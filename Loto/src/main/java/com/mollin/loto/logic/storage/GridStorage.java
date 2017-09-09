package com.mollin.loto.logic.storage;

import com.mollin.loto.logic.main.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mollin.loto.logic.main.grid.LotoGrid;
import com.mollin.loto.logic.utils.FileUtils;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Gestion du chargement/sauvegarde la grille
 *
 * @author MOLLIN Florian
 */
public class GridStorage {
    private static final Gson GSON = new GsonBuilder().create();

    /**
     * Sauvegarde de la grille
     *
     * @param grid La grille
     * @param profileName Le nom du profil
     */
    public static void saveGrid(LotoGrid grid, String profileName) {
        Path filePath = Paths.get(Constants.File.PROFILES_FOLDER_PATH, profileName, Constants.File.GRID_FILE_NAME);
        String json = GSON.toJson(grid, LotoGrid.class);
        FileUtils.write(filePath, json);
    }

    /**
     * Chargement de la grille
     *
     * @param profileName Le nom du profil
     * @return La grille chargé (ou une grille par défaut si erreur)
     */
    public static LotoGrid loadGrid(String profileName) {
        try {
            Path filePath = Paths.get(Constants.File.PROFILES_FOLDER_PATH, profileName, Constants.File.GRID_FILE_NAME);
            if (filePath == null) {
                return getDefaultGrid(profileName);
            }
            List<String> fileContent = FileUtils.read(filePath);
            if (fileContent == null) {
                return getDefaultGrid(profileName);
            }
            String json = String.join("", fileContent);
            return GSON.fromJson(json, LotoGrid.class);
        } catch (Exception e) {
            return getDefaultGrid(profileName);
        }
    }

    /**
     * Renvoi la grille par défaut (grille vierge)
     *
     * @param profileName le nom du profil
     * @return La grille
     */
    private static LotoGrid getDefaultGrid(String profileName) {
        return new LotoGrid(Constants.Size.HISTORY_DEPTH, Constants.Number.MAX_NUMBER, profileName);
    }
}
