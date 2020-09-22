package app.controllers;

import app.models.*;
import au.com.bytecode.opencsv.CSVReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;

public class LoginPageController {
    @FXML Button loginBtn, registerBtn;
    @FXML TextField userNameField;
    @FXML PasswordField passwordField;

    private AccountList accounts;
    private RoomList rooms;

    @FXML private void initialize() throws IOException {
        accounts = new AccountList();
        rooms = new RoomList();
        CSVReader reader = new CSVReader(new FileReader("src\\main\\resources\\adminAccount.csv"), ',');
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            String accountType = nextLine[0];
            String name = nextLine[1];
            String userName = nextLine[2];
            String password = nextLine[3];
            if(accountType.equals("admin")){
                Account ac = new Account(name, userName, password, accountType);
                accounts.addAccount(ac);
            }else if(accountType.equals("personnel")){
                Account ac = new Personnel(name, userName, password);
                accounts.addAccount(ac);
            }else if(accountType.equals("guest")){
                String roomNumber = nextLine[4];
                Account ac = new Guest(name, userName, password, rooms.findRoom(name, roomNumber));
                accounts.addAccount(ac);
            }
        }
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
                stage.show();
            }else if(accounts.getCurrentAccount().getAccountType().equals("personnel")){
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/personnel_home_page.fxml")
                );
                stage.setScene(new Scene(loader.load(), 1024, 768));
                PersonnelHomePageController personnelPage =loader.getController();
                personnelPage.setAccounts(accounts);
                stage.show();
            }else if(accounts.getCurrentAccount().getAccountType().equals("guest")){
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/guest_home_page.fxml")
                );
                stage.setScene(new Scene(loader.load(), 1024, 768));
                GuestHomePageController guestPage =loader.getController();
                guestPage.setAccounts(accounts);
                stage.show();
            }
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
