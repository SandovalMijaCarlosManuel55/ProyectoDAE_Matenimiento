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
public class clsTipoVechiculoMarca {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public ResultSet listar() throws Exception {
//idtipovehiculo,idmarcavehiculo,marcavehiculo,tipovehiculo
        strSQL = """ 
             select * from tipo_vehiculo_marca tvm 
             inner join marca_vehiculo vm on vm.idmarcavehiculo = tvm.idmarcavehiculo
             inner join tipo_vehiculo tv  on tv.idtipovehiculo = tvm.idtipovehiculo 
             order by 1
            """;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResultSet buscarxID(int idtipovehiculo, int idmarcavehiculo) throws Exception {
        strSQL = """
          select * from tipo_vehiculo_marca tvm 
          inner join marca_vehiculo vm on vm.idmarcavehiculo = tvm.idmarcavehiculo
          inner join tipo_vehiculo tv  on tv.idtipovehiculo = tvm.idtipovehiculo
          """
                + " where idtipovehiculo = " + idtipovehiculo + " and idmarcavehiculo=" +  idmarcavehiculo 
                + "          order by 1";
        try {
            rs =objConectar.consultarBD(strSQL);
           if(rs.next()) return rs;
           else return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

}
