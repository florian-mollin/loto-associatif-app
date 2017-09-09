package com.mollin.loto.logic.main.grid;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

/**
 * Test de la LotoGrid
 *
 * @author MOLLIN Florian
 */
public class LotoGridTest {
    private static final int HISTORY_DEPTH = 42;
    private static final int MAX_NUMBER = 90;

    /**
     * Test un enchainement de retours arrieres inutiles.
     */
    @Test
    public void testWrongUndos() {
        final int NB_UNDO = 100;
        LotoGrid grid = new LotoGrid(HISTORY_DEPTH, MAX_NUMBER);
        for (int i = 0; i < NB_UNDO; i++) {
            grid.undo();
        }
    }

    /**
     * Test un depassement de la taille de l'historique.
     */
    @Test
    public void testHistoryOverflow() {
        LotoGrid grid = new LotoGrid(HISTORY_DEPTH, MAX_NUMBER);
        for (int i = 0; i < HISTORY_DEPTH * 2; i++) {
            grid.clic(MAX_NUMBER);
        }
    }

    /**
     * Test de clics successifs.
     */
    @Test
    public void testClic() {
        LotoGrid grid = new LotoGrid(HISTORY_DEPTH, MAX_NUMBER);
        for (int i = 1; i <= MAX_NUMBER; i++) {
            grid.clic(i);
            assertThat(grid.getActualGrid().getNumbers()).isEqualTo(rangeSet(1, i));
        }
    }

    /**
     * Remplit la grille puis effectue des retours arrieres successifs.
     */
    @Test
    public void testUndo() {
        LotoGrid grid = new LotoGrid(MAX_NUMBER + 1, MAX_NUMBER);
        for (int i = 1; i <= MAX_NUMBER; i++) {
            grid.clic(i);
        }
        for (int i = MAX_NUMBER; i >= 0; i--) {
            assertThat(grid.getActualGrid().getNumbers()).isEqualTo(rangeSet(1, i));
            grid.undo();
        }
    }

    /**
     * Test du demarquage de la grille.
     */
    @Test
    public void testClear() {
        LotoGrid grid = new LotoGrid(HISTORY_DEPTH, MAX_NUMBER);
        for (int i = 1; i <= MAX_NUMBER; i++) {
            grid.clic(i);
        }
        grid.clear();
        assertThat(grid.getActualGrid().isEmpty()).isTrue();
    }

    /**
     * Test du bon fonctionnement de l'historique lors d'un retour arriere apres
     * une demarque.
     */
    @Test
    public void testHistoryClear() {
        LotoGrid grid = new LotoGrid(HISTORY_DEPTH * 2, MAX_NUMBER);
        for (int i = 1; i <= MAX_NUMBER; i++) {
            grid.clic(i);
        }
        grid.clear();
        grid.undo();
        assertThat(grid.getActualGrid().getNumbers()).isEqualTo(rangeSet(1, MAX_NUMBER));
    }

    /**
     * Test qu'un nombre s'efface bien lors d'un double clic.
     */
    @Test
    public void testDoubleClic() {
        LotoGrid grid = new LotoGrid(HISTORY_DEPTH, MAX_NUMBER);
        for (int i = 1; i <= MAX_NUMBER; i++) {
            grid.clic(i);
        }
        for (int i = MAX_NUMBER; i >= 1; i--) {
            assertThat(grid.getActualGrid().getNumbers()).isEqualTo(rangeSet(1, i));
            grid.clic(i);
        }
        assertThat(grid.getActualGrid().isEmpty()).isTrue();
    }

    /**
     * Renvoit l'ensemble contenant tous les entiers naturels allant de 'a' a
     * 'b'.
     *
     * @param a	Borne inférieure (comprise)
     * @param b	Borne supérieure (comprise)
     * @return	L'ensemble des entiers naturels entre 'a' et 'b' (inclus)
     */
    private static Set<Integer> rangeSet(int a, int b) {
        return IntStream.rangeClosed(a, b).boxed().collect(Collectors.toSet());
    }
}
