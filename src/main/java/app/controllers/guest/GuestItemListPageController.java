package app.controllers.guest;

import app.controllers.login.LoginPageController;
import app.controllers.popup.ItemInfoPopupController;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class GuestItemListPageController {
    @FXML private Button accountSettingBtn, logoutBtn, checkInfoBtn, receiveBtn;
    @FXML private Label userNameLabel, senderLabel, typeLabel, sizeLabel, dateReceivedLabel;
    @FXML private TableView<Item> itemTable;
    @FXML private ImageView itemImageView;

    private AccountList accounts;
    private RoomList rooms;
    private ItemList items;
    private ReadWriteFile dataSource;
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

        Guest guest = (Guest)accounts.getCurrentAccount();
        if(currentTableShow.equals("Not Received")){
            showItem = items.getGuestNotReceivedItemList(guest);
        }else if(currentTableShow.equals("Received")){
            showItem = items.getGuestReceivedItemList(guest);
        }
        itemObservableList = FXCollections.observableArrayList(showItem);
        itemTable.setItems(itemObservableList);

        TableColumn dateReceivedCol = new TableColumn("DateReceived");
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
        TableColumn senderCol = new TableColumn("Sender");
        senderCol.setCellValueFactory(new PropertyValueFactory<>("senderName"));
        senderCol.setPrefWidth(110);
        TableColumn typeCol = new TableColumn("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        typeCol.setPrefWidth(76);
        TableColumn sizeCol = new TableColumn("Status");
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        sizeCol.setSortable(false);
        sizeCol.setPrefWidth(105);
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
            itemTable.getColumns().addAll(dateReceivedCol, senderCol, typeCol, sizeCol);
        }else if(currentTableShow.equals("Received")){
            itemTable.getColumns().addAll(dateReceivedCol, senderCol, typeCol, sizeCol, pickupDateCol);
        }
    }

    private void showSelectedItem(Item item) {
        selectedItem = item;
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
        setting.setPrevPage("GuestItemList");
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
        setting.setPrevPage("GuestItemList");
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

    @FXML public void handleCheckInfoBtnOnAction(ActionEvent event) throws IOException {
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

    @FXML public void handleRecieveBtnOnAction(ActionEvent event){
        selectedItem.changeStatus();
        selectedItem.setPickupDate(new Date());
        clearSelectedItem();
        showItemList();

        dataSource.setItemData(items);
    }

    @FXML public void handleChangeTableBtnOnAction(ActionEvent event){
        if(currentTableShow.equals("Not Received")){
            currentTableShow = "Received";
        }else if(currentTableShow.equals("Received")){
            currentTableShow = "Not Received";
        }
        clearSelectedItem();
        showItemList();
    }

}

