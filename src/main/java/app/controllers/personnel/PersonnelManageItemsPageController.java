package app.controllers.personnel;

import app.controllers.login.LoginPageController;
import app.controllers.popup.ItemInfoPopupController;
import app.controllers.setting.SettingPageController;
import app.models.*;
import app.services.ImageService;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PersonnelManageItemsPageController {

    @FXML private Button accountSettingBtn, logoutBtn, addItemBtn, checkInfoBtn, changeTableBtn, receiveBtn;
    @FXML private Label userNameLabel, senderLabel, typeLabel, sizeLabel, dateReceivedLabel;
    @FXML private TextField roomSearchField;
    @FXML private TableView<Item> itemTable;
    @FXML private ImageView itemImageView;

    private AccountList accounts;
    private RoomList rooms;
    private ItemList items;
    private ReadWriteFile dataSource;
    private ImageService imageService;
    private Item selectedItem;
    private String currentTableShow;
    private ArrayList<Item> showItem;
    private ObservableList<Item> itemObservableList;
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @FXML private void initialize(){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                userNameLabel.setText(accounts.getCurrentAccount().getName());
                imageService = new ImageService();
                dataSource = new ReadWriteFile("data", "items.csv");
                currentTableShow = "Not Received";
                checkInfoBtn.setDisable(true);
                receiveBtn.setDisable(true);
                itemTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        showSelectedItem(newValue);
                    }
                });
                showItemList();
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

    private void showItemList(){
        itemTable.getColumns().clear();
        if(currentTableShow.equals("Not Received")){
            showItem = items.getNotReceivedItemList();
        }else if(currentTableShow.equals("Received")){
            showItem = items.getReceivedItemList();
        }

        itemObservableList = FXCollections.observableArrayList(showItem);
        itemTable.setItems(itemObservableList);

        TableColumn dateReceivedCol = new TableColumn("Date Received");
        dateReceivedCol.setCellValueFactory(new PropertyValueFactory<>("dateReceived"));
        dateReceivedCol.setCellFactory(tc -> new TableCell<Item, Date>() {
            @Override
            protected void updateItem(Date date, boolean empty) {
                super.updateItem(date, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(formatter.format(date));
                }
            }
        });
        dateReceivedCol.setPrefWidth(145);
        dateReceivedCol.setSortType(TableColumn.SortType.DESCENDING);
        TableColumn recipientCol = new TableColumn("Recipient");
        recipientCol.setCellValueFactory(new PropertyValueFactory<>("recipient"));
        recipientCol.setPrefWidth(110);
        TableColumn roomCol = new TableColumn("Room");
        roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
        roomCol.setPrefWidth(50);
        TableColumn typeCol = new TableColumn("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        typeCol.setPrefWidth(76);
        TableColumn statusCol = new TableColumn("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(105);
        statusCol.setSortable(false);

        TableColumn pickupDateCol = new TableColumn("Pickup Date");
        pickupDateCol.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        pickupDateCol.setCellFactory(tc -> new TableCell<Item, Date>() {
            @Override
            protected void updateItem(Date date, boolean empty) {
                super.updateItem(date, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(formatter.format(date));
                }
            }
        });
        pickupDateCol.setPrefWidth(145);
        pickupDateCol.setSortType(TableColumn.SortType.DESCENDING);

        if(currentTableShow.equals("Not Received")){
            itemTable.getColumns().addAll(dateReceivedCol, recipientCol, roomCol, typeCol, statusCol);
            itemTable.getSortOrder().add(dateReceivedCol);
        }else if(currentTableShow.equals("Received")){
            itemTable.getColumns().addAll(pickupDateCol, recipientCol, roomCol, typeCol, statusCol, dateReceivedCol);
            itemTable.getSortOrder().add(pickupDateCol);
        }

    }

    private void showSearchItemList(String roomNumber){
        itemTable.getColumns().clear();

        if(currentTableShow.equals("Not Received")){
            showItem = items.getNotReceivedSearchItemList(roomNumber);
        }else if(currentTableShow.equals("Received")){
            showItem = items.getReceivedSearchItemList(roomNumber);
        }

        itemObservableList = FXCollections.observableArrayList(showItem);
        itemTable.setItems(itemObservableList);

        TableColumn dateReceivedCol = new TableColumn("Date Received");
        dateReceivedCol.setCellValueFactory(new PropertyValueFactory<>("dateReceived"));
        dateReceivedCol.setCellFactory(tc -> new TableCell<Item, Date>() {
            @Override
            protected void updateItem(Date date, boolean empty) {
                super.updateItem(date, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(formatter.format(date));
                }
            }
        });
        dateReceivedCol.setPrefWidth(145);
        dateReceivedCol.setSortType(TableColumn.SortType.DESCENDING);
        TableColumn recipientCol = new TableColumn("Recipient");
        recipientCol.setCellValueFactory(new PropertyValueFactory<>("recipient"));
        recipientCol.setPrefWidth(110);
        TableColumn roomCol = new TableColumn("Room");
        roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
        roomCol.setPrefWidth(50);
        TableColumn typeCol = new TableColumn("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        typeCol.setPrefWidth(76);
        TableColumn statusCol = new TableColumn("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(105);
        statusCol.setSortable(false);
        TableColumn pickupDateCol = new TableColumn("Pickup Date");
        pickupDateCol.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        pickupDateCol.setCellFactory(tc -> new TableCell<Item, Date>() {
            @Override
            protected void updateItem(Date date, boolean empty) {
                super.updateItem(date, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(formatter.format(date));
                }
            }
        });
        pickupDateCol.setPrefWidth(145);

        if(currentTableShow.equals("Not Received")){
            itemTable.getColumns().addAll(dateReceivedCol, recipientCol, roomCol, typeCol, statusCol);
        }else if(currentTableShow.equals("Received")){
            itemTable.getColumns().addAll(dateReceivedCol, recipientCol, roomCol, typeCol, statusCol, pickupDateCol);
        }
        itemTable.getSortOrder().add(dateReceivedCol);

    }

    private void showSelectedItem(Item item) {
        selectedItem = item;
        itemImageView.setImage(new Image(imageService.getImagePath("classes/Image", item.getImageFileName())));
        senderLabel.setText(item.getSenderName());
        typeLabel.setText(item.getItemType());
        sizeLabel.setText(item.getSize());
        dateReceivedLabel.setText(formatter.format(item.getDateReceived()));
        checkInfoBtn.setDisable(false);
        if(currentTableShow.equals("Received")){
            receiveBtn.setDisable(true);
        }else{
            receiveBtn.setDisable(false);
        }
    }

    private void clearSelectedItem() {
        selectedItem = null;
        itemImageView.setImage(new Image(imageService.getImagePath("classes/Image", "mail default.png")));
        senderLabel.setText("...");
        typeLabel.setText("...");
        sizeLabel.setText("...");
        dateReceivedLabel.setText("...");
        checkInfoBtn.setDisable(true);
        receiveBtn.setDisable(true);
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
        setting.setPrevPage("PersonnelManageItems");
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
        setting.setPrevPage("PersonnelManageItems");
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

    @FXML public void handleAddItemBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/personnel_select_add_item_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        PersonnelSelectAddItemPageController addItem = loader.getController();
        addItem.setAccounts(accounts);
        addItem.setRooms(rooms);
        addItem.setItems(items);
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

    @FXML public void handleCheckInfoBtnOnAction() throws IOException {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setResizable(false);
        FXMLLoader loader = new FXMLLoader
                (getClass().getResource("/item_info_popup.fxml"));
        popup.setScene(new Scene(loader.load(), 775, 550));
        ItemInfoPopupController itemPop = loader.getController();
        itemPop.setSelectedItem(selectedItem);
        popup.showAndWait();
    }

    @FXML public void handleChangeTableBtnOnAction(){
        String searchRoom = roomSearchField.getText();
        if(currentTableShow.equals("Not Received")){
            currentTableShow = "Received";
        }else if(currentTableShow.equals("Received")){
            currentTableShow = "Not Received";
        }
        if(searchRoom.equals("")){
            clearSelectedItem();
            showItemList();
        }else{
            clearSelectedItem();
            showSearchItemList(searchRoom);
        }

    }

    @FXML public void searchItemBtnOnAction(){
        String searchRoom = roomSearchField.getText();
        if(searchRoom.equals("")){
            clearSelectedItem();
            showItemList();
        }else{
            clearSelectedItem();
            showSearchItemList(searchRoom);
        }
    }

    @FXML public void handleRecieveBtnOnAction(ActionEvent event){
        selectedItem.changeStatus();
        selectedItem.setPickupDate(new Date());
        clearSelectedItem();
        showItemList();

        dataSource.setItemData(items);
    }
}
