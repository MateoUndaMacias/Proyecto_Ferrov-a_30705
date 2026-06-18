
package samartRail;

import vista.FrmLogin;
import vista.TemaSistema;

public class SmartRail {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            TemaSistema.aplicar();
            new FrmLogin().setVisible(true);
        });
    }
}
