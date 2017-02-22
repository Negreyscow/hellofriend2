package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import app.Main;
//import sun.applet.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController extends Main {

    private Main main;

    @FXML
    private Label lblStatus;

    @FXML
    private RadioButton ButonUser;
    @FXML
    private RadioButton ButonAdmin;

    @FXML
    private PasswordField txtPassword;


    public void Login(ActionEvent event) throws IOException {
        if (ButonAdmin.isSelected() && txtPassword.getText().equals("1234")){

            lblStatus.setText("Sucesso! :)");
            isAdmin=true;
            showMainView(isAdmin);
            showMainItems();

            //app.Main teste = new app.Main();
            //teste.showMainView();
            //teste.showMainItems();


        }
        else if(ButonUser.isSelected() && txtPassword.getText().equals("1234")){
            lblStatus.setText("Sucesso! :)");
            isAdmin=false;
            showMainView(isAdmin);
            showMainItems();
        }
        if(!ButonUser.isSelected()&&!ButonAdmin.isSelected())
        {
            lblStatus.setText("Nada foi selecionado! Tente novamente");
        }
        else
        {
            lblStatus.setText("Senha incorreta! Tente novamente");
        }
    }






}