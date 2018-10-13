/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemarestaurante.Usuario;

/**
 *
 * @author User
 */
public class DAOUsuario {
    private ConexaoBD conexao;

    public DAOUsuario() {
        this.conexao = new ConexaoBD();
    }
    public void criarUsuario(Usuario user){
        conexao.conectar();
        try {
            PreparedStatement pst = conexao.getConexao().prepareStatement("insert into usuario(nome,login,senha,numeroDaConta) values(?,?,?,?)");
            PreparedStatement pst2 = conexao.getConexao().prepareStatement("insert into usuarioRestricao(nomeUsuario, nomeRestricoes) values(?,?)");
            pst.setString(1, user.getNome());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getSenha());
            pst.setString(4, user.getNumeroDaConta());
            pst.execute();
            for( String restricoes : user.getRestricoes()){
                            pst2.setString(1, user.getNome());
                            pst2.setString(2, restricoes);
                            pst2.execute();
                        }
            
            
        } catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			conexao.desconectar();
		}}
}
