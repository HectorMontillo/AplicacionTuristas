/*
Cosas por hacer
agregar commit: vistas centradas, error de nombre_paquete decorator arreglado
*/
package proyecto;

import controlador.Controlador;


public class Proyecto {

    public static void main(String[] args) {

        //JFrame view = new ViewLogin();
        //Modelo modelo = new Modelo();
        Controlador controlador = new Controlador();
        controlador.iniciar();

    }
    
}
