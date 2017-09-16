package com.mollin.loto.logic.main.utils;

import com.mollin.loto.Loto;
import org.controlsfx.glyphfont.FontAwesome.Glyph;

import java.util.Arrays;
import java.util.List;

/**
 * @author MOLLIN Florian
 */
public interface Constants {

    public static interface Number {
        public static final int NB_ROWS = 10;
        public static final int NB_COLS = 9;
        public static final int MAX_NUMBER = NB_COLS * NB_ROWS;
    }

    public static interface Style {
        /**
         * Classe CSS pour le texte des cellules de la grille
         */
        public static final String STYLE_CLASS_CELL_TEXT = "cell_text";
        /**
         * Classe CSS pour le texte du panneau de dernier numéro
         */
        public static final String STYLE_CLASS_PANE_TEXT = "last_number_style_text";
        /**
         * Classe CSS pour le texte (numéro) du panneau de dernier numéro
         */
        public static final String STYLE_CLASS_PANE_NUMBER = "last_number_style_number";
        /**
         * Classe CSS pour le texte du panneau à textes multiples
         */
        public static final String STYLE_CLASS_MULTI_TEXT = "multi_text";
        /**
         * Feuilles de style (CSS) de l'appli
         */
        public static final List<String> ALL_STYLESHEETS = Arrays.asList(Loto.class.getResource("mainSheet.css").toExternalForm());
    }

    public static interface FontSize {
        /**
         * Taille max de la police pour les numéros des cellules
         */
        public static final int FONT_SIZE_MAX_CELL_NUMBER = 200;
        /**
         * Taille max de la police pour le texte du panneau 'dernier numéro'
         */
        public static final int FONT_SIZE_MAX_PANE_TEXT = 200;
        /**
         * Taille max de la police pour le texte (numéro) du panneau 'dernier
         * numéro'
         */
        public static final int FONT_SIZE_MAX_PANE_NUMBER = 500;
        /**
         * Taille max de la police pour les textes du panneau multiples
         */
        public static final int FONT_SIZE_MAX_MULTI_TEXT = 200;
    }

    public static interface Size {
        /**
         * Largeur initiale de la scene
         */
        public static final double INITIAL_WIDTH_SCENE = 1024;
        /**
         * Largeur initiale de la scene
         */
        public static final double INITIAL_HEIGHT_SCENE = 768;
        /**
         * Profondeur de l'histoire (nombre de coups sauvegardés)
         */
        public static final int HISTORY_DEPTH = 100;
    }

    public static interface Label {
        /**
         * Titre de la fenetre
         */
        public static final String WINDOW_TITLE = "Loto";
        public static final String CLEAR_BUTTON_TOOLTIP = "Démarquer";
        public static final String UNDO_BUTTON_TOOLTIP = "Annuler";
        public static final String EDIT_BUTTON_TOOLTIP = "Editer";
    }

    public static interface File {
        public static final String FILE_SEPARATOR = java.io.File.separator;
        public static final String USER_FOLDER_PATH = System.getProperty("user.home");
        public static final String MAIN_FOLDER_PATH = USER_FOLDER_PATH + FILE_SEPARATOR + ".loto";
        public static final String PROFILES_FOLDER_PATH = MAIN_FOLDER_PATH + FILE_SEPARATOR + "profiles";
        public static final String PARAMETERS_PROFILE_FILE_NAME = "param";
        public static final String GRID_FILE_NAME = "grid";
        public static final String SEPARATOR_FILE_NAME = "separators";
        public static final String PROFILE_FILE_EXTENSION = ".profil.loto";
    }

    public static interface Other {
        public static final Glyph CLEAR_BUTTON_GLYPH = Glyph.TRASH;
        public static final Glyph UNDO_BUTTON_GLYPH = Glyph.MAIL_REPLY;
        public static final Glyph EDIT_BUTTON_GLYPH = Glyph.EDIT;
        public static final Glyph EXIT_BUTTON_GLYPH = Glyph.TIMES;
        public static final boolean FULL_SCREEN_APP = true;
        public static final String VERSION_NUMBER = "1.0.0";
        public static final String WEBSITE_URL = "https://florian-mollin.github.io/loto-associatif/";
        public static final String LICENSE_NAME = "MIT";
    }

}
