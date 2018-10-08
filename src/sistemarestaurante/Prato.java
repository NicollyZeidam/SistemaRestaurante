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
public class Prato implements Produto {
    private String nome;
    private List<Ingredientes> ingredientes;
    private int valor;

    public Prato(String nome, List<Ingredientes> ingredientes, int valor) {
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.valor = valor;
    }

    public Prato() {
    }

    public Prato(String nome) {
        this.nome = nome;
        this.ingredientes = new ArrayList();
        this.valor = 0;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Ingredientes> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingredientes> ingredientes) {
        this.ingredientes = ingredientes;
    }
    
    @Override
    public boolean getIsEstoque() {
        
        return this.ingredientes.stream().noneMatch((i) -> (i.getQuantidadeEstoque()<=0));
        
    }

    public int getValor() {
        return this.valor;
    }

    @Override
    public int getCalorias() {
        int caloriasTotal = 0;
        caloriasTotal = this.ingredientes.stream().map((x) -> x.getCalorias()).map((caloriasProduto) -> caloriasProduto).reduce(caloriasTotal, Integer::sum);
        return caloriasTotal;
    }

    @Override
    public void removerEstoque(int quantidade) {
        this.ingredientes.forEach((Ingredientes i) -> {
            int quantidadeEstoque = i.getQuantidadeEstoque();
            quantidadeEstoque -= quantidade;
            i.setQuantidadeEstoque(quantidadeEstoque);
        });
    }

    @Override
    public void verificarRestricoes(Usuario user) throws Exception {
        user.getRestricoes().forEach((String restricoesUser) -> {
            this.ingredientes.forEach((ingrediente) -> {
                for (String restricoesIngre : ingrediente.getRestricoes()) {
                    if(restricoesUser.equals(restricoesIngre)){
                        throw new RuntimeException();
                    }
                }
            });
        });
    }
    
    
}
