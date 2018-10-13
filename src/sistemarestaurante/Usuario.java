/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarestaurante;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Usuario {
    private String nome;
    private String login;
    private String senha;
    private String numeroDaConta;
    private List<String> restricoes;

    public Usuario(String nome, String login, String senha, String numeroDaConta, List<String> restricoes) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.numeroDaConta = numeroDaConta;
        this.restricoes = restricoes;
    }

    

    public Usuario() {
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
        this.restricoes = new ArrayList();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNumeroDaConta() {
        return numeroDaConta;
    }

    public void setNumeroDaConta(String numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getRestricoes() {
        return restricoes;
    }

    public void setRestricoes(List<String> restricoes) {
        this.restricoes = restricoes;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", restricoes=" + restricoes + '}';
    }
    
}
