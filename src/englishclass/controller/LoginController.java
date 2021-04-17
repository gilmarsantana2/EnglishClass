package englishclass.controller;

import englishclass.conection.UserDao;
import englishclass.model.ModelAcess;
import englishclass.util.Animation;
import englishclass.util.Criptografia;
import englishclass.view.ViewFactory;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends AbstractController implements Initializable {

    @FXML
    public StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private ImageView logoIcon;
    @FXML
    private ImageView googleIcon;

    public LoginController(ModelAcess model) {
        super(model);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logoIcon.setImage(ViewFactory.view.getAppIcon());
        googleIcon.setImage(ViewFactory.view.getGoogleIcon());
        getModel().setController(this);
    }

    @FXML
    public void goForgetPassword(ActionEvent event) {
        stackPane.getChildren().add(ViewFactory.view.getForgetPassView());
        changeAttributes(true);
    }

    @FXML
    public void goSignIn(ActionEvent event) {
        var dao = new UserDao();
        getModel().setUsuario(dao.selectByName(txtUsername.getText()));
        if (getModel().getUsuario().getUserName() != null) {
            if (Criptografia.hash(txtPassword.getText()).equals(getModel().getUsuario().getPassword()))
                getModel().getPrimaryStage().setScene(ViewFactory.view.startBorder());
            else errorToLogIn();
        } else {
            errorToLogIn();
        }
    }

    private void errorToLogIn() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fail to log in");
        alert.setHeaderText("User not found");
        alert.setContentText("Check your username or password");
        if (alert.showAndWait().get() == ButtonType.OK) {
            txtPassword.setText("");
            txtPassword.requestFocus();
        }
    }

    @FXML
    public void goSignUp(ActionEvent event) {
        stackPane.getChildren().add(ViewFactory.view.getSignUpView());
        changeAttributes(true);
    }

    public void changeAttributes(boolean status) {
        if (status) {
            new Animation(anchorPane).fade30();
            txtUsername.setDisable(true);
            txtPassword.setDisable(true);
        } else {
            new Animation(anchorPane).fade100();
            txtUsername.setDisable(false);
            txtPassword.setDisable(false);
        }
    }

    @Override
    public String toString() {
        return "LoginController{" +
                "controller=" + "goooo" +
                '}';
    }
}
