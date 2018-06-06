package modelo;

import javax.swing.JOptionPane;

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
    public boolean CrearExcursion(){
        return true;
    }
    public boolean RealizarReserva(){
        return true;
    }
    public boolean Facturar(){
        return true;
    }
    
    public String BuscarCliente(String ID){
        Cliente cliente;
        cliente = sql.BuscarCliente(ID); 
        if (cliente == null){
            JOptionPane.showMessageDialog(null, "No hay registros de: "+ID,"error",JOptionPane.OK_OPTION);
            return "";
        }else{
            return "Datos del cliente:\nNombre: "+cliente.getNombre()+"\nIdentificaión :"
                    +cliente.getID()+"\nTotal pagar: "+cliente.getSaldoTotal()+"\nTotal abonado: "
                    +cliente.getAbonado();
        }
        
    }
    public boolean Abonar(String ID, double Pago){
        if (sql.Abonar(ID, Pago)){
            JOptionPane.showMessageDialog(null, "Se hizo un abono de : "+Pago);
            return true;
            
        }else{
            JOptionPane.showMessageDialog(null, "No hay registros de: "+ID,"error",JOptionPane.OK_OPTION);
            return false;
        
        }
        
        
        
    }
    
    

  
}
