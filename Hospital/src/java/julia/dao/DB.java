/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package julia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class DB {
    private static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;
    
    public static void Connect() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
            conn = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/privatehospital", "root", "root");
            statmt = conn.createStatement();
    }
}
