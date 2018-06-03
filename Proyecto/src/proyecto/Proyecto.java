
package proyecto;

import controlador.Controlador;
import javax.swing.JFrame;
import modelo.Modelo;
import vista.ViewLogin;

/**
 *
 * @author LordBrakon
 */

public class Proyecto {

    public static void main(String[] args) {

        JFrame view = new ViewLogin();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador((ViewLogin) view,modelo);

    }
    
}
