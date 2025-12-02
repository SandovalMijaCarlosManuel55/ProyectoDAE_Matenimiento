/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package CapaCliente;

import CapaLogica.clsProducto;
import CapaLogica.clsVenta;
import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Josselyn
 */
public class JdSeleccionarProductoVenta extends javax.swing.JDialog {

    clsProducto objProducto = new clsProducto();
    private DefaultTableModel modelo;
    private JdVentas ventas;

    public JdSeleccionarProductoVenta(Frame padre, JdVentas ventas) throws SQLException {
        super(padre, true);
        this.ventas = ventas;
        initComponents();
        listarTipoProducto();
        listarProductosPorTipoProducto();
        cboTipoProducto.setSelectedIndex(-1);
        spnCantidad.setEnabled(false);
    }

    private void listarTipoProducto() throws SQLException {
        try {
            ResultSet rs = objProducto.listarTipoProducto();
            while (rs.next()) {
                cboTipoProducto.addItem(rs.getString("tipoproducto"));
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar tipos de producto", "Sitema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarProductosPorTipoProducto() {
        ResultSet rsProductosTipo = null;
        Vector registro;

        modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Tipo Producto");
        modelo.addColumn("Marca");
        modelo.addColumn("Stock");
        modelo.addColumn("Nombre del Producto");
        modelo.addColumn("Precio");

        try {
            rsProductosTipo = objProducto.listarProductosGeneralesPorTipoProducto();

            while (rsProductosTipo.next()) {
                registro = new Vector();
                double precio = rsProductosTipo.getDouble("precioactual");
                registro.add(0, rsProductosTipo.getInt("idProducto"));
                registro.add(1, rsProductosTipo.getString("tipoproducto"));
                registro.add(2, rsProductosTipo.getString("marcaproducto"));
                registro.add(3, rsProductosTipo.getString("stock"));
                registro.add(4, rsProductosTipo.getString("producto"));
                registro.add(5, precio);
                modelo.addRow(registro);
            }
            tblProductos.setModel(modelo);
            tblProductos.getTableHeader().setReorderingAllowed(false);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(40);
            tblProductos.getColumnModel().getColumn(1).setPreferredWidth(300);
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(40);
            tblProductos.getColumnModel().getColumn(4).setPreferredWidth(300);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(40);
            tblProductos.setRowHeight(22);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void Seleccion() {
        int fila = tblProductos.getSelectedRow();
        int stock = Integer.parseInt(modelo.getValueAt(fila, 3).toString());
        int cantidad = (int) spnCantidad.getValue();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla.", "Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cantidad == 0) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad mayor a 0", "Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cantidad > stock) {
            JOptionPane.showMessageDialog(this, "La cantidad no debe superar el stock", "Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cantidad < 0) {
            JOptionPane.showMessageDialog(this, "La cantidad no debe ser menor de 0", "Sistema", JOptionPane.WARNING_MESSAGE);
        }
        
        try {
            int codigo = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
            String marca = (String) modelo.getValueAt(fila, 2);
            String nombre = (String) modelo.getValueAt(fila, 4);
            double precio = Double.parseDouble(modelo.getValueAt(fila, 5).toString());
            
            ventas.agregarProducto(codigo, nombre, marca, precio, cantidad);
            objProducto.Aumentar_DisminuirStock(cantidad, codigo, "DISMINUIR");
            listarProductosPorTipoProducto();
            int opcion = JOptionPane.showConfirmDialog(this, "Producto añadido con éxito, ¿Desea agregar otro producto?", "Sistema", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                spnCantidad.setValue(0);
                return;
            }
            else if (opcion == JOptionPane.NO_OPTION) {
                dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al seleccionar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        cboTipoProducto = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnSeleccionar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        spnCantidad = new javax.swing.JSpinner();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(31, 41, 55));
        jLabel1.setText("Seleccionar Productos");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(31, 41, 55));
        jLabel5.setText("Tipo de Producto:");

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

        cboTipoProducto.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        btnBuscar.setBackground(new java.awt.Color(31, 41, 55));
        btnBuscar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblProductos.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);

        btnSeleccionar.setBackground(new java.awt.Color(31, 41, 55));
        btnSeleccionar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnSeleccionar.setForeground(new java.awt.Color(255, 255, 255));
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(31, 41, 55));
        jLabel6.setText("Cantidad:");

        spnCantidad.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

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
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSeleccionar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 996, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(cboTipoProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
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

        ResultSet rsProductosTipo = null;
        Vector registro;

        modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Tipo Producto");
        modelo.addColumn("Marca");
        modelo.addColumn("Stock");
        modelo.addColumn("Nombre del Producto");
        modelo.addColumn("Precio");

        try {
            if (cboTipoProducto.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Sistema", "Debe seleccionar un tipo de producto", JOptionPane.INFORMATION_MESSAGE);
            } else {
                rsProductosTipo = objProducto.listarProductosPorTipoProducto(cboTipoProducto.getSelectedItem().toString());

                while (rsProductosTipo.next()) {
                    registro = new Vector();
                    double precio = rsProductosTipo.getDouble("precioactual");
                    registro.add(0, rsProductosTipo.getInt("idProducto"));
                    registro.add(1, rsProductosTipo.getString("tipoproducto"));
                    registro.add(2, rsProductosTipo.getString("marcaproducto"));
                    registro.add(3, rsProductosTipo.getString("stock"));
                    registro.add(4, rsProductosTipo.getString("producto"));
                    registro.add(5, precio);
                    modelo.addRow(registro);
                }
                tblProductos.setModel(modelo);
                tblProductos.getTableHeader().setReorderingAllowed(false);
                tblProductos.getColumnModel().getColumn(0).setPreferredWidth(40);
                tblProductos.getColumnModel().getColumn(1).setPreferredWidth(300);
                tblProductos.getColumnModel().getColumn(2).setPreferredWidth(100);
                tblProductos.getColumnModel().getColumn(3).setPreferredWidth(40);
                tblProductos.getColumnModel().getColumn(4).setPreferredWidth(300);
                tblProductos.getColumnModel().getColumn(5).setPreferredWidth(40);
                tblProductos.setRowHeight(22);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        spnCantidad.setEnabled(true);
    }//GEN-LAST:event_tblProductosMouseClicked

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        Seleccion();
    }//GEN-LAST:event_btnSeleccionarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JComboBox<String> cboTipoProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tblProductos;
    // End of variables declaration//GEN-END:variables
}
