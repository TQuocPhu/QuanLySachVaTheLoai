package org.example;

import Models.User;
import dto.UserDatabase;
import management.UserManagement;
import menu.LoginMenu;
import menu.MainMenu;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    //    Truoc khi co Login
//    public static void main(String[] args) {
//        MainMenu mainMenu = new MainMenu();
//        mainMenu.showMenu();
//    }

    //    Sau khi co Login
    public static void main(String[] args) {
        UserDatabase userDatabase = new UserDatabase("database/user.csv");
        UserManagement userManagement = new UserManagement(userDatabase);

        LoginMenu loginMenu = new LoginMenu(userManagement);
        User currentUser = loginMenu.showLoginMenu();

        if (currentUser != null) {
            MainMenu mainMenu = new MainMenu(currentUser);
            mainMenu.showMenu();
        } else {
            System.out.println("Chuong trinh ket thuc.");
        }
    }
}