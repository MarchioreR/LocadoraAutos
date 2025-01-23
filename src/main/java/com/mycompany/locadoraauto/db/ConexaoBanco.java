/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    private static final String URL = "jdbc:mysql://localhost:3306/LocadoraAuto"; // URL do banco de dados
    private static final String USUARIO = "VitorPC"; // Usuário do banco de dados
    private static final String SENHA = "765627vitor"; // Senha do banco de dados

    /**
     * Estabelece a conexão com o banco de dados.
     * 
     * @return Um objeto Connection ou null em caso de falha.
     */
    public static Connection conectar() {
        try {
            // Estabelecer a conexão com o banco de dados
            var conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão estabelecida com sucesso!");
            return conexao;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados!");
            e.printStackTrace();
            return null;
        }
    }
}
