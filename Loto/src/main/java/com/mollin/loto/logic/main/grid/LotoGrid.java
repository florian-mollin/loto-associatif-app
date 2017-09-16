package com.mollin.loto.logic.main.grid;

import com.mollin.loto.gui.main.grid.LotoGridGUIListener;
import com.mollin.loto.logic.main.utils.SizedStack;
import com.mollin.loto.logic.storage.GridStorage;
import org.javatuples.Pair;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Cette grille représente la "vraie" grille de loto.<br>
 * Représente le tirage des nombres, ainsi que l'historique de celui-ci.
 *
 * @author MOLLIN Florian
 */
public class LotoGrid implements LotoGridInterface, LotoGridGUIListener {
    /**
     * Nombre max autorisé dans la grille
     */
    private final int maxNumber;
    /**
     * File d'historique des couples (grille;dernier numéro)
     */
    private final Stack<Pair<Grid, Integer>> gridHistory;
    /**
     * Ecouteurs de la grille (pour la partie graphique)
     */
    private transient Set<LotoGridListener> listeners;
    /**
     * Nom du profil
     */
    private final String profileName;

    /**
     * Constructeur de la grille.
     *
     * @param historyDepth  Profondeur de l'historique
     * @param maxNumber     Nombre maximum autorisé lors d'un tirage
     * @param profileName   Chemin du fichier de sauvegarde/chargement
     */
    public LotoGrid(int historyDepth, int maxNumber, String profileName) {
        this.gridHistory = new SizedStack<>(historyDepth);
        this.gridHistory.add(Pair.with(new Grid(), null));
        this.maxNumber = maxNumber;
        this.listeners = new HashSet<>();
        this.profileName = profileName;
    }

    /**
     * Constructeur de la grille (sans nom de profil, donc sans sauvegarde)
     *
     * @param historyDepth Profondeur de l'historique
     * @param maxNumber    Nombre maximum autorisé lors d'un tirage
     */
    public LotoGrid(int historyDepth, int maxNumber) {
        this(historyDepth, maxNumber, null);
    }

    /**
     * Calcul le nouveau dernier numéro à afficher.
     *
     * @param clicNumber Le dernier numéro cliqué
     * @return Le nouveau 'dernier numéro'
     */
    private Integer getNewLastNumber(int clicNumber) {
        Grid actualGrid = this.gridHistory.peek().getValue0();
        Grid newGrid = this.gridHistory.peek().getValue0().derive(clicNumber);
        if (!actualGrid.contains(clicNumber)) {
            return clicNumber;
        } else {
            for (int i = this.gridHistory.size() - 1; i >= 0; i--) {
                Integer n = this.gridHistory.get(i).getValue1();
                if (n == null || newGrid.contains(n)) {
                    return n;
                }
            }
            return null;
        }
    }

    @Override
    public void clic(int number) {
        if (1 <= number && number <= this.maxNumber) {
            Grid oldGrid = this.gridHistory.peek().getValue0();
            Grid newGrid = oldGrid.derive(number);
            Integer newLastNumber = getNewLastNumber(number);
            this.gridHistory.push(Pair.with(newGrid, newLastNumber));
            save();
            fireUpdate();
        }
    }

    @Override
    public void clear() {
        if (!this.gridHistory.peek().getValue0().isEmpty()) {
            Grid newGrid = new Grid();
            this.gridHistory.push(Pair.with(newGrid, null));
            save();
            fireUpdate();
        }
    }

    @Override
    public void undo() {
        if (this.gridHistory.size() > 1) {
            this.gridHistory.pop();
            save();
            fireUpdate();
        }
    }

    /**
     * Sauvegarde la grille
     */
    public void save() {
        if (this.profileName != null) {
            GridStorage.saveGrid(this, this.profileName);
        }
    }

    /**
     * Propagation de l'evenement 'update'.
     */
    public void fireUpdate() {
        if (this.listeners != null) {
            this.listeners.forEach((listener) -> {
                Grid grid = gridHistory.peek().getValue0();
                Integer lastNumber = gridHistory.peek().getValue1();
                listener.onUpdate(grid, lastNumber);
            });
        }
    }

    /**
     * Renvoit la grille courante.
     *
     * @return La grille courante.
     */
    protected Grid getActualGrid() {
        return this.gridHistory.peek().getValue0();
    }

    /**
     * Renvoit l'ensemble des ecouteurs de la grille
     *
     * @return L'ensemble des ecouteurs de la grille
     */
    public Set<LotoGridListener> getListeners() {
        if (this.listeners == null) {
            this.listeners = new HashSet<>();
        }
        return this.listeners;
    }

    @Override
    public void onClic(int number) {
        clic(number);
    }
}
