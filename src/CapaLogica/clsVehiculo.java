/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import CapaDatos.clsJDBC;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 */
public class clsVehiculo {
    clsJDBC objConectar = new clsJDBC();
    ResultSet rs = null;
    String strSQL;
    
    public ResultSet listarModeloVehiculoPorid(Integer id) throws Exception {
        strSQL = "select * from modelo_vehiculo where idmodelovehiculo = " + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al listar modelos de vehiculos -> " + ex.getMessage());
        }
    }

    
    public ResultSet buscarVehiculoPorPersona(Integer cod) throws Exception {
        strSQL = "SELECT M.modelovehiculo, V.placa " +
                 "FROM VEHICULO V " +
                 "INNER JOIN modelo_vehiculo M ON V.IDMODELOVEHICULO = M.IDMODELOVEHICULO " +
                 "INNER JOIN CLIENTE C ON V.IDCLIENTE = C.IDCLIENTE " +
                 "Where C.IDCLIENTE ="+ cod;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al buscar vehiculos del cliente: " + ex.getMessage());
        }
    }
    
    public ResultSet buscarVehiculoPorPlaca(String placa) throws Exception {
        strSQL = "SELECT * " +
                 "FROM VEHICULO  " +
                 "Where placa = '"+ placa+"';";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al buscar vehiculos por placa: " + ex.getMessage());
        }
    }
    public ResultSet buscarVehiculoxId(int id)throws Exception{
    strSQL="select * from vehiculo where idvehiculo =" +id;
        try {
           rs = objConectar.consultarBD(strSQL);
            if (rs.next())return rs;
            else return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public void eliminarVehiculo(int id) throws Exception{
    strSQL= "delete from vehiculo where idvehiculo ="+id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    
    }
    
    public ResultSet listarVehiculo()throws Exception{
    strSQL="""
           select c.numdocumento,mv.modelovehiculo, v.* from vehiculo v
           inner join modelo_vehiculo mv on mv.idmodelovehiculo = v.idmodelovehiculo
           inner join cliente c on c.idcliente = v.idcliente
           order by 1
           
           """;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public int obtenercod()throws Exception{
    strSQL = """
             select coalesce(max(idvehiculo),0)+1 from vehiculo
             """;
        try {
            rs = objConectar.consultarBD(strSQL);
           if(rs.next()) return rs.getInt(1);
           else return -1;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public void registrar(
    int idvehiculo, String placa,  int anofabricacion,int idmodelovehiculo, int idcliente
    )throws Exception{
    strSQL= "insert into vehiculo values("+idvehiculo+",'"+placa+"',"+anofabricacion+","+idmodelovehiculo+","+idcliente+")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public void Modificar(int id,String placa, int doc, int fabricacion,int modelo)throws Exception{
    strSQL = "update vehiculo set placa= '"+placa+"',anofabricacion = "+fabricacion+",idmodelovehiculo= "+modelo+",idcliente= +"+doc+" where idvehiculo = "+id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public ResultSet buscarPLacaTotal(String placa)throws Exception{
    strSQL ="""
            select c.numdocumento,mv.modelovehiculo, v.* from vehiculo v
            inner join modelo_vehiculo mv on mv.idmodelovehiculo = v.idmodelovehiculo
            inner join cliente c on c.idcliente = v.idcliente
           """
            + " where placa = '"+placa+"'";
        try {
            rs = objConectar.consultarBD(strSQL);
           return rs;
        } catch (Exception e) {
           throw new Exception(e.getMessage());
        }
  
    }
}
