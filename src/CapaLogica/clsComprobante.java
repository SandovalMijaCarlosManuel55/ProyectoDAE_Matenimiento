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
public class clsComprobante {

    clsJDBC objConectar = new clsJDBC();
    ResultSet rs = null;
    String strSQL;

    public ResultSet listarMedioPago() throws Exception {
        strSQL = "select mediopago from medio_pago;";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar medios de pago -> " + e.getMessage());
        }
    }

    public String numeroALetras(double valor) {
        if (valor == 0) {
            return "Cero soles con 00/100";
        }

        long entero = (long) valor;
        int centavos = (int) Math.round((valor - entero) * 100);
        if (centavos == 100) {
            entero++;
            centavos = 0;
        }

        String[] unidades = {"", "un", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"};
        String[] decenas = {"", "diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa"};
        String[] especiales = {"once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve"};

        java.util.function.LongFunction<String> convertir = new java.util.function.LongFunction<String>() {
            @Override
            public String apply(long n) {
                if (n == 0) {
                    return "";
                }
                if (n < 10) {
                    return unidades[(int) n];
                }
                if (n < 20) {
                    if (n == 10) {
                        return "diez";
                    }
                    return especiales[(int) n - 11];
                }
                if (n < 100) {
                    int d = (int) (n / 10), u = (int) (n % 10);
                    if (u == 0) {
                        return decenas[d];
                    }
                    if (d == 2) {
                        return "veinti" + unidades[u];
                    }
                    return decenas[d] + " y " + unidades[u];
                }
                if (n < 1000) {
                    int c = (int) (n / 100), r = (int) (n % 100);
                    String cien;
                    cien = switch (c) {
                        case 1 -> (r == 0) ? "cien" : "ciento";
                        case 2 -> "doscientos";
                        case 3 -> "trescientos";
                        case 4 -> "cuatrocientos";
                        case 5 -> "quinientos";
                        case 6 -> "seiscientos";
                        case 7 -> "setecientos";
                        case 8 -> "ochocientos";
                        case 9 -> "novecientos";
                        default -> "";
                    };
                    return r == 0 ? cien : cien + " " + apply(r);
                }
                if (n < 2000) {
                    return "mil " + apply(n - 1000);
                }
                if (n < 1000000) {
                    long mil = n / 1000, resto = n % 1000;
                    String milParte = apply(mil);
                    return resto == 0 ? milParte + " mil" : milParte + " mil " + apply(resto);
                }
                return "monto muy alto";
            }
        };

        String parteEntera = convertir.apply(entero).trim();
        if (!parteEntera.isEmpty()) {
            parteEntera = Character.toUpperCase(parteEntera.charAt(0)) + (parteEntera.length() > 1 ? parteEntera.substring(1) : "");
        } else {
            parteEntera = "Cero";
        }

        return parteEntera + " soles con " + String.format("%02d", centavos) + "/100";
    }
    
    public String mostrarNuevoNumeroComprobante(String tipoComprobante) throws Exception{
        strSQL = "select " + 
                 " split_part(MAX(numcomprobante), '-', 1) || '-' || " +
                 " LPAD((split_part(MAX(numcomprobante), '-', 2)::int + 1)::text, 8, '0') AS siguiente_num_comprobante" +
                 " FROM comprobante_venta" +
                 " WHERE tipocomprobante = '" + tipoComprobante + "';";
        
        String numComprobante;
        try{
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {                
                numComprobante = rs.getString("siguiente_num_comprobante");
                return numComprobante;
            }
            return "";
        }
        catch (Exception e){
            throw new Exception("Error al obtener numero de comprobante -> " + e.getMessage());
        }
    }

}
