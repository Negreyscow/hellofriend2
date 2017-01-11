package app.controllers;

import app.Main;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Created by Aristarco on 11/01/17.
 */
public class ProdutosController {

    private Main main;

    @FXML
    private void goDadosProdutos() throws IOException {

        main.showGerenciarProdutos();    }

}
