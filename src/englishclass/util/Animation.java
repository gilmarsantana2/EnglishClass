package englishclass.util;


import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animation {

    public static final Interpolator EASE = Interpolator.SPLINE(0.25D, 0.1D, 0.25D, 1.0D);

    private Node node;
    private Timeline linha;
    private BooleanProperty done = new SimpleBooleanProperty(false);

    public Animation(Node target) {
        this.node = target;
    }

    public void fadeIn() {
        linha = new Timeline(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(node.opacityProperty(), 0, EASE)),
                new KeyFrame(Duration.millis(1000),
                        new KeyValue(node.opacityProperty(), 1, EASE))
        );
        linha.play();
    }

    public void fade30(){
        linha = new Timeline(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(node.opacityProperty(), 1, EASE)),
                new KeyFrame(Duration.millis(1000),
                        new KeyValue(node.opacityProperty(), 0.3, EASE))
        );
        linha.play();
    }
    public void fade100(){
        linha = new Timeline(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(node.opacityProperty(), 0.3, EASE)),
                new KeyFrame(Duration.millis(500),
                        new KeyValue(node.opacityProperty(), 1, EASE))
        );
        linha.play();
    }

    public void fadeInUp(){
        linha = new Timeline(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(node.opacityProperty(), 0, EASE),
                        new KeyValue(node.translateYProperty(), node.getBoundsInParent().getHeight(), EASE)),
                new KeyFrame(Duration.millis(1000),
                        new KeyValue(node.opacityProperty(), 1, EASE),
                        new KeyValue(node.translateYProperty(), 0, EASE))
        );
        linha.play();
    }

    public Timeline fadeOutDown(){
        linha = new Timeline(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(node.opacityProperty(), 1, EASE),
                        new KeyValue(node.translateYProperty(), 0, EASE)
                ),
                new KeyFrame(Duration.millis(1000),
                        new KeyValue(node.opacityProperty(), 0, EASE),
                        new KeyValue(node.translateYProperty(), node.getBoundsInParent().getHeight(), EASE)
                )
        );
        return linha;
    }

    public BooleanProperty fadeLeft() {
        linha = new Timeline(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(node.opacityProperty(), 0, EASE),
                        new KeyValue(node.translateXProperty(), - node.getBoundsInParent().getWidth(), EASE)),
                new KeyFrame(Duration.millis(1000),
                        new KeyValue(node.opacityProperty(), 1, EASE),
                        new KeyValue(node.translateXProperty(), 0, EASE))
        );
        linha.play();
        linha.setOnFinished(e->{
            done.set(true);
        });
        return done;
    }

    public void fadeRight() {
        linha = new Timeline(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(node.opacityProperty(), 0, EASE),
                        new KeyValue(node.translateXProperty(), node.getBoundsInParent().getWidth(), EASE)),
                new KeyFrame(Duration.millis(1000),
                        new KeyValue(node.opacityProperty(), 1, EASE),
                        new KeyValue(node.translateXProperty(), 0, EASE))

        );
        linha.play();
    }

}
