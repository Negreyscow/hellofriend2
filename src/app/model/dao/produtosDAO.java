package app.model.dao;

import app.model.database.ConnectionFactory;
import app.model.domain.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aristarco on 06/01/17.
 */
public class produtosDAO { //esta classe realizar as operações básicas de manipulação de banco de dados para os produtos


    private Connection connection;

    public produtosDAO() {
        connection = new ConnectionFactory().getConnection();
    }

    public void cadastrar(Produtos produtos){

        String sql = "insert into produtos (marca, tipo, tamanho, cor) values (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, produtos.getMarca());
            statement.setString(2, produtos.getTipo());
            statement.setString(3, produtos.getTamanho());
            statement.setString(4, produtos.getCor());

            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void alterar(Produtos produtos){

        String sql = "update produtos set marca=?, tipo=?, tamanho=?, cor=? where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, produtos.getMarca());
            statement.setString(2, produtos.getTipo());
            statement.setString(3, produtos.getTamanho());
            statement.setString(4, produtos.getCor());

            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void deletar(Integer idProduto){

        String sql = "delete from produtos where id=?";

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

    public List<Produtos> consultar(String produtoTipo){

        List<Produtos> produtos = new ArrayList<>();

        String sql = "select * from produtos where id like '%" + produtoTipo + "%'";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                Produtos produto = new Produtos();

                produto.setCodProduto(resultSet.getInt("id"));
                produto.setMarca(resultSet.getString("marca"));
                produto.setTipo(resultSet.getString("tipo"));
                produto.setTamanho(resultSet.getString("tamanho"));
                produto.setCor(resultSet.getString("cor"));

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



}
