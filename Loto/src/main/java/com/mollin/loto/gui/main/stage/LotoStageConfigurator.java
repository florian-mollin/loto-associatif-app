package com.mollin.loto.gui.main.stage;

import com.mollin.loto.gui.main.button.LotoButtonPaneGUI;
import com.mollin.loto.gui.main.cell.LotoCellGUI;
import com.mollin.loto.gui.main.grid.LotoGridGUI;
import com.mollin.loto.gui.main.logo.LotoMultiLogoPaneGUI;
import com.mollin.loto.gui.main.multitextpane.LotoMultiTextPaneGUI;
import com.mollin.loto.gui.main.numberpane.LotoNumberPaneGUI;
import com.mollin.loto.gui.main.utils.ScreenFormat;
import com.mollin.loto.gui.main.utils.SeparatorsParameters;
import com.mollin.loto.gui.utils.GlyphUtils;
import com.mollin.loto.gui.utils.Utils;
import com.mollin.loto.logic.main.grid.LotoGrid;
import com.mollin.loto.logic.main.utils.Constants;
import com.mollin.loto.logic.parameters.model.ParametersModel;
import com.mollin.loto.logic.storage.GridStorage;
import com.mollin.loto.logic.storage.SeparatorsStorage;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Configurateur pour le stage de l'application.
 *
 * @author MOLLIN Florian
 */
public class LotoStageConfigurator {
    // Panneaux
    private static StackPane rootPane;
    private static SplitPane contentPane;
    private static SplitPane leftPane;
    private static SplitPane rightPane;
    private static StackPane gridPane;
    private static StackPane multiTextPane;
    private static BorderPane logoAndButtonsPane;
    private static StackPane buttonsPane2;
    private static StackPane lastNumberPane;
    // Parametres
    private static LotoParameters lotoParam;
    private static LotoGridGUI.LotoGridGUIParameters gridParam;
    private static LotoCellGUI.LotoCellGUIParameters cellParam;
    private static LotoMultiTextPaneGUI.LotoMultiTextPaneGUIParameters multiTextParam;
    private static LotoNumberPaneGUI.LotoNumberPaneGUIParameters lastNumberParam;
    private static LotoMultiLogoPaneGUI.LotoMultiLogoPaneGUIParameters logoParam;
    private static LotoButtonPaneGUI.LotoButtonPaneGUIParameters buttonsParam;
    private static SeparatorsParameters separatorsParam;
    private static ParametersModel profileParam;
    // GUI
    private static LotoGridGUI gridGUI;
    private static LotoMultiTextPaneGUI multiTextGUI;
    private static LotoNumberPaneGUI lastNumberGUI;
    private static LotoMultiLogoPaneGUI logoGUI;
    private static LotoButtonPaneGUI buttonsGUI;
    // Autres
    private static Stage lotoStage;
    private static Stage profStage;
    private static Scene scene;
    private static LotoGrid lotoGrid;
    private static Label exitLabelButton;
    private static String profName;

    /**
     * Classe non instanciable
     */
    private LotoStageConfigurator() {
    }

    /**
     * Configure le stage avec les différents éléments.
     *
     * @param stage        Le stage à configurer
     * @param param        Parametres de l'application
     * @param profileName  Nom du profil
     * @param profileStage Stage pour revenir au choix du profil
     */
    public static void configureStage(Stage stage, ParametersModel param, String profileName, Stage profileStage) {
        lotoStage = stage;
        profileParam = param;
        profName = profileName;
        profStage = profileStage;
        initElements();
        setElements();
        linkElements();
        setEvents();
        refresh();
        Utils.displaySplitPaneSeparator(scene, false);
        Utils.setDefaultIcon(stage);
        stage.setTitle(Constants.Label.WINDOW_TITLE);
        stage.setScene(scene);
        stage.setFullScreen(Constants.Other.FULL_SCREEN_APP);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
    }

    /**
     * Initialisation des elements
     */
    private static void initElements() {
        // === instanciation ===
        // panneaux
        rootPane = new StackPane();
        contentPane = new SplitPane();
        leftPane = new SplitPane();
        leftPane.setOrientation(Orientation.VERTICAL);
        rightPane = new SplitPane();
        rightPane.setOrientation(Orientation.VERTICAL);
        scene = new Scene(rootPane, Constants.Size.INITIAL_WIDTH_SCENE, Constants.Size.INITIAL_HEIGHT_SCENE);
        scene.getStylesheets().addAll(Constants.Style.ALL_STYLESHEETS);
        gridPane = new StackPane();
        multiTextPane = new StackPane();
        logoAndButtonsPane = new BorderPane();
        buttonsPane2 = new StackPane();
        lastNumberPane = new StackPane();
        // params
        lotoParam = profileParam.getLotoParameters();
        gridParam = profileParam.getLotoGridGUIParameters();
        cellParam = profileParam.getLotoCellGUIParameters();
        multiTextParam = profileParam.getLotoMultiTextPaneGUIParameters();
        lastNumberParam = profileParam.getLotoNumberPaneGUIParameters();
        logoParam = profileParam.getLotoMultiLogoPaneGUIParameters(profName);
        buttonsParam = profileParam.getLotoButtonPaneGUIParameters();
        separatorsParam = SeparatorsStorage.loadSeparatorsParameters(profName);
        // logic
        lotoGrid = GridStorage.loadGrid(profName);
        // elements
        gridGUI = new LotoGridGUI(gridParam, cellParam);
        multiTextGUI = new LotoMultiTextPaneGUI(multiTextParam);
        lastNumberGUI = new LotoNumberPaneGUI(lastNumberParam);
        logoGUI = new LotoMultiLogoPaneGUI(logoParam);
        buttonsGUI = new LotoButtonPaneGUI(buttonsParam, lotoGrid, scene, () -> SeparatorsStorage.saveSeparatorsParameters(getSeparatorParam(), profName));
        // bouton quitter
        exitLabelButton = GlyphUtils.createLabel(Constants.Other.EXIT_BUTTON_GLYPH, lotoParam.fontSizeExitButton);
    }

    /**
     * Ajout et parametrage des elements
     */
    private static void setElements() {
        // imbrication
        rootPane.getChildren().add(contentPane);
        contentPane.getItems().addAll(leftPane, rightPane);
        leftPane.getItems().addAll(multiTextPane, gridPane);
        rightPane.getItems().addAll(logoAndButtonsPane, lastNumberPane);
        // padding
        contentPane.setPadding(new Insets(lotoParam.padding));
        lastNumberPane.setPadding(new Insets(0, 0, 0, lotoParam.padding));
        buttonsPane2.setPadding(new Insets(0, 0, 0, lotoParam.padding));
        // parametrage
        Background background = Utils.createBackground(lotoParam.backgroundColor);
        contentPane.setBackground(background);
        leftPane.setBackground(background);
        rightPane.setBackground(background);
        gridPane.setBackground(background);
        multiTextPane.setBackground(background);
        logoAndButtonsPane.setBackground(background);
        buttonsPane2.setBackground(background);
        lastNumberPane.setBackground(background);
        // imbrication
        gridPane.getChildren().add(gridGUI);
        multiTextPane.getChildren().add(multiTextGUI);
        lastNumberPane.getChildren().add(lastNumberGUI);
        logoAndButtonsPane.setCenter(logoGUI);
        logoAndButtonsPane.setBottom(buttonsPane2);
        buttonsPane2.getChildren().add(buttonsGUI);
        // bouton quitter
        rootPane.getChildren().add(exitLabelButton);
        StackPane.setAlignment(exitLabelButton, Pos.TOP_RIGHT);
        exitLabelButton.setPadding(new Insets(5));
    }

    /**
     * Liaison entre les éléments
     */
    private static void linkElements() {
        gridGUI.setListener(lotoGrid);
        lotoGrid.getListeners().add(gridGUI);
        lotoGrid.getListeners().add(lastNumberGUI);
    }

    /**
     * Ajout des evenements
     */
    private static void setEvents() {
        // bouton quitter
        exitLabelButton.setOnMouseClicked(ae -> {
            lotoStage.close();
            profStage.show();
        });
        exitLabelButton.setOnMouseEntered(ae -> exitLabelButton.setTextFill(Color.RED));
        exitLabelButton.setOnMouseExited(ae -> exitLabelButton.setTextFill(Color.BLACK));
        // place des separateurs
        lotoStage.setOnShowing(ae -> setSeparators());
        if (Constants.Other.FULL_SCREEN_APP) {
            scene.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> setSeparators());
        }
    }

    /**
     * Mise en place des séparateurs aux bonnes positions
     */
    private static void setSeparators() {
        // position separateurs
        ScreenFormat format = Utils.getScreenFormat(lotoStage);
        contentPane.setDividerPositions(separatorsParam.getContent(format));
        leftPane.setDividerPositions(separatorsParam.getLeft(format));
        rightPane.setDividerPositions(separatorsParam.getRight(format));
        lastNumberGUI.getContent().setDividerPositions(separatorsParam.getLastNumber(format));
    }

    /**
     * Actualisation de la grille
     */
    private static void refresh() {
        lotoGrid.fireUpdate();
    }

    /**
     * Récupération des positions des séparateurs
     *
     * @return La postion des séparateurs
     */
    private static SeparatorsParameters getSeparatorParam() {
        SeparatorsParameters param = separatorsParam;
        ScreenFormat screenFormat = Utils.getScreenFormat(lotoStage);
        param.setContent(contentPane.getDividerPositions(), screenFormat);
        param.setLeft(leftPane.getDividerPositions(), screenFormat);
        param.setRight(rightPane.getDividerPositions(), screenFormat);
        param.setLastNumber(lastNumberGUI.getContent().getDividerPositions(), screenFormat);
        return param;
    }
}
