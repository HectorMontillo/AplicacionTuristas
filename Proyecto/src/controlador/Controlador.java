
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.Modelo;
import vista.*;

public class Controlador implements ActionListener{
    
    ViewMain view_main = new ViewMain();
    ViewLogin view_login = new ViewLogin();
    ViewPaqueteDestino view_destino = new ViewPaqueteDestino(); 
    ViewCrearPaquete view_paquete = new ViewCrearPaquete(); 
    ViewExcursiones view_excursion = new ViewExcursiones(); 
    Modelo modelo = new Modelo();
    JFrame view;
    String nom_paquete; 
    String id_operador;
    String nom_hotel; 
    
    public Controlador(){
        this.view_login.setVisible(true);
        this.view_login.B_Login.addActionListener(this);
        this.view_login.B_Login.setActionCommand("B_Login");
        this.view_login.B_Registro.addActionListener(this);
        this.view_login.B_Registro.setActionCommand("B_Registro");
        
        // Botones vista Main
        this.view_main.B_crearpaquete.addActionListener(this);
        this.view_main.B_crearpaquete.setActionCommand("B_Crear");
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
        this.view_paquete.CB_listapaquetes.addActionListener(this);
        this.view_paquete.CB_listapaquetes.setActionCommand("lista_paquetes");
        // Vista Excursiones
        this.view_excursion.B_RegistrarExcursion.addActionListener(this);
        this.view_excursion.B_RegistrarExcursion.setActionCommand("Registrar_excursion");
        this.view_excursion.CB_VistaExcursionesOperador.addActionListener(this);
        this.view_excursion.CB_VistaExcursionesOperador.setActionCommand("lista_operadores");
        this.view_excursion.CB_VistaExcursionesHotel.addActionListener(this);
        this.view_excursion.CB_VistaExcursionesHotel.setActionCommand("lista_hoteles");
        
    }
    
    public void iniciar(){
        view_login.setTitle("Login");
        view_login.setLocationRelativeTo(null);
        view_main.setTitle("Pereira Tours: main");
        view_main.setLocationRelativeTo(null);
    }
    
    public void Login(String Codigo, String Clave){
        boolean flag;
        flag = modelo.IniciarSesion(Codigo, Clave);
        
        if (flag){
            System.out.println("Se inicio sesion");
            view_main.setVisible(true);
            view_login.setVisible(false);
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
    
    public void crearPaquete(String nombre_paquete, String destino){
        
        if(modelo.CrearPaquete(nombre_paquete, destino)){
            view_paquete.setVisible(true);
            modelo.cargarCombo(view_paquete);
        }
        else{
            view_destino.T_nombrepaquete.setText("");
            view_destino.T_destino.setText("");
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
                    view_paquete.setVisible(true); 
                    modelo.cargarCombo(view_paquete);
                }
                break; 
            case "Agregar_paquete_des":
                this.crearPaquete(view_destino.T_nombrepaquete.getText(),view_destino.T_destino.getText());
                break; 
            case "salir_destino":
                view_destino.dispose();
                view_main.setVisible(true); 
                break; 
            case "Agregar_excursion":
                view_excursion.setVisible(true);
                modelo.cargarComboOperadores(view_excursion);
                modelo.cargarComboHoteles(view_excursion);
                break; 
                
            case "Registrar_excursion":
                String lugar =view_excursion.T_VistaExcursionesLugar.getText();
                int dias = Integer.parseInt(view_excursion.T_VistaExcursionesDias.getText()); 
                //id_operador = view_excursion.CB_VistaExcursionesOperador.getSelectedItem().toString();
                //nom_hotel = view_excursion.CB_VistaExcursionesHotel.getSelectedItem().toString();
                System.out.println("idopera: "+id_operador);
                System.out.println("nomhotel: "+nom_hotel);
                if(modelo.CrearExcursion(lugar, id_operador, nom_hotel, dias)){
                    view_paquete.setVisible(true);
                    view_excursion.dispose();
                }
                break; 
                
            case "Add_paquete_excursion":
                //String nom_paquete = view_paquete.CB_listapaquetes.getSelectedItem().toString();
                modelo.CrearPaqueteExcursion(nom_paquete);
                break;
            case "lista_paquetes":
                nom_paquete = view_paquete.CB_listapaquetes.getSelectedItem().toString();
                break; 
            case "lista_operadores":
                id_operador = view_excursion.CB_VistaExcursionesOperador.getSelectedItem().toString();
                break;
            case "lista_hoteles":
                nom_hotel = view_excursion.CB_VistaExcursionesHotel.getSelectedItem().toString(); 
                break;
                
            default:
                System.out.println("Error en acción");
        }
        
        
    }
    
    
}

   
