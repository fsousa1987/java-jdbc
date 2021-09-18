import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {

    public static void main(String[] args) throws SQLException {
        var factory = new ConnectionFactory();
        var connection = factory.recuperarConexao();
        connection.setAutoCommit(false);

        try {
            var statement = connection
                    .prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            adicionarVariavel("SMARTTV", "45 POLEGADAS", statement);
            adicionarVariavel("RADIO", "RADIO DE BATERIA", statement);

            connection.commit();
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("ROLLBACK EXECUTADO");
            connection.rollback();
        }
    }

    private static void adicionarVariavel(String nome, String descricao, PreparedStatement statement) throws SQLException {
        statement.setString(1, nome);
        statement.setString(2, descricao);

        if (nome.equals("RADIO")) {
            throw new RuntimeException("Não foi possível adicionar o produto");
        }

        statement.execute();

        var generatedKeys = statement.getGeneratedKeys();
        while (generatedKeys.next()) {
            var id = generatedKeys.getInt(1);
            System.out.println("O id criado foi: " + id);
        }
    }
}
