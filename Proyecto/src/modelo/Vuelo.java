
package modelo;

public class Vuelo {
    private int ID;
    private int IDAeroline;
    private int IDTipo;
    
    public Vuelo(){}

    public Vuelo(int ID, int IDAeroline, int IDTipo) {
        this.ID = ID;
        this.IDAeroline = IDAeroline;
        this.IDTipo = IDTipo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDAeroline() {
        return IDAeroline;
    }

    public void setIDAeroline(int IDAeroline) {
        this.IDAeroline = IDAeroline;
    }

    public int getIDTipo() {
        return IDTipo;
    }

    public void setIDTipo(int IDTipo) {
        this.IDTipo = IDTipo;
    }
    
    
    
    
    
}
