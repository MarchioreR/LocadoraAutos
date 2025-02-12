/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.locadoraauto.interfaces;

import com.mycompany.locadoraauto.enums.TipoID;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vitor
 */
public interface Interface extends Remote {

    public void AddDataCad(JTable jTable1, JScrollPane jScrollPane1, ArrayList<Automovel> novalista) throws SQLException, RemoteException;

    public void AddDataRegistro(JTable jTable1, JScrollPane jScrollPane1, ArrayList<RegistroFinanceiro> novalista) throws SQLException, RemoteException;

    public void CriarContrato(int currentID, Alugador alugador, Locador locador, Seguro seguro, float valorContrato, Automovel automovel) throws RemoteException;

    public void CriarMontadora(Montadora montadora) throws RemoteException;

    public void CriarObtencao(int currentID, Automovel automovel, Montadora montadora, float valorObt) throws RemoteException;

    public void CriarRegistro(RegistroFinanceiro r) throws RemoteException;

    public boolean Devolver(int id) throws RemoteException;

    public Automovel GetAutoAtPOS(int idx) throws RemoteException;

    public Alugador GetAlugAtPOS(int idx) throws RemoteException;

    public Locador GetLocAtPOS(int idx) throws RemoteException;

    public Usuario GetUserAtPOS(int idx) throws RemoteException;

    public void InserirAuto(Automovel novo) throws SQLException, RemoteException;

    public void InserirRegistro(RegistroFinanceiro r) throws SQLException, RemoteException;

    public void adicionarAlugador(Alugador alug) throws RemoteException;

    public void adicionarAutomovel(Automovel automovel) throws RemoteException;

    public void adicionarLocador(Locador loc) throws RemoteException;

    public void adicionarMontadora(Montadora mont) throws RemoteException;

    public void adicionarObtencao(Obtencao obtencao) throws RemoteException;

    public void adicionarRegistroFinanceiro(RegistroFinanceiro registro) throws RemoteException;

    public void adicionarVendedor(Vendedor vendedor) throws RemoteException;

    public boolean Emprestar(int id) throws RemoteException;

    public int UsuarioAtual() throws RemoteException, SQLException;

    public int AutoAtual() throws RemoteException, SQLException;

    public int ContratoAtual() throws RemoteException, SQLException;

    public int RegistroAtual() throws RemoteException, SQLException;

    public int SeguroAtual() throws RemoteException, SQLException;

    public Usuario buscarUsuario(String nome, ArrayList<Usuario> lista) throws RemoteException;

    public Alugador buscarAlugador(String nome, ArrayList<Usuario> lista) throws RemoteException;

    public Locador buscarLocador(String nome, ArrayList<Usuario> lista) throws RemoteException;

    public ArrayList<Usuario> carregarUsuariosDoBanco() throws SQLException, RemoteException;

    public void inserirContrato(Contrato cont) throws SQLException, RemoteException;

    public void inserirLocacao(int idContrato, int idAutomovel, int idLocador) throws SQLException, RemoteException;

    public void inserirObtencao(Obtencao obt) throws SQLException, RemoteException;

    public void inserirRegistroFinanceiro(RegistroFinanceiro r) throws SQLException, RemoteException;

    public void InserirSeguro(Seguro seguro) throws SQLException, RemoteException;

    public void inserirVenda(int idAutomovel, int idVendedor, double valorVenda) throws RemoteException, SQLException;

    public void insertUsuario(Usuario usuario) throws SQLException, RemoteException;

    public ArrayList<Automovel> listarAutomoveis() throws RemoteException;

    public ArrayList<RegistroFinanceiro> listarRegistros(ArrayList<Automovel> auto) throws RemoteException;

    public ArrayList<Usuario> listarUsuariosNovo() throws RemoteException;

    public String getUserType(int idUsuario, Connection conn) throws SQLException, RemoteException;

    public Alugador getAlugadorData(int idUsuario, Connection conn, String nome, TipoID tipoID, String ID, String email, String numCel, String endereco) throws RemoteException, SQLException;

    public Vendedor getVendedorData(int idUsuario, Connection conn, String nome, TipoID tipoID, String ID, String email, String numCel, String endereco) throws RemoteException, SQLException;

    public Montadora getMontadoraData(int idUsuario, Connection conn, String nome, TipoID tipoID, String ID, String email, String numCel, String endereco) throws RemoteException, SQLException;

    public Locador getLocadorData(int idUsuario, Connection conn, String nome, TipoID tipoID, String ID, String email, String numCel, String endereco) throws RemoteException, SQLException;

    public ArrayList<Automovel> PassarAutomoveis() throws RemoteException;

    public void adicionarSeguro(Seguro seguro) throws RemoteException;

    public void updateStatus(int idAutomovel, String novoStatus) throws SQLException, RemoteException;
}
