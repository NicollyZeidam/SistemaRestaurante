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
    private List<String> restricoes;

    public Usuario(String nome, List<String> restricoes) {
        this.nome = nome;
        this.restricoes = restricoes;
    }

    public Usuario() {
    }

    public Usuario(String nome) {
        this.nome = nome;
        this.restricoes = new ArrayList();
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
    
}
