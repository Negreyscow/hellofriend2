package app.controllers;

import app.model.dao.funcionarioDAO;
import app.model.database.ConnectionFactory;
import app.model.domain.Funcionario;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Aristarco on 06/01/17.
 */
public class CrudFuncionarioController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dao = new funcionarioDAO();

        idFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<Funcionario, Integer>("id"));
        nomeFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));
        nascimentoFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<Funcionario, Date>("dataNascimento"));
        cargoFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("cargo"));
        salarioFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<Funcionario, BigDecimal>("salario"));

    }

        @FXML
        private TabPane tabPane;

        @FXML
        private Tab tabCadastrar;

        @FXML
        private TextField novoFuncionarioNome;

        @FXML
        private DatePicker novoFuncionarioNascimento;

        @FXML
        private TextField novoFuncionarioCargo;

        @FXML
        private TextField novoFuncionarioSalario;

        @FXML
        private Button buttonLimpar;

        @FXML
        private Button buttonSalvar;

        @FXML
        private Tab tabConsultar;

        @FXML
        private TextField fieldFuncionarioConsulta;

        @FXML
        private Button buttonConsultar;

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
        private Tab tabAtualizar;

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

        private Funcionario funcioarioSelecionado;



        @FXML
        void salvarFuncionario() {

            Funcionario funcionario = new Funcionario();

            funcionario.setNome(novoFuncionarioNome.getText());
            funcionario.setSalario(new BigDecimal(novoFuncionarioSalario.getText()));
            funcionario.setCargo(novoFuncionarioCargo.getText());
            funcionario.setDataNascimento(Date.valueOf(novoFuncionarioNascimento.getValue()));

            try {
                dao.cadastrar(funcionario);
                exibirDialogoInformacao("Usuario Cadastrado com sucesso!");
                limparCadastroNovoFuncionario();
                tabPane.getSelectionModel().select(tabConsultar);
                consultarFuncionarios();
                tabAtualizar.setDisable(true);

            } catch (Exception e){
                exibirDialogoErro("Falha ao Cadastrar Usuario!");
                e.printStackTrace();
            }
        }

        @FXML
        void AtualizarFuncionario(){

            funcioarioSelecionado.setNome(FuncionarioNome.getText());
            funcioarioSelecionado.setCargo(FuncionarioCargo.getText());
            funcioarioSelecionado.setSalario(new BigDecimal(FuncionarioSalario.getText()));
            funcioarioSelecionado.setDataNascimento(Date.valueOf(FuncionarioNascimento.getValue()));

            try {

                dao.atualizar(funcioarioSelecionado);
                exibirDialogoInformacao("Usuario Atualizado com sucesso");
                tabPane.getSelectionModel().select(tabConsultar);
                consultarFuncionarios();

            } catch (Exception e) {
                exibirDialogoErro("Falha ao atualizar o funcionario!");
                e.printStackTrace();
            }


        }

        @FXML
        void consultarFuncionarios() {

            try {
                List<Funcionario> resultado = dao.consultar(fieldFuncionarioConsulta.getText());

                if(resultado.isEmpty()){
                    exibirDialogoInformacao("Nenhum Resultado foi encontrado");
                } else {

                    funcionarios.setItems(FXCollections.observableList(resultado));

                }
            } catch (Exception e){
                exibirDialogoErro("Falha ao realizar a consulta");
                e.printStackTrace();
            }

        }

        @FXML
        void deletarFuncionario() {

            if (funcionarios.getSelectionModel().getSelectedItem() == null){
                exibirDialogoErro("Não há funcionario selecionado!");
            } else {

                if (exibirDialogoConfirmação("Confirmar a exclusão do funcionario selecionado?")==true){
                    try {

                        dao.deletar(funcionarios.getSelectionModel().getSelectedItem().getId());
                        exibirDialogoInformacao("Funcionario Deletado com sucuesso!");
                        consultarFuncionarios();

                    } catch (Exception e){
                        exibirDialogoErro("Falha ao deletar o funcionario");
                        e.printStackTrace();
                    }
                }

            }


        }

        @FXML
        void exibirAbaAtualizada() {

            funcioarioSelecionado = funcionarios.getSelectionModel().getSelectedItem();

            if (funcioarioSelecionado == null){
                exibirDialogoErro("Não há funcionario selecionado!");
            } else {
                tabAtualizar.setDisable(false);
                tabPane.getSelectionModel().select(tabAtualizar);
                FuncionarioNome.setText(funcioarioSelecionado.getNome());
                FuncionarioCargo.setText(funcioarioSelecionado.getCargo());
                FuncionarioSalario.setText(funcioarioSelecionado.getSalario().toString());
                FuncionarioNascimento.setValue(funcioarioSelecionado.getDataNascimento().toLocalDate());
            }

        }

        @FXML
        void limparCadastroNovoFuncionario(){
            novoFuncionarioNome.clear();
            novoFuncionarioNascimento.setValue(null);
            novoFuncionarioCargo.clear();
            novoFuncionarioSalario.clear();
        }

        @FXML
        void gerenciarAbas() {

            if (tabCadastrar.isSelected() || tabConsultar.isSelected()){
                tabAtualizar.setDisable(true);
                limparCadastroAtualizacaoFuncionario();
            }
        }

        private void limparCadastroAtualizacaoFuncionario(){

            novoFuncionarioNome.clear();
            novoFuncionarioNascimento.setValue(null);
            novoFuncionarioCargo.clear();
            novoFuncionarioSalario.clear();

        }

        private void exibirDialogoInformacao(String informacao){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText(informacao);

            alert.showAndWait();
        }

    private void exibirDialogoErro(String erro){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(erro);

        alert.showAndWait();
    }

    private boolean exibirDialogoConfirmação(String confirmacao){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText(confirmacao);

        Optional<ButtonType> opcao = alert.showAndWait();
        if (opcao.get() == ButtonType.OK) return true;

        return false;

    }






}
