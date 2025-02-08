/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto;

import com.mycompany.locadoraauto.view.FMenu;
import com.mycompany.locadoraauto.interfaces.Interface;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author VITOR MARCHIORE LOPES
 */
public class Cliente {

    public Cliente() {
        
    }


    public static void main(String[] args) {
        try {
            Registry r = LocateRegistry.getRegistry("26.210.206.180", 1099);
            Interface locadora = (Interface) r.lookup("Ola");
            System.out.println("OLA");
            FMenu x = new FMenu(locadora);
            x.setVisible(true);
        } catch (NotBoundException | RemoteException e) {

        }

    }
}
