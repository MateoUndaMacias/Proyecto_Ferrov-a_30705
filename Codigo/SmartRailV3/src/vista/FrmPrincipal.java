
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.ControlSesion;

public class FrmPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmPrincipal.class.getName());

    public FrmPrincipal() {        
    initComponents();
    setLocationRelativeTo(null);
    
        if (!ControlSesion.sesionActiva()) {
            dispose();
            new FrmLogin().setVisible(true);
            return;
        }

        boolean esAdmin = ControlSesion.esAdmin();
        if (!esAdmin) {
            btnUsuarios.setVisible(false);
            btnReportes.setVisible(false);
            btnRegistroTren.setVisible(false);
        }

        cargarPanel(new PnlReserva());

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContenido = new javax.swing.JPanel();
        pnlSidebar = new javax.swing.JPanel();
        btnReservas = new javax.swing.JButton();
        btnTrenes = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnRegistroTren = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartRail");

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnlSidebar.setBorder(javax.swing.BorderFactory.createTitledBorder("Menú"));

        btnReservas.setBackground(new java.awt.Color(0, 0, 0));
        btnReservas.setForeground(new java.awt.Color(0, 0, 51));
        btnReservas.setText("Reservas");
        btnReservas.setBorderPainted(false);
        btnReservas.setContentAreaFilled(false);
        btnReservas.setDefaultCapable(false);
        btnReservas.setFocusPainted(false);
        btnReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservasActionPerformed(evt);
            }
        });

        btnTrenes.setForeground(new java.awt.Color(0, 0, 51));
        btnTrenes.setText("Trenes");
        btnTrenes.setBorderPainted(false);
        btnTrenes.setContentAreaFilled(false);
        btnTrenes.setFocusPainted(false);
        btnTrenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrenesActionPerformed(evt);
            }
        });

        btnUsuarios.setForeground(new java.awt.Color(0, 0, 51));
        btnUsuarios.setText("Registrar Usuarios");
        btnUsuarios.setBorderPainted(false);
        btnUsuarios.setContentAreaFilled(false);
        btnUsuarios.setFocusPainted(false);
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });

        btnReportes.setForeground(new java.awt.Color(0, 0, 51));
        btnReportes.setText("Reportes");
        btnReportes.setBorderPainted(false);
        btnReportes.setContentAreaFilled(false);
        btnReportes.setFocusPainted(false);
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });

        btnRegistroTren.setForeground(new java.awt.Color(0, 0, 51));
        btnRegistroTren.setText("Registrar Tren");
        btnRegistroTren.setBorderPainted(false);
        btnRegistroTren.setContentAreaFilled(false);
        btnRegistroTren.setFocusPainted(false);
        btnRegistroTren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroTrenActionPerformed(evt);
            }
        });

        jButton6.setForeground(new java.awt.Color(204, 0, 0));
        jButton6.setText("Salir");
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setFocusPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSidebarLayout = new javax.swing.GroupLayout(pnlSidebar);
        pnlSidebar.setLayout(pnlSidebarLayout);
        pnlSidebarLayout.setHorizontalGroup(
            pnlSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSidebarLayout.createSequentialGroup()
                .addGroup(pnlSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSidebarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSidebarLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(btnRegistroTren))
                            .addComponent(btnUsuarios)
                            .addGroup(pnlSidebarLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(pnlSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnTrenes)
                                    .addComponent(btnReportes)
                                    .addComponent(btnReservas)))))
                    .addGroup(pnlSidebarLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jButton6)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pnlSidebarLayout.setVerticalGroup(
            pnlSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSidebarLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnReservas)
                .addGap(18, 18, 18)
                .addComponent(btnTrenes)
                .addGap(18, 18, 18)
                .addComponent(btnReportes)
                .addGap(18, 18, 18)
                .addComponent(btnRegistroTren)
                .addGap(18, 18, 18)
                .addComponent(btnUsuarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlSidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlSidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(pnlContenido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        private void confirmarSalir(java.awt.event.ActionEvent evt) {
        int op = JOptionPane.showConfirmDialog(this, "¿Cerrar sesión?", "Salir", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            ControlSesion.cerrarSesion();
            dispose();
            new FrmLogin().setVisible(true);
        }
    }
        
    private void cargarPanel(JPanel panel) {
        pnlContenido.removeAll();
        pnlContenido.setLayout(new BorderLayout());
        pnlContenido.add(panel, BorderLayout.CENTER);
        pnlContenido.setBackground(TemaSistema.getFondo());
        pnlContenido.revalidate();
        pnlContenido.repaint();
    }
    private void btnTrenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrenesActionPerformed
        cargarPanel(new PnlTren());
    }//GEN-LAST:event_btnTrenesActionPerformed

    private void btnReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservasActionPerformed
        cargarPanel(new PnlReserva());
    }//GEN-LAST:event_btnReservasActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        cargarPanel(new PnlReporte());
    }//GEN-LAST:event_btnReportesActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        confirmarSalir(evt);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnRegistroTrenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroTrenActionPerformed
        cargarPanel(new PnlRegistroTren());
    }//GEN-LAST:event_btnRegistroTrenActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
         cargarPanel(new PnlUsuario());
    }//GEN-LAST:event_btnUsuariosActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistroTren;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton btnTrenes;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlSidebar;
    // End of variables declaration//GEN-END:variables
}
