/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package CapaCliente;

import CapaLogica.clsProducto;
import java.awt.Point;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Josselyn
 */
public class JdMantenimientoProducto extends javax.swing.JDialog {

    clsProducto objProducto = new clsProducto();
    private FrmMenuPrincipal frmP;

    public JdMantenimientoProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        /*  setLocation(347, 131);
        Point posicionFija = getLocation();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                setLocation(posicionFija);
            }
        });*/
        listar("");
    }

    public void listar(String dato) {
        ResultSet rs = null;
        Object[] obj;
        DefaultTableModel mdl = new DefaultTableModel();
        mdl.addColumn("Id");
        mdl.addColumn("Nombre");
        mdl.addColumn("stock");
        mdl.addColumn("vigencia");
        mdl.addColumn("Precio");
        mdl.addColumn("Tipo Producto");
        mdl.addColumn("Marca");

        try {
            rs = objProducto.listarIdNombre(dato);

            while (rs.next()) {
                obj = new Object[7];
                obj[0] = rs.getInt("idproducto");
                obj[1] = rs.getString("producto");
                obj[2] = rs.getInt("stock");
                if (rs.getBoolean("vigencia")) {
                    obj[3]= "Vigente";
                } else {
                    obj[3]= "No Vigente";
                }
                obj[4] = rs.getFloat("precioactual");
                obj[5] = rs.getString("tipoproducto");
                obj[6] = rs.getString("marcaproducto");
                mdl.addRow(obj);
            }
            tblProducto.setModel(mdl);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar \n" + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlProducto = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtbuscador = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        btnGestionarPersona = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("Mantenimiento de Producto");

        txtbuscador.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnGestionarPersona.setBackground(new java.awt.Color(51, 51, 255));
        btnGestionarPersona.setForeground(new java.awt.Color(255, 255, 255));
        btnGestionarPersona.setText("Agregar Producto");
        btnGestionarPersona.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGestionarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionarPersonaActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(51, 51, 255));
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblProducto.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tblProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblProducto);

        jLabel1.setText("M");

        javax.swing.GroupLayout pnlProductoLayout = new javax.swing.GroupLayout(pnlProducto);
        pnlProducto.setLayout(pnlProductoLayout);
        pnlProductoLayout.setHorizontalGroup(
            pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProductoLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlProductoLayout.createSequentialGroup()
                        .addGroup(pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2)
                            .addGroup(pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addGroup(pnlProductoLayout.createSequentialGroup()
                                    .addComponent(txtbuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnGestionarPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(41, Short.MAX_VALUE))))
        );
        pnlProductoLayout.setVerticalGroup(
            pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGestionarPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGestionarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionarPersonaActionPerformed
        JdGestionarProducto obj = new JdGestionarProducto(frmP, true, this);
        obj.setLocationRelativeTo(frmP);
        obj.setVisible(true);
    }//GEN-LAST:event_btnGestionarPersonaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        listar(txtbuscador.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGestionarPersona;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlProducto;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTextField txtbuscador;
    // End of variables declaration//GEN-END:variables
}
