/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Gustavo Davila
 */
public class Operador {
    
    private String id_operador;
    private String nombre; 
    
    public Operador(){}

    public Operador(String id_operador,String nombre) {
        this.id_operador = id_operador;
        this.nombre = nombre;       
    }

    public String getId_operador() {
        return id_operador;
    }

    public void setId_operador(String id_operador) {
        this.id_operador = id_operador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
   
}
