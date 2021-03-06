
package modelo;

public class Cliente {
    private String ID;
    private String Nombre;
    private int Riesgo;
    private double SaldoTotal;
    private double Abonado;
    private SQL sql = new SQL();
    
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

    public void Abonar(double Abonado) {
        if (this.Abonado+Abonado < this.SaldoTotal){
             this.Abonado += Abonado;
        }else{
            this.Abonado += Abonado;
            this.Abonado -= this.SaldoTotal;
            this.SaldoTotal = 0;
            if (this.Riesgo < 2){
                this.Riesgo+=1;
            }
            sql.PagarReserva(this.ID);
        }
       
    }
    
    public void setAbonado(double Abonado){
        this.Abonado = Abonado;
    }
    
    
  
}
