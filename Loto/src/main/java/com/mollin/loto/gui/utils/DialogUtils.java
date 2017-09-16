package com.mollin.loto.gui.utils;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.function.Function;

/**
 * Classe utilitaire pour les dialogues
 *
 * @author MOLLIN Florian
 */
public class DialogUtils {
    /**
     * Constructeur privé car classe utilitaire
     */
    private DialogUtils() {
    }

    /**
     * Ouverture d'un dialogue de confirmation
     *
     * @param title   Le titre du dialogue
     * @param header  Header du dialogue
     * @param content Contenu du dialogue
     * @return Le bouton pressé
     */
    public static ButtonType confirmationDialog(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Utils.setDefaultIcon((Stage) alert.getDialogPane().getScene().getWindow());
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(header);
        Optional<ButtonType> result = alert.showAndWait();
        return result.orElse(null);
    }

    /**
     * Ouverture d'un dialogue d'informations
     *
     * @param title   Le titre de la fenetre de dialogue
     * @param content Le contenu de la fenetre
     */
    public static void infosDialog(String title, Node content) {
        ButtonType cancelButton = new ButtonType("Fermer", ButtonBar.ButtonData.CANCEL_CLOSE);
        Dialog<Void> dialog = new Dialog<>();
        Utils.setDefaultIcon((Stage) dialog.getDialogPane().getScene().getWindow());
        dialog.setTitle(title);
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().add(cancelButton);
        dialog.showAndWait();
    }

    /**
     * Ouverture d'un dialogue d'input
     *
     * @param title       Le titre du dialogue
     * @param header      Header du dialogue
     * @param content     Contenu du dialogue
     * @param textChecker Fonction de vérification du texte (active le bouton
     *                    'ok')
     * @return La chaine de caractère de l'input (null si 'annuler')
     */
    public static String inputDialog(String title, String header, String content, Function<String, Boolean> textChecker) {
        TextInputDialog dialog = new TextInputDialog();
        Utils.setDefaultIcon((Stage) dialog.getDialogPane().getScene().getWindow());
        dialog.setTitle(title);
        dialog.setContentText(content);
        dialog.setHeaderText(header);
        StringProperty resultProperty = dialog.getEditor().textProperty();
        BooleanBinding isValid = new BooleanBinding() {
            {
                super.bind(resultProperty);
            }

            @Override
            protected boolean computeValue() {
                return textChecker.apply(resultProperty.get());
            }
        };
        Node okButton = dialog.getDialogPane().lookupButton(ButtonType.OK);
        okButton.disableProperty().bind(isValid.not());
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }
}
