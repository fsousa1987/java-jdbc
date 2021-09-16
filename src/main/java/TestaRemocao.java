import java.sql.SQLException;

public class TestaRemocao {

    public static void main(String[] args) throws SQLException {
        var factory = new ConnectionFactory();
        var connection = factory.recuperarConexao();

        var statement = connection.createStatement();
        statement.execute("DELETE FROM PRODUTO WHERE id > 2");

        var linhasModificadas = statement.getUpdateCount();

        System.out.println("Quantidade de linhas que foram modificadas: " + linhasModificadas);
    }
}
