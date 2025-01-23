/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionSingleton {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/LocadoraAuto";
    private static final String PORT = "3306";
    private static final String USER = "root";
    private static final String PASS = "765627Vitor";
    private static final String DATABASE = "LocadoraAuto";
    private static final String TIMEZONE = "useTimezone=true&serverTimezone=UTC";
    
    private Connection conn;
    private static MySqlConnectionSingleton instance = null;
    
    private MySqlConnectionSingleton() {
        String connect = URL + ":" + PORT + "/" + DATABASE + "?" + TIMEZONE;
        
        try{
            Class.forName(DRIVER);
            System.out.println("Criou DRIVER!");
            conn = DriverManager.getConnection(connect,USER,PASS);
            System.out.println("Criou conn");
        }catch (Exception ex) {
            System.out.println("Erro ao conectar ao banco de dados!");
            System.out.println(ex.getMessage());
        }
    }
    
    public Connection getConn() {
        return conn;
    }
    
    public static MySqlConnectionSingleton getInstance() throws SQLException{
        if(instance == null){
            instance = new MySqlConnectionSingleton();
            System.out.println("Criou instance 1");
        }
       
        if (instance.getConn().isClosed()){
            instance = new MySqlConnectionSingleton();
            System.out.println("Criou instance 2");
        }
            
        
        return instance;
    }
}
