package app.controllers;

import app.models.AccountList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddItemPageController {

    ObservableList<String> itemTypeList = FXCollections.observableArrayList("Letter", "Document", "Package");
    ObservableList<Integer> buildingList = FXCollections.observableArrayList(1, 2, 3);

    @FXML Button roomListBtn, addGuestBtn, accountSettingBtn, logoutBtn, addItemBtn, backBtn;
    @FXML Label userNameLabel;
    @FXML TextField nameField, senderField, sizeField, trackingNumberField, roomField;
    @FXML ChoiceBox itemTypeChoiceBox, buildingChoiceBox;

    private AccountList accounts;

    @FXML private void initialize(){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                userNameLabel.setText(accounts.getCurrentAccount().getName());
            }
        });

        itemTypeChoiceBox.setItems(itemTypeList);
        buildingChoiceBox.setItems(buildingList);
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
        stage.setScene(new Scene(loader.load(), 800, 600));
        SettingPageController setting = loader.getController();
        setting.setAccounts(accounts);
        stage.show();
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

    @FXML public void handleRoomListBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/room_list_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        RoomListPageController guestList = loader.getController();
        guestList.setAccounts(accounts);
        stage.show();
    }

    @FXML public void handleAddItemBtnOnAction(ActionEvent event){

    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/manage_items_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        ManageItemsPageController manageItems = loader.getController();
        manageItems.setAccounts(accounts);
        stage.show();
    }

    @FXML public void handleAddGuestBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/add_guest_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        AddGuestPageController addGuest = loader.getController();
        addGuest.setAccounts(accounts);
        stage.show();
    }
}
