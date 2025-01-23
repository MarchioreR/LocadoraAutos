/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.factory;

import com.mycompany.locadoraauto.db.MySqlConnectionSingleton;
import java.sql.Connection;
import java.sql.SQLException;

public class FactoryConnection {
    public static Connection createConnection() throws SQLException{
        return (Connection) MySqlConnectionSingleton.getInstance().getConn();
    }
}
