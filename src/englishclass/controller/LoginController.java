package englishclass.controller;

import englishclass.conection.UserDao;
import englishclass.model.ModelAcess;
import englishclass.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends AbstractController implements Initializable {

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

    }

    @FXML
    public void goForgetPassword(ActionEvent event) {

    }

    @FXML
    public void goSignIn(ActionEvent event) {
        var dao = new UserDao();
        getModel().setUsuario(dao.selectByName(txtUsername.getText()));
        getModel().getPrimaryStage().setScene(ViewFactory.view.startBorder());
    }

    @FXML
    public void goSignUp(ActionEvent event) {

    }
}
