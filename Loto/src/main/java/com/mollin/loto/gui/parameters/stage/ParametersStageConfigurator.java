package com.mollin.loto.gui.parameters.stage;

import com.mollin.loto.gui.parameters.colorPane.ParametersColorPane;
import com.mollin.loto.gui.parameters.imagePane.ParametersImagePane;
import com.mollin.loto.gui.parameters.textPane.ParametersTextPane;
import com.mollin.loto.gui.utils.Utils;
import com.mollin.loto.logic.parameters.model.ParametersModel;
import com.mollin.loto.logic.parameters.utils.Constants;
import com.mollin.loto.logic.storage.ParametersProfileStorage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.text.MessageFormat;

/**
 * Configurateur pour la fenetre des parametres
 *
 * @author MOLLIN Florian
 */
public class ParametersStageConfigurator {
    private static Scene scene;
    private static Stage stage;
    private static String profileName;
    private static ParametersModel param;
    private static Stage backStage;
    private static BorderPane rootPane;
    // tabs
    private static TabPane tabPane;
    private static Tab colorTab;
    private static Tab textTab;
    private static Tab imageTab;
    // elements
    private static ParametersColorPane colorPane;
    private static ParametersTextPane textPane;
    private static ParametersImagePane imagePane;
    // boutons
    private static HBox buttonsPane;
    private static Button saveButton;
    private static Button backButton;

    /**
     * Classe non instanciable
     */
    private ParametersStageConfigurator() {
    }

    /**
     * Configuration du stage
     *
     * @param stage       Le stage à configurer
     * @param profileName Le nom du profil
     * @param param       Parametres
     * @param backStage   Stage sur lequel on doit revenir lors d'un retour
     *                    arriere
     */
    public static void configureStage(Stage stage, String profileName, ParametersModel param, Stage backStage) {
        ParametersStageConfigurator.stage = stage;
        ParametersStageConfigurator.profileName = profileName;
        ParametersStageConfigurator.param = param;
        ParametersStageConfigurator.backStage = backStage;
        initElements();
        setElements();
        setEvents();
        Utils.setDefaultIcon(stage);
        stage.setTitle(Constants.Label.WINDOW_TITLE);
        stage.setWidth(Constants.Size.INITIAL_WIDTH_SCENE);
        stage.setHeight(Constants.Size.INITIAL_HEIGHT_SCENE);
        stage.setScene(scene);
    }

    /**
     * Initialisation des elements
     */
    private static void initElements() {
        // === instanciation ===
        // panneaux
        rootPane = new BorderPane();
        tabPane = new TabPane();
        colorTab = new Tab(Constants.Label.COLOR_TAB_TITLE);
        colorPane = new ParametersColorPane(param);
        textTab = new Tab(Constants.Label.TEXT_TAB_TITLE);
        textPane = new ParametersTextPane(param);
        imageTab = new Tab(Constants.Label.IMAGE_TAB_TITLE);
        imagePane = new ParametersImagePane(profileName, param);
        scene = new Scene(rootPane);
        // boutons
        buttonsPane = new HBox();
        saveButton = new Button(Constants.Label.SAVE_BUTTON_LABEL);
        backButton = new Button(Constants.Label.BACK_BUTTON_LABEL);
    }

    /**
     * Ajout des elements
     */
    private static void setElements() {
        // imbrication
        rootPane.setCenter(tabPane);
        rootPane.setBottom(new VBox(new Separator(), buttonsPane));
        tabPane.getTabs().addAll(colorTab, textTab, imageTab);
        colorTab.setContent(colorPane);
        textTab.setContent(textPane);
        imageTab.setContent(imagePane);
        buttonsPane.getChildren().addAll(saveButton, backButton);
        // parametrages
        colorTab.setClosable(false);
        textTab.setClosable(false);
        imageTab.setClosable(false);
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setPadding(new Insets(Constants.Size.OUTER_MARGIN_SIZE));
        buttonsPane.setSpacing(Constants.Size.INNER_MARGIN_SIZE);
    }

    /**
     * Ajout des evenements
     */
    private static void setEvents() {
        saveButton.setOnAction(ae -> {
            colorPane.updateParam();
            textPane.updateParam();
            imagePane.updateParam();
            boolean success = ParametersProfileStorage.saveParametersProfile(profileName, param);
            Notifications notif = Notifications.create()
                    .title(success ? Constants.Label.TITLE_NOTIFICATION_SAVE_OK : Constants.Label.TITLE_NOTIFICATION_SAVE_NOK)
                    .text(MessageFormat.format((success ? Constants.Label.CONTENT_NOTIFICATION_SAVE_OK : Constants.Label.CONTENT_NOTIFICATION_SAVE_NOK), profileName));
            if (success) {
                notif.showInformation();
            } else {
                notif.showWarning();
            }
        });
        backButton.setOnAction(ae -> {
            stage.close();
            backStage.show();
        });
    }

}
