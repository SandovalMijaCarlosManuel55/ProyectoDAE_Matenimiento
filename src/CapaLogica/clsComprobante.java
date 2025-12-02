/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import CapaDatos.clsJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Josselyn
 */
public class clsComprobante {

    clsJDBC objConectar = new clsJDBC();
    ResultSet rs = null;
    String strSQL, strSQL1;
    Connection con = null;

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
    
    public int obteneridMedioPago(String medioPago) throws Exception{
        strSQL = "select idmediopago from medio_Pago where medioPago = '" + medioPago + "';";
        int idmediopago;
        try{
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {                
                idmediopago = rs.getInt("idmediopago");
                return idmediopago;
            }
            return 0;
        }
        catch(Exception e){
            throw new Exception("Error al obtener id del medio de pago -> " + e.getMessage());
        }
    }
    
    public int obteneridTrabajador(String trabajador) throws Exception{
        strSQL = "select idtrabajador from trabajador where trabajador = '" + trabajador + "';";
        int idtrabajador;
        try{
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {                
                idtrabajador = rs.getInt("idtrabajador");
                return idtrabajador;
            }
            return 0;
        }
        catch(Exception e){
            throw new Exception("Error al obtener id del trabajador -> " + e.getMessage());
        }
    }
    
    public int obteneridCliente(String cliente) throws Exception{
        strSQL = "select cli.idcliente as idcliente\n" +
                 "from cliente cli\n" +
                 "left join persona per on cli.idcliente = per.idcliente\n" +
                 "left join empresa emp on cli.idcliente = emp.idcliente\n" +
                 "where per.persona = '" + cliente + "' or emp.razonsocial = '" + cliente + "';";
        int idcliente;
        try{
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {                
                idcliente = rs.getInt("idcliente");
                return idcliente;
            }
            return 0;
        }
        catch(Exception e){
            throw new Exception("Error al obtener id del cliente -> " + e.getMessage());
        }
    }
    
    public int obteneridProducto(String producto) throws Exception{
        strSQL = "select idproducto from producto where producto = '" + producto + "';";
        int idproducto;
        try{
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {                
                idproducto = rs.getInt("idproducto");
                return idproducto;
            }
            return 0;
        }
        catch(Exception e){
            throw new Exception("Error al obtener id del producto -> " + e.getMessage());
        }
    }
    
    public int obtenerNuevoNumComprobante() throws Exception{
        strSQL = "select coalesce(max(idcomprobante), 0) + 1 as nuevoNumComprobante from comprobante_venta;";
        int idComprobante;
        try{
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {                
                idComprobante = rs.getInt("nuevoNumComprobante");
                return idComprobante;
            }
            return 0;
        }
        catch (Exception e){
            throw new Exception("Error al obtener el nuevo número de comprobante -> " + e.getMessage());
        }
    }
    
    public void transaccion(String fecha, String hora, String numComprobante, String estado, 
            String tipoComprobante, int idCita, String medioPago, String trabajador, String cliente,
            List<Object[]> listaDetalles) throws Exception {
        
        String nuevaFecha = fecha, nuevaHora = hora;
        Boolean estadoBoolean = null;
        int idMedioPago = obteneridMedioPago(medioPago);
        int idTrabajador = obteneridTrabajador(trabajador);
        int idCliente = obteneridCliente(cliente);
        
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(nuevaFecha, formatoFecha);
        // String fechaGuardada = localDate.format(formatoFecha);
        
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.parse(nuevaHora, formatoHora);
        // String horaGuardada = localTime.format(formatoHora);
        
        if (estado.equalsIgnoreCase("Pagado")) {
            estadoBoolean = true;
        }
        else if(estado.equalsIgnoreCase("Pendiente")){
            estadoBoolean = false;
        }
        
        String sqlComprobante = "INSERT INTO comprobante_venta(fechaemision, horaemision, numcomprobante, estado, " +
                                "tipocomprobante, idcita, idmediopago, idtrabajador, idcliente) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        String sqlDetalle = "INSERT INTO detalle_venta(precioventa, cantidad, idcomprobante, idproducto) " +
                            "VALUES (?, ?, ?, ?)";
        
        objConectar.conectar();
        Connection con = objConectar.getCon();
        con.setAutoCommit(false);
        
        try (
            PreparedStatement psComprobante = con.prepareStatement(sqlComprobante, PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement psDetalle = con.prepareStatement(sqlDetalle)
        ) {
            
            psComprobante.setDate(1, java.sql.Date.valueOf(localDate));
            psComprobante.setTime(2, java.sql.Time.valueOf(localTime));
            psComprobante.setString(3, numComprobante);
            psComprobante.setBoolean(4, estadoBoolean);
            psComprobante.setString(5, tipoComprobante);
            psComprobante.setInt(6, idCita);
            psComprobante.setInt(7, idMedioPago);
            psComprobante.setInt(8, idTrabajador);
            psComprobante.setInt(9, idCliente);
            
            psComprobante.executeUpdate(); 

            int idComprobante = obtenerNuevoNumComprobante();
            
            for (Object[] detalle : listaDetalles) {
                String nombreProductoDetalle = (String) detalle[0];
                int cantidadDetalle = (int) detalle[1];
                float valorVentaDetalle = (float) detalle[2];
                
                int idProducto = obteneridProducto(nombreProductoDetalle);
                
                psDetalle.setFloat(1, valorVentaDetalle);
                psDetalle.setInt(2, cantidadDetalle);
                psDetalle.setInt(3, idComprobante);
                psDetalle.setInt(4, idProducto);
                
                psDetalle.addBatch();
            }

            psDetalle.executeBatch(); 
            
            con.commit(); 
            
        } catch (Exception e){
            con.rollback(); 
            throw new Exception("Error al registrar comprobante y detalles -> " + e.getMessage());
        } finally {
            if (con != null) {
                con.setAutoCommit(true); 
                objConectar.desconectar();
            }
        }
    }

}
