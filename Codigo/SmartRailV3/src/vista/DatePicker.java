package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// SOLID (S): responsabilidad única — componente reutilizable de selección de fecha.
public class DatePicker extends JPanel {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ISO_LOCAL_DATE;
    private final JTextField campoFecha;
    private final JButton btnCalendario;
    private LocalDate fechaActual;

    public DatePicker(int columnas) {
        setLayout(new BorderLayout(4, 0));
        setOpaque(false);
        campoFecha = new JTextField(columnas);
        campoFecha.setEditable(true);
        fechaActual = LocalDate.now();
        campoFecha.setText(fechaActual.format(FORMATO));

        btnCalendario = new JButton("\u2637");
        btnCalendario.setToolTipText("Elegir fecha");
        btnCalendario.setPreferredSize(new Dimension(36, campoFecha.getPreferredSize().height));
        btnCalendario.setFocusPainted(false);
        btnCalendario.addActionListener(e -> mostrarCalendario());

        add(campoFecha, BorderLayout.CENTER);
        add(btnCalendario, BorderLayout.EAST);
    }

    public DatePicker() {
        this(12);
    }

    private void mostrarCalendario() {
        JPopupMenu popup = new JPopupMenu();
        popup.setLightWeightPopupEnabled(true);

        JPanel cal = new JPanel(new BorderLayout(0, 8));
        cal.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        cal.setBackground(TemaSistema.getFondoCard());

        LocalDate fechaSeleccionada = obtenerFechaDelCampo();
        if (fechaSeleccionada == null)
            fechaSeleccionada = LocalDate.now();
        YearMonth mes = YearMonth.from(fechaSeleccionada);

        JLabel lblMes = new JLabel(mes.getMonth().toString().toLowerCase() + " " + mes.getYear(),
                SwingConstants.CENTER);
        lblMes.setFont(lblMes.getFont().deriveFont(Font.PLAIN, 14f));
        JPanel nav = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        nav.setOpaque(false);
        JButton prev = new JButton("\u2190");
        prev.setPreferredSize(new Dimension(32, 28));
        prev.setFocusPainted(false);
        JButton next = new JButton("\u2192");
        next.setPreferredSize(new Dimension(32, 28));
        next.setFocusPainted(false);
        nav.add(prev);
        nav.add(lblMes);
        nav.add(next);
        cal.add(nav, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(0, 7, 2, 2));
        grid.setOpaque(false);
        String[] diasSemana = { "Lu", "Ma", "Mi", "Ju", "Vi", "Sa", "Do" };
        for (String d : diasSemana) {
            JLabel l = new JLabel(d, SwingConstants.CENTER);
            l.setFont(l.getFont().deriveFont(Font.PLAIN, 11f));
            grid.add(l);
        }

        final YearMonth[] refMes = new YearMonth[] { mes };
        Runnable actualizarGrid = () -> {
            grid.removeAll();
            for (String d : diasSemana) {
                JLabel l = new JLabel(d, SwingConstants.CENTER);
                l.setFont(l.getFont().deriveFont(Font.PLAIN, 11f));
                grid.add(l);
            }
            YearMonth m = refMes[0];
            String nombreMes = m.getMonth().toString().toLowerCase();
            if (!nombreMes.isEmpty())
                nombreMes = nombreMes.substring(0, 1).toUpperCase() + nombreMes.substring(1);
            lblMes.setText(nombreMes + " " + m.getYear());
            int primerDia = m.atDay(1).getDayOfWeek().getValue();
            int diasEnMes = m.lengthOfMonth();
            int celdasPrev = primerDia - 1;
            YearMonth mesAnt = m.minusMonths(1);
            int ultimoDiaAnt = mesAnt.lengthOfMonth();
            for (int i = 0; i < celdasPrev; i++) {
                int dia = ultimoDiaAnt - celdasPrev + 1 + i;
                JLabel celda = new JLabel(String.valueOf(dia), SwingConstants.CENTER);
                celda.setForeground(Color.GRAY);
                celda.setOpaque(true);
                celda.setBackground(new Color(0xF5F5F5));
                grid.add(celda);
            }
            for (int dia = 1; dia <= diasEnMes; dia++) {
                final LocalDate ld = m.atDay(dia);
                JLabel celda = new JLabel(String.valueOf(dia), SwingConstants.CENTER);
                celda.setOpaque(true);
                celda.setBackground(Color.WHITE);
                celda.setBorder(BorderFactory.createLineBorder(new Color(0xE0E0E0)));
                celda.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        campoFecha.setText(ld.format(FORMATO));
                        fechaActual = ld;
                        popup.setVisible(false);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        celda.setBackground(new Color(0xE3F2FD));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        celda.setBackground(Color.WHITE);
                    }
                });
                grid.add(celda);
            }
            int restantes = 42 - (celdasPrev + diasEnMes);
            for (int i = 0; i < restantes; i++) {
                JLabel celda = new JLabel(String.valueOf(i + 1), SwingConstants.CENTER);
                celda.setForeground(Color.GRAY);
                celda.setOpaque(true);
                celda.setBackground(new Color(0xF5F5F5));
                grid.add(celda);
            }
            grid.revalidate();
            grid.repaint();
        };

        prev.addActionListener(e -> {
            refMes[0] = refMes[0].minusMonths(1);
            actualizarGrid.run();
        });
        next.addActionListener(e -> {
            refMes[0] = refMes[0].plusMonths(1);
            actualizarGrid.run();
        });

        cal.add(grid, BorderLayout.CENTER);
        actualizarGrid.run();

        popup.add(cal);
        popup.show(btnCalendario, 0, btnCalendario.getHeight());
    }

    private LocalDate obtenerFechaDelCampo() {
        String t = campoFecha.getText().trim();
        if (t.isEmpty())
            return null;
        try {
            return LocalDate.parse(t, FORMATO);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public String getText() {
        return campoFecha.getText().trim();
    }

    public void setText(String fecha) {
        campoFecha.setText(fecha != null ? fecha : "");
    }

    public LocalDate getDate() {
        return obtenerFechaDelCampo();
    }

    public void setDate(LocalDate date) {
        if (date != null) {
            campoFecha.setText(date.format(FORMATO));
            fechaActual = date;
        }
    }

    public JTextField getCampoFecha() {
        return campoFecha;
    }
}
