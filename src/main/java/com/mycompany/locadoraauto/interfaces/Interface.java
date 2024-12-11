/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.locadoraauto.interfaces;
import com.mycompany.locadoraauto.models.Usuario;
import java.rmi.*;
import java.rmi.Remote;

/**
 *
 * @author vitor
 */
public interface Interface {
    public void CriarLocador() throws RemoteException;
    public void CriarAlugador() throws RemoteException;
    public void CriarMontadora() throws RemoteException;
    public void CriarVendedor() throws RemoteException;
    public void CriarAutomovel() throws RemoteException;
    public void CriarSeguro() throws RemoteException;
    public void CriarContrato() throws RemoteException;
    public void CriarLocacao() throws RemoteException;
    public void CriarObtencao() throws RemoteException;
    public void CriarRegistro() throws RemoteException;
    public void CriarVenda() throws RemoteException;
    
    public void AlterarAutomovel() throws RemoteException;
    public void AlterarLocador() throws RemoteException;
    public void AlterarVendedor() throws RemoteException;
    public void AlterarMontadora() throws RemoteException;
    public void AlterarAlugador() throws RemoteException;
    
    public void RemoverAutomovel() throws RemoteException;
    public void RemoverLocador() throws RemoteException;
    public void RemoverVendedor() throws RemoteException;
    public void RemoverAlugador() throws RemoteException;
    public void RemoverMontadora() throws RemoteException;
    
    public void ImprimirLocador() throws RemoteException;
    public void ImprimirAlugador() throws RemoteException;
    public void ImprimirMontadora() throws RemoteException;
    public void ImprimirVendedor() throws RemoteException;
    public void ImprimirAutomovel() throws RemoteException;
    public void ImprimirSeguro() throws RemoteException;
    public void ImprimirContrato() throws RemoteException;
    public void ImprimirLocacao() throws RemoteException;
    public void ImprimirObtencao() throws RemoteException;
    public void ImprimirRegistro() throws RemoteException;
    public void ImprimirVenda() throws RemoteException;
    public void ImprimirAutomovel() throws RemoteException;
}
