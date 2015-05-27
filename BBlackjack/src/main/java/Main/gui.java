/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import logiikka.*;
import logiikka.cards.*;
import logiikka.elements.*;
import logiikka.utilities.*;

/**
 *
 * @author mrremes
 */
public class gui extends javax.swing.JFrame {
    private Kierros kierros;
    private Pelaaja pelaaja;
    /**
     * Creates new form gui
     */
    public gui() {
        this.pelaaja = new Pelaaja(1000);
        this.kierros = new Kierros(pelaaja);
        initComponents();
        Jakaja jakaja = new Jakaja();
        Jakaja.sekoitaKortit();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Better Blackjack");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("HIT");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 356, 99, -1));

        jButton2.setText("STAND");
        jButton2.setEnabled(false);
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 392, 99, -1));

        jButton3.setText("DOUBLE");
        jButton3.setEnabled(false);
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 356, 99, -1));

        jButton4.setText("SPLIT");
        jButton4.setToolTipText("");
        jButton4.setEnabled(false);
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 392, 99, -1));

        jSlider1.setMajorTickSpacing(50);
        jSlider1.setMaximum(1000);
        jSlider1.setMinimum(50);
        jSlider1.setPaintTicks(true);
        jSlider1.setSnapToTicks(true);
        jSlider1.setToolTipText("");
        jSlider1.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jSlider1.setFocusable(false);
        getContentPane().add(jSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 356, -1, 66));

        jButton5.setText("DEAL");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 356, 89, 62));

        jButton6.setText("EXIT");
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(529, 356, 89, 66));
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 204, 606, -1));
        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 606, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        kierros.hit(kierros.getPelaajanKasi());
        jPanel3.add(new JLabel(new ImageIcon("cards/" + kierros.getVikaKortti().src())));
        jPanel3.updateUI();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jPanel3.removeAll();
        jPanel4.removeAll();
        jPanel3.updateUI();
        jPanel4.updateUI();
        kierros = new Kierros(new Pelaaja(1000));
        kierros.jaaKadet();
        Kasi k = kierros.getPelaajanKasi();
        Kasi j = kierros.getJakajanKasi();
        jPanel3.add(new JLabel(new ImageIcon("cards/" + k.getKortti(0).src())));
        jPanel4.add(new JLabel(new ImageIcon("cards/back1.png")));
        jPanel3.add(new JLabel(new ImageIcon("cards/" + k.getKortti(1).src())));
        jPanel4.add(new JLabel(new ImageIcon("cards/" + j.getKortti(1).src())));
        jButton5.setEnabled(false);
        jButton1.setEnabled(true);
        jButton2.setEnabled(true);
        jButton6.setEnabled(false);
        jPanel3.updateUI();
        jPanel4.updateUI();
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSlider jSlider1;
    // End of variables declaration//GEN-END:variables
}