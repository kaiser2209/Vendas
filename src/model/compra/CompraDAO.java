/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.compra;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import model.conexao.ConnectionFactory;
import model.entidades.Compra;
import model.fornecedor.FornecedorDAO;

/**
 *
 * @author usuario.local
 */
public class CompraDAO {
    private FornecedorDAO fDao;
    
    public CompraDAO() {
        fDao = new FornecedorDAO();
    }
    public void salvar(Compra c) throws SQLException {
        String sql = "INSERT INTO tbcompra (CodigoFornecedor, DataCompra, "
                + "ValorTotal, Situacao) VALUE (?, ?, ?, ?)";
        
        PreparedStatement stmt = ConnectionFactory.prepararSQL(sql);
        
        stmt.setInt(1, c.getFornecedor().getCodigo());
        stmt.setString(2, c.getDataCompra().toString());
        stmt.setFloat(3, c.getValorTotal());
        stmt.setInt(4, c.getSituacao());
        
        stmt.executeUpdate();
        
        stmt.close();
    }
    
    public void editar(Compra c) throws SQLException {
        String sql = "UPDATE tbcompra SET CodigoFornecedor = ?, DataCompra = ?, "
                + "ValorTotal = ?, Situacao = ? WHERE Codigo = ?";
        
        PreparedStatement stmt = ConnectionFactory.prepararSQL(sql);
        
        stmt.setInt(1, c.getFornecedor().getCodigo());
        stmt.setString(2, c.getDataCompra().toString());
        stmt.setFloat(3, c.getValorTotal());
        stmt.setInt(4, c.getSituacao());
        stmt.setInt(5, c.getCodigo());
        
        stmt.executeUpdate();
        
        stmt.close();
    }
    
    public void excluir(Compra c) throws SQLException {
        String sql = "DELETE FROM tbcompra WHERE Codigo = ?";
        
        PreparedStatement stmt = ConnectionFactory.prepararSQL(sql);
        
        stmt.setInt(1, c.getCodigo());
        
        stmt.executeUpdate();
        
        stmt.close();
    }
    
    public ArrayList<Compra> listar() throws SQLException {
        String sql = "SELECT * FROM tbcompra";
        
        PreparedStatement stmt = ConnectionFactory.prepararSQL(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Compra> lista = new ArrayList<>();
        
        while(rs.next()) {
            Compra c = new Compra(
                rs.getInt("Codigo"),
                fDao.buscaPeloCodigo(rs.getInt("CodigoFornecedor")),
                LocalDate.parse(rs.getString("DataCompra")),
                rs.getFloat("ValorTotal"),
                rs.getInt("Situacao")
            );
            
            lista.add(c);
        }
        
        rs.close();
        stmt.close();
        
        return lista;
    }
}
