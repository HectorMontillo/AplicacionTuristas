/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import java.util.ArrayList;

/**
 *
 * @author LordBrakon
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        ArrayList paquete = new ArrayList();
        
        obj ob = new obj();
        
        paquete.add(ob);
        
        obj ob2;
        
        ob2 = (obj)paquete.get(0);
        
        ob2.hola = "hola2";
        
        obj ob3;
        ob3 = (obj)paquete.get(0);
        System.out.println(ob3.hola);
        // TODO code application logic here
    }
    
}
