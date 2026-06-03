/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author Multi
 */
public class ExistenciaException extends Exception {
    boolean existe; //TRUE cuando YA existe; FALSE cuando NO existe;
    String terminoproblema;

    public ExistenciaException(boolean existe, String terminoproblema) {
        this.existe = existe;
        this.terminoproblema = terminoproblema;
    }

    @Override
    public String toString() {
        if(existe){
            return "El contacto " + terminoproblema + " ya existe";
        } else {
            return "El contacto " + terminoproblema + " no existe";
        }
    }
}
