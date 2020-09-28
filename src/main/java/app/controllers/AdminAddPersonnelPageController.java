package app.controllers;

import app.models.AccountList;
import app.services.BrowseImage;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AdminAddPersonnelPageController {
    @FXML TextField nameField, idField, passwordField, passwordConfirmField;
    @FXML Button confirmBtn, personnelListBtn, accountSettingBtn, logoutBtn;;
    @FXML Label userNameLabel;
    @FXML ImageView profileImageView;

    private AccountList accounts;

    @FXML private void initialize(){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                userNameLabel.setText(accounts.getCurrentAccount().getName());
            }
        });
    }

    public void setAccounts(AccountList accounts){
        this.accounts = accounts;
    }

    @FXML public void handleAccountSettingBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/setting_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        SettingPageController setting = loader.getController();
        setting.setAccounts(accounts);
        setting.setPrevPage("AdminAddPersonnel");
        stage.show();
    }

    @FXML public void handleLogoutBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
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
        stage.show();
    }

    @FXML public void handleConfirmBtnOnAction(ActionEvent event){

    }

    @FXML public void handleBrowseImageBtnOnAction(ActionEvent event){

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser;

        fileChooser = BrowseImage.Browse();

        File file = fileChooser.showOpenDialog(stage);
        try{
            Image image = new Image(file.toURI().toString());
            profileImageView.setImage(image);
            profileImageView.setPreserveRatio(false);
            profileImageView.setFitHeight(130);
            profileImageView.setFitWidth(150);
        }catch (Exception ex){

        }
    }
}
