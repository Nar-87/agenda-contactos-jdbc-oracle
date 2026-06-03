/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.text.SimpleDateFormat;
import java.util.Comparator;

/**
 *
 * @author Multi
 */
public class ComparadorFechasCumpleanios implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
            
            String fecha1 = sdf.format(((Amigos) o1).getFechanacimiento());
            String fecha2 = sdf.format(((Amigos) o2).getFechanacimiento());          

            return fecha1.compareTo(fecha2);
        }
     
    }

    
