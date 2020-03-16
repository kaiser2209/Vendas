/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.fornecedor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.conexao.ConnectionFactory;
import model.entidades.Fornecedor;

/**
 *
 * @author usuario.local
 */
public class FornecedorDAO {
    public void salvar(Fornecedor f) throws SQLException {
        String sql = "INSERT INTO tbfornecedor (Nome, CNPJ) "
                + "VALUE (?, ?)";
        
        PreparedStatement stmt = ConnectionFactory.prepararSQL(sql);
        
        stmt.setString(1, f.getNome());
        stmt.setString(2, f.getCnpj());
        
        stmt.executeUpdate();
        
        stmt.close();
    }
    
    public void editar(Fornecedor f) throws SQLException {
        String sql = "UPDATE tbfornecedor SET Nome = ?, CNPJ = ? "
                + "WHERE Codigo = ?";
        
        PreparedStatement stmt = ConnectionFactory.prepararSQL(sql);
        
        stmt.setString(1, f.getNome());
        stmt.setString(2, f.getCnpj());
        stmt.setInt(3, f.getCodigo());
        
        stmt.executeUpdate();
        
        stmt.close();
    }
    
    public void excluir(Fornecedor f) throws SQLException {
        String sql = "DELETE FROM tbfornecedor WHERE Codigo = ?";
        
        PreparedStatement stmt = ConnectionFactory.prepararSQL(sql);
        
        stmt.setInt(1, f.getCodigo());
        
        stmt.executeUpdate();
        
        stmt.close();
    }
    
    public ArrayList<Fornecedor> listar() throws SQLException {
        String sql = "SELECT * FROM tbfornecedor";
        
        PreparedStatement stmt = ConnectionFactory.prepararSQL(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Fornecedor> lista = new ArrayList<>();
        
        while(rs.next()) {
            Fornecedor f = new Fornecedor(
                rs.getInt("Codigo"),
                rs.getString("Nome"),
                rs.getString("CNPJ")
            );
            
            lista.add(f);
        }
        
        rs.close();
        stmt.close();
        
        return lista;
    }
}
