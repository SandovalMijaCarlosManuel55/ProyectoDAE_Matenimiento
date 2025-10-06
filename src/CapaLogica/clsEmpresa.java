/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import CapaDatos.clsJDBC;
import java.sql.ResultSet;

/**
 *
 * @author Julon
 */
public class clsEmpresa {
    
    private int idcliente;
    private String razonsocia;
    private String ruc;
    private String correo;
    private String telefono;
    
    public clsEmpresa(){}

    public clsEmpresa(int idcliente, String razonsocia, String ruc, String correo, String telefono) {
        this.idcliente = idcliente;
        this.razonsocia = razonsocia;
        this.ruc = ruc;
        this.correo = correo;
        this.telefono = telefono;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getRazonsocia() {
        return razonsocia;
    }

    public void setRazonsocia(String razonsocia) {
        this.razonsocia = razonsocia;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
 //******************************************************
    String rstSQL="";
    ResultSet rs =null;
    clsJDBC objConectar = new clsJDBC();
    
    public ResultSet listarEmpresa()throws Exception{
    rstSQL ="Select * from empresa order by 1 ";
        try {
            rs=objConectar.consultarBD(rstSQL);
            return rs;
        } catch (Exception e) {
        throw new Exception("Error al listar La Empresa");
        }
    
    }

    public ResultSet bucarEmpresaRUC(String ruc)throws Exception{
    rstSQL ="Select * from empresa where ruc='"+ruc+"'";
     try {
         rs =objConectar.consultarBD(rstSQL);
        if(rs.next())return rs;
     } catch (Exception e) {
     throw new Exception("Error al buscar Empresa");
     }
     return null;
    }

    public void RegistrarEmpresa()throws Exception{}
    
    public void ModificarEmpresa()throws Exception{}
    
    public void eliminar(int idEmpresa)throws Exception{}
    
}

   