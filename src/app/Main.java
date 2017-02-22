package app;

import java.io.IOException;

import app.controllers.CrudProdutosController;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {
	
	public static Stage primaryStage;
	public static BorderPane mainLayout;
	public static boolean isAdmin;
    public static double totalCompra;


	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("MaisVoce");


        showLogin();
		//showMainView();
		//showMainItems();
	}

	public void showLogin() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/login.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
        //scene.getStylesheets().add("app/Styles/myStyle.css");
		primaryStage.show();
	}

	public static void showMainView(boolean Admin) throws IOException{
		isAdmin=Admin;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainView.fxml")); //faz o loader do documento fxml dentro da main
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		scene.getStylesheets().add("app/Styles/myStyle.css");
		primaryStage.show();
	}
	
	public static void showMainItems() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainItems.fxml"));
		BorderPane mainItems = loader.load();
		mainLayout.setCenter(mainItems);//seta o centro do main layout para receber os main items
	}

	public static void showGerenciarScene() throws IOException{ // responsavel pelo acesso ao controlador
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/gerenciar_user.fxml"));
		BorderPane gerenciar_users = loader.load();
		mainLayout.setCenter(gerenciar_users); //the magic happens
	}

	public static void showGerenciarProdutosScene() throws IOException{ // responsavel pelo acesso ao controlador
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/crudProdutosNew.fxml"));
		BorderPane gerenciar_produtos = loader.load();
		mainLayout.setCenter(gerenciar_produtos); //the magic happens
		
	}
    public static void showConsultarProdutos() throws IOException{ // responsavel pelo acesso ao controlador
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/crudConsultarProdutos.fxml"));
        BorderPane crudConsultarProdutos = loader.load();
        mainLayout.setCenter(crudConsultarProdutos); //the magic happens

    }
    public static void showHistoricoVendas() throws IOException{ // responsavel pelo acesso ao controlador
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/crudHistoricoProdutos.fxml"));
        BorderPane crudHistoricoVendas = loader.load();
        mainLayout.setCenter(crudHistoricoVendas); //the magic happens

    }
	public static void showAddUserStage() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/crudFuncionariosNew2.fxml"));
		BorderPane showCrudFuncionario = loader.load();
		mainLayout.setCenter(showCrudFuncionario);
	}

	public static void showGerenciarProdutos() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/crudProdutos.fxml"));
		BorderPane showCrudProdutos = loader.load();
		mainLayout.setCenter(showCrudProdutos);

	}

	public static void showCadastrarProdutos() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/viewCadastrarProdutos.fxml"));
		BorderPane cadastrarProduto = loader.load();

		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("Cadastrar Produto");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		Scene scene = new Scene(cadastrarProduto);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();


	}

	public static void showViewVenda() throws IOException {
	    totalCompra=0;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/mainViewVendas.fxml"));
		BorderPane telaVenda = loader.load();
		mainLayout.setCenter(telaVenda);

	}

	public static void showSecondViewVenda(double tot) throws IOException {
	    totalCompra=tot;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/secondViewVendas.fxml"));
		BorderPane concluirVenda = loader.load();
		mainLayout.setCenter(concluirVenda);

	}

	public static void main(String[] args) {
		launch(args);
	}
}
