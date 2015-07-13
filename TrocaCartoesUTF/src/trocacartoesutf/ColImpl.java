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
import static trocacartoesutf.Colecionador.A;
import static trocacartoesutf.Colecionador.B;
import static trocacartoesutf.Colecionador.C;

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
        
        nomeCol = ref.registrarColecionador(this);
                
        col.setNomeCol(nomeCol);
        
        String split[] = nomeCol.split(" ");
        
        String trans = split[1].toString();
        
        numTrans = Integer.parseInt(trans);
        
        numTrans = 1000 * numTrans; 
        
        System.out.println("Numero das transacoes deste cliente: "+numTrans);
        
        ColecionadorView colecionador = new ColecionadorView();
        
        colecionador.setVisible(true);
    }

    @Override
    public Array consultaColecao() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean trocarCartoes(String cartaTrocada, String cartaRecebida) throws RemoteException {
        
        if(cartaTrocada.equals(A)){
            col.getCartaUm().setNomeCarta(cartaRecebida);
            System.out.println("Carta "+A+" Trocada");
            return true;
        }else if(cartaTrocada.equals(B)){
            col.getCartaDois().setNomeCarta(cartaRecebida);
            System.out.println("Carta "+B+" Trocada");
            return true;
        }else if(cartaTrocada.equals(C)){
            col.getCartaTres().setNomeCarta(cartaRecebida);
            System.out.println("Carta "+C+" Trocada");
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean bloquearCarta(String carta) throws RemoteException {
        
        if(carta.equals(A)){
            col.getCartaUm().setBloqueado(true);
            System.out.println("Carta "+A+" Bloqueada");
            return true;
        }else if(carta.equals(B)){
            col.getCartaDois().setBloqueado(true);
            System.out.println("Carta "+B+" Bloqueada");
            return true;
        }else{
            col.getCartaTres().setBloqueado(true);
            System.out.println("Carta "+C+" Bloqueada");
            return true;
        }

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
        Transacao trans = TransacaoMap.get(numeroTrans);
        trans.efetivarTemp();
        return true;
    }

    @Override
    public boolean efetivarTrans(int numeroTrans) throws RemoteException {
        Transacao trans = TransacaoMap.get(numeroTrans);
        trans.efetivar();
        System.out.println("\n################################################\n");
        System.out.println("\tTransação "+ numeroTrans +" efetivada");
        System.out.println("\n################################################\n");
        return true;
    }

    @Override
    public boolean abortarTrans(int numeroTrans) throws RemoteException {
        Transacao trans = TransacaoMap.get(numeroTrans);
        trans.abortar();
        return true;
    }

    @Override
    public boolean falhaTrans(int numeroTrans) throws RemoteException {
        Transacao trans = TransacaoMap.get(numeroTrans);
        trans.falha();
        return true;
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
        
        if(Y.equals(A)){
            if(col.getCartaUm().isBloqueado()){
                return true;
            }
            return false;
        }else if(Y.equals(B)){
            if(col.getCartaDois().isBloqueado()){
                return true;
            }
            return false;
        }else{
            if(col.getCartaTres().isBloqueado()){
                return true;
            }
            return false;
        }

    }

    @Override
    public void desBloquearCarta(String carta) throws RemoteException {
        
        if(carta.equals(A)){
            col.getCartaUm().setBloqueado(false);
            System.out.println("Carta "+A+" desbloqueada");
        }else if(carta.equals(B)){
            col.getCartaDois().setBloqueado(false);
            System.out.println("Carta "+B+" desbloqueada");
        }else{
            col.getCartaTres().setBloqueado(false);
            System.out.println("Carta "+C+" desbloqueada");
        }
        
    }

    @Override
    public String getNomeCartaUm() throws RemoteException {
        return col.getCartaUm().getNomeCarta();
    }

    @Override
    public String getNomeCartaDois() throws RemoteException {
        return col.getCartaDois().getNomeCarta();
    }

    @Override
    public String getNomeCartaTres() throws RemoteException {
        return col.getCartaTres().getNomeCarta();
    }
    
}
