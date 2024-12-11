/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.locadoraauto.interfaces;

import com.mycompany.locadoraauto.enums.TipoID;
import com.mycompany.locadoraauto.models.Alugador;
import com.mycompany.locadoraauto.models.Locador;
import com.mycompany.locadoraauto.models.Montadora;
import com.mycompany.locadoraauto.models.Usuario;
import com.mycompany.locadoraauto.models.Vendedor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * Implementation of the remote interface. Provides methods to manage various
 * entities in the system.
 */
public class InterfaceImp extends UnicastRemoteObject implements Interface {

    // Constructor
    public InterfaceImp() throws RemoteException {
        super(); // Call the parent class constructor
    }

    public void CriarUsuario(Usuario novo) {
        String nome;
        TipoID tipoID = null;
        String email;
        String numCel;
        int opcao;
        Scanner scan = new Scanner(System.in);

        System.out.println("Nome: ");
        nome = scan.nextLine();
        novo.setNome(nome);

        System.out.println("Email: ");
        email = scan.nextLine();
        novo.setEmail(email);

        System.out.println("Nùmero de contato: ");
        numCel = scan.nextLine();
        novo.setNumCel(numCel);

        do {
            System.out.print("Tipo de Identificação:\n\t1 para CPF;\n\t2 para CNPJ;\n");
            opcao = scan.nextInt();
            scan.nextLine();
            if (opcao < 1 || opcao > 2) {
                System.out.println("Opção inválida!");
            }
        } while (opcao < 1 || opcao > 2);
        switch (opcao) {
            case 1 ->
                ((Usuario) novo).setTipo(tipoID.CPF);
            case 2 ->
                ((Usuario) novo).setTipo(tipoID.CNPJ);
        }
    }

    @Override
    public void CriarLocador(int currentID) {
        // Implementation logic here
        Usuario novo = new Locador();
        Scanner scan = new Scanner(System.in);

        System.out.println("Cadastrar Locador: ");

        float valorSalario;
        float comissaoLoc;

        CriarUsuario(novo);
                
        System.out.println("Valor do salário: ");
        valorSalario = scan.nextFloat();
        ((Locador) novo).setValorSalario(valorSalario);

        System.out.println("Valor da comissão: ");
        comissaoLoc = scan.nextFloat();
        ((Locador) novo).setComissaoLoc(comissaoLoc);

        ((Locador) novo).setIdLocador(currentID);
    }

    @Override
    public void CriarAlugador(int currentID) {
        // Implementation logic here
        String endereco;
        
        Usuario novo = new Alugador();
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Cadastrar Cliente: ");
        
        CriarUsuario(novo);
        
        System.out.println("Endereço: ");
        endereco = scan.nextLine();
        ((Alugador)novo).setEndereco(endereco);
        
        ((Alugador)novo).setIdAlugador(currentID);
    }

    @Override
    public void CriarMontadora(int currentID) {
        // Implementation logic here
        String endereco;
        
        Usuario novo = new Montadora();
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Cadastrar montadora: ");
        
        CriarUsuario(novo);
        
        System.out.println("Endereço: ");
        endereco = scan.nextLine();
        ((Montadora)novo).setEndereco(endereco);
        
        ((Montadora)novo).setIdMontadora(currentID);
    }

    @Override
    public void CriarVendedor(int currentID) {
        // Implementation logic here
        float valorSalario;
        float comissaoVenda;
        
        Usuario novo = new Vendedor();
        Scanner scan = Scanner(System.in);
        
        System.out.println("Cadastrar vendedor: ");
        
        CriarUsuario(novo);
        
        System.out.println("Valor do salário: ");
        valorSalario = scan.nextFloat();
        ((Vendedor)novo).setValorSalario(valorSalario);
        
        System.out.println("Valor da comissão: ");
        comissaoVenda = scan.nextFloat();
        ((Vendedor)novo).setComissaoVenda(comissaoVenda);
        
        ((Vendedor)novo).setIdVendedor(currentID);
    }

    @Override
    public void CriarAutomovel(int currentID) {
        // Implementation logic here
    }

    @Override
    public void CriarSeguro(int currentID) {
        // Implementation logic here
    }

    @Override
    public void CriarContrato(int currentID) {
        // Implementation logic here
    }

    @Override
    public void CriarLocacao(int currentID) {
        // Implementation logic here
    }

    @Override
    public void CriarObtencao(int currentID) {
        // Implementation logic here
    }

    @Override
    public void CriarRegistro(int currentID) {
        // Implementation logic here
    }

    @Override
    public void CriarVenda(int currentID) {
        // Implementation logic here
    }
}
