package app.controllers.personnel;

import app.controllers.login.LoginPageController;
import app.controllers.popup.NotificationPopupController;
import app.controllers.setting.SettingPageController;
import app.exceptions.RoomFullException;
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

public class PersonnelAddGuestPageController {

    @FXML Button roomListBtn, accountSettingBtn, logoutBtn, manageItemsBtn, addGuestBtn, addRoomBtn;
    @FXML Label userNameLabel, errorLabel, nameError, buildingError, roomNumberError;
    @FXML TextField nameField, roomNumberField;
    @FXML ChoiceBox buildingChoiceBox;

    private ObservableList<Integer> buildingList = FXCollections.observableArrayList(1, 2);

    private AccountList accounts;
    private RoomList rooms;
    private ItemList items;
    private ReadWriteFile dataSource;

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
        nameError.setVisible(false);
        buildingError.setVisible(false);
        roomNumberError.setVisible(false);
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
        setting.setPrevPage("PersonnelAddGuest");
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
        setting.setPrevPage("PersonnelAddGuest");
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

    @FXML public void handleAddGuestBtnOnAction() throws IOException {
        setAllErrorDisable();
        if(!nameField.getText().equals("") && !(buildingChoiceBox.getValue() == null) && !roomNumberField.getText().equals("")){
            if(rooms.checkRoom(buildingChoiceBox.getValue().toString(), roomNumberField.getText())){
                Room room = rooms.findRoom(buildingChoiceBox.getValue().toString(), roomNumberField.getText());
                try {
                    room.checkAvailable(nameField.getText());
                    dataSource.setRoomData(rooms);

                    Stage popup = new Stage();
                    popup.initModality(Modality.APPLICATION_MODAL);
                    popup.setResizable(false);
                    FXMLLoader loader = new FXMLLoader
                            (getClass().getResource("/notification_popup.fxml"));
                    popup.setScene(new Scene(loader.load(), 290, 100));
                    NotificationPopupController noti = loader.getController();
                    noti.setTextLabel("Add Success");
                    popup.showAndWait();
                } catch (RoomFullException e) {
                    errorLabel.setVisible(true);
                    errorLabel.setText("* " + e.getMessage());
                    roomNumberError.setVisible(true);
                }

            }else{
                errorLabel.setVisible(true);
                errorLabel.setText("* Invalid room number");
                roomNumberError.setVisible(true);
            }
        }else{
            errorLabel.setVisible(true);
            errorLabel.setText("* Required");
            if(nameField.getText().equals("")){
                nameError.setVisible(true);
            }else{
                nameError.setVisible(false);
            }
            if(buildingChoiceBox.getValue() == null){
                buildingError.setVisible(true);
            }else{
                buildingError.setVisible(false);
            }
            if(roomNumberField.getText().equals("")){
                roomNumberError.setVisible(true);
            }else{
                roomNumberError.setVisible(false);
            }
        }
    }

}
