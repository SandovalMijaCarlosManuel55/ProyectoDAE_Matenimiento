/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import CapaDatos.clsJDBC;
import java.sql.ResultSet;

/**
 *
 * @author Usuario
 */
public class clsTipoTrabajador {
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    
    public ResultSet listarTipoTrabajadores() throws Exception {
        strSQL = "select * from tipo_trabajador";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al listar tipo de trabajadores -> " + ex.getMessage());
        }
    }
    
    public int buscarPorNombre(String nombre) throws Exception {
        try {
            objConectar.conectar();

            String strSQL = "SELECT idtipotrabajador "
                    + "FROM tipo_trabajador "
                    + "WHERE tipotrabajador = '" + nombre +"';" ;

            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idtipotrabajador");
            }
            
        } catch (Exception e) {
            throw new Exception("Error al buscar tipo de trabajador por nombre: " + e.getMessage());
        }
        return 0;
    }
    
    public String buscarPorID(int id) throws Exception {
        try {
            objConectar.conectar();

            String strSQL = "SELECT tipotrabajador "
                    + "FROM tipo_trabajador "
                    + "WHERE idtipotrabajador = " + id +";" ;

            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getString("tipotrabajador");
            }       
        } catch (Exception e) {
            throw new Exception("Error al buscar tipo de trabajador por id: " + e.getMessage());
        }
        return "";
    }
    
}
