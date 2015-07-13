/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf;

import trocacartoesutf.interfaces.InterfaceCol;
import trocacartoesutf.interfaces.InterfaceGer;
import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Mayko
 */
public class ColImpl extends UnicastRemoteObject implements InterfaceCol{

    public static Colecionador col;
    public static InterfaceGer refGer;
    public static String nomeCol;
    public static ArrayList<Transacao> listaTransacao;
    public static Map<Integer, Transacao> TransacaoMap;
    public static int numTrans;
    
    public ColImpl(InterfaceGer ref) throws RemoteException{        
        
        refGer = ref;
        
        listaTransacao = new ArrayList<>();
        
        TransacaoMap = new HashMap<Integer, Transacao>();
        
        col = new Colecionador();
        
        String[] cartas = col.consultaColecao();

        nomeCol = ref.registrarColecionador(this, cartas[0], cartas[1], cartas[2]);
                
        col.setNomeCol(nomeCol);
        
        String split[] = nomeCol.split(" ");
        
        String trans = split[1].toString();
        
        numTrans = Integer.parseInt(trans);
        
        numTrans = 1000 * numTrans; 
        
        System.out.println("Transacao: "+numTrans);
        
        ColecionadorView colecionador = new ColecionadorView();
        
        colecionador.setVisible(true);
    }

    @Override
    public Array consultaColecao() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean trocarCartoes(InterfaceCol cli, Carta card) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean bloquearCarta() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int ativarTrans() throws RemoteException {
        numTrans += 1;
        Transacao novaTrans = new Transacao(numTrans);
        listaTransacao.add(novaTrans);
        TransacaoMap.put(numTrans, novaTrans);
        return numTrans;
    }

    @Override
    public boolean efetivarTempTrans(int numeroTrans) throws RemoteException {
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean efetivarTrans(int numeroTrans) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean abortarTrans(int numeroTrans) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean falhaTrans(int numeroTrans) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verificaAceite(String cartaCol, String cartaTer) throws RemoteException {
        boolean aceito = false;
        
        int resposta = JOptionPane.showConfirmDialog(null, 
                "Deseja trocar a sua carta "+cartaCol+" pela carta "+cartaTer+"?" , "Solicitação de troca", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
           aceito =  true;
        } else if (resposta == JOptionPane.NO_OPTION) {
           aceito =  false;
        }

        return aceito;
    }

    @Override
    public boolean isBloqueadoCarta(String Y) throws RemoteException {
        
        if(Y.equals("A")){
            if(col.getCartaA().isBloqueado()){
                System.out.println("É A");
                return true;
            }
            return false;
        }else if(Y.equals("B")){
            if(col.getCartaB().isBloqueado()){
                System.out.println("É B");
                return true;
            }
            return false;
        }else{
            if(col.getCartaC().isBloqueado()){
                System.out.println("É C");
                return true;
            }
            return false;
        }

    }
    
}
