package englishclass.conection;

import java.sql.*;

public class DatabaseConection {
    private Connection con = null;  //variavel para conexao
    private Statement statement = null;
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;

    private boolean status = false;
    public String mensagem = "";
    private final String SCHEMA = "jdbc:mariadb://";
    private final String HOST = "192.168.1.40:3306";
    private final String DATABASE = "testedb";
    private final String SQL_URI = SCHEMA + HOST + "/" + DATABASE;
    private String usuario = "englishclass";
    private String senha = "";

    private Connection getConnection(){
        try {
            //Driver do PostgreSQL
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(SQL_URI, usuario, senha);
            //"jdbc:mariadb://localhost:3306/DB?user=root&password=myPassword"
        }
        catch(SQLException | ClassNotFoundException e){
            //System.out.println("Erro de conexão: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean executarSQL(String pSQL){
        try {
            con = getConnection();

            // Statements allow to issue SQL queries to the database
            statement = con.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery(pSQL);
            mensagem = writeResultSet(resultSet);
            return true;
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            close();
        }
        return false;
    }

    private String writeResultSet(ResultSet resultSet) throws SQLException {
        String object = "";
        // ResultSet is initially before the first data set
        while (resultSet.next()) {

            int id = resultSet.getInt(1);
            String nome = resultSet.getString(2);
            String valor = resultSet.getString(3);

            object += "id: " + id + "  nome: " + nome + " valor: " + valor + "\n";

        }
        return object;
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {

        }
    }
}
