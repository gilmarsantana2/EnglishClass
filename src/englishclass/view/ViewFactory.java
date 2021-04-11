package englishclass.view;

import englishclass.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import englishclass.model.ModelAcess;
import javafx.scene.image.Image;

import java.io.IOException;

public class ViewFactory {
    public static ViewFactory view = new ViewFactory();
    private ModelAcess modelAcess = new ModelAcess();
    private RootBorder root = new RootBorder();

    private final String mainView = "mainView.fxml";
    private final String leftFullMenu = "leftFullMenu.fxml";
    private final String topMenu = "topMenu.fxml";
    private final String loginView = "loginView.fxml";
    private final String installView = "installView.fxml";

    public Node getMainView(){
        MainController controller = new MainController(modelAcess);
        return inicializeScene(mainView, controller);
    }

    public Node getLeftFullMenu(){
        LeftFullMenuController controller = new LeftFullMenuController(modelAcess);
        return inicializeScene(leftFullMenu, controller);
    }

    public Node getTopMenu(){
        TopMenuController controller = new TopMenuController(modelAcess);
        return inicializeScene(topMenu, controller);
    }

    public Scene getLoginView(){
        LoginController controller = new LoginController(modelAcess);
        return new Scene((Parent) inicializeScene(loginView, controller));
    }

    public Scene getInstallView(){
        InstallController controller = new InstallController(modelAcess);
        return new Scene((Parent) inicializeScene(installView, controller));
    }

    private Node inicializeScene(String path, AbstractController controller) {
        Parent root;
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource(path));
            loader.setController(controller);
            root = loader.load();
            return root;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    return null;
    }

    public Scene startBorder(){
        root.getBorder().setPrefSize(1366, 768);
        root.setCenter(getMainView());
        //root.setLeft(getLeftFullMenu());
        root.setTop(getTopMenu());
        return new Scene(root.getBorder());
    }

    public ModelAcess getModelAcess(){
        return modelAcess;
    }

    public RootBorder getRoot(){
        return root;
    }

    /*****************************************************************************************
     * *******************SESSÃO DAS IMAGENS DO SISTEMA*******************************/

    private String userImage = "user.png";
    private String appIcon ="class.png";
    private String bellIcon ="bell.png";
    private String businessIcon ="business-report.png";
    private String menuIcon ="menu.png";
    private String doorIcon ="opened-door.png";
    private String settingsIcon ="settings.png";
    private String googleIcon= "google_logo.png";

    public Image getUserImage(){
        return getIcon(userImage);
    }
    public Image getAppIcon(){
        return getIcon(appIcon);
    }
    public Image getBellIcon(){
        return getIcon(bellIcon);
    }
    public Image getBusinessIcon(){
        return getIcon(businessIcon);
    }
    public Image getMenuIcon(){
        return getIcon(menuIcon);
    }
    public Image getDoorIcon(){
        return getIcon(doorIcon);
    }
    public Image getSettingIcon(){
        return getIcon(settingsIcon);
    }
    public Image getGoogleIcon(){
        return getIcon(googleIcon);
    }

    private Image getIcon(String path){
        var icon = new Image("icons/" + path);
        return icon;
    }
}
