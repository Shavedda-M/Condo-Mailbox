package app.controllers.personnel;

import app.controllers.login.LoginPageController;
import app.controllers.popup.ItemInfoPopupController;
import app.controllers.popup.NotificationPopupController;
import app.controllers.popup.RoomInfoPopupController;
import app.controllers.setting.SettingPageController;
import app.models.*;
import app.services.ReadWriteFile;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class PersonnelRoomListPageController {

    @FXML private Button manageItemsBtn, addGuestBtn, accountSettingBtn, logoutBtn, addRoomBtn, searchGuestBtn, editRoomBtn;
    @FXML private Label userNameLabel;
    @FXML private TableView<Room> roomTable;
    @FXML private TextField guestNameSearchField;


    private AccountList accounts;
    private RoomList rooms;
    private ItemList items;
    private ReadWriteFile dataSource;
    private Room selectedRoom = null;
    private ObservableList<Room> roomObservableList;


    @FXML private void initialize(){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                userNameLabel.setText(accounts.getCurrentAccount().getName());
                dataSource = new ReadWriteFile("data", "accounts.csv");
                roomTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        showSelectedRoom(newValue);
                    }
                });
                showRoomList();
            }
        });
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


    private void showRoomList(){
        roomObservableList = FXCollections.observableArrayList(rooms.toList());
        roomTable.setItems(roomObservableList);

        TableColumn buildingCol = new TableColumn("Building");
        buildingCol.setCellValueFactory(new PropertyValueFactory<>("building"));
        buildingCol.setPrefWidth(85);
        TableColumn floorCol = new TableColumn("Floor");
        floorCol.setCellValueFactory(new PropertyValueFactory<>("floor"));
        floorCol.setPrefWidth(80);
        TableColumn roomCol = new TableColumn("Room");
        roomCol.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        roomCol.setPrefWidth(90);
        roomCol.setSortType(TableColumn.SortType.ASCENDING);
        TableColumn roomTypeCol = new TableColumn("Room Type");
        roomTypeCol.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        roomTypeCol.setPrefWidth(100);
        TableColumn allGuestCol = new TableColumn("Guest List");
        allGuestCol.setCellValueFactory(new PropertyValueFactory<>("guestNameList"));
        allGuestCol.setPrefWidth(475);
        allGuestCol.setSortable(false);

        roomTable.getColumns().addAll(buildingCol, floorCol, roomCol, roomTypeCol, allGuestCol);
        roomTable.getSortOrder().add(roomCol);
    }

    private void showSearchGuestNameList(String roomNumber){
        roomTable.getColumns().clear();

        roomObservableList = FXCollections.observableArrayList(rooms.getGuestNameSearchRoomList(guestNameSearchField.getText()));
        roomTable.setItems(roomObservableList);

        TableColumn buildingCol = new TableColumn("Building");
        buildingCol.setCellValueFactory(new PropertyValueFactory<>("building"));
        buildingCol.setPrefWidth(85);
        TableColumn floorCol = new TableColumn("Floor");
        floorCol.setCellValueFactory(new PropertyValueFactory<>("floor"));
        floorCol.setPrefWidth(80);
        TableColumn roomCol = new TableColumn("Room");
        roomCol.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        roomCol.setPrefWidth(90);
        roomCol.setSortType(TableColumn.SortType.ASCENDING);
        TableColumn roomTypeCol = new TableColumn("Room Type");
        roomTypeCol.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        roomTypeCol.setPrefWidth(100);
        TableColumn allGuestCol = new TableColumn("Guest List");
        allGuestCol.setCellValueFactory(new PropertyValueFactory<>("guestNameList"));
        allGuestCol.setPrefWidth(475);
        allGuestCol.setSortable(false);

        roomTable.getColumns().addAll(buildingCol, floorCol, roomCol, roomTypeCol, allGuestCol);
        roomTable.getSortOrder().add(roomCol);

    }

    private void showSelectedRoom(Room room) {
        selectedRoom = room;
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
        setting.setPrevPage("PersonnelRoomList");
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
        setting.setPrevPage("PersonnelRoomList");
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
        manageItems.setItems(items);
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

    @FXML public void handleSearchGuestNameBtnOnAction() throws IOException {
        String searchGuestName = guestNameSearchField.getText();
        if(searchGuestName.equals("")){
            showRoomList();
        }else{
            showSearchGuestNameList(searchGuestName);
        }
    }

    @FXML public void handleEditRoomBtnOnAction() throws IOException {

        if (selectedRoom == null) {
            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setResizable(false);
            FXMLLoader loader = new FXMLLoader
                    (getClass().getResource("/notification_popup.fxml"));
            popup.setScene(new Scene(loader.load(), 290, 100));
            NotificationPopupController noti = loader.getController();
            noti.setTextLabel("Please select room");
            popup.showAndWait();
            selectedRoom = null;
        }else{
            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setResizable(false);
            FXMLLoader loader = new FXMLLoader
                    (getClass().getResource("/room_info_popup.fxml"));
            popup.setScene(new Scene(loader.load(), 650, 450));
            RoomInfoPopupController roomPop = loader.getController();
            roomPop.setSelectRoom(selectedRoom);
            roomPop.setRooms(rooms);
            popup.showAndWait();
            showRoomList();
            selectedRoom = null;
        }

    }

}
