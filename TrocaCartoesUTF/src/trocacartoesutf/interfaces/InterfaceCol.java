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
    
    public String getNomeCartaUm() throws RemoteException;
    
    public String getNomeCartaDois() throws RemoteException;
    
    public String getNomeCartaTres() throws RemoteException;
    
    public boolean trocarCartoes(String cartaTrocada, String cartaRecebida) throws RemoteException;
    
    public boolean verificaAceite(String cartaCol, String cartaTer) throws RemoteException;
    
    public boolean bloquearCarta(String carta) throws RemoteException;
    
    public void desBloquearCarta(String carta) throws RemoteException;
    
    public int ativarTrans() throws RemoteException;
    
    public boolean efetivarTempTrans(int numeroTrans) throws RemoteException;
    
    public boolean efetivarTrans(int numeroTrans) throws RemoteException;
    
    public boolean abortarTrans(int numeroTrans) throws RemoteException;
    
    public boolean falhaTrans(int numeroTrans) throws RemoteException;
    
    public boolean isBloqueadoCarta(String Y) throws RemoteException;
    
}
