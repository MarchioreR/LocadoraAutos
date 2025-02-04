/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.controller;

import com.mycompany.locadoraauto.dao.DataAccessObject;
import static com.mycompany.locadoraauto.dao.DataAccessObject.getAutomoveis;
import com.mycompany.locadoraauto.interfaces.Interface;
import com.mycompany.locadoraauto.models.Automovel;
import com.mycompany.locadoraauto.view.FMenu;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vitor
 */
public class Controller {

    DefaultTableModel model;

    public Controller() {
        model = new DefaultTableModel(new String[]{"Modelo", "Tipo", "Valor Diaria", "Status"}, 0);
    }

    public final void AddDataCad(JTable jTable1, JScrollPane jScrollPane1) throws SQLException {
        ArrayList<Automovel> automoveis = getAutomoveis();
        jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);

        try {
            String[] linha = new String[4]; // Array to hold data for each row
            Automovel aux = null; // Temporary variable to hold each Automovel object

            // Loop through the ArrayList of Automovel objects
            for (int i = 0; i < automoveis.size(); i++) {
                aux = automoveis.get(i); // Get the current Automovel object

                // Populate the row with data from the Automovel object
                linha[0] = aux.getModelo(); // modelo
                linha[1] = aux.getTipoVeic().getDescricao(); // tipo_veiculo (enum value as String)
                linha[2] = String.valueOf(aux.getValorDia()); // valor_dia (converted to String)
                linha[3] = aux.getStatus().getDescricao(); // status (enum value as String)

                // Add the row to the JTable model
                model.addRow(linha);
            }
        } catch (Exception ex) {
            Logger.getLogger(FMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final void InserirAuto(Automovel novo, float valorCompra) throws SQLException {
        DataAccessObject.inserirAutos(novo.getIdAutomovel(), novo.getModelo(), novo.getPlaca(), novo.getTipoVeic(), novo.getValorDia(), novo.getStatus());
        DataAccessObject.inserirRegistroFinanceiro(novo.getIdAutomovel(), valorCompra, 0, novo.getValorDia(), 0, (valorCompra + novo.getValorDia()));
    }
}
