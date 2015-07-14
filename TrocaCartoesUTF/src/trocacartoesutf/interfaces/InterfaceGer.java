/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Mayko
 */
public interface InterfaceGer extends Remote{
    
    public String registrarColecionador(InterfaceCol ref) throws RemoteException;
    
    public String[] consultarColecoes() throws RemoteException;
    
    public boolean trocaSimples(String solicitante, String solicitado) throws RemoteException;
    
    public boolean getTransacao(String col, int numTrans) throws RemoteException;
    
}
