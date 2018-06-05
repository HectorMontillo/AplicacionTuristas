
package modelo;

public class Cliente {
    private String ID;
    private String Nombre;
    private int Riesgo;
    private double SaldoTotal;
    private double Abonado;
    
    public Cliente(){}

    public Cliente(String ID, String Nombre, double SaldoTotal, double Abonado, int Riesgo) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.SaldoTotal = SaldoTotal;
        this.Abonado = Abonado;
        this.Riesgo = Riesgo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getRiesgo() {
        return Riesgo;
    }

    public void setRiesgo(int Riesgo) {
        this.Riesgo = Riesgo;
    }

    public double getSaldoTotal() {
        return SaldoTotal;
    }

    public void setSaldoTotal(double SaldoTotal) {
        this.SaldoTotal = SaldoTotal;
    }

    public double getAbonado() {
        return Abonado;
    }

    public void setAbonado(double Abonado) {
        this.Abonado = Abonado;
    }
    
    
  
}
