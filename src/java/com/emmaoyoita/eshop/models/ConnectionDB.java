/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emmaoyoita.eshop.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CHiBEX
 */
public class ConnectionDB {
    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    //static final String DB_URL = "jdbc:mysql://localhost/emma_store";
    static final String DB_URL = "jdbc:derby://localhost:1527/emma_store";
   
    // Database credentials
    private static final String USER = "emma";
    private static final String PASS = "admin";
    
    private static Connection conn;
    
    public ConnectionDB()
    {
    }
    
    public static Connection getInstance()
    {
        if(conn != null)
            return conn;
        
        try{
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch(SQLException ex){
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }
}
