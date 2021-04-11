package englishclass;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import englishclass.view.ViewFactory;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("English Class");
        //var config = new File("../config/config.properties");
        //System.out.println(config.getCanonicalPath());
        //if(config.exists()){
            primaryStage.setScene(ViewFactory.view.startBorder());
       // }else{
        //    primaryStage.setScene(ViewFactory.view.getInstallView());
        //}
        primaryStage.getIcons().add(ViewFactory.view.getAppIcon());
        ViewFactory.view.getModelAcess().setPrimaryStage(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
