package app.model.dao;

import app.model.database.ConnectionFactory;
import app.model.domain.Produtos;
import javafx.fxml.FXML;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aristarco on 06/01/17.
 */

public class  produtosDAO { //esta classe realizar as operações básicas de manipulação de banco de dados para os produtos


    private Connection connection;

    public produtosDAO() {
        connection = new ConnectionFactory().getConnection();
    }

    public void cadastrar(Produtos produto) throws SQLException {

        String sql = "insert into produtos (nome, preco, quantidade, idproduto) values (?, ?, ?,?)";


            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, produto.getNome());
            statement.setDouble(2, produto.getPreco());
            statement.setInt(3, produto.getQuantidade());
            statement.setString(4, produto.getCategoria());


            statement.execute();
            statement.close();

    }

    public void cadastrarTotal(double totale) throws SQLException {

        String sql = "insert into tempTotal (total) values (?)";


        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setDouble(1,totale);

        statement.execute();
        statement.close();

    }

    public void alterar(Produtos produtos) throws SQLException{

        String sql = "update produtos set nome=?, preco=?, quantidade=?, idproduto=? where cdproduto=?";


            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, produtos.getNome());
            statement.setDouble(2, produtos.getPreco());
            statement.setInt(3, produtos.getQuantidade());
            statement.setString(4, produtos.getCategoria());
            statement.setInt(5, produtos.getCdproduto());

            statement.execute();
            statement.close();


    }

    public void deletar(Integer idProduto){

        String sql = "delete from produtos where cdproduto=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, idProduto);

            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public List<Produtos> consultar(String nomeProduto){

        List<Produtos> produtos = new ArrayList<>();

        String sql = "select * from produtos where nome like '%" + nomeProduto + "%'";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                Produtos produto = new Produtos();

                produto.setCdproduto(resultSet.getInt("cdproduto"));
                produto.setNome(resultSet.getString("nome"));
                produto.setQuantidade(resultSet.getInt("quantidade"));
                produto.setPreco(resultSet.getDouble("preco"));
                produto.setCategoria(resultSet.getString("idproduto"));

                produtos.add(produto);

            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return produtos;
    }

    public List<Produtos> consultar2(String nomeProduto){

        List<Produtos> produtos = new ArrayList<>();

        String sql = "select * from produtos where idproduto like '%" + nomeProduto + "%'";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                Produtos produto = new Produtos();

               // produto.setCdproduto(resultSet.getInt("cdproduto"));
                produto.setNome(resultSet.getString("nome"));
                produto.setQuantidade(resultSet.getInt("quantidade"));
                produto.setPreco(resultSet.getDouble("preco"));
                produto.setCategoria(resultSet.getString("idproduto"));

                produtos.add(produto);

            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return produtos;
    }

    ///aditional





}
