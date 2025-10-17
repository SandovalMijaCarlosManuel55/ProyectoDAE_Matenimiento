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
public class clsCliente {
    
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    
    public ResultSet listarNombreClientes() throws Exception{
        strSQL = """
                    select p.persona as cliente from cliente c inner join persona p on p.idcliente = c.idcliente
                    union all
                    select e.razonsocial from cliente c inner join empresa e on e.idcliente = c.idcliente
                 """;
        try{
            rs = objConectar.consultarBD(strSQL);
            return rs;
        }
        catch(Exception e){
            throw new Exception("Error al listar clientes --> " + e.getMessage());
        }
    }
    
}
