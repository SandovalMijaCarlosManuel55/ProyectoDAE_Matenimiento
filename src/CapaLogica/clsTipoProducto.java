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
    private  String nomTipoProducto;
    
    public clsTipoProducto(){}

    public int getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getNomTipoProducto() {
        return nomTipoProducto;
    }

    public void setNomTipoProducto(String nomTipoProducto) {
        this.nomTipoProducto = nomTipoProducto;
    }

    public clsJDBC getObjConectar() {
        return objConectar;
    }

    public void setObjConectar(clsJDBC objConectar) {
        this.objConectar = objConectar;
    }

    public String getStrSQL() {
        return strSQL;
    }

    public void setStrSQL(String strSQL) {
        this.strSQL = strSQL;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public Statement getSent() {
        return sent;
    }

    public void setSent(Statement sent) {
        this.sent = sent;
    }

    public clsTipoProducto(int idTipoProducto, String nomTipoProducto, String strSQL, Statement sent) {
        this.idTipoProducto = idTipoProducto;
        this.nomTipoProducto = nomTipoProducto;
        this.strSQL = strSQL;
        this.sent = sent;
    }
    

//******************************************************************************    
    
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con = null;
    Statement sent;

    // Listar todos los tipos de producto
    public ResultSet listarTipoProducto() throws Exception {
        strSQL = "SELECT * FROM tipo_producto";
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return rs;
    }
    
    public ResultSet buscarIdxNombre(String nombre)throws Exception{
    strSQL = "Select idTipoProducto from tipo_producto where nomTipoProducto ilike '%"+nombre+"%'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if(rs.next())return rs;
        } catch (Exception e) {
        throw new Exception("Error al buscar nombre por id");
        }
    return null;
    }
    
    // Registrar nuevo tipo de producto
    public void registrarTipoProducto(int id, String nombre) throws Exception {
        strSQL = "INSERT INTO tipo_producto (idtipoproducto, nomtipoproducto) VALUES (" + id + ", '" + nombre + "')";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Modificar tipo de producto
    public void modificarTipoProducto(int id, String nombre) throws Exception {
        strSQL = "UPDATE tipo_producto SET nomtipoproducto = '" + nombre + "' WHERE idtipoproducto = " + id;
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
}
