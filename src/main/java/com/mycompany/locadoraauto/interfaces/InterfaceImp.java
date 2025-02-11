/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.locadoraauto.interfaces;

import com.mycompany.locadoraauto.dao.FactoryConnection;
import com.mycompany.locadoraauto.enums.TipoID;
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
import com.mycompany.locadoraauto.view.JFrameMenu;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
    private DefaultTableModel modelL;
    private DefaultTableModel modelR;

    // Constructor
    public InterfaceImp() throws RemoteException {
        super(); // Call the parent class constructor
        modelL = new DefaultTableModel(new String[]{"Modelo", "Tipo", "Valor Diaria", "Status"}, 0);
        modelR = new DefaultTableModel(new String[]{"Modelo", "Valor Diaria", "Valor Manutencao", "Total"}, 0);
    }

    public void CriarContrato(int currentID, Alugador alugador, Locador locador, Seguro seguro, float valorContrato, Automovel automovel) throws RemoteException {
        int idContrato = currentID;
        LocalDate dataIn = LocalDate.now();
        LocalDate dataTer = LocalDate.now().plusDays(3);

        Contrato contrato = new Contrato(idContrato, alugador, dataIn, dataTer, valorContrato, locador, automovel);
        contrato.setSeguro(seguro);
        contratos.add(contrato);
        try {
            inserirContrato(contrato);
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

        adicionarRegistroFinanceiro(r);
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

    public int buscarUsuario(String nomeOuID) throws RemoteException {
        for (Usuario usuario : usuarios) {
            if (usuario.getID().equals(nomeOuID) || usuario.getNome().equalsIgnoreCase(nomeOuID)) {
                return usuario.getIdUsuario(); // Retorna o primeiro usuário encontrado
            }
        }
        return 0; // Retorna null se nenhum usuário for encontrado
    }

    public Locador GetLocAtPOS(int idx) throws RemoteException {
        if (idx < usuarios.size() && idx >= 0 && (usuarios.get(idx)) instanceof Locador) {
            return (Locador) usuarios.get(idx);
        }
        return null;

    }
    
    public Alugador GetAlugAtPOS(int idx) throws RemoteException {
        if (idx < usuarios.size() && idx >= 0 && (usuarios.get(idx)) instanceof Alugador) {
            return (Alugador) usuarios.get(idx);
        }
        return null;

    }

    // DAO
    //
    //
    //
    //
    //
    //
    //
    //
    //
    public ArrayList<Usuario> carregarUsuariosDoBanco() throws SQLException, RemoteException {
        ArrayList<Usuario> novalista = new ArrayList<>();
        Connection conn = FactoryConnection.createConnection();
        String sqlUsuario = "SELECT * FROM Usuario";
        String sqlAlugador = "SELECT * FROM Alugador WHERE id = ?";
        String sqlLocador = "SELECT * FROM Locador WHERE id = ?";
        String sqlMontadora = "SELECT * FROM Montadora WHERE id = ?";
        String sqlVendedor = "SELECT * FROM Vendedor WHERE id = ?";

        try (PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario); PreparedStatement stmtAlugador = conn.prepareStatement(sqlAlugador); PreparedStatement stmtLocador = conn.prepareStatement(sqlLocador); PreparedStatement stmtMontadora = conn.prepareStatement(sqlMontadora); PreparedStatement stmtVendedor = conn.prepareStatement(sqlVendedor); ResultSet rsUsuario = stmtUsuario.executeQuery()) {

            while (rsUsuario.next()) {
                int id = rsUsuario.getInt("id");
                String nome = rsUsuario.getString("nome");
                int tipoId = rsUsuario.getInt("tipo_id");
                String identi = rsUsuario.getString("identi");
                String email = rsUsuario.getString("email");
                String numCel = rsUsuario.getString("num_cel");
                String endereco = rsUsuario.getString("endereco");

                Usuario usuario = null;

                // Verifica em qual tabela o usuário está e recupera seus dados específicos
                stmtAlugador.setInt(1, id);
                try (ResultSet rsAlugador = stmtAlugador.executeQuery()) {
                    if (rsAlugador.next()) {
                        String genero = rsAlugador.getString("genero");
                        int idade = rsAlugador.getInt("idade");
                        usuario = new Alugador(idade, genero, id, nome, identi, email, numCel, endereco);
                    }
                }

                stmtLocador.setInt(1, id);
                try (ResultSet rsLocador = stmtLocador.executeQuery()) {
                    if (rsLocador.next()) {
                        var valorSalario = rsLocador.getBigDecimal("valor_salario").floatValue();
                        var comissaoLoc = rsLocador.getBigDecimal("comissao_loc").floatValue();
                        usuario = new Locador(valorSalario, comissaoLoc, id, nome, identi, email, numCel, endereco);
                    }
                }

                stmtMontadora.setInt(1, id);
                try (ResultSet rsMontadora = stmtMontadora.executeQuery()) {
                    if (rsMontadora.next()) {
                        String paisOrigem = rsMontadora.getString("paisOrigem");
                        String website = rsMontadora.getString("website");
                        usuario = new Montadora(website, paisOrigem, id, nome, identi, email, numCel, endereco);
                    }
                }

                stmtVendedor.setInt(1, id);
                try (ResultSet rsVendedor = stmtVendedor.executeQuery()) {
                    if (rsVendedor.next()) {
                        var valorSalario = rsVendedor.getBigDecimal("valor_salario").floatValue();
                        var comissaoVenda = rsVendedor.getBigDecimal("comissao_venda").floatValue();
                        usuario = new Vendedor(valorSalario, comissaoVenda, id, nome, identi, email, numCel, endereco);
                    }
                }

                if (usuario != null) {
                    novalista.add(usuario);
                }
            }
        } catch (SQLException e) {
        }

        return novalista;
    }

    public void insertUsuario(Usuario usuario) throws SQLException, RemoteException {
        String sqlUsuario = "INSERT INTO Usuario (nome, tipo_id, identi, email, num_cel, endereco) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlSpecific = "";
        String nome = usuario.getNome();
        Connection conn = FactoryConnection.createConnection();
        if (usuario instanceof Alugador) {
            sqlSpecific = "INSERT INTO Alugador (idusuario, idade, genero) VALUES (?, ?, ?)";
        } else if (usuario instanceof Locador) {
            sqlSpecific = "INSERT INTO Locador (idusuario, valor_salario, comissao_loc) VALUES (?, ?, ?)";
        } else if (usuario instanceof Montadora) {
            sqlSpecific = "INSERT INTO Montadora (idusuario, paisOrigem, website) VALUES (?, ?, ?)";
        } else if (usuario instanceof Vendedor) {
            sqlSpecific = "INSERT INTO Vendedor (idusuario, valor_salario, comissao_venda) VALUES (?, ?, ?)";
        }

        try (conn; PreparedStatement pstmtUsuario = conn.prepareStatement(sqlUsuario); PreparedStatement pstmtSpecific = conn.prepareStatement(sqlSpecific)) {

            // Insert into Usuario table
            pstmtUsuario.setString(1, nome);
            pstmtUsuario.setString(2, usuario.getTipoID().getDescricao());
            pstmtUsuario.setString(3, usuario.getID());
            pstmtUsuario.setString(4, usuario.getEmail());
            pstmtUsuario.setString(5, usuario.getNumCel());
            pstmtUsuario.setString(6, usuario.getEndereco());
            pstmtUsuario.executeUpdate();

            // Insert into specific table
            switch (usuario) {
                case Alugador alugador -> {
                    pstmtSpecific.setInt(1, (alugador.getIdUsuario() + 1));
                    pstmtSpecific.setInt(2, alugador.getIdade());
                    pstmtSpecific.setString(3, alugador.getGenero());
                }
                case Locador locador -> {
                    pstmtSpecific.setInt(1, locador.getIdUsuario() + 1);
                    pstmtSpecific.setDouble(2, locador.getValorSalario());
                    pstmtSpecific.setDouble(3, locador.getComissaoLoc());
                }
                case Montadora montadora -> {
                    pstmtSpecific.setInt(1, montadora.getIdUsuario() + 1);
                    pstmtSpecific.setString(2, montadora.getPaisOrigem());
                    pstmtSpecific.setString(3, montadora.getWebsite());
                }
                case Vendedor vendedor -> {
                    pstmtSpecific.setInt(1, vendedor.getIdUsuario() + 1);
                    pstmtSpecific.setDouble(2, vendedor.getValorSalario());
                    pstmtSpecific.setDouble(3, vendedor.getComissaoVenda());
                }
                default -> {
                }
            }

            pstmtSpecific.executeUpdate();

        } catch (SQLException e) {
        }

    }

    public void inserirContrato(Contrato cont) throws SQLException, RemoteException {
        Connection conn = FactoryConnection.createConnection();
        Date DataIn = Date.from(cont.getDataIn().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date DataTer = Date.from(cont.getDataTer().atStartOfDay(ZoneId.systemDefault()).toInstant());

        String sql = "INSERT INTO Contrato (idalugador, idlocador, idautomovel, idseguro, datain, datater, valorcontrato) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cont.getAlugador().getIdUsuario());
            stmt.setInt(2, cont.getLocador().getIdUsuario());
            stmt.setInt(3, cont.getAutomovel().getIdAutomovel());
            stmt.setString(4, cont.getSeguro().getTipoSeguro().getDescricao());
            stmt.setDate(5, new java.sql.Date(DataIn.getTime()));
            stmt.setDate(6, new java.sql.Date(DataTer.getTime()));
            stmt.setFloat(7, cont.getValorContrato());
            stmt.executeUpdate();
        }
    }

    public void inserirLocacao(int idContrato, int idAutomovel, int idLocador) throws SQLException, RemoteException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "INSERT INTO Locacao (id_contrato, id_automovel, id_locador) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idContrato);
            stmt.setInt(2, idAutomovel);
            stmt.setInt(3, idLocador);
            stmt.executeUpdate();
        }
    }

    public void inserirObtencao(Obtencao obt) throws SQLException, RemoteException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "INSERT INTO Obtencao (idautomovel, idmontadora, dataobt, valorobt) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, (obt.getAutomovel().getIdAutomovel() + 1));
            stmt.setInt(2, (obt.getMontadora().getIdUsuario() + 1));
            stmt.setDate(3, new java.sql.Date(obt.getDataObt().getTime()));
            stmt.setDouble(4, obt.getValorObt());
            stmt.executeUpdate();
        }
    }

    public void inserirRegistroFinanceiro(RegistroFinanceiro r) throws SQLException, RemoteException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "INSERT INTO RegistroFinanceiro (idautomovel, valorcompra, valorvenda, valordiaria, valormanutencao, valortotal) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, r.getAutomovel().getIdAutomovel());
            stmt.setDouble(2, r.getValorCompra());
            stmt.setDouble(3, r.getValorVenda());
            stmt.setDouble(4, r.getValorDiaria());
            stmt.setDouble(5, r.getValorManutencao());
            stmt.setDouble(6, r.getValorTotal());
            stmt.executeUpdate();
        }
    }

    public void inserirSeguro(String tipoSeguro, double valorSeguro, String seguradora) throws SQLException, RemoteException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "INSERT INTO Seguro (tiposeguro, valorseguro, seguradora) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipoSeguro);
            stmt.setDouble(2, valorSeguro);
            stmt.setString(3, seguradora);
            stmt.executeUpdate();
        }
    }

    public void adicionarVendedor(Vendedor vendedor) throws RemoteException {
        String sql = "INSERT INTO Usuario (nome, tipoID, ID, email, numCel, endereco) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlVendedor = "INSERT INTO Vendedor (idUsuario, valorSalario, comissaoVenda) VALUES (?, ?, ?)";

        try (Connection conn = FactoryConnection.createConnection(); PreparedStatement stmtUsuario = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); PreparedStatement stmtVendedor = conn.prepareStatement(sqlVendedor)) {

            // Inserindo na tabela Usuario
            stmtUsuario.setString(1, vendedor.getNome());
            stmtUsuario.setString(2, vendedor.getTipoID().name()); // Enum convertido para String
            stmtUsuario.setString(3, vendedor.getID());
            stmtUsuario.setString(4, vendedor.getEmail());
            stmtUsuario.setString(5, vendedor.getNumCel());
            stmtUsuario.setString(6, vendedor.getEndereco());
            stmtUsuario.executeUpdate();

            // Obtendo o ID gerado para o usuário
            var rs = stmtUsuario.getGeneratedKeys();
            if (rs.next()) {
                int idUsuario = rs.getInt(1);

                // Inserindo na tabela Vendedor
                stmtVendedor.setInt(1, idUsuario);
                stmtVendedor.setFloat(2, vendedor.getValorSalario());
                stmtVendedor.setFloat(3, vendedor.getComissaoVenda());
                stmtVendedor.executeUpdate();
            }

            System.out.println("Vendedor cadastrado com sucesso!");
        } catch (SQLException e) {
        }
    }

    public void adicionarMontadora(Montadora mont) throws RemoteException {
        String sql = "INSERT INTO Usuario (nome, tipoID, ID, email, numCel, endereco) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlMontadora = "INSERT INTO Montadora (idUsuario, website, paisOrigem) VALUES (?, ?, ?)";

        try (Connection conn = FactoryConnection.createConnection(); PreparedStatement stmtUsuario = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); PreparedStatement stmtMontadora = conn.prepareStatement(sqlMontadora)) {

            // Inserindo na tabela Usuario
            stmtUsuario.setString(1, mont.getNome());
            stmtUsuario.setString(2, mont.getTipoID().name()); // Enum convertido para String
            stmtUsuario.setString(3, mont.getID());
            stmtUsuario.setString(4, mont.getEmail());
            stmtUsuario.setString(5, mont.getNumCel());
            stmtUsuario.setString(6, mont.getEndereco());
            stmtUsuario.executeUpdate();

            // Obtendo o ID gerado para o usuário
            var rs = stmtUsuario.getGeneratedKeys();
            if (rs.next()) {
                int idUsuario = rs.getInt(1);

                // Inserindo na tabela Montadora
                stmtMontadora.setInt(1, idUsuario);
                stmtMontadora.setString(2, mont.getWebsite());
                stmtMontadora.setString(3, mont.getPaisOrigem());
                stmtMontadora.executeUpdate();
            }

            System.out.println("Montadora cadastrado com sucesso!");
        } catch (SQLException e) {
        }
    }

    public void adicionarAlugador(Alugador alug) throws RemoteException {
        String sql = "INSERT INTO Usuario (nome, tipoID, ID, email, numCel, endereco) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlAlugador = "INSERT INTO Alugador (idUsuario, idade, genero) VALUES (?, ?, ?)";

        try (Connection conn = FactoryConnection.createConnection(); PreparedStatement stmtUsuario = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); PreparedStatement stmtAlugador = conn.prepareStatement(sqlAlugador)) {

            // Inserindo na tabela Usuario
            stmtUsuario.setString(1, alug.getNome());
            stmtUsuario.setString(2, alug.getTipoID().name()); // Enum convertido para String
            stmtUsuario.setString(3, alug.getID());
            stmtUsuario.setString(4, alug.getEmail());
            stmtUsuario.setString(5, alug.getNumCel());
            stmtUsuario.setString(6, alug.getEndereco());
            stmtUsuario.executeUpdate();

            // Obtendo o ID gerado para o usuário
            var rs = stmtUsuario.getGeneratedKeys();
            if (rs.next()) {
                int idUsuario = rs.getInt(1);

                // Inserindo na tabela Alugador
                stmtAlugador.setInt(1, idUsuario);
                stmtAlugador.setInt(2, alug.getIdade());
                stmtAlugador.setString(3, alug.getGenero());
                stmtAlugador.executeUpdate();
            }

            System.out.println("Cliente cadastrado com sucesso!");
        } catch (SQLException e) {
        }
    }

    public void adicionarLocador(Locador loc) throws RemoteException {
        String sql = "INSERT INTO Usuario (nome, tipoID, ID, email, numCel, endereco) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlLocador = "INSERT INTO Locador (idUsuario, valorsalario, comissaoloc) VALUES (?, ?, ?)";

        try (Connection conn = FactoryConnection.createConnection(); PreparedStatement stmtUsuario = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); PreparedStatement stmtLocador = conn.prepareStatement(sqlLocador)) {

            // Inserindo na tabela Usuario
            stmtUsuario.setString(1, loc.getNome());
            stmtUsuario.setString(2, loc.getTipoID().name()); // Enum convertido para String
            stmtUsuario.setString(3, loc.getID());
            stmtUsuario.setString(4, loc.getEmail());
            stmtUsuario.setString(5, loc.getNumCel());
            stmtUsuario.setString(6, loc.getEndereco());
            stmtUsuario.executeUpdate();

            // Obtendo o ID gerado para o usuário
            var rs = stmtUsuario.getGeneratedKeys();
            if (rs.next()) {
                int idUsuario = rs.getInt(1);

                // Inserindo na tabela Locador
                stmtLocador.setInt(1, idUsuario);
                stmtLocador.setFloat(2, loc.getValorSalario());
                stmtLocador.setFloat(3, loc.getComissaoLoc());
                stmtLocador.executeUpdate();
            }

            System.out.println("Locador cadastrado com sucesso!");
        } catch (SQLException e) {
        }
    }

    public void inserirVenda(int idAutomovel, int idVendedor, double valorVenda) throws RemoteException, SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "INSERT INTO Venda (idautomovel, idvendedor, valorvenda) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAutomovel);
            stmt.setInt(2, idVendedor);
            stmt.setDouble(3, valorVenda);
            stmt.executeUpdate();
        }
    }

    public void adicionarAutomovel(Automovel automovel) throws RemoteException {
        String sql = "INSERT INTO Automovel (modelo, placa, tipoVeic, valorDia, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = FactoryConnection.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, automovel.getModelo());
            stmt.setString(2, automovel.getPlaca());
            stmt.setString(3, automovel.getTipoVeic().name()); // Enum convertido para String
            stmt.setFloat(4, automovel.getValorDia());
            stmt.setString(5, automovel.getStatus().name()); // Enum convertido para String

            stmt.executeUpdate();
            System.out.println("Automóvel cadastrado com sucesso!");
        } catch (SQLException e) {
        }
    }

    public void adicionarObtencao(Obtencao obtencao) throws RemoteException {
        String sql = "INSERT INTO Obtencao (idAutomovel, idMontadora, dataObt, valorObt) VALUES (?, ?, ?, ?)";
        try (Connection conn = FactoryConnection.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, (obtencao.getAutomovel().getIdAutomovel() + 1));
            stmt.setInt(2, (obtencao.getMontadora().getIdUsuario() + 1)); // Montadora herda de Usuario
            stmt.setDate(3, new java.sql.Date(obtencao.getDataObt().getTime())); // Convertendo LocalDate para SQL Date
            stmt.setFloat(4, obtencao.getValorObt());

            stmt.executeUpdate();
            System.out.println("Obtenção cadastrada com sucesso!");
        } catch (SQLException e) {
        }
    }

    public void adicionarRegistroFinanceiro(RegistroFinanceiro registro) throws RemoteException {
        String sql = "INSERT INTO RegistroFinanceiro (idAutomovel, valorCompra, valorVenda, valorDiaria, valorManutencao, valorTotal) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = FactoryConnection.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, (registro.getAutomovel().getIdAutomovel() + 1));
            stmt.setFloat(2, registro.getValorCompra());
            stmt.setFloat(3, registro.getValorVenda());
            stmt.setFloat(4, registro.getValorDiaria());
            stmt.setFloat(5, registro.getValorManutencao());
            stmt.setFloat(6, registro.getValorTotal());

            stmt.executeUpdate();
            System.out.println("Registro financeiro cadastrado com sucesso!");
        } catch (SQLException e) {
        }
    }

    public ArrayList<Usuario> listarUsuarios() throws RemoteException {
        ArrayList<Usuario> novalista = new ArrayList<>();
        String sql = "SELECT * FROM Usuario", enom;
        TipoID tp;

        try (Connection conn = FactoryConnection.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario user = new Usuario();
                user.setIdUsuario(rs.getInt("idUsuario"));
                enom = rs.getString("tipoID");
                tp = TipoID.valueOf(enom);
                user.setTipoID(tp);
                user.setID(rs.getString("ID"));
                user.setEmail(rs.getString("email"));
                user.setNumCel(rs.getString("numCel"));
                user.setEndereco(rs.getString("endereco"));

                novalista.add(user);
            }

        } catch (SQLException e) {
        }
        usuarios = novalista;
        return novalista;
    }

    public ArrayList<Automovel> listarAutomoveis() throws RemoteException {
        ArrayList<Automovel> novalista = new ArrayList<>();
        String sql = "SELECT * FROM Automovel";

        try (Connection conn = FactoryConnection.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Automovel automovel = new Automovel();
                automovel.setIdAutomovel(rs.getInt("idAutomovel") - 1);
                automovel.setModelo(rs.getString("modelo"));
                automovel.setPlaca(rs.getString("placa"));
                automovel.setTipoVeic(TipoVeiculo.valueOf(rs.getString("tipoVeic"))); // Converte de String para Enum
                automovel.setValorDia(rs.getFloat("valorDia"));
                automovel.setStatus(TipoStatus.valueOf(rs.getString("status"))); // Converte de String para Enum

                novalista.add(automovel);
            }

        } catch (SQLException e) {
        }
        automoveis = novalista;
        return novalista;
    }

    public ArrayList<RegistroFinanceiro> listarRegistros(ArrayList<Automovel> auto) throws RemoteException {
        ArrayList<RegistroFinanceiro> novalista = new ArrayList<>();
        String sql = "SELECT * FROM RegistroFinanceiro";
        int idAuto;
        Automovel aux;
        try (Connection conn = FactoryConnection.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                RegistroFinanceiro registro = new RegistroFinanceiro();
                idAuto = (rs.getInt("idAutomovel") - 1);
                aux = auto.get(idAuto);
                registro.setAutomovel(aux);
                registro.setIdRegistro(rs.getInt("idregistro") - 1);
                registro.setValorCompra(rs.getFloat("valorcompra"));
                registro.setValorDiaria(rs.getFloat("valordiaria"));
                registro.setValorManutencao(rs.getFloat("valormanutencao"));
                registro.setValorTotal(rs.getFloat("valortotal"));
                registro.setValorVenda(rs.getFloat("valorvenda"));
                novalista.add(registro);
            }

        } catch (SQLException e) {
        }
        registros  = novalista;
        return novalista;
    }

    public void CriarMontadora(Montadora montadora) throws RemoteException {
        usuarios.add(montadora);

        adicionarMontadora(montadora);
    }

    //
    ///
    //
    public void AddDataCad(JTable jTable1, JScrollPane jScrollPane1, ArrayList<Automovel> lista) throws SQLException, RemoteException {
        jTable1.setModel(modelL);
        jScrollPane1.setViewportView(jTable1);

        try {
            String[] linha = new String[4]; // Array to hold data for each row
            Automovel aux = null; // Temporary variable to hold each Automovel object

            // Loop through the ArrayList of Automovel objects
            for (int i = 0; i < lista.size(); i++) {
                aux = lista.get(i); // Get the current Automovel object

                // Populate the row with data from the Automovel object
                linha[0] = aux.getModelo(); // modelo
                linha[1] = aux.getTipoVeic().getDescricao(); // tipo_veiculo (enum value as String)
                linha[2] = String.valueOf(aux.getValorDia()); // valor_dia (converted to String)
                linha[3] = aux.getStatus().getDescricao(); // status (enum value as String)

                // Add the row to the JTable model
                modelL.addRow(linha);
            }
        } catch (Exception ex) {
            Logger.getLogger(JFrameMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTable1.revalidate();
        jTable1.repaint();
    }

    public void AddDataRegistro(JTable jTable1, JScrollPane jScrollPane1, ArrayList<RegistroFinanceiro> lista) throws SQLException, RemoteException {
        jTable1.setModel(modelR);
        jScrollPane1.setViewportView(jTable1);

        try {
            String[] linha = new String[4];
            RegistroFinanceiro aux = null;

            for (int i = 0; i < lista.size(); i++) {
                aux = lista.get(i);
                linha[0] = aux.getAutomovel().getModelo();
                linha[1] = String.valueOf(aux.getValorDiaria());
                linha[2] = String.valueOf(aux.getValorManutencao());
                linha[3] = String.valueOf(aux.getValorTotal());
                modelR.addRow(linha);
            }
        } catch (Exception ex) {
            Logger.getLogger(JFrameMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTable1.revalidate();
        jTable1.repaint();
    }

    public void InserirAuto(Automovel novo) throws SQLException, RemoteException {
        adicionarAutomovel(novo);
    }

    public void InserirRegistro(RegistroFinanceiro r) throws SQLException, RemoteException {
        adicionarRegistroFinanceiro(r);
    }
}
