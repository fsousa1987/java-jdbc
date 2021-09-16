import java.sql.*;

public class TestaListagem {

    public static void main(String[] args) throws SQLException {
        var connectionFactory = new ConnectionFactory();
        var connection = connectionFactory.recuperarConexao();

        var statement = connection.createStatement();
        statement.execute("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
        var resultSet = statement.getResultSet();

        while (resultSet.next()) {
            var id = resultSet.getInt("ID");
            System.out.println(id);
            var nome = resultSet.getString("NOME");
            System.out.println(nome);
            var descricao = resultSet.getString("DESCRICAO");
            System.out.println(descricao);
        }

        connection.close();
    }
}
