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
public interface InterfaceGer extends Remote{
    
    public Array consultarColecoes() throws RemoteException;
    
}
