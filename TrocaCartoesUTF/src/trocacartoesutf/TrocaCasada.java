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
public class TrocaCasada implements Runnable{

    private boolean resultadoTroca;
    private final String cartaCol;
    private final String cartaTerc;
    private final String cartaCasadaCol;
    private final String cartaCasadaTerc;

    public TrocaCasada(String cartaCol, String cartaTerc, String cartaCasadaCol, String cartaCasadaTerc) {
        this.cartaCol = cartaCol;
        this.cartaTerc = cartaTerc;
        this.cartaCasadaCol = cartaCasadaCol;
        this.cartaCasadaTerc = cartaCasadaTerc;
    }
    
    @Override
    public void run() {
        try {
            resultadoTroca = ColImpl.refGer.trocaCasada(cartaCol, cartaTerc, cartaCasadaCol, cartaCasadaTerc);
        } catch (RemoteException ex) {
            Logger.getLogger(TrocaCasada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
