package com.mollin.loto.logic.main.utils;

import com.mollin.loto.Loto;
import org.controlsfx.glyphfont.FontAwesome.Glyph;

import java.util.Arrays;
import java.util.List;

/**
 * @author MOLLIN Florian
 */
public interface Constants {

    interface Number {
        int NB_ROWS = 10;
        int NB_COLS = 9;
        int MAX_NUMBER = NB_COLS * NB_ROWS;
    }

    interface Style {
        /**
         * Classe CSS pour le texte des cellules de la grille
         */
        String STYLE_CLASS_CELL_TEXT = "cell_text";
        /**
         * Classe CSS pour le texte du panneau de dernier numéro
         */
        String STYLE_CLASS_PANE_TEXT = "last_number_style_text";
        /**
         * Classe CSS pour le texte (numéro) du panneau de dernier numéro
         */
        String STYLE_CLASS_PANE_NUMBER = "last_number_style_number";
        /**
         * Classe CSS pour le texte du panneau à textes multiples
         */
        String STYLE_CLASS_MULTI_TEXT = "multi_text";
        /**
         * Feuilles de style (CSS) de l'appli
         */
        List<String> ALL_STYLESHEETS = Arrays.asList(Loto.class.getResource("mainSheet.css").toExternalForm());
    }

    interface FontSize {
        /**
         * Taille max de la police pour les numéros des cellules
         */
        int FONT_SIZE_MAX_CELL_NUMBER = 200;
        /**
         * Taille max de la police pour le texte du panneau 'dernier numéro'
         */
        int FONT_SIZE_MAX_PANE_TEXT = 200;
        /**
         * Taille max de la police pour le texte (numéro) du panneau 'dernier
         * numéro'
         */
        int FONT_SIZE_MAX_PANE_NUMBER = 500;
        /**
         * Taille max de la police pour les textes du panneau multiples
         */
        int FONT_SIZE_MAX_MULTI_TEXT = 200;
    }

    interface Size {
        /**
         * Largeur initiale de la scene
         */
        double INITIAL_WIDTH_SCENE = 1024;
        /**
         * Largeur initiale de la scene
         */
        double INITIAL_HEIGHT_SCENE = 768;
        /**
         * Profondeur de l'histoire (nombre de coups sauvegardés)
         */
        int HISTORY_DEPTH = 100;
    }

    interface Label {
        /**
         * Titre de la fenetre
         */
        String WINDOW_TITLE = "Loto";
        String CLEAR_BUTTON_TOOLTIP = "Démarquer";
        String UNDO_BUTTON_TOOLTIP = "Annuler";
        String EDIT_BUTTON_TOOLTIP = "Editer";
    }

    interface File {
        String FILE_SEPARATOR = java.io.File.separator;
        String USER_FOLDER_PATH = System.getProperty("user.home");
        String MAIN_FOLDER_PATH = USER_FOLDER_PATH + FILE_SEPARATOR + ".loto";
        String PROFILES_FOLDER_PATH = MAIN_FOLDER_PATH + FILE_SEPARATOR + "profiles";
        String PARAMETERS_PROFILE_FILE_NAME = "param";
        String GRID_FILE_NAME = "grid";
        String SEPARATOR_FILE_NAME = "separators";
        String PROFILE_FILE_EXTENSION = ".profil.loto";
    }

    interface Other {
        Glyph CLEAR_BUTTON_GLYPH = Glyph.TRASH;
        Glyph UNDO_BUTTON_GLYPH = Glyph.MAIL_REPLY;
        Glyph EDIT_BUTTON_GLYPH = Glyph.EDIT;
        Glyph EXIT_BUTTON_GLYPH = Glyph.TIMES;
        boolean FULL_SCREEN_APP = true;
        String VERSION_NUMBER = "1.0.0";
        String WEBSITE_URL = "https://florian-mollin.github.io/loto-associatif/";
        String LICENSE_NAME = "MIT";
    }

}
