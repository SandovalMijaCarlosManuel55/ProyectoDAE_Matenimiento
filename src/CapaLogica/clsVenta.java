/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import CapaDatos.clsJDBC;
import java.sql.ResultSet;

/**
 *
 * @author Josselyn
 */
public class clsVenta {
    
    clsJDBC objConectar = new clsJDBC();
    ResultSet rs = null;
    String strSQL;
    
    public Integer generarCodigoVenta() throws Exception{
        strSQL = "select coalesce(max(iddetalleventa),0)+1 as codigo from detalle_venta";
        try{
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {                
                return rs.getInt("codigo");
            }
        }
        catch (Exception e){
            throw new Exception("Error al generar código --> " + e.getMessage());
        }
        return 0;
    }
    
}
