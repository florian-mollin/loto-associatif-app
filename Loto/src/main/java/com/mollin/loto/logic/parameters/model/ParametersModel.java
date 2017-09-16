package com.mollin.loto.logic.parameters.model;

import com.mollin.loto.gui.main.button.LotoButtonPaneGUI;
import com.mollin.loto.gui.main.cell.LotoCellGUI;
import com.mollin.loto.gui.main.grid.LotoGridGUI;
import com.mollin.loto.gui.main.logo.LotoMultiLogoPaneGUI;
import com.mollin.loto.gui.main.multiTextPane.LotoMultiTextPaneGUI;
import com.mollin.loto.gui.main.numberPane.LotoNumberPaneGUI;
import com.mollin.loto.gui.main.stage.LotoParameters;
import com.mollin.loto.logic.storage.ParametersProfileStorage;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Parametres de l'application (classe "sérializée" pour la sauvegarde des
 * parametres)
 *
 * @author MOLLIN Florian
 */
public class ParametersModel {
    // couleurs
    private double[] primaryColor = {0, 0, 0, 0};
    private double[] secondaryColor = {0, 0, 0, 0};
    private double[] backgroundColor = {0, 0, 0, 0};
    private double[] textColor = {0, 0, 0, 0};
    private double[] lineColor = {0, 0, 0, 0};
    // textes
    private List<String> texts = new ArrayList<>();
    // images
    private List<Pair<String, Long>> images = new ArrayList<>();

    private ParametersModel() {
    }

    public static ParametersModel getDefault() {
        ParametersModel param = new ParametersModel();
        // couleurs
        param.primaryColor = new double[]{0.1, 0.2, 0.6, 1};
        param.secondaryColor = new double[]{1, 1, 0.3, 1};
        param.backgroundColor = new double[]{0.95, 0.95, 0.95, 1};
        param.textColor = new double[]{0, 0, 0, 1};
        param.lineColor = new double[]{0, 0, 0, 1};
        // textes
        param.texts = Arrays.asList("Quine", "Double Quine", "Carton Plein", "Carton Vide");
        // images
        param.images = new ArrayList<>();
        return param;
    }

    public static Color toColor(double[] tabColors) {
        return new Color(tabColors[0], tabColors[1], tabColors[2], tabColors[3]);
    }

    public static double[] toTab(Color color) {
        return new double[]{color.getRed(), color.getGreen(), color.getBlue(), color.getOpacity()};
    }

    public Color getPrimaryColor() {
        return toColor(primaryColor);
    }

    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor = toTab(primaryColor);
    }

    public Color getSecondaryColor() {
        return toColor(secondaryColor);
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = toTab(secondaryColor);
    }

    public Color getBackgroundColor() {
        return toColor(backgroundColor);
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = toTab(backgroundColor);
    }

    public Color getTextColor() {
        return toColor(textColor);
    }

    public void setTextColor(Color textColor) {
        this.textColor = toTab(textColor);
    }

    public Color getLineColor() {
        return toColor(lineColor);
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = toTab(lineColor);
    }

    public List<String> getTexts() {
        return this.texts;
    }

    public void setTexts(List<String> texts) {
        this.texts = texts;
    }

    public List<Pair<String, Long>> getImages() {
        return this.images;
    }

    public void setImages(List<Pair<String, Long>> images) {
        this.images = images;
    }

    // ===== Production des parametres ===== //
    public LotoParameters getLotoParameters() {
        LotoParameters param = new LotoParameters();
        param.backgroundColor = this.getBackgroundColor();
        param.padding = 20;
        param.fontSizeExitButton = 30;
        return param;
    }

    public LotoGridGUI.LotoGridGUIParameters getLotoGridGUIParameters() {
        LotoGridGUI.LotoGridGUIParameters param = new LotoGridGUI.LotoGridGUIParameters();
        param.nbCols = 9;
        param.nbRows = 10;
        param.lineSize = 3;
        param.lineColor = this.getLineColor();
        param.cellPadding = 10;
        return param;
    }

    public LotoCellGUI.LotoCellGUIParameters getLotoCellGUIParameters() {
        LotoCellGUI.LotoCellGUIParameters param = new LotoCellGUI.LotoCellGUIParameters();
        param.activeBackgroundColor = this.getPrimaryColor();
        param.activeNumberColor = this.getSecondaryColor();
        param.inactiveBackgroundColor = Color.WHITE;
        param.inactiveNumberColor = Color.BLACK;
        return param;
    }

    public LotoMultiTextPaneGUI.LotoMultiTextPaneGUIParameters getLotoMultiTextPaneGUIParameters() {
        LotoMultiTextPaneGUI.LotoMultiTextPaneGUIParameters param = new LotoMultiTextPaneGUI.LotoMultiTextPaneGUIParameters();
        param.textColor = this.getTextColor();
        param.textList = this.getTexts();
        return param;
    }

    public LotoNumberPaneGUI.LotoNumberPaneGUIParameters getLotoNumberPaneGUIParameters() {
        LotoNumberPaneGUI.LotoNumberPaneGUIParameters param = new LotoNumberPaneGUI.LotoNumberPaneGUIParameters();
        param.backgroundColor = this.getPrimaryColor();
        param.borderColor = Color.BLACK;
        param.textColor = this.getSecondaryColor();
        param.numberColor = this.getSecondaryColor();
        param.borderSize = 3;
        param.textLabel = "Dernier numéro :";
        param.maxNumber = 90;
        param.noNumberLabel = "...";
        return param;
    }

    public LotoMultiLogoPaneGUI.LotoMultiLogoPaneGUIParameters getLotoMultiLogoPaneGUIParameters(String profileName) {
        LotoMultiLogoPaneGUI.LotoMultiLogoPaneGUIParameters param = new LotoMultiLogoPaneGUI.LotoMultiLogoPaneGUIParameters();
        param.logosTimes = this.getImages().stream().map(pSL -> {
            Image image = ParametersProfileStorage.getImage(pSL.getValue0(), profileName);
            return Pair.with(image, pSL.getValue1());
        }).collect(Collectors.toList());
        return param;
    }

    public LotoButtonPaneGUI.LotoButtonPaneGUIParameters getLotoButtonPaneGUIParameters() {
        LotoButtonPaneGUI.LotoButtonPaneGUIParameters param = new LotoButtonPaneGUI.LotoButtonPaneGUIParameters();
        param.fontSize = 25;
        param.buttonSpacing = 20;
        return param;
    }

}
