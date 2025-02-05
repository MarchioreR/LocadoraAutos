/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.dao;

import com.mycompany.locadoraauto.enums.TipoVeiculo;
import com.mycompany.locadoraauto.models.Alugador;
import com.mycompany.locadoraauto.models.Automovel;
import com.mycompany.locadoraauto.models.Locador;
import com.mycompany.locadoraauto.models.Montadora;
import com.mycompany.locadoraauto.models.Usuario;
import com.mycompany.locadoraauto.models.Vendedor;
import com.mycompany.locadoraauto.enums.TipoVeiculo;
import com.mycompany.locadoraauto.enums.TipoStatus;
import com.mycompany.locadoraauto.models.Contrato;
import com.mycompany.locadoraauto.models.Obtencao;
import com.mycompany.locadoraauto.models.RegistroFinanceiro;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataAccessObject {

    public ArrayList<Usuario> carregarUsuariosDoBanco() throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
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
                        usuario = new Alugador(idade, genero, id, nome, tipoId, identi, email, numCel, endereco);
                    }
                }

                stmtLocador.setInt(1, id);
                try (ResultSet rsLocador = stmtLocador.executeQuery()) {
                    if (rsLocador.next()) {
                        var valorSalario = rsLocador.getBigDecimal("valor_salario").floatValue();
                        var comissaoLoc = rsLocador.getBigDecimal("comissao_loc").floatValue();
                        usuario = new Locador(valorSalario, comissaoLoc, id, nome, tipoId, identi, email, numCel, endereco);
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
                        usuario = new Vendedor(valorSalario, comissaoVenda, id, nome, tipoId, identi, email, numCel, endereco);
                    }
                }

                if (usuario != null) {
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
        }

        return usuarios;
    }

    public static void insertUsuario(Usuario usuario) throws SQLException {
        String sqlUsuario = "INSERT INTO Usuario (nome, tipo_id, identi, email, num_cel, endereco) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlSpecific = "";
        Connection conn = FactoryConnection.createConnection();
        if (usuario instanceof Alugador) {
            sqlSpecific = "INSERT INTO Alugador (idade, genero) VALUES (?, ?)";
        } else if (usuario instanceof Locador) {
            sqlSpecific = "INSERT INTO Locador (valor_salario, comissao_loc) VALUES (?, ?)";
        } else if (usuario instanceof Montadora) {
            sqlSpecific = "INSERT INTO Montadora (paisOrigem, website) VALUES (?, ?)";
        } else if (usuario instanceof Vendedor) {
            sqlSpecific = "INSERT INTO Vendedor (valor_salario, comissao_venda) VALUES (?, ?";
        }

        try (conn; PreparedStatement pstmtUsuario = conn.prepareStatement(sqlUsuario); PreparedStatement pstmtSpecific = conn.prepareStatement(sqlSpecific)) {

            // Insert into Usuario table
            pstmtUsuario.setString(1, usuario.getNome());
            pstmtUsuario.setString(2, usuario.getTipoID().name());
            pstmtUsuario.setString(3, usuario.getID());
            pstmtUsuario.setString(4, usuario.getEmail());
            pstmtUsuario.setString(5, usuario.getNumCel());
            pstmtUsuario.setString(6, usuario.getEndereco());
            pstmtUsuario.executeUpdate();

            // Insert into specific table
            switch (usuario) {
                case Alugador alugador -> {
                    pstmtSpecific.setInt(2, alugador.getIdade());
                    pstmtSpecific.setString(3, alugador.getGenero());
                }
                case Locador locador -> {
                    pstmtSpecific.setDouble(2, locador.getValorSalario());
                    pstmtSpecific.setDouble(3, locador.getComissaoLoc());
                }
                case Montadora montadora -> {
                    pstmtSpecific.setString(2, montadora.getPaisOrigem());
                    pstmtSpecific.setString(3, montadora.getWebsite());
                }
                case Vendedor vendedor -> {
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

    public static void inserirContrato(Contrato cont) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "INSERT INTO Contrato (id_alugador, seguro, data_inicio, data_termino, valor_contrato) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cont.getAlugador().getIdUsuario());
            stmt.setString(2, cont.getSeguro().getTipoSeguro().getDescricao());
            stmt.setDate(3, Date.valueOf(cont.getDataIn()));
            stmt.setDate(4, Date.valueOf(cont.getDataTer()));
            stmt.setDouble(5, cont.getValorContrato());
            stmt.executeUpdate();
        }
    }

    public static void inserirLocacao(int idContrato, int idAutomovel, int idLocador) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "INSERT INTO Locacao (id_contrato, id_automovel, id_locador) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idContrato);
            stmt.setInt(2, idAutomovel);
            stmt.setInt(3, idLocador);
            stmt.executeUpdate();
        }
    }

    public static void inserirObtencao(Obtencao obt) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "INSERT INTO Obtencao (id_automovel, id_montadora, data_obtencao, valor_obtencao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, obt.getAutomovel().getIdAutomovel());
            stmt.setInt(2, obt.getMontadora().getIdUsuario());
            stmt.setDate(3, (Date) obt.getDataObt());
            stmt.setDouble(4, obt.getValorObt());
            stmt.executeUpdate();
        }
    }

    public static void inserirRegistroFinanceiro(RegistroFinanceiro r) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "INSERT INTO RegistroFinanceiro (id_automovel, valor_compra, valor_venda, valor_diaria, valor_manutencao, valor_total) VALUES (?, ?, ?, ?, ?, ?)";
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

    public static void inserirSeguro(String tipoSeguro, double valorSeguro, String seguradora) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "INSERT INTO Seguro (tipo_seguro, valor_seguro, seguradora) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipoSeguro);
            stmt.setDouble(2, valorSeguro);
            stmt.setString(3, seguradora);
            stmt.executeUpdate();
        }
    }
    
     public static void adicionarVendedor(Vendedor vendedor) {
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
            e.printStackTrace();
        }
    }

    public static void adicionarMontadora(Montadora mont) {
        String sql = "INSERT INTO Usuario (nome, tipoID, ID, email, numCel, endereco) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlVendedor = "INSERT INTO Vendedor (idUsuario, website, paisOrigem) VALUES (?, ?, ?)";

        try (Connection conn = FactoryConnection.createConnection(); PreparedStatement stmtUsuario = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); PreparedStatement stmtVendedor = conn.prepareStatement(sqlVendedor)) {

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

                // Inserindo na tabela Vendedor
                stmtVendedor.setInt(1, idUsuario);
                stmtVendedor.setString(2, mont.getWebsite());
                stmtVendedor.setString(3, mont.getPaisOrigem());
                stmtVendedor.executeUpdate();
            }

            System.out.println("Vendedor cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void inserirVenda(int idAutomovel, int idVendedor, double valorVenda) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "INSERT INTO Venda (id_automovel, id_vendedor, valor_venda) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAutomovel);
            stmt.setInt(2, idVendedor);
            stmt.setDouble(3, valorVenda);
            stmt.executeUpdate();
        }
    }

    public static void adicionarAutomovel(Automovel automovel) {
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
            e.printStackTrace();
        }
    }

    public static void adicionarObtencao(Obtencao obtencao) {
        String sql = "INSERT INTO Obtencao (idAutomovel, idMontadora, dataObt, valorObt) VALUES (?, ?, ?, ?)";
        try (Connection conn = FactoryConnection.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, obtencao.getAutomovel().getIdAutomovel());
            stmt.setInt(2, obtencao.getMontadora().getIdUsuario()); // Montadora herda de Usuario
            stmt.setDate(3, new java.sql.Date(obtencao.getDataObt().getTime())); // Convertendo LocalDate para SQL Date
            stmt.setFloat(4, obtencao.getValorObt());

            stmt.executeUpdate();
            System.out.println("Obtenção cadastrada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void adicionarRegistroFinanceiro(RegistroFinanceiro registro) {
        String sql = "INSERT INTO RegistroFinanceiro (idAutomovel, valorCompra, valorVenda, valorDiaria, valorManutencao, valorTotal) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = FactoryConnection.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, registro.getAutomovel().getIdAutomovel());
            stmt.setFloat(2, registro.getValorCompra());
            stmt.setFloat(3, registro.getValorVenda());
            stmt.setFloat(4, registro.getValorDiaria());
            stmt.setFloat(5, registro.getValorManutencao());
            stmt.setFloat(6, registro.getValorTotal());

            stmt.executeUpdate();
            System.out.println("Registro financeiro cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Automovel> listarAutomoveis() {
        ArrayList<Automovel> automoveis = new ArrayList<>();
        String sql = "SELECT * FROM Automovel";

        try (Connection conn = FactoryConnection.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Automovel automovel = new Automovel();
                automovel.setIdAutomovel(rs.getInt("idAutomovel"));
                automovel.setModelo(rs.getString("modelo"));
                automovel.setPlaca(rs.getString("placa"));
                automovel.setTipoVeic(TipoVeiculo.valueOf(rs.getString("tipoVeic"))); // Converte de String para Enum
                automovel.setValorDia(rs.getFloat("valorDia"));
                automovel.setStatus(TipoStatus.valueOf(rs.getString("status"))); // Converte de String para Enum

                automoveis.add(automovel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return automoveis;
    }
}
