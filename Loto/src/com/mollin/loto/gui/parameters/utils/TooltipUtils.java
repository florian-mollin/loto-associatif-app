package com.mollin.loto.gui.parameters.utils;

import com.mollin.loto.gui.utils.GlyphUtils;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.PopOver;
import org.controlsfx.glyphfont.FontAwesome;

/**
 * Classe utilitaires pour la création de "tooltip"
 *
 * @author MOLLIN Florian
 */
public class TooltipUtils {

    /**
     * Création d'un icone pour l'affichage de "tooltip"
     *
     * @param tooltipContent Contenu du "tooltip"
     * @return Le label (icon) pour l'affichage du "tooltip"
     */
    public static Label createTooltipIcon(String tooltipContent) {
        Label label = GlyphUtils.createLabel(FontAwesome.Glyph.QUESTION_CIRCLE);
        addTooltip(label, tooltipContent);
        return label;
    }

    /**
     * Ajoute un "tooltip" sur le noeud donné
     *
     * @param node Le noeud affichant le "tooltip"
     * @param content Contenu du "tooltip"
     */
    public static void addTooltip(Node node, String content) {
        PopOver popover = new PopOver();
        Label label = new Label(content);
        StackPane pane = new StackPane(label);
        pane.setPadding(new Insets(5));
        popover.setContentNode(pane);
        node.setOnMouseClicked(me -> popover.show(node));
    }
}
