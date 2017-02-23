package app.controllers;


import app.Main;
import app.model.dao.produtosDAO;
import app.model.dao.vendasDAO;
import app.model.domain.Produtos;
import app.model.domain.Vendas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CrudHistoricoVendas implements Initializable {

    private vendasDAO dao;

    private Main main;

    //private Produtos produtoSelecionado;

    @FXML
    private TableView<Vendas> vendasTableView;

    @FXML
    ListView<String> listViewHistorico;

    @FXML
    private TableColumn<Vendas, Integer> parcelasColumn;

    @FXML
    private TableColumn<Vendas, String> ColunaNome;

    @FXML
    private TableColumn<Vendas,Date> colunaData;

    @FXML
    private TableColumn<Vendas, Double> colunaValor;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dao = new vendasDAO();

        //consultarFuncionarios();
        //goBuscar();


        goBuscar();

    }


    @FXML
    void goBuscar() {
/*
        try {
            List<Vendas> resultado = dao.consultar("");
            ObservableList<Vendas> obs = FXCollections.observableList(resultado);
            System.out.println(resultado.get(0).getNomeCliente());
            if(resultado.isEmpty()){
                exibirDialogoInformacao("Nenhum Resultado foi encontrado");
            } else {

                vendasTableView.setItems(obs);
            }
        } catch (Exception e){
            exibirDialogoErro("Falha ao realizar a consulta");
            e.printStackTrace();
        }*/
        List<Vendas> resultado = dao.consultar("");
        List<String> result = new ArrayList<>();

        for(int i=0;i<resultado.size();i++) {
                    result.add("Data da venda: " + resultado.get(i).getDataVenda() + "      Valor: " + resultado.get(i).getPreco()+ "       Cliente: " + resultado.get(i).getNomeCliente()+ "       Parcelado em " + resultado.get(i).getParcelas() + "X");
        }

        ObservableList<String> obs = FXCollections.observableList(result);
        listViewHistorico.setItems(obs);

    }


    private void exibirDialogoInformacao(String informacao){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(informacao);

        alert.showAndWait();
    }

    private void exibirDialogoErro(String erro){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(erro);

        alert.showAndWait();
    }

    private boolean exibirDialogoConfirmação(String confirmacao){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText(confirmacao);

        Optional<ButtonType> opcao = alert.showAndWait();
        if (opcao.get() == ButtonType.OK) return true;

        return false;

    }




}