/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.util;

import java.awt.Component;
import java.rmi.RemoteException;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author vitor
 */
public class UtilityView {
    
    public boolean VerificarVazio(JPanel panel) throws RemoteException{
        boolean vazio = false;

        for (Component component : panel.getComponents()) {
            if (component instanceof JTextField textField) {
                if (textField.getText().trim().isEmpty()) { // Check if the field is empty
                    vazio = true;
                    break; // Exit loop if an empty field is found
                }
            }
        }
        return vazio;
    }

    public void Redefinir(JPanel panel)  throws RemoteException{
        for (Component componente : panel.getComponents()){
            if (componente instanceof JTextField jTextField) {
                jTextField.setText("");
            }
        }
    }
    
}
