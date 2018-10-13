/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemarestaurante.Usuario;

/**
 *
 * @author User
 */
public class DAOUsuario {
    private final ConexaoBD conexao;

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
    public Usuario buscarUsuario(String nomeUser){
        conexao.conectar();
		ResultSet resultado, resultado2;
        resultado = conexao.executarSQL("select * from Usuario where nome = \'" + nomeUser + "\'");
        resultado2 = conexao.executarSQL("select nomeRestricoes from usuarioRestricao where nomeUsuario = \'" + nomeUser + "\'");
		Usuario user = null;
		try {
			resultado.next();
                                String nome = resultado.getString("nome");
				String login = resultado.getString("login");
                                String senha = resultado.getString("senha");
                                String numeroDaConta = resultado.getString("numeroDaConta");
                                List restricoes = new ArrayList();
                                while(resultado2.next()){
                                    String restricao = resultado2.getString("nomeRestricoes");
                                    restricoes.add(restricao);
                                }
				user = new Usuario(nome,login,senha,numeroDaConta,restricoes);
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			conexao.desconectar();
		}
		return user;
    }
}
