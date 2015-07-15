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
import static trocacartoesutf.Transacao.ABORTADO;
import static trocacartoesutf.Transacao.FALHADO;

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
    public static ContadorTransacao transCont;
    public static Thread contThread;
    
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
        System.out.println("Nova Transacao a ativar: " + numTrans);
        TransacaoMap.put(numTrans, novaTrans);
        System.out.println("Tamanho da TransacaoMap: "+ TransacaoMap.size());
        
        ContadorTransacao contTrans = new ContadorTransacao(numTrans);
        Thread contThread = new Thread(contTrans);
        contThread.start();
        System.out.println("Thread contadora da transação, iniciada.");
        
        return numTrans;
    }

    @Override
    public boolean efetivarTempTrans(int numeroTrans) throws RemoteException {
        Transacao trans = TransacaoMap.get(numeroTrans);
        trans.efetivarTemp();
        
        transCont = new ContadorTransacao(numeroTrans);
        contThread = new Thread(transCont);
        contThread.start();
        
        System.out.println("Transação "+ numeroTrans +" efetivada temporariamente");
        
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
        System.out.println("Numero da transacao a abortar: "+ numeroTrans);
        Transacao trans = TransacaoMap.get(numeroTrans);
        trans.abortar();
        System.out.println("\n################################################\n");
        System.out.println("\tTransação "+ numeroTrans +" abortada");
        System.out.println("\n################################################\n");
        return true;
    }

    @Override
    public boolean falhaTrans(int numeroTrans) throws RemoteException {
        Transacao trans = TransacaoMap.get(numeroTrans);
        trans.falha();
        System.out.println("\n################################################\n");
        System.out.println("\tTransação "+ numeroTrans +" falhou");
        System.out.println("\n################################################\n");
        return true;
    }

    @Override
    public boolean verificaAceite(String cartaCol, String cartaTer, int transacaoNum) throws RemoteException {
        boolean aceito = false;
        
        String nome = this.col.getNomeCol();
        
        int resposta = JOptionPane.showConfirmDialog(null, 
                "Deseja trocar a sua carta "+cartaCol+" pela carta "+cartaTer+"?" , "Solicitação de troca", JOptionPane.YES_NO_OPTION);

        String sol[] = cartaTer.split("-");
        
        if (resposta == JOptionPane.YES_OPTION) {
            System.out.println("COMBO RESPOSTA (Verifica Aceite), COLECIONADOR:" + sol[1] + " TRANSACAO:" + transacaoNum);
            if(refGer.getTransacao(sol[1], transacaoNum)){
               aceito =  true;
            }else{
                aceito = false;
                JOptionPane.showMessageDialog(null, "Transação abortada. Tempo excedido.");
            }
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

    @Override
    public boolean getTransacao(int numTrans) throws RemoteException {
        Transacao trans = TransacaoMap.get(numTrans);
        System.out.println("GET TRANSACAO - Numero da Transacao: " + numTrans);
        if(trans.getStatus() == ABORTADO || trans.getStatus() == FALHADO){
            return false;
        }
        return true;
    }
    
}
