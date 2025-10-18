/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package CapaCliente;

import CapaLogica.clsServicio;
import CapaLogica.clsTipoVehiculo;
import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;


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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Gestionar Servicios");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Busar por código o tipo de vehículo:");

        btnNuevo.setBackground(new java.awt.Color(51, 51, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/anadir.png"))); // NOI18N
        btnNuevo.setText("Nuevo Servicio");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/buscar.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

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

        cboOrdenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nombre", "Precio", "Tiempo Estimado", "Tipo de Vehículo" }));

        jLabel3.setText("Ordenar por:");

        jButton3.setText("Listar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtBuscarServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(cboTipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboOrdenar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(cboTipoVehiculo)
                    .addComponent(txtBuscarServicios)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                    modelo.addRow(registro);
                }} catch (Exception ex){ 
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            tblServicios.setModel(modelo);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void listarServicios() {
        ResultSet rs = null;
        Vector registro;
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Tiempo Estimado");
        modelo.addColumn("Tipo de Vehiculo");
        modelo.addColumn("Modificar"); 
        modelo.addColumn("Eliminar");
        try {
            rs = objServicio.listarServicio();
            while (rs.next()) {
                registro = new Vector();
                registro.add(0, rs.getInt("idServicio"));
                registro.add(1, rs.getString("servicio"));
                registro.add(2, rs.getString("precioActual"));
                registro.add(3, rs.getString("duracion"));
                registro.add(4, rs.getString("tipoVehiculo"));
                modelo.addRow(registro);
            }
            tblServicios.setModel(modelo);

            // Configurar ancho de columnas
            tblServicios.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblServicios.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblServicios.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblServicios.getColumnModel().getColumn(3).setPreferredWidth(120);
            tblServicios.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblServicios.getColumnModel().getColumn(5).setPreferredWidth(80); 
            tblServicios.getColumnModel().getColumn(6).setPreferredWidth(80);

            // Asignar renderizador y editor a las columnas de botones
            ButtonRenderer renderer = new ButtonRenderer();
            ButtonEditor editor = new ButtonEditor(new JCheckBox());

            tblServicios.getColumnModel().getColumn(5).setCellRenderer(renderer);
            tblServicios.getColumnModel().getColumn(5).setCellEditor(editor);
            tblServicios.getColumnModel().getColumn(6).setCellRenderer(renderer);
            tblServicios.getColumnModel().getColumn(6).setCellEditor(editor);

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
    
    class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        if (column == 5) { // Columna "Modificar"
            setText("✏️");
            setBackground(Color.WHITE);
            setForeground(Color.BLUE);
        } else if (column == 6) { // Columna "Eliminar"
            setText("🗑️");
            setBackground(Color.WHITE);
            setForeground(Color.RED);
        }
        
        return this;
    }
}
    
    class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private int row;
    private int column;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        
        this.row = row;
        this.column = column;
        
        if (column == 5) {
            button.setText("✏️");
            button.setForeground(Color.BLUE);
        } else if (column == 6) {
            button.setText("🗑️");
            button.setForeground(Color.RED);
        }
        
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            if (column == 5) {
                try {
                    modificarServicio(row);
                } catch (Exception ex) {
                    Logger.getLogger(JdMantenimientoServicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (column == 6) {
                eliminarServicio(row);
            }
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
    
    public void modificarServicio(int row) throws Exception {
        try{
            DefaultTableModel modelo = (DefaultTableModel) tblServicios.getModel();
            int idServicio = (int) tblServicios.getValueAt(row, 0);
            String tipoVehiculo = (String) tblServicios.getModel().getValueAt(row, 4);
            int idTipoVehiculo = objTipoVehiculo.obtenerCodigoTipoVehiculo(tipoVehiculo);
            JdGestionarServicio obj = new JdGestionarServicio(null, true,idServicio, idTipoVehiculo);
            obj.setVisible(true);  
            modelo.setRowCount(0);
            listarServicios();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Error al modificar datos: " +ex.getMessage());
        }
        
    }

    private void eliminarServicio(int row) {
        
    }
    }
 



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cboOrdenar;
    private javax.swing.JComboBox<String> cboTipoVehiculo;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblServicios;
    private javax.swing.JTextField txtBuscarServicios;
    // End of variables declaration//GEN-END:variables
}
