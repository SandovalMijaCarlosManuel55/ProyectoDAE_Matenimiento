/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import CapaDatos.clsJDBC;
import java.sql.ResultSet;

/**
 *
 * @author SandovalCarlos
 */
public class clsMarcaVehiculo {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    
    public ResultSet listar()throws Exception{
        strSQL = "select * from marca_vehiculo order by 1";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public ResultSet buscarxId(int id)throws Exception{
    strSQL = "select * from marca_vehiculo where idmarcavehiculo="+id;
        try {
            rs = objConectar.consultarBD(strSQL);
            if(rs.next()) return rs;
            else return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public int buscarIdxNombre(String nombre) throws Exception{
    strSQL = "select idmarcavehiculo from marca_vehiculo where marcavehiculo = '"+nombre+"'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if(rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            throw new  Exception(e.getMessage());
        }
        return -1;
    }
    
}
