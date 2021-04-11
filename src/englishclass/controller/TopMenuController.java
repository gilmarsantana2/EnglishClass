package englishclass.controller;

import englishclass.model.ModelAcess;
import englishclass.util.Animation;
import englishclass.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TopMenuController extends AbstractController implements Initializable {

    @FXML
    private AnchorPane topPane;
    @FXML
    private Label userName;
    @FXML
    private ImageView notificationIcon;
    @FXML
    private ImageView settingIcon;
    @FXML
    private ImageView logoutIcon;
    @FXML
    private ImageView menuIcon;

    public TopMenuController(ModelAcess model) {
        super(model);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        topPane.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            userName.setLayoutX((newValue.getWidth() / 2) - 50);
        });
        notificationIcon.setImage(ViewFactory.view.getBellIcon());
        settingIcon.setImage(ViewFactory.view.getSettingIcon());
        logoutIcon.setImage(ViewFactory.view.getDoorIcon());
        menuIcon.setImage(ViewFactory.view.getMenuIcon());
        userName.setText(getModel().getUsuario().getNome());
    }

    @FXML
    public void logout(MouseEvent event) {
        new Animation(logoutIcon).fadeIn();
        getModel().logout();
    }

    @FXML
    public void showMenu(MouseEvent event) {
        new Animation(menuIcon).fadeIn();
        if(getModel().isMenuOpen()){
                ViewFactory.view.getRoot().setLeft(null);
            getModel().setMenuOpen(false);
        }else {
            ViewFactory.view.getRoot().setLeft(ViewFactory.view.getLeftFullMenu());
            getModel().setMenuOpen(true);
        }
    }

    @FXML
    public void showNotification(MouseEvent event) {
    new Animation(notificationIcon).fadeIn();
    }

    @FXML
    public void showSettings(MouseEvent event) {
        new Animation(settingIcon).fadeIn();
    }
}
