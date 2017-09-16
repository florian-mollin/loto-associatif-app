package com.mollin.loto.gui.profile.stage;

import com.mollin.loto.gui.main.stage.LotoStageConfigurator;
import com.mollin.loto.gui.parameters.stage.ParametersStageConfigurator;
import com.mollin.loto.gui.profile.aboutpane.AboutPane;
import com.mollin.loto.gui.utils.DialogUtils;
import com.mollin.loto.gui.utils.GlyphUtils;
import com.mollin.loto.gui.utils.Utils;
import com.mollin.loto.logic.parameters.model.ParametersModel;
import com.mollin.loto.logic.profile.utils.Constants;
import com.mollin.loto.logic.storage.ParametersProfileStorage;
import com.mollin.loto.logic.utils.FileUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.apache.commons.collections4.ListUtils;
import org.controlsfx.control.Notifications;
import org.javatuples.Pair;

import java.io.File;
import java.text.MessageFormat;
import java.util.stream.Collectors;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Configurateur de la fenetre des profils
 *
 * @author MOLLIN Florian
 */
public class ProfileStageConfigurator {
    private static Scene scene;
    private static Stage stage;
    // panneaux
    private static StackPane rootPane;
    private static BorderPane contentPane;
    private static HBox profilePane;
    private static StackPane launchPane;
    private static VBox buttonsPane;
    private static HBox createUpdateDeleteButtonsPane;
    private static HBox importExportButtonsPane;
    // boutons
    private static Button launchButton;
    private static Button createButton;
    private static Button updateButton;
    private static Button deleteButton;
    private static Button importButton;
    private static Button exportButton;
    private static Button aboutButton;
    // elements
    private static Label profileLabel;
    private static ChoiceBox<Pair<String, ParametersModel>> profileChoice;

    /**
     * Classe non instanciable
     */
    private ProfileStageConfigurator() {
    }

    /**
     * Configuration du stage
     *
     * @param stage Le stage à configurer
     */
    public static void configureStage(Stage stage) {
        ProfileStageConfigurator.stage = stage;
        initElements();
        setElements();
        setEvents();
        Utils.setDefaultIcon(stage);
        stage.setTitle(Constants.Label.WINDOW_TITLE);
        stage.setScene(scene);
    }

    /**
     * Initialisation des elements
     */
    private static void initElements() {
        // === instanciation ===
        // panneaux
        rootPane = new StackPane();
        contentPane = new BorderPane();
        profilePane = new HBox();
        launchPane = new StackPane();
        buttonsPane = new VBox();
        createUpdateDeleteButtonsPane = new HBox();
        importExportButtonsPane = new HBox();
        scene = new Scene(rootPane);
        // boutons
        launchButton = GlyphUtils.configureButton(new Button(), Constants.Label.LAUNCH_BUTTON_GLYPH, Constants.Label.LAUNCH_BUTTON_LABEL, Constants.Size.LAUNCH_BUTTON_FONT_SIZE);
        createButton = GlyphUtils.configureButton(new Button(), Constants.Label.CREATE_BUTTON_GLYPH, Constants.Label.CREATE_BUTTON_LABEL);
        updateButton = GlyphUtils.configureButton(new Button(), Constants.Label.UPDATE_BUTTON_GLYPH, Constants.Label.UPDATE_BUTTON_LABEL);
        deleteButton = GlyphUtils.configureButton(new Button(), Constants.Label.DELETE_BUTTON_GLYPH, Constants.Label.DELETE_BUTTON_LABEL);
        importButton = GlyphUtils.configureButton(new Button(), Constants.Label.IMPORT_BUTTON_GLYPH, Constants.Label.IMPORT_BUTTON_LABEL);
        exportButton = GlyphUtils.configureButton(new Button(), Constants.Label.EXPORT_BUTTON_GLYPH, Constants.Label.EXPORT_BUTTON_LABEL);
        aboutButton = GlyphUtils.configureButton(new Button(), Constants.Label.ABOUT_BUTTON_GLYPH);
        // elements
        profileLabel = new Label(Constants.Label.PROFILE_LABEL);
        profileChoice = new ChoiceBox<>();
    }

    /**
     * Ajout des elements
     */
    private static void setElements() {
        // imbrication
        rootPane.getChildren().addAll(contentPane, aboutButton);
        contentPane.setTop(new VBox(new StackPane(profilePane), new Separator()));
        contentPane.setCenter(launchPane);
        contentPane.setBottom(new StackPane(buttonsPane));
        profilePane.getChildren().addAll(profileLabel, profileChoice);
        launchPane.getChildren().add(launchButton);
        buttonsPane.getChildren().addAll(createUpdateDeleteButtonsPane, importExportButtonsPane);
        createUpdateDeleteButtonsPane.getChildren().addAll(createButton, updateButton, deleteButton);
        importExportButtonsPane.getChildren().addAll(importButton, exportButton);
        // parametrages
        StackPane.setAlignment(aboutButton, Pos.TOP_RIGHT);
        contentPane.setPadding(new Insets(Constants.Size.OUTER_MARGIN_SIZE));
        profilePane.setPadding(new Insets(Constants.Size.INNER_MARGIN_SIZE));
        profilePane.setSpacing(Constants.Size.INNER_MARGIN_SIZE);
        profilePane.setAlignment(Pos.CENTER);
        profileChoice.setStyle("-fx-font-size: " + Constants.Size.PROFILE_FONT_SIZE + "px");

        launchPane.setPadding(new Insets(Constants.Size.INNER_MARGIN_SIZE));
        launchButton.setStyle("-fx-font-size: " + Constants.Size.LAUNCH_BUTTON_FONT_SIZE + "px; -fx-font-weight: bold;");

        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setPadding(new Insets(Constants.Size.INNER_MARGIN_SIZE));
        buttonsPane.setSpacing(Constants.Size.INNER_MARGIN_SIZE);
        createUpdateDeleteButtonsPane.setSpacing(Constants.Size.INNER_MARGIN_SIZE);
        createUpdateDeleteButtonsPane.setAlignment(Pos.CENTER);
        importExportButtonsPane.setSpacing(Constants.Size.INNER_MARGIN_SIZE);
        importExportButtonsPane.setAlignment(Pos.CENTER);
        // liste des profils
        refreshProfiles();
        profileChoice.setConverter(new StringConverter<Pair<String, ParametersModel>>() {
            @Override
            public String toString(Pair<String, ParametersModel> pair) {
                return pair.getValue0();
            }

            @Override
            public Pair<String, ParametersModel> fromString(String string) {
                // non utilisé
                return null;
            }
        });
        // proprietes boutons
        launchButton.disableProperty().bind(profileChoice.valueProperty().isNull());
        updateButton.disableProperty().bind(profileChoice.valueProperty().isNull());
        deleteButton.disableProperty().bind(profileChoice.valueProperty().isNull());
        exportButton.disableProperty().bind(profileChoice.valueProperty().isNull());
    }

    /**
     * Ajout des evenements
     */
    private static void setEvents() {
        stage.setOnShowing(ae -> refreshProfiles());
        aboutButton.setOnAction(ae -> DialogUtils.infosDialog("A propos", new AboutPane()));
        createButton.setOnAction(ae -> {
            String profileName = DialogUtils.inputDialog(Constants.Label.TITLE_CREATE_DIALOG, Constants.Label.TITLE_CREATE_DIALOG,
                    Constants.Label.CONTENT_CREATE_DIALOG,
                    s -> s != null && !s.isEmpty() && !s.trim().isEmpty()
                            && !profileChoice.getItems().stream().map(Pair::getValue0).collect(Collectors.toList()).contains(s)
            );
            if (profileName != null) {
                profileName = FileUtils.toValidFileName(profileName);
                goParametersStage(profileName, ParametersModel.getDefault());
            }
        });
        updateButton.setOnAction(ae -> {
            if (profileChoice.getValue() != null) {
                goParametersStage(profileChoice.getValue().getValue0(), profileChoice.getValue().getValue1());
            }
        });
        deleteButton.setOnAction(ae -> {
            if (profileChoice.getValue() != null) {
                ButtonType choice = DialogUtils.confirmationDialog(Constants.Label.TITLE_DELETE_DIALOG, Constants.Label.TITLE_DELETE_DIALOG,
                        MessageFormat.format(Constants.Label.CONTENT_DELETE_DIALOG, profileChoice.getValue().getValue0()));
                if (choice == ButtonType.OK) {
                    ParametersProfileStorage.deleteParametersProfile(profileChoice.getValue().getValue0());
                    refreshProfiles();
                }
            }
        });
        launchButton.setOnAction(ae -> {
            if (profileChoice.getValue() != null) {
                goLotoStage(profileChoice.getValue().getValue0(), profileChoice.getValue().getValue1());
            }
        });
        exportButton.setOnAction(ae -> {
            File directory = new DirectoryChooser().showDialog(stage);
            if (directory != null) {
                boolean success = ParametersProfileStorage.exportProfile(profileChoice.getValue().getValue0(), directory);
                Notifications notif = Notifications.create()
                        .title(Constants.Label.TITLE_EXPORT_NOTIFICATION)
                        .text(success ? Constants.Label.CONTENT_EXPORT_NOTIFICATION_OK : Constants.Label.CONTENT_EXPORT_NOTIFICATION_NOK);
                if (success) {
                    notif.showInformation();
                } else {
                    notif.showError();
                }
            }
        });
        importButton.setOnAction(ae -> {
            FileChooser profileChooser = new FileChooser();
            profileChooser.getExtensionFilters().add(
                    new ExtensionFilter("Profils Loto", "*" + com.mollin.loto.logic.main.utils.Constants.File.PROFILE_FILE_EXTENSION)
            );
            File profileFile = profileChooser.showOpenDialog(stage);
            if (profileFile != null) {
                boolean success = ParametersProfileStorage.importProfile(profileFile);
                Notifications notif = Notifications.create()
                        .title(Constants.Label.TITLE_IMPORT_NOTIFICATION)
                        .text(success ? Constants.Label.CONTENT_IMPORT_NOTIFICATION_OK : Constants.Label.CONTENT_IMPORT_NOTIFICATION_NOK);
                if (success) {
                    refreshProfiles();
                    notif.showInformation();
                } else {
                    notif.showError();
                }
            }
        });
    }

    /**
     * Lancement de la fenetre de parametrage
     *
     * @param profileName Nom du profil
     * @param param       Parametres
     */
    private static void goParametersStage(String profileName, ParametersModel param) {
        Stage paramStage = new Stage();
        ParametersStageConfigurator.configureStage(paramStage, profileName, param, stage);
        stage.hide();
        paramStage.show();
    }

    /**
     * Lancement de la fenetre du loto
     *
     * @param profileName Nom du profil
     * @param param       Parametres
     */
    private static void goLotoStage(String profileName, ParametersModel param) {
        Stage lotoStage = new Stage();
        LotoStageConfigurator.configureStage(lotoStage, param, profileName, stage);
        stage.hide();
        lotoStage.show();
    }

    /**
     * Actualisation des profils
     */
    private static void refreshProfiles() {
        Pair<String, ParametersModel> lastChoice = profileChoice.getValue();
        profileChoice.setItems(observableArrayList(ParametersProfileStorage.loadAllParametersProfile()));
        if (lastChoice != null) {
            String profileName = lastChoice.getValue0();
            int index = ListUtils.indexOf(profileChoice.getItems(), p -> p.getValue0().equals(profileName));
            if (index >= 0) {
                profileChoice.getSelectionModel().select(index);
            }
        }
    }

}
