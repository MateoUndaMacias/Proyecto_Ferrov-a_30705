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

// Patrón MVC: ventana principal que carga paneles (vistas) según la navegación del usuario.
// SOLID (S): responsabilidad única — solo gestiona la navegación y el layout principal.
public class FrmPrincipal extends javax.swing.JFrame {

    private static final int SIDEBAR_WIDTH = 180;
    private static final int MIN_WIDTH = 820;
    private static final int MIN_HEIGHT = 520;

    public FrmPrincipal() {
        initComponents();
        construirSidebar();
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        if (!ControlSesion.sesionActiva()) {
            dispose();
            new FrmLogin().setVisible(true);
            return;
        }

        boolean esAdmin = ControlSesion.esAdmin();
        if (!esAdmin) {
            itemUsuarios.setVisible(false);
            itemReportes.setVisible(false);
            itemRegistroTren.setVisible(false);
            btnUsuarios.setVisible(false);
            btnReportes.setVisible(false);
            btnRegistroTren.setVisible(false);
        }

        cargarPanel(new PnlReserva());
    }

    private void construirSidebar() {
        pnlSidebar.setBackground(TemaSistema.getFondoCard());
        pnlSidebar.setLayout(new BoxLayout(pnlSidebar, BoxLayout.Y_AXIS));
        pnlSidebar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(0xE0E0E0)),
                BorderFactory.createEmptyBorder(16, 8, 16, 8)));
        Insets insets = new Insets(10, 20, 10, 20);
        Font btnFont = new Font(Font.SANS_SERIF, Font.PLAIN, 14);

        btnReservas = crearBotonSidebar("Reservas", insets, btnFont);
        btnTrenes = crearBotonSidebar("Trenes", insets, btnFont);
        btnUsuarios = crearBotonSidebar("Usuarios", insets, btnFont);
        btnReportes = crearBotonSidebar("Reportes", insets, btnFont);
        btnRegistroTren = crearBotonSidebar("Registrar tren", insets, btnFont);
        btnSalir = crearBotonSidebar("Salir", insets, btnFont);
        btnSalir.setForeground(new Color(0xC62828));

        btnReservas.addActionListener(e -> cargarPanel(new PnlReserva()));
        btnTrenes.addActionListener(e -> cargarPanel(new PnlTren()));
        btnUsuarios.addActionListener(e -> cargarPanel(new PnlUsuario()));
        btnReportes.addActionListener(e -> cargarPanel(new PnlReporte()));
        btnRegistroTren.addActionListener(e -> cargarPanel(new PnlRegistroTren()));
        btnSalir.addActionListener(this::confirmarSalir);

        pnlSidebar.add(Box.createVerticalStrut(8));
        pnlSidebar.add(btnReservas);
        pnlSidebar.add(Box.createVerticalStrut(6));
        pnlSidebar.add(btnTrenes);
        pnlSidebar.add(Box.createVerticalStrut(6));
        pnlSidebar.add(btnUsuarios);
        pnlSidebar.add(Box.createVerticalStrut(6));
        pnlSidebar.add(btnReportes);
        pnlSidebar.add(Box.createVerticalStrut(6));
        pnlSidebar.add(btnRegistroTren);
        pnlSidebar.add(Box.createVerticalGlue());
        pnlSidebar.add(btnSalir);
    }

    private JButton crearBotonSidebar(String texto, Insets insets, Font font) {
        JButton b = new JButton(texto);
        b.setMaximumSize(new Dimension(SIDEBAR_WIDTH - 16, 44));
        b.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        b.setMargin(insets);
        b.setFont(font);
        b.setBackground(TemaSistema.getFondoCard());
        b.setForeground(TemaSistema.getTexto());
        b.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        b.setFocusPainted(false);
        b.setContentAreaFilled(true);
        return b;
    }

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        pnlSidebar = new javax.swing.JPanel();
        pnlSidebar.setPreferredSize(new Dimension(SIDEBAR_WIDTH, 400));

        pnlContenido = new javax.swing.JPanel();
        pnlContenido.setBackground(TemaSistema.getFondo());
        pnlContenido.setLayout(new BorderLayout());

        menuBar = new javax.swing.JMenuBar();
        menuSistema = new javax.swing.JMenu();
        itemUsuarios = new javax.swing.JMenuItem();
        itemTrenes = new javax.swing.JMenuItem();
        itemReservas = new javax.swing.JMenuItem();
        itemReportes = new javax.swing.JMenuItem();
        itemRegistroTren = new javax.swing.JMenuItem();
        itemSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Ferroviario Ecuador");
        getContentPane().setBackground(TemaSistema.getFondo());

        menuSistema.setText("Menú");
        itemUsuarios.setText("Usuarios");
        itemUsuarios.addActionListener(this::itemUsuariosActionPerformed);
        menuSistema.add(itemUsuarios);
        itemTrenes.setText("Trenes");
        itemTrenes.addActionListener(this::itemTrenesActionPerformed);
        menuSistema.add(itemTrenes);
        itemReservas.setText("Reservas");
        itemReservas.addActionListener(this::itemReservasActionPerformed);
        menuSistema.add(itemReservas);
        itemReportes.setText("Reportes");
        itemReportes.addActionListener(this::itemReportesActionPerformed);
        menuSistema.add(itemReportes);
        itemRegistroTren.setText("Registrar Tren");
        itemRegistroTren.addActionListener(this::itemRegistroTrenActionPerformed);
        menuSistema.add(itemRegistroTren);
        itemSalir.setText("Salir");
        itemSalir.addActionListener(this::itemSalirActionPerformed);
        menuSistema.add(itemSalir);
        menuBar.add(menuSistema);
        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlSidebar, javax.swing.GroupLayout.PREFERRED_SIZE, SIDEBAR_WIDTH,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(pnlContenido, javax.swing.GroupLayout.DEFAULT_SIZE, 644,
                                        Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlSidebar, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlContenido, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {
        cargarPanel(new PnlUsuario());
    }

    private void itemTrenesActionPerformed(java.awt.event.ActionEvent evt) {
        cargarPanel(new PnlTren());
    }

    private void itemReservasActionPerformed(java.awt.event.ActionEvent evt) {
        cargarPanel(new PnlReserva());
    }

    private void itemReportesActionPerformed(java.awt.event.ActionEvent evt) {
        cargarPanel(new PnlReporte());
    }

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {
        confirmarSalir(evt);
    }

    private void itemRegistroTrenActionPerformed(java.awt.event.ActionEvent evt) {
        cargarPanel(new PnlRegistroTren());
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            TemaSistema.aplicar();
            new FrmPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemRegistroTren;
    private javax.swing.JMenuItem itemReportes;
    private javax.swing.JMenuItem itemReservas;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JMenuItem itemTrenes;
    private javax.swing.JMenuItem itemUsuarios;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuSistema;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlSidebar;
    private JButton btnReservas;
    private JButton btnTrenes;
    private JButton btnUsuarios;
    private JButton btnReportes;
    private JButton btnRegistroTren;
    private JButton btnSalir;
    // End of variables declaration//GEN-END:variables
}
