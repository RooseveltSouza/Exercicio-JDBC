/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.agenda;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Sr. Roosevelt
 */
public class ContatoDao {
    private final String INSERIR = "insert into contato (nome, sobrenome, matricula, cpf, endereço, telefone, email, data) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String ATUALIZAR = "update contato set nome=?, sobrenome=?, matricula=?, endereço=?, telefone=?, email=?, data=? where cpf=?"; 
    private final String DELETAR = "delete from contato where matricula=?";
    private final String PESQUISAR = "select * from contato where cpf=?";
    private final String LISTAR = "select * from contato";
    
    
    public void inserirContato(Contato contato){
        if(contato != null){
            Connection con = null;
            
            
            try {
                con = conexaoDao.getConexao();
                PreparedStatement psmt;
                psmt = con.prepareStatement(INSERIR);
                
                psmt.setString(1, contato.getNome());
                psmt.setString(2, contato.getSobrenome());
                psmt.setInt(3, contato.getMatricula());
                psmt.setInt(4, contato.getCpf());
                psmt.setString(5, contato.getEndereco());
                psmt.setString(6, contato.getTelefone());
                psmt.setString(7, contato.getEmail());
                psmt.setString(8, contato.getData());
                
                psmt.execute();
                conexaoDao.fecharConexao(con, psmt);
                JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "ERRO! " + e.getMessage());
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "O contato enviado por paramentro esta vazio");
        }
    }
    
    public void atualizarContato(Contato contato, int cpf){
        if(contato != null){
            Connection con = null;
            
            
            try {
                con = conexaoDao.getConexao();
                PreparedStatement psmt;
                psmt = con.prepareStatement(ATUALIZAR);
                
                
                psmt.setString(1, contato.getNome());
                psmt.setString(2, contato.getSobrenome());
                psmt.setInt(3, contato.getMatricula());
                psmt.setString(4, contato.getEndereco());
                psmt.setString(5, contato.getTelefone());
                psmt.setString(6, contato.getEmail());
                psmt.setString(7, contato.getData());
                psmt.setInt(8, cpf);
                psmt.execute();
                conexaoDao.fecharConexao(con, psmt);
                
                JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERRO! " + e.getMessage());
            }    
        }else {
            JOptionPane.showMessageDialog(null, "O contato enviado por parâmetro está vazio");
        }
        
    }
    
    public void deletarContato(int matricula){
        Connection con = null;
            try {
                con = conexaoDao.getConexao();
                PreparedStatement psmt;
                psmt = con.prepareStatement(DELETAR);
                
                psmt.setInt(1, matricula);
                psmt.execute();
                
                conexaoDao.fecharConexao(con, psmt);
                JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERRO! " + e.getMessage());
            }
        }
    
    
    public List<Contato> getContatos() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Contato> contatos = new ArrayList<Contato>();
        try {
            conn = conexaoDao.getConexao();
            pstm = conn.prepareStatement(LISTAR);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato();
 
                
                contato.setNome(rs.getString("nome"));
                contato.setSobrenome(rs.getString("sobrenome"));
                contato.setMatricula(rs.getInt("matricula"));
                contato.setCpf(rs.getInt("cpf"));
                contato.setEndereco(rs.getString("endereço"));
                contato.setTelefone(rs.getString("telefone"));
                contato.setEmail(rs.getString("email"));
                contato.setData(rs.getString("data"));
           
                contatos.add(contato);
            }
            conexaoDao.fecharConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar contatos" + e.getMessage());
        }
        return contatos;
    }
    
}

