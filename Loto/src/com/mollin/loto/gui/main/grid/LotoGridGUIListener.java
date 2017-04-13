package com.mollin.loto.gui.main.grid;

/**
 * Ecouteur de la grille graphique du loto.
 *
 * @author MOLLIN Florian
 */
public interface LotoGridGUIListener {

    /**
     * Evenement lors d'un clic sur un nombre de la grille.
     *
     * @param number Le nombre cliqué par l'utilisateur
     */
    public void onClic(int number);
}
