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
public class clsProducto {
private int idproducto;
private String nomproducto;
private int stock;
private  boolean estado;

public clsProducto(){};

    public clsProducto(int idproducto, String nomproducto, int stock, boolean estado, String strSQL, Statement sent) {
        this.idproducto = idproducto;
        this.nomproducto = nomproducto;
        this.stock = stock;
        this.estado = estado;
        this.strSQL = strSQL;
        this.sent = sent;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNomproducto() {
        return nomproducto;
    }

    public void setNomproducto(String nomproducto) {
        this.nomproducto = nomproducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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


    
//******************************************************************************
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con = null;
    Statement sent;

    // Listar todos los productos
    public ResultSet listarProducto() throws Exception {
        strSQL = "select p.idproducto,p.nomproducto,p.stock,p.vigencia,tp.nomtipoproducto,mp.nommarcaproducto from producto p " +
"inner join marca_producto mp on mp.idmarcaproducto = p.idmarcaproducto " +
"inner join  tipo_producto tp on tp.idtipoproducto = p.idtipoproducto " +
"order by 1";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    // Buscar producto por ID
    public ResultSet buscarXid(int id) throws Exception {
        strSQL = "SELECT * FROM producto WHERE idproducto = " + id;
        try {
            rs = objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return rs;
    }

    // Registrar nuevo producto
    public void registrarProducto(int id, String nombre, int stock, boolean estado) throws Exception {
        strSQL = "INSERT INTO producto (idproducto, nomproducto, stock, estado) VALUES (" 
                 + id + ", '" + nombre + "', " + stock + ", " + estado + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Modificar un producto existente
    public void modificarProducto(int id, String nombre, int stock, boolean estado) throws Exception {
        strSQL = "UPDATE producto SET nomproducto = '" + nombre + "', stock = " + stock + 
                 ", estado = " + estado + " WHERE idproducto = " + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Eliminar producto por ID
    public void eliminarProducto(int id) {
        strSQL = "DELETE FROM producto WHERE idproducto = " + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Generar nuevo ID para producto
    public Integer generarCodigoProducto() throws Exception {
        strSQL = "SELECT COALESCE(MAX(idproducto), 0) + 1 AS codigo FROM producto";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código de producto --> " + e.getMessage());
        }
        return 0;
    }
}
