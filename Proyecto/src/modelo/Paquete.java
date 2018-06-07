package modelo;

import java.util.ArrayList;

public class Paquete {

    int ID;
    String Nombre;
    String Destino;
    public ArrayList Excursiones = new ArrayList();
    
    
    public Paquete(){}

    public Paquete(int ID, String Nombre, String Destino) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Destino = Destino;
    }
    
    public void AgregarExcursion(int ID,String IDOperador, int IDHotel, String Lugar,int Dias){
        ExcursionPlus ex = new ExcursionPlus(ID,IDOperador,IDHotel,Lugar,Dias);
        Excursiones.add(ex);
        }
    
    public void AgregarExcursion(ExcursionPlus ex){
        Excursiones.add(ex);   
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String Destino) {
        this.Destino = Destino;
    }
    
        
}
