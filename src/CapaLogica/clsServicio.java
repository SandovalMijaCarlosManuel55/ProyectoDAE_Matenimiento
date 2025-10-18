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
public class clsServicio {
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public ResultSet listarServicio() throws Exception {
        strSQL = "select S.*,"
                + "COALESCE(T.precioactual, 00.00) as precioactual, " 
                + "COALESCE(T.duracion, 00.00) as duracion, " 
                + "COALESCE(Tipo.TipoVehiculo, 'No asignado') as tipovehiculo, "
                + "Tipo.idtipovehiculo "
                + "From Servicio S " + 
                 "Left Join Tarifario T on S.idServicio = T.idServicio " + 
                 "Left Join Tipo_Vehiculo Tipo on T.idTipoVehiculo = Tipo.idTipoVehiculo " +
                 "Order by idservicio";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al consultar servicios: " + e.getMessage());

        }
    }

    public Integer generarCodigoServicio() throws Exception {
        strSQL = "Select COALESCE (max(idServicio),0) +1 as codigo from servicio";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código del servicio"+ e.getMessage());
        }
        return 0;
    }

    public void registrar(int idservicio, String nombre, Float precio,Integer tiempoEstimado, int idTipoVehiculo) throws Exception {
        strSQL = "insert into Servicio values (" + idservicio + ", '" + nombre + "' ); " +
                 "insert into Tarifario values (" + idTipoVehiculo +"," + idservicio + "," + precio + "," + tiempoEstimado + ");";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar el servicio -> " + e.getMessage());
        }
    }
    
    public void modificar(int idservicio, String nombre, Float precio, Integer tiempoEstimado, int idTipoVehiculo) throws Exception {        
        strSQL = "update Servicio set servicio = '" + nombre + "' where idServicio=" + idservicio +";"
               + "update Tarifario set precioactual = " 
               + precio + ", duracion = " + tiempoEstimado 
               + " where idServicio=" + idservicio +" and idTipoVehiculo = " + idTipoVehiculo +";";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception ex) {
            throw new Exception("Error al modificar un servicio" + ex.getMessage());
        }
    }

    public ResultSet buscarServicioPorCodigo(Integer cod) throws Exception {
        strSQL = "select S.*,T.PrecioActual,Tipo.TipoVehiculo,T.duracion From Servicio S " + 
                 "Inner Join Tarifario T on S.idServicio = T.idServicio " + 
                 "Inner Join Tipo_Vehiculo Tipo on T.idTipoVehiculo = Tipo.idTipoVehiculo " +
                "Where S.idServicio ="+ cod;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al buscar servicio por codigo" + ex.getMessage());
        }
    }
    
    public ResultSet buscarServicioPorTipo(String tipo) throws Exception {
        strSQL = "select S.*,T.PrecioActual,Tipo.TipoVehiculo,T.duracion From Servicio S " + 
                 "Inner Join Tarifario T on S.idServicio = T.idServicio " + 
                 "Inner Join Tipo_Vehiculo Tipo on T.idTipoVehiculo = Tipo.idTipoVehiculo " +
                 "Where Tipo.TipoVehiculo ='"+ tipo+"'";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al buscar servicio por tipo de vehiculo" + ex.getMessage());
        }
    }
    
    public ResultSet buscarServicioPorTipoYCodigo(String tipo, Integer cod) throws Exception {
        strSQL = "select S.*,T.PrecioActual,Tipo.TipoVehiculo,T.duracion From Servicio S " + 
                 "Inner Join Tarifario T on S.idServicio = T.idServicio " + 
                 "Inner Join Tipo_Vehiculo Tipo on T.idTipoVehiculo = Tipo.idTipoVehiculo " +
                 "Where Tipo.TipoVehiculo ='"+ tipo + "' and S.idServicio = "+cod;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al buscar servicio por codigo y tipo" + ex.getMessage());
        }
    }

    public void eliminarServicio(Integer cod) throws Exception {
        strSQL = "delete from Servicio where idServicio=" + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception ex) {
            throw new Exception("Error al eliminar servicio" + ex.getMessage());
        }
    }
    
    public ResultSet ordenarPor(String columna) throws Exception{        
        switch (columna){
        case "Código":
            columna = "S.idServicio";
            break;
        case "Nombre":
            columna = "S.servicio";
            break;
        case "Precio":
            columna = "T.precioactual";
            break;
        case "Tiempo Estimado":
            columna = "T.duracion";
            break;
        case "Tipo de Vehículo":
            columna = "Tipo.TipoVehiculo";
            break;
        }       
        strSQL = "select S.*,"
                + "COALESCE(T.precioactual, 00.00) as precioactual, " 
                + "COALESCE(T.duracion, 00.00) as duracion, " 
                + "COALESCE(Tipo.TipoVehiculo, 'No asignado') as tipovehiculo "
                + "From Servicio S " + 
                 "Left Join Tarifario T on S.idServicio = T.idServicio " + 
                 "Left Join Tipo_Vehiculo Tipo on T.idTipoVehiculo = Tipo.idTipoVehiculo " +
                 "Order by " + columna;
      
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al ordernar la tabla" + ex.getMessage());
        }
    }
}
