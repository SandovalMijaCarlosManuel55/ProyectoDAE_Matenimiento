/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package CapaCliente;

import CapaLogica.clsTrabajador;
import CapaLogica.clsUbigeo;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author piero
 */
public class JdMantenimientoTrabajador extends javax.swing.JDialog {

    private JTable tabla;
    private DefaultTableModel modelo;
    clsTrabajador objTrabajador = new clsTrabajador();
    FrmMenuPrincipal mnuPrincipal = new FrmMenuPrincipal();
    clsUbigeo objUbigeo = new clsUbigeo();
    
    public JdMantenimientoTrabajador(java.awt.Frame parent, boolean modal) {
        initComponents();
        listaTrabajadores();
    }
    
    private void listaTrabajadores() {
        ResultSet rsTrabajador = null;
        Vector registro;
        String estado = "";
        String sexo = "";
        String distrito ="";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("DNI");
        modelo.addColumn("Sexo");
        modelo.addColumn("Correo");
        modelo.addColumn("Estado");
        modelo.addColumn("Distrito");
        try {
            rsTrabajador = objTrabajador.listarTrabajadores();
            while (rsTrabajador.next()) {
                if (rsTrabajador.getString("estado").equals("t")) {
                    estado = "Activo";
                } else {
                    estado = "No activo";
                }
                
                if (rsTrabajador.getString("sexo").equals("M")) {
                    sexo = "Masculino";
                } else {
                    sexo = "Femenino";
                }
                ResultSet rsDistrito = objUbigeo.ubigeo(rsTrabajador.getInt("iddistrito"));
                while(rsDistrito.next()){
                    distrito = rsDistrito.getString("distrito");
                }
                registro = new Vector();
                registro.add(0, rsTrabajador.getInt("idtrabajador"));
                registro.add(1, rsTrabajador.getString("trabajador"));
                registro.add(2, rsTrabajador.getString("telefono"));
                registro.add(3, rsTrabajador.getString("dni"));
                registro.add(4, sexo);
                registro.add(5, rsTrabajador.getString("correo"));
                registro.add(6, estado);
                registro.add(7, distrito);
                modelo.addRow(registro);
            }
            tblTrabajadores.setModel(modelo);
            tblTrabajadores.setDefaultEditor(Object.class, null);
            tblTrabajadores.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblTrabajadores.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblTrabajadores.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblTrabajadores.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblTrabajadores.getColumnModel().getColumn(4).setPreferredWidth(50);
            tblTrabajadores.getColumnModel().getColumn(5).setPreferredWidth(150);
            tblTrabajadores.getColumnModel().getColumn(6).setPreferredWidth(50);
            tblTrabajadores.setRowHeight(22);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTrabajadores = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Gestión de trabajadores");

        btnBuscar.setBackground(new java.awt.Color(31, 41, 55));
        btnBuscar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Buscar por codigo");

        btnNuevo.setBackground(new java.awt.Color(31, 41, 55));
        btnNuevo.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("   Nuevo trabajador");
        btnNuevo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNuevo.setBorderPainted(false);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        tblTrabajadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTrabajadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTrabajadoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTrabajadores);

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(691, 691, 691))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtBuscar)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        ResultSet rsTrabajador = null;
        DefaultTableModel modelo = (DefaultTableModel) tblTrabajadores.getModel();
        String estado = "";
        String sexo = "";
        String distrito ="";
        Vector registro;

        try {
            if (txtBuscar.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código para buscar");
            } else {
                int codigo = Integer.parseInt(txtBuscar.getText().trim());
                rsTrabajador = objTrabajador.buscarTrabajadorporID(codigo);

                // Limpiar la tabla antes de mostrar resultados
                modelo.setRowCount(0);

                if (rsTrabajador.next()) {
                    if (rsTrabajador.getString("estado").equals("t")) {
                    estado = "Activo";
                    } else {
                        estado = "No activo";
                    }

                    if (rsTrabajador.getString("sexo").equals("M")) {
                        sexo = "Masculino";
                    } else {
                        sexo = "Femenino";
                    }
                    ResultSet rsDistrito = objUbigeo.ubigeo(rsTrabajador.getInt("iddistrito"));
                    while(rsDistrito.next()){
                        distrito = rsDistrito.getString("distrito");
                    }

                    registro = new Vector();
                    registro.add(0, rsTrabajador.getInt("idtrabajador"));
                    registro.add(1, rsTrabajador.getString("trabajador"));
                    registro.add(2, rsTrabajador.getString("telefono"));
                    registro.add(3, rsTrabajador.getString("dni"));
                    registro.add(4, sexo);
                    registro.add(5, rsTrabajador.getString("correo"));
                    registro.add(6, estado);
                    registro.add(7, distrito);

                    modelo.addRow(registro);
                } else {
                    JOptionPane.showMessageDialog(this, "Datos incorrectos");
                }
                rsTrabajador.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        JdGestionarTrabajador obj = new JdGestionarTrabajador( mnuPrincipal, false);
        obj.setLocationRelativeTo( mnuPrincipal);
        obj.setVisible(true);
        
        listaTrabajadores();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void tblTrabajadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTrabajadoresMouseClicked
        int fila = tblTrabajadores.getSelectedRow();
        int idTrabajador= (int) tblTrabajadores.getValueAt(fila, 0);
        JdGestionarTrabajador obj = new JdGestionarTrabajador( mnuPrincipal, false,idTrabajador);
        obj.setLocationRelativeTo( mnuPrincipal);
        obj.setVisible(true);
        
    }//GEN-LAST:event_tblTrabajadoresMouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTable tblTrabajadores;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
