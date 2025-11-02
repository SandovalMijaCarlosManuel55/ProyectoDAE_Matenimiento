/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package CapaCliente;

import CapaLogica.clsServicio;
import CapaLogica.clsTipoVehiculo;
import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Josselyn
 */
public class JdSeleccionarServicio extends javax.swing.JDialog {

    clsTipoVehiculo objTipoVehiculo = new clsTipoVehiculo();
    clsServicio objServicio = new clsServicio();
        
    private JdGestionarCitas citas;

    public JdSeleccionarServicio(Frame parent, JdGestionarCitas citas) throws SQLException {
        super(parent, true);
        this.citas = citas;
        initComponents();
        listarTipoVehiculos();
        listarServicio();
        cboTipoVehiculo.setSelectedIndex(-1);
    }

    private void listarTipoVehiculos() throws SQLException {
        ResultSet rs = null;
        DefaultComboBoxModel modeloTV = new DefaultComboBoxModel();
        modeloTV.addElement("Todos");
        try {
            rs = objTipoVehiculo.listarTipoVehiculo();
            while (rs.next()) {
                modeloTV.addElement(rs.getString("tipoVehiculo"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar tipoVehiculos"+ e.getMessage());
        }
        cboTipoVehiculo.setModel(modeloTV);
    }

    private void listarServicio() {
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rsServicioTipo = null;
        Vector registro;     
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Tiempo Estimado");
        modelo.addColumn("Tipo de Vehiculo");
        modelo.addColumn("Estado");

        try {
            rsServicioTipo = objServicio.listarServicio();

            while (rsServicioTipo.next()) {
                registro = new Vector();
                registro.add(0, rsServicioTipo.getInt("idServicio"));
                registro.add(1, rsServicioTipo.getString("servicio"));
                registro.add(2, rsServicioTipo.getFloat("precioActual"));
                registro.add(3, rsServicioTipo.getString("duracion"));
                registro.add(4, rsServicioTipo.getString("tipoVehiculo"));
                registro.add(5, rsServicioTipo.getBoolean("estado"));
                modelo.addRow(registro);
            }
            tblServicios.setModel(modelo);
            tblServicios.getTableHeader().setReorderingAllowed(false);
            tblServicios.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblServicios.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblServicios.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblServicios.getColumnModel().getColumn(3).setPreferredWidth(120);
            tblServicios.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblServicios.getColumnModel().getColumn(5).setPreferredWidth(80);
            tblServicios.setRowHeight(22);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void Seleccion() {
        int fila = tblServicios.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) tblServicios.getModel();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un servicio de la tabla.", "Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            int codigo = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
            String nombre = (String) modelo.getValueAt(fila, 1);
            float precio = Float.parseFloat(modelo.getValueAt(fila, 2).toString());
            int duracion = Integer.parseInt(modelo.getValueAt(fila, 3).toString());
            String tipo = (String) modelo.getValueAt(fila, 4);
            boolean estado = Boolean.parseBoolean(modelo.getValueAt(fila, 5).toString());
            
            citas.agregarServicio(codigo, nombre, precio,duracion , tipo, estado);
            listarServicio();
            int opcion = JOptionPane.showConfirmDialog(this, "Servicio añadido con éxito, ¿Desea agregar otro servicio?", "Sistema", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.NO_OPTION) {
                dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al seleccionar el servicio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cboTipoVehiculo = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblServicios = new javax.swing.JTable();
        btnSeleccionar = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(31, 41, 55));
        jLabel1.setText("Seleccionar Servicios");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(31, 41, 55));
        jLabel5.setText("Tipo de Vehiculo:");

        jPanel2.setBackground(new java.awt.Color(31, 41, 55));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        cboTipoVehiculo.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        btnBuscar.setBackground(new java.awt.Color(31, 41, 55));
        btnBuscar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblServicios.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tblServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblServiciosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblServicios);

        btnSeleccionar.setBackground(new java.awt.Color(31, 41, 55));
        btnSeleccionar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnSeleccionar.setForeground(new java.awt.Color(255, 255, 255));
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnSeleccionar)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 996, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboTipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscar)))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboTipoVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37))
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

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        ResultSet rsServicio = null;
        Vector registro;
        String tipoSeleccionado = (String) cboTipoVehiculo.getSelectedItem();
        DefaultTableModel modelo = (DefaultTableModel) tblServicios.getModel();
        try {
            modelo.setRowCount(0);
            
            
            if (cboTipoVehiculo.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Sistema", "Debe seleccionar un tipo de vehiculo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (tipoSeleccionado.equals("Todos")) {
                    rsServicio = objServicio.listarServicio();
                } else{
                    rsServicio = objServicio.buscarServicioPorTipo(cboTipoVehiculo.getSelectedItem().toString());
                }
                while (rsServicio.next()) {
                    registro = new Vector();
                    registro.add(0, rsServicio.getInt("idServicio"));
                    registro.add(1, rsServicio.getString("servicio"));
                    registro.add(2, rsServicio.getFloat("precioActual"));
                    registro.add(3, rsServicio.getString("duracion"));
                    registro.add(4, rsServicio.getString("tipoVehiculo"));
                    registro.add(5, rsServicio.getBoolean("estado"));
                    modelo.addRow(registro);
                }
                tblServicios.setModel(modelo);
                tblServicios.getTableHeader().setReorderingAllowed(false);
                tblServicios.getColumnModel().getColumn(0).setPreferredWidth(80);
                tblServicios.getColumnModel().getColumn(1).setPreferredWidth(200);
                tblServicios.getColumnModel().getColumn(2).setPreferredWidth(100);
                tblServicios.getColumnModel().getColumn(3).setPreferredWidth(120);
                tblServicios.getColumnModel().getColumn(4).setPreferredWidth(120);
                tblServicios.getColumnModel().getColumn(5).setPreferredWidth(80);
                tblServicios.setRowHeight(22);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblServiciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblServiciosMouseClicked
    }//GEN-LAST:event_tblServiciosMouseClicked

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        Seleccion();
    }//GEN-LAST:event_btnSeleccionarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JComboBox<String> cboTipoVehiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblServicios;
    // End of variables declaration//GEN-END:variables
}
