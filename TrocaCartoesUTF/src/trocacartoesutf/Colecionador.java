/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf;

import trocacartoesutf.interfaces.InterfaceCol;
import java.lang.reflect.Array;

/**
 *
 * @author Mayko
 */
public class Colecionador {

    private Carta A;
    private Carta B;
    private Carta C;
    
    public Colecionador() {
        A = new Carta("A");
        B = new Carta("B");
        C = new Carta("C");       
    }
    
    
    public String[] consultaColecao(){
        String[] colecao = new String[3];
        colecao[0] = A.getNomeCarta();
        colecao[1] = B.getNomeCarta();
        colecao[2] = C.getNomeCarta();
        return colecao;
    }
    
    public boolean trocarCartoes(InterfaceCol cli, String carta){
        return false;
    }
    
}
