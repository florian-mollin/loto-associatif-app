package com.mollin.loto.gui.parameters.imagePane;

import com.mollin.loto.gui.parameters.utils.TooltipUtils;
import com.mollin.loto.gui.utils.GlyphUtils;
import com.mollin.loto.logic.parameters.model.ParametersModel;
import com.mollin.loto.logic.parameters.utils.Constants;
import com.mollin.loto.logic.storage.ParametersProfileStorage;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.controlsfx.control.Notifications;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.FontAwesome.Glyph;
import org.javatuples.Pair;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Panneau permettant le parametrages des "images" (cad : des logos)
 *
 * @author MOLLIN Florian
 */
public class ParametersImagePane extends BorderPane {
    private final String profileName;
    private final ParametersModel param;
    private Button addButton;
    private BorderPane buttons;
    private ListView<Pair<String, Long>> listView;

    /**
     * Contructeur du panneau
     *
     * @param profileName Nom du profil
     * @param param       Parametres
     */
    public ParametersImagePane(String profileName, ParametersModel param) {
        super();
        this.profileName = profileName;
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
        this.listView = new ListView<>(FXCollections.observableArrayList(param.getImages()));
    }

    /**
     * Ajout des elements
     */
    private void setElements() {
        // imbrication
        this.buttons.setLeft(this.addButton);
        this.buttons.setRight(new StackPane(TooltipUtils.createTooltipIcon(Constants.Label.IMAGE_PARAMETERS_TOOLTIP_CONTENT)));
        this.setTop(this.buttons);
        this.setCenter(this.listView);
        // parametrage
        this.buttons.setPadding(new Insets(0, 0, Constants.Size.INNER_MARGIN_SIZE, 0));
        this.setPadding(new Insets(Constants.Size.OUTER_MARGIN_SIZE));
        this.listView.setCellFactory((list) -> {
            return new ListCell<Pair<String, Long>>() {
                @Override
                protected void updateItem(Pair<String, Long> item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        // graphic
                        BorderPane root = new BorderPane();
                        String imageName = item.getValue0();
                        Long imageTime = item.getValue1();
                        Image image = ParametersProfileStorage.getImage(imageName, profileName);
                        ImageView imageView = new ImageView(image);
                        imageView.setFitHeight(Constants.Size.IMAGE_SIZE_IN_LIST);
                        imageView.setFitWidth(Constants.Size.IMAGE_SIZE_IN_LIST);
                        imageView.setPreserveRatio(true);
                        Label timeLabel = new Label(imageTime + "s");
                        Label upLabel = GlyphUtils.createLabel(FontAwesome.Glyph.CARET_UP);
                        Label downLabel = GlyphUtils.createLabel(FontAwesome.Glyph.CARET_DOWN);
                        Label deleteLabel = GlyphUtils.createLabel(FontAwesome.Glyph.MINUS_CIRCLE);
                        HBox center = new HBox(imageView, timeLabel);
                        center.setSpacing(Constants.Size.INNER_MARGIN_SIZE);
                        center.setAlignment(Pos.CENTER_LEFT);
                        HBox right = new HBox(upLabel, downLabel, deleteLabel);
                        right.setSpacing(Constants.Size.INNER_MARGIN_SIZE);
                        root.setCenter(center);
                        root.setRight(right);
                        setGraphic(root);
                        // events
                        upLabel.setOnMouseEntered(me -> upLabel.setTextFill(Color.BLUE));
                        upLabel.setOnMouseExited(me -> upLabel.setTextFill(Color.BLACK));
                        upLabel.setOnMouseClicked(me -> {
                            int index = list.getItems().indexOf(item);
                            if (index >= 1) {
                                Collections.swap(list.getItems(), index, index - 1);
                            }
                        });
                        downLabel.setOnMouseEntered(me -> downLabel.setTextFill(Color.BLUE));
                        downLabel.setOnMouseExited(me -> downLabel.setTextFill(Color.BLACK));
                        downLabel.setOnMouseClicked(me -> {
                            int index = list.getItems().indexOf(item);
                            if (index < list.getItems().size() - 1) {
                                Collections.swap(list.getItems(), index, index + 1);
                            }
                        });
                        deleteLabel.setOnMouseEntered(me -> deleteLabel.setTextFill(Color.RED));
                        deleteLabel.setOnMouseExited(me -> deleteLabel.setTextFill(Color.BLACK));
                        deleteLabel.setOnMouseClicked(me -> list.getItems().remove(item));
                    }
                }
            };
        });
    }

    /**
     * Ajout des evenements
     */
    private void setEvents() {
        this.addButton.setOnAction(ae -> {
            Pair<List<File>, Long> result = new ImageDialog().showAndWait().orElse(null);
            if (result != null && result.getValue0() != null) {
                for (File image : result.getValue0()) {
                    String imageName = ParametersProfileStorage.saveImage(image, profileName);
                    if (imageName != null) {
                        this.listView.getItems().add(Pair.with(imageName, result.getValue1()));
                    } else {
                        Notifications.create()
                                .title(Constants.Label.TITLE_NOTIFICATION_SAVE_IMAGE_ERROR)
                                .text(Constants.Label.CONTENT_NOTIFICATION_SAVE_IMAGE_ERROR)
                                .showError();
                    }
                }
            }
        });
    }

    /**
     * Actualisation des parametres
     */
    public void updateParam() {
        this.param.setImages(this.listView.getItems());
    }

}
