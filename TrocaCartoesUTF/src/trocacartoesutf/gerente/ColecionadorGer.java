/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf.gerente;

import java.util.Random;
import trocacartoesutf.interfaces.InterfaceCol;

/**
 *
 * @author Lucas
 */
public class ColecionadorGer {

    private String nomeCol;
    private InterfaceCol refCol;
    private CartaGer cartaA;
    private CartaGer cartaB;
    private CartaGer cartaC;

    public ColecionadorGer(InterfaceCol refCol, String A, String B, String C) {
        Random numeroCli = new Random();
        int num = numeroCli.nextInt(1000);
        nomeCol = "Col " + num;
        this.refCol = refCol;
        cartaA = new CartaGer(A);
        cartaB = new CartaGer(B);
        cartaC = new CartaGer(C);
    }

    public String getNomeCol() {
        return nomeCol;
    }
    
    public InterfaceCol getRefCol() {
        return refCol;
    }

    public void setRefCol(InterfaceCol refCol) {
        this.refCol = refCol;
    }

    public CartaGer getCartaA() {
        return cartaA;
    }

    public void setCartaA(CartaGer cartaA) {
        this.cartaA = cartaA;
    }

    public CartaGer getCartaB() {
        return cartaB;
    }

    public void setCartaB(CartaGer cartaB) {
        this.cartaB = cartaB;
    }

    public CartaGer getCartaC() {
        return cartaC;
    }

    public void setCartaC(CartaGer cartaC) {
        this.cartaC = cartaC;
    }

    @Override
    public String toString() {
        return "ColecionadorGer{" + "refCol=" + refCol + '}';
    }
    
    
}
