package app.controllers.popup;

import app.models.Document;
import app.models.Item;
import app.models.Parcel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ItemInfoPopupController {

    @FXML Label senderLabel, recipientLabel, typeLabel, sizeLabel, roomNumberLabel, receivingPersonnelLabel, receivedDateLabel,
                pickupDateLabel, priorityText, serviceNameText, trackingNumberText, priorityLabel, serviceNameLabel, trackingNumberLabel;
    @FXML Button closeBtn;
    @FXML ImageView itemImageView;

    private Item selectedItem;
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @FXML private void initialize(){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                setOptionalInvisible();
                if(selectedItem.getItemType().equals("mail")){
                    showMailInfo();
                }else if(selectedItem.getItemType().equals("document")){
                    showDocumentInfo();
                }else if(selectedItem.getItemType().equals("parcel")){
                    showParcelInfo();
                }
            }
        });

    }

    public void setOptionalInvisible(){
        priorityText.setVisible(false);
        priorityLabel.setVisible(false);
        serviceNameText.setVisible(false);
        serviceNameLabel.setVisible(false);
        trackingNumberText.setVisible(false);
        trackingNumberLabel.setVisible(false);
    }


    public void showMailInfo(){
        senderLabel.setText(selectedItem.getSenderName());
        recipientLabel.setText(selectedItem.getRecipient());
        typeLabel.setText(selectedItem.getItemType());
        sizeLabel.setText(selectedItem.getSize());
        roomNumberLabel.setText(selectedItem.getRoom().getRoomNumber());
        receivingPersonnelLabel.setText(selectedItem.getReceivingPersonnel());
        receivedDateLabel.setText(formatter.format(selectedItem.getDateReceived()));
        if(selectedItem.getPickupDate() == null){
            pickupDateLabel.setText("-");
        }else{
            pickupDateLabel.setText(formatter.format(selectedItem.getPickupDate()));
        }
    }

    public void showDocumentInfo(){
        priorityText.setVisible(true);
        priorityLabel.setVisible(true);
        Document selectedDocument = (Document)selectedItem;
        senderLabel.setText(selectedDocument.getSenderName());
        recipientLabel.setText(selectedDocument.getRecipient());
        typeLabel.setText(selectedDocument.getItemType());
        sizeLabel.setText(selectedDocument.getSize());
        roomNumberLabel.setText(selectedDocument.getRoom().getRoomNumber());
        receivingPersonnelLabel.setText(selectedDocument.getReceivingPersonnel());
        receivedDateLabel.setText(formatter.format(selectedDocument.getDateReceived()));
        if(selectedItem.getPickupDate() == null){
            pickupDateLabel.setText("-");
        }else{
            pickupDateLabel.setText(formatter.format(selectedDocument.getPickupDate()));
        }
        priorityText.setText("Priority :");
        priorityLabel.setText(selectedDocument.getPriority());
    }

    public void showParcelInfo(){
        serviceNameText.setVisible(true);
        serviceNameLabel.setVisible(true);
        trackingNumberText.setVisible(true);
        trackingNumberLabel.setVisible(true);
        Parcel selectedParcel = (Parcel)selectedItem;
        senderLabel.setText(selectedParcel.getSenderName());
        recipientLabel.setText(selectedParcel.getRecipient());
        typeLabel.setText(selectedParcel.getItemType());
        sizeLabel.setText(selectedParcel.getSize());
        roomNumberLabel.setText(selectedParcel.getRoom().getRoomNumber());
        receivingPersonnelLabel.setText(selectedParcel.getReceivingPersonnel());
        receivedDateLabel.setText(formatter.format(selectedParcel.getDateReceived()));
        if(selectedItem.getPickupDate() == null){
            pickupDateLabel.setText("-");
        }else{
            pickupDateLabel.setText(formatter.format(selectedParcel.getPickupDate()));
        }
        serviceNameText.setText("Service Name :");
        serviceNameLabel.setText(selectedParcel.getServiceName());
        trackingNumberText.setText("Tracking Number :");
        trackingNumberLabel.setText(selectedParcel.getTrackingNumber());

    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }

    @FXML public void handleCloseBtnOnAction(){
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
}
