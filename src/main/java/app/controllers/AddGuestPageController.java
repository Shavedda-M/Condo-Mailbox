package sample;

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

public class AddGuestPageController {

    ObservableList<String> roomTypeList = FXCollections.observableArrayList("Single", "Double");
    ObservableList<Integer> buildingList = FXCollections.observableArrayList(1, 2, 3);

    @FXML Button roomListBtn, accountSettingBtn, logoutBtn, addItemBtn;
    @FXML Label userNameLabel;
    @FXML TextField nameField, roomField;
    @FXML ChoiceBox roomTypeChoiceBox, buildingChoiceBox;

    @FXML private void initialize(){
        buildingChoiceBox.setItems(buildingList);
        roomTypeChoiceBox.setItems(roomTypeList);
    }

    @FXML public void handleAccountSettingBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/setting_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        SettingPageController setting = loader.getController();
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

    @FXML public void handleManageItemsBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/manage_items_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        ManageItemsPageController manageItems = loader.getController();
        stage.show();
    }

    @FXML public void handleAddGuestBtnOnAction(ActionEvent event){

    }

    @FXML public void handleRoomListBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/room_list_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        RoomListPageController guestList = loader.getController();
        stage.show();
    }

}
