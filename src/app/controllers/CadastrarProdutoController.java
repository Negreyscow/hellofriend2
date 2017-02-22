package app.controllers;

        import app.Main;
        import app.model.dao.produtosDAO;
        import app.model.domain.Produtos;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Button;
        import javafx.scene.control.ButtonType;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;

        import java.math.BigDecimal;
        import java.net.URL;
        import java.sql.Date;
        import java.util.Optional;
        import java.util.ResourceBundle;

public class CadastrarProdutoController implements Initializable {

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
    private Button alterarButton;


    private Main main;

    private produtosDAO dao;

    private Produtos produtoSelecionado;

    private CrudProdutosController control;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dao = new produtosDAO();
    }

    @FXML
    void actionCadastrar() {

        boolean erro=false;
        Produtos produto = new Produtos();
        if(!fieldDescricao.getText().isEmpty())
            produto.setNome(fieldDescricao.getText());
        String price =fieldPreco.getText();
        if(!fieldPreco.getText().isEmpty()) {
            price = price.replace(',', '.');
            try {
                produto.setPreco(new Double(price));
            }
            catch (Exception e)
            {
                exibirDialogoErro("Falha ao Cadastrar Produto! Preço incorreto!");
                erro=true;
            }

        }
        if(!fieldEstoque.getText().isEmpty()) {
            try {
                produto.setQuantidade(new Integer(fieldEstoque.getText()));
            }
            catch (Exception e)
            {
                exibirDialogoErro("Falha ao Cadastrar Produto! Quantidade incorreta!");
                erro=true;
            }
        }
        if(!fieldProdutoCat.getText().isEmpty())
            produto.setCategoria(fieldProdutoCat.getText());


        if(!erro) {
            try {
                dao.cadastrar(produto);
                exibirDialogoInformacao("Produto Cadastrado com sucesso!");
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
                // limparCadastroNovoFuncionario();
                // produtosController.goBuscar();

            } catch (Exception e) {
                exibirDialogoErro("Falha ao Cadastrar Produto! Algum campo nao foi preenchido!");
                e.printStackTrace();
            }
        }


    }

    @FXML
    void actionCancelar() {

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

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

    private void exibirDialogoInformacao(String informacao){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(informacao);

        alert.showAndWait();
    }

}


