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
import sistemarestaurante.Bebida;
import sistemarestaurante.Ingredientes;

/**
 *
 * @author User
 */
public class DAOBebidas {
     private ConexaoBD conexao;
    
    public DAOBebidas() {
		// cria o objeto para conexão com banco, porém não o inicializa
		// a conexão deve ser aberta e, consequentemente, fechada durante o envio de comandos
		// ao banco
		this.conexao = new ConexaoBD();
	}
	
    public void criarBebida(Bebida bebida) {
		
		conexao.conectar();

		try {
			PreparedStatement pst = conexao.getConexao().prepareStatement("insert into Bebida(nome,valor,calorias,quantidadeEstoque) values(?,?,?,?)");
			PreparedStatement pst2 = conexao.getConexao().prepareStatement("insert into BebidasRestricoes(nomeBebida,nomeRestricoes) values(?,?)");
                        
                        pst.setString(1, bebida.getNome());
                        pst.setInt(2, bebida.getValor());
			pst.setInt(3, bebida.getCalorias());
			pst.setInt(4, bebida.getQuantidadeEstoque());
                        pst.execute();
                        for( String restricoes : bebida.getRestricoes()){
                            pst2.setString(1, bebida.getNome());
                            pst2.setString(2, restricoes);
                            pst2.execute();
                        }
                        
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			conexao.desconectar();
		}}
    public ArrayList<Bebida> verTodos() {
		ArrayList<Bebida> BebidaList = new ArrayList<>();
		// abrindo a conexão com o BD   
		conexao.conectar();
		ResultSet resultado = conexao.executarSQL("select * from Bebida");
		try {
			// para iterar sobre os resultados de uma consulta, deve-se utilizar o método next()
			while (resultado.next()) {
                                String nomeBebida = resultado.getString("nome");
                                int valor = resultado.getInt("valor");
				int calorias = resultado.getInt("calorias");
                                int quantidadeEstoque = resultado.getInt("quantidadeEstoque");
                                ResultSet resultado2 = conexao.executarSQL("select * from BebidasRestricoes where nomeBebida = \'" + nomeBebida + "\'");
                                List restricoes = new ArrayList();
                                while(resultado2.next()){
                                    String restricao = resultado2.getString("nomeRestricoes");
                                    restricoes.add(restricao);
                                }
                                BebidaList.add(new Bebida(nomeBebida, valor,calorias,quantidadeEstoque,restricoes));
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exceção é lançada
			conexao.desconectar();
		}
		return BebidaList;
    
        }
    public Bebida buscarBebida(String nomeBebida) {
		conexao.conectar();
		ResultSet resultado, resultado2;
        resultado = conexao.executarSQL("select * from Bebida where nome = \'" + nomeBebida + "\'");
        resultado2 = conexao.executarSQL("select nomeRestricoes from BebidasRestricoes where nomeBebida = \'" + nomeBebida + "\'");
		Bebida b = null;
		try {
			resultado.next();
                                String nome = resultado.getString("nome");
                                int valor = resultado.getInt("valor");
				int calorias = resultado.getInt("calorias");
                                int quantidadeEstoque = resultado.getInt("quantidadeEstoque");
                                List restricoes = new ArrayList();
                                while(resultado2.next()){
                                    String restricao = resultado2.getString("nomeRestricoes");
                                    restricoes.add(restricao);
                                }
				b = new Bebida(nome,valor,calorias,quantidadeEstoque,restricoes);
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			conexao.desconectar();
		}
		return b;
	}
    public void retirarEstoque(String nome, int quantidade) {
		// abrindo a conexão com o BD
		conexao.conectar();
		
		try {
			PreparedStatement stm = conexao.getConexao().prepareStatement("UPDATE bebida SET quantidadeEstoque = quantidadeEstoque-\'" + quantidade + "\' WHERE nome = \'" + nome + "\'");
			stm.execute();
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exceção é lançada
			conexao.desconectar();
		}
	}
}
