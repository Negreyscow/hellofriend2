package app.controllers;

import app.Main;
import app.model.dao.produtosDAO;
import app.model.dao.vendasDAO;
import app.model.domain.Produtos;
import app.model.domain.Vendas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Leonardo on 20/02/2017.
 */
public class concluirVendasController extends Main implements Initializable{

    /*public concluirVendasController(){
        vendasClass = new vendasController();
        List<String> listV = vendasClass.getList();
        observableListView = FXCollections.observableArrayList(listV);
        listView.setItems(observableListView);

    }*/


    //private Produtos produtos;

    //vendasController vendasClass;

    private List<String> ListAux = new ArrayList();

    //public ObservableList<String> observableListView2;

    //public ListView<String> listView;

    private vendasDAO dao;

    @FXML
    private ComboBox<Integer> BoxParcelas;

    @FXML
    private Label FieldTotal;

    @FXML
    private DatePicker DatadaVenda;

    @FXML
    public TextField FieldCliente;

    private List<Integer>  listComboBox = new ArrayList<>();
    private ObservableList<Integer> observableListComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dao = new vendasDAO();
        FieldTotal.setText(Double.toString(totalCompra));
        carregarComboBox();
        //carregarListView();

    }

    @FXML
    private void actionVoltar() throws IOException {
        showViewVenda();//
    }

    @FXML
    void concluirVenda2() throws IOException {
        exibirDialogoInformacao("Venda finalizada com sucesso!");
        showMainItems();
    }

    @FXML
    void concluirVenda() throws IOException{
        boolean erro=false;
        Vendas venda=new Vendas();
        vendasController teste = new vendasController();
        venda.setPreco(teste.getTotalCompra());

        if(!FieldCliente.getText().isEmpty()) {
            venda.setNomeCliente(FieldCliente.getText());

        }
        else
        {
            exibirDialogoInformacao("Coloque um nome válido!");
            erro = true;
        }
        if(!BoxParcelas.getValue().toString().isEmpty()) {
            venda.setParcelas(BoxParcelas.getValue());
        }
        else
        {
            exibirDialogoInformacao("Você precisar de parcelas para este tipo de pagamento!");
            erro = true;
        }
        try {
            venda.setDataVenda(Date.valueOf(DatadaVenda.getValue()));
        } catch (Exception e) {
            exibirDialogoInformacao("Use uma data válida!");
            erro = true;
        }


        if(!erro) {
            try {

                dao.cadastrar(venda);
                exibirDialogoInformacao("Venda finalizada com sucesso!");
                showMainItems();
                //closeStage();

            } catch (Exception e) {
                exibirDialogoErro("Venda não finalizada");
                e.printStackTrace();
            }
        }
    }

    public void carregarComboBox(){

        for(int i=1;i<=12;i++) {
            listComboBox.add(i);
        }

        observableListComboBox = FXCollections.observableArrayList(listComboBox);
        //não é possivel popular com listClientes
        BoxParcelas.setItems(observableListComboBox);

    }

    public void carregarListView() {


        //ListAux.add("Eu");

        //observableListView = FXCollections.observableArrayList(ListAux);
        //listView.setItems(observableListView);
    }

    public void setListAux(String str){
        ListAux.add(str);
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