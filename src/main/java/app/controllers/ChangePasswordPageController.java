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

public class ChangePasswordPageController {
    @FXML Button profileSettingBtn, logoutBtn, backBtn, changePasswordBtn;
    @FXML TextField currentPasswordField, newPasswordField, confirmNewPasswordField;
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

    @FXML public void handleProfileSettingBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/profile_setting_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        ProfileSettingPageController profileSet = loader.getController();
        stage.show();
    }

    @FXML public void handleChangePasswordBtnOnAction(ActionEvent event){

    }

    @FXML public void handleBackBtnOnAction(ActionEvent event){

    }
}
