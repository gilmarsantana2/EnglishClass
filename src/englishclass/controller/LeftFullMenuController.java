package englishclass.controller;

import englishclass.model.ModelAcess;
import englishclass.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class LeftFullMenuController extends AbstractController implements Initializable {

    @FXML
    private AnchorPane leftPane;
    @FXML
    private ImageView userImage;
    @FXML
    private Label userName;
    @FXML
    private Label level;
    @FXML
    private ImageView aulasIcon;
    @FXML
    private ImageView estatisticaIcon;
    @FXML
    private ImageView exercicioIcon;
    @FXML
    private ImageView extrasIcon;

    public LeftFullMenuController(ModelAcess model) {
        super(model);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aulasIcon.setImage(ViewFactory.view.getBusinessIcon());
        estatisticaIcon.setImage(ViewFactory.view.getBusinessIcon());
        exercicioIcon.setImage(ViewFactory.view.getBusinessIcon());
        extrasIcon.setImage(ViewFactory.view.getBusinessIcon());
        userName.setText(getModel().getUsuario().getUserName());
        if (getModel().getUsuario().getUserImage() == null){
            userImage.setImage(ViewFactory.view.getUserImage());
        } else {
            try {
                userImage.setImage(new Image(new FileInputStream(getModel().getUsuario().getUserImage())));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getModel().getUsuario().toString());

    }


}
