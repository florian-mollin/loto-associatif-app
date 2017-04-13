package com.mollin.loto.gui.main.cell;

import com.mollin.loto.gui.main.utils.AdaptativeText;
import com.mollin.loto.gui.utils.Utils;
import com.mollin.loto.logic.main.utils.Constants;
import javafx.beans.binding.IntegerBinding;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Représente une cellule (le fond et le numéro).
 *
 * @author MOLLIN Florian
 */
public class LotoCellGUI extends StackPane {
    /**
     * Parametres
     */
    private final LotoCellGUIParameters param;
    /**
     * Label contenant le numéro
     */
    private Text number;
    /**
     * Numéro de la cellule
     */
    private final int cellNumber;
    /**
     * Binding sur la police du numéro
     */
    private final IntegerBinding fontSize;

    /**
     * Constructeur de la cellule.
     *
     * @param cellNumber Le numéro de la cellule
     * @param param Parametres de la cellule
     * @param fontSize Binding sur la taille de la police du numéro
     */
    public LotoCellGUI(int cellNumber, LotoCellGUIParameters param, IntegerBinding fontSize) {
        super();
        this.param = param;
        this.cellNumber = cellNumber;
        this.fontSize = fontSize;
        // dessins
        drawNumber();
        // etat
        off();
    }

    /**
     * Dessine le nombre de la cellule
     */
    private void drawNumber() {
        number = new AdaptativeText(String.valueOf(cellNumber), this.fontSize);
        number.getStyleClass().add(Constants.Style.STYLE_CLASS_CELL_TEXT);
        this.getChildren().add(number);
    }

    /**
     * Rend active ou inactive la cellule.
     *
     * @param isActive Vrai si la cellule doit etre activée
     */
    public void setActive(boolean isActive) {
        if (isActive) {
            on();
        } else {
            off();
        }
    }

    /**
     * Active la cellule
     */
    private void on() {
        this.setBackground(Utils.createBackground(param.activeBackgroundColor));
        this.number.setFill(param.activeNumberColor);
    }

    /**
     * Desactive la cellule
     */
    private void off() {
        this.setBackground(Utils.createBackground(param.inactiveBackgroundColor));
        this.number.setFill(param.inactiveNumberColor);
    }

    /**
     * Renvoi le texte du nombre.
     *
     * @return Le texte représentant le nombre
     */
    public Text getNumber() {
        return this.number;
    }

    /**
     * Classe des parametres de la cellule
     */
    public static class LotoCellGUIParameters {
        public Color activeBackgroundColor;
        public Color inactiveBackgroundColor;
        public Color activeNumberColor;
        public Color inactiveNumberColor;
    }
}
