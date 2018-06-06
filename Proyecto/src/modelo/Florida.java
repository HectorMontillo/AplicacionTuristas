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
public class Florida extends ExcursionDecorator{
    
    private I_Excursion excur;
    
    public Florida(I_Excursion exc){
        super(exc);
        excur = exc; 
    }
    
    @Override
    public String getExcursion() {
        
        return excur.getExcursion()+"-Excursion Florida";   
    }
    
}
