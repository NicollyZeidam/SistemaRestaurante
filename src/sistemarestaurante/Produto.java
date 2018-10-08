/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarestaurante;

/**
 *
 * @author User
 */
public interface Produto {
    public abstract boolean getIsEstoque();
    public abstract int getCalorias();
    public abstract int getValor();
    public abstract void removerEstoque(int quantidade);
    public abstract void verificarRestricoes(Usuario user) throws Exception;
    
}
