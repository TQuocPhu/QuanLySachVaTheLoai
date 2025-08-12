package org.example;

import management.UserManagement;
import menu.LoginMenu;
import menu.MainMenu;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    //    Truoc khi co Login
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.showMenu();
    }
}