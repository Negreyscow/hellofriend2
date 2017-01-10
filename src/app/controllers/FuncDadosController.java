package app.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import app.model.database.Database;
import app.model.database.DatabaseFactory;
import app.model.domain.Cliente;

import app.model.domain.Cliente;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import app.Main;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ResourceBundle;


/**
 * Created by Aristarco on 02/01/17.
 */
public class FuncDadosController implements Initializable {

    private Main main;

    @FXML
    private TableView<Cliente> tableViewFuncionarios;
    @FXML
    private TableColumn<Cliente, String> tableViewFuncionariosColumnNome;
    @FXML
    private Label nomeLabel;
    @FXML
    private Label funcIdLabel;
    @FXML
    private Label enderecoLabel;
    @FXML
    private Label telefoneLabel;
    @FXML
    private Label usuarioLabel;
    @FXML
    private Label senhaLabel;
    @FXML
    private Label nivelAcessoLabel;

    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListClientes;

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final app.model.dao.ClienteDAO clienteDAO = new app.model.dao.ClienteDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        carregarTableViewCliente();

        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        tableViewFuncionarios.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));

    }

    public void carregarTableViewCliente() {
        tableViewFuncionariosColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        listClientes = clienteDAO.listar();
        observableListClientes = FXCollections.observableArrayList(listClientes);
        tableViewFuncionarios.setItems(observableListClientes);
    }

    public void selecionarItemTableViewClientes(Cliente cliente){
        if (cliente != null) {
            funcIdLabel.setText(String.valueOf(cliente.getCdCliente()));
            nomeLabel.setText(cliente.getNome());
            enderecoLabel.setText(cliente.getEndereco());
            telefoneLabel.setText(cliente.getTelefone());
            usuarioLabel.setText(cliente.getUsuario());
            senhaLabel.setText(cliente.getSenha());
            nivelAcessoLabel.setText(cliente.getNivelAcesso());
        } else {
            funcIdLabel.setText("");
            nomeLabel.setText("");
            enderecoLabel.setText("");
            telefoneLabel.setText("");
            usuarioLabel.setText("");
            senhaLabel.setText("");
            nivelAcessoLabel.setText("");
        }

    }
/*
    public void handleButtonAdicionar() throws IOException {
        main.showAddUserStage();
    }*/

    @FXML
    public void handleButtonAdicionar() throws IOException {
        Cliente cliente = new Cliente();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosClientesDialog(cliente);
        if (buttonConfirmarClicked) {
            clienteDAO.inserir(cliente);
            carregarTableViewCliente();
        }
    }

    public boolean showFXMLAnchorPaneCadastrosClientesDialog(Cliente cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AddNewFuncController.class.getResource("app/view/AnchorPaneCadastroDialog.fxml"));
        AnchorPane page;
        page = loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Clientes");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        AddNewFuncController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCliente(cliente);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }


}
