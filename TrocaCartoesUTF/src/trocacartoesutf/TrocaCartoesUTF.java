/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf;

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

                
        InterfaceGer refGer;
        
        Registry referenciaServicoNomes;
        
        referenciaServicoNomes = LocateRegistry.getRegistry("localhost", 1099);
        try
        {
            refGer = (InterfaceGer) referenciaServicoNomes.lookup("Troca de Cartas");
            ColImpl colecionador = new ColImpl();
            System.out.println("Colecionador Criado");
            
        }catch(RemoteException e){
            
            GerImpl gerente = new GerImpl();
            Thread gerThread = new Thread(gerente);
            gerThread.start(); 
            
            //System.out.println(e.getMessage());
            System.out.println("Gerente Criado");
            
        }
       
        ColecionadorView colecionador = new ColecionadorView();
        colecionador.setVisible(true);
        

            
            
            //SE NAO EXISTIR, CRIA SERVICO DE NOMES, E SE TORNA GERENTE
            
            //FAZ O BIND (SE REGISTRA) NO SERVICO DE NOMES
            
            //FAZ O LOOK UP

    }
    
}
