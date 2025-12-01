/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import CapaDatos.clsJDBC;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author Julon
 */
public class clsCliente {

    private int idcliente;
    private String tipocliente;
    private Date fecharegistro;
    private String direccion;
    private String correo;
    private String telefono;
    private int idtipodocumento;
    private int iddistrito;
    private int idrepresentate;

    //***********************************************************
    String strSQL;
    ResultSet rs = null;
    clsJDBC objConectar = new clsJDBC();

    public ResultSet listarClientes() throws Exception {
        strSQL = "select cl.idcliente,tp.tipodocumento from cliente cl inner join tipo_documento tp on tp.idtipodocumento = cl.idtipodocumento";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar Cliente");
        }

    }

    public Integer generarCodigoCliente() throws Exception {
        strSQL = "SELECT COALESCE (max(idcliente),0)+1 as codigo from CLIENTE";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código de cliente");
        }
        return 0;
    }

    public ResultSet buscar(int idcliente) throws Exception {
        strSQL = "Select * from cliente where idcliente= " + idcliente;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs;
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar" + e.getMessage());
        }
        return null;
    }

    public int buscarIdDistrito(String nombre) throws Exception {
        strSQL = "select iddistrito from distrito where nomdistrito ilike '%" + nombre + "%'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("iddistrito");
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar distrito");
        }
        return -1;
    }
    
    public int buscarIdCliente(String nombre) throws Exception {
        strSQL = "SELECT E.idcliente FROM CLIENTE C" +
                 " INNER JOIN EMPRESA E ON E.IDCLIENTE = C.IDCLIENTE \n" +
                 " WHERE E.RAZONSOCIAL = '" + nombre + "';";
        String strSQL1 = "SELECT P.idcliente FROM CLIENTE C" +
                 " INNER JOIN PERSONA P ON P.IDCLIENTE = C.IDCLIENTE \n" +
                 " WHERE P.persona = '" + nombre + "';";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idcliente");
            }
            
            rs = objConectar.consultarBD(strSQL1);
            if (rs.next()) {
                return rs.getInt("idcliente");
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar cliente" + e.getMessage());
        }
        return -1;
    }

    public void registrarCliente(int id, String tipoCliente, Date fechaRegistro, String distrito, int representate) throws Exception {
        strSQL = "insert into cliente values(" + id + ",'" + tipoCliente + "','" + fechaRegistro + "'," + buscarIdDistrito(distrito) + "," + representate + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar Cliente");
        }
    }

    public void modificarCliente(int id, String distrito) throws Exception {
        strSQL = "update cliente set iddistrito=" + buscarIdDistrito(distrito) + " where idcliente=" + id + ";";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar al cliente");
        }

    }

    public void eliminarCliente(int id) throws Exception {
        strSQL = "delete from cliente where idcliente= " + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception();
        }

    }

    public void darDeBajaCliente() throws Exception {
    }

    public ResultSet listarNombreClientes() throws Exception{
        strSQL = """
                    select p.persona as cliente from cliente c inner join persona p on p.idcliente = c.idcliente
                    union all
                    select e.razonsocial from cliente c inner join empresa e on e.idcliente = c.idcliente
                 """;
        try{
            rs = objConectar.consultarBD(strSQL);
            return rs;
        }
        catch(Exception e){
            throw new Exception("Error al listar clientes --> " + e.getMessage());
        }
    }
    
    public String obtenerNumeroDocumento(String nombre) throws Exception{
        strSQL = "select cli.numdocumento as nroDocumento " +
                 "from cliente cli " +
                 "inner join tipo_documento tp on tp.idtipodocumento = cli.idtipodocumento " +
                 "left join persona per on cli.idcliente = per.idcliente " +
                 "left join empresa emp on cli.idcliente = emp.idcliente " +
                 "where per.persona = '" + nombre + "' or emp.razonsocial = '" + nombre + "';";
        String nrodocumento;
        try{
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {                
                nrodocumento = rs.getString("nroDocumento");
                return nrodocumento;
            }
            return "";
        }
        catch (Exception e){
            throw new Exception("Error al obtener numero de documento del cliente -> " + e.getMessage());
        }
    }
           //***********************************************************
    
    public clsCliente() {
    }

    public clsCliente(int idcliente, String tipocliente, Date fecharegistro, String direccion, String correo, String telefono, int idtipodocumento, int iddistrito, int idrepresentate) {
        this.idcliente = idcliente;
        this.tipocliente = tipocliente;
        this.fecharegistro = fecharegistro;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.idtipodocumento = idtipodocumento;
        this.iddistrito = iddistrito;
        this.idrepresentate = idrepresentate;
    }

    public int getIdcliente() {
        return idcliente;
    }
    
    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getTipocliente() {
        return tipocliente;
}

    public void setTipocliente(String tipocliente) {
        this.tipocliente = tipocliente;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdtipodocumento() {
        return idtipodocumento;
    }

    public void setIdtipodocumento(int idtipodocumento) {
        this.idtipodocumento = idtipodocumento;
    }

    public int getIddistrito() {
        return iddistrito;
    }

    public void setIddistrito(int iddistrito) {
        this.iddistrito = iddistrito;
    }

    public int getIdrepresentate() {
        return idrepresentate;
    }

    public void setIdrepresentate(int idrepresentate) {
        this.idrepresentate = idrepresentate;
    }
       
}
