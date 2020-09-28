package app.controllers;

import app.models.AccountList;
import app.services.BrowseImage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SettingProfilePageController {
    @FXML
    Button logoutBtn, backBtn, changePasswordBtn, confirmBtn;
    @FXML TextField newNameField;
    @FXML Label userNameLabel;
    @FXML ImageView profileImageView;

    private AccountList accounts;
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

    public void setPrevPage(String prevPage){
        this.prevPage = prevPage;
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

    @FXML public void handleChangePasswordBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/setting_change_password_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        SettingChangePasswordPageController changePass = loader.getController();
        changePass.setAccounts(accounts);
        changePass.setPrevPage(prevPage);
        stage.show();
    }

    @FXML public void handleConfirmBtnOnAction(ActionEvent event){

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
        }else if(prevPage.equals("AdminHomePage")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/admin_home_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            AdminHomePageController adminPage =loader.getController();
            adminPage.setAccounts(accounts);
        }else if(prevPage.equals("AdminPersonnelList")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/admin_personnel_list_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            AdminPersonnelListPageController perList = loader.getController();
            perList.setAccounts(accounts);
        }else if(prevPage.equals("GuestHomePage")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/guest_home_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            GuestHomePageController guestPage =loader.getController();
            guestPage.setAccounts(accounts);
        }else if(prevPage.equals("GuestItemList")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/guest_item_list_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            GuestItemListPageController itemList = loader.getController();
            itemList.setAccounts(accounts);;
        }else if(prevPage.equals("PersonnelAddGuest")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/personnel_add_guest_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            PersonnelAddGuestPageController addGuest = loader.getController();
            addGuest.setAccounts(accounts);
        }else if(prevPage.equals("PersonnelAddItem")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/personnel_add_item_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            PersonnelAddItemPageController addItem = loader.getController();
            addItem.setAccounts(accounts);
        }else if(prevPage.equals("PersonnelHomePage")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/personnel_home_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            PersonnelHomePageController personnelPage =loader.getController();
            personnelPage.setAccounts(accounts);
        }else if(prevPage.equals("PersonnelManageItems")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/personnel_manage_items_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            PersonnelManageItemsPageController manageItems = loader.getController();
            manageItems.setAccounts(accounts);
        }else if(prevPage.equals("PersonnelRoomList")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/personnel_room_list_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 1024, 768));
            PersonnelRoomListPageController guestList = loader.getController();
            guestList.setAccounts(accounts);
        }
        stage.show();
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
