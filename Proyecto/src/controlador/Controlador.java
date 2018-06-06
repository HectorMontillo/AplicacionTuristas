
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
    ViewCliente view_cliente = new ViewCliente();
    Modelo modelo = new Modelo();
    JFrame view;
    
    public Controlador(){
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
        
        // Botones vista PaqueteDestino
        this.view_destino.B_agregardestino.addActionListener(this);
        this.view_destino.B_agregardestino.setActionCommand("Agregar_paquete_des");
        this.view_destino.B_cancelardestino.addActionListener(this);
        this.view_destino.B_cancelardestino.setActionCommand("salir_destino");
        // Botones vista CrearPaquete
        
        
        //Botones vista clientes
        this.view_cliente.B_buscar.addActionListener(this);
        this.view_cliente.B_buscar.setActionCommand("B_buscar");
        this.view_cliente.B_abonar.addActionListener(this);
        this.view_cliente.B_abonar.setActionCommand("B_abonar");
        this.view_cliente.B_cancelar.addActionListener(this);
        this.view_cliente.B_cancelar.setActionCommand("salir_vista_clientes");
        
        
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
    
    public void crearPaquete(String nombre_paquete, String destino){
        
        if(modelo.CrearPaquete(nombre_paquete, destino)){
            System.out.println("abriendo ventana paquete");
        }
        else{
            System.out.println("no ventana");
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
                    // view_paquete.setVisible(true); 
                }
                break; 
            case "Agregar_paquete_des":
                this.crearPaquete(view_destino.T_nombrepaquete.getText(),view_destino.T_destino.getText());
                break; 
            case "salir_destino":
                view_destino.dispose();
                view_main.setVisible(true); 
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
            default:
                System.out.println("Error en acción");
        }
        
        
    }
    
    
}

   
