package app.controllers.admin;

import app.controllers.login.LoginPageController;
import app.controllers.popup.ConfirmActionPopupController;
import app.controllers.popup.NotificationPopupController;
import app.controllers.setting.SettingPageController;
import app.models.*;
import app.services.ImageService;
import app.services.ReadWriteFile;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminPersonnelListPageController {
    @FXML private Button addPersonnelBtn, accountSettingBtn, logoutBtn, changeStatusBtn, deleteAccountBtn;
    @FXML private Label userNameLabel, perNameLabel, perUsernameLabel, perPasswordLabel, perStatusLabel, perTryLoginLabel;
    @FXML private ImageView perImageView;
    @FXML private TableView<Personnel> personnelTable;

    private AccountList accounts;
    private RoomList rooms;
    private ItemList items;
    private Personnel selectedPersonnel;
    private ReadWriteFile dataSource;
    private ImageService imageService;
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private ObservableList<Personnel> personnelObservableList;


    @FXML private void initialize(){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                userNameLabel.setText(accounts.getCurrentAccount().getName());
                dataSource = new ReadWriteFile("data", "accounts.csv");
                imageService = new ImageService();
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

    public void setItems(ItemList items){
        this.items = items;
    }

    private void showPersonnelList(){
        personnelObservableList = FXCollections.observableArrayList(accounts.getPersonnelAccountList());
        personnelTable.setItems(personnelObservableList);

        TableColumn lastLoginCol = new TableColumn("Last Login");
        lastLoginCol.setCellValueFactory(new PropertyValueFactory<>("lastLoginTime"));
        lastLoginCol.setCellFactory(tc -> new TableCell<Personnel, Date>() {
            @Override
            protected void updateItem(Date date, boolean empty) {
                super.updateItem(date, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(formatter.format(date));
                }
            }
        });
        lastLoginCol.setPrefWidth(145);
        lastLoginCol.setSortType(TableColumn.SortType.DESCENDING);
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(103);
        TableColumn userNameCol = new TableColumn("Username");
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        userNameCol.setPrefWidth(103);
        TableColumn statuscol = new TableColumn("Status");
        statuscol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statuscol.setPrefWidth(75);

        personnelTable.getColumns().addAll(lastLoginCol, nameCol, userNameCol, statuscol);
        personnelTable.getSortOrder().add(lastLoginCol);
    }

    private void showSelectedPersonnel(Personnel personnel) {
        selectedPersonnel = personnel;
        perImageView.setImage(new Image(imageService.getImagePath("classes/Image", personnel.getImageFileName())));
        perNameLabel.setText(personnel.getName());
        perUsernameLabel.setText(personnel.getUserName());
        perPasswordLabel.setText(personnel.getPassword());
        perStatusLabel.setText(personnel.getStatus());
        perTryLoginLabel.setText(Integer.toString(personnel.getTryLogin()));
        changeStatusBtn.setDisable(false);
        deleteAccountBtn.setDisable(false);
    }

    private void clearSelectedPersonnel() {
        perImageView.setImage(new Image(imageService.getImagePath("classes/Image", "default profile.jpg")));
        selectedPersonnel = null;
        perNameLabel.setText("...");
        perUsernameLabel.setText("...");
        perPasswordLabel.setText("...");
        perStatusLabel.setText("...");
        perTryLoginLabel.setText("...");
        changeStatusBtn.setDisable(true);
        deleteAccountBtn.setDisable(true);
    }

    @FXML public void handleChangeStatusBtnOnAction(ActionEvent event) throws IOException {
        selectedPersonnel.changeStatus();
        perStatusLabel.setText(selectedPersonnel.getStatus());
        personnelTable.refresh();
        personnelTable.getSelectionModel().clearSelection();

        dataSource.setAccountsData(accounts);

        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setResizable(false);
        FXMLLoader loader = new FXMLLoader
                (getClass().getResource("/notification_popup.fxml"));
        popup.setScene(new Scene(loader.load(), 290, 100));
        NotificationPopupController noti = loader.getController();
        noti.setTextLabel("Change Complete");
        popup.showAndWait();
    }

    @FXML public void handleDeleteAccountBtnOnAction(ActionEvent event) throws IOException {
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
            accounts.toList().remove(selectedPersonnel);
            clearSelectedPersonnel();
            showPersonnelList();

            dataSource.setAccountsData(accounts);
        }
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
        setting.setItems(items);
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
        setting.setItems(items);
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
        stage.show();
    }
}
