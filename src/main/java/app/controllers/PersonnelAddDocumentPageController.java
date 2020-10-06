package app.controllers;

import app.models.AccountList;
import app.models.RoomList;
import app.services.BrowseImage;
import app.services.ReadWriteFile;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class PersonnelAddDocumentPageController {

    ObservableList<Integer> buildingList = FXCollections.observableArrayList(1, 2, 3);
    ObservableList<String> priorityList = FXCollections.observableArrayList("Low", "Medium", "High", "Urgent", "Secret");

    @FXML Button roomListBtn, addGuestBtn, addItemBtn, backBtn, addRoomBtn, browseImageBtn;
    @FXML Label userNameLabel;
    @FXML TextField recipientField, senderField, sizeField, roomField;
    @FXML ChoiceBox buildingChoiceBox, priorityChoiceBox;
    @FXML ImageView itemImageView;

    private AccountList accounts;
    private RoomList rooms;
    private ReadWriteFile dataSource;

    @FXML private void initialize(){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                userNameLabel.setText(accounts.getCurrentAccount().getName());
            }
        });

        buildingChoiceBox.setItems(buildingList);
        priorityChoiceBox.setItems(priorityList);
    }

    public void setAccounts(AccountList accounts){
        this.accounts = accounts;
    }

    public void setRooms(RoomList rooms){
        this.rooms = rooms;
    }

    public void setDataSource(ReadWriteFile dataSource){
        this.dataSource = dataSource;
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
        setting.setDataSource(dataSource);
        setting.setPrevPage("PersonnelAddDocument");
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
        setting.setDataSource(dataSource);
        setting.setPrevPage("PersonnelAddDocument");
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
        manageItems.setDataSource(dataSource);
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
        guestList.setDataSource(dataSource);
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
        addRoom.setDataSource(dataSource);
        stage.show();
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/personnel_select_add_item_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        PersonnelSelectAddItemPageController addItem = loader.getController();
        addItem.setAccounts(accounts);
        addItem.setRooms(rooms);
        addItem.setDataSource(dataSource);
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
        addGuest.setDataSource(dataSource);
        stage.show();
    }

    @FXML public void handleAddItemBtnOnAction(ActionEvent event){

    }

    @FXML public void handleBrowseImageBtnOnAction(ActionEvent event){

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser;

        fileChooser = BrowseImage.Browse();

        File file = fileChooser.showOpenDialog(stage);
        try{
            Image image = new Image(file.toURI().toString());
            itemImageView.setImage(image);
            itemImageView.setPreserveRatio(false);
            itemImageView.setFitHeight(130);
            itemImageView.setFitWidth(150);
        }catch (Exception ex){

        }
    }
}
