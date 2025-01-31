/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto;
import com.mycompany.locadoraauto.view.FMenu;
import com.mycompany.locadoraauto.interfaces.Interface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author vitor
 */
public class Cliente {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Interface Locadora = (Interface) registry.lookup("Locadora");

            Scanner scanner = new Scanner(System.in);
            FMenu x = new FMenu();
            x.setVisible(true);
            System.out.println("OLA");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
