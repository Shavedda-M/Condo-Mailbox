package app.controllers.popup;

import app.controllers.personnel.PersonnelRoomListPageController;
import app.controllers.setting.SettingPageController;
import app.models.Room;
import app.models.RoomList;
import app.services.ReadWriteFile;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class RoomInfoPopupController {
    @FXML Label buildingLabel, floorLabel, roomNumberLabel, roomTypeLabel, errorLabel, guest1Error, guest2Error;;
    @FXML Button applyChangeBtn, deleteRoomBtn, closeBtn;
    @FXML TextField guest1Field, guest2Field;
    @FXML ImageView deleteGuest1Btn, deleteGuest2Btn;

    private RoomList rooms;
    private Room selectRoom;
    private ReadWriteFile dataSource;

    @FXML private void initialize(){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                dataSource = new ReadWriteFile("data", "rooms.csv");
                setAllErrorDisable();
                showRoomInfo();
            }
        });
    }

    public void setRooms(RoomList rooms) {
        this.rooms = rooms;
    }

    public void setSelectRoom(Room selectRoom) {
        this.selectRoom = selectRoom;
    }

    public void setAllErrorDisable(){
        errorLabel.setVisible(false);
        guest1Error.setVisible(false);
        guest2Error.setVisible(false);
    }

    public void showRoomInfo(){
        buildingLabel.setText(selectRoom.getBuilding());
        floorLabel.setText(selectRoom.getFloor());
        roomNumberLabel.setText(selectRoom.getRoomNumber());
        roomTypeLabel.setText(selectRoom.getRoomType());
        showGuestName();
    }

    public void showGuestName(){
        deleteGuest1Btn.setVisible(false);
        deleteGuest2Btn.setVisible(false);
        guest1Field.setText("");
        guest2Field.setText("");
        if(selectRoom.getRoomType().equals("Single")){
            guest1Field.setDisable(false);
            guest2Field.setDisable(true);
            if(selectRoom.getGuestNameList().size() == 0){
                guest1Field.setDisable(true);
                applyChangeBtn.setDisable(true);
            }else{
                guest1Field.setText(selectRoom.getGuestNameList().get(0));
                deleteGuest1Btn.setVisible(true);
            }
        }else if(selectRoom.getRoomType().equals("Double")){
            guest1Field.setDisable(false);
            guest2Field.setDisable(false);
            if(selectRoom.getGuestNameList().size() == 0){
                guest1Field.setDisable(true);
                guest2Field.setDisable(true);
                applyChangeBtn.setDisable(true);
            }else if(selectRoom.getGuestNameList().size() == 1) {
                guest1Field.setText(selectRoom.getGuestNameList().get(0));
                deleteGuest1Btn.setVisible(true);
                guest2Field.setDisable(true);
            } else{
                guest1Field.setText(selectRoom.getGuestNameList().get(0));
                guest2Field.setText(selectRoom.getGuestNameList().get(1));
                deleteGuest1Btn.setVisible(true);
                deleteGuest2Btn.setVisible(true);
            }
        }
    }

    @FXML public void handleApplyChangeBtnOnAction(){
        setAllErrorDisable();
        if(selectRoom.getRoomType().equals("Single")){
            if(selectRoom.getGuestNameList().size() == 1){
                if(guest1Field.getText().equals("")){
                    errorLabel.setVisible(true);
                    errorLabel.setText("* Required");
                    guest1Error.setVisible(true);
                }else{
                    selectRoom.changeGuestNameList(guest1Field.getText());
                    dataSource.setRoomData(rooms);
                    showGuestName();
                }
            }
        }else if(selectRoom.getRoomType().equals("Double")) {
            if (selectRoom.getGuestNameList().size() == 1) {
                if (guest1Field.getText().equals("")) {
                    errorLabel.setVisible(true);
                    errorLabel.setText("* Required");
                    guest1Error.setVisible(true);
                } else {
                    selectRoom.changeGuestNameList(guest1Field.getText());
                    dataSource.setRoomData(rooms);
                    showGuestName();
                }
            } else if (selectRoom.getGuestNameList().size() == 2) {
                if (!guest1Field.getText().equals("") && !guest2Field.getText().equals("")) {
                    selectRoom.changeGuestNameList(guest1Field.getText(), guest2Field.getText());
                    dataSource.setRoomData(rooms);
                    showGuestName();
                } else {
                    errorLabel.setVisible(true);
                    errorLabel.setText("* Required");
                    if (guest1Field.getText().equals("")) {
                        guest1Error.setVisible(true);
                    } else {
                        guest1Error.setVisible(false);
                    }
                    if (guest2Field.getText().equals("")) {
                        guest2Error.setVisible(true);
                    } else {
                        guest2Error.setVisible(false);
                    }
                }
            }
        }
    }

    @FXML public void handleDeleteGuest1BtnOnAction() throws IOException {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setResizable(false);
        FXMLLoader loader = new FXMLLoader
                (getClass().getResource("/confirm_action_popup.fxml"));
        popup.setScene(new Scene(loader.load(), 290, 100));
        ConfirmActionPopupController confirmAction = loader.getController();
        popup.showAndWait();
        String action = confirmAction.getAction();
        if(action.equals("confirm")){
            selectRoom.getGuestNameList().remove(0);
            dataSource.setRoomData(rooms);
        }
        showGuestName();
    }

    @FXML public void handleDeleteGuest2BtnOnAction() throws IOException {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setResizable(false);
        FXMLLoader loader = new FXMLLoader
                (getClass().getResource("/confirm_action_popup.fxml"));
        popup.setScene(new Scene(loader.load(), 290, 100));
        ConfirmActionPopupController confirmAction = loader.getController();
        popup.showAndWait();
        String action = confirmAction.getAction();
        if(action.equals("confirm")){
            selectRoom.getGuestNameList().remove(1);
            dataSource.setRoomData(rooms);
        }
        showGuestName();

    }

    @FXML public void handleDeleteRoomBtnOnAction(){
        rooms.toList().remove(selectRoom);
        dataSource.setRoomData(rooms);
        Stage stage = (Stage) deleteRoomBtn.getScene().getWindow();
        stage.close();
    }

    @FXML public void handleCloseBtnOnAction(){
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
}
