package com.mollin.loto.logic.profile.utils;

import org.controlsfx.glyphfont.FontAwesome.Glyph;

/**
 * @author MOLLIN Florian
 */
public interface Constants {

    interface Label {
        String WINDOW_TITLE = "Loto - Profils";
        String PROFILE_LABEL = "Profil :";
        // boutons
        String LAUNCH_BUTTON_LABEL = "Lancer";
        Glyph LAUNCH_BUTTON_GLYPH = Glyph.TH_LARGE;
        String CREATE_BUTTON_LABEL = "Créer";
        Glyph CREATE_BUTTON_GLYPH = Glyph.PLUS;
        String UPDATE_BUTTON_LABEL = "Modifier";
        Glyph UPDATE_BUTTON_GLYPH = Glyph.EDIT;
        String DELETE_BUTTON_LABEL = "Supprimer";
        Glyph DELETE_BUTTON_GLYPH = Glyph.MINUS;
        String IMPORT_BUTTON_LABEL = "Importer";
        Glyph IMPORT_BUTTON_GLYPH = Glyph.DOWNLOAD;
        String EXPORT_BUTTON_LABEL = "Exporter";
        Glyph EXPORT_BUTTON_GLYPH = Glyph.UPLOAD;
        Glyph ABOUT_BUTTON_GLYPH = Glyph.INFO_CIRCLE;
        // dialogues
        String TITLE_DELETE_DIALOG = "Suppression";
        String CONTENT_DELETE_DIALOG = "Voulez-vous supprimer le profil \"{0}\" ?";
        String TITLE_CREATE_DIALOG = "Création";
        String CONTENT_CREATE_DIALOG = "Nom du profil :";
        // notifications
        String TITLE_IMPORT_NOTIFICATION = "Import de profil";
        String CONTENT_IMPORT_NOTIFICATION_OK = "Le profil a été importé";
        String CONTENT_IMPORT_NOTIFICATION_NOK = "Une erreur est survenue";
        String TITLE_EXPORT_NOTIFICATION = "Export de profil";
        String CONTENT_EXPORT_NOTIFICATION_OK = "Le profil a été exporté";
        String CONTENT_EXPORT_NOTIFICATION_NOK = "Une erreur est survenue";
    }

    interface Size {
        double OUTER_MARGIN_SIZE = 20;
        double INNER_MARGIN_SIZE = 10;
        double PROFILE_FONT_SIZE = 20;
        double LAUNCH_BUTTON_FONT_SIZE = 20;
    }

}
