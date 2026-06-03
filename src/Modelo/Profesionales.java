/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author Multi
 */
public class Profesionales extends Contactos implements Comparable, Serializable {
    private String empresa;

    public Profesionales(String n, Integer t, String emp) {
        super(n, t);
        empresa=emp;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        String resultado=super.toString();
        resultado += (" -Empresa: "+ empresa);
        return resultado;
    } 
}
    
