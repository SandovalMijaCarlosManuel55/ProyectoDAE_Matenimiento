/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import CapaDatos.clsJDBC;
import java.sql.ResultSet;
/**
 *
 * @author Julon
 */
public class clsUbicacion {
    private String departamento;
    private String provincia;
    private String distrito;
    
    ResultSet rs = null;
    clsJDBC objConectar = new clsJDBC();
    String strSQL="";
    
    public ResultSet listarDepartamento()throws Exception{
    strSQL = "Select * from departamento";
        try {
           rs = objConectar.consultarBD(strSQL);
           return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar Departamentos");
            
        }
    }
    
    public ResultSet listarDistrito(int idpro)throws Exception{
    strSQL ="select  * from distrito where idprovincia="+idpro;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar Distrito");
        }
    
    }
    
    public ResultSet listarProvincia(int idDepartamento)throws Exception{
    strSQL = "select * from provincia where iddepartamento= "+idDepartamento;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Erro al listar Provincias");
        }   
    }
    
    public int buscarIDxDepartamento(String nombre)throws Exception{
    strSQL ="Select * from departamento where departamento ilike  '"+nombre+"';";
        try {
            rs = objConectar.consultarBD(strSQL);
            if(rs.next()) return rs.getInt("iddepartamento");
        } catch (Exception e) {
          throw new Exception("Erroral buscar id x Departamento");
        }
    return -1;
    }
   
   public int buscarIdXProvincia(String nombre)throws Exception{
   strSQL = "Select * from provincia where provincia ilike '"+nombre+"';";
       try {
           rs = objConectar.consultarBD(strSQL);
           if(rs.next()) return rs.getInt("idprovincia");
       } catch (Exception e) {
          throw new Exception("Erro al buscar id x Provincia");
       }
       return -1;
   }
   
   public int buscarIdxDistrito(String distr)throws Exception{
   strSQL ="select * from distrito where distrito ilike '"+distr +"';";
       try {
           rs = objConectar.consultarBD(strSQL);
          if(rs.next()) return rs.getInt("iddistrito");
       } catch (Exception e) {
           throw new Exception("Erro al buscar id x distrito");
       }
       return -1;
   }
   
}
