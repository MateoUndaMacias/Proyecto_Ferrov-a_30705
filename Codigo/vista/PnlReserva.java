
package vista;

import controlador.ReservaControlador;
import controlador.SistemaFacade;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import modelo.ControlSesion;
import modelo.Reserva;
import modelo.Usuario;
import util.NotificadorCambios;
import util.ObservadorCambios;

// Patrón MVC: vista que muestra y gestiona las reservas del usuario.
// Patrón Observer: implementa ObservadorCambios para recargar datos cuando las reservas cambian.
// Patrón Facade: utiliza SistemaFacade para delegar operaciones de negocio.
// SOLID (S): responsabilidad única — solo gestiona la presentación de reservas.
public class PnlReserva extends javax.swing.JPanel implements ObservadorCambios {
    // Patrón Facade: acceso unificado a la lógica de negocio
    private final SistemaFacade fachada = SistemaFacade.getInstancia();

    public PnlReserva() {
        initComponents();
        setBackground(vista.TemaSistema.getFondo());
        setBorder(javax.swing.BorderFactory.createEmptyBorder(vista.TemaSistema.getPadding(),
                vista.TemaSistema.getPadding(), vista.TemaSistema.getPadding(), vista.TemaSistema.getPadding()));
        // Patrón Observer: se registra para recibir notificaciones de cambios en
        // reservas
        NotificadorCambios.getInstancia().registrar(this);

        boolean esAdmin = ControlSesion.getUsuario()
                .getRol()
                .equalsIgnoreCase("ADMIN");

        this.cargarReservas();
    }

    private void cargarReservas() {
        Usuario u = ControlSesion.getUsuario();
        boolean esAdmin = u.getRol().equalsIgnoreCase("ADMIN");

        // Patrón Facade: delega la obtención de reservas a la fachada
        List<Reserva> lista = fachada.obtenerReservas(esAdmin, u.getUsername());

        DefaultTableModel modelo = new DefaultTableModel(
                new Object[] { "ID", "Usuario", "Tren", "Fecha", "Hora Salida", "Hora Llegada", "Estado", "Monto" },
                0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Reserva r : lista) {
            modelo.addRow(new Object[] {
                    r.getId(),
                    r.getUsername(),
                    r.getTren(),
                    r.getFecha(),
                    r.getHoraSalida(),
                    r.getHoraLlegada(),
                    r.getEstado(),
                    r.getMonto()
            });
        }

        tblReservas.setModel(modelo);

        tblReservas.getColumnModel().getColumn(0).setMinWidth(0);
        tblReservas.getColumnModel().getColumn(0).setMaxWidth(0);
        tblReservas.getColumnModel().getColumn(0).setWidth(0);
    }

    // Patrón Observer: callback invocado cuando los datos de reservas cambian
    @Override
    public void onDatosCambiados(String tipoCambio) {
        if (ObservadorCambios.TIPO_RESERVA.equals(tipoCambio)) {
            SwingUtilities.invokeLater(this::cargarReservas);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblReservas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReservas = new javax.swing.JTable();
        btnReservar = new javax.swing.JButton();
        btnPagar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        lblReservas.setText("Reservas");
        lblReservas.setFont(lblReservas.getFont().deriveFont(java.awt.Font.PLAIN, 16f));

        tblReservas.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] { "Usuario", "Tren", "Fecha", "Hora Salida", "Hora Llegada", "Estado", "Monto" }));
        tblReservas.setRowHeight(28);
        jScrollPane1.setViewportView(tblReservas);

        btnReservar.setText("Nueva reserva");
        btnReservar.addActionListener(this::btnReservarActionPerformed);
        btnPagar.setText("Pagar");
        btnPagar.addActionListener(this::btnPagarActionPerformed);
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblReservas)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                                .addGap(0, 0, 0))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnReservar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPagar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar)
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblReservas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnReservar)
                                        .addComponent(btnPagar)
                                        .addComponent(btnCancelar))
                                .addContainerGap()));
    }// </editor-fold>//GEN-END:initComponents

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {
        int fila = tblReservas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una reserva");
            return;
        }

        String estado = tblReservas.getValueAt(fila, 6).toString();
        if (!estado.equalsIgnoreCase("PENDIENTE")) {
            JOptionPane.showMessageDialog(this, "Solo se puede pagar una reserva PENDIENTE");
            return;
        }

        String idReserva = tblReservas.getValueAt(fila, 0).toString();
        String usuario = tblReservas.getValueAt(fila, 1).toString();
        String montoReserva = tblReservas.getValueAt(fila, 7).toString();

        JPanel panelPago = new JPanel();
        panelPago.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints c = new java.awt.GridBagConstraints();
        c.insets = new java.awt.Insets(6, 8, 6, 8);
        c.anchor = java.awt.GridBagConstraints.LINE_START;
        c.fill = java.awt.GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        panelPago.add(new JLabel("Monto:"), c);
        JTextField txtMonto = new JTextField(10);
        txtMonto.setText(montoReserva);
        txtMonto.setEditable(false);
        c.gridx = 1;
        c.weightx = 1;
        panelPago.add(txtMonto, c);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        panelPago.add(new JLabel("Método de pago:"), c);
        JRadioButton rbEfectivo = new JRadioButton("Efectivo", true);
        JRadioButton rbTransferencia = new JRadioButton("Transferencia", false);
        javax.swing.ButtonGroup bgMetodo = new javax.swing.ButtonGroup();
        bgMetodo.add(rbEfectivo);
        bgMetodo.add(rbTransferencia);
        JPanel panelMetodo = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 8, 0));
        panelMetodo.setOpaque(false);
        panelMetodo.add(rbEfectivo);
        panelMetodo.add(rbTransferencia);
        c.gridx = 1;
        c.weightx = 1;
        panelPago.add(panelMetodo, c);

        int opcion = JOptionPane.showConfirmDialog(this, panelPago, "Registrar pago",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (opcion != JOptionPane.OK_OPTION)
            return;

        String input = txtMonto.getText().trim();

        String metodoPago = rbEfectivo.isSelected() ? "Efectivo" : "Transferencia";

        try {
            double monto = Double.parseDouble(input);
            if (monto <= 0) {
                JOptionPane.showMessageDialog(this, "Monto inválido");
                return;
            }

            // Patrón Facade: delega el pago a la fachada
            if (fachada.pagarReserva(idReserva, monto, usuario, metodoPago)) {
                JOptionPane.showMessageDialog(this, "Pago registrado exitosamente");
                cargarReservas();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el pago");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Monto inválido");
        }
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        int fila = tblReservas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una reserva");
            return;
        }

        String estado = tblReservas.getValueAt(fila, 6).toString();
        if (!estado.equalsIgnoreCase("PENDIENTE")) {
            JOptionPane.showMessageDialog(this, "Solo se puede cancelar una reserva PENDIENTE");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro de cancelar?", "Confirmar",
                JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION)
            return;

        String idReserva = tblReservas.getValueAt(fila, 0).toString();

        // Patrón Facade: delega la cancelación a la fachada
        if (fachada.cancelarReserva(idReserva)) {
            JOptionPane.showMessageDialog(this, "Reserva cancelada");
            cargarReservas();
        } else {
            JOptionPane.showMessageDialog(this, "Error al cancelar");
        }
    }

    private void btnReservarActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(
                this,
                "Para reservar, primero debe seleccionar un tren en el módulo Trenes",
                "Información",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnPagar;
    private javax.swing.JButton btnReservar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblReservas;
    private javax.swing.JTable tblReservas;
    // End of variables declaration//GEN-END:variables

}