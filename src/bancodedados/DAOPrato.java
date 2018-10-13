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
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemarestaurante.Ingredientes;
import sistemarestaurante.Prato;

/**
 *
 * @author User
 */
public class DAOPrato {
    private ConexaoBD conexao;

    public DAOPrato() {
                // cria o objeto para conexão com banco, porém não o inicializa
		// a conexão deve ser aberta e, consequentemente, fechada durante o envio de comandos
		// ao banco
		this.conexao = new ConexaoBD();
    }
    
    
	
    public void criarPrato(Prato prato) {
		
		conexao.conectar();

		try {
			PreparedStatement pst = conexao.getConexao().prepareStatement("insert into prato(nome,valor) values(?,?)");
			PreparedStatement pst2 = conexao.getConexao().prepareStatement("insert into PratoIngrediente(nomePrato,nomeIngrediente) values(?,?)");
                        
                        pst.setString(1, prato.getNome());
			pst.setInt(2, prato.getValor());
                        pst.execute();
                        for( Ingredientes ingrediente : prato.getIngredientes()){
                            pst2.setString(1, prato.getNome());
                            pst2.setString(2, ingrediente.getNome());
                            pst2.execute();
                        }
                        
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			conexao.desconectar();
		}}
     public ArrayList<Prato> verTodos() {
		// abrindo a conexão com o BD   
		conexao.conectar();
                DAOIngredientes daoingrediente = new DAOIngredientes();
                ArrayList<Prato> PratoList = new ArrayList<>();
		ResultSet resultado = conexao.executarSQL("select * from Prato");
		try {
			// para iterar sobre os resultados de uma consulta, deve-se utilizar o método next()
			while (resultado.next()) {
                                String nomePrato = resultado.getString("nome");
				int valor = resultado.getInt("valor");
                                List ingredientes = new ArrayList();
                                ResultSet resultado2 = conexao.executarSQL("select * from PratoIngrediente where nomePrato = \'" + nomePrato + "\'");
                                while(resultado2.next()){
                                    Ingredientes ingre = daoingrediente.buscarIngrediente(resultado2.getString("nomeIngrediente"));
                                    ingredientes.add(ingre);
                                }
                                PratoList.add(new Prato(nomePrato,ingredientes,valor));
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exceção é lançada
			conexao.desconectar();
		}
		return PratoList;
    
        }
    public Prato buscarPrato(String nomePrato) {
		conexao.conectar();
                DAOIngredientes daoingrediente = new DAOIngredientes();
		ResultSet resultado, resultado2;
        resultado = conexao.executarSQL("select * from Prato where nome = \'" + nomePrato + "\'");
        resultado2 = conexao.executarSQL("select nomeIngrediente from PratoIngrediente where nomePrato = \'" + nomePrato + "\'");
		Prato p = null;
		try {
			resultado.next();
                                String nome = resultado.getString("nome");
				int valor = resultado.getInt("valor");
                                List ingredientes = new ArrayList();
                                while(resultado2.next()){
                                    Ingredientes ingre = daoingrediente.buscarIngrediente(resultado2.getString("nomeIngrediente"));
                                    ingredientes.add(ingre);
                                }
				p = new Prato(nome,ingredientes,valor);
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			conexao.desconectar();
		}
		return p;
	}
}
