
package modelo;

public class Reserva {
    private int ID;
    private int IDPaquete;
    private int[] Fechainicio = new int[2];
    private int[] Fechafinal = new int[2];
    private int IDVuelo;
    private String IDCliente;
    private boolean Pagado;
    private String IDVendedor;
    
    public Reserva(){}

    public Reserva(int ID, int IDPaquete, int IDVuelo, String IDCliente, boolean Pagado, String IDVendedor) {
        this.ID = ID;
        this.IDPaquete = IDPaquete;
        this.IDVuelo = IDVuelo;
        this.IDCliente = IDCliente;
        this.Pagado = Pagado;
        this.IDVendedor = IDVendedor;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDPaquete() {
        return IDPaquete;
    }

    public void setIDPaquete(int IDPaquete) {
        this.IDPaquete = IDPaquete;
    }

    public int[] getFechainicio() {
        return Fechainicio;
    }

    public void setFechainicio(int[] Fechainicio) {
        this.Fechainicio = Fechainicio;
    }

    public int[] getFechafinal() {
        return Fechafinal;
    }

    public void setFechafinal(int[] Fechafinal) {
        this.Fechafinal = Fechafinal;
    }

    public int getIDVuelo() {
        return IDVuelo;
    }

    public void setIDVuelo(int IDVuelo) {
        this.IDVuelo = IDVuelo;
    }

    public String getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(String IDCliente) {
        this.IDCliente = IDCliente;
    }

    public boolean isPagado() {
        return Pagado;
    }

    public void setPagado(boolean Pagado) {
        this.Pagado = Pagado;
    }

    public String getIDVendedor() {
        return IDVendedor;
    }

    public void setIDVendedor(String IDVendedor) {
        this.IDVendedor = IDVendedor;
    }
    
    
    
    
    
    
    
}
