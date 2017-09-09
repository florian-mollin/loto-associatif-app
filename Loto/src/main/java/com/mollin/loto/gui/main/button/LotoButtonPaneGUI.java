package com.mollin.loto.gui.main.button;

import com.mollin.loto.gui.utils.GlyphUtils;
import com.mollin.loto.gui.utils.Utils;
import com.mollin.loto.logic.main.grid.LotoGrid;
import com.mollin.loto.logic.main.utils.Constants;
import com.mollin.loto.logic.main.utils.Procedure;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Panneau des boutons (demarquer, retour, editer).
 *
 * @author MOLLIN Florian
 */
public class LotoButtonPaneGUI extends BorderPane {
    private final LotoButtonPaneGUIParameters param;
    private Button clearButton;
    private Button undoButton;
    private ToggleButton editButton;
    private final LotoGrid grid;
    private final Scene scene;
    private final Procedure editProcedure;

    /**
     * Constructeur du panneau.
     *
     * @param param Parametres
     * @param grid La grille
     * @param scene La scene
     * @param editProcedure La procedure à appeler lors du clic sur éditer
     */
    public LotoButtonPaneGUI(LotoButtonPaneGUIParameters param, LotoGrid grid, Scene scene, Procedure editProcedure) {
        super();
        this.param = param;
        this.grid = grid;
        this.scene = scene;
        this.editProcedure = editProcedure;
        // boutons
        setAndAddButtons();
        setEvents();
    }

    /**
     * Instanciation et ajout des boutons
     */
    private void setAndAddButtons() {
        // instanciation
        this.clearButton = GlyphUtils.configureButton(new Button(), Constants.Other.CLEAR_BUTTON_GLYPH, param.fontSize);
        this.clearButton.setTooltip(Utils.createTooltip(Constants.Label.CLEAR_BUTTON_TOOLTIP));
        this.undoButton = GlyphUtils.configureButton(new Button(), Constants.Other.UNDO_BUTTON_GLYPH, param.fontSize);
        this.undoButton.setTooltip(Utils.createTooltip(Constants.Label.UNDO_BUTTON_TOOLTIP));
        this.editButton = GlyphUtils.configureButton(new ToggleButton(), Constants.Other.EDIT_BUTTON_GLYPH, param.fontSize);
        this.editButton.setTooltip(Utils.createTooltip(Constants.Label.EDIT_BUTTON_TOOLTIP));
        // ajout
        HBox undoClearBox = new HBox(param.buttonSpacing, this.undoButton, this.clearButton);
        this.setLeft(undoClearBox);
        this.setRight(this.editButton);
    }

    /**
     * Définition des evenements
     */
    private void setEvents() {
        this.clearButton.setOnAction(ae -> {
            grid.clear();
        });
        this.undoButton.setOnAction(ae -> {
            grid.undo();
        });
        this.editButton.setOnAction(ae -> {
            if (!this.editButton.isSelected()) {
                editProcedure.call();
            }
            Utils.displaySplitPaneSeparator(scene, editButton.isSelected());
        });
    }

    /**
     * Classe des parametres de la grille
     */
    public static class LotoButtonPaneGUIParameters {
        public double fontSize;
        public double buttonSpacing;
    }
}
