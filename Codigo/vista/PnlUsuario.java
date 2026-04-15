
package vista;

import controlador.UsuarioControlador;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelo.Usuario;
import util.EstrategiaValidacion;
import util.ValidacionUsuario;

// Patrón MVC: vista para la gestión y registro de usuarios.
// Patrón Strategy: utiliza EstrategiaValidacion (ValidacionUsuario) para validar los datos del formulario.
// SOLID (S): responsabilidad única — solo gestiona la interfaz de usuarios.
public class PnlUsuario extends javax.swing.JPanel {

    public PnlUsuario() {
        initComponents();
        setBackground(vista.TemaSistema.getFondo());
        jPanel1.setBackground(vista.TemaSistema.getFondo());
        setBorder(javax.swing.BorderFactory.createEmptyBorder(vista.TemaSistema.getPadding(),
                vista.TemaSistema.getPadding(), vista.TemaSistema.getPadding(), vista.TemaSistema.getPadding()));
    }

    private boolean modoRegistro;

    public PnlUsuario(boolean modoRegistro) {
        initComponents();
        setBackground(vista.TemaSistema.getFondo());
        jPanel1.setBackground(vista.TemaSistema.getFondo());
        setBorder(javax.swing.BorderFactory.createEmptyBorder(vista.TemaSistema.getPadding(),
                vista.TemaSistema.getPadding(), vista.TemaSistema.getPadding(), vista.TemaSistema.getPadding()));
        this.modoRegistro = modoRegistro;

        if (modoRegistro) {
            cboRol.setSelectedItem("Cliente");
            cboRol.setEnabled(false);
        } else {
            if (!modelo.ControlSesion.getUsuario().getRol().equalsIgnoreCase("ADMIN")) {
                JOptionPane.showMessageDialog(this, "Acceso restringido a Administradores");
                txtNombre.setEnabled(false);
                txtUsuario.setEnabled(false);
                txtPassword.setEnabled(false);
                cboRol.setEnabled(false);
                btnGuardar.setEnabled(false);
                btnLimpiar.setEnabled(false);
                return;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        lblContraseña = new javax.swing.JLabel();
        lblRol = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        cboRol = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        lblTitulo.setText("Gestión de usuarios");
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(java.awt.Font.PLAIN, 16f));

        lblNombre.setText("Nombre");
        lblCorreo.setText("Usuario");
        lblContraseña.setText("Contraseña");
        lblRol.setText("Rol");

        txtNombre.setColumns(22);
        txtUsuario.setColumns(22);
        txtPassword.setColumns(22);
        cboRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente", "Admin" }));
        cboRol.addActionListener(this::cboRolActionPerformed);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTitulo)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 90,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 90,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 90,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblRol, javax.swing.GroupLayout.PREFERRED_SIZE, 90,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNombre)
                                        .addComponent(txtUsuario)
                                        .addComponent(txtPassword)
                                        .addComponent(cboRol, 0, 240, Short.MAX_VALUE))
                                .addGap(0, 0, 0))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpiar)
                                .addGap(0, 0, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTitulo)
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNombre)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCorreo)
                                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblContraseña)
                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblRol)
                                        .addComponent(cboRol, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnGuardar)
                                        .addComponent(btnLimpiar))
                                .addGap(0, 0, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, 0)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    private void cboRolActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {

        String nombre = txtNombre.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword());
        String rolSeleccionado = cboRol.getSelectedItem().toString();

        // Patrón Strategy: delega la validación a la estrategia concreta
        // ValidacionUsuario
        EstrategiaValidacion estrategia = new ValidacionUsuario();
        Map<String, Object> datos = new HashMap<>();
        datos.put("nombre", nombre);
        datos.put("username", usuario);
        datos.put("password", password);
        String error = estrategia.validar(datos);
        if (error != null) {
            JOptionPane.showMessageDialog(this, error, "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String rol;
        if (rolSeleccionado.equalsIgnoreCase("Admin")) {
            rol = "ADMIN";
        } else {
            rol = "CLIENTE";
        }

        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setUsername(usuario);
        u.setPassword(password);
        u.setRol(rol);

        UsuarioControlador control = new UsuarioControlador();

        if (control.registrarUsuario(u)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Usuario registrado correctamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            if (modoRegistro) {
                SwingUtilities.getWindowAncestor(this).dispose();
            } else {
                limpiarFormulario();
            }

        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "El nombre de usuario ya existe",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        limpiarFormulario();
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtUsuario.setText("");
        txtPassword.setText("");
        cboRol.setSelectedIndex(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cboRol;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
