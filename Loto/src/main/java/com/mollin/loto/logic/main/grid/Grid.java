package com.mollin.loto.logic.main.grid;

import java.util.HashSet;
import java.util.Set;

/**
 * Une grille représente les nombres deja tirés lors d'une partie de loto.<br>
 * Ne stocke pas d'historique.
 *
 * @author MOLLIN Florian
 */
public class Grid {
    /**
     * Représente les nombres tirés de la grille.
     */
    private Set<Integer> numbers;

    /**
     * Constructeur d'une grille vide.
     */
    public Grid() {
        this.numbers = new HashSet<>();
    }

    /**
     * Constructeur d'une grille avec un ensemble de nombres déjà tiré.
     *
     * @param numbers L'ensemble de nombres déjà tiré
     */
    public Grid(Set<Integer> numbers) {
        this.numbers = numbers;
    }

    /**
     * Constructeur d'une grille copiant une autre grille.
     *
     * @param grid La grille à copier
     */
    public Grid(Grid grid) {
        this(new HashSet<>(grid.numbers));
    }

    /**
     * Test si une grille est vide.
     *
     * @return
     */
    public boolean isEmpty() {
        return this.numbers.isEmpty();
    }

    /**
     * Test si la grille contient un nombre donné.
     *
     * @param number Le nombre à tester.
     * @return Vrai si le nombre est dans la grille.
     */
    public boolean contains(int number) {
        return this.numbers.contains(number);
    }

    /**
     * Renvoie l'ensemble des nombres tirés de la grille.
     *
     * @return L'ensemble des nombres tirés de la grille.
     */
    public Set<Integer> getNumbers() {
        return this.numbers;
    }

    /**
     * Inverse un nombre dans la grille.<br>
     * Si le nombre est présent, il est enlevé, sinon il est ajouté.
     *
     * @param number Le nombre à inverser
     */
    private void switchNumber(int number) {
        if (this.numbers.contains(number)) {
            this.numbers.remove(number);
        } else {
            this.numbers.add(number);
        }
    }

    /**
     * Crée une nouvelle grille identique avec le nombre inversé donné.
     *
     * @param number Le nombre à inverser sur la nouvelle grille.
     * @return La nouvelle grille.
     */
    public Grid derive(int number) {
        Grid resultGrid = new Grid(this);
        resultGrid.switchNumber(number);
        return resultGrid;
    }
}
