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
public class clsTipoVehiculo {
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public ResultSet listarTipoVehiculo() throws Exception {
        strSQL = "select * from tipo_vehiculo";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al listar tipos de vehiculos -> " + ex.getMessage());
        }
    }
    
    
    public Integer generarCodigoTipoVehiculo() throws Exception {
        strSQL = "Select COALESCE(Max(idTipoVehiculo),0)+1 as codigo from tipoVehiculo";

        try {
            rs = objConectar.consultarBD(strSQL);

            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception ex) {
            throw new Exception("Error al generar codigo del tipo de vehiculo -> " + ex.getMessage());
        }
        return 0;
    }

    public void registrar(int cod, String nom) throws Exception {
        strSQL = "insert into tipo_Vehiculo values (" + cod + ", '" + nom + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar el tipo de vehiculo -> " + e.getMessage());
        }
    }

    public ResultSet buscarTipoVehiculo(Integer cod) throws Exception {
        strSQL = "Select * from tipo_Vehiculo where idTipoVehiculo=" + cod;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al buscar el tipo de vehiculo");
        }
    }

    public void eliminarTipoVehiculo(Integer cod) throws Exception {
        strSQL = "delete from tipo_Vehiculo where idTipoVehiculo=" + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception ex) {
            throw new Exception("Error al eliminar el tipo de vehiculo");
        }
    }

    public void modificar(Integer cod, String nom) throws Exception {
        strSQL = "update tipo_Vehiculo set tipoVehiculo = '" + nom + "' where idTipoVehiculo=" + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception ex) {
            throw new Exception("Error al modificar el tipo de vehiculo");
        }
    }

    public Integer obtenerCodigoTipoVehiculo(String nom) throws Exception {
        strSQL = "Select idTipoVehiculo from tipo_Vehiculo where tipoVehiculo ='" + nom + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idTipoVehiculo");
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar el tipo de vehiculo: " + e.getMessage());
        }
        return 0;
    }
}
