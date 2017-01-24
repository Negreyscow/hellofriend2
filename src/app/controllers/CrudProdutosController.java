package app.controllers;

import app.model.dao.produtosDAO;
import app.model.domain.Produtos;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.util.*;

public class CrudProdutosController implements Initializable {

    private produtosDAO dao;

    @FXML
    private TextField fieldBuscar;

    @FXML
    private TextField buscar;

    @FXML
    private Button buttonBuscar;

    @FXML
    private TableView<Produtos> produtoTable;

    @FXML
    private TableColumn<Produtos, Integer> idColumn;

    @FXML
    private TableColumn<Produtos, String> marcaColumn;

    @FXML
    private TableColumn<Produtos, String> tipoColumn;

    @FXML
    private TableColumn<Produtos, String > tamanhoColumn;

    @FXML
    private TableColumn<Produtos, String> corColumn;

    @FXML
    private Label precoField;

    @FXML
    private Label quantidadeField;

    @FXML
    void goAlterarButton() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dao = new produtosDAO();

        idColumn.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("id"));
        marcaColumn.setCellValueFactory(new PropertyValueFactory<Produtos, String>("marca"));
        tipoColumn.setCellValueFactory(new PropertyValueFactory<Produtos, String>("tipo"));
        tamanhoColumn.setCellValueFactory(new PropertyValueFactory<Produtos, String>("tamanho"));
        corColumn.setCellValueFactory(new PropertyValueFactory<Produtos, String>("cor"));
    }

    @FXML
    void goBuscar() {

            try {
                List<Produtos> resultado = dao.consultar(fieldBuscar.getText());

                if(resultado.isEmpty()){
                    exibirDialogoInformacao("Nenhum Resultado foi encontrado");
                } else {

                    produtoTable.setItems(FXCollections.observableList(resultado));

                }
            } catch (Exception e){
                exibirDialogoErro("Falha ao realizar a consulta");
                e.printStackTrace();
            }
    }

    @FXML
    void goCadastrarButton() {

    }

    @FXML
    void goRemoverButton() {

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
