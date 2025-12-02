/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import CapaDatos.clsJDBC;
import java.sql.ResultSet;

/**
 *
 * @author Mercurio5
 */
public class clsModeloVehiculo {
        clsJDBC objConectar = new clsJDBC();
    ResultSet rs = null;
    String strSQL;
   public ResultSet listar () throws Exception{
   strSQL = """
            select  mv.idmodelovehiculo,mv.modelovehiculo,vm.marcavehiculo,tv.tipovehiculo from modelo_vehiculo mv
            inner join tipo_vehiculo_marca tvm on tvm.idtipovehiculo = mv.idtipovehiculo 
            inner join marca_vehiculo vm on vm.idmarcavehiculo = tvm.idmarcavehiculo
            inner join tipo_vehiculo tv  on tv.idtipovehiculo = tvm.idtipovehiculo
            """;
       try {
           rs = objConectar.consultarBD(strSQL);
           return rs;
       } catch (Exception e) {
           throw new Exception(e.getMessage());
       }
   }
   
   public ResultSet buscarxId(int id)throws Exception{
   strSQL = """
            select  mv.idmodelovehiculo,mv.modelovehiculo,vm.marcavehiculo,tv.tipovehiculo from modelo_vehiculo mv
            inner join tipo_vehiculo_marca tvm on tvm.idtipovehiculo = mv.idtipovehiculo 
            inner join marca_vehiculo vm on vm.idmarcavehiculo = tvm.idmarcavehiculo
            inner join tipo_vehiculo tv  on tv.idtipovehiculo = tvm.idtipovehiculo            
            """
           +" where mv.idmodelovehiculo ="+id;
       try {
           rs = objConectar.consultarBD(strSQL);
           if(rs.next()) return rs;
           else  return null;
       } catch (Exception e) {
           throw new Exception(e.getMessage());
       }
   }
   
   public int buscarIdxNombre(String nombre)throws Exception{
   strSQL = """
            select  mv.idmodelovehiculo from modelo_vehiculo mv
            inner join tipo_vehiculo_marca tvm on tvm.idtipovehiculo = mv.idtipovehiculo 
            inner join marca_vehiculo vm on vm.idmarcavehiculo = tvm.idmarcavehiculo
            inner join tipo_vehiculo tv  on tv.idtipovehiculo = tvm.idtipovehiculo 
            """
           + " where mv.modelovehiculo ilike '%"+nombre+"%'";
       try {
           rs = objConectar.consultarBD(strSQL);
           if(rs.next())return rs.getInt(1);
           else return -1;
       } catch (Exception e) {
           throw new Exception(e.getMessage()); 
       }
   
   }
}
