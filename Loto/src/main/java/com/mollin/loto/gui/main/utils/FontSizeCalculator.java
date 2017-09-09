package com.mollin.loto.gui.main.utils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.NumberExpression;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

/**
 * Permet de produire un 'binding' sur la taille d'une police. Cette classe
 * utilitaire est utilisée afin de rendre un texte responsive dans un panneau.
 *
 * @author MOLLIN Florian
 */
public class FontSizeCalculator {
    /**
     * Construit la map associant une taille de police à une surface.
     *
     * @param lStr La liste des possibles chaines de caractères
     * @param fontSizeMax La taille maximale de la police
     * @param stylesheets Les feuilles de styles
     * @param styleClass La classe potentielle du champ text
     * @return la map 'taille police' vers 'surface' calculée grace aux chaines
     * de caractères données
     */
    public static LinkedHashMap<Integer, Bounds> createMapFontSize2Bounds(List<String> lStr, int fontSizeMax, List<String> stylesheets, String styleClass) {
        List<Text> texts = lStr.stream().map(s -> {
            Text t = new Text(s);
            t.getStyleClass().add(styleClass);
            return t;
        }).collect(Collectors.toList());
        Group g = new Group();
        g.getChildren().addAll(texts);
        Scene s = new Scene(g);
        s.getStylesheets().addAll(stylesheets);
        LinkedHashMap<Integer, Bounds> map = new LinkedHashMap<>();
        for (int i = 1; i <= fontSizeMax; i++) {
            String fontSizeExpression = "-fx-font-size: " + i + "px;";
            texts.forEach(t -> {
                t.setStyle(fontSizeExpression);
                t.applyCss();
            });
            Bounds maxBounds = texts.stream().map(t -> t.getLayoutBounds()).reduce((b1, b2) -> {
                double maxWidth = Math.max(b1.getWidth(), b2.getWidth());
                double maxHeight = Math.max(b1.getHeight(), b2.getHeight());
                return new BoundingBox(0, 0, maxWidth, maxHeight);
            }).get();
            map.put(i, maxBounds);
        }
        return map;
    }

    /**
     * Construit la map associant une taille de police à une surface.
     *
     * @param str La chaine de caractère
     * @param fontSizeMax La taille maximale de la police
     * @param stylesheets Les feuilles de styles
     * @param styleClass La classe potentielle du champ text
     * @return la map 'taille police' vers 'surface' calculée grace à la chaine
     * de caractère donnée
     */
    public static LinkedHashMap<Integer, Bounds> createMapFontSize2Bounds(String str, int fontSizeMax, List<String> stylesheets, String styleClass) {
        return createMapFontSize2Bounds(Arrays.asList(str), fontSizeMax, stylesheets, styleClass);
    }

    /**
     * Crée un binding pour la taille d'une police de caractère en fonction de
     * la hauteur et largeur et de la map données
     *
     * @param widthProperty Largeur
     * @param heightProperty Hauteur
     * @param fontSize2Bounds Map de correspondance
     * @return Un binding sur la police de caractere
     */
    public static IntegerBinding createBinding(NumberExpression widthProperty, NumberExpression heightProperty, LinkedHashMap<Integer, Bounds> fontSize2Bounds) {
        IntegerBinding ib = new IntegerBinding() {
            {
                super.bind(widthProperty, heightProperty);
            }

            @Override
            protected int computeValue() {
                double width = widthProperty.doubleValue();
                double height = heightProperty.doubleValue();
                return computeBestFontSize(width, height, fontSize2Bounds);
            }
        };
        return ib;
    }

    /**
     * Crée un binding pour la taille d'une police de caractère en fonction de
     * la region et de la map données
     *
     * @param region La région
     * @param fontSize2Bounds Map de correspondance
     * @return Un binding sur la police de caractere
     */
    public static IntegerBinding createBinding(Region region, LinkedHashMap<Integer, Bounds> fontSize2Bounds) {
        return createBinding(region.widthProperty(), region.heightProperty(), fontSize2Bounds);
    }

    /**
     * Calcule la police de caractère afin qu'elle rentre dans la zone donnée
     *
     * @param width Largeur de la zone
     * @param height Hauteur de la zone
     * @param fontSize2Bounds La police la plus appropriée
     * @return La police permettant de faire contenir le texte dans la zone
     * donnée
     */
    private static int computeBestFontSize(double width, double height, LinkedHashMap<Integer, Bounds> fontSize2Bounds) {
        int bestFontSize = 1;
        for (Entry<Integer, Bounds> entry : fontSize2Bounds.entrySet()) {
            int entrySizeFont = entry.getKey();
            double entryWidth = entry.getValue().getWidth();
            double entryHeight = entry.getValue().getHeight();
            if (entryWidth < width && entryHeight < height) {
                bestFontSize = entrySizeFont;
            } else {
                return bestFontSize;
            }
        }
        return bestFontSize;
    }
}
