/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import java.util.ArrayList;

/**
 *
 * @author Multi
 */
public class ExistenciaTextoErrores extends Exception{
    ArrayList<String>errores;
    
    public ExistenciaTextoErrores(ArrayList<String>err){
        errores=err;
    }
    @Override
    public String toString(){
        String salida = "";
        for(String problema: errores){
        salida += (errores+"\n");
    }
        return salida;
    }
}
