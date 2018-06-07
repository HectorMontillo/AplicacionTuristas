
package modelo;

public class Aerolinea {
    private int ID;
    private String Nombre;
    private double Preciobase;
    
    public Aerolinea(){}

    public Aerolinea(int ID, String Nombre, double Preciobase) {
        this.ID = ID;
        this.Nombre = Nombre;
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

    public double getPreciobase() {
        return Preciobase;
    }

    public void setPreciobase(double Preciobase) {
        this.Preciobase = Preciobase;
    }
    
    
    
}
