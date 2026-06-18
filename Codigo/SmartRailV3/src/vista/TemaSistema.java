 package vista;

import javax.swing.*;
import java.awt.*;

// SOLID (S): responsabilidad única — centraliza la configuración visual del sistema (colores, fuentes, bordes).
public final class TemaSistema {

    private static final Color FONDO = new Color(0xF5F5F5);
    private static final Color FONDO_CARD = Color.WHITE;
    private static final Color TEXTO = new Color(0x333333);
    private static final Color TEXTO_SECUNDARIO = new Color(0x666666);
    private static final Color PRIMARIO = new Color(0x1976D2);
    private static final Color PRIMARIO_HOVER = new Color(0x1565C0);
    private static final int PADDING = 20;
    private static final String FUENTE = Font.SANS_SERIF;

    private TemaSistema() {
    }

    public static void aplicar() {
        Font f = new Font(FUENTE, Font.PLAIN, 14);
        UIManager.put("Label.font", f); 
        UIManager.put("Button.font", f);
        UIManager.put("TextField.font", f);
        UIManager.put("PasswordField.font", f);
        UIManager.put("ComboBox.font", f);
        UIManager.put("Table.font", f);
        UIManager.put("Menu.font", f);
        UIManager.put("MenuItem.font", f);
        UIManager.put("Panel.background", FONDO);
        UIManager.put("Label.background", FONDO);
        UIManager.put("Label.foreground", TEXTO);
        UIManager.put("TextField.background", FONDO_CARD);
        UIManager.put("TextField.foreground", TEXTO);
        UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0xDDDDDD), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        UIManager.put("TextField.caretForeground", PRIMARIO);
        UIManager.put("PasswordField.background", FONDO_CARD);
        UIManager.put("PasswordField.foreground", TEXTO);
        UIManager.put("PasswordField.border", UIManager.get("TextField.border"));
        UIManager.put("ComboBox.background", FONDO_CARD);
        UIManager.put("ComboBox.foreground", TEXTO);
        UIManager.put("Button.background", PRIMARIO);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(10, 18, 10, 18));
        UIManager.put("Button.focus", PRIMARIO_HOVER);
        UIManager.put("Table.background", FONDO_CARD);
        UIManager.put("Table.foreground", TEXTO);
        UIManager.put("Table.gridColor", new Color(0xEEEEEE));
        UIManager.put("Table.selectionBackground", new Color(0xE3F2FD));
        UIManager.put("Table.selectionForeground", TEXTO);
        UIManager.put("ScrollPane.background", FONDO);
        UIManager.put("ScrollPane.border", BorderFactory.createEmptyBorder(0, 0, 0, 0));
        UIManager.put("MenuBar.background", FONDO_CARD);
        UIManager.put("MenuBar.foreground", TEXTO);
        UIManager.put("Menu.background", FONDO_CARD);
        UIManager.put("Menu.foreground", TEXTO);
        UIManager.put("MenuItem.background", FONDO_CARD);
        UIManager.put("MenuItem.foreground", TEXTO);
        UIManager.put("MenuItem.selectionBackground", new Color(0xE3F2FD));
        UIManager.put("MenuItem.selectionForeground", PRIMARIO);
    }

    public static Color getFondo() {
        return FONDO;
    }

    public static Color getFondoCard() {
        return FONDO_CARD;
    }

    public static Color getTexto() {
        return TEXTO;
    }

    public static Color getPrimario() {
        return PRIMARIO;
    }

    public static int getPadding() {
        return PADDING;
    }
}
