package app.controllers;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;
import app.controllers.CrudProdutosController;

public class MainItemsController {



	private Main main;
	//every FXML have to be a controller class
	private CrudProdutosController produtos;
	
	@FXML
	private void goGerenciar() throws IOException{
		//main = new Main();
		main.showAddUserStage();
		
	}

	@FXML
    private void goGerenciarProdutos() throws IOException {
        main.showGerenciarProdutosScene();
        //produtos.goBuscar();

    }

    @FXML
	private void goViewVenda() throws IOException {
		main.showViewVenda();
	}



}
