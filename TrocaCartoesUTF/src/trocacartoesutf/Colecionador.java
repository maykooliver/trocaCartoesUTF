/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf;

import trocacartoesutf.interfaces.InterfaceCol;

/**
 *
 * @author Mayko
 */
public class Colecionador {

    private String nomeCol;
    private Carta cartaA;
    private Carta cartaB;
    private Carta cartaC;
    
    public Colecionador() {
        nomeCol = null;
        cartaA = new Carta("A");
        cartaB = new Carta("B");
        cartaC = new Carta("C");       
    }
    
    
    public String[] consultaColecao(){
        String[] colecao = new String[3];
        colecao[0] = getCartaA().getNomeCarta();
        colecao[1] = getCartaB().getNomeCarta();
        colecao[2] = getCartaC().getNomeCarta();
        return colecao;
    }
    
    public boolean trocarCartoes(InterfaceCol cli, String carta){
        return false;
    }

    /**
     * @return the nomeCol
     */
    public String getNomeCol() {
        return nomeCol;
    }

    /**
     * @param nomeCol the nomeCol to set
     */
    public void setNomeCol(String nomeCol) {
        this.nomeCol = nomeCol;
    }  

    /**
     * @return the cartaA
     */
    public Carta getCartaA() {
        return cartaA;
    }

    /**
     * @param cartaA the cartaA to set
     */
    public void setCartaA(Carta cartaA) {
        this.cartaA = cartaA;
    }

    /**
     * @return the cartaB
     */
    public Carta getCartaB() {
        return cartaB;
    }

    /**
     * @param cartaB the cartaB to set
     */
    public void setCartaB(Carta cartaB) {
        this.cartaB = cartaB;
    }

    /**
     * @return the cartaC
     */
    public Carta getCartaC() {
        return cartaC;
    }

    /**
     * @param cartaC the cartaC to set
     */
    public void setCartaC(Carta cartaC) {
        this.cartaC = cartaC;
    }
    
}
