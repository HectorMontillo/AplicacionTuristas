package modelo;

public class Administrador extends Usuario{
    
    private static Administrador Admin;
    
    public static Administrador getSingleton(String Codigo, String Usuario,String Clave){
        if (Admin == null){
            Admin = new Administrador(Codigo, Usuario, Clave);
            return Admin;
        }else{
            System.out.println("Ya existe un administrador");
            return null;
        }
        
    }
    
    private Administrador(String Codigo,String Usuario, String Clave){
        super.setCodigo(Codigo);
        super.setUsuario(Usuario);
        super.setClave(Clave);
    }

    
}
