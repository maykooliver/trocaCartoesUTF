/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class TrocaSimples implements Runnable{

    private boolean resultadoTroca;
    private final String cartaCol;
    private final String cartaTerc;

    public TrocaSimples(String cartaCol, String cartaTerc) {
        this.cartaCol = cartaCol;
        this.cartaTerc = cartaTerc;
    }
    
    @Override
    public void run() {
        try {
            resultadoTroca = ColImpl.refGer.trocaSimples(cartaCol, cartaTerc);
        } catch (RemoteException ex) {
            Logger.getLogger(TrocaSimples.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
