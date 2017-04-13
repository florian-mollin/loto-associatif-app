package com.mollin.loto.logic.parameters.utils;

/**
 *
 * @author MOLLIN Florian
 */
public interface Constants {

    public static interface Label {
        public static final String WINDOW_TITLE = "Loto - Paramétrage";
        public static final String COLOR_TAB_TITLE = "Couleurs";
        public static final String TEXT_TAB_TITLE = "Textes";
        public static final String IMAGE_TAB_TITLE = "Images";
        // notifications
        public static final String TITLE_NOTIFICATION_SAVE_OK = "Sauvegarde";
        public static final String CONTENT_NOTIFICATION_SAVE_OK = "Sauvegarde de \"{0}\" réussie";
        public static final String TITLE_NOTIFICATION_SAVE_NOK = "Sauvegarde";
        public static final String CONTENT_NOTIFICATION_SAVE_NOK = "Une erreur est survenue lors de la sauvegarde de \"{0}\"";
        // couleurs
        public static final String PRIMARY_COLOR_LABEL = "Couleur primaire :";
        public static final String SECONDARY_COLOR_LABEL = "Couleur secondaire :";
        public static final String BACKGROUND_COLOR_LABEL = "Couleur de fond :";
        public static final String TEXT_COLOR_LABEL = "Couleur des textes :";
        public static final String LINE_COLOR_LABEL = "Couleur des lignes :";
        public static final String PRIMARY_COLOR_TOOLTIP_CONTENT = "Couleur principale de l'application";
        public static final String SECONDARY_COLOR_TOOLTIP_CONTENT = "Couleur secondaire de l'application";
        public static final String BACKGROUND_COLOR_TOOLTIP_CONTENT = "Couleur du fond de l'application";
        public static final String TEXT_COLOR_TOOLTIP_CONTENT = "Couleur des textes du dessus de la grille";
        public static final String LINE_COLOR_TOOLTIP_CONTENT = "Couleur des lignes de la grille";
        public static final String GLIMPSE_CELL_LABEL = "Aperçu d'une cellule :";
        // textes
        public static final String TITLE_ADD_TEXT_DIALOG = "Ajout de texte";
        public static final String CONTENT_ADD_TEXT_DIALOG = "";
        public static final String TEXT_PARAMETERS_TOOLTIP_CONTENT = "Liste des textes affichables au dessus de la grille";
        // images
        public static final String TITLE_IMAGE_CHOOSER = "Choisissez une image";
        public static final String TITLE_IMAGE_DIALOG = "Paramétrage de l'image";
        public static final String IMAGES_LABEL = "Images :";
        public static final String TIME_LABEL = "Temps (s) :";
        public static final String BROWSE_BUTTON_LABEL = "Parcourir...";
        public static final String TITLE_NOTIFICATION_SAVE_IMAGE_ERROR = "Erreur";
        public static final String CONTENT_NOTIFICATION_SAVE_IMAGE_ERROR = "Erreur lors de la récupération de l'image";
        public static final String IMAGE_PARAMETERS_TOOLTIP_CONTENT = "Liste des logos défilants à côté de la grille";
        // boutons
        public static final String SAVE_BUTTON_LABEL = "Sauvegarder";
        public static final String BACK_BUTTON_LABEL = "Retour";
    }

    public static interface Size {
        public static final double INITIAL_WIDTH_SCENE = 290;
        public static final double INITIAL_HEIGHT_SCENE = 575;
        public static final double OUTER_MARGIN_SIZE = 20;
        public static final double INNER_MARGIN_SIZE = 10;
        public static final double IMAGE_SIZE_IN_LIST = 50;
    }

}
