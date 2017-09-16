package com.mollin.loto.logic.parameters.utils;

/**
 * @author MOLLIN Florian
 */
public interface Constants {

    interface Label {
        String WINDOW_TITLE = "Loto - Paramétrage";
        String COLOR_TAB_TITLE = "Couleurs";
        String TEXT_TAB_TITLE = "Textes";
        String IMAGE_TAB_TITLE = "Images";
        // notifications
        String TITLE_NOTIFICATION_SAVE_OK = "Sauvegarde";
        String CONTENT_NOTIFICATION_SAVE_OK = "Sauvegarde de \"{0}\" réussie";
        String TITLE_NOTIFICATION_SAVE_NOK = "Sauvegarde";
        String CONTENT_NOTIFICATION_SAVE_NOK = "Une erreur est survenue lors de la sauvegarde de \"{0}\"";
        // couleurs
        String PRIMARY_COLOR_LABEL = "Couleur primaire :";
        String SECONDARY_COLOR_LABEL = "Couleur secondaire :";
        String BACKGROUND_COLOR_LABEL = "Couleur de fond :";
        String TEXT_COLOR_LABEL = "Couleur des textes :";
        String LINE_COLOR_LABEL = "Couleur des lignes :";
        String PRIMARY_COLOR_TOOLTIP_CONTENT = "Couleur principale de l'application";
        String SECONDARY_COLOR_TOOLTIP_CONTENT = "Couleur secondaire de l'application";
        String BACKGROUND_COLOR_TOOLTIP_CONTENT = "Couleur du fond de l'application";
        String TEXT_COLOR_TOOLTIP_CONTENT = "Couleur des textes du dessus de la grille";
        String LINE_COLOR_TOOLTIP_CONTENT = "Couleur des lignes de la grille";
        String GLIMPSE_CELL_LABEL = "Aperçu d'une cellule :";
        // textes
        String TITLE_ADD_TEXT_DIALOG = "Ajout de texte";
        String CONTENT_ADD_TEXT_DIALOG = "";
        String TEXT_PARAMETERS_TOOLTIP_CONTENT = "Liste des textes affichables au dessus de la grille";
        // images
        String TITLE_IMAGE_CHOOSER = "Choisissez une image";
        String TITLE_IMAGE_DIALOG = "Paramétrage de l'image";
        String IMAGES_LABEL = "Images :";
        String TIME_LABEL = "Temps (s) :";
        String BROWSE_BUTTON_LABEL = "Parcourir...";
        String TITLE_NOTIFICATION_SAVE_IMAGE_ERROR = "Erreur";
        String CONTENT_NOTIFICATION_SAVE_IMAGE_ERROR = "Erreur lors de la récupération de l'image";
        String IMAGE_PARAMETERS_TOOLTIP_CONTENT = "Liste des logos défilants à côté de la grille";
        // boutons
        String SAVE_BUTTON_LABEL = "Sauvegarder";
        String BACK_BUTTON_LABEL = "Retour";
    }

    interface Size {
        double INITIAL_WIDTH_SCENE = 290;
        double INITIAL_HEIGHT_SCENE = 575;
        double OUTER_MARGIN_SIZE = 20;
        double INNER_MARGIN_SIZE = 10;
        double IMAGE_SIZE_IN_LIST = 50;
    }

}
