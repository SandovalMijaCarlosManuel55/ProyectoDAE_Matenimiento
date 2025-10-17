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
public class clsTrabajador {
    
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    
    public String login(String user, String contra) throws Exception{
        strSQL = "select nomtrabajador || ' ' || apepaterno || ' ' || apematerno as nombrescompletos from trabajador where usuario = '" + user + "' and contrasena = '" + contra + "'";
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
    
        private int idTrabajador;
    private String nombre;
    private String apePat;
    private String apeMat;
    private String telefono;
    private String dni;
    private boolean sexo;
    private String correo;
    private String estado;

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApePat() {
        return apePat;
    }

    public void setApePat(String apePat) {
        this.apePat = apePat;
    }

    public String getApeMat() {
        return apeMat;
    }

    public void setApeMat(String apeMat) {
        this.apeMat = apeMat;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ResultSet listarTrabajadores() throws Exception {
        strSQL = "select * from trabajador";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al listar trabajadores -> " + ex.getMessage());
        }
    }
    
    public ResultSet buscarTrabajador (Integer cod) throws Exception {
        strSQL = "Select * from trabajador where idtrabajador=" + cod;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al buscar el trabajador");
        }
    }
    
    
}
