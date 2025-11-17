/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package CapaCliente;

import CapaLogica.clsServicio;
import CapaLogica.clsTipoVehiculo;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author piero
 */
public class JdMantenimientoServicio extends javax.swing.JDialog {

    clsServicio objServicio = new clsServicio();
    clsTipoVehiculo objTipoVehiculo = new clsTipoVehiculo();
            
    public JdMantenimientoServicio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        listarTiposVehiculos();
        listarServicios();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        txtBuscarServicios = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblServicios = new javax.swing.JTable();
        cboTipoVehiculo = new javax.swing.JComboBox<>();
        cboOrdenar = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setText("Gestionar Servicios");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel2.setText("Busar por código o tipo de vehículo:");

        btnNuevo.setBackground(new java.awt.Color(31, 41, 55));
        btnNuevo.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("Nuevo Servicio");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        txtBuscarServicios.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        btnBuscar.setBackground(new java.awt.Color(31, 41, 55));
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/buscar.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblServicios.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tblServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblServicios);

        cboTipoVehiculo.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        cboOrdenar.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        cboOrdenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nombre", "Precio", "Tiempo Estimado", "Tipo de Vehículo" }));

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel3.setText("Ordenar por:");

        jButton3.setBackground(new java.awt.Color(31, 41, 55));
        jButton3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Listar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(31, 41, 55));
        jButton1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Eliminar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(31, 41, 55));
        jButton2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(31, 41, 55));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtBuscarServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(cboTipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboOrdenar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboTipoVehiculo)
                            .addComponent(txtBuscarServicios))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(181, 181, 181))))
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

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        JdGestionarServicio obj = new JdGestionarServicio(null, true);
        obj.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
            ResultSet rsServicio = null;
            DefaultTableModel modelo = (DefaultTableModel) tblServicios.getModel();
            Vector registro;

            String textoCodigo = txtBuscarServicios.getText().trim();
            String tipoSeleccionado = (String) cboTipoVehiculo.getSelectedItem();

            try {
                modelo.setRowCount(0);

                if (textoCodigo.equals("") && tipoSeleccionado.equals("Todos")) {
                    rsServicio = objServicio.listarServicio();
                }

                if (!textoCodigo.equals("") && !tipoSeleccionado.equals("Todos")) {
                    int codigo = Integer.parseInt(textoCodigo);
                    rsServicio = objServicio.buscarServicioPorTipoYCodigo(tipoSeleccionado, codigo);
                } else if (!textoCodigo.equals("")) {
                    int codigo = Integer.parseInt(textoCodigo);
                    rsServicio = objServicio.buscarServicioPorCodigo(codigo);
                } else if (!tipoSeleccionado.equals("Todos")) {
                    rsServicio = objServicio.buscarServicioPorTipo(tipoSeleccionado);
                }

                // Mostrar resultados
                boolean encontrado = false;
                while (rsServicio != null && rsServicio.next()) {
                    registro = new Vector();
                    registro.add(0, rsServicio.getInt("idServicio"));
                    registro.add(1, rsServicio.getString("servicio"));
                    registro.add(2, rsServicio.getString("precioActual"));
                    registro.add(3, rsServicio.getString("duracion"));
                    registro.add(4, rsServicio.getString("tipoVehiculo"));
                    registro.add(5, rsServicio.getBoolean("estado"));
                    modelo.addRow(registro);
                    encontrado = true;
                }

                if (!encontrado) {
                    JOptionPane.showMessageDialog(this, "No se encontraron resultados.");
                }

                if (rsServicio != null) rsServicio.close();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al buscar: " + ex.getMessage());
            }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String columna = cboOrdenar.getSelectedItem().toString();
        DefaultTableModel modelo = (DefaultTableModel) tblServicios.getModel();
        Vector registro;     
            try {
                ResultSet rs = objServicio.ordenarPor(columna);
                modelo.setRowCount(0);
                while (rs.next()) {
                    registro = new Vector();
                    registro.add(0, rs.getInt("idServicio"));
                    registro.add(1, rs.getString("servicio"));
                    registro.add(2, rs.getString("precioActual"));
                    registro.add(3, rs.getString("duracion"));
                    registro.add(4, rs.getString("tipoVehiculo"));
                    registro.add(5, rs.getBoolean("estado"));
                    modelo.addRow(registro);
                }} catch (Exception ex){ 
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            tblServicios.setModel(modelo);
            tblServicios.setRowHeight(22);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int selectedRow = tblServicios.getSelectedRow();
    
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para editar.");
            return;
        }

        try {
            // Convertir la fila seleccionada al modelo (por si hay ordenamiento/filtro)
            int modelRow = tblServicios.convertRowIndexToModel(selectedRow);
            DefaultTableModel modelo = (DefaultTableModel) tblServicios.getModel();

            int idServicio = (int) modelo.getValueAt(modelRow, 0);
            String tipoVehiculo = (String) modelo.getValueAt(modelRow, 4);
            int idTipoVehiculo = objTipoVehiculo.obtenerCodigoTipoVehiculo(tipoVehiculo);

            JdGestionarServicio obj = new JdGestionarServicio(null, true, idServicio, idTipoVehiculo);
            obj.setVisible(true);

            // Refrescar la tabla después de editar
            listarServicios();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al modificar datos: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int selectedRow = tblServicios.getSelectedRow();
    
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para eliminar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar este servicio?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            int modelRow = tblServicios.convertRowIndexToModel(selectedRow);
            DefaultTableModel modelo = (DefaultTableModel) tblServicios.getModel();

            int idServicio = (int) modelo.getValueAt(modelRow, 0);

            objServicio.eliminarServicio(idServicio);
            listarServicios(); // Refrescar

            JOptionPane.showMessageDialog(this, "Servicio eliminado correctamente.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void listarServicios() {
        ResultSet rs = null;
        Vector registro;
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Tiempo Estimado");
        modelo.addColumn("Tipo de Vehiculo");
        modelo.addColumn("Estado");
        try {
            rs = objServicio.listarServicio();
            while (rs.next()) {
                registro = new Vector();
                registro.add(0, rs.getInt("idServicio"));
                registro.add(1, rs.getString("servicio"));
                registro.add(2, rs.getString("precioActual"));
                registro.add(3, rs.getString("duracion"));
                registro.add(4, rs.getString("tipoVehiculo"));
                registro.add(5, rs.getBoolean("estado"));
                modelo.addRow(registro);
            }
            tblServicios.setModel(modelo);
            tblServicios.setRowHeight(22);

            // Configurar ancho de columnas
            tblServicios.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblServicios.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblServicios.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblServicios.getColumnModel().getColumn(3).setPreferredWidth(120);
            tblServicios.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblServicios.getColumnModel().getColumn(5).setPreferredWidth(80);
            tblServicios.setDefaultEditor(Object.class, null);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }   
    }
    
    private void listarTiposVehiculos() {
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
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cboOrdenar;
    private javax.swing.JComboBox<String> cboTipoVehiculo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblServicios;
    private javax.swing.JTextField txtBuscarServicios;
    // End of variables declaration//GEN-END:variables
}
