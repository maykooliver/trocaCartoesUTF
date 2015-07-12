/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf.gerente;

import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import trocacartoesutf.interfaces.InterfaceCol;
import trocacartoesutf.interfaces.InterfaceGer;

/**
 *
 * @author Mayko
 */
public class GerImpl  extends UnicastRemoteObject implements InterfaceGer, Runnable{
    
    public static ArrayList<ColecionadorGer> listaColecionadores;
    
    public GerImpl() throws RemoteException{
        listaColecionadores = new ArrayList<>();
    }

    @Override
    public String[] consultarColecoes() throws RemoteException {
        String[] colecoes = new String[100];
        int i = 0;
        
        for(ColecionadorGer colGer:listaColecionadores){
            System.out.println(colecoes[i]);
            colecoes[i] = colGer.cartaA.getNomeCarta();
            i++;
            System.out.println(colecoes[i]);
            colecoes[i] = colGer.cartaB.getNomeCarta();
            i++;
            System.out.println(colecoes[i]);
            colecoes[i] = colGer.cartaC.getNomeCarta();
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
    }
    
}