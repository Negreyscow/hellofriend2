package app.controllers;

import app.model.dao.funcionarioDAO;
import app.model.dao.produtosDAO;
import app.model.domain.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Created by Leonardo on 20/02/2017.
 */

public class CrudFuncionarioAlterarController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dao = new funcionarioDAO();
       // AtualizarFuncionario();
    }

    @FXML
    private funcionarioDAO dao;


    @FXML
    private TextField FuncionarioNome;

    @FXML
    private TextField FuncionarioSalario;

    @FXML
    private TextField FuncionarioCargo;

    @FXML
    private DatePicker FuncionarioNascimento;

    @FXML
    private Button buttonSalvar1;

    @FXML
    private Button cancelButton;

    private Funcionario funcioarioSelecionado;

    @FXML
    void AtualizarFuncionario() {

        funcioarioSelecionado.setNome(FuncionarioNome.getText());
        funcioarioSelecionado.setCargo(FuncionarioCargo.getText());
        funcioarioSelecionado.setSalario(new BigDecimal(FuncionarioSalario.getText()));
        funcioarioSelecionado.setDataNascimento(Date.valueOf(FuncionarioNascimento.getValue()));

        try {

            dao.atualizar(funcioarioSelecionado);
            exibirDialogoInformacao("Usuario Atualizado com sucesso");


        } catch (Exception e) {
            exibirDialogoErro("Falha ao atualizar o funcionario!");
            e.printStackTrace();
        }

    }

    @FXML
    void actionCancelar() {

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

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


    private void exibirAtualizado() {
/*
        funcioarioSelecionado = funcionarios.getSelectionModel().getSelectedItem();

        if (funcioarioSelecionado == null){
            exibirDialogoErro("Não há funcionario selecionado!");
        } else { */

            FuncionarioNome.setText(funcioarioSelecionado.getNome());
            FuncionarioCargo.setText(funcioarioSelecionado.getCargo());
            FuncionarioSalario.setText(funcioarioSelecionado.getSalario().toString());
            FuncionarioNascimento.setValue(funcioarioSelecionado.getDataNascimento().toLocalDate());
     //   }

    }


}
