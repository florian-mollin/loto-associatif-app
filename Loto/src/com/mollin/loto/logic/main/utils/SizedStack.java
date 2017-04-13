package com.mollin.loto.logic.main.utils;

import java.util.Stack;

/**
 * Pile avec une taille max.
 *
 * @author MOLLIN Florian
 */
public class SizedStack<T> extends Stack<T> {
    /**
     * Taille maximale de la pile.
     */
    private final int size;

    /**
     * Constructeur de la pile.
     *
     * @param size Taille max de la pile.
     */
    public SizedStack(int size) {
        super();
        this.size = size;
    }

    @Override
    public T push(T item) {
        while (this.size() >= size) {
            this.remove(0);
        }
        return super.push(item);
    }

    @Override
    public boolean add(T item) {
        while (this.size() >= size) {
            this.remove(0);
        }
        return super.add(item);
    }
}
