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
    private int idCliente;
    private String tipoCliente;
    private Date fechaRegistro;
    private int idDistrito;
    private int idRepresentante;
    
    public clsCliente(){}

    public clsCliente(int idCliente, String tipoCliente, Date fechaRegistro, int idDistrito, int idRepresentante) {
        this.idCliente = idCliente;
        this.tipoCliente = tipoCliente;
        this.fechaRegistro = fechaRegistro;
        this.idDistrito = idDistrito;
        this.idRepresentante = idRepresentante;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public int getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(int idRepresentante) {
        this.idRepresentante = idRepresentante;
    }
    
    //***********************************************************
    
    String strSQL;
    ResultSet rs = null;
    clsJDBC objConectar = new clsJDBC();
    
    public ResultSet listarClientes()throws Exception{
       strSQL ="Select *Select cl.idcliente,cl.tipocliente,cl.fecharegistro,di.nomdistrito from cliente cl inner join distrito di on di.iddistrito = cl.iddistrito order by 1";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
        throw new Exception("Error al listar Clliente");
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
        
    public ResultSet buscar(int idcliente)throws Exception {
    strSQL = "Select * from cliente where idcliente= "+idcliente;
        try {
            rs=objConectar.consultarBD(strSQL);
            if(rs.next())return rs;
        } catch (Exception e) {
       throw new Exception("Error al buscar"+e.getMessage());
        }
    return null;
    }
    
    public int buscarIdDistrito(String nombre)throws Exception{
    strSQL ="select iddistrito from distrito where nomdistrito ilike '%"+nombre+"%'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if(rs.next()) return rs.getInt("iddistrito");
        } catch (Exception e) {
        throw new Exception("Error al buscar distrito");
        }
    return -1;
    }
    
    public void registrarCliente(int id, String tipoCliente, Date fechaRegistro, String distrito, int representate )throws Exception{
    strSQL = "insert into cliente values("+id+",'"+tipoCliente+"','"+fechaRegistro+"',"+buscarIdDistrito(distrito)+","+representate+")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar Cliente");
        }
    }
    
    public void modificarCliente(int id,String distrito)throws Exception{
    strSQL="update cliente set idDistrito="+buscarIdDistrito(distrito)+" where idcliente="+id+";";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar al cliente");
        }
    
    }
    
    
    public void eliminarCliente (int id)throws Exception{
    strSQL = "delete from cliente where idcliente= "+ id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception();
        }
    
    }
    
   public void darDeBajaCliente()throws Exception{
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
}
