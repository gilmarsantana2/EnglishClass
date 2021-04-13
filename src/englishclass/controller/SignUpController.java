package englishclass.controller;

import englishclass.conection.UserDao;
import englishclass.model.ModelAcess;
import englishclass.model.UserModel;
import englishclass.util.Animation;
import englishclass.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    @FXML
    private Label lblImage;

    private String imagePath;

    public SignUpController(ModelAcess model) {
        super(model);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userImage.setImage(ViewFactory.view.getUserImage());
        new Animation(anchorPane).fadeInUp();
    }

    @FXML
    public void goAddImage(ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPGE Files", "*.jpg")
                ,new FileChooser.ExtensionFilter("PNG Files", "*.png")
        );
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setTitle("Upload User Image");
        File image = fileChooser.showOpenDialog(getModel().getPrimaryStage());
        lblImage.setText(image.getName());
        imagePath = image.getAbsolutePath();
        userImage.setImage(new Image(new FileInputStream(imagePath)));
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
        if (txtConfPassword.getText().equals(txtPassword.getText())){
            var dao = new UserDao();
            var user = new UserModel();
            user.setNome(txtUsername.getText());
            user.setPassword(txtConfPassword.getText());
            if (imagePath == null){
                user.setUserImage("");
            }else {
                user.setUserImage(imagePath);
            }
            //dao.incluir(user);
            //todo
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Password");
            alert.setHeaderText("Your Password is incorrect");
            alert.setContentText("Please check your password");
            alert.showAndWait();
            txtConfPassword.setText("");
            txtConfPassword.requestFocus();
        }

    }


}
