/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf.interfaces;

import java.lang.reflect.Array;
import java.rmi.Remote;
import java.rmi.RemoteException;
import trocacartoesutf.Carta;

/**
 *
 * @author Mayko
 */
public interface InterfaceCol extends Remote {
    
    public Array consultaColecao() throws RemoteException;
    
    public boolean trocarCartoes(InterfaceCol cli, Carta card) throws RemoteException;
    
    public boolean bloquearCarta() throws RemoteException;
    
    public boolean ativarTrans() throws RemoteException;
    
    public boolean efetivarTempTrans() throws RemoteException;
    
    public boolean efetivarTrans() throws RemoteException;
    
    public boolean abortarTrans() throws RemoteException;
    
}
