/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf.gerente;

import java.util.Random;
import trocacartoesutf.interfaces.InterfaceCol;

/**
 *
 * @author Lucas
 */
public class ColecionadorGer {

    private String nomeCol;
    private InterfaceCol refCol;

    public ColecionadorGer(InterfaceCol refCol) {
        Random numeroCli = new Random();
        int num = numeroCli.nextInt(1000);
        nomeCol = "Col " + num;
        this.refCol = refCol;
    }

    public String getNomeCol() {
        return nomeCol;
    }
    
    public InterfaceCol getRefCol() {
        return refCol;
    }

    public void setRefCol(InterfaceCol refCol) {
        this.refCol = refCol;
    }

    @Override
    public String toString() {
        return "ColecionadorGer{" + "refCol=" + refCol + '}';
    } 
    
}
