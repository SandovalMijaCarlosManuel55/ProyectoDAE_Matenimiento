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

    public String login(String user, String contra) throws Exception {
        strSQL = "select nomtrabajador || ' ' || apepaterno || ' ' || apematerno as nombrescompletos from trabajador where usuario = '" + user + "' and contrasena = '" + contra + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getString("nombrescompletos");
            }
        } catch (Exception e) {
            throw new Exception("Error al iniciar sesión. -->" + e.getMessage());
        }
        return "";
    }

    public Boolean validarVigencia(String user) throws Exception {
        strSQL = "select estado from trabajador where usuario = '" + user + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getBoolean("estado");
            }
        } catch (Exception e) {
            throw new Exception("Error al validar usuario. --> " + e.getMessage());
        }
        return false;
    }

    public String preguntaRecuperarContra(String user) throws Exception {
        strSQL = "Select pregunta from trabajador where usuario = '" + user + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getString("pregunta");
            }
        } catch (Exception e) {
            throw new Exception("Error al mostrar pregunta. --> " + e.getMessage());
        }
        return "";
    }

    public String respuestaRecuperarContra(String user) throws Exception {
        strSQL = "Select respuesta from trabajador where usuario = '" + user + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getString("respuesta");
            }
        } catch (Exception e) {
            throw new Exception("Error al cargar respuesta. --> " + e.getMessage());
        }
        return "";
    }

    public void nuevaContraseña(String nvaContra, String user) throws Exception {
        strSQL = "update trabajador set contrasena = '" + nvaContra + "' where usuario = '" + user + "'";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception ex) {
            throw new Exception("Error al guardar nueva contraseña" + ex.getMessage());
        }
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

    public ResultSet buscarTrabajadorporID(Integer cod) throws Exception {
        strSQL = "Select * from trabajador where idtrabajador=" + cod;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al buscar el trabajador");
        }
    }

    public ResultSet buscarPorNombre(String nombre) throws Exception {
        try {
            objConectar.conectar();

            String strSQL = "SELECT idtrabajador, trabajador, telefono, dni, sexo, correo, estado "
                    + "FROM trabajador "
                    + "WHERE trabajador ILIKE '%" + nombre + "%'";

            rs = objConectar.consultarBD(strSQL);
            return rs;

        } catch (Exception e) {
            throw new Exception("Error al buscar trabajador por nombre: " + e.getMessage());
        }
    }

    public ResultSet ordenarPor(String columna) throws Exception {
        try {
            String campoOrden = "";

            switch (columna) {
                case "Código":
                    campoOrden = "idtrabajador";
                    break;
                case "Nombre":
                    campoOrden = "trabajador";
                    break;
                case "Sexo":
                    campoOrden = "sexo";
                    break;
                case "Estado":
                    campoOrden = "estado";
                    break;
                default:
                    campoOrden = "idtrabajador";
            }

            String strSQL = "SELECT idtrabajador, trabajador, telefono, dni, sexo, correo, estado "
                    + "FROM trabajador "
                    + "ORDER BY " + campoOrden;

            rs = objConectar.consultarBD(strSQL);
            return rs;

        } catch (Exception e) {
            throw new Exception("Error al ordenar trabajadores: " + e.getMessage());
        }
    }

    public boolean eliminarTrabajador(int id) {
        try {
            String sql = "UPDATE TRABAJADOR SET estado = false WHERE idtrabajador = " + id;
            objConectar.ejecutarBD(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void modificar(int idTrabajador, String trabajador, String telefono, String dni, String sexo,
            String correo, String usuario, String contrasena, boolean estado,
            String pregunta, String respuesta, int idTipoTrabajador, int idDistrito) throws Exception {
        try {
            strSQL = "UPDATE trabajador SET "
                    + "trabajador = '" + trabajador + "', "
                    + "telefono = '" + telefono + "', "
                    + "dni = '" + dni + "', "
                    + "sexo = '" + sexo + "', "
                    + "correo = '" + correo + "', "
                    + "usuario = '" + usuario + "', "
                    + "\"contraseña\" = '" + contrasena + "', "
                    + "estado = " + estado + ", "
                    + "pregunta = '" + pregunta + "', "
                    + "respuesta = '" + respuesta + "', "
                    + "idtipotrabajador = " + idTipoTrabajador + ", "
                    + "iddistrito = " + idDistrito + " "
                    + "WHERE idtrabajador = " + idTrabajador;

            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar trabajador: " + e.getMessage());
        }
    }

    public void registrar(int cod, String trabajador, String telefono, String dni, String sexo, String correo,
            String usuario, String contrasena, Boolean estado, String pregunta, String respuesta, int distrito, int tipotrabajador
    ) throws Exception {

        String strSQL = "INSERT INTO trabajador ("
                + "idtrabajador, trabajador, telefono, dni, sexo, correo, usuario, \"contraseña\", estado, "
                + "pregunta, respuesta, idtipotrabajador, iddistrito) VALUES ("
                + cod + ", '"
                + trabajador + "', '"
                + telefono + "', '"
                + dni + "', '"
                + sexo + "', '"
                + correo + "', '"
                + usuario + "', '"
                + contrasena + "', "
                + estado + ", '"
                + pregunta + "', '"
                + respuesta + "', "
                + tipotrabajador + ", "
                + distrito + ")";

        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar el trabajador -> " + e.getMessage());
        }
    }

    public int obtenerSiguienteID() throws Exception {
        String sql = "SELECT COALESCE(MAX(idtrabajador), 0) + 1 AS siguiente_id FROM trabajador";
        try {
            ResultSet rs = objConectar.consultarBD(sql);
            if (rs.next()) {
                return rs.getInt("siguiente_id");
            } else {
                return 1; // si no hay registros aún
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el siguiente ID -> " + e.getMessage());
        }
    }
    
    public int buscarIdDistrito(String Distrito, String Provincia, String Departamento) throws Exception{
        try{
            String sql = "SELECT D.*, p.provincia,dep.* FROM distrito d\n" +
                        " inner join provincia p on d.idprovincia = p.idprovincia\n" +
                        " inner join departamento dep on p.iddepartamento = dep.iddepartamento\n" +
                        " where D.distrito = '" + Distrito + "' and p.provincia = '"+Provincia +
                        "' and dep.departamento = '"+Departamento+"';";
            ResultSet rs = objConectar.consultarBD(sql);
            if (!rs.next()) {
               return rs.getInt("iddistrito"); 
            }
               
        }catch (Exception e) {
            throw new Exception("Error al obtener el id del distrito -> " + e.getMessage());
        }
        return 0;
    }
    

    public ResultSet ubigeo(int idDistrito) throws Exception{
        try{
            String sql = "SELECT D.*, p.provincia,dep.* FROM distrito d\n" +
                        " inner join provincia p on d.idprovincia = p.idprovincia\n" +
                        " inner join departamento dep on p.iddepartamento = dep.iddepartamento\n" +
                        " where iddistrito = " + idDistrito;
            ResultSet rs = objConectar.consultarBD(sql);
               return rs;
        }catch (Exception e) {
            throw new Exception("Error al obtener el ubigeo -> " + e.getMessage());
        } 
    }
    
    public ResultSet listarDistritos() throws Exception {
        strSQL = "select * from distrito";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al listar distrito -> " + ex.getMessage());
        }
    }
    
    public ResultSet listarDepartamentos() throws Exception {
        strSQL = "select * from departamento";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al listar departamento -> " + ex.getMessage());
        }
    }
    
    public ResultSet listarProvincias() throws Exception {
        strSQL = "select * from provincia";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception ex) {
            throw new Exception("Error al listar provincia -> " + ex.getMessage());
        }
    }
    

    
}
