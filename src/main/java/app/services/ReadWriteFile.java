package app.services;

import app.models.*;

import java.io.*;

public class ReadWriteFile {

    private AccountList accounts;
    private RoomList rooms = new RoomList();
    private String fileDirectoryName;
    private String fileName;


    public ReadWriteFile(String fileDirectoryName, String fileName) {
        accounts = new AccountList();
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    public void setFileDirectoryName(String fileDirectoryName) {
        this.fileDirectoryName = fileDirectoryName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    private void readAccountData() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            String accountType = data[0];
            String name = data[1];
            String userName = data[2];
            String password = data[3];
            if (accountType.equals("admin")) {
                Account ac = new Account(name, userName, password, accountType);
                accounts.addAccount(ac);
            }else if (accountType.equals("personnel")) {
                String status = data[4];
                int tryLogin = Integer.parseInt(data[5]);
                Account ac = new Personnel(name, userName, password, status, tryLogin);
                accounts.addAccount(ac);
            }else if (accountType.equals("guest")) {
                String building = data[4];
                String roomNumber = data[5];
                Account ac = new Guest(name, userName, password, rooms.findRoom(name, building, roomNumber));
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
        }
        return accounts;
    }

    public void setAccountsData(AccountList accounts) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Account account : accounts.toList()) {
                if(account.getAccountType().equals("admin")){
                    String line = account.getAccountType() + ","
                                + account.getName() + ","
                                + account.getUserName() + ","
                                + account.getPassword();
                    writer.append(line);
                }else if(account.getAccountType().equals("personnel")){
                    Personnel per = (Personnel) account;
                    String line = per.getAccountType() + ","
                                + per.getName() + ","
                                + per.getUserName() + ","
                                + per.getPassword() + ","
                                + per.getStatus() + ","
                                + per.getTryLogin();
                    writer.append(line);
                }else if(account.getAccountType().equals("guest")){
                    Guest guest = (Guest) account;
                    String line = guest.getAccountType() + ","
                                + guest.getName() + ","
                                + guest.getUserName() + ","
                                + guest.getPassword() + ","
                                + guest.getRoom().getBuilding() + ","
                                + guest.getRoom().getRoomNumber();
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
            rooms.addRoom(room);
            }
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

}
