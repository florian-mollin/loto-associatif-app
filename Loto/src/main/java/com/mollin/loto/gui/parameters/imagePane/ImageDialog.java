package com.mollin.loto.gui.parameters.imagePane;

import com.mollin.loto.gui.utils.Utils;
import com.mollin.loto.logic.parameters.utils.Constants;
import java.io.File;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.javatuples.Pair;

/**
 * Dialogue pour l'ajout d'images et d'un temps d'affichage
 *
 * @author MOLLIN Florian
 */
public class ImageDialog extends Dialog<Pair<List<File>, Long>> {
    private static final FileChooser IMAGE_CHOOSER = createImageChooser();
    private Label imageLabel;
    private Label selectedImagesLabel;
    private Button browseButton;
    private Label timeLabel;
    private TextField timeField;
    private GridPane root;
    private ObjectProperty<List<File>> selectedImages;

    /**
     * Contructeur du dialogue
     */
    public ImageDialog() {
        super();
        Utils.setDefaultIcon((Stage) this.getDialogPane().getScene().getWindow());
        initElements();
        setElements();
        setEvents();
    }

    /**
     * Initialisation des elements
     */
    private void initElements() {
        this.imageLabel = new Label(Constants.Label.IMAGES_LABEL);
        this.selectedImagesLabel = new Label("...");
        this.browseButton = new Button(Constants.Label.BROWSE_BUTTON_LABEL);
        this.timeLabel = new Label(Constants.Label.TIME_LABEL);
        this.timeField = new TextField();
        this.root = new GridPane();
        this.selectedImages = new SimpleObjectProperty<>(null);
    }

    /**
     * Parametrage des elements
     */
    private void setElements() {
        // imbrication
        this.getDialogPane().setContent(root);
        this.root.add(imageLabel, 0, 0);
        this.root.add(selectedImagesLabel, 1, 0);
        this.root.add(browseButton, 2, 0);
        this.root.add(timeLabel, 0, 1);
        this.root.add(timeField, 1, 1);
        // parametrage
        this.root.setHgap(Constants.Size.INNER_MARGIN_SIZE);
        this.root.setVgap(Constants.Size.INNER_MARGIN_SIZE);
        GridPane.setHalignment(this.imageLabel, HPos.RIGHT);
        GridPane.setHalignment(this.timeLabel, HPos.RIGHT);
        this.setTitle(Constants.Label.TITLE_IMAGE_DIALOG);
        this.setHeaderText(Constants.Label.TITLE_IMAGE_DIALOG);
        this.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        this.timeField.setTextFormatter(Utils.createNumberTextFormatter());
        this.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                return new Pair<>(this.selectedImages.get(), Long.parseLong(this.timeField.getText()));
            }
            return null;
        });
    }

    /**
     * Ajout des evenements
     */
    private void setEvents() {
        this.browseButton.setOnAction(ae -> {
            this.selectedImages.set(IMAGE_CHOOSER.showOpenMultipleDialog(this.getDialogPane().getScene().getWindow()));
            if (this.selectedImages.get() != null) {
                selectedImagesLabel.setText(String.valueOf(this.selectedImages.get().size()));
            } else {
                selectedImagesLabel.setText("...");
            }
        });
        this.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(selectedImages.isNull()
                .or(timeField.textProperty().isNull())
                .or(timeField.textProperty().isEmpty()));
    }

    /**
     * Création d'un selecteur d'image
     *
     * @return Le selecteur de fichiers (images)
     */
    private static FileChooser createImageChooser() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle(Constants.Label.TITLE_IMAGE_CHOOSER);
        chooser.getExtensionFilters().add(new ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"));
        return chooser;
    }
}
