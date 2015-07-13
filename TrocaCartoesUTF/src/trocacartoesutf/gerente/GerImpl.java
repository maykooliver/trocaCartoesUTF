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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public String registrarColecionador(InterfaceCol ref) throws RemoteException {
        ColecionadorGer col = new ColecionadorGer(ref);
        listaColecionadores.add(col);
        ColecionadorMap.put(col.getNomeCol(), col);
        return col.getNomeCol();
    }

    @Override
    public String[] consultarColecoes() throws RemoteException {
        String[] colecoes = new String[listaColecionadores.size()*3];
        int i = 0;
        int indexCol = 0;
        
        for(ColecionadorGer colGer:listaColecionadores){
            try {
                colecoes[i] = colGer.getRefCol().getNomeCartaUm() + "-" + colGer.getNomeCol();
                i++;
                colecoes[i] = colGer.getRefCol().getNomeCartaDois() + "-" + colGer.getNomeCol();
                i++;
                colecoes[i] = colGer.getRefCol().getNomeCartaTres() + "-" + colGer.getNomeCol();
                i++;
            } catch (RemoteException remoteException) {
                System.out.println("Um dos colecionadores registrados está inativo");
            }
        }
                
        return colecoes;
    }
    
    @Override
    public boolean trocaSimples(String solicitante, String solicitado) throws RemoteException {        
        
        System.out.println("Nova troca simples. Solicitante: " + solicitante +" - Solicitado: " + solicitado);
        
        String sol1[] = solicitante.split("-");
        String sol2[] = solicitado.split("-");
        
        ColecionadorGer requerente = ColecionadorMap.get(sol1[1]);
        ColecionadorGer requerido = ColecionadorMap.get(sol2[1]);
        
        System.out.println("Requerente: " + requerente.getRefCol());
        System.out.println("Requerido: " + requerente.getRefCol());
        
        
        /*Ativa transação*/
        int numTrans = requerente.getRefCol().ativarTrans();
        System.out.println("Transacao ativada(iniciada): " + numTrans);
        
        
        /*Verifica se as cartas solicitadas estão bloqueadas
        Se estiver, cancela a troca*/
        System.out.println("Verifica Bloqueio");
        System.out.println("Sol1: "+ sol1[0] + " Sol2: " + sol2[0]);
        if(requerente.getRefCol().isBloqueadoCarta(sol1[0]) || requerido.getRefCol().isBloqueadoCarta(sol2[0])){
            System.out.println("Transação "+ numTrans +" abortada. Uma das cartas está bloqueada.");
            requerente.getRefCol().abortarTrans(numTrans);
            return false;
        }
        
        
        /*Verifica a aceitação do requerido
        Se for aceita, efetiva transação temporariamente*/
        if(aceitaTroca(requerido, solicitado, solicitante)){
            boolean x, y;
            x = requerente.getRefCol().bloquearCarta(sol1[0]);
            y = requerido.getRefCol().bloquearCarta(sol1[0]);
            if(x & y){
                requerente.getRefCol().efetivarTempTrans(numTrans);
                System.out.println("Transação "+ numTrans +" efetivada temporariamente");
            }else{
                requerido.getRefCol().falhaTrans(numTrans);
                System.out.println("Transação "+ numTrans +" falhou");
            }
        }else{
            requerido.getRefCol().abortarTrans(numTrans);
            System.out.println("Transação "+ numTrans +" abortada");
        }
        
        
        /*Tenta trocar as cartas e realiza o debloqueio*/
        try{
            boolean x, y;
            requerente.getRefCol().desBloquearCarta(sol1[0]);
            x = requerente.getRefCol().trocarCartoes(sol1[0], sol2[0]);
            requerido.getRefCol().desBloquearCarta(sol2[0]);
            y = requerido.getRefCol().trocarCartoes(sol2[0], sol1[0]);
            if(x & y){

                requerente.getRefCol().efetivarTrans(numTrans);
                System.out.println("Transação "+ numTrans +" efetivada");
            }
        }catch(RemoteException e){
            requerido.getRefCol().falhaTrans(numTrans);
        }
        
        System.out.println("\n################################################\n");
        System.out.println("\tTransação "+ numTrans +" efetivada");
        System.out.println("\n################################################\n");
        
        return true;
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
    
    public boolean aceitaTroca(ColecionadorGer requerido, String cartaSolicitada, String cartaATrocar){
        
        boolean resultado = false;
        
        try {
            resultado = requerido.getRefCol().verificaAceite(cartaSolicitada, cartaATrocar);
        } catch (RemoteException ex) {
            Logger.getLogger(GerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }
    
}
