package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private DataBase(){}
    public static String URL = "jdbc:mysql://localhost:3306/dbmyapp", USER = "developer", PWD = "1234567";
    private static Connection conexao = null;
    
    public static Connection getConnection() {
        if(conexao == null) {
            try {
                conexao = DriverManager.getConnection(URL, USER, PWD);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return conexao;
    }

    public static void closeConnection() throws SQLException {
        if(conexao != null) {
            conexao.close();
        }
        else {
            System.out.println("A conexção ainda não foi instânciada!");
        }
    }
}
