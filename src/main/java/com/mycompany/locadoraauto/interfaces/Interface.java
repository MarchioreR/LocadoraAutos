/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.locadoraauto.interfaces;

import com.mycompany.locadoraauto.models.Alugador;
import com.mycompany.locadoraauto.models.Automovel;
import com.mycompany.locadoraauto.models.Locador;
import com.mycompany.locadoraauto.models.Montadora;
import com.mycompany.locadoraauto.models.RegistroFinanceiro;
import com.mycompany.locadoraauto.models.Seguro;
import com.mycompany.locadoraauto.models.Usuario;
import com.mycompany.locadoraauto.models.Venda;
import java.rmi.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JPanel;

/**
 *
 * @author vitor
 */
public interface Interface extends Remote {

   
    public void CriarContrato(int currentID, Alugador alugador, Locador locador, Seguro seguro, float valorContrato, Automovel automovel) throws RemoteException;

    public void CriarObtencao(int currentID, Automovel automovel, Montadora montadora, float valorObt) throws RemoteException;

    public void CriarRegistro(RegistroFinanceiro r) throws RemoteException;

    public int UsuarioAtual() throws RemoteException;

    public int AutoAtual() throws RemoteException;

    public int ContratoAtual() throws RemoteException;

    public int RegistroAtual() throws RemoteException;

    public boolean Devolver(int id) throws RemoteException;

    public boolean Emprestar(int id) throws RemoteException;

    public ArrayList<Automovel> PassarAutomoveis() throws RemoteException;

    public Automovel GetAutoAtPOS(int idx) throws RemoteException;

    public Usuario buscarUsuario(String nomeOuID) throws RemoteException;

    public void CriarMontadora(Montadora user) throws RemoteException;

}
