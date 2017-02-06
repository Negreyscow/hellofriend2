package app.controllers;


import app.model.dao.produtosDAO;
import app.Main;
import app.model.domain.Produtos;
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
import java.util.*;

public class CrudProdutosController implements Initializable {

    private produtosDAO dao;

    private Main main;

    private produtosDAO produtoSelecionado;

    @FXML
    private TextField fieldBuscar;

    @FXML
    private Button buttonBuscar;

    @FXML
    private TableView<Produtos> produtoTable;

    @FXML
    private TableColumn<Produtos, Integer> idColumn;

    @FXML
    private TableColumn<Produtos, String> produtoColumn;

    @FXML
    private TableColumn<Produtos, Integer> categoriaColumn;

    @FXML
    private TableColumn<Produtos, Integer> quantidadeColumn;

    @FXML
    private TableColumn<Produtos, BigDecimal> precoColumn;

    @FXML
    private Label precoField;

    @FXML
    private Label quantidadeField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dao = new produtosDAO();

        //consultarFuncionarios();
        goBuscar();

        idColumn.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("cdproduto"));
        produtoColumn.setCellValueFactory(new PropertyValueFactory<Produtos, String>("nome"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<Produtos, BigDecimal>("preco"));
        quantidadeColumn.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("quantidade"));
        categoriaColumn.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("cdcategoria"));

        //goBuscar();

    }



    @FXML
    void goAlterarButton() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/viewAlterarProdutos.fxml"));
        BorderPane alterarProduto = loader.load();

        Stage addDialogStage = new Stage();
        addDialogStage.setTitle("Alterar Produto");
        addDialogStage.initModality(Modality.WINDOW_MODAL);
        // addDialogStage.initOwner(primaryStage);
        Scene scene = new Scene(alterarProduto);
        addDialogStage.setScene(scene);
        addDialogStage.showAndWait();

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
    void goCadastrarButton() throws IOException {

       //main.showCadastrarProdutos();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/viewCadastrarProdutos.fxml"));
        BorderPane cadastrarProduto = loader.load();

        Stage addDialogStage = new Stage();
        addDialogStage.setTitle("Cadastrar Produto");
        addDialogStage.initModality(Modality.WINDOW_MODAL);
       // addDialogStage.initOwner(primaryStage);
        Scene scene = new Scene(cadastrarProduto);
        addDialogStage.setScene(scene);
        addDialogStage.showAndWait();

    }

    @FXML
    void goRemoverButton() {


        if (produtoTable.getSelectionModel().getSelectedItem() == null){
            exibirDialogoErro("Não há funcionario selecionado!");
        } else {

            if (exibirDialogoConfirmação("Confirmar a exclusão do funcionario selecionado?")==true){
                try {

                    dao.deletar(produtoTable.getSelectionModel().getSelectedItem().getCdproduto());
                    exibirDialogoInformacao("Funcionario Deletado com sucuesso!");
                    goBuscar();


                } catch (Exception e){
                    exibirDialogoErro("Falha ao deletar o funcionario");
                    e.printStackTrace();
                }
            }

        }

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
