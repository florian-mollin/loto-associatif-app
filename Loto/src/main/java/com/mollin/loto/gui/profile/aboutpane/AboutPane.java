package com.mollin.loto.gui.profile.aboutpane;

import com.mollin.loto.gui.utils.Utils;
import com.mollin.loto.logic.main.utils.Constants;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Panneau d'affichage pour les crédits
 *
 * @author MOLLIN Florian
 */
public class AboutPane extends BorderPane {
    private Label title;
    private VBox content;

    /**
     * Constructeur du panneau
     */
    public AboutPane() {
        super();
        initElements();
        setElements();
    }

    /**
     * Initialisation des elements
     */
    private void initElements() {
        // title
        ImageView logo = new ImageView(Utils.getImageIcon());
        logo.setPreserveRatio(true);
        logo.setFitHeight(30);
        title = new Label("Loto Associatif", logo);
        title.setFont(new Font(30));
        // content
        Label version = new Label("Version : " + Constants.Other.VERSION_NUMBER);
        Label author = new Label("Auteur : Florian M.");
        TextFlow website = new TextFlow(new Text("Site web : "), createUrlLink(Constants.Other.WEBSITE_URL, Constants.Other.WEBSITE_URL));
        TextFlow license = new TextFlow(new Text("Licence : "), new Text(Constants.Other.LICENSE_NAME));
        content = new VBox(3.0, version, author, website, license);
    }

    /**
     * Ajout des elements au panneau
     */
    private void setElements() {
        this.setTop(title);
        this.setCenter(content);
    }

    /**
     * Méthode créant un lien cliquable redirigeant vers le navigateur par
     * défaut du client.
     *
     * @param label Le label du lien
     * @param url   L'adresse vers laquelle pointe le lien
     * @return Le lien cliquable
     */
    private Hyperlink createUrlLink(String label, String url) {
        Hyperlink hl = new Hyperlink(label);
        hl.setOnMouseClicked(me -> {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException ex) {
            }
        });
        return hl;
    }

}
