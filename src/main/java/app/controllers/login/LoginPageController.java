package app.controllers.login;

import app.controllers.admin.AdminHomePageController;
import app.controllers.guest.GuestHomePageController;
import app.controllers.personnel.PersonnelHomePageController;
import app.controllers.popup.NotificationPopupController;
import app.exceptions.BannedAccountException;
import app.exceptions.NoAccountFoundException;
import app.models.*;
import app.services.ReadWriteFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class LoginPageController {
    @FXML private Button loginBtn, createAccountBtn;
    @FXML private TextField userNameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private AccountList accounts = new AccountList();
    private RoomList rooms = new RoomList();
    private ItemList items;
    private ReadWriteFile dataSource;

    @FXML private void initialize() throws IOException {
        dataSource = new ReadWriteFile("data", "rooms.csv");
        rooms = dataSource.getRoomData();
        dataSource.setFileName("accounts.csv");
        accounts = dataSource.getAccountsData();
        dataSource.setFileName("items.csv");
        items = dataSource.getItemData();
    }

    @FXML public void handleLoginBtnOnAction(ActionEvent event) throws IOException {
        try{
            if(accounts.checkAccount(userNameField.getText(), passwordField.getText())) {
                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();
                if (accounts.getCurrentAccount().getAccountType().equals("admin")) {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/admin_home_page.fxml")
                    );
                    stage.setScene(new Scene(loader.load(), 1024, 768));
                    AdminHomePageController adminPage = loader.getController();
                    adminPage.setAccounts(accounts);
                    adminPage.setRooms(rooms);
                } else if (accounts.getCurrentAccount().getAccountType().equals("personnel")) {
                    Personnel per = (Personnel)accounts.getCurrentAccount();
                    per.setLastLoginTime(new Date());

                    dataSource.setFileName("accounts.csv");
                    dataSource.setAccountsData(accounts);

                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/personnel_home_page.fxml")
                    );
                    stage.setScene(new Scene(loader.load(), 1024, 768));
                    PersonnelHomePageController personnelPage = loader.getController();
                    personnelPage.setAccounts(accounts);
                    personnelPage.setRooms(rooms);
                    personnelPage.setItems(items);
                } else if (accounts.getCurrentAccount().getAccountType().equals("guest")) {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/guest_home_page.fxml")
                    );
                    stage.setScene(new Scene(loader.load(), 1024, 768));
                    GuestHomePageController guestPage = loader.getController();
                    guestPage.setAccounts(accounts);
                    guestPage.setRooms(rooms);
                    guestPage.setItems(items);
                }
                stage.show();
            }
        }catch (BannedAccountException e){
            dataSource.setFileName("accounts.csv");
            dataSource.setAccountsData(accounts);

            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setResizable(false);
            FXMLLoader loader = new FXMLLoader
                    (getClass().getResource("/notification_popup.fxml"));
            popup.setScene(new Scene(loader.load(), 290, 100));
            NotificationPopupController noti = loader.getController();
            noti.setTextLabel(e.getMessage());
            popup.showAndWait();
        } catch(NoAccountFoundException e){
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML public void handleRegisterBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/register_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        RegisterPageController regis =loader.getController();
        regis.setAccounts(accounts);
        regis.setRooms(rooms);
        stage.show();
    }
}
