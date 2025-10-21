/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import CapaDatos.clsJDBC;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Julon
 */
public class clsPersona extends clsCliente {

    private int idpersona;
    private String persona;
    private String sexo;
    private Date fechanacimiento;

    //***************************************************************************
    String strSQL;
    ResultSet rs = null;
    clsJDBC objConectar = new clsJDBC();
    clsCliente objCliente = new clsCliente();

    /*listar persona
idcliente|nombres|tipodocumento|numerodoc|direccion|correo|telefono|sexo|fecharegistro|nomdistrito
     */
    public ResultSet listarPersona() throws Exception {
        strSQL = "select pe.idcliente,pe.idpersona,pe.persona,pe.sexo,cl.direccion,cl.correo from persona pe " +
" inner join cliente cl on cl.idcliente = pe.idcliente ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar Personas");
        }

    }

    public int generarIdPersona() throws Exception {
        strSQL = "select coalesce(max(idpersona)+1,1) as cant from persona";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("cant");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar id Persona");
        }
        return 0;
    }

    public ResultSet buscarPersona(String numeroDocumento) throws Exception {
        strSQL = "select pe.idcliente,pe.persona,pe.sexo,cl.direccion,cl.correo from persona pe "
                + "inner join cliente cl on cl.idcliente = pe.idcliente "
                + "where persona ilike '%" + numeroDocumento + "%'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs;
            }
        } catch (SQLException e) {
            throw new Exception("Error al buscar Persona");
        }

        return null;
    }

    public void registrarPersona(
            int cliente,
            String nombre,
            String apepaterno,
            String apematerno,
            String tipodocumento,
            String numerodoc,
            String direccion,
            String correo,
            String telefono,
            String sexo,
            Date fechaRegistro,
            String distrito
    ) throws Exception {
        try {
            objCliente.registrarCliente(cliente, "NATURAL", fechaRegistro, distrito, cliente);
        } catch (Exception e) {
            throw new Exception("Error al registrarcliente");
        }
        strSQL = "insert into persona values("
                + cliente + " ,"
                + nombre + " ,"
                + apepaterno + " ,"
                + apematerno + ","
                + tipodocumento + ","
                + numerodoc + ","
                + direccion + ","
                + correo + ", "
                + telefono + ","
                + sexo + ")";

        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("error al registrar cliente");
        }
    }

    public void modificarPersona(
            int cliente,
            String nombre,
            String apepaterno,
            String apematerno,
            String tipodocumento,
            String numerodoc,
            String direccion,
            String correo,
            String telefono,
            String sexo
    ) throws Exception {
        strSQL = "update persona set "
                + " nombre=" + nombre + ","
                + "apepaterno=" + apepaterno + ","
                + "apematerno=" + apematerno + ","
                + "tipodocumento=" + tipodocumento + ","
                + "numerodoc=" + numerodoc + ","
                + "direccion=" + direccion + ","
                + "correo" + correo + ","
                + "telefono" + telefono + ","
                + "sexo" + sexo + ","
                + "where idcliente=" + cliente;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar Persona");
        }
    }

    public void eliminarPersona(int id) throws Exception {
        strSQL = "delete from cliente where idcliente=" + id;
        objCliente.eliminarCliente(id);
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar Persona");
        }
    }
    //***************************************************************************

    public clsPersona() {
    }

    public clsPersona(int idpersona, String persona, String sexo, Date fechanacimiento, int idcliente, String tipocliente, Date fecharegistro, String direccion, String correo, String telefono, int idtipodocumento, int iddistrito, int idrepresentate) {
        super(idcliente, tipocliente, fecharegistro, direccion, correo, telefono, idtipodocumento, iddistrito, idrepresentate);
        this.idpersona = idpersona;
        this.persona = persona;
        this.sexo = sexo;
        this.fechanacimiento = fechanacimiento;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

}
