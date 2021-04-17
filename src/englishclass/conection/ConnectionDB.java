package englishclass.conection;

import java.io.File;
import java.sql.*;

public class ConnectionDB {
    private Connection con = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private final String SCHEMA = "jdbc:hsqldb:file:";
    private static final String HOST =  "/home/gilmar/db/"; //"db" + File.separator;
    private final String DATABASE = "englishdb";
    private final String SQL_URI = SCHEMA + HOST + DATABASE;
    private String usuario = "admin";
    private String senha = "englishclass";
    private boolean sucess = false;

    protected Connection getConnection() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            return DriverManager.getConnection(SQL_URI, usuario, senha);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            return null;
        }
    }

    public void createEmptyDatabase() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            DriverManager.getConnection(SQL_URI, usuario, senha);
            System.out.println("Banco criado com sucesso");
            sucess = true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            //e.printStackTrace();
            sucess = false;
        } finally {
            close();
        }
    }

    /*
     * Execute Query retorna um ResultSet
     * Ideal para SQL tipo Select
     * */
    protected void selectSQL(String pSQL) {
        try {
            con = getConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery(pSQL);
        } catch (SQLException e) {
            System.out.println(e.getCause());
            System.out.println(pSQL);
        }
    }

    protected boolean executarUpdateDeleteSQL(String pSQL) {
        try {
            con = getConnection();
            statement = con.createStatement();
            statement.executeUpdate(pSQL);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(pSQL);
            return false;
        }finally {
            close();
        }
    }

    protected int insertSQL(String pSQL) {
        try {
            con = getConnection();
            statement = con.createStatement();
            return statement.executeUpdate(pSQL);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(pSQL);
            return 0;
        }finally {
            close();
        }
    }

    protected ResultSet getResultSet() {
        return resultSet;
    }

    // You need to close the resultSet
    protected void close() {
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
            e.printStackTrace();
        }
    }

    public boolean getSucess() {
        return sucess;
    }

}
