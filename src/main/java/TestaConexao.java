import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexao {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://172.17.0.2/loja_virtual?useTimezone=true&serverTimezone=UTC", "root", "admin");

        System.out.println("Fechando conexão!!");
        connection.close();
    }
}
