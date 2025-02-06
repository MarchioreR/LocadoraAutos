/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.locadoraauto;

import com.mycompany.locadoraauto.interfaces.Interface;
import com.mycompany.locadoraauto.interfaces.InterfaceImp;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
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

    public static void main(String[] args) throws AlreadyBoundException {
        try {

            Interface ci = new InterfaceImp();
            System.out.println("Registrando objeto no RMI Registry...");
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Ola", ci);
            System.out.println("Servidor pronto.");

        } catch (RemoteException e) {
        }
    }

}
