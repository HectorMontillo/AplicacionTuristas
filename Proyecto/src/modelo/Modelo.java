package modelo;

import javax.swing.JOptionPane;
import vista.ViewCrearPaquete;
import vista.ViewExcursiones;

public class Modelo{
    
    Administrador Admin = null;
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
                JOptionPane.showMessageDialog(null, "Inició sesión: Administrador");
                return true;
            case 1:
                vendedor.setClave(Clave);
                vendedor.setCodigo(Codigo);
                usuario = vendedor;
                JOptionPane.showMessageDialog(null, "Inició sesión: Vendedor");
                return true;
            default:
                JOptionPane.showMessageDialog(null, "No se encontró a ningun usuario","error",JOptionPane.OK_OPTION);
                return false;
        }
         
         
    }
    
    public boolean Registro(String Codigo, String Usuario, String Clave, int Tipo){
        if(Tipo == 0){
            Admin = Administrador.getSingleton(Codigo,Usuario,Clave);
            System.out.println(Admin);
            System.out.println(sql.ConsultaAdministrador());
            if ((Admin != null) && (this.CargarAdministradores() == false)){
                JOptionPane.showMessageDialog(null, "Se registró como Administrador");
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
    public boolean CrearPaquete(String nombre_paquete, String destino){
        if(sql.agregarPaquete(nombre_paquete, destino)){
           JOptionPane.showMessageDialog(null, "Se agrego un nuevo paquete");
           return true;
        } 
        else{
            return false;
        }
    }
    
    public boolean CrearExcursion(String lugar, String id_operador, String hotel, int dias){
        
        boolean result = false;
        int id_hotel = sql.obtenerIdHotel(hotel);
        if(id_hotel != 0){ // si retorna cero es porq hay 1 error 
            if(sql.registrarExcursion(id_operador,id_hotel,lugar,dias)){
                JOptionPane.showMessageDialog(null, "Se registro la excursion"); 
                result= true; 
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "error con el id del hotel");
        }
        return result;
    }
    
    public void CrearPaqueteExcursion(String nom_paquete){
        int id_paquete = sql.obtenerIdPaquete(nom_paquete);
        int id_excursion = sql.obtenerIdExcursion(); // se hace en base a la  ultima excursion agregada 
        
        if(id_paquete != 0 && id_excursion != 0){
            if(sql.registrarPaqueteExcursion(id_paquete,id_excursion)){ // registrar paquete_excursion
                JOptionPane.showMessageDialog(null, "Se registro paquete-excursion");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "error con id_paquete ó id_excursion");
        }
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

    public void cargarCombo(ViewCrearPaquete view_paquete){
        sql.cargarComboBox(view_paquete);
    }
    public void cargarComboOperadores(ViewExcursiones view_excursion){
        sql.cargarComboOperadores(view_excursion);
    }
    public void cargarComboHoteles(ViewExcursiones view_excursion){
        sql.cargarComboHoteles(view_excursion);
    }
}
