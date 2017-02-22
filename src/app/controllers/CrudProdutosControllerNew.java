package app.controllers;


import app.Main;
import app.model.dao.produtosDAO;
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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CrudProdutosControllerNew implements Initializable {

    private produtosDAO dao;

    private Main main;

    private Produtos produtoSelecionado;

    @FXML
    private TextField fieldBuscar;

    @FXML
    private Button buttonBuscar;

    @FXML
    private TableView<Produtos> produtoTable;

    @FXML
    private TableColumn<Produtos, String> idColumn;

    @FXML
    private TableColumn<Produtos, String> produtoColumn;

    @FXML
    private TableColumn<Produtos, String> categoriaColumn;

    @FXML
    private TableColumn<Produtos, Integer> quantidadeColumn;

    @FXML
    private TableColumn<Produtos, BigDecimal> precoColumn;

    @FXML
    private Label precoField;

    @FXML
    private Label quantidadeField;

    @FXML
    private Tab tabAtualizar;

    @FXML
    private Tab tabConsultar;

    @FXML
    private TabPane abas;

    @FXML
    private TextField fieldProduto;

    @FXML
    private TextField fieldQtd;

    @FXML
    private TextField fieldValor;


    @FXML
    private TextField fieldCategoria;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dao = new produtosDAO();

        //consultarFuncionarios();
        goBuscar();

        idColumn.setCellValueFactory(new PropertyValueFactory<Produtos, String>("cdproduto"));
        produtoColumn.setCellValueFactory(new PropertyValueFactory<Produtos, String>("nome"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<Produtos, BigDecimal>("preco"));
        quantidadeColumn.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("quantidade"));
        categoriaColumn.setCellValueFactory(new PropertyValueFactory<Produtos, String>("categoria"));

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
        goBuscar();

    }

    @FXML
    void goRemoverButton() {


        if (produtoTable.getSelectionModel().getSelectedItem() == null){
            exibirDialogoErro("Não há produto selecionado!");
        } else {

            if (exibirDialogoConfirmação("Confirmar a exclusão do produto selecionado?")==true){
                try {

                    dao.deletar(produtoTable.getSelectionModel().getSelectedItem().getCdproduto());
                    exibirDialogoInformacao("Produto Deletado com sucuesso!");
                    goBuscar();


                } catch (Exception e){
                    exibirDialogoErro("Falha ao deletar o produto");
                    e.printStackTrace();
                }
            }

        }

    }

    @FXML
    void AtualizarFuncionario(){

        produtoSelecionado.setNome(fieldProduto.getText());
        produtoSelecionado.setCategoria(fieldCategoria.getText());
        produtoSelecionado.setQuantidade(new Integer(fieldQtd.getText()));
        produtoSelecionado.setPreco(new Double(fieldValor.getText()));

        try {

            dao.alterar(produtoSelecionado);
            exibirDialogoInformacao("Produto Atualizado!");
            goBuscar();
            abas.getSelectionModel().select(tabConsultar);


        } catch (Exception e){
            exibirDialogoErro("Falha ao Atualizar!");
        }

    }

    @FXML
    void exibirAbaAtualizada(){
        produtoSelecionado = produtoTable.getSelectionModel().getSelectedItem();

        if (produtoTable.getSelectionModel().getSelectedItem() == null){
            exibirDialogoErro("Não há um produto selecionado!");
        } else {
            tabAtualizar.setDisable(false);
            abas.getSelectionModel().select(tabAtualizar);
            fieldProduto.setText(produtoSelecionado.getNome());
            fieldCategoria.setText(produtoSelecionado.getCategoria());
            fieldQtd.setText(produtoSelecionado.getQuantidade().toString());
            fieldValor.setText(Double.toString(produtoSelecionado.getPreco()));
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
