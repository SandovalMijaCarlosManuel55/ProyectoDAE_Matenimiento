/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import CapaDatos.clsJDBC;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author Julon
 */
public class clsEmpresa extends clsCliente{
    
    private int idempresa;
    private String razonsocia;
    
    public clsEmpresa(){}


 //******************************************************
    String rstSQL="";
    ResultSet rs =null;
    clsJDBC objConectar = new clsJDBC();
    String [] strSQLGrupo;
    
    public ResultSet listarEmpresa()throws Exception{
    rstSQL ="select  * from empresa e inner join cliente c on c.idcliente = e.idcliente order by 1 ";
        try {
            rs=objConectar.consultarBD(rstSQL);
            return rs;
        } catch (Exception e) {
        throw new Exception("Error al listar La Empresa");
        }
    
    }

    public ResultSet bucarEmpresaRUC(String ruc)throws Exception{
    rstSQL ="Select * from empresa where idempresa='"+ruc+"'";
     try {
         rs =objConectar.consultarBD(rstSQL);
        if(rs.next())return rs;
     } catch (Exception e) {
     throw new Exception("Error al buscar Empresa");
     }
     return null;
    }

    public void RegistrarEmpresa(
    int idcliente,
    String direccion,
    String correo,
    String telefono,
    int idDistrito,
    int idrepresentante,
    int idempresa,
    String razon
    )throws Exception{
    strSQLGrupo =new String[2];
    strSQLGrupo[0] = "insert into cliente values("+idcliente+",'EMPRESA',current_date,'"+
            direccion+"','"+correo+"','"+telefono+"',2,"+idDistrito+","+idrepresentante+");";
    strSQLGrupo[1]="insert into empresa values("+idempresa+","+razon+","+idcliente+")";
        try {
            objConectar.ejecutarBDTransacciones(strSQLGrupo);
        } catch (Exception e) {
            throw new Exception("Error al Registrar Empresa");
        }
    }
    
    public void ModificarEmpresa(
        int idcliente,
    String direccion,
    String correo,
    String telefono,
    int idDistrito,
    int idrepresentante,
   int idempresa,
    String razon
    )throws Exception{
        strSQLGrupo =new String[2];
    strSQLGrupo[0] ="update cliente set "+
            " direccion = '"+direccion+"',"+
            " correo = '"+correo+"',"+
            " telefono = '"+telefono+"',"+
            " iddistrito ="+idDistrito+","+
            " idrepresentante ="+idrepresentante+
            " where idcliente = "+idcliente+";";
    strSQLGrupo[1]=" update  empresa set "+
            " razon = '"+razon+"',"+
            " where insert into empresa values("+idempresa+","+razon+","+idcliente+")";
    }
    
    public void eliminar(int idEmpresa)throws Exception{
        strSQL = "delete from empresa where idempresa="+ idEmpresa;
        try {
         objConectar.ejecutarBD(strSQL);   
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
     //******************************************************

    public clsEmpresa(int idempresa, String razonsocia, int idcliente, String tipocliente, Date fecharegistro, String direccion, String correo, String telefono, int idtipodocumento, int iddistrito, int idrepresentate) {
        super(idcliente, tipocliente, fecharegistro, direccion, correo, telefono, idtipodocumento, iddistrito, idrepresentate);
        this.idempresa = idempresa;
        this.razonsocia = razonsocia;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public String getRazonsocia() {
        return razonsocia;
    }

    public void setRazonsocia(String razonsocia) {
        this.razonsocia = razonsocia;
    }
    
}

   