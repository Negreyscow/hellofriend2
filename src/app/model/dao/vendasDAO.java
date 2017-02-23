package app.model.dao;

import app.model.database.ConnectionFactory;
import app.model.domain.Funcionario;
import app.model.domain.Produtos;
import app.model.domain.Vendas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonardo on 19/02/2017.
 */
public class vendasDAO {

    private Connection connection;

    public vendasDAO() {
        connection = new ConnectionFactory().getConnection();
    }

    public void cadastrar(Vendas vendas) throws SQLException {

        String sql = "insert into vendas (data_venda, valor, nome_cliente, parcelas) values (?, ?, ?,?)";


        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setDate(1, (Date) vendas.getDataVenda());
        statement.setDouble(2, vendas.getPreco());
        statement.setString(3, vendas.getNomeCliente());
        statement.setInt(4, vendas.getParcelas());


        statement.execute();
        statement.close();

    }


    public List<Vendas> consultar(String nomeCliente){

        List<Vendas> vendaas = new ArrayList<>();

        String sql = "select * from vendas where nome_cliente like '%" + nomeCliente + "%'";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                Vendas venda = new Vendas();
//
                venda.setId(resultSet.getInt("idvenda"));
                venda.setPreco(resultSet.getDouble("valor"));
                venda.setDataVenda(resultSet.getDate("data_venda"));
                venda.setParcelas(resultSet.getInt("parcelas"));
                venda.setNomeCliente(resultSet.getString("nome_cliente"));

                vendaas.add(venda);

            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return vendaas;
    }



}
