/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf.gerente;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import trocacartoesutf.interfaces.InterfaceCol;
import trocacartoesutf.interfaces.InterfaceGer;

/**
 *
 * @author Mayko
 */
public class GerImpl  extends UnicastRemoteObject implements InterfaceGer, Runnable{
    
    public static ArrayList<ColecionadorGer> listaColecionadores;
    public static Map<String, ColecionadorGer> ColecionadorMap;
    
    public GerImpl() throws RemoteException{
        listaColecionadores = new ArrayList<>();
        ColecionadorMap = new HashMap<String, ColecionadorGer>();
    }

    @Override
    public String[] consultarColecoes() throws RemoteException {
        String[] colecoes = new String[100];
        int i = 0;
        
        for(ColecionadorGer colGer:listaColecionadores){
            colecoes[i] = colGer.getCartaA().getNomeCarta()+"-"+colGer.getNomeCol();
            i++;
            colecoes[i] = colGer.getCartaB().getNomeCarta()+"-"+colGer.getNomeCol();
            i++;
            colecoes[i] = colGer.getCartaC().getNomeCarta()+"-"+colGer.getNomeCol();
            i++;
        }
                
        return colecoes;
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

    @Override
    public void registrarColecionador(InterfaceCol ref, String A, String B, String C) throws RemoteException {
        ColecionadorGer col = new ColecionadorGer(ref, A, B, C);
        listaColecionadores.add(col);
        ColecionadorMap.put(col.getNomeCol(), col);
    }
    
}
