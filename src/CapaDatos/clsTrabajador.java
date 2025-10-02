/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;
import java.sql.ResultSet;

/**
 *
 * @author Josselyn
 */
public class clsTrabajador {
    
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    
    public String login(String user, String contra) throws Exception{
        strSQL = "select nombre || ' ' || apepaterno || ' ' || apematerno as nombrescompletos from trabajador where usuario = '" + user + "' and contrasena = '" + contra + "'";
        try{
            rs = objConectar.consultarBD(strSQL);
            while(rs.next()){
                return rs.getString("nombrescompletos");
            }
        }
        catch (Exception e){
            throw new Exception("Error al iniciar sesión. -->" + e.getMessage());
        }
        return "";
    }
    
    public Boolean validarVigencia(String user) throws Exception{
        strSQL = "select estado from trabajador where usuario = '" + user + "'";
        try{
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {                
                return rs.getBoolean("estado");
            }
        }
        catch (Exception e){
            throw new Exception("Error al validar usuario. --> " + e.getMessage());
        }
        return false;
    }
    
    public String preguntaRecuperarContra(String user) throws Exception{
        strSQL = "Select pregunta from trabajador where usuario = '" + user + "'";
        try{
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {                
                return rs.getString("pregunta");
            }
        }
        catch (Exception e){
            throw new Exception("Error al mostrar pregunta. --> " +e.getMessage());
        }
        return "";
    }
    
    public String respuestaRecuperarContra(String user) throws Exception{
        strSQL = "Select respuesta from trabajador where usuario = '" + user + "'";
        try{
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {                
                return rs.getString("respuesta");
            }
        }
        catch (Exception e){
            throw new Exception("Error al cargar respuesta. --> " +e.getMessage());
        }
        return "";
    }
    
    public void nuevaContraseña(String nvaContra, String user) throws Exception{
        strSQL = "update trabajador set contrasena = '" + nvaContra + "' where usuario = '" + user + "'";
        try{
            objConectar.ejecutarBD(strSQL);
        }catch(Exception ex){
            throw new Exception("Error al guardar nueva contraseña" + ex.getMessage());
        }
    }
    
}
