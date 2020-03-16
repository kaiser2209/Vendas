/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.conexao.ConnectionFactory;
import model.entidades.Cliente;

/**
 *
 * @author usuario.local
 */
public class ClienteDAO {
    public void salvar(Cliente c) throws SQLException {
        String sql = "INSERT INTO tbcliente (Nome, CPF, DataNascimento)"
                + " VALUE (?, ?, ?)";
        
        PreparedStatement stmt = ConnectionFactory.prepararSQL(sql);
        
        stmt.setString(1, c.getNome());
        stmt.setString(2, c.getCpf());
        stmt.setString(3, c.getDataNascimento().toString());
        
        stmt.executeUpdate();
        
        stmt.close();
    }
    
    public void editar(Cliente c) throws SQLException {
        String sql = "UPDATE tbcliente SET Nome = ?, CPF = ? DataNascimento = ? "
                + "where Codigo = ?";
        
        PreparedStatement stmt = ConnectionFactory.prepararSQL(sql);
        
        stmt.setString(1, c.getNome());
        stmt.setString(2, c.getCpf());
        stmt.setString(3, c.getDataNascimento().toString());
        stmt.setInt(4, c.getCodigo());
        
        stmt.executeUpdate();
        
        stmt.close();
    }
    
    public void excluir(Cliente c) throws SQLException {
        String sql = "DELETE FROM tbcliente WHERE codigo = ?";
        
        PreparedStatement stmt = ConnectionFactory.prepararSQL(sql);
        
        stmt.setInt(1, c.getCodigo());
        
        stmt.executeUpdate();
        
        stmt.close();
    }
    
    public ArrayList<Cliente> listar() throws SQLException {
        String sql = "SELECT * FROM tbcliente";
        
        PreparedStatement stmt = ConnectionFactory.prepararSQL(sql);
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Cliente> lista = new ArrayList<>();
        while(rs.next()) {
            Cliente c = new Cliente(
                rs.getInt("Codigo"),
                rs.getString("Nome"),
                rs.getString("CPF"),
                LocalDate.parse(rs.getString("DataNascimento"))
            );
            
            lista.add(c);
        }
        
        rs.close();
        stmt.close();
        
        return lista;
    }
}
