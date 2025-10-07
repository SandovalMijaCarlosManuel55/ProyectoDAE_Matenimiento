/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import CapaDatos.clsJDBC;
import com.sun.jdi.connect.spi.Connection;
import java.beans.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

/**
 *
 * @author laboratorio_computo
 */
public class clsMarca {
   private int idMarca;
   private String nombre;
  
   public clsMarca(){} 

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public clsMarca(int idMarca, String nombre) {
        this.idMarca = idMarca; 
        this.nombre = nombre;
    }
   
    
    //********************************************************************
       clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con = null;
    Statement sent;

    // Listar todas las marcas
    public ResultSet listarMarca() throws Exception {
        strSQL = "SELECT * FROM marca_producto";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    // Buscar marca por ID
    public ResultSet buscarXid(int id) throws Exception {
        strSQL = "SELECT * FROM marca_producto WHERE idmarcaproducto = " + id;
        try {
            rs = objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return rs;
    }
    
    public ResultSet buscaridxNombre(String nombre)throws Exception{
    strSQL = "Select idmarcaproducto from marca_producto where nommarcaproducto ilike '%"+nombre+"%'" ;
        try {
            rs = objConectar.consultarBD(strSQL);
            if(rs.next())return rs;
        } catch (Exception e) {
        throw new Exception("Error al buscar idMarca por en nombre");
        }
    return null;
    }
    // Registrar nueva marca
    public void registrarMarca(int id, String nombre) throws Exception {
        strSQL = "INSERT INTO marca_producto (idmarcaproducto, nommarcaproducto) VALUES (" + id + ", '" + nombre + "')";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Modificar marca existente
    public void modificarMarca(int id, String nombre) throws Exception {
        strSQL = "UPDATE marca_producto SET nommarcaproducto = '" + nombre + "' WHERE idmarcaproducto = " + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Eliminar marca por ID
    public void eliminarMarca(int id) {
        strSQL = "DELETE FROM marca_producto WHERE idmarcaproducto = " + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Generar nuevo código de marca (ID autoincremental)
    public Integer generarCodigoMarca() throws Exception {
        strSQL = "SELECT COALESCE(MAX(idmarcaproducto), 0) + 1 AS codigo FROM marca_producto";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código de marca --> " + e.getMessage());
        }
        return 0;
    }
  
}
