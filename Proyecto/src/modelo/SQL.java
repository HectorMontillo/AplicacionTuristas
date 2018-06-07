/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vista.ViewCrearPaquete;
import vista.ViewExcursiones;

public class SQL extends Conexion{
    
    // metodo para verificar 
    public boolean ConsultaAdministrador(){
        Connection con = getConexion();
        //SELECT * FROM `empleados` WHERE id_emp = "1088345137" AND password = "cafeoscuro"; 
        String sql = "SELECT * FROM empleado WHERE id_tipo = 0";
                  
        try{
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()){
                rs.close();
                st.close();
                con.close();
                return true;
            }else{
                rs.close();
                st.close();
                con.close();
                return false;
            }
            

            } catch (SQLException ea) {
                System.out.println("No hay administradores en la base de datos");
                  return false; 
            }       
    }
    
    // metodo para consultar inicio 
    public int ConsultaInicio(String Codigo, String Clave){
        int tipo = 3; 
        Connection con = getConexion();
        //SELECT * FROM `empleados` WHERE id_emp = "1088345137" AND password = "cafeoscuro"; 
        String sql = "SELECT * FROM empleado WHERE id_admin = '"+Codigo+"' AND"
         + " password = '"+Clave+"'";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                 tipo = rs.getInt("id_tipo");
            }
            
            rs.close();
            st.close();
            con.close();
            
           
            } catch (SQLException ea) {
                    
                    System.out.println("Exception");
                    tipo = 3;    

            }
            
    return tipo;  
    }
    
    // metodo para insertar un nuevo empleado
    public boolean AgregarAdminstrador(Administrador Admin){
       
       // para insertar en mysql vamos a prepara la consulta 
       PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql= "INSERT INTO empleado(id_admin,nombre,password,"
               + "id_tipo) VALUES(?,?,?,?)"; 
       
       try {
           ps = con.prepareStatement(sql);
           ps.setString(1, Admin.getCodigo());
           ps.setString(2, Admin.getUsuario());
           ps.setString(3, Admin.getClave());
           ps.setInt(4, 0);
           ps.execute();
           
           ps.close();
           con.close();
           return true; 
           
       } catch (SQLException ex) {
          
           if(ex.getErrorCode()==1062){
               JOptionPane.showMessageDialog(null, "La cedula ya esta resgistrada","error al registrar",JOptionPane.OK_OPTION);
           }
           else{
               JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.OK_OPTION); 
           }
           
           return false; 
       }
       
   }
    
    public boolean AgregarVendedor(Vendedor vendedor){
       
       // para insertar en mysql vamos a prepara la consulta 
       PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql= "INSERT INTO empleado(id_admin,nombre,password,"
               + "id_tipo) VALUES(?,?,?,?)"; 
       
       try {
           ps = con.prepareStatement(sql);
           ps.setString(1, vendedor.getCodigo());
           ps.setString(2, vendedor.getUsuario());
           ps.setString(3, vendedor.getClave());
           ps.setInt(4, 1);
           ps.execute();
           
           ps.close();
           con.close();
           return true; 
           
       } catch (SQLException ex) {
          
           if(ex.getErrorCode()==1062){
               JOptionPane.showMessageDialog(null, "La cedula ya esta resgistrada","error al registrar",JOptionPane.OK_OPTION);
           }
           else{
               JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.OK_OPTION); 
           }
           
           return false; 
       }
       
   }
    
    public boolean agregarPaquete(String nombre_paquete, String destino){
        
       PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql= "INSERT INTO paquete(nombre,destino) VALUES(?,?)"; 
       
       try {
           ps = con.prepareStatement(sql);
           ps.setString(1, nombre_paquete);
           ps.setString(2, destino);
           ps.execute();
           
           ps.close();
           con.close();
           return true; 
           
       } catch (SQLException ex) {

               JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.OK_OPTION); 
           }
           
           return false; 
    } 
    
    public void cargarComboBox(ViewCrearPaquete view_paquete){
        
        Connection con = getConexion(); 
        String sql = "SELECT * FROM paquete";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
             // limpia todo los items que estaban antes
            //view_paquete.CB_listapaquetes.removeAllItems();
            
            while(rs.next()){
                view_paquete.CB_listapaquetes.addItem(rs.getString("nombre"));
            }
            
            rs.close();
            st.close();
            con.close();
            
           
            } catch (SQLException ea) {
                    
                JOptionPane.showMessageDialog(null, ea.getMessage(),"error",JOptionPane.OK_OPTION);
            }       
    }
    
    public void cargarComboOperadores(ViewExcursiones view_excursion){
        
        Connection con = getConexion(); 
        String sql = "SELECT * FROM operador";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

             // limpia todo los items que estaban antes
            view_excursion.CB_VistaExcursionesOperador.removeAllItems();
            
            while(rs.next()){
                view_excursion.CB_VistaExcursionesOperador.addItem(rs.getString("id_operador"));
            }
            
            rs.close();
            st.close();
            con.close();
            
           
            } catch (SQLException ea) {
                    
                JOptionPane.showMessageDialog(null, ea.getMessage(),"error",JOptionPane.OK_OPTION);
            }       
    }

    public void cargarComboHoteles(ViewExcursiones view_excursion){
        
        Connection con = getConexion(); 
        String sql = "SELECT * FROM hotel";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

             // limpia todo los items que estaban antes
            view_excursion.CB_VistaExcursionesHotel.removeAllItems();
            
            while(rs.next()){
                view_excursion.CB_VistaExcursionesHotel.addItem(rs.getString("nombre"));
            }
            
            rs.close();
            st.close();
            con.close();
            
           
            } catch (SQLException ea) {
                    
                JOptionPane.showMessageDialog(null, ea.getMessage(),"error",JOptionPane.OK_OPTION);
            }       
    }
    
    public int obtenerIdHotel(String nombre_hotel){
        
        int id_hotel = 0; 
        Connection con = getConexion();

        String sql = "SELECT * FROM hotel WHERE nombre = '"+nombre_hotel+"' ";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                id_hotel = rs.getInt("id_hotel");
                
            }
            
            rs.close();
            st.close();
            con.close();
            
           
            } catch (SQLException ea) {
                    
                    JOptionPane.showMessageDialog(null, ea.getMessage(),"Error",JOptionPane.OK_OPTION);    

            }
            
    return id_hotel;
    }
    

    public boolean registrarExcursion(String id_operador,int id_hotel, String lugar,int dias){
        
        PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql= "INSERT INTO excursion(id_operador,id_hotel,lugar,dias)"
               + " VALUES(?,?,?,?)"; 
       
       try {
           ps = con.prepareStatement(sql);
           ps.setString(1, id_operador);
           ps.setInt(2, id_hotel);
           ps.setString(3, lugar);
           ps.setInt(4, dias);
           ps.execute();
           
           ps.close();
           con.close();
           return true; 
           
       } catch (SQLException ex) {
          
            JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.OK_OPTION);
            return false; 
       }
       
    }
     public Cliente BuscarCliente(String ID){
        Connection con = getConexion();
        //SELECT * FROM `empleados` WHERE id_emp = "1088345137" AND password = "cafeoscuro"; 
        String sql = "SELECT * FROM cliente WHERE id_cliente = '"+ID+"'";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            Cliente cliente = new Cliente();

            if(rs.next()){
                cliente.setID(rs.getString("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setRiesgo(rs.getInt("id_riesgo"));
                cliente.setAbonado(rs.getDouble("saldo_acumulado"));
                System.out.println(cliente.getAbonado());
                cliente.setSaldoTotal(rs.getDouble("saldo_total")); 
                rs.close();
                st.close();
                con.close();
                return cliente;
            }else{
                rs.close();
                st.close();
                con.close();
                return null;
            
            }
        } catch (SQLException ea) {
            return null;
        }
              
    }
     
     public boolean Abonar(String ID,double abono){
      
       PreparedStatement ps = null; 
       Connection con = getConexion(); 
       
       Cliente cliente = this.BuscarCliente(ID);
       
       cliente.Abonar(abono);
         System.out.println(cliente.getAbonado()+" "+cliente.getSaldoTotal());
       
       String sql = "UPDATE cliente SET saldo_acumulado = ?, saldo_total = ? WHERE id_cliente = ?;";
       
       try {
           ps = con.prepareStatement(sql);
           ps.setDouble(1, cliente.getAbonado());
           ps.setDouble(2, cliente.getSaldoTotal());
           ps.setString(3, cliente.getID());
           ps.execute();
           
           ps.close();
           con.close();
           return true; 
           
       } catch (SQLException ex) {
           Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null, "Error al abononar","Error",JOptionPane.OK_OPTION);
           return false; 
       }
  }
  
    


           
    
    
    public int obtenerIdPaquete(String nombre_paquete){
        
        int id_paquete = 0; 
        Connection con = getConexion();

        String sql = "SELECT * FROM paquete WHERE nombre = '"+nombre_paquete+"' ";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                id_paquete = rs.getInt("id_paquete");
                
            }
            
            rs.close();
            st.close();
            con.close();
            
           
            } catch (SQLException ea) {
                    
                    JOptionPane.showMessageDialog(null, ea.getMessage(),"Error",JOptionPane.OK_OPTION);    

            }
            
    return id_paquete;
    }
    
    public int obtenerIdExcursion(){
        
        int id_excursion = 0; 
        Connection con = getConexion();
        //SELECT MAX(id_paquete) AS max_idpaquete FROM paquete
        String sql = "SELECT MAX(id_excursion) AS id_max FROM excursion";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                id_excursion = rs.getInt("id_max");
                
            }
            
            rs.close();
            st.close();
            con.close();
            
           
            } catch (SQLException ea) {
                    
                    JOptionPane.showMessageDialog(null, ea.getMessage(),"Error",JOptionPane.OK_OPTION);    

            }
            
    return id_excursion;
    }
    
    public boolean registrarPaqueteExcursion(int id_paquete, int id_excursion){
        PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql= "INSERT INTO paquete_excursion(id_paquete,id_excursion)"
               + " VALUES(?,?)"; 
       
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, id_paquete);
           ps.setInt(2, id_excursion);
           ps.execute();
           
           ps.close();
           con.close();
           return true; 
           
       } catch (SQLException ex) {
          
            JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.OK_OPTION); 

           return false; 
       }
    }
    
     public boolean CargarExcursiones(ArrayList Excursiones){
        
        Connection con = getConexion();
        String sql = "SELECT * FROM excursion";      
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                ExcursionPlus ex = new ExcursionPlus();
                ex.setID(rs.getInt("id_excursion"));
                ex.setIDOperador(rs.getString("id_operador"));
                ex.setLugar(rs.getString("lugar"));
                ex.setIDHotel(rs.getInt("id_hotel"));
                ex.setDias(rs.getInt("dias"));
                Excursiones.add(ex); 
            }
            rs.close();
            st.close();
            con.close();
            return true;
           
            } catch (SQLException ea) {
                    
                    JOptionPane.showMessageDialog(null, ea.getMessage(),"Error",JOptionPane.OK_OPTION);
                    return false;

            }
    }
    
    public boolean CargarPaquetes(ArrayList Paquetes){
        
        Connection con = getConexion();
        String sql = "SELECT * FROM paquete";
        
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                Paquete paquete = new Paquete();
                paquete.setDestino(rs.getString("destino"));
                paquete.setID(rs.getInt("id_paquete"));
                paquete.setNombre(rs.getString("nombre"));
                Paquetes.add(paquete);
            }
            
            rs.close();
            st.close();
            con.close();
            return true;

            } catch (SQLException ea) {
                    
                JOptionPane.showMessageDialog(null, ea.getMessage(),"Error",JOptionPane.OK_OPTION);
                return false;

            }
    }
    
     public boolean RelacionarExcursionesPaquetes(ArrayList Paquetes,ArrayList Excursiones){
        
         int id_paquete;
         int id_excursion; 
        Connection con = getConexion();
        String sql = "SELECT * FROM paquete_excursion";      
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
               Paquete paquete; 
               ExcursionPlus ex; 
                id_paquete = rs.getInt("id_paquete");
                id_excursion = rs.getInt("id_paquete");
                
                paquete = (Paquete)Paquetes.get(id_paquete-1);
                ex = (ExcursionPlus)Excursiones.get(id_excursion-1);
                
                paquete.AgregarExcursion(ex); 
                
            }
            rs.close();
            st.close();
            con.close();
            return true;
           
            } catch (SQLException ea) {
                    
                    JOptionPane.showMessageDialog(null, ea.getMessage(),"Error",JOptionPane.OK_OPTION);
                    return false;

            }
    }
    
    public boolean CargarAerolineas(ArrayList Aerolineas){
        
        Connection con = getConexion();
        String sql = "SELECT * FROM aerolinea";
        
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                Aerolinea aerolinea = new Aerolinea();
                aerolinea.setID(rs.getInt("id_aero"));
                aerolinea.setNombre(rs.getString("nombre"));
                Aerolineas.add(aerolinea);
            }
            
            rs.close();
            st.close();
            con.close();
            return true;

            } catch (SQLException ea) {
                    
                JOptionPane.showMessageDialog(null, ea.getMessage(),"Error",JOptionPane.OK_OPTION);
                return false;

            }
    }
    
    
    /*
    public int entrarAdmin(String cod,String pass){
        
        codigo = cod;
        password = pass; 
        String id_emp="";
        int result = 0; 
        Connection con = getConexion();
        //SELECT * FROM `empleados` WHERE id_emp = "1088345137" AND password = "cafeoscuro"; 
        String sql = "SELECT * FROM empleado WHERE id_emp = '"+codigo+"' AND"
         + " password = '"+password+"' AND id_tipo = 1";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                 id_emp = rs.getString("id_emp");
            }
            
            rs.close();
            st.close();
            con.close();
            
            if(id_emp.equals("")){

                JOptionPane.showMessageDialog(null, "Tiene que ser admin para ingresar","Error ingreso",JOptionPane.OK_OPTION); 
            }
            else{
                result= 1;
                }
           
            } catch (Exception ea) {
                    System.out.println(ea.getMessage());

            }
            
        return result;   
    }
    
    public boolean registrar(UsuarioP user){
       
       // para insertar en mysql vamos a prepara la consulta 
       PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql= "INSERT INTO empleado(id_emp,password,nombre,apellido,telefono,correo,"
               + "id_tipo) VALUES(?,?,?,?,?,?,?)"; 
       
       try {
           ps = con.prepareStatement(sql);
           ps.setString(1, user.getId_emp());
           ps.setString(2, user.getPassword());
           ps.setString(3, user.getNombre());
           ps.setString(4, user.getApellido());
           ps.setString(5, user.getTelefono());
           ps.setString(6, user.getCorreo());
           ps.setInt(7, user.getTipo_emp());
           ps.execute();
           
           //ps.close();
           //con.close();
           return true; 
           
       } catch (SQLException ex) {
          
           if(ex.getErrorCode()==1062){
               JOptionPane.showMessageDialog(null, "La cedula ya esta resgistrada","error al registrar",JOptionPane.OK_OPTION);
           }
           else{
               JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.OK_OPTION); 
           }
           
           return false; 
       }
       
   }
    
    public boolean registrarProd(Producto prod){
       
       // para insertar en mysql vamos a prepara la consulta 
       PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql= "INSERT INTO productos(id_productos,nombre_producto,precio,ubicacion,existencia,descripcion,"
               + "garantia,valor_compra) VALUES(?,?,?,?,?,?,?,?)"; 
       
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, prod.getId_prod());
           ps.setString(2, prod.getNombre());
           ps.setDouble(3, prod.getPrecio());
           ps.setString(4, prod.getUbicacion());
           ps.setInt(5, prod.getExistencias());
           ps.setString(6, prod.getDescripcion());
           ps.setInt(7, prod.getGarantia());
           ps.setDouble(8, prod.getValor_compra());
           ps.execute();
           
           ps.close();
           con.close();
           return true; 
           
       } catch (SQLException ex) {
           
           if(ex.getErrorCode()==1062){
               JOptionPane.showMessageDialog(null, "El id_prod ya esta resgistrado","error al registrar",JOptionPane.OK_OPTION);
           }
           else{
               JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.OK_OPTION); 
           }
           
           return false; 
       }
       
   }
   
    public boolean registrarProdBorrado(Producto prod){
        // para insertar en mysql vamos a prepara la consulta 
       PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql= "INSERT INTO productos_borrados(id_productos,nombre_producto,precio,ubicacion,existencia,descripcion,"
               + "garantia,valor_compra) VALUES(?,?,?,?,?,?,?,?)"; 
       
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, prod.getId_prod());
           ps.setString(2, prod.getNombre());
           ps.setDouble(3, prod.getPrecio());
           ps.setString(4, prod.getUbicacion());
           ps.setInt(5, prod.getExistencias());
           ps.setString(6, prod.getDescripcion());
           ps.setInt(7, prod.getGarantia());
           ps.setDouble(8, prod.getValor_compra());
           ps.execute();
           
           ps.close();
           con.close();
           return true; 
           
       } catch (SQLException ex) {
           
           if(ex.getErrorCode()==1062){
               JOptionPane.showMessageDialog(null, "El id_prod ya esta resgistrado","error al registrar",JOptionPane.OK_OPTION);
           }
           else{
               JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.OK_OPTION); 
           }
           
           return false; 
       }
    }
    
    // se copia el producto borrado a tabla productos
    public boolean copiarAProductos(int id_prod){
        
         // para insertar en mysql vamos a prepara la consulta 
       PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql= "INSERT INTO productos SELECT * FROM productos_borrados WHERE id_productos = '"+id_prod+"' "; 
       
       try {
           ps = con.prepareStatement(sql);
           ps.execute();
           
           ps.close();
           con.close();
           return true; 
           
       } catch (SQLException ex) {
           
           if(ex.getErrorCode()==1062){
               JOptionPane.showMessageDialog(null, "El id_prod ya esta resgistrado","error al registrar",JOptionPane.OK_OPTION);
           }
           else{
               JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.OK_OPTION); 
           }
           
           return false; 
       }
    }
    public String Buscarproducto(String nom){
      String nombreprod = nom;
      String datonombre= "";
      double datoprecio; 
      String datoubicacion; 
      int datoexistencias; 
      int datogarantia; 
      int id_prod; 
      String datodescripcion; 
      String texto = "";
      
      Connection con = getConexion();
      
      String sql = "SELECT * FROM `productos` WHERE nombre_producto like '%"+nombreprod+"' or nombre_producto"
              + " like '"+nombreprod+"%' or nombre_producto like '%"+nombreprod+"%'";        
      
      try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                id_prod = rs.getInt("id_productos");
                 datonombre = rs.getString("nombre_producto");
                 datoprecio = rs.getDouble("precio"); 
                 datoubicacion = rs.getString("ubicacion"); 
                 datoexistencias = rs.getInt("existencia"); 
                 datogarantia = rs.getInt("garantia");
                 datodescripcion = rs.getString("descripcion");
                 texto = texto + "id Producto: "+ id_prod+ "\nNombre: "+datonombre+ "\nUbicacion: "+datoubicacion+
                         "\nexistencias: "+datoexistencias +"\nPrecio: "+datoprecio+
                         "\ngarantia: "+datogarantia+ "\ndescripcion: "+datodescripcion +"\n\n"; 
            }
            
            rs.close();
            st.close();
            con.close();
            
            if(datonombre.equals("")){

                JOptionPane.showMessageDialog(null, "NO se encontro el producto","error al buscar",JOptionPane.OK_OPTION); 
            }
                       
            } catch (Exception ea) {
                    System.out.println(ea.getMessage());

            }
        //texto= datonombre + "/n" + datoprecio;   
        return texto;
  }
    // buscar producto solo por id
  public int Buscarproducto(int id_prod){
      
      int result = 0;  // 0 si no esta, 1 si esta 
      String nomb = ""; 
      Connection con = getConexion();
      
      String sql = "SELECT * FROM `productos` WHERE id_productos = '"+id_prod+"';";       
      
      try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                nomb = rs.getString("nombre_producto");
                   
            }
            if(nomb.equals("")){
              result = 0;  
            }
            else{
                result = 1; 
            }
            
            rs.close();
            st.close();
            con.close();
                       
            } catch (Exception ea) {
                    System.out.println(ea.getMessage());

            }
      return result; 
  }
  
  public int buscarProductoBorrado(int id_prod){
     int result = 0;  // 0 si no esta, 1 si esta 
      String nomb = ""; 
      Connection con = getConexion();
      
      String sql = "SELECT * FROM `productos_borrados` WHERE id_productos = '"+id_prod+"';";       
      
      try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                nomb = rs.getString("nombre_producto");
                   
            }
            if(nomb.equals("")){
              result = 0;  
            }
            else{
                result = 1; 
            }
            
            rs.close();
            st.close();
            con.close();
                       
            } catch (Exception ea) {
                    System.out.println(ea.getMessage());

            }
      return result; 
  }
  
  public void Buscarproducto(int id_prod,ViewModificar view_mod){
      
      String nomb = ""; 
      Connection con = getConexion();
      
      String sql = "SELECT * FROM `productos` WHERE id_productos = '"+id_prod+"';";       
      
      try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                nomb = rs.getString("nombre_producto");
                view_mod.caja_nombre.setText(rs.getString("nombre_producto")); 
                view_mod.caja_precio.setText(String.valueOf(rs.getDouble("precio")));
                view_mod.caja_ubicacion.setText(rs.getString("ubicacion"));
                view_mod.caja_existencias.setText(String.valueOf(rs.getInt("existencia")));
                view_mod.caja_garantia.setText(String.valueOf(rs.getInt("garantia"))); 
                view_mod.caja_areadescrip.setText(rs.getString("descripcion")); 
                view_mod.caja_valcompra.setText(String.valueOf(rs.getDouble("valor_compra")));
                   
            }
            if(nomb.equals("")){
              JOptionPane.showMessageDialog(null, "NO se encontro el producto","No-Encontrado",JOptionPane.OK_OPTION);  
            }
            
            rs.close();
            st.close();
            con.close();
                       
            } catch (Exception ea) {
                    System.out.println(ea.getMessage());

            }
  }
  
  public boolean registrarSesion(String id_emp){
      
       // para insertar en mysql vamos a prepara la consulta 
       PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql= "INSERT INTO sesion(id_emp) VALUES(?)"; 
       
       try {
           ps = con.prepareStatement(sql);
           ps.setString(1, id_emp);
           ps.execute();
           
           ps.close();
           con.close();
           return true; 
           
       } catch (SQLException ex) {
           Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null, "problema al registrar sesion","sesion",JOptionPane.OK_OPTION);
           return false; 
       }
  }
  
  
  // aplicaar sobrecarga de metodos, 1 recibe el num_fact y el otro no 
  // debe rebir el id del empleado 
  public boolean registrarVenta(int id_prod,String id_emp,int cant,int num_factura){
      
       // para insertar en mysql vamos a prepara la consulta 
       PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql= "INSERT INTO ventas(id_prod,id_emp,num_factura,cantidad) VALUES(?,?,?,?)"; 
       
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, id_prod);
           ps.setString(2, id_emp);
           ps.setInt(3, num_factura);
           ps.setInt(4, cant);
           ps.execute();
           
           this.restarExistencias(id_prod,cant);
           ps.close();
           con.close();
           return true; 
           
       } catch (SQLException ex) {
           Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null, "problema al registrar venta","error venta",JOptionPane.OK_OPTION);
           return false; 
       }
  }
  
  public int cantExistencias(int id_prod){
      int cant=0; 
      Connection con = getConexion();
      
      String sql = "SELECT existencia FROM productos WHERE id_productos = '"+id_prod+"'";       
      
      try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                cant = rs.getInt("existencia"); 
            }
            
            rs.close();
            st.close();
            con.close();
                       
            } catch (Exception ea) {
                    System.out.println(ea.getMessage());

            }
      return cant; 
  }
  
  //restar existencias 
  public boolean restarExistencias(int id_prod,int cant){
      
       PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql = "UPDATE productos SET existencia = productos.existencia - ?  WHERE id_productos = ?;";
       
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, cant);
           ps.setInt(2, id_prod);
           ps.execute();
           
           ps.close();
           con.close();
           return true; 
           
       } catch (SQLException ex) {
           Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null, "Error al restar existencias","Error",JOptionPane.OK_OPTION);
           return false; 
       }
  }
  
  // mira cual fue el ultimo numero de factura
  public int capturarFactura(){
      
      int max_fact = 0;
        Connection con = getConexion();
        String sql = "SELECT MAX(num_factura) AS max_factura FROM ventas;";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                 max_fact = rs.getInt("max_factura");
            }
            
            rs.close();
            st.close();
            con.close();
            } catch (Exception ea) { 
                  System.out.println(ea.getMessage());
                  JOptionPane.showMessageDialog(null, "no hay facturas","factura",JOptionPane.OK_OPTION);

            } 
            
        return max_fact;   
  }
  
  public String productoFactura(int max_fact){
      String datonombre= "";
      double datoprecio;  
      int datogarantia; 
      String datodescripcion; 
      String texto = "";
      
      Connection con = getConexion();
      
      String sql = "SELECT nombre_producto,precio, descripcion, garantia FROM `productos`"+ 
                    " WHERE id_productos = (SELECT id_prod FROM ventas "+ 
                     "WHERE num_factura = "+max_fact+" AND productos.id_productos = ventas.id_prod);";        
      
      try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                 datonombre = rs.getString("nombre_producto");
                 datoprecio = rs.getDouble("precio");  
                 datogarantia = rs.getInt("garantia");
                 datodescripcion = rs.getString("descripcion");
                 
                 texto = texto + "Nombre: "+datonombre+ "\nPrecio: "+datoprecio+
                         "\ngarantia: "+datogarantia+ "\ndescripcion: "+datodescripcion +"\n\n"; 
            }
            
            rs.close();
            st.close();
            con.close();
            
            if(datonombre.equals("")){

                JOptionPane.showMessageDialog(null, "NO se encontro el producto","error al buscar",JOptionPane.OK_OPTION); 
            }
                       
            } catch (Exception ea) {
                    System.out.println(ea.getMessage());

            }
        return texto;
  }
  
  public boolean modificarProd(ViewModificar view_mod){
      
       PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql = "UPDATE productos SET id_productos = ?, nombre_producto = ?,"+
       " precio = ?, ubicacion = ?, existencia = ?, descripcion = ?, garantia = ?,"+
               " valor_compra = ? WHERE id_productos = ?";
       
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, Integer.parseInt(view_mod.caja_idprod.getText()));
           ps.setString(2, view_mod.caja_nombre.getText());
           ps.setDouble(3, Double.parseDouble(view_mod.caja_precio.getText()));
           ps.setString(4, view_mod.caja_ubicacion.getText());
           ps.setInt(5, Integer.parseInt(view_mod.caja_existencias.getText()));
           ps.setString(6, view_mod.caja_areadescrip.getText());
           ps.setInt(7, Integer.parseInt(view_mod.caja_garantia.getText()));
           ps.setDouble(8, Double.parseDouble(view_mod.caja_valcompra.getText()));
           ps.setInt(9, Integer.parseInt(view_mod.caja_idprod.getText()));
           ps.execute();
           
           return true; 
           
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.OK_OPTION);
           return false; 
       } 
  }
  
  // se actualiza el id_prod_delete 
  public void updateVenta(Producto produc){
      PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql = "UPDATE ventas SET id_prod_delete = ? WHERE id_prod = ?";
       
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, produc.getId_prod());
           ps.setInt(2, produc.getId_prod());

           ps.execute(); 
           
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.OK_OPTION);

       } 
  }
  
  // se vuelve a colocar en null el  id_prod_delete 
  public boolean updateVentaRetro(int id_prod){
     PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql = "UPDATE ventas SET id_prod_delete = NULL WHERE id_prod_delete = '"+id_prod+"' ";
       
       try {
           ps = con.prepareStatement(sql);
           ps.execute();
           return true;
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.OK_OPTION);
           return false; 
       }  
  }
  
  public boolean updateIdProd(int id_prod){
     PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql = "UPDATE ventas SET id_prod = ? WHERE id_prod_delete = ?";
       
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, id_prod);
           ps.setInt(2,id_prod);
           ps.execute();
           return true;
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.OK_OPTION);
           return false; 
       }  
  }
  
  public String empleadoFactura(String id_emp){
        
        String nombre;
        String apellido;
        String texto="";  
        Connection con = getConexion();
        //SELECT nombre, apellido FROM `empleado` WHERE id_emp = "1088345138";
        String sql = "SELECT nombre, apellido FROM `empleado` WHERE id_emp = '"+id_emp+"'; ";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                 nombre = rs.getString("nombre");
                 apellido = rs.getString("apellido");
                 texto = "Atendido por: "+nombre+" "+apellido;
                 
            }
            
            rs.close();
            st.close();
            con.close();
            
            if(id_emp.equals("")){

                JOptionPane.showMessageDialog(null, "no se encontro empleado","error_factura_emp",JOptionPane.OK_OPTION); 
            }
           
            } catch (Exception ea) {
                    System.out.println(ea.getMessage());

            }
            
        return texto;   
    }
  
  public boolean eliminarProd(ViewModificar view_mod){
       
       PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql = "DELETE FROM productos WHERE id_productos = ?";
       
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, Integer.parseInt(view_mod.caja_idprod.getText()));
           ps.execute();
           
           con.close();
           return true; 
           
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.OK_OPTION);
           return false; 
       }
  }
  
  public boolean eliminarProdBorrado(int id_prod){
      PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql = "DELETE FROM productos_borrados WHERE id_productos = ?";
       
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, id_prod);
           ps.execute();
           
           con.close();
           return true; 
           
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.OK_OPTION);
           return false; 
       }
  }
  
  public String ganancias(){

        Double ganancia =0.0; // toma las ganancias de los productos que estan en inventario 
        Double ganancia_total = 0.0;  // ganancias de los productos que fueron vendidos 
        String texto = "";
        Connection con = getConexion();

        // Ganancias por mes 
        String sql = "SELECT SUM(ventas.cantidad*(productos.precio-productos.valor_compra)) AS Ganancia"+
        " FROM productos INNER JOIN ventas on productos.id_productos=ventas.id_prod WHERE ventas.fecha BETWEEN"+
        " sysdate()- INTERVAL 30 DAY AND Sysdate()";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                  ganancia= rs.getDouble("Ganancia");
            }
            ganancia_total =ganancia + this.ganancias_borrados();
            texto = "Historico de Ganancias del ultimo mes: "+ ganancia_total+"\n";
            rs.close();
            st.close();
            con.close();
            
            } catch (SQLException ex) { 

                  JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.OK_OPTION);
                  texto = "";

            } 

        return texto;
  }
  public double ganancias_borrados(){
        Double ganancia =0.0;
        Connection con = getConexion();

        // Ganancias por mes 
        String sql = "SELECT SUM(ventas.cantidad*(productos_borrados.precio-productos_borrados.valor_compra)) AS Ganancia"+
        " FROM productos_borrados INNER JOIN ventas on productos_borrados.id_productos=ventas.id_prod_delete"+
        " WHERE ventas.fecha BETWEEN sysdate()- INTERVAL 30 DAY AND Sysdate()";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                  ganancia= rs.getDouble("Ganancia");
            }
            
            rs.close();
            st.close();
            con.close();
            
            } catch (SQLException ex) { 
                  JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.OK_OPTION);

            } 

        return ganancia;
  }
  // me muestra el valor a pagar, de la última compra
  public String valorComprado(int num_fact){
      
        Double valor = 0.0;
        String texto = "";
        Connection con = getConexion();

        // Ganancias por mes 
        String sql = "SELECT SUM(ventas.cantidad*(productos.precio)) AS Valor_Comprado FROM ventas"+ 
                " INNER JOIN productos ON id_prod = productos.id_productos WHERE num_factura = '"+num_fact+"' ";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                  valor= rs.getDouble("Valor_Comprado");
                  texto = "Valor a pagar: "+valor+"\n\n";
            }
            
            rs.close();
            st.close();
            con.close();
            
            } catch (SQLException ea) { 
                  JOptionPane.showMessageDialog(null, ea.getMessage(),"Error",JOptionPane.OK_OPTION);

            } 
        if(texto.equals("")){
            texto = "Error al obtener ganancias"; 
        }
        return texto;
  }
  public String masymenosVendidos(){
        String nombre;
        int cantidad; 
        String texto = "";
        Connection con = getConexion();

        // Ganancias por mes 
        String sql = "SELECT ventas.id_prod,productos.nombre_producto, SUM(ventas.cantidad) AS Suma"+
        " FROM ventas INNER JOIN productos ON ventas.id_prod = productos.id_productos WHERE ventas.fecha"+
        " BETWEEN sysdate()- INTERVAL 30 DAY AND Sysdate() GROUP BY id_prod ORDER BY Suma DESC";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                  nombre= rs.getString("nombre_producto");
                  cantidad = rs.getInt("Suma");
                  texto = texto + "Nombre: "+ nombre + "\ncantidad vendida: "+ cantidad+"\n\n"; 

            }
            
            rs.close();
            st.close();
            con.close();
            
            } catch (SQLException ea) { 
                  JOptionPane.showMessageDialog(null, ea.getMessage(),"Error",JOptionPane.OK_OPTION);

            } 
        if(texto.equals("")){
            texto = "No hay productos vendidos registrados, para sacar estadisticas"; 
        }
        return texto;
  }
  
  public void limpiar(ViewModificar view_mod){
      
      view_mod.caja_idprod.setText("");
      view_mod.caja_nombre.setText("");
      view_mod.caja_precio.setText("");
      view_mod.caja_ubicacion.setText("");
      view_mod.caja_existencias.setText("");
      view_mod.caja_garantia.setText("");
      view_mod.caja_areadescrip.setText("");
      
  }
  
  public String soloNombre(int id_prod){
      
      String nombreprod = "";
        Connection con = getConexion();
        String sql = "SELECT nombre_producto FROM productos WHERE id_productos = '"+id_prod+"';";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                  nombreprod= rs.getString("nombre_producto");
            }
            
            rs.close();
            st.close();
            con.close();
            
            } catch (Exception ea) { 
                  System.out.println(ea.getMessage());
                  JOptionPane.showMessageDialog(null, "no se encontro el id","Error id",JOptionPane.OK_OPTION);

            } 
            
        return nombreprod;   
  }
  
  

  public void setUltimoLogeado(String aux_emp){
      
      this.aux_emp = aux_emp; 
  }
  
  public String getUltimoLogeado(){
      return aux_emp; 
  }*/
}
