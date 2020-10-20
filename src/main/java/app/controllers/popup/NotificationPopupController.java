package app.controllers.popup;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.Stage;

public class NotificationPopupController {

    @FXML Label textLabel;
    @FXML Button closeBtn;

    public void setTextLabel(String text){
        textLabel.setText(text);
    }

    @FXML public void handleCloseBtnOnAction(){
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
}
