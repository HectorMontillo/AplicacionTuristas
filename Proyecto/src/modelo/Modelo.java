package modelo;

import javax.swing.JOptionPane;

public class Modelo{
    
    Administrador Admin;
    Vendedor vendedor = new Vendedor("default","default","default");
    Usuario usuario;
    SQL sql = new SQL();
    int tipo;
    boolean flag;
    
    
 
    public Modelo(){}
    
    public boolean CargarAdministradores(){
        return sql.ConsultaAdministrador(); 
    }
    
    public boolean IniciarSesion(String Codigo, String Clave){
        
        tipo = sql.ConsultaInicio(Codigo, Clave);
         
        switch (tipo) {
            case 0:
                usuario = Admin;
                System.out.println("Adminstrador");
                return true;
            case 1:
                vendedor.setClave(Clave);
                vendedor.setCodigo(Codigo);
                usuario = vendedor;
                System.out.println("Vendedor");
                return true;
            default:
                System.out.println("No hay datos");
                return false;
        }
         
         
    }
    
    public boolean Registro(String Codigo, String Usuario, String Clave, int Tipo){
        if(Tipo == 0){
            Admin = Administrador.getSingleton(Codigo,Usuario,Clave);
            if ((Admin != null) && (this.CargarAdministradores() == false)){
                return sql.AgregarAdminstrador(Admin);
            } 
            else{
                 JOptionPane.showMessageDialog(null, "Ya existe un Administrador","error",JOptionPane.OK_OPTION);
                return false;
                
            }
        }
        else{
            vendedor = new Vendedor(Codigo,Usuario,Clave);
            return sql.AgregarVendedor(vendedor);
        }
    }
    public boolean CrearPaquete(){
        return true;
    }
    public boolean CrearExcursion(){
        return true;
    }
    public boolean RealizarReserva(){
        return true;
    }
    public boolean Facturar(){
        return true;
    }
    public boolean Abonar(){
        return true;
    }

  
}
