import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection recuperarConexao() throws SQLException {
        return DriverManager
                .getConnection("jdbc:mysql://172.17.0.2/loja_virtual?useTimezone=true&serverTimezone=UTC", "root", "admin");
    }
}
