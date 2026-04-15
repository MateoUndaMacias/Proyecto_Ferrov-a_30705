
package vista;

import controlador.ReservaControlador;
import controlador.TrenControlador;
import controlador.UsuarioControlador;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.Reserva;
import modelo.Tren;
import modelo.Usuario;

// Patrón MVC: vista para la generación de reportes administrativos.
// SOLID (S): responsabilidad única — solo gestiona la presentación de reportes.
public class PnlReporte extends javax.swing.JPanel {

    UsuarioControlador control = new UsuarioControlador();
    List<Usuario> lista = control.listarUsuarios();

    public PnlReporte() {
        initComponents();
        setBackground(vista.TemaSistema.getFondo());
        jPanel1.setBackground(vista.TemaSistema.getFondo());
        setBorder(javax.swing.BorderFactory.createEmptyBorder(vista.TemaSistema.getPadding(),
                vista.TemaSistema.getPadding(), vista.TemaSistema.getPadding(), vista.TemaSistema.getPadding()));

        if (!modelo.ControlSesion.getUsuario().getRol().equalsIgnoreCase("ADMIN")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Acceso restringido a Administradores");
            btnGenerar.setEnabled(false);
        }
    }

    private void cargarReporteReservas() {
        String[] columnas = { "ID", "Usuario", "Tren", "Fecha", "Estado", "Monto" };
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        ReservaControlador control = new ReservaControlador();

        for (Reserva r : control.listarReservas()) {
            modelo.addRow(new Object[] {
                    r.getId(),
                    r.getUsername(),
                    r.getTren(),
                    r.getFecha(),
                    r.getEstado(),
                    r.getMonto()
            });
        }
        jTable1.setModel(modelo);
    }

    private void cargarReporteTrenes() {
        String[] columnas = { "Codigo", "Ruta", "Capacidad", "Estado" };
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        TrenControlador control = new TrenControlador();

        for (Tren t : control.listarTrenes()) {
            modelo.addRow(new Object[] {
                    t.getCodigo(),
                    t.getRuta(),
                    t.getCapacidad(),
                    t.isActivo() ? "Activo" : "Inactivo"
            });
        }
        jTable1.setModel(modelo);
    }

    private void cargarReporteUsuarios() {
        String[] columnas = { "Nombre", "Usuario", "Rol", "Estado" };
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        UsuarioControlador control = new UsuarioControlador();

        for (Usuario u : control.listarUsuarios()) {
            modelo.addRow(new Object[] {
                    u.getNombre(),
                    u.getUsername(),
                    u.getRol(),
                    u.isActivo() ? "Activo" : "Bloqueado"
            });
        }
        jTable1.setModel(modelo);
    }

    private void cargarReportePagos() {
        String[] columnas = { "ID Pago", "Usuario", "Reserva ID", "Monto", "Estado" };
        DefaultTableModel tblModel = new DefaultTableModel(null, columnas);
        modelo.PagoDAO pagoDao = new modelo.PagoDAO();

        for (modelo.Pago p : pagoDao.listarPagos()) {
            tblModel.addRow(new Object[] {
                    p.getId(),
                    p.getUsername(),
                    p.getReservaId(),
                    p.getMonto(),
                    p.getEstado()
            });
        }
        jTable1.setModel(tblModel);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        cboTipoReporte = new javax.swing.JComboBox<>();
        btnGenerar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        lblTitulo.setText("Reportes");
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(java.awt.Font.PLAIN, 16f));
        lblTipo.setText("Tipo:");

        cboTipoReporte.setModel(
                new javax.swing.DefaultComboBoxModel<>(new String[] { "Reservas", "Pagos", "Trenes", "Usuarios" }));

        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(this::btnGenerarActionPerformed);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {}));
        jTable1.setRowHeight(28);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTitulo)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTipo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboTipoReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 160,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGenerar)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTitulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTipo)
                                        .addComponent(cboTipoReporte, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnGenerar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320,
                                        Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
    }

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {
        String tipo = cboTipoReporte.getSelectedItem().toString();

        switch (tipo) {
            case "Reservas":
                cargarReporteReservas();
                break;
            case "Pagos":
                cargarReportePagos();
                break;
            case "Trenes":
                cargarReporteTrenes();
                break;
            case "Usuarios":
                cargarReporteUsuarios();
                break;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerar;
    private javax.swing.JComboBox<String> cboTipoReporte;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
