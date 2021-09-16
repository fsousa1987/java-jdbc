import java.sql.*;

public class TestaListagem {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://172.17.0.2/loja_virtual?useTimezone=true&serverTimezone=UTC", "root", "admin");

        Statement statement = connection.createStatement();
        statement.execute("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("ID");
            System.out.println(id);
            String nome = resultSet.getString("NOME");
            System.out.println(nome);
            String descricao = resultSet.getString("DESCRICAO");
            System.out.println(descricao);
        }

        connection.close();
    }
}
