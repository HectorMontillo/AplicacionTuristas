/*
Cosas por hacer
agregar la relacion paquete excursion dentro del decorator 
no permitir que un empleado, cree paquetes 
crear el boton para mostrar lista de reservas ( con un combobox) 
Boton buscar dentro de reservas no esta funcionando
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
