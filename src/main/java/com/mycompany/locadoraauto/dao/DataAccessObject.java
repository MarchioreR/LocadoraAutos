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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataAccessObject {

    /*public static boolean inserirItemDB(Item item) throws SQLException{
        Connection conn = FactoryConnection.createConnection();
        String sql;
    }*/
 /*private static boolean getItemDB(Item item, int id) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "select i.cdu,i.quantidade,i.qtdcontrol,i.titulo,group_concat(distinct(a.nome) separator ',') as autores,group_concat(distinct(pc.nome) separator ',') as pchaves,i.cidade,i.data_pub,i.assunto,i.tipo_item,i.tam_x,i.tam_y,i.tam_z "
                + "from item i join item_autor ia on i.id_item = ia.id_item "
                + "join autor a on a.id_autor = ia.id_autor "
                + "join item_pchave ip on i.id_item = ip.id_item "
                + "join pchave pc on pc.id_pchave = ip.id_pchave "
                + "where i.id_item =" + id + " group by i.titulo";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet result = ps.executeQuery();

        if (result.next()) {
            item.setCdu(result.getString("cdu"));
            item.setQuantidade(result.getInt("quantidade"), result.getInt("qtdcontrol"));
            item.setTitulo(result.getString("titulo"));

            item.setAutores(UtilBU.separaElementos(result.getString("autores")));
            item.setPchaves(UtilBU.separaElementos(result.getString("pchaves")));

            item.setCidade(result.getString("cidade"));
            item.setAssunto(result.getString("assunto"));

            java.sql.Date data = result.getDate("data_pub");
            item.setData_pub(new Date(data.getTime()));

            double[] tam = new double[3];
            tam[0] = result.getDouble("tam_x");
            tam[1] = result.getDouble("tam_y");
            tam[2] = result.getDouble("tam_z");
            item.setTamanho(tam[0], tam[1], tam[2]);

            return true;
        } else {
            System.err.println("Erro ao pegar item");
            return false;
        }
    }

    public static boolean getPeriodicoDB(Item item, int id) throws SQLException {
        getItemDB(item, id);

        Connection conn = FactoryConnection.createConnection();
        String sql = "select isbn,paginas,edicao,editora,tipo_periodico from livro where id_item = " + id;
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet result = ps.executeQuery();

        if (result.next()) {
            ((Livro) item).setIsbn(result.getString("isbn"));
            ((Livro) item).setPaginas(result.getInt("paginas"));
            ((Livro) item).setEdicao(result.getInt("edicao"));
            ((Livro) item).setEditora(result.getString("editora"));

            int tipo = result.getInt("tipo_periodico");
            switch (tipo) {
                case 1:
                    ((Periodico) item).setTipo(TipoPeriodico.JORNAL);
                    break;
                case 2:
                    ((Periodico) item).setTipo(TipoPeriodico.REVISTA);
                    break;
            }

            return true;
        }
        return false;
    }

    public static boolean inserirItemDB(Item item, int tipoItem) throws SQLException {
        int id_item = inserirItemTableDB(item, tipoItem);

        if (id_item != -1) {
            ArrayList<String> autores = item.getAutor();
            inserirAutores(autores, id_item);

            ArrayList<String> pchaves = item.getPchave();
            inserirPchaves(pchaves, id_item);

            switch (tipoItem) {
                case 1:
                    inserirLivroDB(item, id_item);
                    break;
                case 2:
                    inserirPeriodicoDB(item, id_item);
                    break;
                case 3:
                    inserirTrabDB(item, id_item);
                    break;
                case 4:
                    inserirMidiaDB(item, id_item);
                    break;
                case 5:
                    inserirMapaDB(item, id_item);
                    break;
            }

            return true;
        }
        System.err.println("Falha ao receber id_item!");
        return false;

    }

    private static int inserirItemTableDB(Item item, int tipoItem) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "insert into item (cdu,quantidade,qtdcontrol,titulo,cidade,data_pub,assunto,tam_x,tam_y,tam_z,tipo_item) values "
                + "(?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, item.getCdu()); // cdu
        ps.setInt(2, item.getQuantidade()); // quantidade
        ps.setInt(3, item.getQtdcontrol()); // qtdcontrol
        ps.setString(4, item.getTitulo()); // titulo
        ps.setString(5, item.getCidade()); // cidade
        ps.setDate(6, new java.sql.Date(item.getData_pub().getTime())); // data_pub
        ps.setString(7, item.getAssunto()); // assunto
        ps.setDouble(8, item.getTamanho()[0]); // tam_x
        ps.setDouble(9, item.getTamanho()[1]); // tam_y
        ps.setDouble(10, item.getTamanho()[2]); // tam_z
        ps.setInt(11, tipoItem); // tipo_item

        int rows = ps.executeUpdate();

        if (rows > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                try {
                    int id_item = rs.getInt(1);
                    System.out.println("id_item: " + id_item);
                    return id_item;
                } catch (SQLException e) {
                    int id_item = rs.getInt(1);
                    System.out.println("id_item error: " + id_item);
                }
            }
        }

        //TODO:terminar função
        return -1;
    }

    private static void inserirTrabDB(Item item, int id_item) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "insert into livro (id_item,isbn,paginas,edicao,editora,tipo_trab,instituicao) values "
                + "(?,?,?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id_item);
        ps.setString(2, ((Livro) item).getIsbn());
        ps.setInt(3, ((Livro) item).getPaginas());
        ps.setInt(4, ((Livro) item).getEdicao());
        ps.setString(5, ((Livro) item).getEditora());
        switch (((TrabConclusao) item).getTipo()) {
            case TipoTrabalho.MONOGRAFIA:
                ps.setInt(6, 1);
                break;
            case TipoTrabalho.DISSERTACAO:
                ps.setInt(6, 2);
                break;
            case TipoTrabalho.TESE:
                ps.setInt(6, 3);
                break;
            case TipoTrabalho.RELATORIO:
                ps.setInt(6, 4);
                break;
            default:
                ps.setInt(6, 5);
                break;
        }
        ps.setString(7, ((TrabConclusao) item).getInstituicao());

        ps.execute();
    }

    public static boolean inserirAutor(String autor, int id) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "insert into autor (nome) values ('" + autor + "')";

        PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        int row = ps.executeUpdate();

        if (row > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id_autor = rs.getInt(1);
                System.out.println("id_autor: " + id_autor);

                sql = "insert into item_autor (id_item,id_autor) values (" + id + "," + id_autor + ")";
                ps = conn.prepareStatement(sql);
                ps.execute();
            } else {
                System.err.println("Falha ao obter id_autor!");
            }
        }
        return true;
    }

    public static void inserirAutores(ArrayList<String> autores, int id_item) throws SQLException {
        boolean related;

        ArrayList<String> relacionados = new ArrayList<>();

        for (int i = 0; i < autores.size(); i++) { //relaciona id com autores já existentes
            related = relacionarAutor(autores.get(i), id_item);
            if (related) {
                System.out.println("relacionado: " + autores.get(i));
                //autores.remove(i); //bo
                relacionados.add(autores.get(i));
            }
        }

        for (int i = relacionados.size() - 1; i >= 0; i--) {
            System.out.println(autores.remove(relacionados.get(i)));
        }

        for (int i = 0; i < autores.size(); i++) {
            inserirAutor(autores.get(i), id_item);
            System.out.println("inserido: " + autores.get(i));
        }
    }

    private static boolean relacionarAutor(String autor, int id_item) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "select id_autor from autor where nome = '" + autor + "'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet result = ps.executeQuery();

        if (result.next()) {
            sql = "insert into item_autor (id_item,id_autor) values ("
                    + id_item + "," + result.getInt("id_autor") + ")";
            ps = conn.prepareStatement(sql);
            ps.execute();

            return true;
        }
        return false;
    }

    public static boolean inserirPchave(String pchave, int id) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "insert into pchave (nome) values ('" + pchave + "')";

        PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        int row = ps.executeUpdate();

        if (row > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id_pchave = rs.getInt(1);
                System.out.println("id_autor: " + id_pchave);

                sql = "insert into item_pchave (id_item,id_pchave) values (" + id + "," + id_pchave + ")";
                ps = conn.prepareStatement(sql);
                ps.execute();
            } else {
                System.err.println("Falha ao obter id_chave!");
            }
        }

        return true;
    }

    public static void inserirPchaves(ArrayList<String> pchaves, int id_item) throws SQLException {
        boolean related;

        ArrayList<String> relacionados = new ArrayList<>();

        for (int i = 0; i < pchaves.size(); i++) { //relaciona id com autores já existentes
            related = relacionarPchave(pchaves.get(i), id_item);
            if (related) {
                System.out.println("relacionado: " + pchaves.get(i));
                //pchaves.remove(i); //bo
                relacionados.add(pchaves.get(i));
            }
        }

        for (int i = relacionados.size() - 1; i >= 0; i--) {
            System.out.println(pchaves.remove(relacionados.get(i)));
        }

        for (int i = 0; i < pchaves.size(); i++) {
            inserirPchave(pchaves.get(i), id_item);
            System.out.println("inserido: " + pchaves.get(i));
        }
    }

    private static boolean relacionarPchave(String pchave, int id_item) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "select id_pchave from pchave where nome = '" + pchave + "'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet result = ps.executeQuery();

        if (result.next()) {
            sql = "insert into item_pchave (id_item,id_pchave) values ("
                    + id_item + "," + result.getInt("id_pchave") + ")";
            ps = conn.prepareStatement(sql);
            ps.execute();

            return true;
        }
        return false;
    }

    public static ArrayList<Item> getAllItemsDB() throws SQLException {
        ArrayList<ArrayList<Integer>> ids = getIds();

        if (ids != null) {
            ArrayList<Item> listaItens = new ArrayList<>();
            Item aux = null;
            for (int i = 0; i < ids.get(1).size(); i++) {
                //System.out.println(ids.get(0).get(i)+" - "+ids.get(1).get(i));
                switch (ids.get(1).get(i)) {
                    case 1:
                        aux = new Livro();
                        getLivroDB(aux, ids.get(0).get(i));
                        listaItens.add(aux);
                        break;
                    case 2:
                        aux = new Periodico();
                        getPeriodicoDB(aux, ids.get(0).get(i));
                        listaItens.add(aux);
                        break;
                    case 3:
                        aux = new TrabConclusao();
                        getTrabDB(aux, ids.get(0).get(i));
                        listaItens.add(aux);
                        break;
                    case 4:
                        aux = new Midia();
                        getMidiaDB(aux, ids.get(0).get(i));
                        listaItens.add(aux);
                        break;
                    case 5:
                        aux = new Mapa();
                        getMapaDB(aux, ids.get(0).get(i));
                        listaItens.add(aux);
                        break;
                }
            }
            return listaItens;
        } else {
            System.err.println("Banco vazio!");
            return null;
        }
    }

    private static ArrayList<ArrayList<Integer>> getIds() throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "select id_item,tipo_item from item order by id_item";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet result = ps.executeQuery();
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<Integer> tipos = new ArrayList<>();

        while (result.next()) {
            ids.add(result.getInt("id_item"));
            tipos.add(result.getInt("tipo_item"));
        }

        if (ids.size() > 0 && tipos.size() > 0) {
            ArrayList<ArrayList<Integer>> matriz = new ArrayList<>();
            matriz.add(ids);
            matriz.add(tipos);
            return matriz;
        }
        return null;
    }

    public static boolean deleteItemDB(int id_item) throws SQLException { //ainda é necessário testes para função
        Connection conn = FactoryConnection.createConnection();
        String sql = "delete from item where id_item = " + id_item;
        PreparedStatement ps = conn.prepareStatement(sql);
        int row = ps.executeUpdate();
        if (row > 0) {
            return true;
        }
        return false;
    }

    public static boolean emprestar(int id_item) throws SQLException {
        if (verificarQtdControl(id_item, true)) {
            Connection conn = FactoryConnection.createConnection();
            String sql = "update item set quantidade = quantidade-1 where id_item=" + id_item;
            PreparedStatement ps = conn.prepareStatement(sql);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean devolver(int id_item) throws SQLException {
        if (verificarQtdControl(id_item, false)) {
            Connection conn = FactoryConnection.createConnection();
            String sql = "update item set quantidade = quantidade+1 where id_item=" + id_item;
            PreparedStatement ps = conn.prepareStatement(sql);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                return true;
            }
        }
        return false;
    }

    private static boolean verificarQtdControl(int id_item, boolean emprestar) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "select quantidade,qtdcontrol from item where id_item=" + id_item;
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet result = ps.executeQuery();

        if (result.next()) {
            int qtd = result.getInt("quantidade");
            int qtdcontrol = result.getInt("qtdcontrol");

            if (emprestar) {
                if (qtd > 0) {
                    return true;
                }
                return false;
            }
            if (qtd < qtdcontrol) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static int getIdByCDU(String cdu) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "select id_item from item where cdu like '" + cdu + "'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet result = ps.executeQuery();

        if (result.next()) {
            return result.getInt("id_item");
        }
        return -1;
    }

    public static ArrayList<Item> getItemByTitulo(String titulo) throws SQLException {
        ArrayList<Item> items = getAllItemsDB(); //tem como fazer melhor
        ArrayList<Item> obj = new ArrayList<>();
        Item aux = null;
        for (int i = 0; i < items.size(); i++) {
            aux = items.get(i);
            if (aux.getTitulo().compareTo(titulo) == 0) {
                obj.add(aux);
            }
        }
        return obj;
    }

    public static void inserirTipoID(String descricao) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "INSERT INTO TipoID (descricao) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, descricao);
            stmt.executeUpdate();
        }
    }*/
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

    public static void inserirContrato(int idAlugador, int idSeguro, String dataInicio, String dataTermino, double valorContrato) throws SQLException {
        Connection conn = FactoryConnection.createConnection();
        String sql = "INSERT INTO Contrato (id_alugador, id_seguro, data_inicio, data_termino, valor_contrato) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAlugador);
            stmt.setInt(2, idSeguro);
            stmt.setString(3, dataInicio);
            stmt.setString(4, dataTermino);
            stmt.setDouble(5, valorContrato);
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
