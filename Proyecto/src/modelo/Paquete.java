package modelo;

import java.util.ArrayList;

public class Paquete {

    private int ID;
    private String Nombre;
    private String Destino;
    public ArrayList Excursiones = new ArrayList();
    private double Preciobase;
    
    
    public Paquete(){}

    public Paquete(int ID, String Nombre, String Destino, double Preciobase) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Destino = Destino;
        this.Preciobase = Preciobase;
    }
    

    public void AgregarExcursion(int ID,String IDOperador, int IDHotel, String Lugar,int Dias,double Preciobase){
        ExcursionPlus ex = new ExcursionPlus(ID,IDOperador,IDHotel,Lugar,Dias,Preciobase);
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

    public double getPreciobase() {
        return Preciobase;
    }

    public void setPreciobase(double Preciobase) {
        this.Preciobase = Preciobase;
    }
    
        
}
