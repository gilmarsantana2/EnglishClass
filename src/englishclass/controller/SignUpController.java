package englishclass.controller;

import englishclass.model.ModelAcess;
import englishclass.util.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController extends AbstractController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField txtUsername;
    @FXML
    private ImageView userImage;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtConfPassword;
    @FXML
    private TextField txtname;

    public SignUpController(ModelAcess model) {
        super(model);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Animation(anchorPane).fadeInUp();
    }

    @FXML
    public void goAddImage(ActionEvent event) {

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
    public void goSignUp(ActionEvent event) {

    }


}
