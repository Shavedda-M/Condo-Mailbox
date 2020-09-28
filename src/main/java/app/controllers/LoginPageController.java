package app.controllers;

import app.models.*;
import app.services.ReadFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageController {
    @FXML Button loginBtn, registerBtn;
    @FXML TextField userNameField;
    @FXML PasswordField passwordField;

    private AccountList accounts = new AccountList();;
    private RoomList rooms = new RoomList();

    @FXML private void initialize() throws IOException {
        rooms = ReadFile.addRoomFromFile();
        accounts = ReadFile.addAccountFromFile("src\\main\\resources\\adminAccount.csv", rooms);
    }

    @FXML public void handleLoginBtnOnAction(ActionEvent event) throws IOException {
        if(accounts.checkAccount(userNameField.getText(), passwordField.getText())){
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();
            if(accounts.getCurrentAccount().getAccountType().equals("admin")){
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/admin_home_page.fxml")
                );
                stage.setScene(new Scene(loader.load(), 1024, 768));
                AdminHomePageController adminPage =loader.getController();
                adminPage.setAccounts(accounts);
            }else if(accounts.getCurrentAccount().getAccountType().equals("personnel")){
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/personnel_home_page.fxml")
                );
                stage.setScene(new Scene(loader.load(), 1024, 768));
                PersonnelHomePageController personnelPage =loader.getController();
                personnelPage.setAccounts(accounts);
            }else if(accounts.getCurrentAccount().getAccountType().equals("guest")){
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/guest_home_page.fxml")
                );
                stage.setScene(new Scene(loader.load(), 1024, 768));
                GuestHomePageController guestPage =loader.getController();
                guestPage.setAccounts(accounts);
            }
            stage.show();
        }

    }

    @FXML public void handleRegisterBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/register_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        stage.show();
    }
}
