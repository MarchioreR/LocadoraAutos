/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.locadoraauto;

import com.mycompany.locadoraauto.interfaces.Interface;
import com.mycompany.locadoraauto.interfaces.InterfaceImp;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vitor
 */
public class LocadoraAuto {

    public static void main(String[] args) {
        try {

            Interface ci = new InterfaceImp();
            String nome = "rmi://26.210.206.180/LocadoraAuto";
            System.out.println("Registrando objeto no RMI Registry...");
            LocateRegistry.createRegistry(1099);
            Naming.rebind(nome, ci);
            System.out.println("Servidor pronto.");

        } catch (RemoteException e) {
        } catch (MalformedURLException ex) {
            Logger.getLogger(LocadoraAuto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
