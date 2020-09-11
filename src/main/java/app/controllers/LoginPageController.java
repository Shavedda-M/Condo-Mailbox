package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageController {
    @FXML Button loginBtn, registerBtn;
    @FXML TextField idField;
    @FXML PasswordField passwordField;

    @FXML private void initialize(){
    }

    @FXML public void handleLoginBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        if(idField.getText().equals("1")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/admin_home_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 800, 600));
            stage.show();
        }else if(idField.getText().equals("2")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/personnel_home_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 800, 600));
            stage.show();
        }else if(idField.getText().equals("3")){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/guest_home_page.fxml")
            );
            stage.setScene(new Scene(loader.load(), 800, 600));
            stage.show();
        }
    }

    @FXML public void handleRegisterBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/register_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }
}
