package com.mollin.loto.gui.main.utils;

/**
 * Information concernant les séparateurs (en fonction du format de l'écran)
 *
 * @author MOLLIN Florian
 */
public class SeparatorsParameters {
    private double[] content4_3;
    private double[] left4_3;
    private double[] right4_3;
    private double[] lastNumber4_3;
    private double[] content16_9;
    private double[] left16_9;
    private double[] right16_9;
    private double[] lastNumber16_9;

    public static SeparatorsParameters getDefault() {
        SeparatorsParameters param = new SeparatorsParameters();
        // 4:3
        param.content4_3 = new double[]{0.63};
        param.left4_3 = new double[]{0.1};
        param.right4_3 = new double[]{0.6};
        param.lastNumber4_3 = new double[]{0.18};
        // 16:9
        param.content16_9 = new double[]{0.52};
        param.left16_9 = new double[]{0.08};
        param.right16_9 = new double[]{0.54};
        param.lastNumber16_9 = new double[]{0.18};
        return param;
    }

    public double[] getContent(ScreenFormat format) {
        return format == ScreenFormat._4_3 ? content4_3 : content16_9;
    }

    public double[] getLeft(ScreenFormat format) {
        return format == ScreenFormat._4_3 ? left4_3 : left16_9;
    }

    public double[] getRight(ScreenFormat format) {
        return format == ScreenFormat._4_3 ? right4_3 : right16_9;
    }

    public double[] getLastNumber(ScreenFormat format) {
        return format == ScreenFormat._4_3 ? lastNumber4_3 : lastNumber16_9;
    }

    public void setContent(double[] val, ScreenFormat format) {
        if (format == ScreenFormat._4_3) {
            content4_3 = val;
        } else {
            content16_9 = val;
        }
    }

    public void setLeft(double[] val, ScreenFormat format) {
        if (format == ScreenFormat._4_3) {
            left4_3 = val;
        } else {
            left16_9 = val;
        }
    }

    public void setRight(double[] val, ScreenFormat format) {
        if (format == ScreenFormat._4_3) {
            right4_3 = val;
        } else {
            right16_9 = val;
        }
    }

    public void setLastNumber(double[] val, ScreenFormat format) {
        if (format == ScreenFormat._4_3) {
            lastNumber4_3 = val;
        } else {
            lastNumber16_9 = val;
        }
    }
}
