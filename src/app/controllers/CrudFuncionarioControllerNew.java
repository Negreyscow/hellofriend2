package app.controllers;

import app.Main;
import app.model.dao.funcionarioDAO;
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
public class CrudFuncionarioControllerNew implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dao = new funcionarioDAO();

        consultarFuncionarios();

        idFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<Funcionario, Integer>("id"));
        nomeFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));
        nascimentoFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<Funcionario, Date>("dataNascimento"));
        cargoFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("cargo"));
        salarioFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<Funcionario, BigDecimal>("salario"));

    }


    @FXML
    private TextField fieldFuncionarioConsulta;




    @FXML
    private TableView<Funcionario> funcionarios;

    @FXML
    private TableColumn<Funcionario, Integer> idFuncionarioColumn;

    @FXML
    private TableColumn<Funcionario, String> nomeFuncionarioColumn;

    @FXML
    private TableColumn<Funcionario, Date> nascimentoFuncionarioColumn;

    @FXML
    private TableColumn<Funcionario, String> cargoFuncionarioColumn;

    @FXML
    private TableColumn<Funcionario, BigDecimal> salarioFuncionarioColumn;

    @FXML
    private Button buttonDeletar;

    @FXML
    private Button buttonAtualizar;


    @FXML
    private TextField FuncionarioNome;

    @FXML
    private DatePicker FuncionarioNascimento;

    @FXML
    private TextField FuncionarioCargo;

    @FXML
    private TextField FuncionarioSalario;

    @FXML
    private Button buttonSalvar1;

    private funcionarioDAO dao;

    private Funcionario funcioarioSelecionado = new Funcionario();

    public Funcionario ultimoHomem;


    @FXML
    void consultarFuncionarios() {

        try {
            List<Funcionario> resultado = dao.consultar(fieldFuncionarioConsulta.getText());

            if (resultado.isEmpty()) {
                exibirDialogoInformacao("Nenhum Resultado foi encontrado");
            } else {

                funcionarios.setItems(FXCollections.observableList(resultado));

            }
        } catch (Exception e) {
            exibirDialogoErro("Falha ao realizar a consulta");
            e.printStackTrace();
        }

    }

    @FXML
    void deletarFuncionario() {

        if (funcionarios.getSelectionModel().getSelectedItem() == null) {
            exibirDialogoErro("Não há funcionario selecionado!");
        } else {

            if (exibirDialogoConfirmação("Confirmar a exclusão do funcionario selecionado?") == true) {
                try {

                    dao.deletar(funcionarios.getSelectionModel().getSelectedItem().getId());
                    exibirDialogoInformacao("Funcionario Deletado com sucuesso!");
                    consultarFuncionarios();

                } catch (Exception e) {
                    exibirDialogoErro("Falha ao deletar o funcionario");
                    e.printStackTrace();
                }
            }

        }
    }


    @FXML
    void exibirAbaAtualizada() throws IOException {

        funcioarioSelecionado = funcionarios.getSelectionModel().getSelectedItem();
        retornaFuncionario(funcionarios.getSelectionModel().getSelectedItem());



        if (funcioarioSelecionado == null) {
            exibirDialogoErro("Não há funcionario selecionado!");
        } else {
/*
            FuncionarioNome.setText(funcioarioSelecionado.getNome());
            FuncionarioCargo.setText(funcioarioSelecionado.getCargo());
            FuncionarioSalario.setText(funcioarioSelecionado.getSalario().toString());
            FuncionarioNascimento.setValue(funcioarioSelecionado.getDataNascimento().toLocalDate());*/
            //retornaFuncionario(funcioarioSelecionado);
            goAlterarDados();
        }
    }

    public void retornaFuncionario(Funcionario pessoa) {
        ultimoHomem = pessoa;
    }

    public Funcionario getUltimoHomem(){
        return ultimoHomem;
    }



    /*
        private void limparCadastroAtualizacaoFuncionario() {

            novoFuncionarioNome.clear();
            novoFuncionarioNascimento.setValue(null);
            novoFuncionarioCargo.clear();
            novoFuncionarioSalario.clear();

        }*/


    //@FXML
    void goAlterarDados() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/viewAlterarFuncionariosNew.fxml"));
        BorderPane cadastrarProduto = loader.load();

        Stage addDialogStage = new Stage();
        addDialogStage.setTitle("Cadastrar Produto");
        addDialogStage.initModality(Modality.WINDOW_MODAL);
        // addDialogStage.initOwner(primaryStage);
        Scene scene = new Scene(cadastrarProduto);
        addDialogStage.setScene(scene);
        addDialogStage.showAndWait();
        consultarFuncionarios();

    }

    @FXML
    void goCadastrarFuncionario() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/viewCadastrarFuncionariosNew.fxml"));
        BorderPane cadastrarProduto = loader.load();

        Stage addDialogStage = new Stage();
        addDialogStage.setTitle("Cadastrar Produto");
        addDialogStage.initModality(Modality.WINDOW_MODAL);
        // addDialogStage.initOwner(primaryStage);
        Scene scene = new Scene(cadastrarProduto);
        addDialogStage.setScene(scene);
        addDialogStage.showAndWait();
        consultarFuncionarios();

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


}
