package app.controllers;

import app.Main;
import app.model.dao.produtosDAO;
import app.model.domain.Produtos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Leonardo on 20/02/2017.
 */
public class concluirVendasController implements Initializable {


    private Produtos produtos;

    private vendasController vendas;

    private produtosDAO dao;

    private Main main;


    @FXML
    private TableView<Produtos> tableVendasItems;

    @FXML
    private TableColumn<Produtos, String> produtoColumn;

    @FXML
    private TableColumn<Produtos, Integer> qtdColumn;

    @FXML
    private TableColumn<Produtos, Double> valorColumn;

    @FXML
    private Label fieldTotal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dao = new produtosDAO();

    }
    @FXML
    private void actionVoltar() throws IOException {
        main.showViewVenda();

    }









}
