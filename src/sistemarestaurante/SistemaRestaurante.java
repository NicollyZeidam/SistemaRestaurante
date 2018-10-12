/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarestaurante;

import bancodedados.DAOBebidas;
import bancodedados.DAOIngredientes;
import bancodedados.DAOResticoes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class SistemaRestaurante {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
       Ingredientes i1 = new Ingredientes("BOI");
       i1.setCalorias(60);
       Ingredientes i2 = new Ingredientes("Pão");
       i2.setCalorias(10);
       i2.setQuantidadeEstoque(2);
       i1.setQuantidadeEstoque(2);
       List ingredientes = new ArrayList();
       ingredientes.add(i2);
       ingredientes.add(i1);
       DAOResticoes daoRestricoes = new DAOResticoes();
       
       
       //daoRestricoes.criarResticao("HIPER TENSÃO");
       
       
       List restricoes = new ArrayList();
       restricoes.add("Diabetis");
       restricoes.add("HIPER TENSÃO");
       i1.setRestricoes(restricoes);
       Usuario user = new Usuario("Jhonatan",new ArrayList());
       DAOBebidas b = new DAOBebidas();
       Bebida b1 = new Bebida("SUCO",7,100,5,restricoes);
       System.out.println(b.buscarBebida("SUCO"));
       b.retirarEstoque("SUCO", 1);
       System.out.print(b.buscarBebida("SUCO"));
       //DAOIngredientes daoingredientes = new DAOIngredientes();
       //System.out.println(daoingredientes.buscarIngrediente("Pão"));
       //daoingredientes.criarIngredientes(i1);
       //daoingredientes.retirarEstoque("Pão", 1);

       
       Prato p1 = new Prato("Pão de milho",ingredientes,500);
       Pedido pedido1 = new Pedido(user);
       pedido1.addProduto(p1);
       pedido1.addProduto(b1);
       try{
           pedido1.verificarRestricoes();
       }catch(RuntimeException ex){
           System.out.print("Ele é diabetico");
       }
       //System.out.println("CALORIA:"+pedido1.calcularCalorias());
       //.out.println("VALOR:"+pedido1.getValorPedido());
       
       ///p1.removerEstoque(1);
       //System.out.println(daoRestricoes);
       //System.out.print(i1.getQuantidadeEstoque());
    }
    
}
