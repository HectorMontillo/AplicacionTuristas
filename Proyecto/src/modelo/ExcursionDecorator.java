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

public abstract class ExcursionDecorator implements I_Excursion{
    
    
    private I_Excursion excursion; 
    
    public ExcursionDecorator(I_Excursion exc){
        excursion = exc; 
    }
            
    public I_Excursion getIngrediente(){
        return excursion;   
        
    }
}
