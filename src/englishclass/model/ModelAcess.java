package englishclass.model;

import englishclass.controller.AbstractController;
import englishclass.controller.LoginController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class ModelAcess {

    private boolean menu = false;
    private Stage primaryStage;
    private UserModel usuario;
    private Object controller;

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

    public UserModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UserModel usuario) {
        this.usuario = usuario;
    }

    public void setController(Object controller){
        this.controller = controller;
    }

    public <T> T getController(){
        return (T) controller;
    }

    public void logout(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are leaving");
        alert.setContentText("Are you sure to Logout?");

        if (alert.showAndWait().get() == ButtonType.OK){
            System.out.println("Saindo....");
            primaryStage.close();
        }
    }
}
