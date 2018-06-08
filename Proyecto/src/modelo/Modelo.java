package modelo;

import java.util.ArrayList;
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
    int op_exc; 
    int id_paquete; 
    I_Excursion excursion = new Excursion(); 
    
    public ArrayList Paquetes = new ArrayList();
    public ArrayList Excursiones = new ArrayList();
    public ArrayList Aerolineas = new ArrayList();
    public ArrayList Operadores = new ArrayList();
    public ArrayList Hoteles = new ArrayList();
    public ArrayList Reservas = new ArrayList();

    
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

    public boolean CrearPaquete(String nombre_paquete, String destino, double preciobase){
        if(sql.agregarPaquete(nombre_paquete, destino,preciobase)){

           JOptionPane.showMessageDialog(null, "Se agrego un nuevo paquete");  
           int id = Paquetes.size();
           Paquete paquete = new Paquete(id, nombre_paquete, destino, preciobase);
           Paquetes.add(paquete);
           return true;
        } 
        else{
            return false;
        }
    }
    


    public boolean CrearExcursion(String lugar, String id_operador, String hotel, int dias, double preciobase){

        
        boolean result = false;
        int id_hotel = sql.obtenerIdHotel(hotel);
        if(id_hotel != 0){ // si retorna cero es porq hay 1 error 


            if(sql.registrarExcursion(id_operador,id_hotel,lugar,dias, preciobase)){
                int id_ex = Excursiones.size();
                ExcursionPlus excur = new ExcursionPlus(id_ex+1,id_operador,id_hotel,lugar,dias,preciobase); 
                Excursiones.add(excur);
                JOptionPane.showMessageDialog(null, "Se registro la excursion"); 
                result= true; 
            }
            for(int i =0; i<Excursiones.size();i++){
                ExcursionPlus ex = (ExcursionPlus)Excursiones.get(i);
                System.out.println("nombre: "+ex.getID());
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "error con el id del hotel");
        }
        return result;
    }
    
    public boolean CrearPaqueteExcursion(String nom_paquete){
        boolean result = false; 
        int id_paquete = sql.obtenerIdPaquete(nom_paquete);
        int id_excursion = sql.obtenerIdExcursion(); // se hace en base a la  ultima excursion agregada 
        
        if(id_paquete != 0 && id_excursion != 0){
            if(sql.registrarPaqueteExcursion(id_paquete,id_excursion)){ // registrar paquete_excursion
                
               Paquete paquete; 
               ExcursionPlus ex; 
                
                paquete = (Paquete)Paquetes.get(id_paquete-1);
                ex = (ExcursionPlus)Excursiones.get(id_excursion-1);
                paquete.AgregarExcursion(ex); 
                
                //this.imprimir_paq_ex(Paquetes);
                JOptionPane.showMessageDialog(null, "Se registro paquete-excursion");
                result = true; 
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "error con id_paquete ó id_excursion");
        }
        return result; 
    }

    public void setIdPaquete(String nom_paquete){
        
        id_paquete = sql.obtenerIdPaquete(nom_paquete);
    }
    
    public Cliente BuscaryCrearCliente(String IDCliente, String Nombre){
        Cliente cliente = this.BuscarClientePlus(IDCliente);
        if(cliente == null){
            cliente = new Cliente(IDCliente,Nombre,0,0,1);
            sql.registrarCliente(cliente);
        }
        return cliente;
    }
    
    public void RealizarReserva(int[] Fechainicio, int[] Fechafinal,int IDPaquete,int IDVuelo, String IDCliente,String Nombre,double Cuota, boolean Pagado, String IDVendedor, double Precio){
        
        Cliente cliente = this.BuscarClientePlus(IDCliente);
        
        
        
        if(cliente.getSaldoTotal()<= 0){
            
            Aerolinea aero = (Aerolinea)Aerolineas.get(IDVuelo);
            Paquete paq = (Paquete)Paquetes.get(IDPaquete);
            String nombrevendedor = sql.ConsultaVendedor(IDVendedor);
            int id = Reservas.size();
            Reserva res = new Reserva(id+1,IDPaquete, IDVuelo,IDCliente,Pagado,IDVendedor, Precio);
            res.setFechainicio(Fechainicio);
            res.setFechafinal(Fechafinal);
            res.setDescription("Reserva------------------ \nCliente : "+cliente.getNombre()+
                    "\nAerolinea : "+aero.getNombre()+"\nPaquete : "+paq.getNombre()+"----->"+paq.getDestino()+
                    "\nVendedor : "+nombrevendedor+"\nFecha inicio : "+Fechainicio[0]+"/"+Fechainicio[1]+
                    "\nFechafinal : "+Fechafinal[0]+"/"+Fechafinal[1]+"\n");
            
            if(sql.registrarReserva(res)){
                JOptionPane.showMessageDialog(null, "Se hizo una reserva exitosamente");
                Reservas.add(res);
                sql.actualizarSaldoTotal(IDCliente,Precio);
                this.Abonar(IDCliente, Cuota);
            }else{
                JOptionPane.showMessageDialog(null, "Error con la base de datos","error",JOptionPane.OK_OPTION);
            }
            

        }else{
            JOptionPane.showMessageDialog(null, "Este cliente no puede hacer una reserva, antes debe pagar todo el saldo pendiente","error",JOptionPane.OK_OPTION);
            
        }
        
        
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
    public Cliente BuscarClientePlus(String ID){
        Cliente cliente;
        cliente = sql.BuscarCliente(ID); 
        if (cliente == null){
            JOptionPane.showMessageDialog(null, "No hay registros de: "+ID,"error",JOptionPane.OK_OPTION);
            return null;
        }else{
            return cliente;
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

    public void setOpcion(int op){
       op_exc= op; 
   }
   
    public String Opcion(){
        
        Paquete paquete; 
        ExcursionPlus ex;
        paquete = (Paquete)Paquetes.get(id_paquete-1);
        
        if(op_exc == 1){
            excursion = new Nevado(excursion);
            sql.registrarPaqueteExcursion(id_paquete, 5);
            
            ex = (ExcursionPlus)Excursiones.get(5-1);
            paquete.AgregarExcursion(ex); 
          
        }
        if(op_exc == 2){
            sql.registrarPaqueteExcursion(id_paquete, 2);
            excursion = new Otun(excursion); 
            
            ex = (ExcursionPlus)Excursiones.get(2-1);
            paquete.AgregarExcursion(ex);
        } 
        if(op_exc == 3){
            sql.registrarPaqueteExcursion(id_paquete, 4);
            excursion = new Florida(excursion); 
            
            ex = (ExcursionPlus)Excursiones.get(4-1);
            paquete.AgregarExcursion(ex);
        }
        return excursion.getExcursion();
        
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
    
    public void CargarDatos(){
        sql.CargarPaquetes(Paquetes);
        sql.CargarExcursiones(Excursiones);   
        sql.CargarAerolineas(Aerolineas);
        sql.RelacionarExcursionesPaquetes(Paquetes, Excursiones);
        sql.CargarOperadores(Operadores);
        sql.CargarHoteles(Hoteles);  
        sql.CargarReservas(Reservas);
    }
    
    public String imprimirDescrip(int id_reserva){
        
        Reserva reser = (Reserva)Reservas.get(id_reserva+1);
        return reser.getDescription();
    }
    
    public void imprimirReservas(){
        for(int i=0; i<Reservas.size();i++){
            Reserva reser = (Reserva)Reservas.get(i);
            System.out.println("descripcion: "+reser.getDescription());
        }
        
    }
    /*
    public void imprimir_paq_ex(ArrayList Paquetes){
       //Paquete paquet;
        for(int i= 0; i<Paquetes.size(); i++){
            System.out.println("n: "+Paquetes.get(i));
        }
    }
    
    public void imprimir_paq_ex2(ArrayList Excursiones){
       
        for(int i= 0; i<Excursiones.size(); i++){
            System.out.println("n: "+Excursiones.get(i));
        }
    } */
}
