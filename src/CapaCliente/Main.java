/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CapaCliente;

import java.awt.Frame;

/**
 *
 * @author Josselyn
 */
public class Main {

    private static Frame parent;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new JdInicioSesion(parent, true).setVisible(true);
    }
    
}
