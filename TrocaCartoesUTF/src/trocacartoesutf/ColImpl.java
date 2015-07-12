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

/**
 *
 * @author Mayko
 */
public class ColImpl extends UnicastRemoteObject implements InterfaceCol{

    public static Colecionador col;
    public static InterfaceGer refGer;
    
    public ColImpl(InterfaceGer ref) throws RemoteException{        
        
        refGer = ref;
        
        col = new Colecionador();
        
        String[] cartas = col.consultaColecao();
        
        ref.registrarColecionador(this, cartas[0], cartas[1], cartas[2]);
        
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
    public boolean ativarTrans() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean efetivarTempTrans() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean efetivarTrans() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean abortarTrans() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
