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
    public String registrarColecionador(InterfaceCol ref, String A, String B, String C) throws RemoteException {
        ColecionadorGer col = new ColecionadorGer(ref, A, B, C);
        listaColecionadores.add(col);
        ColecionadorMap.put(col.getNomeCol(), col);
        return col.getNomeCol();
    }

    @Override
    public String[] consultarColecoes() throws RemoteException {
        String[] colecoes = new String[listaColecionadores.size()*3];
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
    public boolean trocaSimples(String solicitante, String solicitado) throws RemoteException {        
        System.out.println(solicitante +"-"+solicitado);
        
        String sol1[] = solicitante.split("-");
        String sol2[] = solicitado.split("-");
        
        ColecionadorGer requerente = ColecionadorMap.get(sol1[1]);
        ColecionadorGer requerido = ColecionadorMap.get(sol2[1]);
        
        System.out.println("Requerente: " + requerente.getCartaA());
        System.out.println("Requerido: " + requerente.getCartaB());
        
        /*Ativa transação temporariamente*/
        int numTrans = requerente.getRefCol().ativarTrans();
        System.out.println("Transacao efetivada temporariamente: " + numTrans);
        
        /*Verifica se as cartas solicitadas estão bloqueadas
        Se estiver, cancela a troca*/
        System.out.println("Verifica Bloqueio");
        System.out.println("Sol1: "+ sol1[0] + " Sol2: " + sol2[0]);
        
        //System.out.println("IsBloqueado Sol1: " + requerente.getRefCol().isBloqueadoCarta(sol1[0]));
        //System.out.println("IsBloqueado Sol2: " + requerido.getRefCol().isBloqueadoCarta(sol2[0]));
        
        if(requerente.getRefCol().isBloqueadoCarta(sol1[0]) || requerido.getRefCol().isBloqueadoCarta(sol2[0])){
            requerente.getRefCol().abortarTrans(numTrans);
            return false;
        }
        
        System.out.println("Cartas estão desbloqueadas");
        
        /*Verifica a aceitação do requerido*/
        if(aceitaTroca(requerido, sol2[0], sol1[0])){
            requerido.getRefCol().efetivarTempTrans(numTrans);
        }else{
            requerido.getRefCol().abortarTrans(numTrans);
        }
        
        /*Tenta trocar as cartas*/
        /*try{
            requerido.getRefCol().efetivarTrans(numTrans);
        }catch(){
            requerido.getRefCol().falhaTrans(numTrans);
        }*/
        
        
        
        
        //CartaGer X1 = requerente.getCartaY(sol1[0]);
        //CartaGer X2 = requerido.getCartaY(sol2[0]);
        
        

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
        
        
        try {
            requerido.getRefCol().verificaAceite(cartaSolicitada, cartaATrocar);
        } catch (RemoteException ex) {
            Logger.getLogger(GerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
}
