package app.controllers.login;

import app.controllers.popup.NotificationPopupController;
import app.exceptions.UsernameNotAvailableException;
import app.models.*;
import app.services.BrowseImage;
import app.services.ReadWriteFile;
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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class RegisterPageController {
    @FXML private TextField userNameField, passwordField, passwordConfirmField, nameField, roomNumberField;
    @FXML private Label errorLabel, usernameError, passwordError, passwordConfirmError, nameError, buildingError, roomNumberError;
    @FXML private Button cancelBtn, confirmBtn;
    @FXML private ChoiceBox buildingChoiceBox;
    @FXML private ImageView profileImageView;

    private ObservableList<String> buildingList = FXCollections.observableArrayList("1", "2");
    private AccountList accounts;
    private RoomList rooms;
    private ReadWriteFile dataSource;


    @FXML private void initialize(){
        dataSource = new ReadWriteFile("data", "accounts.csv");
        buildingChoiceBox.setItems(buildingList);
        setAllErrorDisable();
    }

    public void setAccounts(AccountList accounts){
        this.accounts = accounts;
    }

    public void setRooms(RoomList rooms){
        this.rooms = rooms;
    }

    public void clearAllField(){
        userNameField.setText("");
        passwordField.setText("");
        passwordConfirmField.setText("");
        nameField.setText("");
        buildingChoiceBox.setValue(null);
        roomNumberField.setText("");
    }

    public void setAllErrorDisable(){
        errorLabel.setVisible(false);
        usernameError.setVisible(false);
        passwordError.setVisible(false);
        passwordConfirmError.setVisible(false);
        nameError.setVisible(false);
        buildingError.setVisible(false);
        roomNumberError.setVisible(false);
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
        setAllErrorDisable();
        if(!userNameField.getText().equals("") && !passwordField.getText().equals("") && !passwordConfirmField.getText().equals("") && !(buildingChoiceBox.getValue() == null) && !nameField.getText().equals("") && !roomNumberField.getText().equals("")) {
            try {
                if (accounts.checkUsernameAvaliable(userNameField.getText())) {
                    if (passwordField.getText().equals(passwordConfirmField.getText())) {
                        if (rooms.checkRoom(nameField.getText(), buildingChoiceBox.getValue().toString(), roomNumberField.getText())) {
                            Account ac = new Guest(nameField.getText(), userNameField.getText(), passwordField.getText(), rooms.findRoom(nameField.getText(), buildingChoiceBox.getValue().toString(), roomNumberField.getText()));
                            accounts.addAccount(ac);
                            dataSource.setAccountsData(accounts);

                            Stage popup = new Stage();
                            popup.initModality(Modality.APPLICATION_MODAL);
                            popup.setResizable(false);
                            FXMLLoader loader = new FXMLLoader
                                    (getClass().getResource("/notification_popup.fxml"));
                            popup.setScene(new Scene(loader.load(), 290, 100));
                            NotificationPopupController noti = loader.getController();
                            noti.setTextLabel("Create Successful");
                            popup.showAndWait();
                            clearAllField();
                        } else {
                            errorLabel.setVisible(true);
                            errorLabel.setText("* Can't find room. Please check your info");
                            nameError.setVisible(true);
                            buildingError.setVisible(true);
                            roomNumberError.setVisible(true);
                        }
                    }else{
                        errorLabel.setVisible(true);
                        errorLabel.setText("* Please enter the same password");
                        passwordError.setVisible(true);
                        passwordConfirmError.setVisible(true);
                    }
                }
            } catch (UsernameNotAvailableException e) {
                errorLabel.setVisible(true);
                errorLabel.setText("* " + e.getMessage());
                usernameError.setVisible(true);
            }
        }else{
            errorLabel.setVisible(true);
            errorLabel.setText("* Required");
            if(userNameField.getText().equals("")){
                usernameError.setVisible(true);
            }else{
                usernameError.setVisible(false);
            }
            if(passwordField.getText().equals("")){
                passwordError.setVisible(true);
            }else{
                passwordError.setVisible(false);
            }
            if(passwordConfirmField.getText().equals("")){
                passwordConfirmError.setVisible(true);
            }else{
                passwordConfirmError.setVisible(false);
            }
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


    @FXML public void handleBrowseImageBtnOnAction(ActionEvent event){

        BrowseImage browseImage = new BrowseImage();

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser;

        fileChooser = browseImage.Browse();

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


