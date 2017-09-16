package com.mollin.loto.gui.main.grid;

import com.mollin.loto.gui.main.cell.LotoCellGUI;
import com.mollin.loto.gui.main.cell.LotoCellGUI.LotoCellGUIParameters;
import com.mollin.loto.gui.main.utils.BindingUtils;
import com.mollin.loto.gui.main.utils.FontSizeCalculator;
import com.mollin.loto.gui.main.utils.resizable.ResizableGroup;
import com.mollin.loto.logic.main.grid.Grid;
import com.mollin.loto.logic.main.grid.LotoGridListener;
import com.mollin.loto.logic.main.utils.Constants;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Représente la grille du loto.
 *
 * @author MOLLIN Florian
 */
public class LotoGridGUI extends StackPane implements LotoGridListener {
    /**
     * Parametres de la grille
     */
    private final LotoGridGUIParameters gridParam;
    /**
     * Parametre des cellules
     */
    private final LotoCellGUIParameters cellParam;
    /**
     * Group principal pour le contenu du panneau
     */
    private final Group content;
    /**
     * Binding sur la taille d'une cellule
     */
    private final NumberBinding cellSize;
    /**
     * Ensemble des cellules (numéro vers cellule)
     */
    private Map<Integer, LotoCellGUI> cells;
    /**
     * Ecouteur de la grille
     */
    private LotoGridGUIListener listener;

    /**
     * Constructeur de la grille
     *
     * @param gridParam Parametre de la grille
     * @param cellParam Parametre de la cellule
     */
    public LotoGridGUI(LotoGridGUIParameters gridParam, LotoCellGUIParameters cellParam) {
        super();
        this.content = new ResizableGroup();
        this.getChildren().add(this.content);
        this.gridParam = gridParam;
        this.cellParam = cellParam;
        // calcul taille des cellules
        int maxNbCells = Math.max(gridParam.nbCols, gridParam.nbRows);
        NumberBinding minHeightWidthSize = Bindings.min(this.heightProperty(), this.widthProperty());
        this.cellSize = BindingUtils.floor(minHeightWidthSize.subtract(gridParam.lineSize * maxNbCells).divide(maxNbCells));
        // dessin
        drawLines();
        drawCells();
        // evenements
        setEvents();
    }

    /**
     * Dessin des lignes de la grille
     */
    private void drawLines() {
        // lignes verticales
        NumberBinding verticalLineHeight = cellSize.multiply(gridParam.nbRows).add(gridParam.lineSize * (gridParam.nbRows + 1));
        NumberBinding horizontalLineWidth = cellSize.multiply(gridParam.nbCols).add(gridParam.lineSize * (gridParam.nbCols + 1));
        for (int i = 0; i <= gridParam.nbCols; i++) {
            Rectangle verticalLine = new Rectangle();
            verticalLine.setWidth(gridParam.lineSize);
            verticalLine.heightProperty().bind(verticalLineHeight);
            verticalLine.xProperty().bind(cellSize.add(gridParam.lineSize).multiply(i));
            verticalLine.setY(0.0);
            verticalLine.setFill(gridParam.lineColor);
            this.content.getChildren().add(verticalLine);
        }
        // lignes horizontales
        for (int i = 0; i <= gridParam.nbRows; i++) {
            Rectangle horizontalLine = new Rectangle();
            horizontalLine.setHeight(gridParam.lineSize);
            horizontalLine.widthProperty().bind(horizontalLineWidth);
            horizontalLine.yProperty().bind(cellSize.add(gridParam.lineSize).multiply(i));
            horizontalLine.setX(0.0);
            horizontalLine.setFill(gridParam.lineColor);
            this.content.getChildren().add(horizontalLine);
        }
    }

    /**
     * Dessin des cellules de la grille
     */
    private void drawCells() {
        LinkedHashMap<Integer, Bounds> fontSize2Bounds = FontSizeCalculator.createMapFontSize2Bounds(
                String.valueOf(gridParam.nbCols * gridParam.nbRows),
                Constants.FontSize.FONT_SIZE_MAX_CELL_NUMBER,
                Constants.Style.ALL_STYLESHEETS,
                Constants.Style.STYLE_CLASS_CELL_TEXT);
        IntegerBinding fontSizeBinding = FontSizeCalculator.createBinding(cellSize.subtract(gridParam.cellPadding), cellSize.subtract(gridParam.cellPadding), fontSize2Bounds);
        this.cells = new HashMap<>();
        for (int r = 0; r < gridParam.nbRows; r++) {
            for (int c = 0; c < gridParam.nbCols; c++) {
                int cellNumber = coordToNumber(r, c);
                LotoCellGUI cell = new LotoCellGUI(cellNumber, cellParam, fontSizeBinding);
                cell.layoutXProperty().bind(cellSize.add(gridParam.lineSize).multiply(c).add(gridParam.lineSize));
                cell.layoutYProperty().bind(cellSize.add(gridParam.lineSize).multiply(r).add(gridParam.lineSize));
                cell.prefHeightProperty().bind(cellSize);
                cell.prefWidthProperty().bind(cellSize);
                this.cells.put(cellNumber, cell);
                this.content.getChildren().add(cell);
            }
        }
    }

    /**
     * Mise en place des evenements
     */
    private void setEvents() {
        this.setOnMouseClicked((MouseEvent event) -> {
            double x = event.getX();
            double y = event.getY();
            Integer number = positionToNumber(x, y);
            if (number != null) {
                fireClic(number);
            }
        });
    }

    /**
     * Convertit une coordonnées en nombre associé sur la grille
     *
     * @param r La ligne
     * @param c La colonne
     * @return Le numéro de la grille à la position (r,c)
     */
    private int coordToNumber(int r, int c) {
        return gridParam.nbRows * c + r + 1;
    }

    /**
     * Convertit une position (de clic) en nombre
     *
     * @param x Position x
     * @param y Position y
     * @return Le nombre en position (x,y)
     */
    private Integer positionToNumber(double x, double y) {
        double boardX = x - this.content.getLayoutX();
        double boardY = y - this.content.getLayoutY();

        int nbRows = gridParam.nbRows;
        int nbColumns = gridParam.nbCols;

        int max = Math.max(nbRows, nbColumns);
        int r = -1, c = -1;
        for (int i = 0; i < max; i++) {
            if (isInIndex(boardX, i)) {
                c = i;
            }
            if (isInIndex(boardY, i)) {
                r = i;
            }
        }
        if (r != -1 && c != -1) {
            return coordToNumber(r, c);
        } else {
            return null;
        }
    }

    /**
     * Vérifie si une position se trouve dans un index
     *
     * @param xory La position
     * @param ind  L'index
     * @return Vrai si la position est dans un index
     */
    private boolean isInIndex(double xory, int ind) {
        double lineSize = gridParam.lineSize;
        double cellSizeDouble = this.cellSize.doubleValue();
        double start = lineSize + (ind * (lineSize + cellSizeDouble));
        return (xory >= start && xory <= start + cellSizeDouble);
    }

    /**
     * Définit l'écouteur de la grille.
     *
     * @param listener L'écouteur
     */
    public void setListener(LotoGridGUIListener listener) {
        this.listener = listener;
    }

    /**
     * Propagation de l'evenement 'clic'.
     *
     * @param number Le nombre cliqué.
     */
    private void fireClic(int number) {
        if (this.listener != null) {
            this.listener.onClic(number);
        }
    }

    @Override
    public void onUpdate(Grid grid, Integer newLastNumber) {
        for (int i = 1; i <= gridParam.nbCols * gridParam.nbRows; i++) {
            this.cells.get(i).setActive(grid.contains(i));
        }
    }

    /**
     * Classe des parametres de la grille
     */
    public static class LotoGridGUIParameters {
        public Integer nbRows;
        public Integer nbCols;
        public Integer lineSize;
        public Color lineColor;
        public double cellPadding;
    }
}
