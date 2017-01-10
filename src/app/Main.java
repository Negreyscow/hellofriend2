package app;

import java.io.IOException;
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



	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("MaisVoce");


        //showLogin();
		showMainView();
		showMainItems();
	}

	private void showLogin() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("login/login.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
        scene.getStylesheets().add("app/Styles/myStyle.css");
		primaryStage.show();
	}

	public static void showMainView() throws IOException{
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

	//this
	public static void showAddUserStage() throws IOException {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/crudFuncionario.fxml"));
			AnchorPane showCrudFuncionario = loader.load();
			mainLayout.setCenter(showCrudFuncionario);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
