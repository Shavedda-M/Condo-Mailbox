package app.services;

import app.models.*;
import app.models.Parcel;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ReadWriteFile {

    private AccountList accounts;
    private RoomList rooms;
    private ItemList items;
    private String fileDirectoryName;
    private String fileName;


    public ReadWriteFile(String fileDirectoryName, String fileName) {
        accounts = new AccountList();
        rooms = new RoomList();
        items = new ItemList();
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    public void setFileDirectoryName(String fileDirectoryName) {
        this.fileDirectoryName = fileDirectoryName;
        checkFileIsExisted();
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted() {
        File file = new File(fileDirectoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Cannot create " + filePath);
            }
        }
    }

    private void readAccountData() throws IOException, ParseException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            String accountType = data[0];
            String name = data[1];
            String userName = data[2];
            String password = data[3];
            if (accountType.equals("admin")) {
                String imageFileName = data[4];
                Account ac = new Account(name, userName, password, accountType, imageFileName);
                accounts.addAccount(ac);
            }else if (accountType.equals("personnel")) {
                String status = data[4];
                String date = data[5];
                Date lastLoginTime = formatter.parse(date);
                int tryLogin = Integer.parseInt(data[6]);
                String imageFileName = data[7];
                Account ac = new Personnel(name, userName, password, status, lastLoginTime, tryLogin, imageFileName);
                accounts.addAccount(ac);
            }else if (accountType.equals("guest")) {
                String building = data[4];
                String roomNumber = data[5];
                String imageFileName = data[6];
                Account ac = new Guest(name, userName, password, rooms.findRoom(name, building, roomNumber), imageFileName);
                accounts.addAccount(ac);
            }
        }
        reader.close();
    }

    public AccountList getAccountsData() {
        try {
            readAccountData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public void setAccountsData(AccountList accounts) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Account account : accounts.toList()) {
                if(account.getAccountType().equals("admin")){
                    String line = account.getAccountType() + ","
                                + account.getName() + ","
                                + account.getUserName() + ","
                                + account.getPassword() + ","
                                + account.getImageFileName();
                    writer.append(line);
                }else if(account.getAccountType().equals("personnel")){
                    Personnel per = (Personnel) account;
                    if (per.getLastLoginTime().equals(null)) {
                        String line = per.getAccountType() + ","
                                + per.getName() + ","
                                + per.getUserName() + ","
                                + per.getPassword() + ","
                                + per.getStatus() + ","
                                + "00/00/0000 00:00:00" + ","
                                + per.getTryLogin() + ","
                                + per.getImageFileName();
                        writer.append(line);
                    }else{
                        String line = per.getAccountType() + ","
                                + per.getName() + ","
                                + per.getUserName() + ","
                                + per.getPassword() + ","
                                + per.getStatus() + ","
                                + formatter.format(per.getLastLoginTime()) + ","
                                + per.getTryLogin() + ","
                                + per.getImageFileName();
                        writer.append(line);
                    }
                }else if(account.getAccountType().equals("guest")){
                    Guest guest = (Guest) account;
                    String line = guest.getAccountType() + ","
                                + guest.getName() + ","
                                + guest.getUserName() + ","
                                + guest.getPassword() + ","
                                + guest.getRoom().getBuilding() + ","
                                + guest.getRoom().getRoomNumber() + ","
                                + guest.getImageFileName();
                    writer.append(line);
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }

    private void readRoomData() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            String building = data[0];
            String floor = data[1];
            String roomNumber = data[2];
            String roomType = data[3];
            Room room = new Room(building, floor, roomNumber, roomType);
            for(int i = 4; i < data.length; i++){
                String guestName = data[i];
                room.addGuest(guestName);
            }
            rooms.addRoom(room);
        }
        reader.close();
    }

    public RoomList getRoomData() {
        try {
            readRoomData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return rooms;
    }

    public void setRoomData(RoomList rooms){
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Room room : rooms.toList()) {
                String line = room.getBuilding() + ","
                            + room.getFloor() + ","
                            + room.getRoomNumber() + ","
                            + room.getRoomType();
                            if(room.getGuestNameList().size() > 0){
                                line += "," + room.getGuestName();
                            }
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }

    private void readItemData() throws IOException, ParseException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            String itemType = data[0];
            String recipient = data[1];
            String building = data[2];
            String roomNumber = data[3];
            String sender = data[4];
            String size = data[5];
            String receivingPersonnel = data[6];
            Date dateReceived = formatter.parse(data[7]);
            if (itemType.equals("mail")) {
                String status = data[8];
                if(status.equals("Received")){
                    Date pickupDate = formatter.parse(data[9]);
                    String imageFileName = data[10];
                    Item item = new Item(itemType, recipient, rooms.findRoom(recipient, building, roomNumber), sender, size
                                        , receivingPersonnel, dateReceived, status, pickupDate, imageFileName);
                    items.addItem(item);
                }else{
                    String imageFileName = data[9];
                    Item item = new Item(itemType, recipient, rooms.findRoom(recipient
                                        , building, roomNumber), sender, size, receivingPersonnel, dateReceived, status, imageFileName);
                    items.addItem(item);
                }
            }else if (itemType.equals("document")) {
                String priority = data[8];
                String status = data[9];
                if(status.equals("Received")){
                    Date pickupDate = formatter.parse(data[10]);
                    String imageFileName = data[11];
                    Item item = new Document(itemType, recipient, rooms.findRoom(recipient, building, roomNumber), sender
                                            , size, receivingPersonnel, dateReceived, status, priority, pickupDate, imageFileName);
                    items.addItem(item);
                }else{
                    String imageFileName = data[10];
                    Item item = new Document(itemType, recipient, rooms.findRoom(recipient, building, roomNumber), sender
                                            , size, receivingPersonnel, dateReceived, status, priority, imageFileName);
                    items.addItem(item);
                }

            }else if (itemType.equals("parcel")) {
                String serviceName = data[8];
                String trackingNumeber = data[9];
                String status = data[10];
                if(status.equals("Received")){
                    Date pickupDate = formatter.parse(data[11]);
                    String imageFileName = data[12];
                    Item item = new Parcel(itemType, recipient, rooms.findRoom(recipient, building, roomNumber), sender, size, receivingPersonnel, dateReceived, status, serviceName,trackingNumeber, pickupDate, imageFileName);
                    items.addItem(item);
                }else{
                    String imageFileName = data[11];
                    Item item = new Parcel(itemType, recipient, rooms.findRoom(recipient, building, roomNumber), sender, size, receivingPersonnel, dateReceived, status, serviceName,trackingNumeber, imageFileName);
                    items.addItem(item);
                }
            }
        }
        reader.close();
    }

    public ItemList getItemData() {
        try {
            readItemData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void setItemData(ItemList items){
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Item item : items.toList()) {
                if(item.getItemType().equals("mail")){
                    String line = item.getItemType() + ","
                                + item.getRecipient() + ","
                                + item.getRoom().getBuilding() + ","
                                + item.getRoom().getRoomNumber() + ","
                                + item.getSenderName() + ","
                                + item.getSize() + ","
                                + item.getReceivingPersonnel() + ","
                                + formatter.format(item.getDateReceived()) + ","
                                + item.getStatus();
                    if(item.getStatus().equals("Received")){
                        line += "," + formatter.format(item.getPickupDate()) + "," + item.getImageFileName();
                    }else{
                        line += "," + item.getImageFileName();
                    }
                    writer.append(line);
                }else if(item.getItemType().equals("document")){
                    Document doc = (Document) item;
                    String line = doc.getItemType() + ","
                                + doc.getRecipient() + ","
                                + doc.getRoom().getBuilding() + ","
                                + doc.getRoom().getRoomNumber() + ","
                                + doc.getSenderName() + ","
                                + doc.getSize() + ","
                                + doc.getReceivingPersonnel() + ","
                                + formatter.format(doc.getDateReceived()) + ","
                                + doc.getPriority() + ","
                                + doc.getStatus();
                    if(doc.getStatus().equals("Received")){
                        line += "," + formatter.format(doc.getPickupDate()) + "," + doc.getImageFileName();
                    }else{
                        line += "," + doc.getImageFileName();
                    }
                    writer.append(line);
                }else if(item.getItemType().equals("parcel")){
                    Parcel par = (Parcel) item;
                    String line = par.getItemType() + ","
                                + par.getRecipient() + ","
                                + par.getRoom().getBuilding() + ","
                                + par.getRoom().getRoomNumber() + ","
                                + par.getSenderName() + ","
                                + par.getSize() + ","
                                + par.getReceivingPersonnel() + ","
                                + formatter.format(par.getDateReceived())  + ","
                                + par.getServiceName()  + ","
                                + par.getTrackingNumber() + ","
                                + par.getStatus();
                    if(par.getStatus().equals("Received")){
                        line += "," + formatter.format(par.getPickupDate()) + "," + par.getImageFileName();
                    }else{
                        line += "," + par.getImageFileName();
                    }
                    writer.append(line);
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }

}
