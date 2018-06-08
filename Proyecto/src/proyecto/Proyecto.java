/*
Cosas por hacer
agregar la relacion paquete excursion dentro del decorator --Ya
no permitir que un empleado, cree paquetes 
crear el boton para mostrar lista de reservas ( con un combobox) -- Ya
Boton buscar dentro de reservas no esta funcionando  --Ya
implementar otro patron de diseños 

cambie consulta sql en registra reserva  --A no hubo problem
aumente el id a la hora de registrar reserva, en le metodo registraReserva de modelo --> no hubo problem
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
