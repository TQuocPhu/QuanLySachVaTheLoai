package menu;

import Models.User;
import lib.Input;
import management.UserManagement;

public class LoginMenu {
    private UserManagement userManagement;

    public LoginMenu(UserManagement userManagement) {
        this.userManagement = userManagement;
    }

    public void showLoginMenu() {
        int choice;
        do {
            System.out.println("====== LOGIN MENU ======");
            System.out.println("1. Dang ky");
            System.out.println("2. Dang nhap");
            System.out.println("0. Thoat");

            System.out.print("Nhap lua chon: ");
            choice = Input.inputIntNumber();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    if (login()) {
                        System.out.println("Dang nhap thanh cong!");
                        MainMenu mainMenu = new MainMenu(); // vào menu chính
                        mainMenu.showMenu();
                    } else {
                        System.out.println("Sai tai khoan hoac mat khau!");
                    }
                    break;
                case 0:
                    System.out.println("Tam biet!");
                    break;
                default:
                    System.out.println("Khong hop le!");
            }
        } while (choice != 0);
    }

    public void register(){
        System.out.println("====== REGISTER ======");
        System.out.println("Nhap username: ");
        String username = Input.inputString();
        if(userManagement.findByUserName(username) != null) {
            System.out.println("Username da ton tai !!");
            return;
        }
        System.out.println("Nhap password: ");
        String password = Input.inputString();
        System.out.println("Nhap fullname: ");
        String fullName = Input.inputString();
        User user = new User(username, password, fullName);
        userManagement.add(user);
        System.out.println("Dang ki thanh cong !");
    }

    private boolean login(){
        System.out.println("====== LOGIN ======");
        System.out.println("Nhap username");
        String username = Input.inputString();
        System.out.println("Nhap password: ");
        String password = Input.inputString();
        return userManagement.checkLogin(username, password);
    }
}

