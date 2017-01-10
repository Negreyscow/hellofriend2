package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import app.Main;
//import sun.applet.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController {

    private Main main;

    @FXML
    private Label lblStatus;

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPassword;


    public void Login(ActionEvent event) throws IOException {
        if (txtUser.getText().equals("hello") && txtPassword.getText().equals("friend")){
            lblStatus.setText("Sucesso! :)");
            main.showMainView();
            main.showMainItems();
           //app.Main teste = new app.Main();
           //teste.showMainView();
           //teste.showMainItems();


        }
        else {
            lblStatus.setText("I want to read a hello, friend :(");
        }
    }





}
