package com.mollin.loto.gui.main.utils;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberExpression;
import javafx.beans.binding.StringExpression;
import javafx.scene.text.Text;

/**
 * Texte adaptatif en fonction d'un 'binding' avec la taille de la police.
 *
 * @author MOLLIN Florian
 */
public class AdaptativeText extends Text {

    /**
     * Constructeur du texte
     *
     * @param label    Le label
     * @param fontSize Le binding correspondant à la taille de la police
     */
    public AdaptativeText(String label, NumberExpression fontSize) {
        super(label);
        StringExpression fontSizeExpression = Bindings.concat("-fx-font-size: ", fontSize, "px;");
        this.styleProperty().bind(fontSizeExpression);
    }

    @Override
    public double minWidth(double height) {
        return 0.0;
    }

    @Override
    public double minHeight(double height) {
        return 0.0;
    }
}
