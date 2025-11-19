/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package CapaCliente;

import static CapaCliente.JdVentas.extraerColumnas;
import CapaLogica.clsCita;
import CapaLogica.clsCliente;
import CapaLogica.clsServicio;
import CapaLogica.clsVehiculo;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author 
 */
public class JdGestionarCitas extends javax.swing.JDialog {

    clsCliente objCliente = new clsCliente();
    clsVehiculo objVehiculo = new clsVehiculo();
    clsCita objCita = new clsCita();
    clsServicio objServicio = new clsServicio();
    Boolean nuevo = true;
    private DefaultTableModel modelo;

    public JdGestionarCitas(java.awt.Frame parent, boolean modal) throws Exception {
        super(parent, modal);
        initComponents();
        mostrarFechaLarga();
        listarClientes();
        mostrarFechaCorta();
        hora();
        limpiarDatos();
        nuevo = true;
        cboVehiculo.setEnabled(false);
        columnasTabla();
    }
    
    public JdGestionarCitas(java.awt.Frame parent, boolean modal, int idcita) throws Exception {
        super(parent, modal);
        initComponents();
        limpiarDatos();
        nuevo = false;
        cboVehiculo.setEnabled(false);
        columnasTabla();
        cargarDatos(idcita);
    }

    private void limpiarDatos() {
        cboClientes.setSelectedIndex(-1);
        cboTipoComprobante.setSelectedIndex(-1);
        txtComentario.setText("");
        txtFecha.setText("");
    }

    private void mostrarFechaLarga() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        lblFecha.setText(fechaActual.format(formatter));
    }

    private void mostrarFechaCorta() {
        LocalDate fecha = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("es", "ES"));
        lblFechaCorta.setText(fecha.format(formato));
    }

    private void hora() {
        Timer timer = new Timer(1000, (ActionEvent e) -> {
            LocalTime ahora = LocalTime.now();
            String horaFormateada = ahora.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            lblHora.setText(horaFormateada);
        });
        timer.start();
    }

    private void listarClientes() throws Exception {
        try {
            ResultSet rs = objCliente.listarNombreClientes();
            while (rs.next()) {
                String cliente = rs.getString("cliente");
                cboClientes.addItem(cliente);
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar clientes -> " + e.getMessage(), "Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarDatos(int idcita){
        try{
        listarClientes();

        ResultSet rsCita = objCita.buscarCitaPorCodigo(idcita); 

        if (rsCita.next()) {
            // Recuperamos los datos de la cita
            String nombreCliente = rsCita.getString("CLIENTE_NOMBRE"); // <- Asumiendo nombre de columna
            String placaVehiculo = rsCita.getString("placa"); // <- Asumiendo nombre de columna
            String tipoComprobante = rsCita.getString("tipocomprobante"); // <- Asumiendo nombre de columna
            String comentario = rsCita.getString("comentario"); // <- Asumiendo nombre de columna
            java.sql.Date fechaCita = rsCita.getDate("fecha"); // <- Asumiendo nombre de columna

            cboClientes.setSelectedItem(nombreCliente);
            cboTipoComprobante.setSelectedItem(tipoComprobante);
            txtComentario.setText(comentario);

            // Formatear y setear la fecha (si 'txtFecha' es un JTextField)
            if (fechaCita != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                txtFecha.setText(sdf.format(fechaCita));
            }
            // Si txtFecha es un JDateChooser, usarías:
            // jdcFecha.setDate(fechaCita);


            // --- 2. Cargar Vehículos del Cliente ---
            
            // Debemos cargar los vehículos de ese cliente manualmente
            cboVehiculo.setEnabled(true); // Lo activamos
            cboVehiculo.removeAllItems(); // Limpiamos ítems por si acaso

            ResultSet rsVehiculos = objVehiculo.buscarVehiculoPorPersona(rsCita.getInt("idcliente"));
            
            while (rsVehiculos.next()) {
                cboVehiculo.addItem(rsVehiculos.getString("PLACA")); // <- Asumiendo nombre de columna
            }
            rsVehiculos.close();

            cboVehiculo.setSelectedItem(placaVehiculo);
        }
        rsCita.close();

        ResultSet rsServicios = objServicio.listarServiciosPorCita(idcita);

        modelo.setRowCount(0); 

        while (rsServicios.next()) {
            int codigo = rsServicios.getInt("idservicio"); 
            String nombre = rsServicios.getString("servicio"); 
            float precio = rsServicios.getFloat("precioventa"); 
            int duracion = rsServicios.getInt("tiempoestimado"); 
            String tipo = rsServicios.getString("tipovehiculo"); 
            boolean estado = rsServicios.getBoolean("estado"); 

            agregarServicio(codigo, nombre, precio, duracion, tipo, estado);
        }
        rsServicios.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al cargar datos de la cita: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace(); // Muy útil para ver el error en consola
    }
    }
    
    private void columnasTabla() {
        modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Tiempo Estimado");
        modelo.addColumn("Tipo de Vehiculo");
        modelo.addColumn("Estado");

        tblDetalle.setModel(modelo);
        tblDetalle.getColumnModel().getColumn(0).setPreferredWidth(80);
        tblDetalle.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblDetalle.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblDetalle.getColumnModel().getColumn(3).setPreferredWidth(120);
        tblDetalle.getColumnModel().getColumn(4).setPreferredWidth(120);
        tblDetalle.getColumnModel().getColumn(5).setPreferredWidth(80);
        tblDetalle.setRowHeight(22);
    }
    
    public void agregarServicio(int codigo, String nombre, float precio, int duracion, String tipo, boolean estado) {
        modelo.addRow(new Object[]{codigo, nombre, precio, duracion, tipo, estado});
        actualizarTotal();
    }

    private double calcularTotal() {
        double total = 0.0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object valorPrecio = modelo.getValueAt(i, 2);
            if (valorPrecio != null) {
                total += Double.parseDouble(valorPrecio.toString());
            }
        }
        return total;
    }

    private void actualizarTotal() {
        double total = calcularTotal();
        lblTotal.setText(String.format("S/. %.2f", total));
    }

    private void Eliminar() {
        int fila = tblDetalle.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un servicio de la tabla.", "Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este servicio?", "Sistema", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            modelo.removeRow(fila);
            actualizarTotal();
            JOptionPane.showMessageDialog(this, "Servicio eliminado con éxito", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el servicio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Object[][] extraerColumnas(JTable tablaOrigen, int[] indicesColumnas) {
        TableModel modelo = tablaOrigen.getModel();
        int filas = modelo.getRowCount();
        Object[][] resultado = new Object[filas][indicesColumnas.length];

        for (int j = 0; j < indicesColumnas.length; j++) {
            int col = indicesColumnas[j];
            for (int i = 0; i < filas; i++) {
                resultado[i][j] = modelo.getValueAt(i, col);
            }
        }
        return resultado;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboClientes = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblFechaCorta = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cboTipoComprobante = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtComentario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        lbltotal = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        cboVehiculo = new javax.swing.JComboBox<>();
        txtFecha = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(31, 41, 55));
        jLabel1.setText("Gestión de Citas");

        lblFecha.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(31, 41, 55));

        jPanel2.setBackground(new java.awt.Color(31, 41, 55));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 951, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(31, 41, 55));
        jLabel2.setText("CLIENTE:");

        cboClientes.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        cboClientes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboClientesItemStateChanged(evt);
            }
        });
        cboClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboClientesMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(31, 41, 55));
        jLabel3.setText("VEHICULO:");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(31, 41, 55));
        jLabel5.setText("FECHA DE RECOJO:");

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(31, 41, 55));
        jLabel7.setText("FECHA:");

        lblFechaCorta.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblFechaCorta.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        lblHora.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblHora.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(31, 41, 55));
        jLabel10.setText("HORA:");

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(31, 41, 55));
        jLabel8.setText("TIPO DE COMPROBANTE:");

        cboTipoComprobante.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        cboTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Boleta", "Factura" }));

        jButton1.setBackground(new java.awt.Color(31, 41, 55));
        jButton1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Agregar Servicio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblDetalle);

        jPanel3.setBackground(new java.awt.Color(31, 41, 55));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jButton2.setBackground(new java.awt.Color(31, 41, 55));
        jButton2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Generar Cita");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(31, 41, 55));
        jLabel6.setText("COMENTARIO:");

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(31, 41, 55));
        jLabel9.setText("TOTAL:");

        lbltotal.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        lbltotal.setForeground(new java.awt.Color(31, 41, 55));

        jButton3.setBackground(new java.awt.Color(31, 41, 55));
        jButton3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Eliminar Servicio");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cboVehiculo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboVehiculoItemStateChanged(evt);
            }
        });

        lblTotal.setText("...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(819, Short.MAX_VALUE)
                .addComponent(lbltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboClientes, 0, 270, Short.MAX_VALUE)
                                    .addComponent(cboVehiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(cboTipoComprobante, 0, 160, Short.MAX_VALUE)))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFechaCorta, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtFecha))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(484, 484, 484)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                            .addComponent(txtComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1)
                                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(348, 348, 348)
                        .addComponent(jButton1)
                        .addGap(60, 60, 60)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(406, 406, 406)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaCorta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(65, 65, 65)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotal)))
                    .addComponent(lbltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboClientesMouseClicked
        
    }//GEN-LAST:event_cboClientesMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            JdSeleccionarServicio objSeleccionar =  new JdSeleccionarServicio(null, this);
            objSeleccionar.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(JdGestionarCitas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            Integer id = objCita.generarCodigoCita();
            String cliente = cboClientes.getSelectedItem().toString();
            String tipoComprobante = cboTipoComprobante.getSelectedItem().toString();
            String fecha = lblFechaCorta.getText();
            String hora = lblHora.getText();
            String estado = "pendiente";
            String comentario = txtComentario.getText();
            String fechaRecojo = txtFecha.getText();
            String valor = cboVehiculo.getSelectedItem().toString();
            String placaVehiculo = valor.substring(Math.max(0, valor.length() - 7));
            Integer idVehiculo = 0;
            ResultSet rsVehiculo = objVehiculo.buscarVehiculoPorPlaca(placaVehiculo); 
            while(rsVehiculo.next()){
                    idVehiculo = rsVehiculo.getInt("idvehiculo");
                }
            Integer idTrabajador = 1;
            int filas = tblDetalle.getRowCount();
                
            if (txtFecha.equals("") || cboClientes.getSelectedItem().toString().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos obligatorios");
            }else{
                if (tblDetalle.getRowCount() < 1) {
                    JOptionPane.showMessageDialog(this, "Seleccione almenos un servicio");
                }else{
                    if(nuevo == true){
                        objCita.registrar(filas, id, fecha, hora, estado, comentario, fechaRecojo, idVehiculo, idTrabajador, tblDetalle);
                        }else{
                            objCita.modificar(id, fecha, hora, estado, comentario, fechaRecojo, idVehiculo, idTrabajador, tblDetalle);
                        }
                        int[] columnasAPasar = {1,2, 3};
                        Object[][] datosFiltrados = extraerColumnas(this.tblDetalle, columnasAPasar);
                        String[] encabezados = {"Servicio", "Precio", "Tipo de Vehiculo"};
                        JdComprobanteVenta dialogDestino = new JdComprobanteVenta(this, true, datosFiltrados, encabezados, cliente, fecha, id, tipoComprobante, true, cboVehiculo.getSelectedItem().toString());
                        dialogDestino.setVisible(true);
                }
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar datos: " +ex.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Eliminar();
    }//GEN-LAST:event_jButton3ActionPerformed

    
    
    private void cboVehiculoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboVehiculoItemStateChanged
        
    }//GEN-LAST:event_cboVehiculoItemStateChanged

    private void cboClientesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboClientesItemStateChanged
        if (cboClientes.getSelectedItem() != null) {
        cboVehiculo.setEnabled(true);  
        try {
            cboVehiculo.removeAllItems();
            String nombre = cboClientes.getSelectedItem().toString();
            int id = objCliente.buscarIdCliente(nombre);
            ResultSet rs = objVehiculo.buscarVehiculoPorPersona(id);
            while (rs.next()) {
                String cliente = rs.getString("modelovehiculo") + " - " + rs.getString("placa");
                cboVehiculo.addItem(cliente);
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar vehiculos -> " + e.getMessage(), "Sistema", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        cboVehiculo.setEnabled(false); 
    }
    }//GEN-LAST:event_cboClientesItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboClientes;
    private javax.swing.JComboBox<String> cboTipoComprobante;
    private javax.swing.JComboBox<String> cboVehiculo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFechaCorta;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lbltotal;
    private javax.swing.JTable tblDetalle;
    private javax.swing.JTextField txtComentario;
    private javax.swing.JTextField txtFecha;
    // End of variables declaration//GEN-END:variables
}