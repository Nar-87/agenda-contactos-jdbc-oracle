/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Multi
 */
    public class BdContactos {
        
   
    public BdContactos(){
        try {
            DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    
    public Connection abrirConexion(){
       Connection conn=null;
        try {
            conn=DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe",
                "<USUARIO>",
                "<CONTRASEÑA>"
            );
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return conn;
    }
    
    public boolean existe(String nombre){
        Contactos c=getContacto(nombre);
        if (c!=null) return true;
        else return false;
    }
    
    public void insertar(Contactos c) throws SQLException {
        Connection conn=abrirConexion();
        PreparedStatement ps=null;
        try {
            if(c instanceof Amigos){
                ps = conn.prepareStatement("INSERT into AMIGOS  (NOMBRE, TELEFONO, FECHA_CUMPLE) VALUES(?,?,?)");
                ps.setDate(3, new java.sql.Date(((Amigos) c).getFechanacimiento().getTime()));
            }else  if(c instanceof Profesionales){
                ps = conn.prepareStatement("INSERT into PROFESIONALES (NOMBRE, TELEFONO, EMPRESA) VALUES(?,?,?)");
                ps.setString(3,((Profesionales) c).getEmpresa());
            }
            ps.setString(1, c.getNombre());
            ps.setInt(2,c.getTelefono());
            ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            if(ex.getErrorCode()== 12899){
               throw ex;
            }else  {
               System.err.println(ex.getMessage());
            }
        }
    }
    
    //es el get claves
    public Contactos getContacto(String n){
        Contactos retorno=null;
        Connection conn=abrirConexion();
        PreparedStatement ps=null;
        try {
            ps = conn.prepareStatement("SELECT NOMBRE, TELEFONO, FECHA, EMPRESA, TIPO FROM CONTACTOS WHERE NOMBRE=?");
            ps.setString(1, n);
        ResultSet rs=null;
                rs=ps.executeQuery();
            while(rs.next()){
                String nombre=rs.getString(1);
                Integer telefono=rs.getInt(2);
                Date fechaNacimiento=rs.getDate(3);
                String empresa=rs.getString(4);
                String tipo=rs.getString(5);  
                if (tipo.equalsIgnoreCase("A") || tipo.equalsIgnoreCase("Amigos")){
                    retorno=new Amigos(nombre, telefono, fechaNacimiento);
                }else if(tipo.equalsIgnoreCase("P") || tipo.equalsIgnoreCase("Profesionales"))
                    retorno=new Profesionales(nombre, telefono, empresa);
            }
            
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return retorno;
    }
    
    public ArrayList<String> getNombres(){
        ArrayList<String> nombre = new ArrayList<>();
        Connection conn = abrirConexion();

        PreparedStatement ps=null;
        try {
            ps = conn.prepareStatement("SELECT NOMBRE FROM CONTACTOS ORDER BY NOMBRE");
            ResultSet rs= ps.executeQuery();
            while (rs.next()) {
                nombre.add(rs.getString("NOMBRE"));
            }
            
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return nombre;
    }
    
    //el método listar de la agenda (toda la info de los contactos, 
    //listar por fecha, dameDatos)
     public ArrayList<Contactos> getTodos(){
        ArrayList<Contactos> c=new ArrayList<>();
        Connection conn=abrirConexion();
        PreparedStatement ps=null;
        try {
            ps = conn.prepareStatement("SELECT * FROM CONTACTOS");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                String nombre=rs.getString(1);
                Integer telefono=rs.getInt(2);
                Date fechaNacimiento=rs.getDate(3);
                String empresa=rs.getString(4);
                String tipo=rs.getString(5);  
                if (tipo.equals("A")){
                    c.add(new Amigos(nombre, telefono, fechaNacimiento));
                }else if(tipo.equals("P"))
                    c.add(new Profesionales(nombre, telefono, empresa));
            }
            
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return c;
     }
    
    //el modificar de la agenda
    public void actualizar(Contactos c){
        Connection conn = abrirConexion();
        try {
            PreparedStatement ps=null;
        if (c instanceof Amigos) {
                ps = conn.prepareStatement("UPDATE AMIGOS SET TELEFONO = ?, FECHA_CUMPLE = ? WHERE NOMBRE = ?");
                ps.setInt(1, c.getTelefono());
                ps.setDate(2, new java.sql.Date(((Amigos) c).getFechanacimiento().getTime()));
                ps.setString(3, c.getNombre());
                ps.executeUpdate();
        } else if (c instanceof Profesionales) {
                ps = conn.prepareStatement("UPDATE PROFESIONALES SET TELEFONO = ?, EMPRESA = ? WHERE NOMBRE = ?");
                ps.setInt(1, c.getTelefono());
                ps.setString(2, ((Profesionales) c).getEmpresa());
                ps.setString(3, c.getNombre());
                ps.executeUpdate();
        }
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    
   public void eliminar(String nombre){
            PreparedStatement ps=null;
            Connection conn = abrirConexion();
        try {
            Contactos c=getContacto(nombre);
            if (c instanceof Amigos) {
                    ps = conn.prepareStatement("DELETE FROM AMIGOS WHERE NOMBRE=?");
                    ps.setString(1, c.getNombre());
                    
            } else if (c instanceof Profesionales) {
                    ps = conn.prepareStatement("DELETE FROM PROFESIONALES WHERE NOMBRE=?");
                    ps.setString(1, c.getNombre());
                   
            }
                  int n= ps.executeUpdate();
                    ps.executeUpdate();
                ps.close();
                conn.close();
                
            } catch (SQLException ex) {
            System.err.println(ex);
            }
        } 

}
