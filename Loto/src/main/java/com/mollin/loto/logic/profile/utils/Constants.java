package com.mollin.loto.logic.profile.utils;

import org.controlsfx.glyphfont.FontAwesome.Glyph;

/**
 * @author MOLLIN Florian
 */
public interface Constants {

    public static interface Label {
        public static final String WINDOW_TITLE = "Loto - Profils";
        public static final String PROFILE_LABEL = "Profil :";
        // boutons
        public static final String LAUNCH_BUTTON_LABEL = "Lancer";
        public static final Glyph LAUNCH_BUTTON_GLYPH = Glyph.TH_LARGE;
        public static final String CREATE_BUTTON_LABEL = "Créer";
        public static final Glyph CREATE_BUTTON_GLYPH = Glyph.PLUS;
        public static final String UPDATE_BUTTON_LABEL = "Modifier";
        public static final Glyph UPDATE_BUTTON_GLYPH = Glyph.EDIT;
        public static final String DELETE_BUTTON_LABEL = "Supprimer";
        public static final Glyph DELETE_BUTTON_GLYPH = Glyph.MINUS;
        public static final String IMPORT_BUTTON_LABEL = "Importer";
        public static final Glyph IMPORT_BUTTON_GLYPH = Glyph.DOWNLOAD;
        public static final String EXPORT_BUTTON_LABEL = "Exporter";
        public static final Glyph EXPORT_BUTTON_GLYPH = Glyph.UPLOAD;
        public static final Glyph ABOUT_BUTTON_GLYPH = Glyph.INFO_CIRCLE;
        // dialogues
        public static final String TITLE_DELETE_DIALOG = "Suppression";
        public static final String CONTENT_DELETE_DIALOG = "Voulez-vous supprimer le profil \"{0}\" ?";
        public static final String TITLE_CREATE_DIALOG = "Création";
        public static final String CONTENT_CREATE_DIALOG = "Nom du profil :";
        // notifications
        public static final String TITLE_IMPORT_NOTIFICATION = "Import de profil";
        public static final String CONTENT_IMPORT_NOTIFICATION_OK = "Le profil a été importé";
        public static final String CONTENT_IMPORT_NOTIFICATION_NOK = "Une erreur est survenue";
        public static final String TITLE_EXPORT_NOTIFICATION = "Export de profil";
        public static final String CONTENT_EXPORT_NOTIFICATION_OK = "Le profil a été exporté";
        public static final String CONTENT_EXPORT_NOTIFICATION_NOK = "Une erreur est survenue";
    }

    public static interface Size {
        public static final double OUTER_MARGIN_SIZE = 20;
        public static final double INNER_MARGIN_SIZE = 10;
        public static final double PROFILE_FONT_SIZE = 20;
        public static final double LAUNCH_BUTTON_FONT_SIZE = 20;
    }

}
