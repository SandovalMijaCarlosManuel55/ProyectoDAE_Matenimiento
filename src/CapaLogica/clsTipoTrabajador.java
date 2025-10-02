/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

/**
 *
 * @author Usuario
 */
public class clsTipoTrabajador {

    private int idTipoTrabajador;
    private String nombre;

    // Constructor
    public clsTipoTrabajador(int idTipoTrabajador, String nombre) {
        this.idTipoTrabajador = idTipoTrabajador;
        this.nombre = nombre;
    }

    // Getters y setters
    public int getIdTipoTrabajador() {
        return idTipoTrabajador;
    }

    public void setIdTipoTrabajador(int idTipoTrabajador) {
        this.idTipoTrabajador = idTipoTrabajador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
