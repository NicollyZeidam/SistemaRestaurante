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
 * @author User
 */
public class DAOResticoes {
    private ConexaoBD conexao;
    
    public DAOResticoes() {
		// cria o objeto para conexão com banco, porém não o inicializa
		// a conexão deve ser aberta e, consequentemente, fechada durante o envio de comandos
		// ao banco
		this.conexao = new ConexaoBD();
	}
	
    public void criarResticao(String resticao) {
		
		conexao.conectar();

		try {
			PreparedStatement pst = conexao.getConexao().prepareStatement("insert into restricoes values(?)");
			
                        
                        pst.setString(1, resticao);


			pst.execute();

		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			conexao.desconectar();
		}}
    public ArrayList<String> verTodos() {
		ArrayList<String> ListRestricoes = new ArrayList<>();
		// abrindo a conexão com o BD   
		conexao.conectar();
		ResultSet resultado = conexao.executarSQL("select * from restricoes");
		try {
			// para iterar sobre os resultados de uma consulta, deve-se utilizar o método next()
			while (resultado.next()) {
                                String nomeIngrediente = resultado.getString("nome");
				int calorias = resultado.getInt("calorias");
                                ListRestricoes.add(nomeIngrediente);
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// o banco deve ser desconectado, mesmo quando a exceção é lançada
			conexao.desconectar();
		}
		return ListRestricoes;
    
        }
}
