package com.mollin.loto.gui.parameters.textpane;

import com.mollin.loto.gui.parameters.utils.TooltipUtils;
import com.mollin.loto.gui.utils.DialogUtils;
import com.mollin.loto.gui.utils.GlyphUtils;
import com.mollin.loto.logic.parameters.model.ParametersModel;
import com.mollin.loto.logic.parameters.utils.Constants;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.controlsfx.glyphfont.FontAwesome.Glyph;

/**
 * Panneau de parametrage des textes (textes en haut de la grille)
 *
 * @author MOLLIN Florian
 */
public class ParametersTextPane extends BorderPane {
    private final ParametersModel param;
    private Button addButton;
    private BorderPane buttons;
    private ListView<String> listView;

    /**
     * Constructeur du panneau
     *
     * @param param Parametres
     */
    public ParametersTextPane(ParametersModel param) {
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
        this.buttons = new BorderPane();
        this.addButton = GlyphUtils.configureButton(new Button(), Glyph.PLUS_CIRCLE);
        this.listView = new ListView<>(FXCollections.observableArrayList(param.getTexts()));
    }

    /**
     * Ajout des elements
     */
    private void setElements() {
        // imbrication
        this.buttons.setLeft(this.addButton);
        this.buttons.setRight(new StackPane(TooltipUtils.createTooltipIcon(Constants.Label.TEXT_PARAMETERS_TOOLTIP_CONTENT)));
        this.setTop(this.buttons);
        this.setCenter(this.listView);
        // parametrage
        this.buttons.setPadding(new Insets(0, 0, Constants.Size.INNER_MARGIN_SIZE, 0));
        this.setPadding(new Insets(Constants.Size.OUTER_MARGIN_SIZE));
        this.listView.setCellFactory(list -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // graphic
                    BorderPane root = new BorderPane();
                    Label label = new Label(item);
                    Label deleteLabel = GlyphUtils.createLabel(Glyph.MINUS_CIRCLE);
                    root.setCenter(new HBox(label));
                    root.setRight(deleteLabel);
                    setGraphic(root);
                    // events
                    deleteLabel.setOnMouseEntered(me -> deleteLabel.setTextFill(Color.RED));
                    deleteLabel.setOnMouseExited(me -> deleteLabel.setTextFill(Color.BLACK));
                    deleteLabel.setOnMouseClicked(me -> list.getItems().remove(item));
                }
            }
        });
    }

    /**
     * Ajout des evenements
     */
    private void setEvents() {
        this.addButton.setOnAction(ae -> {
            String newText = DialogUtils.inputDialog(Constants.Label.TITLE_ADD_TEXT_DIALOG, Constants.Label.TITLE_ADD_TEXT_DIALOG,
                    Constants.Label.CONTENT_ADD_TEXT_DIALOG,
                    s -> s != null && !s.isEmpty()
                            && !listView.getItems().contains(s)
            );
            if (newText != null) {
                listView.getItems().add(newText);
            }
        });
    }

    /**
     * Actualisation des parametres
     */
    public void updateParam() {
        this.param.setTexts(this.listView.getItems());
    }

}
