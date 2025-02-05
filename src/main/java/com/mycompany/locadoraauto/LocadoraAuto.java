/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.locadoraauto;

import com.mycompany.locadoraauto.interfaces.Interface;
import com.mycompany.locadoraauto.interfaces.InterfaceImp;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author vitor
 */
public class LocadoraAuto {

    public static void main(String[] args) {
        try {
            Interface servidor = new InterfaceImp();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("rmi://26.210.206.180/LocadoraAuto", servidor);
            System.out.println("Servidor pronto.");
            /*ControllerInterface ci = new ControllerMain();
            String nome = Utility.getServerIp();

            System.out.println("Registrando objeto no RMI Registry...");
            LocateRegistry.createRegistry(1099);
            Naming.rebind(nome, ci);*/

        } catch (RemoteException e) {
        }
    }

}
