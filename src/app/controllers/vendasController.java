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
import javafx.util.Callback;

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

    @FXML
    public ListView<String> listView;

    private List<String> listListView = new ArrayList();

    public ObservableList<String> observableListView;

    private produtosDAO dao;

    private Produtos produtoSelecionado;

    @FXML
    private ComboBox<String> comboBoxBusca;

    private List<String>  listComboBox = new ArrayList<>();
    private ObservableList<String> observableListComboBox;

    //private List<Produtos> listCarrinho;
    //private ObservableList<Produtos> observableListCarrinho;

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


    //table view do carrinho de compras
    @FXML
    private TableView<Produtos> tableCarrinho;

    @FXML
    private TableColumn<Produtos, String> columnProdutoCar;

    @FXML
    private TableColumn<Produtos, Integer> columnQtdCar;

    @FXML
    private TableColumn<Produtos, BigDecimal> columnValorCar;

    private List<Produtos> listCarrinho = new ArrayList();

    public ObservableList<Produtos> observableListCarrinho;

    @FXML
    private Label fieldTotal;

    private double totalCompra = 0;


    class myObject{
        String nome;
        Integer qtd;
        BigDecimal maney;

       // http://java-buddy.blogspot.com.br/2013/05/implement-javafx-listview-for-custom.html

        myObject(String n, Integer q, BigDecimal m){
            nome = n;
            qtd = q;
            maney = m;
        }

        String getNomee(){
            return nome;
        }

            Integer getQtd(){
            return qtd;
            }

            BigDecimal getManey(){
                return maney;
            }

    }

    List<myObject> mylist;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        carregarComboBox();

        dao = new produtosDAO();
        goBuscar();

        idColumn.setCellValueFactory(new PropertyValueFactory<Produtos, String>("categoria"));
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

    public void carregarListView(){

        produtoSelecionado = tableVendasItems.getSelectionModel().getSelectedItem();
        if (produtoSelecionado == null)
            exibirDialogoInformacao("Precisa selecionar um item!");
        else {
            if (!listListView.contains(produtoSelecionado.getNome()))
                listListView.add(produtoSelecionado.getNome() + "           -          Valor R$: " + produtoSelecionado.getPreco());
            else
                System.out.println("works!");

            observableListView = FXCollections.observableArrayList(listListView);
            listView.setItems(observableListView);
        }
        carregarTableViewCarrinho();
    }

    @FXML
    void removerList(){
        String algo = listView.getSelectionModel().getSelectedItem();
        String aux;
        int contador = algo.length();
        for(int i = 0;i<contador;i++){
            if (algo.substring(i,i+1).equals(":")){
                int posicao = i+1;
                aux=algo.substring(posicao,algo.length());
                double valor=Double.parseDouble(aux);

                totalCompra -=valor;
                totalCompra = totalCompra*100;
                totalCompra = Math.round(totalCompra);
                totalCompra = totalCompra/100;
                fieldTotal.setText(Double.toString(totalCompra));
            }}

        listListView.remove(algo);
        listView.getItems().remove(algo);
    }

    public void carregarTableViewCarrinho(){

        produtoSelecionado = tableVendasItems.getSelectionModel().getSelectedItem();

        //columnProdutoCar.setCellValueFactory(new PropertyValueFactory<Produtos, String>("nome"));
        //columnQtdCar.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("quantidade"));
        //columnValorCar.setCellValueFactory(new PropertyValueFactory<Produtos, BigDecimal>("preco"));

        listCarrinho.add(produtoSelecionado);
        //comentario
        //observableListCarrinho = FXCollections.observableArrayList(listCarrinho);
        //tableCarrinho.setItems(observableListCarrinho);

        totalCompra += produtoSelecionado.getPreco();
        fieldTotal.setText(Double.toString(totalCompra));


    }

    public ObservableList<Produtos> getTableView(){
        return observableListCarrinho;
    }

    @FXML
    public void removerTableViewCarrinho(){
        Produtos produto = tableCarrinho.getSelectionModel().getSelectedItem();
        tableCarrinho.getItems().remove(produto);

    }


    @FXML
    void goCarrinho(){

       // carregarTableViewCarrinho();
        carregarListView();
        //pega o item selecionado na tabela

        //produtoSelecionado = tableVendasItems.getSelectionModel().getSelectedItem();
        /*
        observableListCarrinho = FXCollections.observableArrayList(produtoSelecionado);
        tableCarrinho.setItems(observableListCarrinho);
        System.out.println(produtoSelecionado.getNome());*/

       // String name = produtoSelecionado.getNome();
        //Integer quantidade = produtoSelecionado.getQuantidade();
       // BigDecimal preco = produtoSelecionado.getPreco();

       // mylist.add(new myObject(name,quantidade,preco));

        /*
        ListView<myObject> listView = new ListView<>();
        ObservableList<myObject> myObjectObservableList = FXCollections.observableList(mylist);
        listView.setCellFactory(new Callback<ListView<myObject>, ListCell<myObject>>() {
            @Override
            public ListCell<myObject> call(ListView<myObject> param) {
                ListCell<myObject> cell = new ListCell<myObject>(){
                    @Override
                    protected void updateItem(myObject t,boolean bln){
                        super.updateItem(t, bln);
                        if (t != null){
                            setText(t.getNomee() + ":" + t.getQtd() + ":" + t.getManey());
                        }
                    }
                };
                return cell;
            }
        }); */


    }

    @FXML
    void goNextView() throws IOException {

        if (listView.getItems().isEmpty()){
           exibirDialogoErro("Carrinho vazio!");
        }
        else
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
