/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

//import modelo.Conexion;
//import Atxy2k.CustomTextField.RestrictedTextField;
/*import modelo.SqlUsuario;
import vista.ViewLogin;
import vista.ViewMenuAdmin; 
//import java.sql.Connection;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Producto;
import modelo.Usuario;
import vista.ViewModificar;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import javax.swing.JOptionPane;
import vista.ViewRegistro;
import vista.ViewVenta;


*/
/**
 *
 * @author Gustavo Davila
 */
public class Controlador implements ActionListener{
    
    //Conexion conexion;
    ViewLogin view_login;
    ViewRegistro view_reg;
    ViewMenuAdmin view_menu_admin; 
    ViewVenta view_venta; 
    ViewModificar view_modificar; 
    String codigo; 
    String password; 
    String id_ultLog; 
    String nom_prodadd=""; 
    int id_prod;
    int contador = 1;
    int max_fact;
    //String datocapturado =""; 
    
    public Controlador(ViewLogin vist, ViewRegistro reg, ViewMenuAdmin vistadmin,
                        ViewVenta ven, ViewModificar mod){
        
        //conexion = cone;
        view_login = vist;
        view_reg = reg; 
        view_menu_admin = vistadmin; 
        view_venta = ven;
        view_modificar = mod; 
        
         
        // vista login 
        this.view_login.entrar_boton.addActionListener(this);
        this.view_login.entrar_boton.setActionCommand("entrar"); 
        
        this.view_login.admin_boton.addActionListener(this);
        this.view_login.admin_boton.setActionCommand("admin"); // boton entrar a registrar 
        
         // vista menu administrador   
        this.view_menu_admin.ingresar_user.addActionListener(this);
        this.view_menu_admin.ingresar_user.setActionCommand("ingresar_user");
        this.view_menu_admin.modificar_prod_Boton.addActionListener(this);
        this.view_menu_admin.modificar_prod_Boton.setActionCommand("modificar_prod"); // modificar producto
        this.view_menu_admin.estadisticas_boton.addActionListener(this);
        this.view_menu_admin.estadisticas_boton.setActionCommand("estadisticas");
        this.view_menu_admin.salir_menu_admin_boton.addActionListener(this);
        this.view_menu_admin.salir_menu_admin_boton.setActionCommand("salir_admin");
        
        // Vista Registro
        this.view_reg.registrar_emp_boton.addActionListener(this);
        this.view_reg.registrar_emp_boton.setActionCommand("registrar_emp");     
        this.view_reg.cancelar_reg_boton.addActionListener(this);
        this.view_reg.cancelar_reg_boton.setActionCommand("cancelar_reg"); // cancelar registro
        
        // vista venta
        this.view_venta.buscarBoton.addActionListener(this);
        this.view_venta.buscarBoton.setActionCommand("buscar");
        this.view_venta.vender_boton.addActionListener(this);
        this.view_venta.vender_boton.setActionCommand("vender"); // vender producto
        this.view_venta.facturar_boton.addActionListener(this);
        this.view_venta.facturar_boton.setActionCommand("facturar");
        this.view_venta.salir_venta_boton.addActionListener(this);
        this.view_venta.salir_venta_boton.setActionCommand("salir_venta");
        
        // vista modificar
        this.view_modificar.buscar_boton.addActionListener(this);
        this.view_modificar.buscar_boton.setActionCommand("buscar_prod"); // boton buscar producto (por id) 
        this.view_modificar.modificar_boton.addActionListener(this);
        this.view_modificar.modificar_boton.setActionCommand("update");
        this.view_modificar.eliminar_boton.addActionListener(this);
        this.view_modificar.eliminar_boton.setActionCommand("eliminar");
        this.view_modificar.agregarProd_boton.addActionListener(this);
        this.view_modificar.agregarProd_boton.setActionCommand("agregar_prod");
        this.view_modificar.limpiar_boton.addActionListener(this);
        this.view_modificar.limpiar_boton.setActionCommand("limpiar");
        this.view_modificar.salir_mod_boton.addActionListener(this);
        this.view_modificar.salir_mod_boton.setActionCommand("salir_mod");
    }
    
    public void iniciar(){
        
        view_login.setTitle("Repuestos y accesorios Buena Vibra Club");
        view_login.setLocationRelativeTo(null);

    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        String op = e.getActionCommand();
        String nombreprod; // nombre producto 
        //Connection con = conexion.getConexion(); 
        SqlUsuario sqluser = new SqlUsuario(); 
        codigo = view_login.caja_codigo.getText();
        password = new String(view_login.caja_contraseña.getPassword()); 
        switch(op){ 
                 
            case "entrar":                
                int result = sqluser.entrarLogin(codigo,password);
                if(result == 1){
                    view_venta.setVisible(true); 
                    view_login.dispose();
                    // es el nuevo numero de factura que se va a utilizar 
                    max_fact = sqluser.capturarFactura(); // se captura el ult n° factura registrado
                    id_ultLog = sqluser.getUltimoLogeado();  // se toma el id del ultimo empleado logeado
                    }                   
                break; 
                
            case "admin":
                result = sqluser.entrarAdmin(codigo, password);
                if(result == 1){
                    //ViewRegistro registro = new ViewRegistro();
                    view_menu_admin.setVisible(true);
                    view_login.dispose();
                }
                break;
                
            case "ingresar_user":
                view_reg.setVisible(true);
                view_menu_admin.dispose();
                break;
                
            case "registrar_emp":
                Usuario newuser = new Usuario();
        
                String pass = new String(view_reg.caja_password.getPassword());
                String password_confir = new String(view_reg.caja_confirmar_password.getPassword());
                if(pass.equals(password_confir)){ 
                    // Hacer cifrado de contraseña
                  
                    newuser.setId_emp(view_reg.caja_codigo.getText());
                    newuser.setPassword(pass);
                    newuser.setNombre(view_reg.caja_nombre.getText());
                    newuser.setApellido(view_reg.caja_apellido.getText());
                    newuser.setTelefono(view_reg.caja_telefono.getText());
                    newuser.setCorreo(view_reg.caja_correo.getText());
                    
                    if(!view_reg.caja_tipo_emp.getText().equals("")){
                        newuser.setTipo_emp(Integer.parseInt(view_reg.caja_tipo_emp.getText()));
                    }
                        // añadir que no haya otro usuario con el mismos id 
                    if(!view_reg.caja_codigo.getText().equals("") && !pass.equals("") &&
                        !view_reg.caja_nombre.getText().equals("") &&
                        !view_reg.caja_apellido.getText().equals("") &&
                        !view_reg.caja_telefono.getText().equals("") &&
                        !view_reg.caja_tipo_emp.getText().equals("") ){

                        if(sqluser.registrar(newuser)){
                            JOptionPane.showMessageDialog(null,"El registro se ha guardado");
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Error al guardar");
                        }
                    }    
                    else{
                        JOptionPane.showMessageDialog(null,"Los campos con * son obligatorios");  
                    }
                
            }else{
                JOptionPane.showMessageDialog(null,"Las contraseñas no coinciden"); 
            } 
                    
                break; 
                  
            case "buscar":
                nombreprod= view_venta.caja_buscar.getText();
                String resultado = sqluser.Buscarproducto(nombreprod);
                if(!resultado.equals("")){
                    view_venta.caja_areatexto.setText(resultado);
                }                
                break; 
            case "vender":
                 
                if(!view_venta.caja_id.getText().equals("") && !view_venta.caja_cantidad.getText().equals("")){
                    
                    // Para evitar que no se ingrese 2 vecses el mismo producto 
                    // se hace una consulta en donde se mire que ya no este el id en la tabla ventas - de la ultima factura.
                    int id_prod = Integer.parseInt(view_venta.caja_id.getText());
                    int cant = Integer.parseInt(view_venta.caja_cantidad.getText());
                    
                    //si el id del producto si esta en la basa de dato
                    if(sqluser.Buscarproducto(id_prod) == 1){ 
                        
                        if( (sqluser.cantExistencias(id_prod) - cant) >= 0){
                            sqluser.registrarVenta(id_prod,id_ultLog,cant, max_fact+1);
                            nom_prodadd = nom_prodadd + sqluser.soloNombre(id_prod)+"\n"; // nombre del producto añadido 
                            view_venta.caja_areaVentas.setText(nom_prodadd);
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"No hay productos","No-existencias",JOptionPane.OK_OPTION); 
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"El id del producto no existe","No-existe",JOptionPane.OK_OPTION);
                    }
   
                }
                else{
                    JOptionPane.showMessageDialog(null,"El campo idProd y cantidad son obligatorios");
                }
                
                view_venta.caja_id.setText("");
                view_venta.caja_cantidad.setText("");
                break; 
                
            case "facturar":
                String infoProd = sqluser.productoFactura(max_fact+1);
                String infoEmp = sqluser.empleadoFactura(id_ultLog);
                String infoAlmacen =  "Almacen: Buena vibtra\nNIT: 900.480.569-7\nDirección: Cr 12 N° 13-22"+
                                      " Miraflores-Pereira\nTelefono: 3116336812\n\n";
                String valor_pagar = sqluser.valorComprado(max_fact+1);
                if(!infoProd.equals("") && !infoEmp.equals("")){
                    JOptionPane.showMessageDialog(null,infoAlmacen+infoProd+valor_pagar+infoEmp,"Factura",JOptionPane.INFORMATION_MESSAGE);
                    max_fact = max_fact+1; 
                }
                else{
                  JOptionPane.showMessageDialog(null,"Error al recuperar info de productos y empleados","Error",JOptionPane.OK_OPTION);  
                }
                nom_prodadd = "";
                view_venta.caja_buscar.setText("");
                view_venta.caja_areatexto.setText("");
                view_venta.caja_areaVentas.setText(""); 
                break;
            
            case "buscar_prod":
                if(!view_modificar.caja_idprod.getText().equals("")){
                    id_prod = Integer.parseInt(view_modificar.caja_idprod.getText());
                    sqluser.Buscarproducto(id_prod,view_modificar); 
                }
                break; 
            case "agregar_prod":
                Producto newprod = new Producto();
                if(!view_modificar.caja_idprod.getText().equals("")){
                    
                    int aux_id = Integer.parseInt(view_modificar.caja_idprod.getText()); 
                    // primero verifica si el id_prod ya existe dentro de productos borrados
                    // si es asi, se hace la copia de tabla productos_borrados a tabla productos 
                    if(sqluser.buscarProductoBorrado(aux_id) == 1){
                        
                        if(JOptionPane.showConfirmDialog(null, "Ya hay un producto con ese id en productos borrados, desea volver a integrarlo a inventario?", "Agregar", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == 0){
                        
                            if(sqluser.copiarAProductos(aux_id)){ // se copia el ese producto en tabla productos
                                JOptionPane.showMessageDialog(null,"El producto se ha restablecido en inventario");
                               
                                if(sqluser.updateIdProd(aux_id)){
                                   JOptionPane.showMessageDialog(null,"Se actualizo id_prod"); 
                                }
                                // se hace actualiza el campo id_prod_delete, pasa a null
                                if(sqluser.updateVentaRetro(aux_id)){
                                   JOptionPane.showMessageDialog(null,"Se actualizo id_prod_delete"); 
                                }
                                
                                // se elimina de tabla productos_borrados
                                if(sqluser.eliminarProdBorrado(aux_id)){
                                    JOptionPane.showMessageDialog(null,"Se borro de tabla productos_borrados");
                                }
                            }
                        }
                    }
                    else{
                        // Se hace la verificación que todos los campos tengan valor 
                        if(!view_modificar.caja_nombre.getText().equals("") &&
                        !view_modificar.caja_precio.getText().equals("") &&
                        !view_modificar.caja_ubicacion.getText().equals("") &&
                        !view_modificar.caja_existencias.getText().equals("")&&
                        !view_modificar.caja_valcompra.getText().equals("")&&   
                        !view_modificar.caja_areadescrip.getText().equals("")){
                            
                            //se ingrese los atributos del nuevo producto
                            newprod.setId_prod(Integer.parseInt(view_modificar.caja_idprod.getText()));
                            newprod.setNombre(view_modificar.caja_nombre.getText());
                            newprod.setPrecio(Double.parseDouble(view_modificar.caja_precio.getText()));
                            newprod.setUbicacion(view_modificar.caja_ubicacion.getText());
                            newprod.setExistencias(Integer.parseInt(view_modificar.caja_existencias.getText()));
                            newprod.setGarantia(Integer.parseInt(view_modificar.caja_garantia.getText()));
                            newprod.setValor_compra(Double.parseDouble(view_modificar.caja_valcompra.getText()));
                            newprod.setDescripcion(view_modificar.caja_areadescrip.getText());
                        
                            if(sqluser.registrarProd(newprod)){
                                JOptionPane.showMessageDialog(null,"El registro se ha guardado");
                            }
                        } 
                        else{
                            JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios");  
                        }  
                    }  
                }
                else{
                   JOptionPane.showMessageDialog(null,"ingrese un id_prod"); 
                }

                break; 
                
            case "modificar_prod":
                view_menu_admin.dispose();
                view_modificar.setVisible(true); 
                
                break; 
            case "update":
                // todos los campos tienen que ser obligatoris, para modificar
                if(!view_modificar.caja_idprod.getText().equals("") && 
                    !view_modificar.caja_nombre.getText().equals("") &&
                    !view_modificar.caja_precio.getText().equals("") &&
                    !view_modificar.caja_ubicacion.getText().equals("") &&
                    !view_modificar.caja_existencias.getText().equals("")&&
                    !view_modificar.caja_valcompra.getText().equals("")&&   
                    !view_modificar.caja_areadescrip.getText().equals("")){
                    
                     // verifica que el producto a modificar si este
                    if(sqluser.Buscarproducto(Integer.parseInt(view_modificar.caja_idprod.getText()))== 1){
                        
                        // verifica que no se haya cambiado el id_prod
                        if(Integer.parseInt(view_modificar.caja_idprod.getText()) == id_prod){
                            
                           if(sqluser.modificarProd(view_modificar)){
                                JOptionPane.showMessageDialog(null,"Producto modificado");
                            }   
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"No se puede cambiar el id_prod que busco inicialmente");
                        }
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"El Producto no existe");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios");  
                }            
                
                break; 
                
            case "eliminar":
                Producto newprod_borrado = new Producto();  //donde vamos a guardar el producto que vamos a borrar
                
                if(!view_modificar.caja_idprod.getText().equals("") && 
                    !view_modificar.caja_nombre.getText().equals("") &&
                    !view_modificar.caja_precio.getText().equals("") &&
                    !view_modificar.caja_ubicacion.getText().equals("") &&
                    !view_modificar.caja_existencias.getText().equals("")&&
                    !view_modificar.caja_valcompra.getText().equals("")&&   
                    !view_modificar.caja_areadescrip.getText().equals("")){
                    
                        
                        
                    //si el producto esta, entonces eliminelo 
                    if(sqluser.Buscarproducto(Integer.parseInt(view_modificar.caja_idprod.getText()))== 1){
                        
                        newprod_borrado.setId_prod(Integer.parseInt(view_modificar.caja_idprod.getText()));
                        // guardamos el id del producto borrado
                        sqluser.updateVenta(newprod_borrado);
                        // metodo que elimina el producto
                        if(sqluser.eliminarProd(view_modificar)){
                            JOptionPane.showMessageDialog(null,"Producto eliminado");
                            
                            
                            newprod_borrado.setNombre(view_modificar.caja_nombre.getText());
                            newprod_borrado.setPrecio(Double.parseDouble(view_modificar.caja_precio.getText()));
                            newprod_borrado.setUbicacion(view_modificar.caja_ubicacion.getText());
                            newprod_borrado.setExistencias(Integer.parseInt(view_modificar.caja_existencias.getText()));
                            newprod_borrado.setGarantia(Integer.parseInt(view_modificar.caja_garantia.getText()));
                            newprod_borrado.setValor_compra(Double.parseDouble(view_modificar.caja_valcompra.getText()));
                            newprod_borrado.setDescripcion(view_modificar.caja_areadescrip.getText());
                            
                            if(sqluser.registrarProdBorrado(newprod_borrado)){
                                JOptionPane.showMessageDialog(null,"El Producto borrado se guardo en una tabla auxiliar, por si algo");
                                sqluser.limpiar(view_modificar);
                            }
                        }
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"El Producto no existe");
                    }
                } 
                else{
                   JOptionPane.showMessageDialog(null,"Para borrar 1ero hacer busqueda y que todos los campos no esten vacios"); 
                }
                sqluser.limpiar(view_modificar);                  
                break; 
            case "estadisticas": 
                String ganacias = sqluser.ganancias(); 
                String cant_vend = sqluser.masymenosVendidos();
                String inf = "\nProductos ordenados por cantidad de ventas\n\n";
                if(!ganacias.equals("") || !cant_vend.equals("")){
                    JOptionPane.showMessageDialog(null,ganacias+inf+cant_vend,"Estadisticas",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null,"No hay productos registrados");
                }
                break;
                
            case "limpiar":
                sqluser.limpiar(view_modificar);
                break;
            case "cancelar_reg":
                view_reg.dispose();
                view_menu_admin.setVisible(true); 
                break;  
            case "salir_venta":
                view_venta.dispose();
                view_login.setVisible(true); 
                view_login.caja_codigo.setText("");
                view_login.caja_contraseña.setText("");
                break; 
            case "salir_mod":
                view_modificar.dispose();
                view_menu_admin.setVisible(true);
                sqluser.limpiar(view_modificar); 
                break;
            case "salir_admin":
                view_menu_admin.dispose();
                view_login.setVisible(true); 
                view_login.caja_codigo.setText("");
                view_login.caja_contraseña.setText(""); 
                break;

        }
        
    }
    
} 

/*
codigo = view_login.caja_codigo.getText();
                password = new String(view_login.caja_contraseña.getPassword()); 
                //SELECT * FROM `empleados` WHERE id_emp = "1088345137" AND password = "cafeoscuro"; 
                String sql = "SELECT * FROM empleados WHERE id_emp = '"+codigo+"' AND"
                + " password = '"+password+"' ";
                  
                try{
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datocapturado = rs.getString("id_emp");
                }

                if(datocapturado.equals("")){

                    JOptionPane.showMessageDialog(null, "no se ha registrado","error al iniciar sesion",JOptionPane.OK_OPTION);
                }
                else{
                    ViewMenu menu = new ViewMenu();
                    menu.setVisible(true);
                    view_login.dispose(); 
                }
           
                } catch (Exception ea) {
                    System.out.println(ea.getMessage());

                }
*/
