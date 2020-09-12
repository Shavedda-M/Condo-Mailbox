package app.controllers;

import app.models.Account;
import app.models.AccountList;
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
    @FXML TextField idField;
    @FXML PasswordField passwordField;

    private AccountList accounts;

    @FXML private void initialize() throws IOException {
        accounts = new AccountList();
        CSVReader reader = new CSVReader(new FileReader("src\\main\\resources\\adminAccount.csv"), ',');
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null)
        {
            Account ac = new Account(nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
            accounts.addAccount(ac);
        }
    }

    @FXML public void handleLoginBtnOnAction(ActionEvent event) throws IOException {
        if(accounts.checkAccount(idField.getText(), passwordField.getText())){
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();
            if(accounts.getCurrentAccount().getAccountType().equals("admin")){
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/admin_home_page.fxml")
                );
                stage.setScene(new Scene(loader.load(), 800, 600));
                AdminHomePageController adminPage =loader.getController();
                adminPage.setAccounts(accounts);
                stage.show();
            }else if(accounts.getCurrentAccount().getAccountType().equals("personnel")){
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/personnel_home_page.fxml")
                );
                stage.setScene(new Scene(loader.load(), 800, 600));
                PersonnelHomePageController personnelPage =loader.getController();
                personnelPage.setAccounts(accounts);
                stage.show();
            }else if(accounts.getCurrentAccount().getAccountType().equals("guest")){
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/guest_home_page.fxml")
                );
                stage.setScene(new Scene(loader.load(), 800, 600));
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
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }
}
