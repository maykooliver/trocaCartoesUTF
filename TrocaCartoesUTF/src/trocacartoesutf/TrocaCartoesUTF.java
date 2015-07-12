/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf;

import trocacartoesutf.interfaces.InterfaceGer;
import trocacartoesutf.gerente.GerImpl;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mayko
 */
public class TrocaCartoesUTF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, NotBoundException {

                
        InterfaceGer refGer = null;
        
        Registry referenciaServicoNomes;
        
        referenciaServicoNomes = LocateRegistry.getRegistry("localhost", 1099);
        
        boolean gerAtivo = false;
        
        try
        {
            /* Verifica se o gerente já está ativo */
            refGer = (InterfaceGer) referenciaServicoNomes.lookup("Troca de Cartas");
            gerAtivo = true;
            
        }catch(RemoteException e){
            
            GerImpl gerente = new GerImpl();
            Thread gerThread = new Thread(gerente);
            gerThread.start(); 
            
            //System.out.println(e.getMessage());
            System.out.println("Gerente Criado");
            
        }
        
        /* Verifica se o gerente está ativo. Se o gerente já estava ativo
        anteriormente, não acessa o 'if'.*/
        if(!gerAtivo){
            refGer = (InterfaceGer) referenciaServicoNomes.lookup("Troca de Cartas");
        }
        
        ColImpl colecionador = new ColImpl(refGer);
        System.out.println("Colecionador Criado");

    }
    
}
