package app.controllers;

import app.models.Account;
import app.models.AccountList;
import app.models.Personnel;
import app.models.RoomList;
import app.services.ReadWriteFile;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPersonnelListPageController {
    @FXML Button addPersonnelBtn, accountSettingBtn, logoutBtn, changeStatusBtn, deleteAccountBtn;
    @FXML Label userNameLabel, perNameLabel, perUsernameLabel, perPasswordLabel, perStatusLabel, perTryLoginLabel;
    @FXML ImageView perImageView;
    @FXML TableView<Personnel> personnelTable;

    private AccountList accounts;
    private RoomList rooms;
    private Personnel selectedPersonnel;
    private ObservableList<Personnel> personnelObservableList;
    private ReadWriteFile dataSource;

    @FXML private void initialize(){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                userNameLabel.setText(accounts.getCurrentAccount().getName());
                changeStatusBtn.setDisable(true);
                deleteAccountBtn.setDisable(true);
                personnelTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        showSelectedPersonnel(newValue);
                    }
                });
                showPersonnelList();
            }
        });
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

    private void showPersonnelList(){
        personnelObservableList = FXCollections.observableArrayList();
        for(Account a : accounts.toList()){
            if(a.getAccountType().equals("personnel")){
                Personnel per = (Personnel)a;
                personnelObservableList.add(per);
            }
        }
        personnelTable.setItems(personnelObservableList);

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(165);
        TableColumn userNameCol = new TableColumn("Username");
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        userNameCol.setPrefWidth(165);
        TableColumn statuscol = new TableColumn("Status");
        statuscol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statuscol.setPrefWidth(79);

        personnelTable.getColumns().addAll(nameCol, userNameCol, statuscol);
    }

    private void showSelectedPersonnel(Personnel personnel) {
        selectedPersonnel = personnel;
        perNameLabel.setText(personnel.getName());
        perUsernameLabel.setText(personnel.getUserName());
        perPasswordLabel.setText(personnel.getPassword());
        perStatusLabel.setText(personnel.getStatus());
        perTryLoginLabel.setText(Integer.toString(personnel.getTryLogin()));
        changeStatusBtn.setDisable(false);
        deleteAccountBtn.setDisable(false);
    }

    private void clearSelectedPersonnel() {
        selectedPersonnel = null;
        perNameLabel.setText("...");
        perUsernameLabel.setText("...");
        perPasswordLabel.setText("...");
        perStatusLabel.setText("...");
        perTryLoginLabel.setText("...");
        changeStatusBtn.setDisable(true);
        deleteAccountBtn.setDisable(true);
    }

    @FXML public void handleChangeStatusBtnOnAction(ActionEvent event){
        selectedPersonnel.changeStatus();
        clearSelectedPersonnel();
        personnelTable.refresh();
        personnelTable.getSelectionModel().clearSelection();

        dataSource.setAccountsData(accounts);
    }

    @FXML public void handleDeleteAccountBtnOnAction(ActionEvent event){
        accounts.toList().remove(selectedPersonnel);
        clearSelectedPersonnel();
        showPersonnelList();

        dataSource.setAccountsData(accounts);
    }

    @FXML public void handleAccountSettingImageOnAction(MouseEvent event) throws IOException {
        ImageView b = (ImageView) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/setting_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        SettingPageController setting = loader.getController();
        setting.setAccounts(accounts);
        setting.setRooms(rooms);
        setting.setDataSource(dataSource);
        setting.setPrevPage("AdminPersonnelList");
        stage.show();
    }

    @FXML public void handleLogoutImageOnAction(MouseEvent event) throws IOException {
        ImageView b = (ImageView) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/login_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        LoginPageController login = loader.getController();
        stage.show();
    }

    @FXML public void handleAccountSettingBtnOnAction(MouseEvent event) throws IOException {
        Circle b = (Circle) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/setting_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        SettingPageController setting = loader.getController();
        setting.setAccounts(accounts);
        setting.setRooms(rooms);
        setting.setDataSource(dataSource);
        setting.setPrevPage("AdminPersonnelList");
        stage.show();
    }

    @FXML public void handleLogoutBtnOnAction(MouseEvent event) throws IOException {
        Circle b = (Circle) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/login_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        LoginPageController login = loader.getController();
        stage.show();
    }

    @FXML public void handleAddPersonnelBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/admin_add_personnel_page.fxml")
        );
        stage.setScene(new Scene(loader.load(), 1024, 768));
        AdminAddPersonnelPageController addPer = loader.getController();
        addPer.setAccounts(accounts);
        addPer.setRooms(rooms);
        addPer.setDataSource(dataSource);
        stage.show();
    }
}
