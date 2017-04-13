package com.mollin.loto.logic.main.grid;

/**
 * Définit les actions qu'un utilisateur peut effectuer sur une grille.
 *
 * @author MOLLIN Florian
 */
public interface LotoGridInterface {
    /**
     * Effectue un clic sur le nombre donné.
     *
     * @param number	Le nombre sur lequel cliquer
     */
    public void clic(int number);

    /**
     * Demarque la grille (la vide).
     */
    public void clear();

    /**
     * Effectue un retour arriere dans l'historique.
     */
    public void undo();
}
