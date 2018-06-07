
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.Aerolinea;
import modelo.ExcursionPlus;
import modelo.Hotel;
import modelo.Modelo;
import modelo.Operador;
import modelo.Paquete;
import modelo.Cliente;
import vista.*;

public class Controlador implements ActionListener{
    
    ViewMain view_main = new ViewMain();
    ViewLogin view_login = new ViewLogin();
    ViewPaqueteDestino view_destino = new ViewPaqueteDestino(); 

    ViewCrearPaquete view_paquete = new ViewCrearPaquete(); 
    ViewExcursiones view_excursion = new ViewExcursiones(); 

    ViewCliente view_cliente = new ViewCliente();
    ViewReserva view_reserva = new ViewReserva();
    
    ViewExcDecorator view_decorator = new ViewExcDecorator();
    
    Modelo modelo = new Modelo();
    JFrame view;
    String nom_paquete; 
    String id_operador;
    String nom_hotel; 
    int id_paquete; 
    
    public Controlador(){
        this.modelo.CargarDatos();
        this.view_login.setVisible(true);
        this.view_login.B_Login.addActionListener(this);
        this.view_login.B_Login.setActionCommand("B_Login");
        this.view_login.B_Registro.addActionListener(this);
        this.view_login.B_Registro.setActionCommand("B_Registro");
        
        // Botones vista Main
        this.view_main.B_crearpaquete.addActionListener(this);
        this.view_main.B_crearpaquete.setActionCommand("B_Crear");
        this.view_main.B_listadoclientes.addActionListener(this);
        this.view_main.B_listadoclientes.setActionCommand("listado_clientes");
        this.view_main.B_reservarpaquete.addActionListener(this);
        this.view_main.B_reservarpaquete.setActionCommand("reservar paquete");
        
        // Botones vista PaqueteDestino
        this.view_destino.B_agregardestino.addActionListener(this);
        this.view_destino.B_agregardestino.setActionCommand("Agregar_paquete_des");
        this.view_destino.B_cancelardestino.addActionListener(this);
        this.view_destino.B_cancelardestino.setActionCommand("salir_destino");
        // Botones vista CrearPaquete
        this.view_paquete.B_CrearPaqueteExcursionesAgregar.addActionListener(this);
        this.view_paquete.B_CrearPaqueteExcursionesAgregar.setActionCommand("Agregar_excursion");
        this.view_paquete.B_CrearPaqueteExcursionesOk.addActionListener(this);
        this.view_paquete.B_CrearPaqueteExcursionesOk.setActionCommand("Add_paquete_excursion");
        //this.view_paquete.CB_listapaquetes.addActionListener(this);
        //this.view_paquete.CB_listapaquetes.setActionCommand("lista_paquetes");
        this.view_paquete.B_ExcursionDecorator.addActionListener(this);
        this.view_paquete.B_ExcursionDecorator.setActionCommand("Exc_Decorator"); 
        this.view_paquete.B_salirpaqueteexcursion.addActionListener(this);
        this.view_paquete.B_salirpaqueteexcursion.setActionCommand("salir_paquete_exc");
        // Vista Excursiones
        this.view_excursion.B_RegistrarExcursion.addActionListener(this);
        this.view_excursion.B_RegistrarExcursion.setActionCommand("Registrar_excursion");
        //this.view_excursion.CB_VistaExcursionesOperador.addActionListener(this);
        //this.view_excursion.CB_VistaExcursionesOperador.setActionCommand("lista_operadores");
        //this.view_excursion.CB_VistaExcursionesHotel.addActionListener(this);
        //this.view_excursion.CB_VistaExcursionesHotel.setActionCommand("lista_hoteles");
        this.view_excursion.B_CancelarExcursion.addActionListener(this);
        this.view_excursion.B_CancelarExcursion.setActionCommand("cancelar_excursion");
        //Botones vista clientes
        this.view_cliente.B_buscar.addActionListener(this);
        this.view_cliente.B_buscar.setActionCommand("B_buscar");
        this.view_cliente.B_abonar.addActionListener(this);
        this.view_cliente.B_abonar.setActionCommand("B_abonar");
        this.view_cliente.B_cancelar.addActionListener(this);
        this.view_cliente.B_cancelar.setActionCommand("salir_vista_clientes");
        
        // vista Reserva
        this.view_reserva.CB_ReservasPaquetes.addActionListener(this);
        this.view_reserva.B_cancelar.addActionListener(this);
        this.view_reserva.B_cancelar.setActionCommand("salir_reserva");
        this.view_reserva.B_hacerreserva.addActionListener(this);
        this.view_reserva.B_hacerreserva.setActionCommand("hacer_reserva");
        this.view_reserva.B_actualizar.addActionListener(this);
        this.view_reserva.B_actualizar.setActionCommand("actualizar");
        this.view_reserva.B_buscarclientereserva.addActionListener(this);
        this.view_reserva.B_buscarclientereserva.setActionCommand("BuscarClienteReserva");
        /*this.view_reserva.CB_ReservasPaquetes.addActionListener(this);
        this.view_reserva.CB_ReservasPaquetes.setActionCommand("combobox_reserva");
        this.view_reserva.CB_reservavuelo.addActionListener(this);
        this.view_reserva.CB_reservavuelo.setActionCommand("combobox_vuelo");*/
        
        
        // vista Excursion_Decorator
        this.view_decorator.B_nevado.addActionListener(this);
        this.view_decorator.B_nevado.setActionCommand("Nevado");
        this.view_decorator.B_otun.addActionListener(this);
        this.view_decorator.B_otun.setActionCommand("Otun");
        this.view_decorator.B_florida.addActionListener(this);
        this.view_decorator.B_florida.setActionCommand("Florida");
        this.view_decorator.B_salirdecorator.addActionListener(this);
        this.view_decorator.B_salirdecorator.setActionCommand("salir_decorator");
    }
    
    public void iniciar(){
        view_login.setTitle("Login");
        view_login.setLocationRelativeTo(null);
        view_main.setTitle("Pereira Tours: main");
        view_main.setLocationRelativeTo(null);
        view_cliente.setTitle("Clientes");
        view_cliente.setLocationRelativeTo(null);
    }
    
    public void Login(String Codigo, String Clave){
        boolean flag;
        flag = modelo.IniciarSesion(Codigo, Clave);
        
        if (flag){
            System.out.println("Se inicio sesion");
            view_main.setVisible(true);
            view_login.dispose();
        }else{
            System.out.println("No se inicio sesion");
        }
        
    }
    
    public void Registro(String Codigo, String Nombre, String Clave,boolean Tipo){
        boolean flag;
        int Tipoint;
        if(Tipo){
            Tipoint = 0;
        }else{
            Tipoint = 1;
        }
        flag = modelo.Registro(Codigo, Nombre, Clave, Tipoint);
        
        if (flag){
            System.out.println("Se registró");
        }else{
            System.out.println("No se registró");
        }
        this.view_login.T_regclave.setText("");
        this.view_login.T_regnombre.setText("");
        this.view_login.T_regcodigo.setText("");
        
    }
    
    public void crearPaquete(String nombre_paquete, String destino, double Preciobase){
         
        if(modelo.CrearPaquete(nombre_paquete, destino, Preciobase)){
            this.cargarCBpaquetes();
            this.cargarCBoperadores();
            this.cargarCBHoteles();

            view_paquete.setVisible(true);
            view_destino.setVisible(false); 
            view_destino.T_nombrepaquete.setText("");
            view_destino.T_destino.setText("");
            view_destino.T_preciopaquete.setText(""); 
        }
        else{
            view_destino.T_nombrepaquete.setText("");
            view_destino.T_destino.setText("");
        }
    }
    

    public void BuscarCliente(String ID){
        String description = modelo.BuscarCliente(ID);
        if (!"".equals(description)){
            this.view_cliente.TA_cliente.setText(description);
        }else{
            this.view_cliente.TA_cliente.setText("");
        }
          
    }
    
    public void BuscarClienteAbonar(String ID, double Pago){
        this.BuscarCliente(ID);
        if (modelo.Abonar(ID, Pago)){
            this.BuscarCliente(ID);
        }else{
            this.view_cliente.T_buscarclientes.setText("");
            this.view_cliente.T_pago.setText("");
        }
        
          
    }
    
    public void VistaReservarPaquete(){
        this.view_main.dispose();
        for(int i = 0; i < modelo.Paquetes.size(); i++){
            Paquete paquete = (Paquete)modelo.Paquetes.get(i);
            this.view_reserva.CB_ReservasPaquetes.addItem(i+" "+paquete.getNombre());
        }
        for(int i = 0; i < modelo.Aerolineas.size(); i++){
            Aerolinea aerolinea = (Aerolinea)modelo.Aerolineas.get(i);
            this.view_reserva.CB_reservavuelo.addItem(i+" "+aerolinea.getNombre());
        }
        this.view_reserva.setVisible(true);
    }
    

    // metodo para cargar comboBox de vista crear paquetes 
    public void VistaCrearPaquete(){
        this.view_main.dispose();
        this.cargarCBpaquetes();
        this.cargarCBoperadores();
        this.cargarCBHoteles(); 
    }
    
    public void cargarCBpaquetes(){
        for(int i = 0; i < modelo.Paquetes.size(); i++){
            Paquete paquete = (Paquete)modelo.Paquetes.get(i);
            this.view_paquete.CB_listapaquetes.addItem(paquete.getNombre());
        }
    }
    public void cargarCBoperadores(){
        // ciclo para cargar CB 
        for(int i = 0; i < modelo.Operadores.size(); i++){
            Operador operador = (Operador)modelo.Operadores.get(i);
            this.view_excursion.CB_VistaExcursionesOperador.addItem(operador.getId_operador());
        }
    }
    public void cargarCBHoteles(){
        // caragar hoteles
        for(int i = 0; i < modelo.Operadores.size(); i++){
            Hotel hotel = (Hotel)modelo.Hoteles.get(i);
            this.view_excursion.CB_VistaExcursionesHotel.addItem(hotel.getNombre());
        }
        this.view_paquete.setVisible(true);
    }
    public void ReservarPaquete(String IDCliente,String Nombre ,double Cuota){
        
        
                String sindex = (String)this.view_reserva.CB_ReservasPaquetes.getSelectedItem();
                String cindex = sindex.substring(0,1);
                int indexpaquete = Integer.parseInt(cindex);
                sindex = (String)this.view_reserva.CB_reservavuelo.getSelectedItem();
                cindex = sindex.substring(0,1);
                int indexvuelo = Integer.parseInt(cindex);
                
                Paquete paquete = (Paquete)modelo.Paquetes.get(indexpaquete);
                Aerolinea aero = (Aerolinea)modelo.Aerolineas.get(indexvuelo);
                
                String clase = this.view_reserva.List_clase.getSelectedValue();
                double claseprecio = aero.getPreciobase();
                int dias = 0;
                int dia = 0;
                int mes = 0;
                int diafin = 0;
                int mesfin = 0;
              
                if ("Media".equals(clase)){
                    claseprecio += 100000;
                }else if("Alta".equals(clase)){
                    claseprecio += 200000;
                }
                double preciototal = paquete.getPreciobase()+claseprecio;
                for (int i=0; i<paquete.Excursiones.size(); i++){
                    ExcursionPlus ex = (ExcursionPlus)paquete.Excursiones.get(i);
                    preciototal += ex.getDias()*ex.getPreciobase();
                    dias += ex.getDias();
                }
                if ("dia".equals(this.view_reserva.T_fechainicio.getText()) || "mes".equals(this.view_reserva.T_fechainicio1.getText()) ){
                    JOptionPane.showMessageDialog(null, "Ingrese un día y un mes válido","error",JOptionPane.OK_OPTION);
                }else{
                    dia = Integer.parseInt(this.view_reserva.T_fechainicio.getText());
                    mes = Integer.parseInt(this.view_reserva.T_fechainicio1.getText());
                    int meses = 0;
                    int diascont = dias + dia;
                    while(diascont > 31){
                        diascont -= 31;
                        meses += 1;
                    }
                    diafin = diascont;
                    mesfin = mes+meses;
                    while(mesfin > 12){
                        mesfin -= 12;
                    }
                    int[] Fechainicio = {dia,mes};
                    int[] Fechafinal = {diafin,mesfin};
                    if(Cuota>=preciototal){
                        modelo.RealizarReserva(Fechainicio, Fechafinal, id_paquete, indexvuelo, IDCliente,Nombre,Cuota, true, id_operador, preciototal);
                    }else if(Cuota >=(preciototal*0.5)){
                        modelo.RealizarReserva(Fechainicio, Fechafinal, id_paquete, indexvuelo, IDCliente,Nombre,Cuota, false, id_operador, preciototal);
                    }else{
                    JOptionPane.showMessageDialog(null, "Debe pagar la totalidad de la cuota inicial","error",JOptionPane.OK_OPTION);
                    }
                }

    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        
        switch (command){
            case "B_Login":
                String clave = new String(view_login.T_loginclave.getPassword());
                this.Login(view_login.T_logincodigo.getText(),clave);
                break;
                
            case "B_Registro":
                String clavereg = new String(view_login.T_regclave.getPassword());
                this.Registro(view_login.T_regcodigo.getText(), view_login.T_regnombre.getText(), clavereg,view_login.Cb_reg.isSelected());
                break; 
            case "B_Crear":
                if(JOptionPane.showConfirmDialog(null,"¿Desea agregar un nuevo paquete?", "Crear paquete", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == 0){
                    view_destino.setVisible(true);                
                }
                else{
                    // si no desea 1 nuevo paquete, entonces se trabaja con los paquetes existentes 
                    this.VistaCrearPaquete(); 
                    //this.crearPaquete(nom_paquete, command);
                    //view_paquete.setVisible(true);
                    //System.out.println("wer");
                    //modelo.cargarCombo(view_paquete);
                }
                //view_main.setVisible(false);
                break; 
            case "Agregar_paquete_des":
                Double precio = Double.parseDouble(view_destino.T_preciopaquete.getText()); 
                this.crearPaquete(view_destino.T_nombrepaquete.getText(),view_destino.T_destino.getText(),precio);
                break; 
            case "salir_destino":
                view_destino.dispose();
                view_main.setVisible(true); 
                break; 

            case "Agregar_excursion":
                view_excursion.setVisible(true);
                //modelo.cargarComboOperadores(view_excursion);
                //modelo.cargarComboHoteles(view_excursion);
                break; 
                
            case "Registrar_excursion":
                String lugar =view_excursion.T_VistaExcursionesLugar.getText();
                int dias = Integer.parseInt(view_excursion.T_VistaExcursionesDias.getText()); 

                //id_operador = view_excursion.CB_VistaExcursionesOperador.getSelectedItem().toString();
                //nom_hotel = view_excursion.CB_VistaExcursionesHotel.getSelectedItem().toString();
                
                double precio_exc = Double.parseDouble(view_excursion.T_precioexcursion.getText());
                id_operador = view_excursion.CB_VistaExcursionesOperador.getSelectedItem().toString();
                nom_hotel = view_excursion.CB_VistaExcursionesHotel.getSelectedItem().toString();

                if(modelo.CrearExcursion(lugar, id_operador, nom_hotel, dias,precio_exc)){
                    view_paquete.setVisible(true);
                    view_excursion.setVisible(false);
                    view_excursion.T_VistaExcursionesLugar.setText("");
                    view_excursion.T_VistaExcursionesDias.setText("");
                    view_excursion.T_precioexcursion.setText("");
                }
                break; 
                
            case "Add_paquete_excursion":
            
                nom_paquete = view_paquete.CB_listapaquetes.getSelectedItem().toString();
                if(modelo.CrearPaqueteExcursion(nom_paquete)){
                    view_excursion.T_VistaExcursionesLugar.setText("");
                    view_excursion.T_VistaExcursionesDias.setText("");
                    //view_paquete.setVisible(false); 
                    //view_main.setVisible(true);
                }
                break;
                
     
            case "listado_clientes":
                this.view_cliente.setVisible(true);
                this.view_main.dispose(); 
                break;
                
            case "B_buscar":
                this.BuscarCliente(view_cliente.T_buscarclientes.getText());
                break;
            
            case "B_abonar":
                this.BuscarClienteAbonar(view_cliente.T_buscarclientes.getText(),Double.parseDouble(view_cliente.T_pago.getText()));
                break;
                
                
                
            case "salir_vista_clientes":
                this.view_cliente.dispose();
                this.view_main.setVisible(true);
                break;
            case "Exc_Decorator":
                view_paquete.setVisible(false); 
                view_decorator.setVisible(true); 
                
                // Se le envia el nombre del paquete que haya seleccionado en el comboBox
                modelo.setIdPaquete(view_paquete.CB_listapaquetes.getSelectedItem().toString()); 
                view_decorator.T_namepaquete.setText(view_paquete.CB_listapaquetes.getSelectedItem().toString()); 
                break; 
            case "Nevado":
                modelo.setOpcion(1);
                view_decorator.T_excursionesadd.setText(modelo.Opcion());               
                break;
            case "Otun":
                modelo.setOpcion(2);
                view_decorator.T_excursionesadd.setText(modelo.Opcion());           
                break;
            case "Florida":
                modelo.setOpcion(3);
                view_decorator.T_excursionesadd.setText(modelo.Opcion());           
                break;
                
                
            case "reservar paquete":
                this.VistaReservarPaquete();
                break;
                
             
                
            case "actualizar":
                
                 String sindex = (String)this.view_reserva.CB_ReservasPaquetes.getSelectedItem();
                String cindex = sindex.substring(0,1);
                int indexpaquete = Integer.parseInt(cindex);
                sindex = (String)this.view_reserva.CB_reservavuelo.getSelectedItem();
                cindex = sindex.substring(0,1);
                int indexvuelo = Integer.parseInt(cindex);
                
                Paquete paquete = (Paquete)modelo.Paquetes.get(indexpaquete);
                Aerolinea aero = (Aerolinea)modelo.Aerolineas.get(indexvuelo);
                
                String clase = this.view_reserva.List_clase.getSelectedValue();
                double claseprecio = aero.getPreciobase();
                int diass = 0;
                int dia = 0;
                int mes = 0;
                int diafin = 0;
                int mesfin = 0;
              
                if ("Media".equals(clase)){
                    claseprecio += 100000;
                }else if("Alta".equals(clase)){
                    claseprecio += 200000;
                }
                double preciototal = paquete.getPreciobase()+claseprecio;
                 this.view_reserva.TA_reservasdescripcion.setText("Paquete : "+paquete.getNombre()
                        +"\nDestino : "+paquete.getDestino()+"\nPrecio base : "+paquete.getPreciobase()
                        +"\nExcursiones\n__________________ \n");
                for (int i=0; i<paquete.Excursiones.size(); i++){
                    ExcursionPlus ex = (ExcursionPlus)paquete.Excursiones.get(i);
                    this.view_reserva.TA_reservasdescripcion.append("Excursion: "+ex.getLugar()+" x "+ex.getDias()+" Dias = "+(ex.getDias()*ex.getPreciobase())+"\n__________________\n");
                    preciototal += ex.getDias()*ex.getPreciobase();
                    diass += ex.getDias();
                }
                if ("dia".equals(this.view_reserva.T_fechainicio.getText()) || "mes".equals(this.view_reserva.T_fechainicio1.getText()) ){
                    JOptionPane.showMessageDialog(null, "Ingrese un día y un mes válido","error",JOptionPane.OK_OPTION);
                }else{
                    dia = Integer.parseInt(this.view_reserva.T_fechainicio.getText());
                    mes = Integer.parseInt(this.view_reserva.T_fechainicio1.getText());
                    int meses = 0;
                    int diascont = diass + dia;
                    while(diascont > 31){
                        diascont -= 31;
                        meses += 1;
                    }
                    diafin = diascont;
                    mesfin = mes+meses;
                    while(mesfin > 12){
                        mesfin -= 12;
                    }
                    
                    this.view_reserva.L_fechainicio.setText(diafin+"/"+mesfin);
  
                }
                this.view_reserva.TA_reservasdescripcion.append("Vuelo:\nAerolinea : "+aero.getNombre()+"\nClase : "+clase+"\nPrecio : "+claseprecio);
                this.view_reserva.L_precio.setText(preciototal+" $");

                
                break;
                
            case "BuscarClienteReserva":
                Cliente cliente = modelo.BuscaryCrearCliente(this.view_reserva.T_dni.getText(),this.view_reserva.T_nombre.getText());
                sindex = (String)this.view_reserva.CB_ReservasPaquetes.getSelectedItem();
                cindex = sindex.substring(0,1);
                indexpaquete = Integer.parseInt(cindex);
                sindex = (String)this.view_reserva.CB_reservavuelo.getSelectedItem();
                cindex = sindex.substring(0,1);
                indexvuelo = Integer.parseInt(cindex);
                
                paquete = (Paquete)modelo.Paquetes.get(indexpaquete);
                aero = (Aerolinea)modelo.Aerolineas.get(indexvuelo);
                
                clase = this.view_reserva.List_clase.getSelectedValue();
                claseprecio = aero.getPreciobase();
                
              
                if ("Media".equals(clase)){
                    claseprecio += 100000;
                }else if("Alta".equals(clase)){
                    claseprecio += 200000;
                }
                preciototal = paquete.getPreciobase()+claseprecio;
                for (int i=0; i<paquete.Excursiones.size(); i++){
                    ExcursionPlus ex = (ExcursionPlus)paquete.Excursiones.get(i);
                    preciototal += ex.getDias()*ex.getPreciobase();
                }
                
                switch(cliente.getRiesgo()){
                    case 0:
                        this.view_reserva.L_cuota.setText((preciototal*0.1)+"");
                        this.view_reserva.L_saldo.setText((preciototal-(preciototal*0.1))+" $");
                        break;
                    case 1:
                        this.view_reserva.L_cuota.setText((preciototal-(preciototal*0.5))+" $");
                        this.view_reserva.L_saldo.setText((preciototal-(preciototal*0.5))+" $");
                        break;
                    case 2:
                        this.view_reserva.L_cuota.setText(preciototal+"");
                        this.view_reserva.L_saldo.setText("0 $");
                        break;
                }
                
                
                break;
                
            case "salir_reserva":
                this.view_reserva.CB_ReservasPaquetes.removeAllItems();
                this.view_reserva.CB_reservavuelo.removeAllItems();
                this.view_reserva.dispose();
                this.view_main.setVisible(true);
                break;

            case "hacer_reserva":
                this.ReservarPaquete(this.view_reserva.T_dni.getText(),this.view_reserva.T_nombre.getText(),Double.parseDouble(this.view_reserva.T_pago.getText()));
                break;
            case "salir_paquete_exc":
                view_paquete.setVisible(false);
                view_paquete.CB_listapaquetes.removeAllItems();
                view_excursion.CB_VistaExcursionesOperador.removeAllItems();
                view_excursion.CB_VistaExcursionesHotel.removeAllItems();
                view_main.setVisible(true); 
                break; 
            case "salir_excursion":
                view_excursion.setVisible(false);
                view_paquete.setVisible(true);
                break;
            case "salir_decorator":
                view_decorator.setVisible(false);
                view_paquete.setVisible(true); 
                break;
            case "cancelar_excursion":
                view_excursion.setVisible(false); 
                view_paquete.setVisible(true);
                break; 
            default:
                System.out.println(command);
        }
        
        
    }
    
    
}

   
