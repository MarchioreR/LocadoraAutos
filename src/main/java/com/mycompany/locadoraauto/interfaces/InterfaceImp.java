/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.locadoraauto.interfaces;

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
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Implementation of the remote interface. Provides methods to manage various
 * entities in the system.
 */
public class InterfaceImp extends UnicastRemoteObject implements Interface {

    private List<Venda> vendas = new ArrayList<>();
    private List<RegistroFinanceiro> registros = new ArrayList<>();
    private List<Automovel> automoveis = new ArrayList<>();
    private List<Contrato> contratos = new ArrayList<>();
    private List<Obtencao> obtencoes = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    

    // Constructor
    public InterfaceImp() throws RemoteException {
        super(); // Call the parent class constructor
    }

    public void CriarUsuario(Usuario novo) {
        String nome;
        TipoID tipoID = null;
        String email;
        String numCel;
        String endereco;
        int opcao;
        Scanner scan = new Scanner(System.in);

        System.out.println("Nome: ");
        nome = scan.nextLine();
        novo.setNome(nome);

        System.out.println("Email: ");
        email = scan.nextLine();
        novo.setEmail(email);

        System.out.println("Endereço: ");
        endereco = scan.nextLine();
        novo.setEndereco(endereco);

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
                ((Usuario) novo).setTipoID(tipoID.CPF);
            case 2 ->
                ((Usuario) novo).setTipoID(tipoID.CNPJ);
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
        usuarios.add(novo);
    }

    @Override
    public void CriarAlugador(int currentID) {
        // Implementation logic here
        String endereco;

        Usuario novo = new Alugador();
        Scanner scan = new Scanner(System.in);

        System.out.println("Cadastrar Cliente: ");

        CriarUsuario(novo);

        ((Alugador) novo).setIdAlugador(currentID);
        usuarios.add(novo);
    }

    @Override
    public void CriarMontadora(int currentID) {
        // Implementation logic here
        String endereco;

        Usuario novo = new Montadora();
        Scanner scan = new Scanner(System.in);

        System.out.println("Cadastrar montadora: ");

        CriarUsuario(novo);

        ((Montadora) novo).setIdMontadora(currentID);
        usuarios.add(novo);
    }

    @Override
    public void CriarVendedor(int currentID) {
        // Implementation logic here
        float valorSalario;
        float comissaoVenda;

        Usuario novo = new Vendedor();
        Scanner scan = new Scanner(System.in);

        System.out.println("Cadastrar vendedor: ");

        CriarUsuario(novo);

        System.out.println("Valor do salário: ");
        valorSalario = scan.nextFloat();
        ((Vendedor) novo).setValorSalario(valorSalario);

        System.out.println("Valor da comissão: ");
        comissaoVenda = scan.nextFloat();
        ((Vendedor) novo).setComissaoVenda(comissaoVenda);

        ((Vendedor) novo).setIdVendedor(currentID);
        usuarios.add(novo);
    }

    @Override
    public void CriarAutomovel(int currentID) {
        // Implementation logic here
        String modelo;
        String placa;
        int tipoveiculo;
        TipoVeiculo tipoVeic;
        float valorDia;
        int tipostatus;
        TipoStatus status;

        Automovel automovel = new Automovel();

        Scanner scan = new Scanner(System.in);

        System.out.println("Cadastrar automóvel: ");

        System.out.println("Modelo: ");
        modelo = scan.nextLine();

        System.out.println("Placa: ");
        placa = scan.nextLine();

        do {
            System.out.println("Tipo de Veículo: (1) Híbrido (2) Elétrico (3) Combustão");
            tipoveiculo = scan.nextInt();
        } while (tipoveiculo < 1 || tipoveiculo > 3);

        switch (tipoveiculo) {
            case 1 ->
                tipoVeic = TipoVeiculo.HIBRIDO;
            case 2 ->
                tipoVeic = TipoVeiculo.ELETRICO;
            case 3 ->
                tipoVeic = TipoVeiculo.COMBUSTAO;
            default ->
                throw new AssertionError();
        }

        System.out.println("Valor da diária: ");
        valorDia = scan.nextFloat();

        do {
            System.out.println("Tipo de Veículo: (1) Disponível (2) Indisponível (3) Manutenção");
            tipostatus = scan.nextInt();
        } while (tipostatus < 1 || tipostatus > 3);

        switch (tipostatus) {
            case 1 ->
                status = TipoStatus.DISPONIVEL;
            case 2 ->
                status = TipoStatus.INDISPONIVEL;
            case 3 ->
                status = TipoStatus.MANUTENCAO;
            default ->
                throw new AssertionError();
        }

        automovel.setIdAutomovel(currentID);
        automovel.setModelo(modelo);
        automovel.setPlaca(placa);
        automovel.setStatus(status);
        automovel.setTipoVeic(tipoVeic);
        automovel.setValorDia(valorDia);

        automoveis.add(automovel);
        System.out.println("Automóvel criado: " + automovel.getModelo());
    }

    @Override
    public Seguro CriarSeguro() {
        Scanner scan = new Scanner(System.in);
        int tiposeguro;
        TipoSeguro tipoSeguro;
        float valorSeguro;
        String seguradora;
        
        Seguro seguro = new Seguro();
        
        System.out.println("Nome da Seguradora: ");
        seguradora = scan.nextLine();
        
        do {
            System.out.println("Tipo Seguro: (1) Básico (2) Intermediário (3) Avançado");
            tiposeguro = scan.nextInt();
        } while (tiposeguro < 1 || tiposeguro > 3);

        switch (tiposeguro) {
            case 1 ->
                tipoSeguro = TipoSeguro.BASICO;
            case 2 ->
                tipoSeguro = TipoSeguro.INTERMEDIARIO;
            case 3 ->
                tipoSeguro = TipoSeguro.AVANCADO;
            default ->
                throw new AssertionError();
        }
        
        System.out.println("Valor do Seguro: ");
        valorSeguro = scan.nextFloat();
        
        seguro.setSeguradora(seguradora);
        seguro.setTipoSeguro(tipoSeguro);
        seguro.setValorSeguro(valorSeguro);
        return seguro;
    }

    @Override
    public void CriarContrato(int currentID) {
        // Implementation logic here
    }

    @Override
    public void CriarObtencao(int currentID) {
        // Implementation logic here
    }

    @Override
    public void CriarRegistro(int currentID) {
        Scanner scan = new Scanner(System.in);

        // Coletando dados para criar o registro financeiro
        System.out.println("Cadastrar Registro Financeiro:");

        System.out.print("ID do Automóvel: ");
        int idAutomovel = scan.nextInt();
        Automovel automovel = buscarAutomovel(idAutomovel); // Supondo que já tenha um método para buscar o automóvel

        System.out.print("Valor de Compra: ");
        float valorCompra = scan.nextFloat();

        System.out.print("Valor de Venda: ");
        float valorVenda = scan.nextFloat();

        System.out.print("Valor da Diária: ");
        float valorDiaria = scan.nextFloat();

        System.out.print("Valor de Manutenção: ");
        float valorManutencao = scan.nextFloat();

        // Calculando o valor total
        float valorTotal = valorCompra + valorManutencao;

        // Criando o registro financeiro
        RegistroFinanceiro registro = new RegistroFinanceiro(currentID, automovel, valorCompra, valorVenda, valorDiaria, valorManutencao, valorTotal);
        registros.add(registro);

        System.out.println("Registro financeiro criado para o veículo: " + automovel.getModelo());
    }

    @Override
    public void CriarVenda(int currentID) {
        Scanner scan = new Scanner(System.in);

        // Coletando dados para criar a venda
        System.out.println("Cadastrar Venda:");

        System.out.print("ID do Automóvel: ");
        int idAutomovel = scan.nextInt();

        System.out.print("ID do Vendedor: ");
        int idVendedor = scan.nextInt();

        System.out.print("Valor da Venda: ");
        int valorVenda = scan.nextInt();

        // Criando a venda
        Venda venda = new Venda(currentID, idAutomovel, idVendedor, valorVenda);
        vendas.add(venda);

        System.out.println("Venda registrada: " + venda.getIdVenda());
    }

    private Automovel buscarAutomovel(int idAutomovel) {
        // Método fictício para buscar o automóvel na lista de automóveis
        for (Automovel automovel : automoveis) {
            if (automovel.getIdAutomovel() == idAutomovel) {
                return automovel;
            }
        }
        return null; // Retorna null se o automóvel não for encontrado
    }

    @Override
    public void AlterarAutomovel() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void AlterarLocador() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void AlterarVendedor() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void AlterarMontadora() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void AlterarAlugador() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void RemoverAutomovel() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void RemoverLocador() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void RemoverVendedor() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void RemoverAlugador() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void RemoverMontadora() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void ImprimirContrato() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void ImprimirObtencao() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void ImprimirRegistro() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void ImprimirVenda() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
