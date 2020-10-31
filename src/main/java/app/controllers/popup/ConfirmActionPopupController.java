package app.controllers.popup;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmActionPopupController {

    @FXML Button cancelBtn, confirmBtn;

    private String action = "";

    public String getAction() {
        return action;
    }

    @FXML public void handleConfirmBtnOnAction(){
        this.action = "confirm";
        Stage stage = (Stage) confirmBtn.getScene().getWindow();
        stage.close();
    }

    @FXML public void handleCancelBtnOnAction(){
        this.action = "cancel";
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

}
