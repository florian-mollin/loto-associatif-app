package com.mollin.loto.gui.main.logo;

import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.javatuples.Pair;

import java.util.List;

/**
 * Panneau pour l'affichage des logos des sponsors
 *
 * @author MOLLIN Florian
 */
public class LotoMultiLogoPaneGUI extends StackPane {
    private final LotoMultiLogoPaneGUIParameters param;
    private final ImageView imageView;

    /**
     * Constructeur du panneau
     *
     * @param param Parametres du panneau
     */
    public LotoMultiLogoPaneGUI(LotoMultiLogoPaneGUIParameters param) {
        super();
        this.param = param;
        this.imageView = createImageView();
        this.getChildren().add(this.imageView);
        // dessin
        drawInitialImage();
        // timeline
        setEvents();
    }

    /**
     * Creation de la vue contenant les images
     *
     * @return la vue destinée à contenir les images
     */
    private ImageView createImageView() {
        ImageView view = new ImageView() {
            @Override
            public double minWidth(double height) {
                return 0.0;
            }

            @Override
            public double minHeight(double height) {
                return 0.0;
            }
        };
        view.fitHeightProperty().bind(this.heightProperty());
        view.fitWidthProperty().bind(this.widthProperty());
        view.setPreserveRatio(true);
        return view;
    }

    /**
     * Dessin de la première image du "cycle"
     */
    private void drawInitialImage() {
        if (this.param.logosTimes != null && !this.param.logosTimes.isEmpty()) {
            this.imageView.setImage(this.param.logosTimes.get(0).getValue0());
        }
    }

    /**
     * Ajout des événements
     */
    private void setEvents() {
        if (this.param.logosTimes != null && !this.param.logosTimes.isEmpty()) {
            SequentialTransition transition = new SequentialTransition();
            int logosTimesSize = this.param.logosTimes.size();
            for (int i = 1; i <= logosTimesSize; i++) {
                Timeline timeline = new Timeline();
                Image image = this.param.logosTimes.get(i % logosTimesSize).getValue0();
                Long time = this.param.logosTimes.get((i - 1) % logosTimesSize).getValue1();
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(time), ae -> imageView.setImage(image));
                timeline.getKeyFrames().add(keyFrame);
                transition.getChildren().add(timeline);
            }
            transition.setCycleCount(SequentialTransition.INDEFINITE);
            transition.play();
        }
    }

    /**
     * Classe des parametres de la cellule
     */
    public static class LotoMultiLogoPaneGUIParameters {
        public List<Pair<Image, Long>> logosTimes;
    }
}
