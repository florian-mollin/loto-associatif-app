package com.mollin.loto.logic.main.grid;

/**
 * Ecouteur de grille de loto (principalement utilisée par les composants
 * graphiques).
 *
 * @author MOLLIN Florian
 */
public interface LotoGridListener {

    /**
     * Evenement lorsque la grille de loto est mise à jour (clic, demarque,
     * retour arrière).
     *
     * @param grid       Le contenu de la nouvelle grille.
     * @param lastNumber Le dernier nombre tiré
     */
    public void onUpdate(Grid grid, Integer lastNumber);
}
