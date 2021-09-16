import java.sql.SQLException;

public class TestaConexao {

    public static void main(String[] args) throws SQLException {
        var connectionFactory = new ConnectionFactory();
        var connection = connectionFactory.recuperarConexao();

        System.out.println("Fechando conexão!!");
        connection.close();
    }
}
