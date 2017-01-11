package app.controllers;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;

public class MainItemsController {



	private Main main;
	//every FXML have to be a controller class
	
	@FXML
	private void goGerenciar() throws IOException{
		//main = new Main();
		main.showGerenciarScene();
		
	}

	@FXML
    private void goGerenciarProdutos() throws IOException {
        main.showGerenciarProdutosScene();
    }



}
