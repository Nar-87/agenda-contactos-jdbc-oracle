/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Multi
 */
public abstract class Contactos implements Comparable, Serializable{

    private String nombre;
    private Integer telefono;

    protected Contactos(String n, Integer t) {
        nombre=n;
        telefono=t;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contactos other = (Contactos) obj;
        return Objects.equals(this.nombre, other.nombre);
    }
    
    @Override
    public String toString() {
        return "- Nombre: " + nombre + " - Telefono: " + telefono;
    }

    @Override
    public int compareTo(Object o) {
        Contactos otroContacto = (Contactos) o;
        String objnombre=otroContacto.getNombre();
        return nombre.compareTo(otroContacto.nombre);
    }
}
    
    
    

