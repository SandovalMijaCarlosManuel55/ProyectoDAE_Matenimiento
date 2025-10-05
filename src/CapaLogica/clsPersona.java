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
public class clsPersona {
        private int idCliente;
    private String tipoCliente;
    private Date fechaRegistro;
    private int idDistrito;
    private int idRepresentante;
   private String nombre;
   private String apePaterno;
   private String apeMaterno;
   private String tipoDocumento;
   private String numeroDoc;
   private String direccion;
   private String correo;
   private String Telefono;
   private String Sexo;
   
   public clsPersona(){}

    public clsPersona(int idCliente, String tipoCliente, Date fechaRegistro, int idDistrito, int idRepresentante, String nombre, String apePaterno, String apeMaterno, String tipoDocumento, String numeroDoc, String direccion, String correo, String Telefono, String Sexo) {
        this.idCliente = idCliente;
        this.tipoCliente = tipoCliente;
        this.fechaRegistro = fechaRegistro;
        this.idDistrito = idDistrito;
        this.idRepresentante = idRepresentante;
        this.nombre = nombre;
        this.apePaterno = apePaterno;
        this.apeMaterno = apeMaterno;
        this.tipoDocumento = tipoDocumento;
        this.numeroDoc = numeroDoc;
        this.direccion = direccion;
        this.correo = correo;
        this.Telefono = Telefono;
        this.Sexo = Sexo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public int getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(int idRepresentante) {
        this.idRepresentante = idRepresentante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

   //***************************************************************************
String strSQL;
ResultSet rs=null;
clsJDBC objConectar = new clsJDBC();
clsCliente objCliente = new clsCliente();
/*listar persona
idcliente|nombres|tipodocumento|numerodoc|direccion|correo|telefono|sexo|fecharegistro|nomdistrito
*/
public ResultSet listarPersona()throws Exception{
strSQL ="Select cl.idcliente, pe.apepaterno||' '||pe.apematerno||' '||pe.nombre as nombres," +
" pe.tipodocumento,pe.numerodoc,pe.direccion,pe.correo,pe.telefono,pe.sexo,cl.fecharegistro," +
" dis.nomdistrito from persona pe" +
" inner join cliente cl on cl.idcliente = pe.idcliente" +
" inner join distrito dis on dis.iddistrito = cl.iddistrito";
    try {
        rs=objConectar.consultarBD(strSQL);
        return rs;
    } catch (Exception e) {
    throw new Exception("Error al listar Personas");
    }

}

public void generarIdPersona()throws Exception{
    try {
        setIdCliente(objCliente.generarCodigoCliente()); 
    } catch (Exception e) {
        throw new Exception("Error al generar id Persona");
    }
}

public ResultSet buscarPersona(String numeroDocumento)throws Exception{
    strSQL ="Select cl.idcliente, pe.apepaterno||' '||pe.apematerno||' '||pe.nombre as nombres," +
" pe.tipodocumento,pe.numerodoc,pe.direccion,pe.correo,pe.telefono,pe.sexo,cl.fecharegistro," +
" dis.nomdistrito from persona pe" +
" inner join cliente cl on cl.idcliente = pe.idcliente" +
" inner join distrito dis on dis.iddistrito = cl.iddistrito"
        + " where pe.numerodoc= '"+ numeroDocumento+"'";
    try {
        rs=objConectar.consultarBD(strSQL);
        if(rs.next())return rs;
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
) throws Exception{
        try {
      objCliente.registrarCliente(cliente, "NATURAL", fechaRegistro, distrito,cliente);  
    } catch (Exception e) {
        throw new Exception("Error al registrarcliente");
    }
strSQL = "insert into persona values("+
cliente+ " ," +
nombre+" ," +
apepaterno+" ," +
apematerno+"," +
tipodocumento+"," +
numerodoc+"," +
direccion+"," +
correo+", " +
telefono+"," +
sexo+")";

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
)throws Exception{
strSQL="update persona set "
        + " nombre="+nombre+","
        + "apepaterno="+apepaterno+","
        + "apematerno="+apematerno+","
        + "tipodocumento="+tipodocumento+","
        + "numerodoc="+numerodoc+","
        + "direccion="+direccion+","
        + "correo"+correo+","
        + "telefono"+telefono+","
        + "sexo"+sexo+","
        + "where idcliente="+cliente;
    try {
       objConectar.ejecutarBD(strSQL);
    } catch (Exception e) {
   throw new Exception("Error al modificar Persona");
    }
}


public void eliminarPersona(int id)throws Exception{
    strSQL="delete from cliente where idcliente="+id;
    objCliente.eliminarCliente(id);
    try {
       objConectar.ejecutarBD(strSQL);
    } catch (Exception e) {
        throw new Exception("Error al eliminar Persona");
    }
}
}
