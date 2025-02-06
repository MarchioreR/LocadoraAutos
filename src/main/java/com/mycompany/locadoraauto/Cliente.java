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
// Trabalho está incompleto, não consegui fazer funcionar o RMI, 
// a interface está incompleta e há algum problema ao cadastrar objetos.
// Infelizmente o código foi feito apenas por mim, 
// a minha dupla João Vitor não foi de muita ajuda na codificação, 
// mas trocamos algumas ideias no processo de estruturação do projeto,
// foram só algumas ideias pois é fácil de perceber que o código está uma bagunça
// pois queria fazer muitas coisas ao mesmo simultaneamente com pouco tempo
public class Cliente {

    public static void main(String[] args) {
        try {
            Registry r = LocateRegistry.getRegistry("26.210.206.180", 1099);
            Interface Locadora = (Interface) r.lookup("Ola");
            System.out.println("OLA");
            FMenu x = new FMenu(Locadora);
            x.setVisible(true);
        } catch (NotBoundException | RemoteException e) {
        }
    }
}
