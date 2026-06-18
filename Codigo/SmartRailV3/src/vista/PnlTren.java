
package vista;

import controlador.TrenControlador;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import modelo.Horario;
import modelo.HorarioDAO;
import util.NotificadorCambios;
import util.ObservadorCambios;

// Patrón MVC: vista que muestra horarios y trenes al usuario.
// Patrón Observer: implementa ObservadorCambios para reaccionar a cambios en datos de trenes.
// SOLID (S): responsabilidad única — solo gestiona la presentación de trenes/horarios.
public class PnlTren extends javax.swing.JPanel implements ObservadorCambios {

    private static final int FILAS_POR_PAGINA = 10;

    private final List<Object[]> datosCompletos = new ArrayList<>();
    private int paginaActual = 0;

    public PnlTren() {
        initComponents();
        setBackground(vista.TemaSistema.getFondo());
        jPanel1.setBackground(vista.TemaSistema.getFondo());
        setBorder(javax.swing.BorderFactory.createEmptyBorder(vista.TemaSistema.getPadding(),
                vista.TemaSistema.getPadding(), vista.TemaSistema.getPadding(), vista.TemaSistema.getPadding()));
        cargarHorariosIniciales();
        tblTrenes.setDefaultEditor(Object.class, null);
        // Patrón Observer: se registra como observador para recibir notificaciones de
        // cambios
        NotificadorCambios.getInstancia().registrar(this);
    }

    // Patrón Observer: callback invocado cuando los datos de trenes cambian
    @Override
    public void onDatosCambiados(String tipoCambio) {
        if (ObservadorCambios.TIPO_TREN.equals(tipoCambio)) {
            SwingUtilities.invokeLater(this::cargarHorariosIniciales);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        scrTrenes = new javax.swing.JScrollPane();
        tblTrenes = new javax.swing.JTable();
        btnReservar = new javax.swing.JButton();

        lblTitulo.setText("Trenes y horarios");
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(java.awt.Font.PLAIN, 16f));

        lblFecha.setText("Fecha:");
        datePicker = new DatePicker(14);
        datePicker.getCampoFecha().addActionListener(this::txtFechaActionPerformed);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        tblTrenes.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] { "ID", "Código", "Fecha", "Salida", "Llegada", "Capacidad", "Precio", "Estado" }));
        tblTrenes.setRowHeight(28);
        scrTrenes.setViewportView(tblTrenes);

        btnReservar.setText("Reservar seleccionado");
        btnReservar.addActionListener(this::btnReservarActionPerformed);

        lblPaginacion = new javax.swing.JLabel("Página 1 de 1");
        btnPagAnterior = new javax.swing.JButton("Anterior");
        btnPagSiguiente = new javax.swing.JButton("Siguiente");
        btnPagAnterior.addActionListener(this::btnPagAnteriorActionPerformed);
        btnPagSiguiente.addActionListener(this::btnPagSiguienteActionPerformed);

        pnlPaginacion = new javax.swing.JPanel();
        pnlPaginacion.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 4));
        pnlPaginacion.setOpaque(false);
        pnlPaginacion.add(btnPagAnterior);
        pnlPaginacion.add(lblPaginacion);
        pnlPaginacion.add(btnPagSiguiente);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTitulo)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblFecha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 220,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReservar)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(scrTrenes, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                        .addComponent(pnlPaginacion, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTitulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblFecha)
                                        .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBuscar)
                                        .addComponent(btnReservar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrTrenes, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlPaginacion, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap()));
    }// </editor-fold>//GEN-END:initComponents

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        String fechaFiltro = datePicker.getText();
        if (fechaFiltro.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese una fecha para buscar (yyyy-MM-dd)", "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        HorarioDAO hDao = new HorarioDAO();
        List<Horario> horarios = hDao.buscarPorFecha(fechaFiltro);
        llenarDatosDesdeHorarios(horarios);
        if (horarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No existen horarios para la fecha indicada", "Sin resultados",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cargarHorariosIniciales() {
        HorarioDAO dao = new HorarioDAO();
        List<Horario> horarios = dao.listarTodos();
        llenarDatosDesdeHorarios(horarios);
    }

    private void llenarDatosDesdeHorarios(List<Horario> horarios) {
        datosCompletos.clear();
        for (Horario h : horarios) {
            if (h.getTren() == null)
                continue;
            datosCompletos.add(new Object[] {
                    h.getId(),
                    h.getTren().getCodigo(),
                    h.getFecha(),
                    h.getHoraSalida(),
                    h.getHoraLlegada(),
                    h.getTren().getCapacidad(),
                    h.getTren().getPrecio(),
                    "Libre"
            });
        }
        paginaActual = 0;
        actualizarTablaYPaginacion();
    }

    private void actualizarTablaYPaginacion() {
        int total = datosCompletos.size();
        int totalPaginas = total == 0 ? 1 : (total + FILAS_POR_PAGINA - 1) / FILAS_POR_PAGINA;
        if (paginaActual >= totalPaginas)
            paginaActual = Math.max(0, totalPaginas - 1);

        int desde = paginaActual * FILAS_POR_PAGINA;
        int hasta = Math.min(desde + FILAS_POR_PAGINA, total);

        DefaultTableModel modelo = (DefaultTableModel) tblTrenes.getModel();
        modelo.setRowCount(0);
        for (int i = desde; i < hasta; i++) {
            modelo.addRow(datosCompletos.get(i));
        }

        lblPaginacion.setText("Página " + (totalPaginas == 0 ? 0 : paginaActual + 1) + " de " + totalPaginas + "  ("
                + total + " registros)");
        btnPagAnterior.setEnabled(paginaActual > 0);
        btnPagSiguiente.setEnabled(paginaActual < totalPaginas - 1 && totalPaginas > 0);
    }

    private void btnPagAnteriorActionPerformed(java.awt.event.ActionEvent evt) {
        if (paginaActual > 0) {
            paginaActual--;
            actualizarTablaYPaginacion();
        }
    }

    private void btnPagSiguienteActionPerformed(java.awt.event.ActionEvent evt) {
        int totalPaginas = datosCompletos.isEmpty() ? 1
                : (datosCompletos.size() + FILAS_POR_PAGINA - 1) / FILAS_POR_PAGINA;
        if (paginaActual < totalPaginas - 1) {
            paginaActual++;
            actualizarTablaYPaginacion();
        }
    }

    private void btnReservarActionPerformed(java.awt.event.ActionEvent evt) {
        int fila = tblTrenes.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un tren para reservar", "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int indiceReal = paginaActual * FILAS_POR_PAGINA + fila;
        if (indiceReal >= datosCompletos.size())
            return;
        Object[] row = datosCompletos.get(indiceReal);
        String idHorario = row[0].toString();
        String codigoTren = row[1].toString();
        String fecha = row[2].toString();
        double precioTren = 0.0;
        try {
            precioTren = Double.parseDouble(row[6].toString());
        } catch (Exception ignored) {
        }

        modelo.Usuario u = modelo.ControlSesion.getUsuario();
        if (u == null) {
            JOptionPane.showMessageDialog(this, "No hay sesión activa");
            return;
        }

        org.bson.Document reserva = new org.bson.Document()
                .append("username", u.getUsername())
                .append("tren", codigoTren)
                .append("fecha", fecha)
                .append("idHorario", idHorario)
                .append("monto", precioTren)
                .append("estado", "PENDIENTE");

        // Patrón Facade: usa la fachada para crear la reserva
        controlador.SistemaFacade fach = controlador.SistemaFacade.getInstancia();
        if (fach.crearReserva(reserva)) {
            JOptionPane.showMessageDialog(this, "Reserva creada exitosamente");
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear reserva (¿Ya existe?)", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnPagAnterior;
    private javax.swing.JButton btnPagSiguiente;
    private javax.swing.JButton btnReservar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblPaginacion;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlPaginacion;
    private javax.swing.JScrollPane scrTrenes;
    private javax.swing.JTable tblTrenes;
    private DatePicker datePicker;
    // End of variables declaration//GEN-END:variables
}
