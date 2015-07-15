/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf;

import java.util.logging.Level;
import java.util.logging.Logger;
import static trocacartoesutf.ColImpl.TransacaoMap;
import static trocacartoesutf.Transacao.EFETIVADO;

/**
 *
 * @author Lucas
 */
public class ContadorTransacao implements Runnable{

    private int numTrans;

    public ContadorTransacao(int transacao) {
        this.numTrans = transacao;
    }
    
    @Override
    public void run() {
        try {

            Thread.sleep(7000);
            Transacao trans = TransacaoMap.get(numTrans);
            if(trans.getStatus() != EFETIVADO){
                trans.abortar();
                System.out.println("\nESTADO TRANSACAO NA THREAD "+ trans.getStatus());
            }
           
        } catch (InterruptedException ex) {
            Logger.getLogger(ContadorTransacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
