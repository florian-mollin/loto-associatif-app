package com.mollin.loto.gui.utils;

import javafx.scene.control.ButtonBase;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import org.controlsfx.glyphfont.FontAwesome.Glyph;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;

/**
 * Classe utilitaire manipulant les "Glyph"
 *
 * @author MOLLIN Florian
 */
public class GlyphUtils {
    private static final GlyphFont GLYPH_FONT_AWESOME = GlyphFontRegistry.font("FontAwesome");

    /**
     * Configure un bouton pour lui ajouter un texte et un icon
     *
     * @param <T>            Le type de bouton
     * @param button         Le bouton à parametrer
     * @param icon           L'icon du bouton
     * @param text           Le label du bouton
     * @param fontSize       La taille de la police
     * @param contentDisplay La position de l'icone
     * @return Le bouton avec l'icone et le label
     */
    public static <T extends ButtonBase> T configureButton(T button, Glyph icon, String text, Double fontSize, ContentDisplay contentDisplay) {
        if (text != null) {
            button.setText(text);
            if (fontSize != null) {
                button.setStyle("-fx-font-size: " + fontSize + "px;");
            }
        }
        if (icon != null) {
            org.controlsfx.glyphfont.Glyph glyphFont = GLYPH_FONT_AWESOME.create(icon);
            if (fontSize != null && fontSize > 0) {
                glyphFont.size(fontSize);
            }
            button.setGraphic(glyphFont);
        }
        if (contentDisplay != null) {
            button.setContentDisplay(contentDisplay);
        }
        return button;
    }

    /**
     * @see GlyphUtils#configureButton(javafx.scene.control.ButtonBase,
     * org.controlsfx.glyphfont.FontAwesome.Glyph, java.lang.String,
     * java.lang.Double, javafx.scene.control.ContentDisplay)
     */
    public static <T extends ButtonBase> T configureButton(T button, Glyph icon, String text, Double fontSize) {
        return configureButton(button, icon, text, fontSize, null);
    }

    /**
     * @see GlyphUtils#configureButton(javafx.scene.control.ButtonBase,
     * org.controlsfx.glyphfont.FontAwesome.Glyph, java.lang.String,
     * java.lang.Double, javafx.scene.control.ContentDisplay)
     */
    public static <T extends ButtonBase> T configureButton(T button, Glyph icon, String text) {
        return configureButton(button, icon, text, null, null);
    }

    /**
     * @see GlyphUtils#configureButton(javafx.scene.control.ButtonBase,
     * org.controlsfx.glyphfont.FontAwesome.Glyph, java.lang.String,
     * java.lang.Double, javafx.scene.control.ContentDisplay)
     */
    public static <T extends ButtonBase> T configureButton(T button, Glyph icon) {
        return configureButton(button, icon, null, null, null);
    }

    /**
     * @see GlyphUtils#configureButton(javafx.scene.control.ButtonBase,
     * org.controlsfx.glyphfont.FontAwesome.Glyph, java.lang.String,
     * java.lang.Double, javafx.scene.control.ContentDisplay)
     */
    public static <T extends ButtonBase> T configureButton(T button, Glyph icon, Double fontSize) {
        return configureButton(button, icon, null, fontSize, null);
    }

    /**
     * Crée un icon
     *
     * @param icon     L'icone à créer
     * @param fontSize La taille de l'icone
     * @return L'icone (sous forme de Label)
     */
    public static Label createLabel(Glyph icon, Double fontSize) {
        if (icon != null) {
            org.controlsfx.glyphfont.Glyph glyphFont = GLYPH_FONT_AWESOME.create(icon);
            if (fontSize != null && fontSize > 0) {
                glyphFont.size(fontSize);
            }
            return glyphFont;
        }
        return new Label();
    }

    /**
     * @see GlyphUtils#createLabel(org.controlsfx.glyphfont.FontAwesome.Glyph,
     * java.lang.Double)
     */
    public static Label createLabel(Glyph icon) {
        return createLabel(icon, null);
    }

}
