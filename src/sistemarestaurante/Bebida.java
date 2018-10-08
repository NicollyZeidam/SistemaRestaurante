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
public class Bebida implements Produto {
    private String nome;
    private int valor;
    private int calorias;
    private int quantidadeEstoque;
    private List<String> restricoes;

    public List<String> getRestricoes() {
        return restricoes;
    }

    public void setRestricoes(List<String> restricoes) {
        this.restricoes = restricoes;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Bebida(String nome, int valor, int calorias, int quantidadeEstoque, List restricoes) {
        this.nome = nome;
        this.valor = valor;
        this.calorias = calorias;
        this.quantidadeEstoque = quantidadeEstoque;
        this.restricoes = restricoes;
    }

    public Bebida() {
    }

    public Bebida(String nome) {
        this.nome = nome;
        this.restricoes = new ArrayList();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }
    
    @Override
    public boolean getIsEstoque() {
        return this.quantidadeEstoque > 0;
    }

    @Override
    public int getCalorias() {
        return this.calorias;
    }

    @Override
    public void removerEstoque(int quantidade) {
        this.quantidadeEstoque -= quantidade;
    }

    @Override
    public void verificarRestricoes(Usuario user) throws Exception {
        user.getRestricoes().forEach((String restricoesUser) -> {
            this.restricoes.stream().filter((restricoesBebida) -> (restricoesUser.equals(restricoesBebida))).forEachOrdered((_item) -> {
                throw new RuntimeException();
            });
        });
    }
    
}
