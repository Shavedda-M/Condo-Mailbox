package app.controllers;

import app.models.AccountList;
import app.models.RoomList;
import app.services.ReadWriteFile;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingChangePasswordPageController {
    @FXML Button profileSettingBtn, logoutBtn, backBtn, changePasswordBtn;
    @FXML TextField currentPasswordField, newPasswordField, confirmNewPasswordField;
    @FXML Label userNameLabel;

    private AccountList accounts;
    private RoomList rooms;
    private ReadWriteFile dataSource;
    private String prevPage;

    @FXML private void initialize(){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                userNameLabel.setText(accounts.getCurrentAccount().getName());
            }
        });
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

    public void setPrevPage(String prevPage){
        this.prevPage = prevPage;
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
        setting.setPrevPage(prevPage);
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
        setting.setPrevPage(prevPage);
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

    @FXML public void handleProfileSettingBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/setting_profile_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        SettingProfilePageController profileSet = loader.getController();
        profileSet.setAccounts(accounts);
        profileSet.setRooms(rooms);
        profileSet.setDataSource(dataSource);
        profileSet.setPrevPage(prevPage);
        stage.show();
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        if(prevPage.equals("AdminAddPersonnel")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/admin_add_personnel_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            AdminAddPersonnelPageController addPer = loader.getController();
            addPer.setAccounts(accounts);
            addPer.setRooms(rooms);
            addPer.setDataSource(dataSource);
        }else if(prevPage.equals("AdminHomePage")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/admin_home_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            AdminHomePageController adminPage =loader.getController();
            adminPage.setAccounts(accounts);
            adminPage.setRooms(rooms);
            adminPage.setDataSource(dataSource);
        }else if(prevPage.equals("AdminPersonnelList")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/admin_personnel_list_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            AdminPersonnelListPageController perList = loader.getController();
            perList.setAccounts(accounts);
            perList.setRooms(rooms);
            perList.setDataSource(dataSource);
        }else if(prevPage.equals("GuestHomePage")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/guest_home_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            GuestHomePageController guestPage =loader.getController();
            guestPage.setAccounts(accounts);
            guestPage.setRooms(rooms);
            guestPage.setDataSource(dataSource);
        }else if(prevPage.equals("GuestItemList")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/guest_item_list_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            GuestItemListPageController itemList = loader.getController();
            itemList.setAccounts(accounts);
            itemList.setRooms(rooms);
            itemList.setDataSource(dataSource);
        }else if(prevPage.equals("PersonnelAddGuest")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/personnel_add_guest_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            PersonnelAddGuestPageController addGuest = loader.getController();
            addGuest.setAccounts(accounts);
            addGuest.setRooms(rooms);
            addGuest.setDataSource(dataSource);
        }else if(prevPage.equals("PersonnelSelectAddItem")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/personnel_select_add_item_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            PersonnelSelectAddItemPageController addItem = loader.getController();
            addItem.setAccounts(accounts);
            addItem.setRooms(rooms);
            addItem.setDataSource(dataSource);
        }else if(prevPage.equals("PersonnelHomePage")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/personnel_home_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            PersonnelHomePageController personnelPage =loader.getController();
            personnelPage.setAccounts(accounts);
            personnelPage.setRooms(rooms);
            personnelPage.setDataSource(dataSource);
        }else if(prevPage.equals("PersonnelManageItems")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/personnel_manage_items_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            PersonnelManageItemsPageController manageItems = loader.getController();
            manageItems.setAccounts(accounts);
            manageItems.setRooms(rooms);
            manageItems.setDataSource(dataSource);
        }else if(prevPage.equals("PersonnelRoomList")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/personnel_room_list_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            PersonnelRoomListPageController guestList = loader.getController();
            guestList.setAccounts(accounts);
            guestList.setRooms(rooms);
            guestList.setDataSource(dataSource);
        }else if(prevPage.equals("PersonnelAddMail")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/personnel_add_mail_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            PersonnelAddMailPageController addMail = loader.getController();
            addMail.setAccounts(accounts);
            addMail.setRooms(rooms);
            addMail.setDataSource(dataSource);
        }else if(prevPage.equals("PersonnelAddDocument")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/personnel_add_document_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            PersonnelAddDocumentPageController addDocument = loader.getController();
            addDocument.setAccounts(accounts);
            addDocument.setRooms(rooms);
            addDocument.setDataSource(dataSource);
        }else if(prevPage.equals("PersonnelAddPackage")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/personnel_add_package_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            PersonnelAddPackagePageController addPackage = loader.getController();
            addPackage.setAccounts(accounts);
            addPackage.setRooms(rooms);
            addPackage.setDataSource(dataSource);
        }else if(prevPage.equals("PersonnelAddRoom")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/personnel_add_room_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            PersonnelAddRoomPageController addRoom = loader.getController();
            addRoom.setAccounts(accounts);
            addRoom.setRooms(rooms);
            addRoom.setDataSource(dataSource);
        }
        stage.show();
    }

    @FXML public void handleChangePasswordBtnOnAction(){

    }
}
