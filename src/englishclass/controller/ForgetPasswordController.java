package englishclass.controller;

import englishclass.conection.UserDao;
import englishclass.model.ModelAcess;
import englishclass.util.Animation;
import englishclass.util.Criptografia;
import englishclass.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ForgetPasswordController extends AbstractController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtNewPassword;
    @FXML
    private PasswordField txtConfPassword;

    public ForgetPasswordController(ModelAcess model) {
        super(model);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Animation(anchorPane).fadeInUp();
        txtUserName.requestFocus();
    }

    @FXML
    public void goCancel(ActionEvent event) {
        LoginController controller = getModel().getController();
        var anima = new Animation(anchorPane).fadeOutDown();
        anima.setOnFinished(actionEvent -> {
            controller.stackPane.getChildren().remove(1);
            controller.changeAttributes(false);
        });
        anima.play();
    }

    @FXML
    public void goChange(ActionEvent event) {
        var dao = new UserDao();
        var user = dao.selectByName(txtUserName.getText());
        if (txtConfPassword.getText().equals(txtNewPassword.getText()) && user.getUserName() != null){
            user.setPassword(Criptografia.hash(txtConfPassword.getText()));
            if (dao.alterar(user)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Password has changed");
                alert.setHeaderText("Password has changed in user: " + user.getUserName());
                alert.setContentText("Congrats");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    getModel().setUsuario(user);
                    getModel().getPrimaryStage().setScene(ViewFactory.view.startBorder());
                }
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fail to change");
            alert.setHeaderText("Password is not the same or user doesn't exit");
            alert.setContentText("Check the Information");
            if (alert.showAndWait().get() == ButtonType.OK) {
                txtConfPassword.setText("");
                txtConfPassword.requestFocus();
            }
        }
    }
}
