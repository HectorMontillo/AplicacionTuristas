
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import modelo.Modelo;
import vista.*;

public class Controlador implements ActionListener{
    
    ViewMain view_main = new ViewMain();
    ViewLogin view_login = new ViewLogin();
    Modelo modelo = new Modelo();
    JFrame view;
    
    public Controlador(){
        this.view_login.setVisible(true);
        this.view_login.B_Login.addActionListener(this);
        this.view_login.B_Login.setActionCommand("B_Login");
        this.view_login.B_Registro.addActionListener(this);
        this.view_login.B_Registro.setActionCommand("B_Registro");
        
        
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
                
            default:
                System.out.println("Error en acción");
        }
        
        
    }
    
    
}

   
