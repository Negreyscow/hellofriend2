package app.model.dao;

import app.model.database.ConnectionFactory;
import app.model.domain.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

/**
 * Created by Aristarco on 06/01/17.
 */
public class funcionarioDAO { //esta classe realizar as operações básicas de manipulação de banco de dados para o funcionario

    private Connection connection;

    public funcionarioDAO() {
        connection = new ConnectionFactory().getConnection();
    }

    public void cadastrar(Funcionario funcionario){

        String sql = "insert into funcionarios (nome, data_nascimento, cargo, salario, password) values (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, funcionario.getNome());
            statement.setDate(2, funcionario.getDataNascimento());
            statement.setString(3, funcionario.getCargo());
            statement.setBigDecimal(4, funcionario.getSalario());
            statement.setString(5,funcionario.getPassword());

            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void atualizar(Funcionario funcionario){

        String sql = "update funcionarios set nome=?, data_nascimento=?, cargo=?, salario=? where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, funcionario.getNome());
            statement.setDate(2, funcionario.getDataNascimento());
            statement.setString(3, funcionario.getCargo());
            statement.setBigDecimal(4, funcionario.getSalario());
            statement.setInt(5, funcionario.getId());


            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void deletar(Integer idFuncionario){

        String sql = "delete from funcionarios where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, idFuncionario);

            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public List<Funcionario> consultar(String nomeFuncionario){

        List<Funcionario> funcionarios = new ArrayList<>();

        String sql = "select * from funcionarios where nome like '%" + nomeFuncionario + "%'";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                Funcionario funcionario = new Funcionario();

                funcionario.setId(resultSet.getInt("id"));
                funcionario.setNome(resultSet.getString("nome"));
                funcionario.setDataNascimento(resultSet.getDate("data_nascimento"));
                funcionario.setCargo(resultSet.getString("cargo"));
                funcionario.setSalario(resultSet.getBigDecimal("Salario"));

                funcionarios.add(funcionario);

            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return funcionarios;
    }



}
