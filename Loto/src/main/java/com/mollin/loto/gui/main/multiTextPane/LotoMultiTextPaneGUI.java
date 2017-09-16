package com.mollin.loto.gui.main.multiTextPane;

import com.mollin.loto.gui.main.utils.AdaptativeText;
import com.mollin.loto.gui.main.utils.FontSizeCalculator;
import com.mollin.loto.logic.main.utils.Constants;
import javafx.beans.binding.IntegerBinding;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.controlsfx.control.PopOver;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Panneau affichant un texte pouvant être changé par l'utilisateur.
 *
 * @author MOLLIN Florian
 */
public class LotoMultiTextPaneGUI extends StackPane {
    /**
     * Marge de la popover
     */
    private static final double POPOVER_MARGIN_SIZE = 5;
    /**
     * Taille de la police de la popover
     */
    private static final int POPOVER_FONT_SIZE = 12;
    /**
     * Parametres du panneau
     */
    private final LotoMultiTextPaneGUIParameters param;
    /**
     * Text graphique du panneau
     */
    private Text text;

    /**
     * Constructeur du panneau
     *
     * @param param Parametres du panneau
     */
    public LotoMultiTextPaneGUI(LotoMultiTextPaneGUIParameters param) {
        super();
        this.param = param;
        //dessins
        drawText();
        //evenements
        setEvents();
    }

    /**
     * Dessin du texte
     */
    private void drawText() {
        if (param.textList != null && !param.textList.isEmpty()) {
            LinkedHashMap<Integer, Bounds> fontSize2Bounds = FontSizeCalculator.createMapFontSize2Bounds(
                    param.textList,
                    Constants.FontSize.FONT_SIZE_MAX_MULTI_TEXT,
                    Constants.Style.ALL_STYLESHEETS,
                    Constants.Style.STYLE_CLASS_MULTI_TEXT
            );
            IntegerBinding fontSizeBinding = FontSizeCalculator.createBinding(this, fontSize2Bounds);
            String strText = "";
            if (param.textList.size() > 0) {
                strText = param.textList.get(0);
            }
            this.text = new AdaptativeText(strText, fontSizeBinding);
            this.text.getStyleClass().add(Constants.Style.STYLE_CLASS_MULTI_TEXT);
            this.text.setFill(param.textColor);
            this.getChildren().add(text);
        }
    }

    /**
     * Change le texte du panneau
     *
     * @param strText Le nouveau texte
     */
    private void setText(String strText) {
        this.text.setText(strText);
    }

    /**
     * Mise en place des evenements
     */
    private void setEvents() {
        if (param.textList != null && !param.textList.isEmpty()) {
            this.text.underlineProperty().bind(this.text.hoverProperty());
            //popover
            PopOver popover = new PopOver();
            List<Button> buttons = param.textList.stream().map((String str) -> {
                Button button = new Button(str);
                button.setMaxWidth(Double.MAX_VALUE);
                button.setOnAction((ActionEvent event) -> {
                    setText(str);
                    popover.hide();
                });
                return button;
            }).collect(Collectors.toList());
            VBox buttonsBox = new VBox();
            buttonsBox.setPadding(new Insets(POPOVER_MARGIN_SIZE));
            buttonsBox.setSpacing(POPOVER_MARGIN_SIZE);
            buttonsBox.setAlignment(Pos.CENTER);
            buttonsBox.getChildren().addAll(buttons);
            popover.setContentNode(buttonsBox);
            popover.setStyle("-fx-font-size: " + POPOVER_FONT_SIZE + "px");

            this.text.setOnMouseClicked((MouseEvent event) -> {
                popover.show(this.text);
            });
        }
    }

    /**
     * Classe des parametres du panneau
     */
    public static class LotoMultiTextPaneGUIParameters {
        public Color textColor;
        public List<String> textList;
    }

}
