
package ifpb.ads.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edilva
 */
public class Conexao {
    
    private static String url = "jdbc:postgresql://banco:5432/atividade-emprestimo";
    private static String user = "postgres";
    private static String password = "postgres";
    
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
