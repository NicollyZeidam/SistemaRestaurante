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
import sistemarestaurante.Ingredientes;

/**
 *
 * @author jhonatanPovoas
 */
public class DAOIngredientes {
     private ConexaoBD conexao;
    
    public DAOIngredientes() {
		// cria o objeto para conexão com banco, porém não o inicializa
		// a conexão deve ser aberta e, consequentemente, fechada durante o envio de comandos
		// ao banco
		this.conexao = new ConexaoBD();
	}
	
    public void criarIngredientes(Ingredientes ingrediente) {
		
		conexao.conectar();

		try {
			PreparedStatement pst = conexao.getConexao().prepareStatement("insert into ingrediente(nome,calorias,quantidadeEstoque) values(?,?,?)");
			PreparedStatement pst2 = conexao.getConexao().prepareStatement("insert into ingredienteRestricoes(nomeIngrediente,nomeRestricoes) values(?,?)");
                        
                        pst.setString(1, ingrediente.getNome());
			pst.setInt(2, ingrediente.getCalorias());
			pst.setInt(3, ingrediente.getQuantidadeEstoque());
                        for( String restricoes : ingrediente.getRestricoes()){
                            pst2.setString(1, ingrediente.getNome());
                            pst2.setString(2, restricoes);
                        }
                        
			pst.execute();
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			conexao.desconectar();
		}}
    public ArrayList<Ingredientes> verTodos() {
		ArrayList<Ingredientes> IngredientesList = new ArrayList<>();
		// abrindo a conexão com o BD   
		conexao.conectar();
		ResultSet resultado = conexao.executarSQL("select * from ingrediente");
		try {
			// para iterar sobre os resultados de uma consulta, deve-se utilizar o método next()
			while (resultado.next()) {
                                String nomeIngrediente = resultado.getString("nome");
				int calorias = resultado.getInt("calorias");
                                int quantidadeEstoque = resultado.getInt("quantidadeEstoque");
                                ResultSet resultado2 = conexao.executarSQL("select * from ingredienteResticoes where nomeIngrediente = \'" + nomeIngrediente + "\'");
                                List restricoes = new ArrayList();
                                while(resultado2.next()){
                                    String restricao = resultado2.getString("nomeRestricoes");
                                    restricoes.add(restricao);
                                }
                                IngredientesList.add(new Ingredientes(nomeIngrediente,calorias,quantidadeEstoque,restricoes));
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exceção é lançada
			conexao.desconectar();
		}
		return IngredientesList;
    
        }
    /*public Atividade buscarAtividade(int cod_atividade) {
		conexao.conectar();
		ResultSet resultado;
        resultado = conexao.executarSQL("select * from atividade where cod_atividade = \'" + cod_atividade + "\'");
		Atividade atv = null;
                DAODisciplina disciplina = new DAODisciplina();
                DAOTag tag = new DAOTag();
		try {
			resultado.next();
                                int cod_atividadeN = resultado.getInt("cod_atividade");
				Tag idTag = tag.buscarTag(resultado.getString("tag"));
				Disciplina idDisciplina = disciplina.buscarDisciplina(resultado.getString("disciplina"));
                                String deadline = resultado.getString("deadline");
				String disc = resultado.getString("disc");
                                String status = resultado.getString("status");
				atv = new Atividade(idTag,deadline,idDisciplina,disc,cod_atividadeN,status);
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			conexao.desconectar();
		}
		return atv;
	}*/
    public void retirarEstoque(String nome, int quantidade) {
		// abrindo a conexão com o BD
		conexao.conectar();
		
		try {
			PreparedStatement stm = conexao.getConexao().prepareStatement("UPDATE produtos SET quantidadeEstoque = quantidadeEstoque-\'" + quantidade + "\' WHERE nome = \'" + nome + "\'");
			stm.execute();
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exceção é lançada
			conexao.desconectar();
		}
	}
}