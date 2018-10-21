/**
 * 
 */
package org.solsistemas.myspa.db;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * 
 */
public class ConexionMySQL {
 
    private Connection conn;
    private String userName;
    private String password;
    private String url;
    
    
    public ConexionMySQL(){
    
        try{
           /** Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://192.168.43.147:3306/myspa";
            userName = "user1";
            password = "root";**/
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/myspa";
            userName = "root";
            password = "root";
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public Connection getConn(){
        return conn;
    }
    
    public Connection abrir() throws Exception{
        conn = DriverManager.getConnection(url, userName, password);
        return conn;
    }
    
    public void cerrar(){
        try{
            if(conn != null){
                conn.close();
                conn = null;
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
}
