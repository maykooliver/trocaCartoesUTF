/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf;

/**
 *
 * @author Mayko
 */
public class Transacao {
    
    private int id;
    private int status;
    public static final int ATIVADO = 1;
    public static final int FALHADO = 2;
    public static final int ABORTADO = 3;
    public static final int EFETIVADOTEMP = 4;
    public static final int EFETIVADO = 5;
    
    
    public Transacao(int id) {
        this.id = id;
        this.ativar();
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public void ativar(){
        this.status = ATIVADO;
    }
    
    public void falha(){
        this.status = FALHADO;
    }
    
    public void abortar(){
        this.status = ABORTADO;
    }
   
    public void efetivarTemp(){
        this.status = EFETIVADOTEMP;
    }
    
        public void efetivar(){
        this.status = EFETIVADO;
    }



}
