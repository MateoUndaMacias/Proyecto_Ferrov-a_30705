
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblReservas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReservas = new javax.swing.JTable();
        btnReservar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        lblReservas.setText("RESERVAS");

        tblReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Usuario", "Tren", "Fecha", "Hora Salida", "Hora Llegada", "Estado", "Monto"
            }
        ));
        jScrollPane1.setViewportView(tblReservas);

        btnReservar.setText("Reservar");
        btnReservar.addActionListener(this::btnReservarActionPerformed);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnReservar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(101, 101, 101))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(217, 217, 217))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblReservas)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnReservar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
    int fila = tblReservas.getSelectedRow();

    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione una reserva");
        return;
    }

    String estado = tblReservas.getValueAt(fila, 6).toString();

    // ✅ Ahora solo permite cancelar si está RESERVADO
    if (!estado.equalsIgnoreCase("RESERVADO")) {
        JOptionPane.showMessageDialog(this, "Solo se puede cancelar una reserva RESERVADA");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "¿Seguro de cancelar?",
            "Confirmar",
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
    private javax.swing.JButton btnReservar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblReservas;
    private javax.swing.JTable tblReservas;
    // End of variables declaration//GEN-END:variables

}