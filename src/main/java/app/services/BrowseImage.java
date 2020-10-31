package app.services;

import javafx.stage.FileChooser;

import java.io.File;
import java.net.URISyntaxException;

public class BrowseImage {

    public FileChooser Browse() throws URISyntaxException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Browse Image");

        File jarDir = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        File codeDir = jarDir.getParentFile();

//        String userDirectoryString = System.getProperty("user.home") + "\\Pictures";
        String userDirectoryString = codeDir.toString() + File.separator + "classes" + File.separator + "Image";
        File userDirectory = new File(userDirectoryString);

        if(!userDirectory.canRead()){
            userDirectory = new File("c:/");
        }

        fileChooser.setInitialDirectory(userDirectory);

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.JPEG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterJPEG, extFilterPNG);

        return fileChooser;
    }
}
