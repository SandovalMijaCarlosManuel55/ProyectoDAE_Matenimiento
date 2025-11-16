/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import CapaDatos.clsJDBC;
import java.sql.ResultSet;

/**
 *
 * @author piero
 */
public class clsUbigeo {
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    public int buscarIdDistrito(String Distrito, String Provincia, String Departamento) throws Exception{
        try{
            String sql = "SELECT D.*, p.provincia,dep.* FROM distrito d\n" +
                        " inner join provincia p on d.idprovincia = p.idprovincia\n" +
                        " inner join departamento dep on p.iddepartamento = dep.iddepartamento\n" +
                        " where D.distrito = '" + Distrito + "' and p.provincia = '"+Provincia +
                        "' and dep.departamento = '"+Departamento+"';";
            ResultSet rs = objConectar.consultarBD(sql);
            if (rs.next()) {
                return rs.getInt("iddistrito"); 
            }
            return rs.getInt("iddistrito"); 
               
        }catch (Exception e) {
            throw new Exception("Error al obtener el id del distrito -> " + e.getMessage());
        }
    }
    

    public ResultSet ubigeo(int idDistrito) throws Exception{
        try{
            String sql = "SELECT D.*, p.provincia,dep.* FROM distrito d\n" +
                        " inner join provincia p on d.idprovincia = p.idprovincia\n" +
                        " inner join departamento dep on p.iddepartamento = dep.iddepartamento\n" +
                        " where iddistrito = " + idDistrito;
            ResultSet rs = objConectar.consultarBD(sql);
               return rs;
        }catch (Exception e) {
            throw new Exception("Error al obtener el ubigeo -> " + e.getMessage());
        } 
    }
    
    public ResultSet listarDistritos() throws Exception {
        strSQL = "select * from distrito";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al listar distrito -> " + ex.getMessage());
        }
    }
    
    public ResultSet listarDepartamentos() throws Exception {
        strSQL = "select * from departamento";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al listar departamento -> " + ex.getMessage());
        }
    }
    
    public ResultSet listarProvincias() throws Exception {
        strSQL = "select * from provincia";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al listar provincia -> " + ex.getMessage());
        }
    }
    
    public int buscarDepartamento(String dep) throws Exception{
        strSQL = "SELECT * " +
                 "FROM departamento " +
                 "Where departamento ='"+ dep+"';";
        int id = 0;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
               id = rs.getInt("iddepartamento");
            }
        } catch (Exception ex) {
            throw new Exception("Error al buscar departamento: " + ex.getMessage());
        }
        return id;
    }
    
    public ResultSet listarProvinciaPorDep(int dep) throws Exception{
        strSQL = "SELECT P.* " +
                 "FROM provincia P " +
                 "INNER JOIN departamento d ON d.iddepartamento = p.iddepartamento " +
                 "Where d.iddepartamento ="+ dep;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al buscar provincias por departamento: " + ex.getMessage());
        }
    }
    
    public int buscarProvincia(String prov) throws Exception{
        strSQL = "SELECT * " +
                 "FROM provincia " +
                 "Where provincia ='"+ prov+"';";
        int id = 0;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                id = rs.getInt("idprovincia");
            }
        } catch (Exception ex) {
            throw new Exception("Error al buscar provincia: " + ex.getMessage());
        }
        return id;
    }
    
    public ResultSet listarDistritosPorProv(int prov) throws Exception{
        strSQL = "SELECT d.* " +
                 "FROM distrito d " +
                 "INNER JOIN provincia p ON d.idprovincia = p.idprovincia " +
                 "Where p.idprovincia ="+ prov;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al buscar distritos por provincia: " + ex.getMessage());
        }
    }
}
