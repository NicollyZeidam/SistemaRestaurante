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
public class Pedido {
    private Usuario user;
    private List<Produto> produtoPedido;

    public Pedido(Usuario user, List<Produto> produtoPedido) {
        this.user = user;
        this.produtoPedido = produtoPedido;
    }

    public Pedido() {
    }

    public Pedido(Usuario user) {
        this.user = user;
        this.produtoPedido = new ArrayList();
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<Produto> getProdutoPedido() {
        return produtoPedido;
    }

    public void setProdutoPedido(List<Produto> produtoPedido) {
        this.produtoPedido = produtoPedido;
    }
    
    public int getValorPedido(){
        int valorTotal = 0;
        //eu preferir usar o método usual para perdorer nesse caso, porém outros são mais eficientes
        
        /*
        
        valorTotal = this.produtoPedido.stream().map((produto) -> produto.getValor()).reduce(valorTotal, Integer::sum);
        return valorTotal;
        
        */
        for(Produto produto: this.produtoPedido){
            valorTotal += produto.getValor();
        }
        return valorTotal;
    }
    
    public void verificarRestricoes(){
        try{
            for(Produto produto: this.produtoPedido){
                produto.verificarRestricoes(user);
            }
        }catch(Exception ex){
            throw new RuntimeException();
        }
    }
    
    public int calcularCalorias(){
        int caloriasTotal = 0;
        //eu preferir usar o método usual para perdorer nesse caso, porém outros são mais eficientes
        
        /*
        
        caloriasTotal = this.produtoPedido.stream().map((produto) -> produto.getCalorias()).reduce(caloriasTotal, Integer::sum);
        return caloriasTotal;
        
        */
        for(Produto produto : this.produtoPedido){
            caloriasTotal += produto.getCalorias();
        }
        return caloriasTotal;
    }
    public void addProduto(Produto produto){
        this.produtoPedido.add(produto);
    }

    @Override
    public String toString() {
        return "Pedido{" + "user=" + user + ", produtoPedido=" + produtoPedido + '}';
    }
}
