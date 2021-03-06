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
        
        boolean x, y;
        x = requerente.getRefCol().bloquearCarta(sol1[0]);
        y = requerido.getRefCol().bloquearCarta(sol2[0]);
        /*Verifica a aceitação do requerido
        Se for aceita, efetiva transação temporariamente*/
        if(x & y ){

            if(aceitaTroca(requerido, solicitado, solicitante, numTrans)){
                requerente.getRefCol().efetivarTempTrans(numTrans);

                /*Tenta trocar as cartas e realiza o debloqueio*/
                try{
                    boolean w, z;
                    requerente.getRefCol().desBloquearCarta(sol1[0]);
                    w = requerente.getRefCol().trocarCartoes(sol1[0], sol2[0]);
                    requerido.getRefCol().desBloquearCarta(sol2[0]);
                    z = requerido.getRefCol().trocarCartoes(sol2[0], sol1[0]);
                    if(w & z){

                        requerente.getRefCol().efetivarTrans(numTrans);

                        return true;

                    }
                }catch(RemoteException e){
                    requerente.getRefCol().desBloquearCarta(sol1[0]);
                    requerido.getRefCol().desBloquearCarta(sol2[0]);
                    
                    requerido.getRefCol().falhaTrans(numTrans);
                    
                    return false;
                }
                
            }else{
                
                requerente.getRefCol().desBloquearCarta(sol1[0]);
                requerido.getRefCol().desBloquearCarta(sol2[0]);
                
                requerido.getRefCol().falhaTrans(numTrans);

                return false;
                
            }
            
            requerente.getRefCol().desBloquearCarta(sol1[0]);
            requerido.getRefCol().desBloquearCarta(sol2[0]);
            
            return false;
            
        }else{
            
            requerente.getRefCol().desBloquearCarta(sol1[0]);
            requerido.getRefCol().desBloquearCarta(sol2[0]);
            
            requerente.getRefCol().falhaTrans(numTrans);

            return false;

        }

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
    
    public boolean aceitaTroca(ColecionadorGer requerido, String cartaSolicitada, String cartaATrocar, int transNum){
        
        boolean resultado = false;
        
        try {
            resultado = requerido.getRefCol().verificaAceite(cartaSolicitada, cartaATrocar, transNum);
        } catch (RemoteException ex) {
            Logger.getLogger(GerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }

    @Override
    public boolean getTransacao(String col, int numTrans) throws RemoteException {
        ColecionadorGer colGer = ColecionadorMap.get(col);
        return colGer.getRefCol().getTransacao(numTrans);
    }

    @Override
    public boolean trocaCasada(String solicitante, String solicitado, String solicitanteCasada, String solicitadoCasada) throws RemoteException {
        System.out.println("Nova Casada. Solicitante: " + solicitante +" - Solicitado: " + solicitado);
        System.out.println("Solicitante Casada: " + solicitanteCasada +" - Solicitado Casada: " + solicitadoCasada);
        
        
        String sol1[] = solicitante.split("-");
        String sol2[] = solicitado.split("-");
        String sol3[] = solicitanteCasada.split("-");
        String sol4[] = solicitadoCasada.split("-");
        
        ColecionadorGer requerente = ColecionadorMap.get(sol1[1]);
        ColecionadorGer requerido = ColecionadorMap.get(sol2[1]);
        ColecionadorGer requerenteCasada = ColecionadorMap.get(sol3[1]);
        ColecionadorGer requeridoCasada = ColecionadorMap.get(sol4[1]);
        
        System.out.println("Requerente: " + requerente.getRefCol());
        System.out.println("Requerido: " + requerente.getRefCol());
        System.out.println("Requerente Casada: " + requerenteCasada.getRefCol());
        System.out.println("Requerido Casada: " + requeridoCasada.getRefCol());
        
        /*Ativa transação*/
        int numTrans = requerente.getRefCol().ativarTrans();
        System.out.println("Transacao ativada(iniciada): " + numTrans);
        
        
        /*Verifica se as cartas solicitadas estão bloqueadas
        Se estiver, cancela a troca*/
        System.out.println("Verifica Bloqueio");
        System.out.println("Sol1: "+ sol1[0] + " Sol2: " + sol2[0]);
        System.out.println("Sol3: "+ sol3[0] + " Sol4: " + sol4[0]);
        if(requerente.getRefCol().isBloqueadoCarta(sol1[0]) || requerido.getRefCol().isBloqueadoCarta(sol2[0])
                || requerenteCasada.getRefCol().isBloqueadoCarta(sol3[0]) || requeridoCasada.getRefCol().isBloqueadoCarta(sol4[0]))
        {
            System.out.println("Transação "+ numTrans +" abortada. Uma das cartas está bloqueada.");
            requerente.getRefCol().abortarTrans(numTrans);
            return false;
        }
        
        boolean x, y, w, z;
        x = requerente.getRefCol().bloquearCarta(sol1[0]);
        y = requerido.getRefCol().bloquearCarta(sol2[0]);
        w = requerenteCasada.getRefCol().bloquearCarta(sol3[0]);
        z = requeridoCasada.getRefCol().bloquearCarta(sol4[0]);
        /*Verifica a aceitação do requerido
        Se for aceita, efetiva transação temporariamente*/
        if(x & y & w & z)
        {
            if(aceitaTroca(requerido, solicitado, solicitante, numTrans) 
                & aceitaTroca(requeridoCasada, solicitadoCasada, solicitanteCasada, numTrans))
            {
                requerente.getRefCol().efetivarTempTrans(numTrans);

                /*Tenta trocar as cartas e realiza o debloqueio*/
                try{
                    boolean a, b, c, d;
                    requerente.getRefCol().desBloquearCarta(sol1[0]);
                    a = requerente.getRefCol().trocarCartoes(sol1[0], sol2[0]);
                    requerido.getRefCol().desBloquearCarta(sol2[0]);
                    b = requerido.getRefCol().trocarCartoes(sol2[0], sol1[0]);
                    requerenteCasada.getRefCol().desBloquearCarta(sol3[0]);
                    c = requerenteCasada.getRefCol().trocarCartoes(sol3[0], sol4[0]);
                    requeridoCasada.getRefCol().desBloquearCarta(sol4[0]);
                    d = requeridoCasada.getRefCol().trocarCartoes(sol4[0], sol3[0]);
                    if(a & b & c & d){

                        requerente.getRefCol().desBloquearCarta(sol1[0]);
                        requerido.getRefCol().desBloquearCarta(sol2[0]);
                        requerenteCasada.getRefCol().desBloquearCarta(sol3[0]);
                        requeridoCasada.getRefCol().desBloquearCarta(sol4[0]);
                        
                        requerente.getRefCol().efetivarTrans(numTrans);

                        return true;

                    }
                }catch(RemoteException e){
                    
                    requerente.getRefCol().desBloquearCarta(sol1[0]);
                    requerido.getRefCol().desBloquearCarta(sol2[0]);
                    requerenteCasada.getRefCol().desBloquearCarta(sol3[0]);
                    requeridoCasada.getRefCol().desBloquearCarta(sol4[0]);
                    
                    requerido.getRefCol().falhaTrans(numTrans);
                    
                    return false;
                }
                
            }else{
                
                
                requerente.getRefCol().desBloquearCarta(sol1[0]);
                requerido.getRefCol().desBloquearCarta(sol2[0]);
                requerenteCasada.getRefCol().desBloquearCarta(sol3[0]);
                requeridoCasada.getRefCol().desBloquearCarta(sol4[0]);

                return false;
                
            }
            
            requerente.getRefCol().desBloquearCarta(sol1[0]);
            requerido.getRefCol().desBloquearCarta(sol2[0]);
            requerenteCasada.getRefCol().desBloquearCarta(sol3[0]);
            requeridoCasada.getRefCol().desBloquearCarta(sol4[0]);
            
            return false;
            
        }else{
            
            requerente.getRefCol().desBloquearCarta(sol1[0]);
            requerido.getRefCol().desBloquearCarta(sol2[0]);
            requerenteCasada.getRefCol().desBloquearCarta(sol3[0]);
            requeridoCasada.getRefCol().desBloquearCarta(sol4[0]);
            
            requerente.getRefCol().falhaTrans(numTrans);

            return false;

        }
    }
    
}
