package app.controllers.admin;

import app.controllers.login.LoginPageController;
import app.controllers.popup.NotificationPopupController;
import app.controllers.setting.SettingPageController;
import app.exceptions.UsernameNotAvailableException;
import app.models.*;
import app.services.BrowseImage;
import app.services.ImageService;
import app.services.ReadWriteFile;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class AdminAddPersonnelPageController {
    @FXML private TextField nameField, userNameField, passwordField, passwordConfirmField;
    @FXML private Button confirmBtn, personnelListBtn, accountSettingBtn, logoutBtn;;
    @FXML private Label userNameLabel, errorLabel, nameError, usernameError, passwordError, passwordConfirmError;
    @FXML private ImageView profileImageView;

    private AccountList accounts;
    private RoomList rooms;
    private ReadWriteFile dataSource;
    private ImageService imageService;
    private File currentImageFile;

    @FXML private void initialize(){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                userNameLabel.setText(accounts.getCurrentAccount().getName());
                dataSource = new ReadWriteFile("data", "accounts.csv");
                currentImageFile = new File("classes/Image" + File.separator + "default profile.jpg");
                imageService = new ImageService();
                setAllErrorDisable();
            }
        });
    }

    public void setAccounts(AccountList accounts){
        this.accounts = accounts;
    }

    public void setRooms(RoomList rooms){
        this.rooms = rooms;
    }

    public void setAllErrorDisable(){
        errorLabel.setVisible(false);
        usernameError.setVisible(false);
        passwordError.setVisible(false);
        passwordConfirmError.setVisible(false);
        nameError.setVisible(false);
    }

    public void resetField(){
        nameField.setText("");
        userNameField.setText("");
        passwordField.setText("");
        passwordConfirmField.setText("");
        profileImageView.setImage(new Image(imageService.getImagePath("classes/Image", "default profile.jpg")));
    }

    @FXML public void handleAccountSettingImageOnAction(MouseEvent event) throws IOException {
        ImageView b = (ImageView) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/setting_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        SettingPageController setting = loader.getController();
        setting.setAccounts(accounts);
        setting.setRooms(rooms);
        setting.setPrevPage("AdminAddPersonnel");
        stage.show();
    }

    @FXML public void handleLogoutImageOnAction(MouseEvent event) throws IOException {
        ImageView b = (ImageView) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/login_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        LoginPageController login = loader.getController();
        stage.show();
    }

    @FXML public void handleAccountSettingBtnOnAction(MouseEvent event) throws IOException {
        Circle b = (Circle) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/setting_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        SettingPageController setting = loader.getController();
        setting.setAccounts(accounts);
        setting.setRooms(rooms);
        setting.setPrevPage("AdminAddPersonnel");
        stage.show();
    }

    @FXML public void handleLogoutBtnOnAction(MouseEvent event) throws IOException {
        Circle b = (Circle) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/login_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        LoginPageController login = loader.getController();
        stage.show();
    }

    @FXML public void handlePersonnelListBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/admin_personnel_list_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        AdminPersonnelListPageController perList = loader.getController();
        perList.setAccounts(accounts);
        perList.setRooms(rooms);
        stage.show();
    }

    @FXML public void handleConfirmBtnOnAction(ActionEvent event) throws IOException {
        setAllErrorDisable();
        if(!nameField.getText().equals("") && !userNameField.getText().equals("") && !passwordField.getText().equals("") && !passwordConfirmField.getText().equals("")){
            try{
                if(accounts.checkUsernameAvailable(userNameField.getText())){
                    if(passwordField.getText().equals(passwordConfirmField.getText())){
                        Account ac = new Personnel(nameField.getText(), userNameField.getText(), passwordField.getText(), imageService.copyImage("classes/Image", currentImageFile, "default profile.jpg"));
                        accounts.addAccount(ac);
                        dataSource.setAccountsData(accounts);

                        Stage popupWindow=new Stage();
                        popupWindow.initModality(Modality.APPLICATION_MODAL);
                        popupWindow.setResizable(false);

                        Stage popup = new Stage();
                        popup.initModality(Modality.APPLICATION_MODAL);
                        popup.setResizable(false);
                        FXMLLoader loader = new FXMLLoader
                                (getClass().getResource("/notification_popup.fxml"));
                        popup.setScene(new Scene(loader.load(), 290, 100));
                        NotificationPopupController noti = loader.getController();
                        noti.setTextLabel("Create Successful");
                        popup.showAndWait();
                        resetField();
                    }else{
                        errorLabel.setVisible(true);
                        errorLabel.setText("* Please enter the same password");
                        passwordError.setVisible(true);
                        passwordConfirmError.setVisible(true);
                    }
                }
            }catch (UsernameNotAvailableException e){
                errorLabel.setVisible(true);
                errorLabel.setText("* " + e.getMessage());
                usernameError.setVisible(true);
            }
        }else{
            errorLabel.setVisible(true);
            errorLabel.setText("* Required");
            if(nameField.getText().equals("")){
                nameError.setVisible(true);
            }else{
                nameError.setVisible(false);
            }
            if(userNameField.getText().equals("")){
                usernameError.setVisible(true);
            }else{
                usernameError.setVisible(false);
            }
            if(passwordField.getText().equals("")){
                passwordError.setVisible(true);
            }else{
                passwordError.setVisible(false);
            }
            if(passwordConfirmField.getText().equals("")){
                passwordConfirmError.setVisible(true);
            }else{
                passwordConfirmError.setVisible(false);
            }
        }
    }

    @FXML public void handleBrowseImageBtnOnAction(ActionEvent event) throws URISyntaxException {

        BrowseImage browseImage = new BrowseImage();

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser;

        fileChooser = browseImage.Browse();

        File file = fileChooser.showOpenDialog(stage);
        try{
            currentImageFile = file;
            Image image = new Image(file.toURI().toString());
            profileImageView.setImage(image);
            profileImageView.setPreserveRatio(false);
            profileImageView.setFitHeight(145);
            profileImageView.setFitWidth(140);
        }catch (Exception ex){

        }
    }
}
