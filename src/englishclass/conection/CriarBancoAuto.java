package englishclass.conection;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CriarBancoAuto extends ConnectionDB {

    private final String sql = "CREATE TABLE user (\n" +
            "id int not null primary key identity,\n" +
            "nome varchar(100) not null,\n" +
            "password varchar(256) not null,\n" +
            "user_image longvarchar,\n" +
            "type varchar(10) default 'comum');";

    private String[] stataments;

    public boolean executeCreation() {
        stataments = sql.split(";|;\\s");
        Statement currentStatement = null;

        for (String comand : stataments) {
            try {
                // Execute statement
                currentStatement = this.getConnection().createStatement();
                currentStatement.execute(comand.concat(";"));

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                // Release resources
                if (currentStatement != null) {
                    try {
                        getConnection().close();
                        currentStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                currentStatement = null;
            }
        }
        return true;
    }

    public void executeSqlScript(File inputFile) {

        // Delimiter
        String delimiter = ";";

        // Create scanner
        Scanner scanner;
        try {
            scanner = new Scanner(inputFile).useDelimiter(delimiter);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return;
        }
        ConnectionDB con = new ConnectionDB();
        // Loop through the SQL file statements
        Statement currentStatement = null;
        while (scanner.hasNext()) {

            // Get statement
            String rawStatement = scanner.next() + delimiter;
            System.out.println(rawStatement);
            try {
                // Execute statement
                currentStatement = con.getConnection().createStatement();
                currentStatement.execute(rawStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Release resources
                if (currentStatement != null) {
                    try {
                        currentStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                currentStatement = null;
            }
        }
    }
}
