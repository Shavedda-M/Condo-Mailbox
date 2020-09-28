package app.services;

import app.models.*;
import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

    private static AccountList accounts = new AccountList();
    private static RoomList rooms = new RoomList();

    public static AccountList addAccountFromFile(String fileName, RoomList rooms) {
        try {
            CSVReader reader = new CSVReader(new FileReader(fileName), ',');
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String accountType = nextLine[0];
                String name = nextLine[1];
                String userName = nextLine[2];
                String password = nextLine[3];
                if (accountType.equals("admin")) {
                    Account ac = new Account(name, userName, password, accountType);
                    accounts.addAccount(ac);
                } else if (accountType.equals("personnel")) {
                    Account ac = new Personnel(name, userName, password);
                    accounts.addAccount(ac);
                } else if (accountType.equals("guest")) {
                    String roomNumber = nextLine[4];
                    Account ac = new Guest(name, userName, password, rooms.findRoom(name, roomNumber));
                    accounts.addAccount(ac);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public static RoomList addRoomFromFile(){
        return  rooms;
    }

    public void addItemFromFile(){

    }
}
