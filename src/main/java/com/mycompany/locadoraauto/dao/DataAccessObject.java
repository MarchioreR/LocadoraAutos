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
                        usuario = new Montadora(website, paisOrigem, id, nome, tipoId, identi, email, numCel, endereco);
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

    public void insertUsuario(Usuario usuario) throws SQLException {
        String sqlUsuario = "INSERT INTO Usuario (id, nome, tipo_id, identi, email, num_cel, endereco) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlSpecific = "";
        Connection conn = FactoryConnection.createConnection();
        if (usuario instanceof Alugador) {
            sqlSpecific = "INSERT INTO Alugador (id, idade, genero) VALUES (?, ?, ?)";
        } else if (usuario instanceof Locador) {
            sqlSpecific = "INSERT INTO Locador (id, valor_salario, comissao_loc) VALUES (?, ?, ?)";
        } else if (usuario instanceof Montadora) {
            sqlSpecific = "INSERT INTO Montadora (id, paisOrigem, website) VALUES (?, ?, ?)";
        } else if (usuario instanceof Vendedor) {
            sqlSpecific = "INSERT INTO Vendedor (id, valor_salario, comissao_venda) VALUES (?, ?, ?)";
        }

        try (conn; PreparedStatement pstmtUsuario = conn.prepareStatement(sqlUsuario); PreparedStatement pstmtSpecific = conn.prepareStatement(sqlSpecific)) {

            // Insert into Usuario table
            pstmtUsuario.setInt(1, usuario.getIdUsuario());
            pstmtUsuario.setString(2, usuario.getNome());
            pstmtUsuario.setString(3, usuario.getTipoID().name());
            pstmtUsuario.setString(4, usuario.getIdenti());
            pstmtUsuario.setString(5, usuario.getEmail());
            pstmtUsuario.setString(6, usuario.getNumCel());
            pstmtUsuario.setString(7, usuario.getEndereco());
            pstmtUsuario.executeUpdate();

            // Insert into specific table
            switch (usuario) {
                case Alugador alugador -> {
                    pstmtSpecific.setInt(1, alugador.getId());
                    pstmtSpecific.setInt(2, alugador.getIdade());
                    pstmtSpecific.setString(3, alugador.getGenero());
                }
                case Locador locador -> {
                    pstmtSpecific.setInt(1, locador.getId());
                    pstmtSpecific.setDouble(2, locador.getValorSalario());
                    pstmtSpecific.setDouble(3, locador.getComissaoLoc());
                }
                case Montadora montadora -> {
                    pstmtSpecific.setInt(1, montadora.getId());
                    pstmtSpecific.setString(2, montadora.getPaisOrigem());
                    pstmtSpecific.setString(3, montadora.getWebsite());
                }
                case Vendedor vendedor -> {
                    pstmtSpecific.setInt(1, vendedor.getId());
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

    /*id INT AUTO_INCREMENT PRIMARY KEY,
    id_alugador INT,
    id_seguro INT,
    data_inicio DATE NOT NULL,
    data_termino DATE NOT NULL,
    valor_contrato DECIMAL(10, 2),
    FOREIGN KEY (id_alugador) REFERENCES Alugador(id),
    FOREIGN KEY (id_seguro) REFERENCES Seguro(id)*/
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

    public static void inserirObtencao(int idAutomovel, int idMontadora, String dataObtencao, double valorObtencao) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "INSERT INTO Obtencao (id_automovel, id_montadora, data_obtencao, valor_obtencao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAutomovel);
            stmt.setInt(2, idMontadora);
            stmt.setString(3, dataObtencao);
            stmt.setDouble(4, valorObtencao);
            stmt.executeUpdate();
        }
    }

    public static void inserirRegistroFinanceiro(int idAutomovel, double valorCompra, double valorVenda, double valorDiaria, double valorManutencao, double valorTotal) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "INSERT INTO RegistroFinanceiro (id_automovel, valor_compra, valor_venda, valor_diaria, valor_manutencao, valor_total) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAutomovel);
            stmt.setDouble(2, valorCompra);
            stmt.setDouble(3, valorVenda);
            stmt.setDouble(4, valorDiaria);
            stmt.setDouble(5, valorManutencao);
            stmt.setDouble(6, valorTotal);
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

    public static void inserirAutos(int idAutomovel, String modelo, String placa, TipoVeiculo tipoveic, float valordia, TipoStatus status) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "INSERT INTO Automovel (modelo, placa, tipo_veiculo, valor_dia, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAutomovel);
            stmt.setString(2, modelo);
            stmt.setString(3, placa);
            stmt.setString(4, tipoveic.getDescricao());
            stmt.setBigDecimal(5, BigDecimal.valueOf(valordia));
            stmt.setString(6, status.getDescricao());
            stmt.executeUpdate();
        }
    }

    public static ArrayList<Automovel> getAutomoveis() throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        ArrayList<Automovel> automoveis = new ArrayList<>();
        String sql = "SELECT id, modelo, placa, tipo_veiculo, valor_dia, status FROM Automovel";
        int tipov, tipos;
        try (conn; PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String modelo = rs.getString("modelo");
                String placa = rs.getString("placa");
                TipoVeiculo tipo_veiculo = TipoVeiculo.valueOf(rs.getString("tipo_veiculo"));
                float valor_dia = rs.getFloat("valor_dia");
                TipoStatus status = TipoStatus.valueOf(rs.getString("status"));
                if (tipo_veiculo.toString().equals(TipoVeiculo.COMBUSTAO.getDescricao())) {
                    tipov = 1;
                } else if (tipo_veiculo.toString().equals(TipoVeiculo.ELETRICO.getDescricao())) {
                    tipov = 2;
                } else {
                    tipov = 3;
                }
                if (status.toString().equals(TipoStatus.DISPONIVEL.getDescricao())) {
                    tipos = 1;
                } else if (status.toString().equals(TipoStatus.INDISPONIVEL.getDescricao())) {
                    tipos = 2;
                } else {
                    tipos = 3;
                }
                Automovel automovel = new Automovel(id, modelo, placa, tipov, valor_dia, tipos);
                automoveis.add(automovel);
            }

        } catch (SQLException e) {
        }

        return automoveis;
    }
}
