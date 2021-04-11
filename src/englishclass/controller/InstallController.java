package englishclass.controller;

import englishclass.model.ModelAcess;
import englishclass.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;

import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ResourceBundle;

public class InstallController extends AbstractController implements Initializable {

    @FXML
    private Label lblIndicador;

    private String path;
    private String absolutePath;
    private File dir, save;

    public InstallController(ModelAcess model) {
        super(model);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dir = new File("..");
    }

    @FXML
    public void goInstall(ActionEvent event) {
        save = new DirectoryChooser().showDialog(getModel().getPrimaryStage());
        absolutePath = save.getAbsolutePath() + "/EnglishClass";
        var novoDir = new File(absolutePath);
        novoDir.mkdir();
        Runnable run = () ->{
            try {
                copyDirectory(dir, novoDir);
                System.out.println("Instalando..");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Falha ao Copiar");
            }
        };
        Thread copy = new Thread(run);
        copy.start();
        while(copy.isAlive()){
            lblIndicador.setText("Instalando..");
        }
        boolean done = createProperty();
        if(done){
            lblIndicador.setText("Instalado com Sucesso. \nAbrindo as aulas!");
        }else {
            lblIndicador.setText("Falha na Instalação.");
        }

    }

    private boolean createProperty() {
        var createDir = new File(absolutePath + "/config");
        createDir.mkdir();
        /*
        var createFile = new File(absolutePath + "/configs/config.properties");
        try {
            createFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }*/

        try {
            var properties = new FileWriter(absolutePath + "/config/config.properties");
            var text= new PrintWriter(properties);
            text.print("banco = true");
            text.close();
            System.out.println("Criando arquivo de configuração");
            return true;
        } catch (IOException e) {
            System.out.println("falha ao criar arquivo de configuração");
            return false;
        }
    }

    public void copyFile(File source, File destination) throws IOException {
        if (destination.exists())
            destination.delete();

        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;

        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destinationChannel = new FileOutputStream(destination).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),
                    destinationChannel);
        } finally {
            if (sourceChannel != null && sourceChannel.isOpen())
                sourceChannel.close();
            if (destinationChannel != null && destinationChannel.isOpen())
                destinationChannel.close();
        }
    }

    public void copyDirectory(File source, File dest) throws IOException {
        if (source.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }
            String[] children = source.list();
            for (int i = 0; i < children.length; i++) {
                copyDirectory(new File(source, children[i]), new File(dest, children[i]));
            }
        } else {
            copyFile(source, dest);
        }
    }

}
