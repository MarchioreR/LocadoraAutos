/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.locadoraauto.interfaces;

import com.mycompany.locadoraauto.models.Alugador;
import com.mycompany.locadoraauto.models.Automovel;
import com.mycompany.locadoraauto.models.Contrato;
import com.mycompany.locadoraauto.models.Locador;
import com.mycompany.locadoraauto.models.Montadora;
import com.mycompany.locadoraauto.models.Obtencao;
import com.mycompany.locadoraauto.models.RegistroFinanceiro;
import com.mycompany.locadoraauto.models.Seguro;
import com.mycompany.locadoraauto.models.Usuario;
import com.mycompany.locadoraauto.models.Vendedor;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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

    public void CriarMontadora(Montadora montadora) throws RemoteException;

    public ArrayList<Automovel> listarAutomoveis() throws RemoteException;

    public ArrayList<Usuario> listarUsuarios() throws RemoteException;

    public void adicionarRegistroFinanceiro(RegistroFinanceiro registro) throws RemoteException;

    public void adicionarObtencao(Obtencao obtencao) throws RemoteException;

    public void adicionarAutomovel(Automovel automovel) throws RemoteException;

    public void inserirVenda(int idAutomovel, int idVendedor, double valorVenda) throws RemoteException, SQLException;

    public void adicionarMontadora(Montadora mont) throws RemoteException;

    public void adicionarVendedor(Vendedor vendedor) throws RemoteException;

    public void inserirSeguro(String tipoSeguro, double valorSeguro, String seguradora) throws SQLException, RemoteException;

    public void inserirRegistroFinanceiro(RegistroFinanceiro r) throws SQLException, RemoteException;

    public void inserirObtencao(Obtencao obt) throws SQLException, RemoteException;

    public void inserirLocacao(int idContrato, int idAutomovel, int idLocador) throws SQLException, RemoteException;

    public void inserirContrato(Contrato cont) throws SQLException, RemoteException;

    public void insertUsuario(Usuario usuario) throws SQLException, RemoteException;

    public ArrayList<Usuario> carregarUsuariosDoBanco() throws SQLException, RemoteException;

    public void InserirRegistro(RegistroFinanceiro r) throws SQLException, RemoteException;

    public void InserirAuto(Automovel novo) throws SQLException, RemoteException;

    public void AddDataCad(JTable jTable1, JScrollPane jScrollPane1, ArrayList<Automovel> novalista) throws SQLException, RemoteException;
}
