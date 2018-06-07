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
public class Hotel {
    
    private int id_hotel;
    private String nombre; 
    private int estrellas;
    private double costo_noche;
    
    public Hotel(){}

    public Hotel(int id_hotel, String nombre, int estrellas, double costo_noche) {
        this.id_hotel = id_hotel;
        this.nombre = nombre;
        this.estrellas = estrellas;
        this.costo_noche = costo_noche;
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public double getCosto_noche() {
        return costo_noche;
    }

    public void setCosto_noche(double costo_noche) {
        this.costo_noche = costo_noche;
    }
    
    
    
}
