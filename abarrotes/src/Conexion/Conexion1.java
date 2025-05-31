package Conexion;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion1 {
    Connection ccn = null;
    Statement st = null;
    public Conexion1(){
        try{
            String rutafile = "dbabarrotes.accdb";
            String Url = "jdbc:ucanaccess://"+rutafile;
            ccn = DriverManager.getConnection(Url);
            st = ccn.createStatement();
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Conexion erronea"+e);
        }
    }
    public Connection getConnection(){
        return ccn;
    }
    public void Desconexion(){
        try{
            ccn.close();
            System.exit(0);
        }catch(SQLException ex){
            Logger.getLogger(Conexion1.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
