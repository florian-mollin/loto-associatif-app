package com.mollin.loto.gui.main.utils;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.value.ObservableNumberValue;

/**
 * Classe contenant les méthodes utilitaires pour faire du 'binding'
 *
 * @author MOLLIN Florian
 */
public class BindingUtils {

    /**
     * Fonction 'floor'
     *
     * @param onv Valeur
     * @return floor(valeur)
     */
    public static NumberBinding floor(ObservableNumberValue onv) {
        return new DoubleBinding() {
            {
                super.bind(onv);
            }

            @Override
            protected double computeValue() {
                return Math.floor(onv.getValue().doubleValue());
            }
        };
    }
}
