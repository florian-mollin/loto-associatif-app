package com.mollin.loto.gui.parameters.colorPane;

import com.mollin.loto.gui.main.cell.LotoCellGUI;
import com.mollin.loto.gui.parameters.utils.TooltipUtils;
import com.mollin.loto.gui.utils.Utils;
import com.mollin.loto.logic.parameters.model.ParametersModel;
import com.mollin.loto.logic.parameters.utils.Constants;
import javafx.beans.binding.IntegerBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Panneau pour le parametrage des couleurs
 *
 * @author MOLLIN Florian
 */
public class ParametersColorPane extends GridPane {
    private final ParametersModel param;
    private ColorPicker primaryColorPicker;
    private ColorPicker secondaryColorPicker;
    private ColorPicker backgroundColorPicker;
    private ColorPicker textColorPicker;
    private ColorPicker lineColorPicker;
    private LotoCellGUI glimpseCell;

    /**
     * Constructeur du panneau
     *
     * @param param Parametres
     */
    public ParametersColorPane(ParametersModel param) {
        super();
        this.param = param;
        initElements();
        setElements();
        setEvents();
    }

    /**
     * Initialisation des elements
     */
    private void initElements() {
        this.primaryColorPicker = createColorPicker(param.getPrimaryColor());
        this.secondaryColorPicker = createColorPicker(param.getSecondaryColor());
        this.backgroundColorPicker = createColorPicker(param.getBackgroundColor());
        this.textColorPicker = createColorPicker(param.getTextColor());
        this.lineColorPicker = createColorPicker(param.getLineColor());
        this.glimpseCell = createGlimpseCell();
    }

    /**
     * Parametrage des elements
     */
    private void setElements() {
        // labels
        this.add(new Label(Constants.Label.PRIMARY_COLOR_LABEL), 0, 0);
        this.add(new Label(Constants.Label.SECONDARY_COLOR_LABEL), 0, 1);
        this.add(new Label(Constants.Label.BACKGROUND_COLOR_LABEL), 0, 2);
        this.add(new Label(Constants.Label.TEXT_COLOR_LABEL), 0, 3);
        this.add(new Label(Constants.Label.LINE_COLOR_LABEL), 0, 4);
        // color picker
        this.add(primaryColorPicker, 1, 0);
        this.add(secondaryColorPicker, 1, 1);
        this.add(backgroundColorPicker, 1, 2);
        this.add(textColorPicker, 1, 3);
        this.add(lineColorPicker, 1, 4);
        // tooltips
        this.add(TooltipUtils.createTooltipIcon(Constants.Label.PRIMARY_COLOR_TOOLTIP_CONTENT), 2, 0);
        this.add(TooltipUtils.createTooltipIcon(Constants.Label.SECONDARY_COLOR_TOOLTIP_CONTENT), 2, 1);
        this.add(TooltipUtils.createTooltipIcon(Constants.Label.BACKGROUND_COLOR_TOOLTIP_CONTENT), 2, 2);
        this.add(TooltipUtils.createTooltipIcon(Constants.Label.TEXT_COLOR_TOOLTIP_CONTENT), 2, 3);
        this.add(TooltipUtils.createTooltipIcon(Constants.Label.LINE_COLOR_TOOLTIP_CONTENT), 2, 4);
        // apercu d'une cellule
        this.add(new Separator(), 0, 5, 3, 1);
        this.add(new Label(Constants.Label.GLIMPSE_CELL_LABEL), 0, 6, 3, 1);
        glimpseCell.setBorder(Utils.createBorder(param.getLotoGridGUIParameters().lineColor, param.getLotoGridGUIParameters().lineSize));
        this.add(glimpseCell, 0, 7, 3, 1);
        // parametrage
        this.setAlignment(Pos.CENTER);
        this.setHgap(Constants.Size.INNER_MARGIN_SIZE);
        this.setVgap(Constants.Size.INNER_MARGIN_SIZE);
        this.setPadding(new Insets(Constants.Size.OUTER_MARGIN_SIZE));
    }

    /**
     * Ajout des evenements
     */
    private void setEvents() {
        this.primaryColorPicker.setOnAction(ae -> {
            this.glimpseCell.setBackground(Utils.createBackground(this.primaryColorPicker.getValue()));
        });
        this.secondaryColorPicker.setOnAction(ae -> {
            this.glimpseCell.getNumber().setFill(this.secondaryColorPicker.getValue());
        });
        this.lineColorPicker.setOnAction(ae -> {
            this.glimpseCell.setBorder(Utils.createBorder(this.lineColorPicker.getValue(), param.getLotoGridGUIParameters().lineSize));
        });
    }

    /**
     * Mise à jour des parametres
     */
    public void updateParam() {
        this.param.setPrimaryColor(primaryColorPicker.getValue());
        this.param.setSecondaryColor(secondaryColorPicker.getValue());
        this.param.setBackgroundColor(backgroundColorPicker.getValue());
        this.param.setTextColor(textColorPicker.getValue());
        this.param.setLineColor(lineColorPicker.getValue());
    }

    /**
     * Crétion d'un "color picker" sans label
     *
     * @param color La couleur initiale
     * @return Le "color picker"
     */
    private static ColorPicker createColorPicker(Color color) {
        ColorPicker cp = new ColorPicker(color);
        cp.setStyle("-fx-color-label-visible: false ;");
        return cp;
    }

    /**
     * Création de l'apercu de la cellule
     *
     * @return L'apercu de la cellule
     */
    private LotoCellGUI createGlimpseCell() {
        LotoCellGUI cell = new LotoCellGUI(42, param.getLotoCellGUIParameters(), new IntegerBinding() {
            @Override
            protected int computeValue() {
                return 130;
            }
        });
        cell.setActive(true);
        return cell;
    }

}
