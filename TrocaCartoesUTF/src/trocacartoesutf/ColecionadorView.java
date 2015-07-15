/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trocacartoesutf;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import trocacartoesutf.interfaces.InterfaceGer;

/**
 *
 * @author Mayko
 */
public class ColecionadorView extends javax.swing.JFrame {
    
    /**
     * Creates new form ColecionadorView
     */
    public ColecionadorView() {
        initComponents();
        
        String[] colecao = ColImpl.col.consultaColecao();
        
        cartasCombo.removeAllItems();
        cartasCombo.addItem(colecao[0]+"-"+ColImpl.nomeCol);
        cartasCombo.addItem(colecao[1]+"-"+ColImpl.nomeCol);
        cartasCombo.addItem(colecao[2]+"-"+ColImpl.nomeCol);
        
        cartasCasadasCombo.removeAllItems();
        cartasCasadasCombo.addItem(colecao[0]+"-"+ColImpl.nomeCol);
        cartasCasadasCombo.addItem(colecao[1]+"-"+ColImpl.nomeCol);
        cartasCasadasCombo.addItem(colecao[2]+"-"+ColImpl.nomeCol);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cartasCombo = new javax.swing.JComboBox();
        btnConsultarCartas = new javax.swing.JButton();
        btnTrocarCartas = new javax.swing.JButton();
        btnTrocarCartasCasadas = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cartasCasadasCombo = new javax.swing.JComboBox();
        colecaoTerceiros = new javax.swing.JComboBox();
        colecaoCasadaTerceiros = new javax.swing.JComboBox();
        btnAtualizarCartas = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Colecionador");

        jLabel2.setText("Cartas Colecionadas:");

        cartasCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cartas do colecionador." }));

        btnConsultarCartas.setText("Consultar todas as Cartas");
        btnConsultarCartas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarCartasActionPerformed(evt);
            }
        });

        btnTrocarCartas.setText("Trocar Cartas");
        btnTrocarCartas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrocarCartasActionPerformed(evt);
            }
        });

        btnTrocarCartasCasadas.setText("Troca Casada");
        btnTrocarCartasCasadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrocarCartasCasadasActionPerformed(evt);
            }
        });

        jLabel3.setText("Cartas Colecionadas:");

        cartasCasadasCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cartas do colecionador." }));

        colecaoTerceiros.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cartas de Terceiros" }));
        colecaoTerceiros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colecaoTerceirosActionPerformed(evt);
            }
        });

        colecaoCasadaTerceiros.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cartas de Terceiros." }));

        btnAtualizarCartas.setText("Atualizar");
        btnAtualizarCartas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarCartasActionPerformed(evt);
            }
        });

        jButton1.setText("Status");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cartasCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(btnConsultarCartas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTrocarCartas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(colecaoTerceiros, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAtualizarCartas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(colecaoCasadaTerceiros, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cartasCasadasCombo, 0, 159, Short.MAX_VALUE)
                    .addComponent(btnTrocarCartasCasadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cartasCasadasCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(197, 197, 197))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAtualizarCartas, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)))
                        .addComponent(btnTrocarCartasCasadas))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cartasCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnConsultarCartas)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(colecaoCasadaTerceiros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(colecaoTerceiros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(118, 118, 118)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTrocarCartas)
                            .addComponent(jButton1))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarCartasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarCartasActionPerformed
           
        String[] colecoesDeTerceiros = null;
        
        try {
            colecoesDeTerceiros = ColImpl.refGer.consultarColecoes();
        } catch (RemoteException ex) {
            Logger.getLogger(ColecionadorView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        colecaoTerceiros.removeAllItems();
        colecaoCasadaTerceiros.removeAllItems();
        
        for(String cartaTerceiros:colecoesDeTerceiros){
            String sol1[] = cartaTerceiros.split("-");
            if(!sol1[1].equals(ColImpl.col.getNomeCol())){
                colecaoTerceiros.addItem(cartaTerceiros);
                colecaoCasadaTerceiros.addItem(cartaTerceiros);
            }
        }
        
    }//GEN-LAST:event_btnConsultarCartasActionPerformed

    private void colecaoTerceirosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colecaoTerceirosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_colecaoTerceirosActionPerformed

    private void btnTrocarCartasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrocarCartasActionPerformed
        String cartaCol = cartasCombo.getSelectedItem().toString();
        String cartaTerc = colecaoTerceiros.getSelectedItem().toString();

        TrocaSimples troca = new TrocaSimples(cartaCol, cartaTerc);
        Thread trocaThread = new Thread(troca);
        trocaThread.start();
    }//GEN-LAST:event_btnTrocarCartasActionPerformed

    private void btnAtualizarCartasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarCartasActionPerformed
        
        /*Atualiza as cartas do colecionador*/
        String[] colecao = ColImpl.col.consultaColecao();
        
        cartasCombo.removeAllItems();
        cartasCombo.addItem(colecao[0]+"-"+ColImpl.nomeCol);
        cartasCombo.addItem(colecao[1]+"-"+ColImpl.nomeCol);
        cartasCombo.addItem(colecao[2]+"-"+ColImpl.nomeCol);
        
        cartasCasadasCombo.removeAllItems();
        cartasCasadasCombo.addItem(colecao[0]+"-"+ColImpl.nomeCol);
        cartasCasadasCombo.addItem(colecao[1]+"-"+ColImpl.nomeCol);
        cartasCasadasCombo.addItem(colecao[2]+"-"+ColImpl.nomeCol);
        
        
        /*Atualiza todas as cartas que estão no servidor*/
        String[] colecoesDeTerceiros = null;
        
        try {
            colecoesDeTerceiros = ColImpl.refGer.consultarColecoes();
        } catch (RemoteException ex) {
            Logger.getLogger(ColecionadorView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        colecaoTerceiros.removeAllItems();
        colecaoCasadaTerceiros.removeAllItems();
        
        for(String cartaTerceiros:colecoesDeTerceiros){
            String sol1[] = cartaTerceiros.split("-");
            if(!sol1[1].equals(ColImpl.col.getNomeCol())){
                colecaoTerceiros.addItem(cartaTerceiros);
                colecaoCasadaTerceiros.addItem(cartaTerceiros);
            }
        }
        
    }//GEN-LAST:event_btnAtualizarCartasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here
        EstadoTransacoesView SubMenuPro = new EstadoTransacoesView(this, true);
        SubMenuPro.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnTrocarCartasCasadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrocarCartasCasadasActionPerformed
        String cartaCol = cartasCombo.getSelectedItem().toString();
        String cartaTerc = colecaoTerceiros.getSelectedItem().toString();
        String cartaCasadaCol = cartasCasadasCombo.getSelectedItem().toString();
        String cartaCasadaTerc = colecaoCasadaTerceiros.getSelectedItem().toString();

        if(cartaCol.equals(cartaCasadaCol) || cartaTerc.equals(cartaCasadaTerc)){
            JOptionPane.showMessageDialog(null, "Não é possível trocar uma mesma carta por duas.");
        }else{
            TrocaCasada troca = new TrocaCasada(cartaCol, cartaTerc, cartaCasadaCol, cartaCasadaTerc);
            Thread trocaThread = new Thread(troca);
            trocaThread.start();
        }
    }//GEN-LAST:event_btnTrocarCartasCasadasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ColecionadorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ColecionadorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ColecionadorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ColecionadorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ColecionadorView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizarCartas;
    private javax.swing.JButton btnConsultarCartas;
    private javax.swing.JButton btnTrocarCartas;
    private javax.swing.JButton btnTrocarCartasCasadas;
    private javax.swing.JComboBox cartasCasadasCombo;
    private javax.swing.JComboBox cartasCombo;
    private javax.swing.JComboBox colecaoCasadaTerceiros;
    private javax.swing.JComboBox colecaoTerceiros;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
