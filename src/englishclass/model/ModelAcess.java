package englishclass.model;

import javafx.stage.Stage;

public class ModelAcess {

    private boolean menu = false;
    private Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public boolean isMenuOpen() {
        return menu;
    }

    public void setMenuOpen(boolean menu) {
        this.menu = menu;
    }
}
