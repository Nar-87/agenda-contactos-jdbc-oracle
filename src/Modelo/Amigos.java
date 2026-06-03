/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Control.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Multi
 */
public class Amigos extends Contactos implements Comparable, Serializable{
     private Date fechanacimiento;
   
    public Amigos(String n, Integer t, Date d) {
        super(n, t);
        fechanacimiento=d;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    @Override
    public String toString() {
    String resultado=super.toString();
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    String fechastr=sdf.format(fechanacimiento);
        resultado += (" - Fecha de Nacimiento: " +fechastr);
        return resultado; 
    }
}
