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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Implementation of the remote interface. Provides methods to manage various entities in the system.
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
        String email;
        String numCel;
        String endereco;
        String ID;
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
                ((Usuario) novo).setTipoID(TipoID.CPF);
            case 2 ->
                ((Usuario) novo).setTipoID(TipoID.CNPJ);
        }
        System.out.println("Identificação (" +  novo.getTipoID().getDescricao() + "): ");
        ID = scan.nextLine();
        novo.setID(ID);
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

        ((Locador) novo).setIdUsuario(currentID);
        usuarios.add(novo);
    }

    @Override
    public void CriarAlugador(int currentID) {
        Scanner scan = new Scanner(System.in);
        Usuario novo = new Alugador();
        int opt = 0;

        System.out.println("Cadastrar Cliente: ");

        CriarUsuario(novo);
        System.out.println("Idade do cliente: ");
        opt = scan.nextInt();
        ((Alugador) novo).setIdade(opt);

        do {
            System.out.println("Gênero do cliente (1) Masculino (2) Feminino (3) Outro: ");
            opt = scan.nextInt();
            if (opt > 3 || opt < 1) {
                System.out.println("Opção inválida, insira novamente.");
            }
        } while (opt > 3 || opt < 1);

        switch (opt) {
            case 1 ->
                ((Alugador) novo).setGenero("M");
            case 2 ->
                ((Alugador) novo).setGenero("F");
            case 3 ->
                ((Alugador) novo).setGenero("O");
            default ->
                throw new AssertionError();
        }

        ((Alugador) novo).setIdUsuario(currentID);
        usuarios.add(novo);
    }

    @Override
    public void CriarMontadora(int currentID) {
        Scanner scan = new Scanner(System.in);
        // Implementation logic here
        String website;
        String paisOrigem;

        Usuario novo = new Montadora();

        System.out.println("Cadastrar montadora: ");

        CriarUsuario(novo);

        System.out.println("Website da montadora: ");
        website = scan.nextLine();
        ((Montadora) novo).setWebsite(website);

        System.out.println("País da montadora: ");
        paisOrigem = scan.nextLine();
        ((Montadora) novo).setWebsite(paisOrigem);

        ((Montadora) novo).setIdUsuario(currentID);
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

        ((Vendedor) novo).setIdUsuario(currentID);
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

    public void CriarContrato(int currentID, int idAutomovel, int idAlugador, int idLocador) {
        Scanner scan = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        Seguro seguro = null;
        int idContrato = currentID;
        Date dataIn = null;
        Date dataTer = null;
        float valorContrato;
        String aux;

        do {
            System.out.println("Insira a data do início do contrato(formato dd/MM/yyyy)): ");
            aux = scan.nextLine();
            try {
                // Parse the input string into a Date object
                dataIn = dateFormat.parse(aux);
            } catch (ParseException e) {
                System.out.println("Formato de data Inválido. Use dd/MM/yyyy.");
            }
        } while (dataIn == null);

        do {
            System.out.println("Insira a data do fim do contrato(formato dd/MM/yyyy)): ");
            aux = scan.nextLine();
            try {
                // Parse the input string into a Date object
                dataTer = dateFormat.parse(aux);
            } catch (ParseException e) {
                System.out.println("Formato de data Inválido. Use dd/MM/yyyy.");
            }
        } while (dataTer == null);

        System.out.println("Valor do Contrato: ");
        valorContrato = scan.nextFloat();

        seguro = CriarSeguro();

        Contrato contrato = new Contrato(idContrato, idAlugador, dataIn, dataTer, valorContrato, idLocador, idAutomovel);
        contrato.setSeguro(seguro);
        contratos.add(contrato);
    }

    public void CriarObtencao(int currentID, int idAutomovel, int idMontadora, int idVendedor) {
        Date dataObt = null;
        float valorObt;
        String aux;
        Scanner scan = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        do {
            System.out.println("Insira a data do início do contrato(formato dd/MM/yyyy)): ");
            aux = scan.nextLine();
            try {
                // Parse the input string into a Date object
                dataObt = dateFormat.parse(aux);
            } catch (ParseException e) {
                System.out.println("Formato de data Inválido. Use dd/MM/yyyy.");
            }
        } while (dataObt == null);

        System.out.println("Insira o valor da compra: ");
        valorObt = scan.nextFloat();
        Obtencao obtencao = new Obtencao(currentID, idAutomovel, idMontadora, idVendedor, dataObt, valorObt);
        obtencoes.add(obtencao);
    }

    public void CriarRegistro(int currentID) {
        Scanner scan = new Scanner(System.in);
        int idx;
        // Coletando dados para criar o registro financeiro
        System.out.println("Cadastrar Registro Financeiro:");

        idx = BuscarAutomovel();
        int idVeiculo = idx;

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
        RegistroFinanceiro registro = new RegistroFinanceiro(currentID, idVeiculo, valorCompra, valorVenda, valorDiaria, valorManutencao, valorTotal);
        registros.add(registro);

        System.out.println("Registro financeiro criado para o veículo: " + automoveis.get(idx).getModelo());
    }

    public void CriarVenda(int currentID, int idAutomovel, int idVendedor, int idComprador) {
        Scanner scan = new Scanner(System.in);

        // Coletando dados para criar a venda
        System.out.println("Cadastrar Venda:");

        System.out.print("Valor da Venda: ");
        int valorVenda = scan.nextInt();

        // Criando a venda
        Venda venda = new Venda(currentID, idAutomovel, idVendedor, valorVenda, idComprador);
        vendas.add(venda);

        System.out.println("Venda registrada: " + venda.getIdVenda());
    }

    public int BuscarAutomovel() {
        int index = 0, opt, i = 0;
        Scanner scan = new Scanner(System.in);
        index = scan.nextInt();
        for (Automovel automovel : automoveis) {
            System.out.println("ID - " + i);
            automovel.ImprimirAutomovel();
            System.out.println("");
            i++;
        }
        do {
            System.out.println("Escolha o automóvel pelo ID da lista acima:");
            opt = scan.nextInt();
            if (opt > automoveis.size() || opt < 0) {
                System.out.println("Escolha inválida, tente novamente");
            }
        } while (opt > automoveis.size() || opt < 0);
        return index;
    }

    public void AlterarAutomovel() throws RemoteException {
        Scanner scan = new Scanner(System.in);
        int idx = 0, opt = 0, i = 0, enum_opt;
        Float diaria;
        String novo;

        idx = BuscarAutomovel();

        do {
            System.out.println("O que deseja alterar (1) Modelo (2) Placa (3) Tipo de Veiculo (4) Valor da diária  (5) Status");
            opt = scan.nextInt();
            if (opt > 5 || opt < 1) {
                System.out.println("Escolha inválida, tente novamente");
            }
        } while (opt < 1 || opt > 5);
        switch (opt) {
            case 1 -> {
                System.out.println("Escreva o nome do novo modelo: ");
                novo = scan.nextLine();
                automoveis.get(idx).setModelo(novo);
            }
            case 2 -> {
                System.out.println("Escreva o nome da nova placa: ");
                novo = scan.nextLine();
                automoveis.get(idx).setPlaca(novo);
            }
            case 3 -> {
                do {
                    System.out.println("Escolha o novo Status: (1) Disponível (2) Indisponível (3) Manutenção");
                    enum_opt = scan.nextInt();
                    if (enum_opt > 3 || enum_opt < 1) {
                        System.out.println("Escolha inválida, tente novamente");
                    }
                } while (enum_opt > 3 || enum_opt < 1);
                automoveis.get(idx).setStatus(TipoStatus.values()[enum_opt - 1]);
            }
            case 4 -> {
                do {
                    System.out.println("Escolha o novo tipo do veículo: (1) Híbrido (2) Elétrico (3) Combustão");
                    enum_opt = scan.nextInt();
                    if (enum_opt > 3 || enum_opt < 1) {
                        System.out.println("Escolha inválida, tente novamente");
                    }
                } while (enum_opt > 3 || enum_opt < 1);
                automoveis.get(idx).setTipoVeic(TipoVeiculo.values()[enum_opt - 1]);
            }
            case 5 -> {
                System.out.println("Escreva o novo valor da diária: ");
                diaria = scan.nextFloat();
                automoveis.get(idx).setValorDia(diaria);
            }
            default ->
                throw new AssertionError();
        }

        System.out.println("Automóvel atualizado: ");
        automoveis.get(idx).ImprimirAutomovel();
    }

    public int BuscarUsuario() {
        int index = 0, opt, i = 0;
        Scanner scan = new Scanner(System.in);
        index = scan.nextInt();
        for (Usuario usuario : usuarios) {
            System.out.println("ID - " + i);
            usuario.ImprimirUsuario();
            System.out.println("");
            i++;
        }
        do {
            System.out.println("Escolha o usuario pelo ID da lista acima:");
            opt = scan.nextInt();
            if (opt > usuarios.size() || opt < 0) {
                System.out.println("Escolha inválida, tente novamente");
            }
        } while (opt > usuarios.size() || opt < 0);
        return index;
    }

    public void AlterarUsuário(int opcao) throws RemoteException {
        int idx = BuscarUsuario();

        Scanner scanner = new Scanner(System.in);
        do {
            switch (opcao) {
                case 1 -> {
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    usuarios.get(idx).setNome(novoNome);
                }
                case 2 -> {
                    System.out.println("Selecione o novo TipoID:");
                    System.out.println("1 - CPF");
                    System.out.println("2 - CNPJ");
                    int tipoOpcao = scanner.nextInt();
                    scanner.nextLine(); // Consumir quebra de linha
                    switch (tipoOpcao) {
                        case 1 ->
                            usuarios.get(idx).setTipoID(TipoID.CPF);
                        case 2 ->
                            usuarios.get(idx).setTipoID(TipoID.CNPJ);
                        default ->
                            System.out.println("Opção inválida.");
                    }
                }
                case 3 -> {
                    System.out.print("Novo email: ");
                    String novoEmail = scanner.nextLine();
                    usuarios.get(idx).setEmail(novoEmail);
                }
                case 4 -> {
                    System.out.print("Novo número de celular: ");
                    String novoNumCel = scanner.nextLine();
                    usuarios.get(idx).setNumCel(novoNumCel);
                }
                case 5 -> {
                    System.out.print("Novo endereço: ");
                    String novoEndereco = scanner.nextLine();
                    usuarios.get(idx).setEndereco(novoEndereco);
                }
                case 0 ->
                    System.out.println("Alterações finalizadas.");
                default ->
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    public void AlterarLocador() throws RemoteException {

        float valorSalario;
        float comissaoLoc;
        int opt = 0, idx = BuscarUsuario(), sair = 0;
        Usuario usuario = usuarios.get(idx);
        Scanner scanner = new Scanner(System.in);
        do {
            do {
                System.out.println("Selecione o atributo que deseja alterar:");
                System.out.println("1 - Nome");
                System.out.println("2 - TipoID");
                System.out.println("3 - Email");
                System.out.println("4 - Número de Celular");
                System.out.println("5 - Endereço");
                System.out.println("6 - Valor do salário");
                System.out.println("7 - Comissão do locador");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");
            } while (opt != 0);

            opt = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            if (opt <= 5) {
                AlterarUsuário(opt);
            } else {
                switch (opt) {
                    case 6 -> {
                        Locador locador = (Locador) usuario;
                        System.out.print("Novo valor do salário: ");
                        valorSalario = scanner.nextFloat();
                        locador.setValorSalario(valorSalario);
                    }
                    case 7 -> {
                        Locador locador = (Locador) usuario;
                        System.out.print("Novo valor de comissão de venda: ");
                        comissaoLoc = scanner.nextFloat();
                        locador.setComissaoLoc(comissaoLoc);
                    }
                    default ->
                        throw new AssertionError();
                }
            }
            System.out.println("Digite ( 1 ) se deseja alterar outro valor e ( 0 ) se deseja voltar ao menu: ");
            sair = scanner.nextInt();
        } while (sair == 0);

    }

    public void AlterarVendedor() throws RemoteException {
        float valorSalario;
        float comissaoVenda;
        int opt = 0, idx = BuscarUsuario(), sair = 0;
        Usuario usuario = usuarios.get(idx);
        Scanner scanner = new Scanner(System.in);
        do {
            do {
                System.out.println("Selecione o atributo que deseja alterar:");
                System.out.println("1 - Nome");
                System.out.println("2 - TipoID");
                System.out.println("3 - Email");
                System.out.println("4 - Número de Celular");
                System.out.println("5 - Endereço");
                System.out.println("6 - Valor do salário");
                System.out.println("7 - Comissão do locador");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");
            } while (opt != 0);

            opt = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            if (opt <= 5) {
                AlterarUsuário(opt);
            } else {
                switch (opt) {
                    case 6 -> {
                        Vendedor vendedor = (Vendedor) usuario;
                        System.out.print("Novo valor do salário: ");
                        valorSalario = scanner.nextFloat();
                        vendedor.setValorSalario(valorSalario);
                    }
                    case 7 -> {
                        Vendedor vendedor = (Vendedor) usuario;
                        System.out.print("Novo valor de comissão de venda: ");
                        comissaoVenda = scanner.nextFloat();
                        vendedor.setComissaoVenda(comissaoVenda);
                    }
                    default ->
                        throw new AssertionError();
                }
            }
            System.out.println("Digite ( 1 ) se deseja alterar outro valor e ( 0 ) se deseja voltar ao menu: ");
            sair = scanner.nextInt();
        } while (sair == 0);

    }

    public void AlterarMontadora() throws RemoteException {
        String website;
        String paisOrigem;

        int opt = 0, idx = BuscarUsuario(), sair = 0;
        Usuario usuario = usuarios.get(idx);
        Scanner scanner = new Scanner(System.in);
        do {
            do {
                System.out.println("Selecione o atributo que deseja alterar:");
                System.out.println("1 - Nome");
                System.out.println("2 - TipoID");
                System.out.println("3 - Email");
                System.out.println("4 - Número de Celular");
                System.out.println("5 - Endereço");
                System.out.println("6 - WebSite");
                System.out.println("7 - País de Origem");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");
            } while (opt != 0);

            opt = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            if (opt <= 5) {
                AlterarUsuário(opt);
            } else {
                switch (opt) {
                    case 6 -> {
                        Montadora montadora = (Montadora) usuario;
                        System.out.print("Novo Website: ");
                        website = scanner.nextLine();
                        montadora.setWebsite(website);
                    }
                    case 7 -> {
                        Montadora montadora = (Montadora) usuario;
                        System.out.print("Novo Website: ");
                        paisOrigem = scanner.nextLine();
                        montadora.setPaisOrigem(paisOrigem);
                    }
                    default ->
                        throw new AssertionError();
                }
            }
            System.out.println("Digite ( 1 ) se deseja alterar outro valor e ( 0 ) se deseja voltar ao menu: ");
            sair = scanner.nextInt();
        } while (sair == 0);

    }

    public void AlterarAlugador() throws RemoteException {
        int idade;
        String genero;

        int opt = 0, idx = BuscarUsuario(), sair = 0;
        Usuario usuario = usuarios.get(idx);
        Scanner scanner = new Scanner(System.in);
        do {
            do {
                System.out.println("Selecione o atributo que deseja alterar:");
                System.out.println("1 - Nome");
                System.out.println("2 - TipoID");
                System.out.println("3 - Email");
                System.out.println("4 - Número de Celular");
                System.out.println("5 - Endereço");
                System.out.println("6 - Idade");
                System.out.println("7 - Gênero");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");
            } while (opt != 0);

            opt = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            if (opt <= 5) {
                AlterarUsuário(opt);
            } else {
                switch (opt) {
                    case 6 -> {
                        Alugador alugador = (Alugador) usuario;
                        System.out.print("Nova idade: ");
                        idade = scanner.nextInt();
                        alugador.setIdade(idade);
                    }
                    case 7 -> {
                        Alugador alugador = (Alugador) usuario;
                        do {
                            System.out.println("Gênero do cliente (1) Masculino (2) Feminino (3) Outro: ");
                            opt = scanner.nextInt();
                            if (opt > 3 || opt < 1) {
                                System.out.println("Opção inválida, insira novamente.");
                            }
                        } while (opt > 3 || opt < 1);

                        switch (opt) {
                            case 1 ->
                                alugador.setGenero("M");
                            case 2 ->
                                alugador.setGenero("F");
                            case 3 ->
                                alugador.setGenero("O");
                            default ->
                                throw new AssertionError();
                        }
                        genero = scanner.nextLine();
                        alugador.setGenero(genero);
                    }
                    default ->
                        throw new AssertionError();
                }
            }
            System.out.println("Digite ( 1 ) se deseja alterar outro valor e ( 0 ) se deseja voltar ao menu: ");
            sair = scanner.nextInt();
        } while (sair == 0);

    }

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

    public void ImprimirObtencao() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void ImprimirRegistro() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void ImprimirVenda() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void CriarObtencao(int currentID) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
