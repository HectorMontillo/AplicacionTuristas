
package modelo;

public class ExcursionPlus {
    private int ID;
    private String IDOperador;
    private int IDHotel;
    private String Lugar;
    private int Dias;

    private double Preciobase;
    
    public ExcursionPlus(){}

    public ExcursionPlus(int ID, String IDOperador, int IDHotel, String Lugar, int Dias, double Preciobase) {
        this.ID = ID;
        this.IDOperador = IDOperador;
        this.IDHotel = IDHotel;
        this.Lugar = Lugar;
        this.Dias = Dias;
        this.Preciobase = Preciobase;

    }

    public double getPrecio() {
        return Preciobase;
    }

    public void setPrecio(double precio) {
        this.Preciobase = precio;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIDOperador() {
        return IDOperador;
    }

    public void setIDOperador(String IDOperador) {
        this.IDOperador = IDOperador;
    }

    public int getIDHotel() {
        return IDHotel;
    }

    public void setIDHotel(int IDHotel) {
        this.IDHotel = IDHotel;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String Lugar) {
        this.Lugar = Lugar;
    }

    public int getDias() {
        return Dias;
    }

    public void setDias(int Dias) {
        this.Dias = Dias;
    }

    public double getPreciobase() {
        return Preciobase;
    }

    public void setPreciobase(double Preciobase) {
        this.Preciobase = Preciobase;
    }
    
    
    
    
    
    
}
