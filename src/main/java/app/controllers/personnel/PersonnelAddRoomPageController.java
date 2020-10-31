package app.controllers.personnel;

import app.controllers.login.LoginPageController;
import app.controllers.popup.NotificationPopupController;
import app.controllers.setting.SettingPageController;
import app.models.AccountList;
import app.models.ItemList;
import app.models.Room;
import app.models.RoomList;
import app.services.ReadWriteFile;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PersonnelAddRoomPageController {

    @FXML Button roomListBtn, manageItemsBtn, accountSettingBtn, logoutBtn, addGuestBtn, addRoomBtn;
    @FXML Label userNameLabel, errorLabel, buildingError, floorError, roomNumberError, roomTypeError;
    @FXML ChoiceBox buildingChoiceBox, roomTypeChoiceBox, roomNumberChoiceBox, floorChoiceBox;

    private AccountList accounts;
    private RoomList rooms;
    private ItemList items;
    private ReadWriteFile dataSource;
    private String roomNumber;

    private ObservableList<String> roomTypeList = FXCollections.observableArrayList("Single", "Double");
    private ObservableList<String> buildingList = FXCollections.observableArrayList("1", "2");
    private ObservableList<String> roomNumberList = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    private ObservableList<String> floorList = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8");

    @FXML private void initialize(){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                userNameLabel.setText(accounts.getCurrentAccount().getName());
                setAllErrorDisable();
            }
        });
        dataSource = new ReadWriteFile("data", "rooms.csv");
        buildingChoiceBox.setItems(buildingList);
        roomTypeChoiceBox.setItems(roomTypeList);
        roomNumberChoiceBox.setItems(roomNumberList);
        floorChoiceBox.setItems(floorList);
    }

    public void setAccounts(AccountList accounts){
        this.accounts = accounts;
    }

    public void setRooms(RoomList rooms){
        this.rooms = rooms;
    }

    public void setItems(ItemList items){
        this.items = items;
    }

    public void setAllErrorDisable(){
        errorLabel.setVisible(false);
        buildingError.setVisible(false);
        floorError.setVisible(false);
        roomNumberError.setVisible(false);
        roomTypeError.setVisible(false);
    }

    public void resetField(){
        buildingChoiceBox.setValue(null);
        roomTypeChoiceBox.setValue(null);
        roomNumberChoiceBox.setValue(null);
        floorChoiceBox.setValue(null);
    }

    @FXML public void handleAccountSettingImageOnAction(MouseEvent event) throws IOException {
        ImageView b = (ImageView) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/setting_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        SettingPageController setting = loader.getController();
        setting.setAccounts(accounts);
        setting.setRooms(rooms);
        setting.setItems(items);
        setting.setPrevPage("PersonnelAddRoom");
        stage.show();
    }

    @FXML public void handleLogoutImageOnAction(MouseEvent event) throws IOException {
        ImageView b = (ImageView) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/login_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        LoginPageController login = loader.getController();
        stage.show();
    }

    @FXML public void handleAccountSettingBtnOnAction(MouseEvent event) throws IOException {
        Circle b = (Circle) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/setting_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        SettingPageController setting = loader.getController();
        setting.setAccounts(accounts);
        setting.setRooms(rooms);
        setting.setItems(items);
        setting.setPrevPage("PersonnelAddRoom");
        stage.show();
    }

    @FXML public void handleLogoutBtnOnAction(MouseEvent event) throws IOException {
        Circle b = (Circle) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/login_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        LoginPageController login = loader.getController();
        stage.show();
    }

    @FXML public void handleAddRoomBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/personnel_add_room_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        PersonnelAddRoomPageController addRoom = loader.getController();
        addRoom.setAccounts(accounts);
        addRoom.setRooms(rooms);
        addRoom.setItems(items);
        stage.show();
    }

    @FXML public void handleAddGuestBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/personnel_add_guest_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        PersonnelAddGuestPageController addGuest = loader.getController();
        addGuest.setAccounts(accounts);
        addGuest.setRooms(rooms);
        addGuest.setItems(items);
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
        manageItems.setRooms(rooms);
        manageItems.setItems(items);
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
        guestList.setRooms(rooms);
        guestList.setItems(items);
        stage.show();
    }

    @FXML public void handleConfirmBtnOnAction() throws IOException {
        setAllErrorDisable();
        if(!(buildingChoiceBox.getValue() == null) && !(floorChoiceBox.getValue() == null) && !(roomNumberChoiceBox.getValue() == null) && !(roomTypeChoiceBox.getValue() == null)){
            if(roomNumberChoiceBox.getValue().toString().equals("10")){
                roomNumber = buildingChoiceBox.getValue().toString() + floorChoiceBox.getValue().toString() + roomNumberChoiceBox.getValue().toString();
            }else{
                roomNumber = buildingChoiceBox.getValue().toString() + floorChoiceBox.getValue().toString() + "0" + roomNumberChoiceBox.getValue().toString();
            }
            if (rooms.checkRoomAvailable(buildingChoiceBox.getValue().toString(), roomNumber)){
                rooms.addRoom(new Room(buildingChoiceBox.getValue().toString(), floorChoiceBox.getValue().toString(), roomNumber, roomTypeChoiceBox.getValue().toString()));
                dataSource.setRoomData(rooms);

                Stage popup = new Stage();
                popup.initModality(Modality.APPLICATION_MODAL);
                popup.setResizable(false);
                FXMLLoader loader = new FXMLLoader
                        (getClass().getResource("/notification_popup.fxml"));
                popup.setScene(new Scene(loader.load(), 290, 100));
                NotificationPopupController noti = loader.getController();
                noti.setTextLabel("Create Successful");
                popup.showAndWait();

                resetField();
            }else{
                errorLabel.setVisible(true);
                errorLabel.setText("* This room has already been used");
                buildingError.setVisible(true);
                floorError.setVisible(true);
                roomNumberError.setVisible(true);
            }
        }else{
            errorLabel.setVisible(true);
            errorLabel.setText("* Required");
            if(buildingChoiceBox.getValue() == null){
                buildingError.setVisible(true);
            }else{
                buildingError.setVisible(false);
            }
            if(floorChoiceBox.getValue() == null){
                floorError.setVisible(true);
            }else{
                floorError.setVisible(false);
            }
            if(roomNumberChoiceBox.getValue() == null){
                roomNumberError.setVisible(true);
            }else{
                roomNumberError.setVisible(false);
            }
            if(roomTypeChoiceBox.getValue() == null){
                roomTypeError.setVisible(true);
            }else{
                roomTypeError.setVisible(false);
            }
        }

    }
}
