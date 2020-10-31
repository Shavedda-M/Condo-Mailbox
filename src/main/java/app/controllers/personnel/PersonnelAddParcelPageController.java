package app.controllers.personnel;

import app.controllers.login.LoginPageController;
import app.controllers.popup.NotificationPopupController;
import app.controllers.setting.SettingPageController;
import app.models.*;
import app.services.BrowseImage;
import app.services.ImageService;
import app.services.ReadWriteFile;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PersonnelAddParcelPageController {

    ObservableList<String> buildingList = FXCollections.observableArrayList("1", "2");

    @FXML Button roomListBtn, addGuestBtn, addItemBtn, backBtn, addRoomBtn, browseImageBtn;
    @FXML Label userNameLabel, errorLabel, recipientError, senderError, sizeError, serviceNameError, trackingNumberError, buildingError, roomNumberError;
    @FXML TextField recipientField, senderField, sizeField, trackingNumberField, roomField, serviceNameField;
    @FXML ChoiceBox buildingChoiceBox;
    @FXML ImageView itemImageView;

    private AccountList accounts;
    private RoomList rooms;
    private ItemList items;
    private ReadWriteFile dataSource;
    private ImageService imageService;
    private File currentImageFile;
    private Image defaultImage;

    @FXML private void initialize(){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                userNameLabel.setText(accounts.getCurrentAccount().getName());
                dataSource = new ReadWriteFile("data", "items.csv");
                currentImageFile = new File("classes/Image" + File.separator + "parcel default.png");
                imageService = new ImageService();
                defaultImage = itemImageView.getImage();
                setAllErrorDisable();
            }
        });
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
        recipientError.setVisible(false);
        senderError.setVisible(false);
        sizeError.setVisible(false);
        serviceNameError.setVisible(false);
        trackingNumberError.setVisible(false);
        buildingError.setVisible(false);
        roomNumberError.setVisible(false);
    }

    public void resetField(){
        recipientField.setText("");
        senderField.setText("");
        sizeField.setText("");
        roomField.setText("");
        buildingChoiceBox.setValue(null);
        roomField.setText("");;
        serviceNameField.setText("");;
        itemImageView.setImage(defaultImage);
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
        setting.setPrevPage("PersonnelAddPackage");
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
        setting.setPrevPage("PersonnelAddPackage");
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

    @FXML public void handleAddItemBtnOnAction(ActionEvent event) throws IOException {
        setAllErrorDisable();
        if(!recipientField.getText().equals("") && !senderField.getText().equals("") && !sizeField.getText().equals("") && !roomField.getText().equals("") && !(buildingChoiceBox.getValue() == null) && !serviceNameField.getText().equals("") && !trackingNumberField.getText().equals("")){
            if(rooms.checkRoom(recipientField.getText(), buildingChoiceBox.getValue().toString(), roomField.getText())){
                Item item = new Parcel("parcel", recipientField.getText(), rooms.findRoom(recipientField.getText()
                                        , buildingChoiceBox.getValue().toString(), roomField.getText()), senderField.getText()
                                        , sizeField.getText(), accounts.getCurrentAccount().getName(), new Date(), serviceNameField.getText()
                                        , trackingNumberField.getText(), imageService.copyImage("classes/Image", currentImageFile, "parcel default.png"));
                items.addItem(item);
                dataSource.setItemData(items);

                Stage popup = new Stage();
                popup.initModality(Modality.APPLICATION_MODAL);
                popup.setResizable(false);
                FXMLLoader loader = new FXMLLoader
                        (getClass().getResource("/notification_popup.fxml"));
                popup.setScene(new Scene(loader.load(), 290, 100));
                NotificationPopupController noti = loader.getController();
                noti.setTextLabel("Add Success");
                popup.showAndWait();
                resetField();
            }else{
                errorLabel.setVisible(true);
                errorLabel.setText("* Invalid room number");
                recipientError.setVisible(true);
                buildingError.setVisible(true);
                roomNumberError.setVisible(true);
            }
        }else{
            errorLabel.setVisible(true);
            errorLabel.setText("* Required");
            if(recipientField.getText().equals("")){
                recipientError.setVisible(true);
            }else{
                recipientError.setVisible(false);
            }
            if(senderField.getText().equals("")){
                senderError.setVisible(true);
            }else{
                senderError.setVisible(false);
            }
            if(sizeField.getText().equals("")){
                sizeError.setVisible(true);
            }else{
                sizeError.setVisible(false);
            }
            if(serviceNameField.getText().equals("")){
                serviceNameError.setVisible(true);
            }else{
                serviceNameError.setVisible(false);
            }
            if(trackingNumberField.getText().equals("")){
                trackingNumberError.setVisible(true);
            }else{
                trackingNumberError.setVisible(false);
            }
            if(buildingChoiceBox.getValue() == null){
                buildingError.setVisible(true);
            }else{
                buildingError.setVisible(false);
            }
            if(roomField.getText().equals("")){
                roomNumberError.setVisible(true);
            }else{
                roomNumberError.setVisible(false);
            }
        }
    }

    @FXML public void handleBrowseImageBtnOnAction(ActionEvent event) throws URISyntaxException {

        BrowseImage browseImage = new BrowseImage();

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser;

        fileChooser = browseImage.Browse();

        File file = fileChooser.showOpenDialog(stage);
        try{
            currentImageFile = file;
            Image image = new Image(file.toURI().toString());
            itemImageView.setImage(image);
            itemImageView.setPreserveRatio(false);
            itemImageView.setFitHeight(130);
            itemImageView.setFitWidth(150);
        }catch (Exception ex){

        }
    }
}
