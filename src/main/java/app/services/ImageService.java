package app.services;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageService {

    public String getImagePath(String dirName, String imageName){
        File jarDir = null;
        File codeDir = null;
        try{
            jarDir = new File(this.getClass().getProtectionDomain()
                    .getCodeSource().getLocation()
                    .toURI().getPath());
            codeDir = jarDir.getParentFile();
            String path = codeDir.toString() + File.separator + dirName + File.separator + imageName;
            File uploadFile = new File(path);
            return uploadFile.toURI().toString();
        }catch (URISyntaxException e){
            e.printStackTrace();
        }
        return null;
    }


    public String copyImage(String dirName, File originalImageName, String defaultImage) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
        File file = new File(dirName);
        if (!file.exists()) {
            file.mkdirs();
        }
        File jarDir = null;
        File codeDir = null;
        try {

            jarDir = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            codeDir = jarDir.getParentFile();

            String copyPath = codeDir.toString() + File.separator + dirName + File.separator + originalImageName.getName();

            File copy = new File(copyPath);

            try {
                if (!copy.exists()) {
                    Files.copy(originalImageName.toPath(), copy.toPath());
                    return originalImageName.getName();
                } else {
                    if (originalImageName.getName().contains(".jpg")) {
                        copyPath = codeDir.toString() + File.separator + dirName + File.separator + dateFormat.format(new Date()) + ".jpg";
                        copy = new File(copyPath);
                        Files.copy(originalImageName.toPath(), copy.toPath());
                    } else if (originalImageName.getName().contains(".jpeg")) {
                        copyPath = codeDir.toString() + File.separator + dirName + File.separator + dateFormat.format(new Date()) + ".jpeg";
                        copy = new File(copyPath);
                        Files.copy(originalImageName.toPath(), copy.toPath());
                    } else if (originalImageName.getName().contains(".png")) {
                        copyPath = codeDir.toString() + File.separator + dirName + File.separator + dateFormat.format(new Date()) + ".png";
                        copy = new File(copyPath);
                        Files.copy(originalImageName.toPath(), copy.toPath());
                    }
                    return copy.getName();
                }
            } catch (IOException e) {
                System.out.println("Can't copy. Already have this image");
            } catch (NullPointerException n){
                System.out.println("Can't copy file");
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (NullPointerException n){
            System.out.println("Can't copy file");
        }
        return defaultImage;
    }
}
