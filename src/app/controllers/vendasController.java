package app.controllers;

import app.Main;
import app.model.dao.produtosDAO;
import app.model.domain.Produtos;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

import static app.Main.mainLayout;

/**
 * Created by Leonardo on 19/02/2017.
 */
public class vendasController implements Initializable {

    private Main main;

    private produtosDAO dao;

    private Produtos produtoSelecionado;

    @FXML
    private ComboBox<String> comboBoxBusca;

    private List<String>  listComboBox = new ArrayList<>();
    private ObservableList<String> observableListComboBox;

    private List<Produtos> listCarrinho = new ArrayList<>();
    private ObservableList<Produtos> observableListCarrinho;

    @FXML
    private TableColumn<Produtos, String> idColumn;

    @FXML
    private TableColumn<Produtos, String> produtoColumn;

    @FXML
    private TableColumn<Produtos, String> categoriaColumn;

    @FXML
    private TableColumn<Produtos, Integer> qtdColumn;

    @FXML
    private TableColumn<Produtos, BigDecimal> valorColumn;

    @FXML
    private TextField fieldBuscar;

    @FXML
    private TableView<Produtos> tableVendasItems;

    @FXML
    private TableView tableCarrinho = new TableView<>();

    @FXML
    private TableColumn columnProdutoCar = new TableColumn<>();

    @FXML
    private TableColumn<Produtos, Integer> columnQtdCar;

    @FXML
    private TableColumn<Produtos, BigDecimal> columnValorCar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        carregarComboBox();

        dao = new produtosDAO();


        idColumn.setCellValueFactory(new PropertyValueFactory<Produtos, String>("idproduto"));
        produtoColumn.setCellValueFactory(new PropertyValueFactory<Produtos, String>("nome"));
        valorColumn.setCellValueFactory(new PropertyValueFactory<Produtos, BigDecimal>("preco"));
        qtdColumn.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("quantidade"));

    }


    //combo box para a categoria da busca
    public void carregarComboBox(){

        listComboBox.add("Nome Produto");
        listComboBox.add("Codigo Produto");

        observableListComboBox = FXCollections.observableArrayList(listComboBox);
        //não é possivel popular com listClientes
        comboBoxBusca.setItems(observableListComboBox);

    }

    @FXML
    void goCarrinho(){
        //pega o item selecionado na tabela
        //produtoSelecionado = tableVendasItems.getSelectionModel().getSelectedItem();
        listCarrinho = tableVendasItems.getSelectionModel().getSelectedItems();
        observableListCarrinho = FXCollections.observableArrayList(listCarrinho);
        tableCarrinho.setItems(observableListCarrinho);


        System.out.println(produtoSelecionado.getNome());



    }

    @FXML
    void goNextView() throws IOException {

        main.showSecondViewVenda();

    }


    @FXML
    void goBuscar() {

        try {

            List<Produtos> resultado;

            if (comboBoxBusca.getValue() == "Codigo Produto"){
                resultado = dao.consultar2(fieldBuscar.getText());
            }
            else {
                resultado = dao.consultar(fieldBuscar.getText());
            }

            if(resultado.isEmpty()){
                exibirDialogoInformacao("Nenhum Resultado foi encontrado");
            } else {

                tableVendasItems.setItems(FXCollections.observableList(resultado));
            }

        } catch (Exception e){
            exibirDialogoErro("Falha ao realizar a consulta");
            e.printStackTrace();
        }

    }


    //alerts

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
