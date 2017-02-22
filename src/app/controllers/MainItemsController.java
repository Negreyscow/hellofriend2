package app.controllers;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;
import app.controllers.CrudProdutosController;
import javafx.scene.control.Alert;

public class MainItemsController extends Main {



	private Main main;
	//every FXML have to be a controller class
	private CrudProdutosController produtos;

	@FXML
	private void goGerenciar() throws IOException{
		if(isAdmin)
			showAddUserStage();
		else
			exibirDialogoErro("Voce nao tem permissao de acesso a este item!");


	}

	@FXML
	private void goGerenciarProdutos() throws IOException {
		if(isAdmin)
			showGerenciarProdutosScene();
		else
			exibirDialogoErro("Voce nao tem permissao de acesso a este item");
		//produtos.goBuscar();

	}

	@FXML
	private void goViewVenda() throws IOException {
		main.showViewVenda();
	}
	public void exibirDialogoErro(String erro){
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Erro");
		alert.setHeaderText(null);
		alert.setContentText(erro);

		alert.showAndWait();
	}

	@FXML
	void emConstrucao()
	{
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Aviso");
		alert.setHeaderText(null);
		alert.setContentText("Esta funcionalidade está em fase de implementação!");
		alert.showAndWait();
	}

}

