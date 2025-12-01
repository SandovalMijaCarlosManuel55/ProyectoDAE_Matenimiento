/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Josselyn
 */
public class clsJDBC {

    private String driver, url, user, password;
    private Connection con;
    private Statement sent = null;

    public clsJDBC() {
        this.driver = "org.postgresql.Driver";
        this.url = "jdbc:postgresql://localhost:5432/BD_DAE_SistemaMantenimientoVehiculos";
        this.user = "UsuarioDAE";
        this.password = "daepass";
        this.con = null;
    }
    
    public Connection getCon() {
        return con;
    }

    public void conectar() throws Exception {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new Exception("Error al conectar a la BD! " + ex.getMessage());
        }
    }

    public void desconectar() throws Exception {
        try {
            con.close();
        } catch (SQLException ex) {
            throw new Exception("Error al desconectar de la BD! " + ex.getMessage());
        }
    }

    public ResultSet consultarBD(String strSQL) throws Exception {
        ResultSet rs = null;
        try {
            conectar();
            sent = con.createStatement();
            rs = sent.executeQuery(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al ejecutar consulta ->" + e.getMessage());
        } finally {
            if (con != null) {
                desconectar();
            }
        }
    }

    public void ejecutarBD(String strSQL) throws Exception {
        try {
            conectar();
            sent = con.createStatement();
            sent.execute(strSQL);
        } catch (Exception ex) {
            throw new Exception("Error al ejecutar BD -> " + ex.getMessage());
        } finally {
            if (con != null) {
                desconectar();
            }
        }
    }

    public void ejecutarBDTransacciones(String[] str) throws Exception {

        if (str.length < 1) {
            return;
        }
        try {
            conectar();
            con.setAutoCommit(false);
            sent = con.createStatement();
            for (int i = 0; i < str.length; i++) {
                sent.executeUpdate(str[i]);
            }
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al ejecutar Transaccion " + e.getMessage());
        } finally {
            if (con != null) {
                desconectar();
            }
        }
    }
}
