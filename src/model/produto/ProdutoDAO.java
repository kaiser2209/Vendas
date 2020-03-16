/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.conexao.ConnectionFactory;
import model.entidades.Produto;

/**
 *
 * @author usuario.local
 */
public class ProdutoDAO {
    public void salvar(Produto p) throws SQLException {
        String sql = "INSERT INTO tbproduto (Nome, PrecoCompra, PrecoVenda) "
                + "VALUE (?, ?, ?)";
        
        PreparedStatement stmt = ConnectionFactory.prepararSQL(sql);
        
        stmt.setString(1, p.getNome());
        stmt.setFloat(2, p.getPrecoCompra());
        stmt.setFloat(3, p.getPrecoVenda());
        
        stmt.executeUpdate();
        
        stmt.close();
    }
    
    public void editar(Produto p) throws SQLException {
        String sql = "UPDATE tbproduto SET Nome = ?, PrecoCompra = ?, "
                + "PrecoVenda = ? WHERE Codigo = ?";
        
        PreparedStatement stmt = ConnectionFactory.prepararSQL(sql);
        
        stmt.setString(1, p.getNome());
        stmt.setFloat(2, p.getPrecoCompra());
        stmt.setFloat(3, p.getPrecoVenda());
        stmt.setInt(4, p.getCodigo());
        
        stmt.executeUpdate();
        
        stmt.close();
    }
    
    public void excluir(Produto p) throws SQLException {
        String sql = "DELETE FROM tbproduto WHERE Codigo = ?";
        
        PreparedStatement stmt = ConnectionFactory.prepararSQL(sql);
        
        stmt.setInt(1, p.getCodigo());
        
        stmt.executeUpdate();
        
        stmt.close();
    }
    
    public ArrayList<Produto> listar() throws SQLException {
        String sql = "SELECT * FROM tbproduto";
        
        PreparedStatement stmt = ConnectionFactory.prepararSQL(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Produto> lista = new ArrayList<>();
        
        while(rs.next()) {
            Produto p = new Produto(
                    rs.getInt("Codigo"),
                    rs.getString("Nome"),
                    rs.getFloat("PrecoCompra"),
                    rs.getFloat("PrecoVenda")
            );
            
            lista.add(p);
        }
        
        rs.close();
        stmt.close();
        
        return lista;
    }
}
