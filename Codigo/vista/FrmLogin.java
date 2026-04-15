
package vista;

import controlador.SistemaFacade;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import modelo.ControlSesion;
import modelo.Usuario;

// Patrón MVC: vista de inicio de sesión (punto de entrada de la aplicación).
// SOLID (S): responsabilidad única — solo gestiona la interfaz de login.
// SOLID (D): inversión de dependencias — depende de SistemaFacade (abstracción), no de DAOs concretos.
public class FrmLogin extends javax.swing.JFrame {

    public FrmLogin() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Iniciar sesión — Sistema Ferroviario");
        getContentPane().setBackground(TemaSistema.getFondo());

        lblTitulo = new javax.swing.JLabel("Sistema Ferroviario Ecuador");
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(java.awt.Font.PLAIN, 20f));
        lblTitulo.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        lblCorreo = new javax.swing.JLabel("Usuario");
        lblPassword = new javax.swing.JLabel("Contraseña");
        txtUsuario = new javax.swing.JTextField(20);
        txtPassword = new javax.swing.JPasswordField(20);
        txtUsuario.setPreferredSize(new Dimension(260, 36));
        txtPassword.setPreferredSize(new Dimension(260, 36));
        txtPassword.addActionListener(this::txtPasswordActionPerformed);

        btnIngresar = new javax.swing.JButton("Ingresar");
        btnRegistrar = new javax.swing.JButton("Registrarse");
        btnIngresar.setPreferredSize(new Dimension(130, 40));
        btnRegistrar.setPreferredSize(new Dimension(130, 40));
        btnIngresar.addActionListener(this::btnIngresarActionPerformed);
        btnRegistrar.addActionListener(this::btnRegistrarActionPerformed);

        JPanel formCard = new JPanel();
        formCard.setBackground(TemaSistema.getFondoCard());
        formCard.setBorder(new EmptyBorder(32, 40, 32, 40));
        formCard.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 8, 6, 8);
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0;
        formCard.add(lblTitulo, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_START;
        formCard.add(Box.createVerticalStrut(22), c);

        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        formCard.add(lblCorreo, c);
        c.gridx = 1;
        c.weightx = 1;
        formCard.add(txtUsuario, c);
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0;
        formCard.add(lblPassword, c);
        c.gridx = 1;
        c.weightx = 1;
        formCard.add(txtPassword, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.weightx = 0;
        c.insets = new Insets(22, 8, 0, 8);
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        botones.setOpaque(false);
        botones.add(btnIngresar);
        botones.add(btnRegistrar);
        formCard.add(botones, c);

        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(TemaSistema.getFondo());
        center.setBorder(new EmptyBorder(24, 24, 24, 24));
        center.add(formCard, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(center, BorderLayout.CENTER);

        pack();
    }

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {

        String username = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar usuario y contraseña",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Patrón Facade + SOLID (D): accede a la lógica de negocio mediante la fachada
        SistemaFacade fachada = SistemaFacade.getInstancia();
        Usuario usuario = fachada.login(username, password);

        if (usuario != null) {
            // Patrón Singleton: inicia la sesión global
            ControlSesion.iniciarSesion(usuario);

            JOptionPane.showMessageDialog(
                    this,
                    "Bienvenido " + usuario.getNombre(),
                    "Acceso concedido",
                    JOptionPane.INFORMATION_MESSAGE);

            FrmPrincipal principal = new FrmPrincipal();
            principal.setVisible(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Usuario o contraseña incorrectos",
                    "Error de autenticación",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {
        btnIngresarActionPerformed(evt);
    }

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {
        JDialog dialog = new JDialog(this, "Registro de usuario", true);
        dialog.getContentPane().setBackground(TemaSistema.getFondo());
        dialog.setContentPane(new PnlUsuario(true));
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            TemaSistema.aplicar();
            new FrmLogin().setVisible(true);
        });
    }

    // Variables declaration
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
}
