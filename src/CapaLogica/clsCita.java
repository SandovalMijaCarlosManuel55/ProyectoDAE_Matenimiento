/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

/**
 *
 * @author piero
 */
public class clsCita {
    public String categoria, descripcion, precio, duracion, estado;
    String fecha, hora;

    public clsCita( String categoria, String descripcion, String precio, String duracion, String estado) {
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracion = duracion;
        this.estado = estado;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getEstado() {
        return estado;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

}
