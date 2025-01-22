/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.locadoraauto.interfaces;

import com.mycompany.locadoraauto.models.Automovel;
import com.mycompany.locadoraauto.models.RegistroFinanceiro;
import com.mycompany.locadoraauto.models.Seguro;
import com.mycompany.locadoraauto.models.Usuario;
import com.mycompany.locadoraauto.models.Venda;
import java.rmi.*;
import java.rmi.Remote;

/**
 *
 * @author vitor
 */
public interface Interface extends Remote {

    public void CriarUsuario(Usuario novo) throws RemoteException;

    public void CriarLocador(int currentID) throws RemoteException;

    public void CriarAlugador(int currentID) throws RemoteException;

    public void CriarMontadora(int currentID) throws RemoteException;

    public void CriarVendedor(int currentID) throws RemoteException;

    public void CriarAutomovel(int currentID) throws RemoteException;

    public Seguro CriarSeguro() throws RemoteException;

    public void CriarContrato(int currentID, int idAutomovel, int idAlugador, int idLocador) throws RemoteException;

    public void CriarObtencao(int currentID, int idAutomovel, int idMontadora, int idVendedor) throws RemoteException;

    public void CriarRegistro(int currentID) throws RemoteException;

    public void CriarVenda(int currentID, int idAutomovel, int idVendedor, int idComprador) throws RemoteException;

    public void AlterarAutomovel() throws RemoteException;

    public void AlterarUsuário(int opcao) throws RemoteException;

    public void AlterarLocador() throws RemoteException;

    public void AlterarVendedor() throws RemoteException;

    public void AlterarMontadora() throws RemoteException;

    public void AlterarAlugador() throws RemoteException;

    public void RemoverAutomovel() throws RemoteException;

    public void RemoverLocador() throws RemoteException;

    public void RemoverVendedor() throws RemoteException;

    public void RemoverAlugador() throws RemoteException;

    public void RemoverMontadora() throws RemoteException;

    public void ImprimirContrato() throws RemoteException;

    public void ImprimirObtencao() throws RemoteException;

    public void ImprimirRegistro() throws RemoteException;

    public void ImprimirVenda() throws RemoteException;

    public int BuscarAutomovel() throws RemoteException;

    public int BuscarUsuario() throws RemoteException;
}
