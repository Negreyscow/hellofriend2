package app.controllers;

import app.model.dao.produtosDAO;
import app.model.domain.Produtos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Leonardo on 06/02/2017.
 */
public class AlterarProdutoController implements Initializable{

    public void initialize(URL location, ResourceBundle resources) {
        dao = new produtosDAO();

        fieldDescricao.getText();
        fieldPreco.getText();
        fieldEstoque.getText();
        fieldProdutoCat.getText();


    }

    private Produtos produtoSelecionado;

    private produtosDAO dao;
    @FXML
    private TextField fieldDescricao;

    @FXML
    private TextField fieldEstoque;

    @FXML
    private TextField fieldPreco;

    @FXML
    private TextField fieldProdutoCat;

    @FXML
    private Button cancelButton;

    @FXML
    void actionAlterar() {
        produtoSelecionado.setNome(fieldDescricao.getText());
        produtoSelecionado.setCategoria(fieldProdutoCat.getText());
        produtoSelecionado.setPreco(new Double(fieldPreco.getText()));
        produtoSelecionado.setQuantidade(new Integer(fieldEstoque.getText()));

        try {

            dao.alterar(produtoSelecionado);
            exibirDialogoInformacao("Produto Atualizado com sucesso");


        } catch (Exception e) {
            exibirDialogoErro("Falha ao atualizar o produto!");
            e.printStackTrace();
        }
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

}
