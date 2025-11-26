/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import CapaDatos.clsJDBC;
import java.sql.ResultSet;

/**
 *
 * @author 
 */
public class clsVehiculo {
    clsJDBC objConectar = new clsJDBC();
    ResultSet rs = null;
    String strSQL;
    
    public ResultSet listarModeloVehiculoPorid(Integer id) throws Exception {
        strSQL = "select * from modelo_vehiculo where idmodelovehiculo = " + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al listar modelos de vehiculos -> " + ex.getMessage());
        }
    }

    
    public ResultSet buscarVehiculoPorPersona(Integer cod) throws Exception {
        strSQL = "SELECT M.modelovehiculo, V.placa " +
                 "FROM VEHICULO V " +
                 "INNER JOIN modelo_vehiculo M ON V.IDMODELOVEHICULO = M.IDMODELOVEHICULO " +
                 "INNER JOIN CLIENTE C ON V.IDCLIENTE = C.IDCLIENTE " +
                 "Where C.IDCLIENTE ="+ cod;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al buscar vehiculos del cliente: " + ex.getMessage());
        }
    }
    
    public ResultSet buscarVehiculoPorPlaca(String placa) throws Exception {
        strSQL = "SELECT * " +
                 "FROM VEHICULO  " +
                 "Where placa = '"+ placa+"';";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al buscar vehiculos por placa: " + ex.getMessage());
        }
    }
    
    
}
