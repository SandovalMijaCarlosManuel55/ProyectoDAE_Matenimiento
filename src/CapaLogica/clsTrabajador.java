/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import CapaDatos.clsJDBC;
import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.ResultSet;

/**
 *
 * @author Usuario
 */
public class clsTrabajador {

    private int idTrabajador;
    private String nombre;
    private String apePat;
    private String apeMat;
    private String telefono;
    private String dni;
    private boolean sexo;
    private String correo;
    private String estado;

    String strSQL;
    ResultSet rs = null;
    clsJDBC objConectar = new clsJDBC();

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

    public ResultSet listarMarcas() throws Exception {
        strSQL = "select * from trabajador";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al listar trabajadores -> " + ex.getMessage());
        }
    }

    public ResultSet buscarTrabajador(Integer cod) throws Exception {
        strSQL = "Select * from trabajador where idtrabajador=" + cod;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al buscar el trabajador");
        }
    }

    public void registrar(int cod, String nombre, String apepaterno, String apematerno,
            String telefono, String dni, String sexo, String correo, Boolean estado,
            String usuario, String contrasena, int distrito, int tipotrabajador,
            String pregunta, String respuesta) throws Exception {

        String strSQL = "INSERT INTO trabajador VALUES ("
                + cod + ", '"
                + nombre + "', '"
                + apepaterno + "', '"
                + apematerno + "', '"
                + telefono + "', '"
                + dni + "', '"
                + sexo + "', '"
                + correo + "', "
                + estado + ", '"
                + usuario + "', '"
                + contrasena + "', "
                + distrito + ", "
                + tipotrabajador + ", '"
                + pregunta + "', '"
                + respuesta + "')";

        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar el trabajador -> " + e.getMessage());
        }
    }

}
