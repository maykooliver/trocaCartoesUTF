/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf.interfaces;

import java.lang.reflect.Array;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import trocacartoesutf.gerente.ColecionadorGer;

/**
 *
 * @author Mayko
 */
public interface InterfaceGer extends Remote{
    
    public void registrarColecionador(InterfaceCol ref, String A, String B, String C) throws RemoteException;
    
    public String[] consultarColecoes() throws RemoteException;
    
}
