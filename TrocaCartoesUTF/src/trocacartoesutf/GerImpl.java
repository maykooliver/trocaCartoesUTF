/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf;

import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Mayko
 */
public class GerImpl  extends UnicastRemoteObject implements InterfaceGer, Runnable{
    
    public GerImpl() throws RemoteException{
        
    }

    @Override
    public Array consultarColecoes() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        
        try{
            //Cria o registro para receber as referencias, para a porta 1099, local
            Registry referenciaServicoNome = LocateRegistry.createRegistry(1099);

            //A classe é associada a um nome para ser acessado externamente
            //(Registra uma referencia de objeto remoto)
            referenciaServicoNome.rebind("Troca de Cartas", this);

            System.out.println("Serviços de nome iniciado..\n");
            
        }catch(RemoteException e){
            System.out.println("Teste" + e.getMessage());
            System.exit(0);
        }
        
    }
    
}
