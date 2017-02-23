package app.controllers;

import app.model.dao.funcionarioDAO;
import app.model.domain.Funcionario;
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

public class CrudFuncionarioAlterarController implements Initializable{ //extends CrudFuncionarioControllerNew  {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dao = new funcionarioDAO();
        //setarLabels();


    }


    @FXML
    private funcionarioDAO dao;

    //CrudFuncionarioControllerNew theLast;

    Funcionario lastMan = new Funcionario();

    @FXML
    private TextField FuncionarioNome;

    @FXML
    private TextField FuncionarioSalario;

    @FXML
    private TextField FuncionarioCargo;

    @FXML
    private DatePicker FuncionarioNascimento;

    @FXML
    private Button cancelButton;

    private Funcionario funcioarioSelecionado;


    public void setarLabels(){
        //lastMan = getUltimoHomem();
          if (lastMan == null) System.out.println("nooooooooo!");
          FuncionarioNome.setText("nome");
       // FuncionarioCargo.setText(ultimoHomem.getCargo());
       // FuncionarioSalario.setText(ultimoHomem.getSalario().toString());
        //FuncionarioNascimento.setValue(ultimoHomem.getDataNascimento().toLocalDate());
    }

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





}
