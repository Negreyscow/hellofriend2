package app.controllers;

import app.Main;
import app.model.dao.ClienteDAO;
import app.model.database.Database;
import app.model.database.DatabaseFactory;
import app.model.domain.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.util.Observable;

/**
 * Created by Aristarco on 27/12/16.
 */
public class AddNewFuncController {

    Main main;

   // ObservableList<String> nivelDeAcessoList = FXCollections
       //     .observableArrayList("Funcionario", "Gerente", "Eu");


    @FXML
    private TextField nome_field;

    @FXML
    private TextField enderecoField;

    @FXML
    private TextField telefoneField;

    @FXML
    private TextField login_field;

    @FXML
    private TextField nivelDeAcessoField;

    @FXML
    private PasswordField senhaField;


    @FXML
    private ChoiceBox nivelDeAcessoBox;


    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Cliente cliente;


    @FXML
    private void initialize() {
        nivelDeAcessoBox.setValue("Funcionario");
        nivelDeAcessoBox.setItems(FXCollections.observableArrayList(
                "Funcionario", "Gerente"
        ));
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        //this.textFieldClienteNome
        this.nome_field.setText(cliente.getNome());
        this.enderecoField.setText(cliente.getEndereco());
        this.telefoneField.setText(cliente.getTelefone());
        this.login_field.setText(cliente.getUsuario());
        this.senhaField.setText(cliente.getSenha());
        this.nivelDeAcessoField.setText(cliente.getNivelAcesso());

    }

    @FXML
    public void handleButtonConfirmar() {

        if (validarEntradaDeDados()) {

            cliente.setNome(nome_field.getText());
            cliente.setEndereco(enderecoField.getText());
            cliente.setTelefone(telefoneField.getText());

            buttonConfirmarClicked = true;
            dialogStage.close();
        }

    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }


    //Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (nome_field.getText() == null || nome_field.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
        }
        if (enderecoField.getText() == null || enderecoField.getText().length() == 0) {
            errorMessage += "CPF inválido!\n";
        }
        if (telefoneField.getText() == null || telefoneField.getText().length() == 0) {
            errorMessage += "Telefone inválido!\n";
        }
        if (login_field.getText() == null || login_field.getText().length() == 0) {
            errorMessage += "Usuário inválido!\n";
        }
        if (senhaField.getText() == null || senhaField.getText().length() == 0) {
            errorMessage += "Senha inválido!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }

}
