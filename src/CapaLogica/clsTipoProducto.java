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
public class clsTipoProducto {

    private int idTipoProducto;
    private  String tipoProducto;

//******************************************************************************    
    
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con = null;
    Statement sent;

    // Listar todos los tipos de producto
    public ResultSet listarTipoProducto() throws Exception {
        strSQL = "SELECT * FROM tipo_producto order by 1 desc";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    // Buscar tipo de producto por ID
    public ResultSet buscarXid(int id) throws Exception {
        strSQL = "SELECT * FROM tipo_producto WHERE idtipoproducto = " + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            if(rs.next()) return rs;
        } catch (Exception e) {
         throw new Exception("Error al buscar x id"+e.getMessage());
        }
        return rs;
    }
    
    public ResultSet buscarIdxNombre(String nombre)throws Exception{
    strSQL = "Select idTipoProducto from tipo_producto where TipoProducto ilike '%"+nombre+"%'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if(rs.next())return rs;
        } catch (Exception e) {
        throw new Exception("Error al buscar nombre por id");
        }
    return null;
    }
    
    // Registrar nuevo tipo de producto
    public void registrarTipoProducto(int id, String nombre) throws Exception  {
        strSQL = "INSERT INTO tipo_producto (idtipoproducto, tipoproducto) VALUES (" + id + ", '" + nombre + "')";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Modificar tipo de producto
    public void modificarTipoProducto(int id, String nombre) throws Exception {
        strSQL = "UPDATE tipo_producto SET tipoproducto = '" + nombre + "' WHERE idtipoproducto = " + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Eliminar tipo de producto por ID
    public void eliminarTipoProducto(int id) {
        strSQL = "DELETE FROM tipo_producto WHERE idtipoproducto = " + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Generar nuevo ID para tipo de producto
    public Integer generarCodigoTipoProducto() throws Exception {
        strSQL = "SELECT COALESCE(MAX(idtipoproducto), 0) + 1 AS codigo FROM tipo_producto";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código de tipo de producto --> " + e.getMessage());
        }
        return 0;
    }
    /*********************************************************************/
        
    public clsTipoProducto(){}

    public int getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
    
    public clsTipoProducto(int idTipoProducto, String tipoProducto) {
        this.idTipoProducto = idTipoProducto;
        this.tipoProducto = tipoProducto;
        
    }
    

}
