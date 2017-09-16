package com.mollin.loto.gui.utils;

import com.mollin.loto.gui.main.utils.ScreenFormat;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.lang.reflect.Field;
import java.util.function.UnaryOperator;

/**
 * Classe contenant les méthodes utilitaires
 *
 * @author MOLLIN Florian
 */
public class Utils {
    /**
     * Constructeur privé car classe utilitaire
     */
    private Utils() {
    }

    /**
     * Css permettant de masquer les séparateurs (des splitPanes)
     */
    private static final String DISABLE_SPLIT_PANE_SEPARATOR_CSS = "DisableSplitPaneSeparator.css";
    /**
     * Nom de l'icone par defaut de l'application
     */
    private static final String ICON = "icon.png";

    /**
     * Permet de masquer ou de faire apparaitre les séparateurs des splitPanes.
     *
     * @param scene   La scene (pour le chargement du css)
     * @param display Vrai pour afficher les séparateurs, faux sinon
     */
    public static void displaySplitPaneSeparator(Scene scene, boolean display) {
        if (display) {
            scene.getStylesheets().remove(Utils.class.getResource(DISABLE_SPLIT_PANE_SEPARATOR_CSS).toExternalForm());
        } else {
            scene.getStylesheets().add(Utils.class.getResource(DISABLE_SPLIT_PANE_SEPARATOR_CSS).toExternalForm());
        }
    }

    /**
     * Crée un fond de couleur unie
     *
     * @param color La couleur du fond
     * @return Le fond de couleur donnée
     */
    public static Background createBackground(Color color) {
        return new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
    }

    /**
     * Crée une bordure de couleur unie
     *
     * @param color       La couleur de la bordure
     * @param borderWidth La taille de la bordure
     * @return Une bordure de couleur unie
     */
    public static Border createBorder(Color color, int borderWidth) {
        return new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(borderWidth)));
    }

    /**
     * Renvoi le format courant de l'écran
     *
     * @param stage Le stage
     * @return La taille de l'écran
     */
    public static ScreenFormat getScreenFormat(Stage stage) {
        double ratio = stage.getWidth() / stage.getHeight();
        return ratio > 1.5 ? ScreenFormat._16_9 : ScreenFormat._4_3;
    }

    /**
     * Renvoi l'image correspondant à l'icone de l'application
     *
     * @return L'image de l'icone de l'application
     */
    public static Image getImageIcon() {
        return new Image(Utils.class.getResourceAsStream(ICON));
    }

    /**
     * Définit l'icone par défaut pour le stage
     *
     * @param stage Le stage
     */
    public static void setDefaultIcon(Stage stage) {
        stage.getIcons().add(getImageIcon());
    }

    /**
     * Change le temps d'apparition d'une tooltip (grace a la reflexivité).
     *
     * @param tooltip La tooltip pour lequel il faut changer le temps
     *                d'apparition.
     */
    private static void hackTooltipStartTiming(Tooltip tooltip) {
        try {
            Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(true);
            Object objBehavior = fieldBehavior.get(tooltip);

            Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
            fieldTimer.setAccessible(true);
            Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(250)));
        } catch (Exception e) {

        }
    }

    /**
     * Créé une tooltip avec un temps d'apparition inférieur à la "normale".
     *
     * @param text Le contenu de la tooltip
     * @return La tooltip avec le texte donné et un temps d'apparition plus
     * faible
     */
    public static Tooltip createTooltip(String text) {
        Tooltip tooltip = new Tooltip(text);
        hackTooltipStartTiming(tooltip);
        return tooltip;
    }

    /**
     * Création d'un formateur n'acceptant que les chiffres
     *
     * @return Un formateur n'acceptant que les chiffres
     */
    public static TextFormatter<String> createNumberTextFormatter() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            return null;
        };
        return new TextFormatter<>(filter);
    }
}
