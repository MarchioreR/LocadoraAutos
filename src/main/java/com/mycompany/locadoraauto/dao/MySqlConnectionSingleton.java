/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vitor
 */
public class MySqlConnectionSingleton {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://26.210.206.180/LocadoraAuto";
    private static final String TIMEZONE = "useTimezone=true&serverTimezone=UTC";
    private static final String USER = "cliente";
    private static final String PASS = "ALFABET0!";

    private Connection conn;
    private static MySqlConnectionSingleton instance = null;

    private MySqlConnectionSingleton() {
        String connect = URL + "?" + TIMEZONE;
        try {
            Class.forName(DRIVER);
            System.out.println("Criou DRIVER!");
            conn = DriverManager.getConnection(connect, USER, PASS);
            System.out.println("Criou conn");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar ao banco de dados!");
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConn() {
        return conn;
    }

    public static MySqlConnectionSingleton getInstance() throws SQLException {
        if (instance == null) {
            instance = new MySqlConnectionSingleton();
            System.out.println("Criou instance 1");
        }

        if (instance.getConn().isClosed()) {
            instance = new MySqlConnectionSingleton();
            System.out.println("Criou instance 2");
        }

        return instance;
    }
}
