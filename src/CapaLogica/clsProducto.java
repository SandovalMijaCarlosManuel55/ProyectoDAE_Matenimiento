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
    private String producto;
    private int stock;
    private boolean estado;
    private float precioactual;
    private int idMarcaProducto;
    private int idTipoProducto;

    public clsProducto() {
    };

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con = null;
    Statement sent;

    // Listar todos los productos
    public ResultSet listarProducto() throws Exception {
        strSQL = "select p.idproducto,p.producto,p.stock,p.vigencia,p.precioactual,"
                + "tp.tipoproducto,mp.marcaproducto from producto p "
                + "inner join marca_producto mp on mp.idmarcaproducto = p.idmarcaproducto "
                + "inner join  tipo_producto tp on tp.idtipoproducto = p.idtipoproducto "
                + "order by 1";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    // Buscar producto por ID
    //Estructura: idproducto,producto,stock,vigencia,marcaproducto,marcaproducto
    public ResultSet buscarXid(int id) throws Exception {
        strSQL = "select "
                + "pr.idproducto, "
                + "pr.producto, "
                + "pr.stock, "
                + "pr.vigencia, "
                +"pr.precioactual,"
                + "pm.marcaproducto, "
                + "tp.tipoproducto "
                + "from producto pr  "
                + "inner join marca_producto pm on pm.idmarcaproducto = pr.idmarcaproducto "
                + "inner join tipo_producto tp on tp.idtipoproducto = pr.idtipoproducto "
                + "where idproducto = " + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }

    public ResultSet listarIdNombre(String dato) throws Exception {
        String datoS = "*";
        int datoI = 0;
        if (this.EsEntero(dato)) {
            datoI = Integer.parseInt(dato);
        } else {
            datoS = dato;
        }

        strSQL = "select "
                + " pr.idproducto, "
                + " pr.producto, "
                + " pr.stock, "
                + " pr.vigencia, "
                + " pr.precioactual,"
                + " pm.marcaproducto, "
                + " tp.tipoproducto "
                + " from producto pr "
                + " inner join marca_producto pm on pm.idmarcaproducto = pr.idmarcaproducto "
                + " inner join tipo_producto tp on tp.idtipoproducto = pr.idtipoproducto "
                + " where pr.idproducto = " + datoI + "  or pr.producto ilike '%" + datoS + "%'  "
                + " order by 1 ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar por Nombre o id");
        }
    }

    // Registrar nuevo producto
    public void registrarProducto(int id, String nombre, int stock, boolean estado,float precio, int idtipoProducto, int idmarcaproducto) throws Exception {
        strSQL = "INSERT INTO producto VALUES ("
                + id + ", '" + nombre + "', " + stock + ", " + estado + ","+precio+"," +idmarcaproducto + "," +  idtipoProducto + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Modificar un producto existente
    public void modificarProducto(int id, String nombre, int stock, boolean estado,float precio, int idtipoProducto, int idmarcaproducto) throws Exception {
        strSQL = "UPDATE producto SET producto = '" + nombre + "', stock = " + stock
                + ", vigencia = " + estado+ ", precioactual = " + precio
                + ", idmarcaproducto = " + idtipoProducto
                + ", idtipoproducto = " + idmarcaproducto + " WHERE idproducto = " + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Eliminar producto por ID
    public void eliminarProducto(int id) {
       String  strSQL[] = new String [2];
               strSQL[1] = "DELETE FROM producto WHERE idproducto = " + id;
               strSQL[0] = "DELETE FROM detalle_venta WHERE idproducto = " + id;
        try {
            objConectar.ejecutarBDTransacciones(strSQL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
 
// Dar de baja
    public void darbajaProducto(int id) throws Exception{
        strSQL ="update producto set vigencia =false where idproducto = "+id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("\n"+e.getMessage());
        }
    }
    
    // recuperar producto
    public void recuperarProducto(int id) throws Exception{
        strSQL ="update producto set vigencia =true where idproducto = "+id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("\n"+e.getMessage());
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

    public boolean EsEntero(String a) {//Para saber si es numero o letra
        try {
            Integer.parseInt(a);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    //******************************************************************************
    public clsProducto(int idproducto, String producto, int stock, boolean estado, float precioactual, int idMarcaProducto, int idTipoProducto, String strSQL, Statement sent) {
        this.idproducto = idproducto;
        this.producto = producto;
        this.stock = stock;
        this.estado = estado;
        this.precioactual = precioactual;
        this.idMarcaProducto = idMarcaProducto;
        this.idTipoProducto = idTipoProducto;
        this.strSQL = strSQL;
        this.sent = sent;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
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

    public float getPrecioactual() {
        return precioactual;
    }

    public void setPrecioactual(float precioactual) {
        this.precioactual = precioactual;
    }

    public int getIdMarcaProducto() {
        return idMarcaProducto;
    }

    public void setIdMarcaProducto(int idMarcaProducto) {
        this.idMarcaProducto = idMarcaProducto;
    }

    public int getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
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
    
    /*---------------- VENTAS -------------------*/
    
    // Listar los tipos de producto
    public ResultSet listarTipoProducto() throws Exception{
        strSQL = "select tipoproducto from tipo_producto";
        try{
            rs = objConectar.consultarBD(strSQL);
            return rs;
        }
        catch(Exception e){
            throw new Exception("Error al listar productos --> " + e.getMessage());
        }
    }
    
    // Listar los productos por un tipo espécifico de producto
    public ResultSet listarProductosPorTipoProducto(String producto) throws Exception{
        strSQL = "select p.idproducto, p.producto, tp.tipoproducto, mp.marcaproducto, p.precioactual, p.stock " +
                 "from producto p inner join tipo_producto tp on p.idtipoproducto = tp.idtipoproducto " +
                 "inner join marca_producto mp on p.idmarcaproducto = mp.idmarcaproducto where tp.tipoproducto = '" + producto + "' " + 
                 "order by p.idproducto";
        
        try{
            rs = objConectar.consultarBD(strSQL);
            return rs;
        }
        catch (Exception e){
            throw new Exception("Error al listar los productos según el tipo de producto -> " + e.getMessage());
        }
    }
    
    // Listar todos los producros según su tipo de producto
    public ResultSet listarProductosGeneralesPorTipoProducto() throws Exception{
        strSQL = "select p.idproducto, p.producto, tp.tipoproducto, mp.marcaproducto, p.precioactual, p.stock " +
                 "from producto p inner join tipo_producto tp on p.idtipoproducto = tp.idtipoproducto " +
                 "inner join marca_producto mp on p.idmarcaproducto = mp.idmarcaproducto order by p.idproducto";
        
        try{
            rs = objConectar.consultarBD(strSQL);
            return rs;
        }
        catch (Exception e){
            throw new Exception("Error al listar los productos generales según el tipo de producto -> " + e.getMessage());
        }
    }
    
    
    // Disminuir stock cuando se selecciona un producto y aumentar stock cuando se elimina un producto
    public void Aumentar_DisminuirStock(int cantidad, int codProducto, String valor) throws Exception{
        if (valor.equalsIgnoreCase("AUMENTAR")) {
            strSQL = "Update producto set stock = stock + " + cantidad + " where idproducto = " + codProducto;
        }
        else if (valor.equalsIgnoreCase("DISMINUIR")){
            strSQL = "Update producto set stock = stock - " + cantidad + " where idproducto = " + codProducto;
        }
        
        try{
            objConectar.ejecutarBD(strSQL);
        }
        catch (Exception e){
            throw new Exception("Error al aumentar o disminuir stock -> " + e.getMessage());
        }
    }
    

}
