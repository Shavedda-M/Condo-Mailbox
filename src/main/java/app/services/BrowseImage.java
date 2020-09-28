package app.services;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;

public class BrowseImage {

    public static FileChooser Browse(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Browse Image");

        String userDirectoryString = System.getProperty("user.home") + "\\Pictures";
        File userDirectory = new File(userDirectoryString);

        if(!userDirectory.canRead()){
            userDirectory = new File("c:/");
        }

        fileChooser.setInitialDirectory(userDirectory);

        FileChooser.ExtensionFilter extFilterAll = new FileChooser.ExtensionFilter("All Files", "*.*");
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterAll,extFilterJPG, extFilterPNG);

        return fileChooser;
    }
}
