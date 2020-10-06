package app.controllers;

import app.models.Account;
import app.models.AccountList;
import app.models.Guest;
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
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class RegisterPageController {
    @FXML TextField userNameField, passwordField, passwordConfirmField, nameField, roomNumberField;
    @FXML Button cancelBtn, confirmBtn;
    @FXML ChoiceBox buildingChoiceBox;
    @FXML ImageView profileImageView;

    ObservableList<String> buildingList = FXCollections.observableArrayList("1", "2", "3");

    private AccountList accounts;
    private RoomList rooms;
    private ReadWriteFile dataSource;


    @FXML private void initialize(){
        buildingChoiceBox.setItems(buildingList);
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

    @FXML public void handleCancelBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/login_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        stage.show();
    }

    @FXML public void handleConfirmBtnOnAction(ActionEvent event) throws IOException {
        if(!userNameField.getText().equals("") && !passwordField.getText().equals("") && !passwordConfirmField.getText().equals("") && !nameField.getText().equals("") && !buildingChoiceBox.getValue().toString().equals("") && !roomNumberField.getText().equals("")){
            if(passwordField.getText().equals(passwordConfirmField.getText()) && rooms.checkRoom(nameField.getText(), buildingChoiceBox.getValue().toString(), roomNumberField.getText())){
                Account ac = new Guest(nameField.getText(), userNameField.getText(), passwordField.getText(), rooms.findRoom(nameField.getText(), buildingChoiceBox.getValue().toString(), roomNumberField.getText()));
                accounts.addAccount(ac);
                dataSource.setAccountsData(accounts);

                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/login_page.fxml")
                );
                stage.setScene(new Scene(loader.load(), 1024, 768));
                stage.show();

                System.out.println("Create Successful");
            }else{
                System.out.println("Create Unsuccessful");
            }
        }
        System.out.println("Please check your info");
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


