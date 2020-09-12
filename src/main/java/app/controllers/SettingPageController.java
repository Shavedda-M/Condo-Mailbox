package app.controllers;

import app.models.AccountList;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingPageController {
    @FXML Button changePasswordBtn, logoutBtn, backBtn;
    @FXML Label userNameLabel;

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

    @FXML public void handleLogoutBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/login_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        LoginPageController login = loader.getController();
        stage.show();

    }

    @FXML public void handleProfileSettingBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/profile_setting_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        ProfileSettingPageController profileSet = loader.getController();
        profileSet.setAccounts(accounts);
        stage.show();
    }

    @FXML public void handleChangePasswordBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/change_password_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        ChangePasswordPageController changePass = loader.getController();
        changePass.setAccounts(accounts);
        stage.show();
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event){

    }

}
