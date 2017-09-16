package com.mollin.loto;

import com.mollin.loto.gui.profile.stage.ProfileStageConfigurator;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Classe principale de l'application de loto.
 *
 * @author MOLLIN Florian
 */
public class Loto extends Application {

    @Override
    public void init() {
        Font.loadFont(Loto.class.getResource("fontawesome-webfont.ttf").toExternalForm(), -1);
    }

    @Override
    public void start(Stage primaryStage) {
        ProfileStageConfigurator.configureStage(primaryStage);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
