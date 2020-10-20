# Project Application
---

## Setup

1. Open Link [Project Link](https://bitbucket.org/6210402461/6210402461/src/master/)
2. Click Button "Clone"
3. Copy clone link
4. Go to your directory you want to setup
5. Right-click the select "Git Bash Here"
6. Paste clone link with Right-click then paste or shift + insert
7. Press Enter
8. When clone finished open project folder
9. Open launcher folder
10. Open file 6210402461.jar

```
# Clone File
$ git clone https://Shavedda_M@bitbucket.org/6210402461/6210402461.git
```


#### ** If you can't clone or can't find clone link
1. Go to [Project Link](https://bitbucket.org/6210402461/6210402461/src/master/)
2. Click Button ••• then Select "Download repository"
3. Open zip file
4. Extract the file

#### ** If you can't right-clicked but don't have "Git Bash Here"
1. Open cmd the change directory to where you want to setup
2. Paste clone link

```
# Example

# Change Directory | Use "cd" to change directory
cd C:\Users\M S I\Downloads

# Clone file
git clone https://Shavedda_M@bitbucket.org/6210402461/6210402461.git
```

#### ** If you double clicked but program won't launch
##### Git Bash
1. Right-click then select "Git Bash Here"
2. Type command

```
# Open File
$ java -jar 6210402461.jar
```
##### Command Prompt
1. Open cmd the change directory to launcher folder in the file
2. Type command


```
# Example

# Change Directory to launcher folder
cd C:\Work\Year 2\01418211 Software Construction\Lab\Project\6210402461\launcher

# Open jar File
java -jar 6210402461.jar
```
---
## Project Structure

* .idea → เก็บไฟล์การตั้งค่า project ของ IntelliJ
* data → เก็บข้อมูล csv สำหรับใช้ใน IntelliJ
* launcher → เก็บ jar file ที่ build แล้ว
    * data → เก็บข้อมูลไฟล์ csv สำหรับ jar file ใน launcher
* src → สำหรับเก็บข้อมูลหลักของโปรแกรม
    * main
        * java
            * app
                * controllers → เก็บ class controller ของหน้าต่างๆ
                    * admin → เก็บ controller หน้าการทำงานของ admin
                    * guest → เก็บ controller หน้าการทำงานของ guest
                    * login → เก็บ controller หน้า login
                    * personnel → เก็บ controller หน้าการทำงานของ personnel
                    * popup → เก็บ controller ของ popup
                    * setting → เก็บ controller หน้าของ setting
                * exceptions → เก็บ class exception ที่สร้างไว้
                * models → เก็บ class model ของโปรแกรม
                * services → เก็บ class ส่วนใช้งานต่างๆ เช่น อ่านเขียนไฟล์, browse รูป
        * resources → เก็บข้อมูลไฟล์ fxml และ รูปภาพ
            * image → เก็บไฟล์รูปภาพ
* target → ไว้เก็บข้อมูลที่ได้จากการ build
---
## BuildLog

\+ Added [ เพิ่ม ] 

^ Improved / Fixed [ ปรับปรุง / แก้ไข ] 

\# Mixed [ มีการแก้หลายอย่าง ]

~ Removed [ ลบ ]


[20/10/2020 22:39:24 AM] [ ]

* \+ Added More Room to rooms.csv
* \+ Added More Item to items.csv
* \+ Added LastLoginTime to Personnel
* \+ Added Received Date, Pickup Date and Receiving Personnel
    * Item
    * Document
    * Parcel
* \+ Added Search Room by Room Number to PersonnelRoomListPageController
* \+ Added Search Item by Room Number to PersonnelManageItemPageController
* \+ Added Recieve Item Function to GuestItemListPageController
* \+ Added Popup Controller
    * ConfirmActionPopupController
    * ItemInfoPopupController
    * NotificationPopupController
    * RoomInfoPopupController
* \+ Added fxml
    * comfirm_action_popup
    * item_info_popup
    * notification_popup
    * room_info_popup
* \+ Added Warning Label for incorrect info and Notification Popup when Added or Changed complete
    * LoginPageController
    * RegisterPageController
    * AdminAddPersonnelPageController
    * PersonnelAddRoomPageController
    * PersonnelAddMailPageController
    * PersonnelAddDocumentPageController
    * PersonnelAddParcelPageController
* \+ Added Check Info button to find more about information
    * PersonnelManageItemPageController
    * GuestItemListPageController
* \+ Added Exception
    * BannedAccountException
    * InvalidRoomNumberException
    * NoAccountFoundException
    * RoomFullException
    * UsernameNotAvailableException
* \+ Added more TableColumn to Tableview
    * AdminPersonnelListPageController
    * PersonnelRoomListPageController
    * PersonnelManagaItemPageController
    * GuestItemListPageController
* ^ Updated UI Button
* ^ Improved Change Password in SettingChangePasswordPageController
* ^ Change Controller Filename
    * { PersonnelAddPackagePageController.java → PersonnelAddParcelPageController.java } 
* ^ Update ReadWriteFile to match new added Function
* ^ Change Model name
    * { Package → Parcel }
* ^ Update AddRoom Fuction to match Room Number pattern
* ~ Removed Browse Image from PersonnelAddGuestController


[06/10/2020 02:07:43 PM] [fa67887]

* \+ Added csv and Change csv file directory to data
    * rooms.csv
* \+ Added Services
    * ReadWriteFile
* ^ Improved TableView Column
    * AdminPersonnelListController
    * PersonnelManageItemController
    * PersonnelRoomListController
    * GuestItemListController
* ^ Improved UI
* ^ Improved Project Structure
* \# Update Controller
    * ^ Change Controller Filename
        * Personnel
            * { PersonnelAddItemPageController.java → PersonnelAddDocumentPageController.java } 
    * \+ Added Controller
        * Personnel
            * PersonnelAddRoomPageController
            * PersonnelSelectAddItemPageController
            * PersonnelAddMailPageController
            * PersonnelAddPackagePageController
* \# Update fxml
    * ^ Change fxml Filename
        * { personnel_add_item_page.fxml → personnel_add_document_page.fxml } 
    * \+ Added fxml
        * personnel_add_room_page
        * personnel_select_add_item_page
        * personnel_add_mail_page
        * personnel_add_package_page
* ~ Removed Interface
    * Items
* ~ Removed Services
    * ReadFile

[28/09/2020 01:02:32 PM] [f2ce2f9]

* \+ Added Interface
    * Accounts
    * Items
* \+ Added Model
    * Item
    * Document
    * Package
    * ItemList
* \+ Added Services
    * BrowseImage
    * ReadFile
* \+ Added Return to Previous page Button to Controller
    * SettingPageController
    * SettingChangePasswordPageController
    * SettingProfilePageController
* ^ Improved BrowseImage
    * AdminAddPersonnelController
    * PersonnelAddItemController
    * PersonnelAddGuestController
    * RegisterPageController
    * SettingProfilePageController

[22/09/2020 08:12:37 PM] [aaae92c]

* \+ Added Model
    * Personnel
    * Guest
    * Room
    * RoomList
* \+ Added TableView
    * AdminPersonnelListController
    * PersonnelManageItemController
    * PersonnelRoomListController
    * GuestItemListController
* \+ Added Browse Image
    * AdminAddPersonnelController
    * PersonnelAddItemController
    * PersonnelAddGuestController
    * RegisterPageController
    * SettingProfilePageController
* ^ Resize Project
    * [ 800 x 600 --->  1024 x 768 ]
* ^ Change Controller Filename
    * Admin
        * { AddPersonnelPageController → AdminAddPersonnelPageController }
        * { PersonnelListPageController → AdminPersonnelListPageController }
    * Personnel
        * { AddGuestPageController → PersonnelAddGuestPageController }
        * { AddItemPageController → PersonnelAddItemPageController }
        * { ManageItemPageController → PersonnelManageItemPageController }
        * { RoomListPageController → PersonnelRoomListPageController }
    * Guest
        * { ItemListPageController → GuestItemListPageController }
    * Setting
        * { ProfilePageController → SettingProfilePageController }
        * { ChangePasswordPageController → SettingChangePasswordPageController }
* ^ Change fxml Filename
    * { add_personnel_page.fxml → admin_add_personnel_page.fxml }
    * { personnel_list_page.fxml → admin_personnel_list_page.fxml }
    * { add_guest_page.fxml → personnel_add_guest_page.fxml }
    * { add_item_page_controller.fxml → personnel_add_item_page.fxml }
    * { manage_item_page.fxml → personnel_manage_item_page.fxml }
    * { room_list_page.fxml → personnel_room_list_page.fxml }
    * { item_list_page.fxml → guest_item_list_page.fxml }
    * { profile_page.fxml → setting_profile_page.fxml }
    * { change_password_page.fxml → setting_change_password_page.fxml }

[12/09/2020 09:41:00 PM] [197bf6d]

* \+ Added Project
    * \+ Added Maven to Project
    * \+ Added Main.java
    * \+ Added Controller
        * Login
            * LoginPageController
            * RegisterPageController
        * Admin
            * AdminHomePageController
            * AddPersonnelPageController
            * PersonnelListPageController
        * Personnel
            * PersonnelHomePageController
            * AddGuestPageController
            * AddItemPageController
            * ManageItemPageController
            * RoomListPageController
        * Guest
            * GuestHomePageController
            * ItemListPageController
        * Setting
            * SettingPageController
            * ProfilePageController
            * ChangePasswordPageController
    * \+ Added Model
        * Account
        * AccountList
    * \+ Added fxml
        * Login
        * Admin
        * Personnel
        * Guest
        * Setting      
    * \+ Added csv
        * accounts.csv
        
```java

```