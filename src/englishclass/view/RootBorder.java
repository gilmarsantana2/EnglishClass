package englishclass.view;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class RootBorder {

    public BorderPane borderPane;
    private StackPane centerStack = new StackPane();

    public RootBorder(){
        borderPane = new BorderPane(centerStack);
    }

    public void openView(Node view){
        centerStack.getChildren().add(view);
    }

    public void closeView(Node view){
        centerStack.getChildren().remove(view);
    }

    public void setCenter(Node nCentro){
        centerStack.getChildren().add(nCentro);
    }

    public void setTop(Node nTop){
        borderPane.setTop(nTop);
    }

    public void setLeft(Node nLeft){
        borderPane.setLeft(nLeft);
    }

    public void setRight(Node nRight){
        borderPane.setRight(nRight);
    }

    public void setBottom(Node nBottom){
        borderPane.setBottom(nBottom);
    }

    public BorderPane getBorder(){
        return borderPane;
    }

}
