package menu;

import Models.User;
import lib.Input;
import management.UserManagement;

public class LoginMenu {
    private UserManagement userManagement;

    public LoginMenu(UserManagement userManagement) {
        this.userManagement = userManagement;
    }

    //    public User showLoginMenu() {
//        try {
//            int choice;
//            do {
//                System.out.println("====== LOGIN MENU ======");
//                System.out.println("1. Dang ky");
//                System.out.println("2. Dang nhap");
//                System.out.println("0. Thoat");
//
//                System.out.print("Nhap lua chon: ");
//                choice = Input.inputIntNumber();
//
//                switch (choice) {
//                    case 1:
//                        register();
//                        break;
//                    case 2:
//                        User currentUser = loginSystem();
//                        if (currentUser != null) {
//                            System.out.println("Dang nhap thanh cong!");
//                            System.out.println("Xin chao " + currentUser.getFullName());
//                            //MainMenu mainMenu = new MainMenu(); // vào menu chính
//                            MainMenu mainMenu = new MainMenu(currentUser); // vào menu chính
//                            mainMenu.showMenu();
//                        } else {
//                            System.out.println("Sai tai khoan hoac mat khau!");
//                        }
//                        break;
//                    case 0:
//                        System.out.println("Tam biet!");
//                        break;
//                    default:
//                        System.out.println("Khong hop le!");
//                }
//            } while (choice != 0);
//        } catch(Exception e){
//            System.out.println(e);
//        }
//    }
    public User showLoginMenu() {
        try {
            int choice;
            do {
                System.out.println("====== LOGIN MENU ======");
                System.out.println("1. Dang ky");
                System.out.println("2. Dang nhap");
                System.out.println("0. Thoat");

                System.out.print("Nhap lua chon: ");
                choice = Input.inputIntNumber();

                switch (choice) {
                    case 1 -> register();
                    case 2 -> {
                        User currentUser = loginSystem();
                        if (currentUser != null) {
                            System.out.println("Dang nhap thanh cong!");
                            System.out.println("Xin chao " + currentUser.getFullName());
                            return currentUser; // trả về user
                        } else {
                            System.out.println("Sai tai khoan hoac mat khau!");
                        }
                    }
                    case 0 -> {
                        System.out.println("Tam biet!");
                        return null; //thoát
                    }
                    default -> System.out.println("Khong hop le!");
                }
            } while (choice != 0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    public void register() {
        System.out.println("====== REGISTER ======");
        String usernameRegex = "^[a-zA-Z0-9]+$";
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$";

        String username;
        do{
            System.out.println("Nhap username: ");
            username = Input.inputString();
            if(!username.matches(usernameRegex)){
                System.out.println("Username khong hop le! Vui long chi nhap chu cai va chu so.");
            } else if (userManagement.findByUserName(username) != null) {
                System.out.println("Username da ton tai !!");
            } else {
                break;
            }
        } while(true);

        String password;
        do {
            System.out.println("Nhap password (toi thieu 8 ky tu, it nhat 1 chu hoa, 1 chu so, 1 ky tu dac biet): ");
            password = Input.inputString();
            // Regex: 8 ký tự trở lên, ít nhất 1 chữ hoa, 1 chữ số, 1 ký tự đặc biệt
            if (!password.matches(passwordRegex)) {
                System.out.println("Password khong hop le! Vui long thu lai.");
            } else {
                break;
            }
        } while (true);

        System.out.println("Nhap fullname: ");
        String fullName = Input.inputString();
        String role = "user";
        User user = new User(username, password, fullName, role);
        userManagement.add(user);
        System.out.println("Dang ki thanh cong !");
    }

    private boolean login() {
        System.out.println("====== LOGIN ======");
        System.out.println("Nhap username");
        String username = Input.inputString();
        System.out.println("Nhap password: ");
        String password = Input.inputString();
        return userManagement.checkLogin(username, password);
    }

    private User loginSystem() {
        System.out.println("====== LOGIN ======");
        System.out.println("Nhap username");
        String username = Input.inputString();
        System.out.println("Nhap password: ");
        String password = Input.inputString();

        return userManagement.login(username, password);
    }
}

