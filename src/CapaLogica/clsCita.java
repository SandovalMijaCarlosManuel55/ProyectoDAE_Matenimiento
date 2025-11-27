/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import CapaDatos.clsJDBC;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.swing.JTable;

/**
 *
 * @author piero
 */
public class clsCita {
    clsJDBC objConectar = new clsJDBC();
    ResultSet rs = null;
    String strSQL;
    clsTipoVehiculo objTV = new clsTipoVehiculo();
    Connection con = null;
    Statement sent;
    public ResultSet listarCitas() throws Exception {
        strSQL = "SELECT C.*, DC.PRECIOVENTA,TV.TIPOVEHICULO, V.PLACA ,S.SERVICIO, T.TRABAJADOR," +
                 "COALESCE(P.PERSONA, E.RAZONSOCIAL) AS CLIENTE_NOMBRE " +
                 "FROM CITA C " +
                 "LEFT JOIN DETALLE_CITA DC ON DC.IDCITA = C.IDCITA " +
                 "LEFT JOIN TIPO_VEHICULO TV ON TV.IDTIPOVEHICULO = DC.IDTIPOVEHICULO " +
                 "LEFT JOIN SERVICIO S ON S.IDSERVICIO = DC.IDSERVICIO " +
                 "LEFT JOIN VEHICULO V ON V.IDVEHICULO = C.IDVEHICULO " +
                 "LEFT JOIN CLIENTE CL ON CL.IDCLIENTE = V.IDCLIENTE " +
                 "LEFT JOIN PERSONA P ON CL.IDCLIENTE = P.IDCLIENTE " +
                 "LEFT JOIN EMPRESA E ON CL.IDCLIENTE = E.IDCLIENTE " +
                 "LEFT JOIN TRABAJADOR T ON T.IDTRABAJADOR = C.IDTRABAJADOR " +
                 "ORDER BY IDCITA";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al consultar citas: " + e.getMessage());

        }
    }
    
    public ResultSet listarCitasPorEstado(String estado) throws Exception {
        strSQL = "SELECT C.*, DC.PRECIOVENTA,TV.TIPOVEHICULO, V.PLACA ,S.SERVICIO, T.TRABAJADOR," +
                 "COALESCE(P.PERSONA, E.RAZONSOCIAL) AS CLIENTE_NOMBRE " +
                 "FROM CITA C " +
                 "LEFT JOIN DETALLE_CITA DC ON DC.IDCITA = C.IDCITA " +
                 "LEFT JOIN TIPO_VEHICULO TV ON TV.IDTIPOVEHICULO = DC.IDTIPOVEHICULO " +
                 "LEFT JOIN SERVICIO S ON S.IDSERVICIO = DC.IDSERVICIO " +
                 "LEFT JOIN VEHICULO V ON V.IDVEHICULO = C.IDVEHICULO " +
                 "LEFT JOIN CLIENTE CL ON CL.IDCLIENTE = V.IDCLIENTE " +
                 "LEFT JOIN PERSONA P ON CL.IDCLIENTE = P.IDCLIENTE " +
                 "LEFT JOIN EMPRESA E ON CL.IDCLIENTE = E.IDCLIENTE " +
                 "LEFT JOIN TRABAJADOR T ON T.IDTRABAJADOR = C.IDTRABAJADOR " +
                 "WHERE C.estado = '" + estado + "' " +
                 "ORDER BY IDCITA";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al consultar citas por estado: " + e.getMessage());

        }
    }

    public Integer generarCodigoCita() throws Exception {
        strSQL = "Select COALESCE (max(idCita),0) +1 as codigo from cita";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código de la cita"+ e.getMessage());
        }
        return 0;
    }

    public void registrar(int cantidad,int idcita, String fecha, String hora,String estado, String comentario, String fechaRecojo, Integer idVehiculo, Integer idTrabajador, JTable servicios) throws Exception {
        try{
            objConectar.conectar();
            con = (Connection) objConectar.getCon();
            con.setAutoCommit(false);
            sent = (Statement) con.createStatement();
            strSQL = "insert into Cita values (" + idcita + ", '" + fecha + "', '" + hora + "', '" +estado + "', '" +  comentario + "', '" +
                                               fechaRecojo + "', " + idVehiculo + ", " + idTrabajador +"); ";

            sent.executeUpdate(strSQL);
            int ctd = servicios.getRowCount();
            for (int i = 0; i < ctd; i++) {
                int id = objTV.obtenerCodigoTipoVehiculo(servicios.getValueAt(i, 4).toString());
            
                String strSQL1 = "insert into Detalle_Cita(precioventa,cantidad,idcita,idtipovehiculo,idservicio) values ("+servicios.getValueAt(i, 2) + ", " +
                        1 + ", " + idcita + ", " + id + ", " + servicios.getValueAt(i, 0)+"); ";
                sent.executeUpdate(strSQL1);
                
            }
            
            con.commit();
        }catch (Exception e) {
            con.rollback();
            throw new Exception("Error al registrar la cita -> " + e.getMessage());
        } finally {
            objConectar.desconectar();
        }    
    }
    
    public void modificar(int idcita, String fecha, String hora,String estado, String comentario, String fechaRecojo, Integer idVehiculo, Integer idTrabajador, JTable servicios) throws Exception {        
        strSQL = "update Cita set fecha = " + fecha + ", hora = " + hora + ", estado = ' " + estado + "', comentario = ' "+ comentario + 
                 "', fechaRecojo = " + fechaRecojo + ", idVehiculo = " + idVehiculo + ", idTrabajador =  "+ idTrabajador +
                 " where idcita=" + idcita +";";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception ex) {
            throw new Exception("Error al modificar la cita" + ex.getMessage());
        }
    }

    public ResultSet buscarCitaPorCodigo(Integer cod) throws Exception {
        strSQL = "SELECT C.*, DC.PRECIOVENTA,TV.TIPOVEHICULO,S.SERVICIO, T.TRABAJADOR, V.placa, CV.tipocomprobante, " +
                 "COALESCE(P.PERSONA, E.RAZONSOCIAL) AS CLIENTE_NOMBRE, COALESCE(P.idCliente,E.idCliente) as idcliente " +
                 "FROM CITA C " +
                 "LEFT JOIN DETALLE_CITA DC ON DC.IDCITA = C.IDCITA " +
                 "LEFT JOIN TIPO_VEHICULO TV ON TV.IDTIPOVEHICULO = DC.IDTIPOVEHICULO " +
                 "LEFT JOIN SERVICIO S ON S.IDSERVICIO = DC.IDSERVICIO " +
                 "LEFT JOIN VEHICULO V ON V.IDVEHICULO = C.IDVEHICULO " +
                 "LEFT JOIN CLIENTE CL ON CL.IDCLIENTE = V.IDCLIENTE " +
                 "LEFT JOIN PERSONA P ON CL.IDCLIENTE = P.IDCLIENTE " +
                 "LEFT JOIN EMPRESA E ON CL.IDCLIENTE = E.IDCLIENTE " +
                 "LEFT JOIN COMPROBANTE_VENTA CV ON C.IDCITA = CV.IDCITA " +
                 "LEFT JOIN TRABAJADOR T ON T.IDTRABAJADOR = C.IDTRABAJADOR " +
                 "Where C.idCita ="+ cod;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al buscar cita por codigo" + ex.getMessage());
        }
    }
    
    public ResultSet buscarServicioPorTipo(String tipo) throws Exception {
        strSQL = "SELECT C.*, DC.PRECIOVENTA,TV.TIPOVEHICULO,S.SERVICIO, T.TRABAJADOR," +
                 "COALESCE(P.PERSONA, E.RAZONSOCIAL) AS CLIENTE_NOMBRE " +
                 "FROM CITA C " +
                 "LEFT JOIN DETALLE_CITA DC ON DC.IDCITA = C.IDCITA " +
                 "LEFT JOIN TIPO_VEHICULO TV ON TV.IDTIPOVEHICULO = DC.IDTIPOVEHICULO " +
                 "LEFT JOIN SERVICIO S ON S.IDSERVICIO = DC.IDSERVICIO " +
                 "LEFT JOIN VEHICULO V ON V.IDVEHICULO = C.IDVEHICULO " +
                 "LEFT JOIN CLIENTE CL ON CL.IDCLIENTE = V.IDCLIENTE " +
                 "LEFT JOIN PERSONA P ON CL.IDCLIENTE = P.IDCLIENTE " +
                 "LEFT JOIN EMPRESA E ON CL.IDCLIENTE = E.IDCLIENTE " +
                 "LEFT JOIN TRABAJADOR T ON T.IDTRABAJADOR = C.IDTRABAJADOR " +
                 "Where T.TipoVehiculo ='"+ tipo+"';";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al buscar cita por tipo de vehiculo" + ex.getMessage());
        }
    }
    
    public ResultSet buscarCitaPorTipoYCodigo(String tipo, Integer cod) throws Exception {
        strSQL = "SELECT C.*, DC.PRECIOVENTA,TV.TIPOVEHICULO,S.SERVICIO, T.TRABAJADOR," +
                 "COALESCE(P.PERSONA, E.RAZONSOCIAL) AS CLIENTE_NOMBRE " +
                 "FROM CITA C " +
                 "LEFT JOIN DETALLE_CITA DC ON DC.IDCITA = C.IDCITA " +
                 "LEFT JOIN TIPO_VEHICULO TV ON TV.IDTIPOVEHICULO = DC.IDTIPOVEHICULO " +
                 "LEFT JOIN SERVICIO S ON S.IDSERVICIO = DC.IDSERVICIO " +
                 "LEFT JOIN VEHICULO V ON V.IDVEHICULO = C.IDVEHICULO " +
                 "LEFT JOIN CLIENTE CL ON CL.IDCLIENTE = V.IDCLIENTE " +
                 "LEFT JOIN PERSONA P ON CL.IDCLIENTE = P.IDCLIENTE " +
                 "LEFT JOIN EMPRESA E ON CL.IDCLIENTE = E.IDCLIENTE " +
                 "LEFT JOIN TRABAJADOR T ON T.IDTRABAJADOR = C.IDTRABAJADOR " +
                 "Where Tipo.TipoVehiculo ='"+ tipo + "' and C.idCita = "+cod;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al buscar cita por codigo y tipo" + ex.getMessage());
        }
    }

    public void eliminarServicio(Integer cod) throws Exception {
        strSQL = "delete from Cita where idCita=" + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception ex) {
            throw new Exception("Error al eliminar cita" + ex.getMessage());
        }
    }
}
