package app.model.dao;

import app.model.database.ConnectionFactory;
import app.model.domain.Produtos;
import app.model.domain.Vendas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Leonardo on 19/02/2017.
 */
public class vendasDAO {

    private Connection connection;

    public vendasDAO() {
        connection = new ConnectionFactory().getConnection();
    }

    public void cadastrar(Vendas vendas) throws SQLException {

        String sql = "insert into produtos (data_venda, valor, nome_cliente, parcelas) values (?, ?, ?,?)";


        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, vendas.getNome());
        statement.setDouble(2, vendas.getPreco());
        statement.setString(3, vendas.getNomeCliente());
        statement.setInt(4, vendas.getParcelas());


        statement.execute();
        statement.close();

    }










}
