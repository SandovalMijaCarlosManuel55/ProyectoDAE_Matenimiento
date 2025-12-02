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
                + "COALESCE(T.tiempoestimado, 00.00) as duracion, " 
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
        strSQL = "update Servicio set servicio = '" + nombre + "' where idServicio=" + idservicio +"; "
               + "update Tarifario set precioactual = " 
               + precio + ", tiempoestimado = " + tiempoEstimado 
               + " where idServicio=" + idservicio +" and idTipoVehiculo = " + idTipoVehiculo +";";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception ex) {
            throw new Exception("Error al modificar un servicio" + ex.getMessage());
        }
    }

    public ResultSet buscarServicioPorCodigo(Integer cod) throws Exception {
        strSQL = "select S.*,T.PrecioActual,Tipo.TipoVehiculo,T.tiempoestimado as duracion From Servicio S " + 
                 "LEFT Join Tarifario T on S.idServicio = T.idServicio " + 
                 "LEFT Join Tipo_Vehiculo Tipo on T.idTipoVehiculo = Tipo.idTipoVehiculo " +
                "Where S.idServicio ="+ cod;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al buscar servicio por codigo" + ex.getMessage());
        }
    }
    
    public ResultSet buscarServicioPorTipo(String tipo) throws Exception {
        strSQL = "select S.*,T.PrecioActual,Tipo.TipoVehiculo,T.tiempoestimado as duracion From Servicio S " + 
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
        strSQL = "select S.*,T.PrecioActual,Tipo.TipoVehiculo,T.tiempoestimado as duracion From Servicio S " + 
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
        strSQL = "update Servicio set estado = false where idServicio=" + cod + ";";
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
            columna = "T.tiempoestimado";
            break;
        case "Tipo de Vehículo":
            columna = "Tipo.TipoVehiculo";
            break;
        }       
        strSQL = "select S.*,"
                + "COALESCE(T.precioactual, 00.00) as precioactual, " 
                + "COALESCE(T.tiempoestimado, 00.00) as duracion, " 
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
    
    public ResultSet listarServiciosPorCita(int idCita) throws Exception{
        strSQL = "select S.*,tv.tipovehiculo, DC.precioventa,T.tiempoestimado From cita C " + 
                 "Inner Join Detalle_cita DC on DC.idcita = C.idcita " + 
                 "Inner Join Servicio S on S.idservicio = DC.idservicio " +
                 "Inner Join Tarifario T on S.idServicio = T.idServicio " +
                 "Inner Join tipo_vehiculo TV on T.idtipovehiculo = TV.idtipovehiculo " +
                 "Where DC.idcita ="+ idCita + " and DC.idtipovehiculo = T.idtipovehiculo";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al buscar servicio por codigo y tipo" + ex.getMessage());
        }
    }
}
