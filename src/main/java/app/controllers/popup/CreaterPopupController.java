package app.controllers.popup;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CreaterPopupController {
    @FXML Button closeBtn;

    @FXML public void handleCloseBtnOnAction(){
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
}
