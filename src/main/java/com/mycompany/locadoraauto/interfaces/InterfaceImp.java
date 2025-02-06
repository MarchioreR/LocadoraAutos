/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.locadoraauto.interfaces;

import com.mycompany.locadoraauto.dao.DataAccessObject;
import com.mycompany.locadoraauto.enums.TipoID;
import com.mycompany.locadoraauto.enums.TipoSeguro;
import com.mycompany.locadoraauto.enums.TipoStatus;
import com.mycompany.locadoraauto.enums.TipoVeiculo;
import com.mycompany.locadoraauto.models.Alugador;
import com.mycompany.locadoraauto.models.Automovel;
import com.mycompany.locadoraauto.models.Contrato;
import com.mycompany.locadoraauto.models.Locador;
import com.mycompany.locadoraauto.models.Montadora;
import com.mycompany.locadoraauto.models.Obtencao;
import com.mycompany.locadoraauto.models.RegistroFinanceiro;
import com.mycompany.locadoraauto.models.Seguro;
import com.mycompany.locadoraauto.models.Usuario;
import com.mycompany.locadoraauto.models.Venda;
import com.mycompany.locadoraauto.models.Vendedor;
import java.awt.Component;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Implementation of the remote interface. Provides methods to manage various entities in the system.
 */
public class InterfaceImp extends UnicastRemoteObject implements Interface {

    private ArrayList<Venda> vendas = new ArrayList<>();
    private ArrayList<RegistroFinanceiro> registros = new ArrayList<>();
    private ArrayList<Automovel> automoveis = new ArrayList<>();
    private ArrayList<Contrato> contratos = new ArrayList<>();
    private ArrayList<Obtencao> obtencoes = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    // Constructor
    public InterfaceImp() throws RemoteException {
        super(); // Call the parent class constructor
    }

    public void CriarMontadora(Usuario montadora) {
        usuarios.add(montadora);
        try {
            DataAccessObject.insertUsuario(montadora);
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CriarContrato(int currentID, Alugador alugador, Locador locador, Seguro seguro, float valorContrato, Automovel automovel) throws RemoteException {
        int idContrato = currentID;
        LocalDate dataIn = LocalDate.now();
        LocalDate dataTer = LocalDate.now().plusDays(3);

        Contrato contrato = new Contrato(idContrato, alugador, dataIn, dataTer, valorContrato, locador, automovel);
        contrato.setSeguro(seguro);
        contratos.add(contrato);
        try {
            DataAccessObject.inserirContrato(contrato);
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Contrato criado");
    }

    public void CriarObtencao(int currentID, Automovel automovel, Montadora montadora, float valorObt) throws RemoteException {
        Date dataObt = new Date();
        Obtencao obtencao = new Obtencao(currentID, automovel, montadora, dataObt, valorObt);
        obtencoes.add(obtencao);
    }

    public void CriarRegistro(RegistroFinanceiro r) throws RemoteException {
        registros.add(r);

        try {
            DataAccessObject.inserirRegistroFinanceiro(r);
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int UsuarioAtual() throws RemoteException {
        int id = usuarios.size() - 1;
        return id;
    }

    public int AutoAtual() throws RemoteException {
        int id = automoveis.size() - 1;
        return id;
    }

    public int ContratoAtual() throws RemoteException {
        int id = contratos.size() - 1;
        return id;
    }

    public int RegistroAtual() throws RemoteException {
        int id = registros.size() - 1;
        return id;
    }

    public synchronized boolean Devolver(int id) throws RemoteException {
        if (id <= automoveis.size() && automoveis.get(id).getStatus() == TipoStatus.INDISPONIVEL) {
            automoveis.get(id).setStatus(TipoStatus.DISPONIVEL);
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean Emprestar(int id) throws RemoteException {
        if (id <= automoveis.size() && TipoStatus.DISPONIVEL == automoveis.get(id).getStatus()) {
            automoveis.get(id).setStatus(TipoStatus.INDISPONIVEL);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Automovel> PassarAutomoveis() throws RemoteException {
        return automoveis;
    }

    public Automovel GetAutoAtPOS(int idx) throws RemoteException {
        return automoveis.get(idx);
    }

    public Usuario buscarUsuario(String nomeOuID) throws RemoteException {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nomeOuID) || usuario.getID().equals(nomeOuID)) {
                return usuario; // Retorna o primeiro usuário encontrado
            }
        }
        return null; // Retorna null se nenhum usuário for encontrado
    }

    public Usuario GetLocAtPOS(int idx) throws RemoteException {
        if (idx < usuarios.size() && idx >= 0) {
            return usuarios.get(idx);
        }
        return null;

    }

    @Override
    public void CriarMontadora(Montadora user) throws RemoteException {
        usuarios.add(user);

        DataAccessObject.adicionarMontadora(user);
    }
}
