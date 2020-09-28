package app.controllers;

import app.models.AccountList;
import app.services.BrowseImage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class PersonnelAddGuestPageController {

    ObservableList<String> roomTypeList = FXCollections.observableArrayList("Single", "Double");
    ObservableList<Integer> buildingList = FXCollections.observableArrayList(1, 2, 3);

    @FXML Button roomListBtn, accountSettingBtn, logoutBtn, manageItemsBtn, addItemBtn, addRoomBtn;
    @FXML Label userNameLabel;
    @FXML TextField nameField, roomField;
    @FXML ChoiceBox roomTypeChoiceBox, buildingChoiceBox;
    @FXML ImageView profileImageView;

    private AccountList accounts;

    @FXML private void initialize(){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                userNameLabel.setText(accounts.getCurrentAccount().getName());
            }
        });

        buildingChoiceBox.setItems(buildingList);
        roomTypeChoiceBox.setItems(roomTypeList);
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
        setting.setPrevPage("PersonnelAddGuest");
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

    @FXML public void handleManageItemsBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/personnel_manage_items_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        PersonnelManageItemsPageController manageItems = loader.getController();
        manageItems.setAccounts(accounts);
        stage.show();
    }

    @FXML public void handleRoomListBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/personnel_room_list_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        PersonnelRoomListPageController guestList = loader.getController();
        guestList.setAccounts(accounts);
        stage.show();
    }

    @FXML public void handleAddGuestBtnOnAction(ActionEvent event){

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
