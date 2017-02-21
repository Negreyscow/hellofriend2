package app.controllers;

import app.Main;
import app.model.dao.produtosDAO;
import app.model.domain.Produtos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Leonardo on 20/02/2017.
 */
public class concluirVendasController extends vendasController {


    private Produtos produtos;

    private produtosDAO dao;

    private Main main;


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
