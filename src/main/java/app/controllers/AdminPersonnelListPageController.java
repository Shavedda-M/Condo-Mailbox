package app.controllers;

import app.models.Account;
import app.models.AccountList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPersonnelListPageController {
    @FXML Button addPersonnelBtn, accountSettingBtn, logoutBtn;
    @FXML Label userNameLabel;
    @FXML TableView personnelTable;

    private AccountList accounts;
    private ObservableList<Account> accountObservableList;

    @FXML private void initialize(){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                userNameLabel.setText(accounts.getCurrentAccount().getName());
                showPersonnelList();
            }
        });
    }

    private void showPersonnelList(){
        accountObservableList = FXCollections.observableArrayList();
        for(Account a : accounts.toList()){
            if(a.getAccountType().equals("personnel")){
                accountObservableList.add(a);
            }
        }
        personnelTable.setItems(accountObservableList);

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(250);
        TableColumn userNameCol = new TableColumn("Username");
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        userNameCol.setPrefWidth(250);
        TableColumn statuscol = new TableColumn("Status");
        statuscol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statuscol.setPrefWidth(113);

        personnelTable.getColumns().addAll(nameCol, userNameCol, statuscol);

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
        setting.setPrevPage("AdminPersonnelList");
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

    @FXML public void handleAddPersonnelBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/admin_add_personnel_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        AdminAddPersonnelPageController addPer = loader.getController();
        addPer.setAccounts(accounts);
        stage.show();
    }
}
