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
    private String status;

    public Transacao(int id) {
        this.id = id;
        this.ativar();
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void ativar(){
        this.status = "Ativado";
    }
    
    public boolean efetivado(){
        return false;
    }
        
    public boolean abortar(){
        return false;
    }

    public boolean efetivarTemp(){
        return false;
    }
    
    public boolean falha(){
        return false;
    }

}
