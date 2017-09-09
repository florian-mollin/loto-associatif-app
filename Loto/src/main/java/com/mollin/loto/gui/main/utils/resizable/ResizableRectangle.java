package com.mollin.loto.gui.main.utils.resizable;

import javafx.scene.shape.Rectangle;

/**
 * Rectangle redimensionnable. Permet à un rectangle de se redimensionner si il
 * n'a pas assez de place.
 *
 * @author MOLLIN Florian
 */
public class ResizableRectangle extends Rectangle {

    @Override
    public double minWidth(double height) {
        return 0.0;
    }

    @Override
    public double minHeight(double height) {
        return 0.0;
    }

}
