package app.controllers;

import app.Main;
import app.model.dao.produtosDAO;
import app.model.domain.Produtos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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


    private Produtos produtos;

    vendasController vendasClass;

    private List<String> ListAux = new ArrayList();

    public ObservableList<String> observableListView2;

    public ListView<String> listView;

    private produtosDAO dao;

    //private Main main;


    @FXML
    private Label FieldTotal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dao = new produtosDAO();
        FieldTotal.setText(Double.toString(totalCompra));
        //carregarListView();

    }

    @FXML
    private void actionVoltar() throws IOException {
        showViewVenda();//
    }

    @FXML
    void concluirVenda(){
        vendasController teste = new vendasController();
        System.out.println(teste.getTotalCompra());

    }


    public void carregarListView() {


        //ListAux.add("Eu");

        //observableListView = FXCollections.observableArrayList(ListAux);
        //listView.setItems(observableListView);
    }

    public void setListAux(String str){
        ListAux.add(str);
    }

}





