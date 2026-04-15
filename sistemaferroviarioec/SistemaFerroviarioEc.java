
package sistemaferroviarioec;

import vista.FrmLogin;
import vista.TemaSistema;

public class SistemaFerroviarioEc {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            TemaSistema.aplicar();
            new FrmLogin().setVisible(true);
        });
    }
}
