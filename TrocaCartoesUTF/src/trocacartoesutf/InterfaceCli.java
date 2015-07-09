/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf;

import java.lang.reflect.Array;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Mayko
 */
public interface InterfaceCli extends Remote {
    
    public Array consultaColecao() throws RemoteException;
    
    public boolean trocarCartoes(InterfaceCli cli, Carta card) throws RemoteException;
    
    public boolean bloquearCarta() throws RemoteException;
    
    public boolean ativarTrans() throws RemoteException;
    
    public boolean efetivarTempTrans() throws RemoteException;
    
    public boolean efetivarTrans() throws RemoteException;
    
    public boolean abortarTrans() throws RemoteException;
    
}
