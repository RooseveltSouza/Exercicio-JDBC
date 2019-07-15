package my.agenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class conexaoDao {
    
    private static final String LOGIN = "root";
    private static final String SENHA = "";
    private static final String URL = "jdbc:mysql://localhost/agenda";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public static Connection getConexao(){
        Connection con = null;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, LOGIN, SENHA);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO " + ex.getMessage());
        } catch (ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "ERRO! " + e.getMessage());
        }
        
        return con;
    }
    
    public static void fecharConexao(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(conexaoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void fecharConexao(Connection con, PreparedStatement stmt){
        
        try {
            if(con != null){
                fecharConexao(con);
            }
            if(stmt != null){
                stmt.close();
            }
                
        } catch (SQLException ex) {
                    Logger.getLogger(conexaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fecharConexao(Connection con, PreparedStatement stmt, ResultSet rs) {
 
        try {
            if (con != null || stmt != null) {
                fecharConexao(con, stmt);
            }
            if (rs != null) {
                rs.close();
            }
 
 
        } catch (Exception e) {
            System.out.println("Não foi possível fechar o ResultSet " + e.getMessage());
        }
    }
}
