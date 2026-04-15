package vista;

import controlador.TrenControlador;
import modelo.HorarioDAO;
import org.bson.Document;
import util.EstrategiaValidacion;
import util.ValidacionTren;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import modelo.ControlSesion;

// Patrón MVC: vista para el registro de trenes y horarios.
// Patrón Strategy: utiliza EstrategiaValidacion (ValidacionTren) para validar los datos del formulario.
// SOLID (S): responsabilidad única — solo gestiona la interfaz de registro de trenes.
public class PnlRegistroTren extends JPanel {

    private final TrenControlador trenControlador = new TrenControlador();
    private final HorarioDAO horarioDAO = new HorarioDAO();
    private final List<Document> horariosTemp = new ArrayList<>();

    private static final int ANCHO_CAMPO = 200;

    private JLabel lblTitulo;
    private JTextField txtCodigo;
    private JTextField txtRuta;
    private JTextField txtCapacidad;
    private JTextField txtPrecio;
    private JCheckBox chkActivo;
    private DatePicker datePicker;
    private TimePicker timePickerSalida;
    private TimePicker timePickerLlegada;
    private JButton btnAgregarHorario;
    private JTable tblHorarios;
    private JScrollPane scrollTabla;
    private JButton btnGuardar;
    private JButton btnLimpiar;

    public PnlRegistroTren() {
        setBackground(TemaSistema.getFondo());
        setLayout(new BorderLayout(0, 0));
        setBorder(new EmptyBorder(TemaSistema.getPadding(), TemaSistema.getPadding(), TemaSistema.getPadding(),
                TemaSistema.getPadding()));

        if (!ControlSesion.getUsuario().getRol().equalsIgnoreCase("ADMIN")) {
            JOptionPane.showMessageDialog(this, "Acceso restringido", "Seguridad", JOptionPane.ERROR_MESSAGE);
            setEnabled(false);
        }

        initComponents();
        setModeloTabla();
    }

    private void initComponents() {
        lblTitulo = new JLabel("Registro de tren");
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.PLAIN, 16f));

        txtCodigo = new JTextField(12);
        txtRuta = new JTextField(12);
        txtCapacidad = new JTextField(6);
        txtPrecio = new JTextField(8);
        chkActivo = new JCheckBox("Activo", true);
        datePicker = new DatePicker(10);
        timePickerSalida = new TimePicker();
        timePickerLlegada = new TimePicker();
        btnAgregarHorario = new JButton("Agregar horario");
        tblHorarios = new JTable();
        scrollTabla = new JScrollPane(tblHorarios);
        btnGuardar = new JButton("Guardar");
        btnLimpiar = new JButton("Limpiar");

        txtCodigo.setMaximumSize(new Dimension(ANCHO_CAMPO, 28));
        txtRuta.setMaximumSize(new Dimension(ANCHO_CAMPO, 28));
        txtCapacidad.setMaximumSize(new Dimension(80, 28));
        txtPrecio.setMaximumSize(new Dimension(120, 28));

        scrollTabla.setPreferredSize(new Dimension(400, 120));
        scrollTabla.setMinimumSize(new Dimension(200, 80));

        btnAgregarHorario.addActionListener(e -> agregarHorario());
        btnGuardar.addActionListener(e -> guardarTodo());
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(TemaSistema.getFondo());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 6, 4, 6);
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        c.gridx = 0;
        c.gridy = row;
        c.weightx = 0;
        form.add(lblTitulo, c);
        row++;
        c.gridy = row;
        c.gridwidth = 2;
        form.add(Box.createVerticalStrut(12), c);
        row++;
        c.gridwidth = 1;

        c.gridy = row;
        form.add(new JLabel("Código"), c);
        c.gridx = 1;
        c.weightx = 1;
        form.add(txtCodigo, c);
        c.gridx = 0;
        row++;
        c.gridy = row;
        form.add(new JLabel("Ruta"), c);
        c.gridx = 1;
        c.weightx = 1;
        form.add(txtRuta, c);
        c.gridx = 0;
        row++;
        c.gridy = row;
        form.add(new JLabel("Capacidad"), c);
        c.gridx = 1;
        c.weightx = 1;
        form.add(txtCapacidad, c);
        c.gridx = 0;
        row++;
        c.gridy = row;
        form.add(new JLabel("Precio ($)"), c);
        c.gridx = 1;
        c.weightx = 1;
        form.add(txtPrecio, c);
        c.gridx = 0;
        row++;
        c.gridy = row;
        c.gridwidth = 2;
        form.add(chkActivo, c);
        c.gridwidth = 1;
        row++;
        c.gridy = row;
        form.add(Box.createVerticalStrut(16), c);
        row++;

        JLabel lblSecHorarios = new JLabel("Horarios");
        lblSecHorarios.setFont(lblSecHorarios.getFont().deriveFont(Font.PLAIN, 14f));
        c.gridy = row;
        c.gridwidth = 2;
        form.add(lblSecHorarios, c);
        c.gridwidth = 1;
        row++;
        c.gridy = row;
        form.add(new JLabel("Fecha"), c);
        c.gridx = 1;
        c.weightx = 1;
        form.add(datePicker, c);
        c.gridx = 0;
        row++;
        c.gridy = row;
        form.add(new JLabel("Hora salida"), c);
        c.gridx = 1;
        c.weightx = 1;
        form.add(timePickerSalida, c);
        c.gridx = 0;
        row++;
        c.gridy = row;
        form.add(new JLabel("Hora llegada"), c);
        c.gridx = 1;
        c.weightx = 1;
        form.add(timePickerLlegada, c);
        c.gridx = 0;
        row++;
        JPanel panelAgregar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelAgregar.setOpaque(false);
        panelAgregar.add(btnAgregarHorario);
        c.gridy = row;
        c.gridwidth = 2;
        form.add(panelAgregar, c);
        c.gridwidth = 1;
        row++;
        c.gridy = row;
        c.weighty = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        form.add(scrollTabla, c);
        row++;
        c.gridy = row;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEADING, 8, 0));
        botones.setOpaque(false);
        botones.add(btnGuardar);
        botones.add(btnLimpiar);
        c.gridwidth = 2;
        form.add(botones, c);
        c.gridwidth = 1;
        row++;

        c.gridy = row;
        c.weighty = 1;
        c.fill = GridBagConstraints.VERTICAL;
        form.add(Box.createVerticalGlue(), c);

        JScrollPane scrollForm = new JScrollPane(form);
        scrollForm.setBorder(null);
        scrollForm.getViewport().setBackground(TemaSistema.getFondo());
        scrollForm.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollForm.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollForm, BorderLayout.CENTER);
    }

    private void setModeloTabla() {
        tblHorarios.setModel(new DefaultTableModel(
                new Object[] { "Fecha", "Hora Salida", "Hora Llegada" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    private void agregarHorario() {
        String fecha = datePicker.getText();
        String salida = timePickerSalida.getText();
        String llegada = timePickerLlegada.getText();

        if (fecha.isEmpty() || salida.isEmpty() || llegada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete fecha y horas del horario.");
            return;
        }
        if (salida.compareTo(llegada) >= 0) {
            JOptionPane.showMessageDialog(this, "La hora de llegada debe ser posterior a la salida.");
            return;
        }

        Document horario = new Document()
                .append("fecha", fecha)
                .append("horaSalida", salida)
                .append("horaLlegada", llegada);
        horariosTemp.add(horario);

        DefaultTableModel modelo = (DefaultTableModel) tblHorarios.getModel();
        modelo.addRow(new Object[] { fecha, salida, llegada });

        timePickerSalida.setText("");
        timePickerLlegada.setText("");
    }

    private void guardarTodo() {
        if (!ControlSesion.getUsuario().getRol().equalsIgnoreCase("ADMIN"))
            return;

        String codigo = txtCodigo.getText().trim();
        String ruta = txtRuta.getText().trim();
        String capStr = txtCapacidad.getText().trim();
        String precioStr = txtPrecio.getText().trim();

        // Patrón Strategy: delega la validación a la estrategia concreta ValidacionTren
        EstrategiaValidacion estrategia = new ValidacionTren();
        Map<String, Object> datos = new HashMap<>();
        datos.put("codigo", codigo);
        datos.put("ruta", ruta);
        datos.put("capacidad", capStr.isEmpty() ? null : capStr);
        String error = estrategia.validar(datos);
        if (error != null) {
            JOptionPane.showMessageDialog(this, error);
            return;
        }
        if (precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el precio del tren.");
            return;
        }
        double precio;
        try {
            precio = Double.parseDouble(precioStr);
            if (precio <= 0) {
                JOptionPane.showMessageDialog(this, "El precio debe ser mayor a 0.");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio inválido. Ingrese un número.");
            return;
        }
        if (horariosTemp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe agregar al menos un horario.");
            return;
        }

        int capacidad = Integer.parseInt(capStr);
        Document tren = new Document()
                .append("codigo", codigo)
                .append("ruta", ruta)
                .append("capacidad", capacidad)
                .append("precio", precio)
                .append("activo", chkActivo.isSelected());

        if (trenControlador.registrarTren(tren)) {
            for (Document h : horariosTemp) {
                h.append("codigoTren", codigo);
                horarioDAO.insertar(h);
            }
            JOptionPane.showMessageDialog(this, "Tren y horarios registrados correctamente.");
            limpiarFormulario();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar tren (¿código duplicado?).");
        }
    }

    private void limpiarFormulario() {
        txtCodigo.setText("");
        txtRuta.setText("");
        txtCapacidad.setText("");
        txtPrecio.setText("");
        chkActivo.setSelected(true);
        datePicker.setText("");
        datePicker.setDate(LocalDate.now());
        timePickerSalida.setText("");
        timePickerLlegada.setText("");
        horariosTemp.clear();
        setModeloTabla();
    }
}
