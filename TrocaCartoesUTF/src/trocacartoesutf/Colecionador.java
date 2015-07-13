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
    private Carta cartaUm;
    private Carta cartaDois;
    private Carta cartaTres;
    
    public Colecionador() {
        nomeCol = null;
        cartaUm = new Carta("A");
        cartaDois = new Carta("B");
        cartaTres = new Carta("C");       
    }
    
    
    public String[] consultaColecao(){
        String[] colecao = new String[3];
        colecao[0] = getCartaUm().getNomeCarta();
        colecao[1] = getCartaDois().getNomeCarta();
        colecao[2] = getCartaTres().getNomeCarta();
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
     * @return the cartaUm
     */
    public Carta getCartaUm() {
        return cartaUm;
    }

    /**
     * @param cartaA the cartaUm to set
     */
    public void setCartaUm(Carta cartaA) {
        this.cartaUm = cartaA;
    }

    /**
     * @return the cartaDois
     */
    public Carta getCartaDois() {
        return cartaDois;
    }

    /**
     * @param cartaDois the cartaDois to set
     */
    public void setCartaDois(Carta cartaDois) {
        this.cartaDois = cartaDois;
    }

    /**
     * @return the cartaTres
     */
    public Carta getCartaTres() {
        return cartaTres;
    }

    /**
     * @param cartaTres the cartaTres to set
     */
    public void setCartaTres(Carta cartaTres) {
        this.cartaTres = cartaTres;
    }
    
}
