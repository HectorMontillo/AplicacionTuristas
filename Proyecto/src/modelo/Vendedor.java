package modelo;

public class Vendedor extends Usuario{
    
    public Vendedor(String Codigo,String Usuario, String Clave) {
        super.setUsuario(Usuario);
        super.setClave(Clave);
        super.setCodigo(Codigo);
    } 
}
