/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package CapaCliente;


import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import CapaLogica.clsTrabajador;
import java.awt.GridLayout;
import java.awt.Point;
/**
 *
 * @author Mercurio5
 */
public class IfMantenimientoTrabajador extends javax.swing.JInternalFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    clsTrabajador objTrabajador = new clsTrabajador();
    FrmMenuPrincipal mnuPrincipal = new FrmMenuPrincipal();
    
    public IfMantenimientoTrabajador() {
        initComponents();
        listarMarcas();
    }
   private void listarMarcas() {
        ResultSet rsTrabajador = null;
        Vector registro;
        String estado = "";

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Telefono");
        modelo.addColumn("DNI");
        modelo.addColumn("Sexo");
        modelo.addColumn("Correo");
        modelo.addColumn("Estado");
        try {
            rsTrabajador = objTrabajador.listarMarcas();
            while (rsTrabajador.next()) {
                if (rsTrabajador.getString("estado").equals("t")) {
                    estado = "Activo";
                } else {
                    estado = "No activo";
                }
                registro = new Vector();
                registro.add(0, rsTrabajador.getInt("idtrabajador"));
                registro.add(1, rsTrabajador.getString("nombre"));
                registro.add(2, rsTrabajador.getString("apepaterno"));
                registro.add(3, rsTrabajador.getString("telefono"));
                registro.add(4, rsTrabajador.getString("dni"));
                registro.add(5, rsTrabajador.getString("sexo"));
                registro.add(6, rsTrabajador.getString("correo"));
                registro.add(7, estado);
                modelo.addRow(registro);
            }
            tblTrabajadores.setModel(modelo);
            tblTrabajadores.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblTrabajadores.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblTrabajadores.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblTrabajadores.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblTrabajadores.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblTrabajadores.getColumnModel().getColumn(5).setPreferredWidth(100);
            tblTrabajadores.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblTrabajadores.getColumnModel().getColumn(7).setPreferredWidth(100);
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

        pnlPrincipal.setBackground(new java.awt.Color(223, 218, 214));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Gestión de trabajadores:");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/buscar.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel2.setText("Buscar por codigo");

        btnNuevo.setBackground(new java.awt.Color(51, 51, 255));
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/anadir.png"))); // NOI18N
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                                .addComponent(txtBuscar)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
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
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtBuscar)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        Vector registro;

        try {
            if (txtBuscar.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código para buscar");
            } else {
                int codigo = Integer.parseInt(txtBuscar.getText().trim());
                rsTrabajador = objTrabajador.buscarTrabajador(codigo);

                // Limpiar la tabla antes de mostrar resultados
                modelo.setRowCount(0);

                if (rsTrabajador.next()) {
                    // Traducir estado
                    if (rsTrabajador.getString("estado").equalsIgnoreCase("t")) {
                        estado = "Activo";
                    } else {
                        estado = "No activo";
                    }

                    registro = new Vector();
                    registro.add(0, rsTrabajador.getInt("idtrabajador"));
                    registro.add(1, rsTrabajador.getString("nombre"));
                    registro.add(2, rsTrabajador.getString("apepaterno"));
                    registro.add(3, rsTrabajador.getString("telefono"));
                    registro.add(4, rsTrabajador.getString("dni"));
                    registro.add(5, rsTrabajador.getString("sexo"));
                    registro.add(6, rsTrabajador.getString("correo"));
                    registro.add(7, estado);

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
    }//GEN-LAST:event_btnNuevoActionPerformed


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
