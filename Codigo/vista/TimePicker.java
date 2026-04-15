package vista;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// SOLID (S): responsabilidad única — componente reutilizable de selección de hora (HH:mm).
public class TimePicker extends JPanel {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("HH:mm");
    private final JSpinner spinnerHora;
    private final JSpinner spinnerMinuto;

    public TimePicker() {
        setLayout(new FlowLayout(FlowLayout.LEADING, 4, 0));
        setOpaque(false);

        SpinnerNumberModel modelHora = new SpinnerNumberModel(7, 0, 23, 1);
        SpinnerNumberModel modelMinuto = new SpinnerNumberModel(0, 0, 59, 1);
        spinnerHora = new JSpinner(modelHora);
        spinnerMinuto = new JSpinner(modelMinuto);

        JComponent editorH = spinnerHora.getEditor();
        if (editorH instanceof JSpinner.DefaultEditor) {
            ((JSpinner.DefaultEditor) editorH).getTextField().setColumns(2);
        }
        JComponent editorM = spinnerMinuto.getEditor();
        if (editorM instanceof JSpinner.DefaultEditor) {
            ((JSpinner.DefaultEditor) editorM).getTextField().setColumns(2);
        }

        spinnerHora.setPreferredSize(new Dimension(48, 28));
        spinnerMinuto.setPreferredSize(new Dimension(48, 28));

        add(spinnerHora);
        add(new JLabel(" : "));
        add(spinnerMinuto);
    }

    public String getText() {
        int h = (Integer) spinnerHora.getValue();
        int m = (Integer) spinnerMinuto.getValue();
        return String.format("%02d:%02d", h, m);
    }

    public void setText(String horaMinuto) {
        if (horaMinuto == null || horaMinuto.trim().isEmpty())
            return;
        try {
            LocalTime t = LocalTime.parse(horaMinuto.trim(), FORMATO);
            spinnerHora.setValue(t.getHour());
            spinnerMinuto.setValue(t.getMinute());
        } catch (DateTimeParseException ignored) {
        }
    }

    public void setTime(int hora, int minuto) {
        spinnerHora.setValue(Math.max(0, Math.min(23, hora)));
        spinnerMinuto.setValue(Math.max(0, Math.min(59, minuto)));
    }
}
