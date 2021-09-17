import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {

    public static void main(String[] args) throws SQLException {
        var nome = "MOUSE";
        var descricao = "MOUSE SEM FIO); delete from PRODUTO;";
        var factory = new ConnectionFactory();
        var connection = factory.recuperarConexao();

        var statement = connection
                .prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, nome);
        statement.setString(2, descricao);

        statement.execute();

        var generatedKeys = statement.getGeneratedKeys();
        while (generatedKeys.next()) {
            var id = generatedKeys.getInt(1);
            System.out.println("O id criado foi: " + id);
        }
    }
}
