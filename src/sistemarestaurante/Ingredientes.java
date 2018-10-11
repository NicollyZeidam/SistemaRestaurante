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
public class Ingredientes {
    private String nome;
    private int calorias;
    private int quantidadeEstoque;
    private List<String> restricoes;

    public Ingredientes(String nome, int calorias, int quantidadeEstoque, List<String> restricoes) {
        this.nome = nome;
        this.calorias = calorias;
        this.quantidadeEstoque = quantidadeEstoque;
        this.restricoes = restricoes;
    }

    public Ingredientes() {
    }

    public Ingredientes(String nome) {
        this.nome = nome;
        this.restricoes = new ArrayList();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public List<String> getRestricoes() {
        return restricoes;
    }

    public void setRestricoes(List<String> restricoes) {
        this.restricoes = restricoes;
    }

    @Override
    public String toString() {
        return "Ingredientes{" + "nome=" + nome + ", calorias=" + calorias + ", quantidadeEstoque=" + quantidadeEstoque + ", restricoes=" + restricoes + '}';
    }
    
    
}
