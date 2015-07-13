/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf.gerente;

/**
 *
 * @author Mayko
 */
public class CartaGer {
    
    private String nomeCarta;
    private boolean bloqueado;

    public CartaGer(String nomeCarta) {
        this.nomeCarta = nomeCarta;
        this.bloqueado = false;
    }

    public String getNomeCarta() {
        return nomeCarta;
    }

    public void setNomeCarta(String nomeCarta) {
        this.nomeCarta = nomeCarta;
    }
    
    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }
        
}
