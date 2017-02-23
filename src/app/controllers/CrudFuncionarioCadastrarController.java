package app.controllers;

import app.Main;
import app.model.dao.funcionarioDAO;
import app.model.dao.produtosDAO;
import app.model.domain.Funcionario;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Aristarco on 06/01/17.
 */
public class CrudFuncionarioCadastrarController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dao = new funcionarioDAO();
    }


    @FXML
    private TextField novoFuncionarioNome;

    @FXML
    private DatePicker novoFuncionarioNascimento;

    @FXML
    private TextField novoFuncionarioCargo;

    @FXML
    private TextField novoFuncionarioSalario;

    @FXML
    private TextField novoFuncionarioSenha;

    @FXML
    private Button buttonLimpar;

    @FXML
    private Button buttonSalvar;

    private funcionarioDAO dao;

    @FXML
    void salvarFuncionario(){
        boolean erro=false;
        Funcionario funcionario = new Funcionario();
        System.out.println(novoFuncionarioNome.getText());
        if(!novoFuncionarioNome.getText().isEmpty()) {
                funcionario.setNome(novoFuncionarioNome.getText());

        }
        else
        {
            exibirDialogoErro("Falha ao Cadastrar funcionário! Nome não preenchido!");
            erro = true;
        }
        String price =novoFuncionarioSalario.getText();
        if(!novoFuncionarioSalario.getText().isEmpty()) {
            price = price.replace(',', '.');
                funcionario.setSalario(new BigDecimal(price));
        }
        else
        {
            exibirDialogoErro("Falha ao Cadastrar funcionário! Salario não preenchido!");
            erro = true;
        }
        if(!novoFuncionarioCargo.getText().isEmpty()) {
                funcionario.setCargo(novoFuncionarioCargo.getText());

        }
        else
        {
            exibirDialogoErro("Falha ao Cadastrar funcionário! Cargo não preenchido!");
            erro = true;
        }
        try {
            funcionario.setDataNascimento(Date.valueOf(novoFuncionarioNascimento.getValue()));
        } catch (Exception e) {
            exibirDialogoErro("Falha ao Cadastrar funcionário! Data não preenchida!");
            erro = true;
        }

        //funcionario.setPassword(novoFuncionarioSenha.getText());

        if(!erro) {
            try {

                dao.cadastrar(funcionario);
                exibirDialogoInformacao("Usuario Cadastrado com sucesso!");
                limparCadastroNovoFuncionario();
                closeStage();


            } catch (Exception e) {
                exibirDialogoErro("Falha ao Cadastrar Usuario!");
                e.printStackTrace();
            }
        }
    }

    @FXML
    void limparCadastroNovoFuncionario(){

        novoFuncionarioNome.clear();
        novoFuncionarioNascimento.setValue(null);
        novoFuncionarioCargo.clear();
        novoFuncionarioSalario.clear();

    }

    private void exibirDialogoInformacao(String informacao) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(informacao);

        alert.showAndWait();
    }

    private void exibirDialogoErro(String erro) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(erro);

        alert.showAndWait();
    }

    private boolean exibirDialogoConfirmação(String confirmacao) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText(confirmacao);

        Optional<ButtonType> opcao = alert.showAndWait();
        if (opcao.get() == ButtonType.OK) return true;

        return false;

    }


    private void closeStage(){

        Stage stage = (Stage) buttonSalvar.getScene().getWindow();
        stage.close();

    }



}
