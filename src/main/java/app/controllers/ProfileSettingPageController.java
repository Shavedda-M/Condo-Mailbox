package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileSettingPageController {
    @FXML
    Button logoutBtn, backBtn, changePasswordBtn, confirmBtn;
    @FXML TextField newNameField;
    @FXML Label userNameLabel;

    @FXML public void handleLogoutBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/login_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        LoginPageController login = loader.getController();
        stage.show();
    }

    @FXML public void handleChangePasswordBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/change_password_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        ChangePasswordPageController changePass = loader.getController();
        stage.show();
    }

    @FXML public void handleConfirmBtnOnAction(ActionEvent event){

    }

    @FXML public void handleBackBtnOnAction(ActionEvent event){

    }
}
