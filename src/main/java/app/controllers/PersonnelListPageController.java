package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PersonnelListPageController {
    @FXML Button addPersonnelBtn, accountSettingBtn, logoutBtn;
    @FXML Label userNameLabel;


    @FXML public void handleAccountSettingBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/setting_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        SettingPageController setting = loader.getController();
        stage.show();
    }

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

    @FXML public void handleAddPersonnelBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/add_personnel_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        AddPersonnelPageController addPer = loader.getController();
        stage.show();
    }
}
