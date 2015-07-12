/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf.gerente;

import trocacartoesutf.interfaces.InterfaceCol;

/**
 *
 * @author Lucas
 */
public class ColecionadorGer {
    
    InterfaceCol refCol;
    CartaGer cartaA;
    CartaGer cartaB;
    CartaGer cartaC;

    public ColecionadorGer(InterfaceCol refCol, String A, String B, String C) {
        this.refCol = refCol;
        cartaA = new CartaGer(A);
        cartaB = new CartaGer(B);
        cartaC = new CartaGer(C);
    }
    
}
