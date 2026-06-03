/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Modelo.Amigos;
import Modelo.BdContactos;
import Modelo.ComparadorFechasCumpleanios;
import Modelo.Contactos;
import Modelo.Profesionales;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Multi
 */
public class Agenda{
    BdContactos dao;

    public Agenda(){
        this.dao = new BdContactos();
    }
 
    public void alta(String nombre, String telefono, String fn, String empresa, String tipo) throws ParseException, ExistenciaException, NumberFormatException, IllegalArgumentException, SQLException{
        if (!tipo.equals("Amigo") && !tipo.equals("Profesional")) {
            throw new IllegalArgumentException("Tipo de contacto inválido. Debe ser 'Amigo' o 'Profesional'.");
        }
        if(dao.existe(nombre)){ 
            throw new ExistenciaException(true, nombre);
        }
        int tel = Integer.parseInt(telefono);
            if (tel <= 0) {
            throw new NumberFormatException("El número de teléfono debe ser positivo.");
        }
            Contactos c = null;
        if (tipo.equals("Amigo")) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            Date fechaNacimiento = sdf.parse(fn);
            c = new Amigos(nombre, tel, fechaNacimiento);
        } else if (tipo.equals("Profesional")) { 
            c = new Profesionales(nombre, tel, empresa);
        } 
        dao.insertar(c);
    }
    
    public void borrar(String nombre)throws ExistenciaException{
            if (!dao.existe(nombre)) {
        throw new ExistenciaException(false, nombre); // Si no existe, lanzamos la excepción
    }
         dao.eliminar(nombre);
    }
    
    public String listar() throws Exception{
        String resultado="";
        ArrayList<Contactos> lista=new ArrayList<>(dao.getTodos());
        if(lista.isEmpty()){
        throw new Exception ("La agenda esta vácia");
        }
        Collections.sort(lista);
        Iterator it=lista.iterator();
        while (it.hasNext()){
        Contactos a=(Contactos) it.next();
        resultado += (a.toString()+ "\n");
        }
        return resultado;
    }
   
    public String listaCumpleanios() throws Exception {
        String resultado="";
       
        ArrayList<Contactos> listacontactos=dao.getTodos();
        ArrayList<Amigos> listaamigos=new ArrayList<>();
        for (Contactos c : listacontactos ) {
                if (c instanceof Amigos) {
                   listaamigos.add((Amigos) c);
                }
            } 
                if(listaamigos.isEmpty()){
        throw new Exception ("No hay amigos para felicitar.");
        }
        Collections.sort(listaamigos, new ComparadorFechasCumpleanios());
        Date d= new Date ();
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String fechaActualStr = sdf.format(d);
        
        Iterator it=listaamigos.iterator();
        while (it.hasNext()){
        Amigos a=(Amigos) it.next();
        Date dstr=a.getFechanacimiento();
        String dstr1=sdf.format(dstr);
            if(dstr1.compareTo(fechaActualStr)>=0){
                resultado += a.toString()+ "\n";
            }
        }
        
        it=listaamigos.iterator();
        while (it.hasNext()){
        Amigos a=(Amigos) it.next();
        Date dstr=a.getFechanacimiento();
        String dstr1=sdf.format(dstr);
            if(dstr1.compareTo(fechaActualStr)<0){
                resultado += a.toString()+ "\n";
            }
        }
            return resultado;
    }

    /*public String buscar(String nombre)throws ExistenciaException{
        Contactos c = mapa.get(nombre); 
        if(c==null) throw new ExistenciaException(false, nombre);
        return c.toString()+ "\n";  
    }*/
    
    public void modificar(String nombre, String nuevotel, String nuevaemp, String nuevafecha) throws ParseException, ExistenciaException, NumberFormatException {
        Contactos c =dao.getContacto(nombre);  // Obtenemos el contacto por nombre
        if (c == null) {
            throw new ExistenciaException(false, nombre); //si no existe lanzamos la excepción
        }
            // Modificamos el teléfono si se proporcionó un nuevo teléfono
            if (nuevotel != null && !nuevotel.isEmpty()) {
                int nuevoteli = Integer.parseInt(nuevotel);
                c.setTelefono(nuevoteli); //actualizamos el telefono
            }
            
            // Si el contacto es de tipo 'Amigos', modificamos la fecha de nacimiento
            if (c instanceof Amigos) {
                if (nuevafecha != null && !nuevafecha.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
                    Date nuevaFechaNacimiento = sdf.parse(nuevafecha);
                    ((Amigos) c).setFechanacimiento(nuevaFechaNacimiento);//actualizamos la fecha de nacimiento
                }
            }
             // Si el contacto es de tipo 'Profesionales', modificamos la empresa
            if (c instanceof Profesionales) {
                if (nuevaemp != null && !nuevaemp.isEmpty()) {
                    ((Profesionales) c).setEmpresa(nuevaemp);
                }
            }
            dao.actualizar(c); // Actualizamos el mapa con el contacto modificado
    }
    
    public String contactosPorLetra(char letra) {
        String resultado = "";
            for (Contactos c : dao.getTodos()) { 
                if (c.getNombre().toUpperCase().charAt(0) == Character.toUpperCase(letra)) {
                    resultado += c.toString() + "\n";
                }
            }
            return resultado;
    }
    
    //método para escribir Archivo
    public void salvar (String ruta) throws FileNotFoundException, IOException{
        File fex= new File(ruta);
        File fnex = new File(ruta + "old"); // Archivo de respaldo
        if(fex.exists()){
            fex.renameTo(fnex); // Si existe el archivo, lo renombra como respaldo
        }
        try ( FileOutputStream fos = new FileOutputStream(ruta); // Nodo para escritura en archivo
        ObjectOutputStream oos = new ObjectOutputStream(fos); ){ // Filtro para escribir objetos
        oos.writeObject(null); // Escribe el mapa en el archivo
        
        oos.close();
        fos.close();
        
        //fnex.delete(); //se deja comentado para que vea el error que sale cuando no existe este método
        } catch(IOException ex){
            fnex.renameTo(fex); // Si hay error, restaura el archivo original
            throw ex;
        }
    }
    
    /*public void recuperar (String ruta) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(ruta); // Nodo para lectura de archivo
        ObjectInputStream ois = new ObjectInputStream(fis); // Filtro para leer objetos
        
        mapa = (HashMap<String, Contactos>)ois.readObject(); // Recupera el objeto del archivo
        
        ois.close();
        fis.close();
    }*/
    
    public void listarEnFichero(String ruta) throws IOException, Exception{
        String contenidodelFIchero=listar(); // Obtiene la lista de contactos como String
        try ( 
                FileWriter fw = new FileWriter(ruta);
                PrintWriter pw=new PrintWriter(fw);)
        {
            pw.print( contenidodelFIchero ); // Escribe el contenido en el archivo
            
            pw.close();
        }
    }
    
    public void listaCumplaniosEnFichero(String ruta) throws IOException, Exception{  
        String contenidodelFIcheroCumpleanios=listaCumpleanios(); // Obtiene lista de cumpleaños
        try (FileWriter fw = new FileWriter(ruta);
                PrintWriter pw=new PrintWriter(fw);) {
            
            pw.print( contenidodelFIcheroCumpleanios); // Escribe en el archivo
            
            pw.close();
        }
    }
    
    public void importar (String ruta) throws FileNotFoundException, IOException, ExistenciaTextoErrores, ParseException, ExistenciaException, SQLException{
        FileReader fr = new FileReader (ruta);
        BufferedReader br = new BufferedReader(fr);
        
        String linea;
        ArrayList<String> errores=new ArrayList(); // Lista para almacenar errores
        while ((linea = br.readLine()) != null){
            String [] datos = linea.split(",");//Divide la línea en datos separados por coma
        {                  
            try {   
                try {
                    alta(datos[0], datos[1], datos[2], datos[3], datos[4]);  // Crea un nuevo contacto   
                } catch (IllegalArgumentException ex) {
                    System.err.println(ex);
                }
            }   catch(NumberFormatException ex){
                errores.add(linea + "    error:" + ex); // Guarda errores en la lista
            }
            }
        }
            if (!errores.isEmpty()) throw new ExistenciaTextoErrores(errores);
        br.close();
        fr.close();         
    } 
    
    public ArrayList<String>getNombre(){ 
        ArrayList<String> nombre = dao.getNombres(); // Obtiene los nombres de los contactos
        Collections.sort(nombre); // Ordena alfabéticamente
        return nombre;   
    }
    
    public ArrayList<String>dameContacto(String n) {
        ArrayList<String> retorno = new ArrayList<>(); // Obtiene el contacto por nombre
        Contactos c = dao.getContacto(n);
        if (c instanceof Amigos) { // Identifica el contacto como amigo
            retorno.add("A"); 
            Amigos a = (Amigos) c;
            retorno.add(a.getNombre());
            retorno.add(String.valueOf(a.getTelefono()));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            retorno.add(sdf.format(a.getFechanacimiento()));  // Agrega fecha de nacimiento
            retorno.add("");
        } else if (c instanceof Profesionales) {
            retorno.add("P");  // Identifica el contacto como profesional
            Profesionales p = (Profesionales) c;
            retorno.add(p.getNombre());
            retorno.add(String.valueOf(p.getTelefono()));
            retorno.add("");
            retorno.add(p.getEmpresa());
        }
            return retorno;
    }
}
