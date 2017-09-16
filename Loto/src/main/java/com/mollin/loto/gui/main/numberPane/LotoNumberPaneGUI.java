package com.mollin.loto.gui.main.numberPane;

import com.mollin.loto.gui.main.utils.AdaptativeText;
import com.mollin.loto.gui.main.utils.FontSizeCalculator;
import com.mollin.loto.gui.utils.Utils;
import com.mollin.loto.logic.main.grid.Grid;
import com.mollin.loto.logic.main.grid.LotoGridListener;
import com.mollin.loto.logic.main.utils.Constants;
import javafx.beans.binding.IntegerBinding;
import javafx.geometry.Bounds;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.LinkedHashMap;

/**
 * Panneau d'affichage du dernier numéro tiré
 *
 * @author MOLLIN Florian
 */
public class LotoNumberPaneGUI extends StackPane implements LotoGridListener {
    /**
     * Paramètres du panneau
     */
    private final LotoNumberPaneGUIParameters param;
    /**
     * Panneau du contenu
     */
    private final SplitPane content;
    /**
     * Panneau contenant le text
     */
    private final StackPane textPane;
    /**
     * Panneau contenant le numéro
     */
    private final StackPane numberPane;
    /**
     * Texte graphique (associé au texte)
     */
    private Text text;
    /**
     * Texte graphique (associé au numéro)
     */
    private Text number;

    /**
     * Constructeur du panneau
     *
     * @param param Parametres du panneau
     */
    public LotoNumberPaneGUI(LotoNumberPaneGUIParameters param) {
        super();
        this.param = param;
        this.textPane = new StackPane();
        this.numberPane = new StackPane();
        this.content = new SplitPane();
        this.content.getItems().addAll(this.textPane, this.numberPane);
        this.content.setOrientation(Orientation.VERTICAL);
        this.getChildren().add(this.content);
        // dessins
        drawBackground();
        drawBorder();
        drawText();
        drawNumber();
    }

    /**
     * Dessin du fond d'écran
     */
    private void drawBackground() {
        Background background = Utils.createBackground(param.backgroundColor);
        this.textPane.setBackground(background);
        this.numberPane.setBackground(background);
        this.content.setBackground(background);
        this.setBackground(background);
    }

    /**
     * Dessin des bordures
     */
    private void drawBorder() {
        this.setBorder(Utils.createBorder(param.borderColor, param.borderSize));
    }

    /**
     * Dessin du texte
     */
    private void drawText() {
        LinkedHashMap<Integer, Bounds> fontSize2Bounds = FontSizeCalculator.createMapFontSize2Bounds(
                param.textLabel,
                Constants.FontSize.FONT_SIZE_MAX_PANE_TEXT,
                Constants.Style.ALL_STYLESHEETS,
                Constants.Style.STYLE_CLASS_PANE_TEXT
        );
        IntegerBinding fontSizeBinding = FontSizeCalculator.createBinding(this.textPane, fontSize2Bounds);
        this.text = new AdaptativeText(param.textLabel, fontSizeBinding);
        this.text.getStyleClass().add(Constants.Style.STYLE_CLASS_PANE_TEXT);
        this.text.setFill(param.textColor);
        this.textPane.getChildren().add(text);
    }

    /**
     * Dessin du numéro
     */
    private void drawNumber() {
        LinkedHashMap<Integer, Bounds> fontSize2Bounds = FontSizeCalculator.createMapFontSize2Bounds(
                String.valueOf(param.maxNumber),
                Constants.FontSize.FONT_SIZE_MAX_PANE_NUMBER,
                Constants.Style.ALL_STYLESHEETS,
                Constants.Style.STYLE_CLASS_PANE_NUMBER
        );
        IntegerBinding fontSizeBinding = FontSizeCalculator.createBinding(this.numberPane, fontSize2Bounds);
        this.number = new AdaptativeText(param.noNumberLabel, fontSizeBinding);
        this.number.getStyleClass().add(Constants.Style.STYLE_CLASS_PANE_NUMBER);
        this.number.setFill(param.numberColor);
        this.numberPane.getChildren().add(number);
    }

    @Override
    public void onUpdate(Grid grid, Integer lastNumber) {
        String strNumber;
        if (lastNumber == null) {
            strNumber = param.noNumberLabel;
        } else {
            strNumber = String.valueOf(lastNumber);
        }
        this.number.setText(strNumber);
    }

    /**
     * Renvoit le contenu (splitPane principal) du panneau
     *
     * @return Le splitPane principal du panneau
     */
    public SplitPane getContent() {
        return this.content;
    }

    /**
     * Classe des parametres du panneau de nombre
     */
    public static class LotoNumberPaneGUIParameters {
        public Color backgroundColor;
        public Color borderColor;
        public Color textColor;
        public Color numberColor;
        public Integer borderSize;
        public String textLabel;
        public String noNumberLabel;
        public int maxNumber;
    }
}
