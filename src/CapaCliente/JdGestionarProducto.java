/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package CapaCliente;

import CapaLogica.clsMarca;
import CapaLogica.clsProducto;
import CapaLogica.clsTipoProducto;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.Comparator;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Sandoval_Carlos
 */
public class JdGestionarProducto extends javax.swing.JDialog {

    private FrmMenuPrincipal frmP;
    private JdMantenimientoProducto padre;
    clsProducto objProducto = new clsProducto();
    clsMarca objMarca = new clsMarca();
    clsTipoProducto objTipoProducto = new clsTipoProducto();

    public JdGestionarProducto(java.awt.Frame parent, boolean modal, JdMantenimientoProducto padre) {
        super(parent, modal);
        initComponents();
        this.padre = padre;
        listar("");
        listarcbo();

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
                if (rs.getBoolean("vigencia")) {

                    obj = new Object[7];
                    obj[0] = rs.getInt("idproducto");
                    obj[1] = rs.getString("producto");
                    obj[2] = rs.getInt("stock");
                    if (rs.getBoolean("vigencia")) {
                        obj[3] = "Vigente";
                    }
                    obj[4] = rs.getFloat("precioactual");
                    obj[5] = rs.getString("tipoproducto");
                    obj[6] = rs.getString("marcaproducto");
                    mdl.addRow(obj);
                }
                tblProducto.setModel(mdl);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar \n" + e.getMessage());
        }
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(mdl);
        Comparator<Object> numericComparator = (a, b) -> {
            try {
                Double n1 = Double.parseDouble(a.toString().trim());
                Double n2 = Double.parseDouble(b.toString().trim());
                return n1.compareTo(n2);
            } catch (Exception e) {
                return a.toString().compareTo(b.toString());
            }
        };

        sorter.setComparator(0, numericComparator); // ID
        sorter.setComparator(2, numericComparator); // STOCK
        sorter.setComparator(4, numericComparator); // PRECIO

        tblProducto.setRowSorter(sorter);
    }

    public void listarcbo() {
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {
            rs1 = objMarca.listarMarca();
            rs2 = objTipoProducto.listarTipoProducto();

            while (rs1.next()) {
                cboMarcaProducto.addItem(rs1.getString("marcaproducto"));
            }
            while (rs2.next()) {
                cboTipoProducto.addItem(rs2.getString("tipoproducto"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar marca y/o TipoProducto\n" + e);
        }

    }

    public void limpiar() {

        cboMarcaProducto.setSelectedIndex(-1);
        cboTipoProducto.setSelectedIndex(-1);
        chkVigencia.setSelected(true);
        txtId.setText("");
        spnStock.setValue(0);
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        spnStock = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cboTipoProducto = new javax.swing.JComboBox<>();
        cboMarcaProducto = new javax.swing.JComboBox<>();
        chkVigencia = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnTipoProducto = new javax.swing.JButton();
        btnMarca = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnDarsebaja = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel3.setText("Mantenimiento de Producto");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel1.setText("id:");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        btnBuscar.setBackground(new java.awt.Color(31, 41, 55));
        btnBuscar.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        spnStock.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel5.setText("Stock:");

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel7.setText("Tipo de Producto:");

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel8.setText("Marca:");

        chkVigencia.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        chkVigencia.setText("Vigencia");

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel6.setText("Precio:");

        btnTipoProducto.setBackground(new java.awt.Color(31, 41, 55));
        btnTipoProducto.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnTipoProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnTipoProducto.setText("...");
        btnTipoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTipoProductoActionPerformed(evt);
            }
        });

        btnMarca.setBackground(new java.awt.Color(31, 41, 55));
        btnMarca.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnMarca.setForeground(new java.awt.Color(255, 255, 255));
        btnMarca.setText("...");
        btnMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnStock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chkVigencia)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboMarcaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMarca)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkVigencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cboMarcaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTipoProducto)
                    .addComponent(btnMarca))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblProducto.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnNuevo.setBackground(new java.awt.Color(31, 41, 55));
        btnNuevo.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(31, 41, 55));
        btnModificar.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(31, 41, 55));
        btnEliminar.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnDarsebaja.setBackground(new java.awt.Color(31, 41, 55));
        btnDarsebaja.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnDarsebaja.setForeground(new java.awt.Color(255, 255, 255));
        btnDarsebaja.setText("Dar de Baja");
        btnDarsebaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDarsebajaActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(31, 41, 55));
        btnSalir.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(31, 41, 55));
        btnLimpiar.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDarsebaja)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnModificar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnDarsebaja)
                    .addComponent(btnEliminar)
                    .addComponent(btnSalir))
                .addGap(97, 97, 97))
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if (padre != null) {
            padre.listar("");
        }

    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing


    }//GEN-LAST:event_formWindowClosing

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        ResultSet rs = null;
        //Estructura: idproducto,producto,stock,vigencia,marcaproducto
        try {
            rs = objProducto.buscarXid(Integer.parseInt(txtId.getText()));
            if (rs.getBoolean("vigencia")) {
                txtNombre.setText(rs.getString("producto"));
                spnStock.setValue(rs.getInt("stock"));
                chkVigencia.setSelected(rs.getBoolean("vigencia"));
                cboMarcaProducto.setSelectedItem(rs.getString("marcaproducto"));
                cboTipoProducto.setSelectedItem(rs.getString("tipoproducto"));
                txtPrecio.setText(Float.toString(rs.getFloat("precioactual")));
            } else {
                JOptionPane.showMessageDialog(rootPane, "EL PRODUCTO ESTA DADO DE BAJA");
                int resp = JOptionPane.showConfirmDialog(
                        null,
                        "¿Quiere habilitar el producto en cuestión?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                
                if (resp == JOptionPane.YES_OPTION) {
                objProducto.recuperarProducto(Integer.parseInt(txtId.getText()));
                listar("");
                } 

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "EL PRODUCTO NO EXISTE O NO SE ENCUENTRA DISPONIBLE");

        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        //cboMarcaProducto,cboTipoProducto,chkVigencia,txtId,spnStock,txtNombre
        int id = -1, tipoProducto = -1, marcaProducto = -1, stock = -1;
        String nombre = "";
        boolean vigencia = false;
        ResultSet rs = null;
        float precio = 0.0f;

        try {
            id = objProducto.generarCodigoProducto();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al generar codigo Producto\n" + ex);
        }

        if (btnNuevo.getText().equals("Nuevo")) {
            btnNuevo.setText("Guardar");
            limpiar();
            txtId.setText(String.valueOf(id));
        } else {
            btnNuevo.setText("Nuevo");
            try {
                rs = objTipoProducto.buscarIdxNombre((String) cboTipoProducto.getSelectedItem());
                tipoProducto = rs.getInt("idtipoproducto");
                rs = null;
                rs = objMarca.buscaridxNombre((String) cboMarcaProducto.getSelectedItem());
                marcaProducto = rs.getInt("idmarcaproducto");
                stock = (Integer) spnStock.getValue();
                nombre = txtNombre.getText();
                vigencia = chkVigencia.isSelected();
                precio = Float.parseFloat(txtPrecio.getText());

                objProducto.registrarProducto(id, nombre, stock, vigencia, precio, tipoProducto, marcaProducto);
                JOptionPane.showMessageDialog(this, "PRODUCTO CORRECTAMENTE GUARDADO");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al buscar uno de los datos en la base de datos \n" + e);
            } catch (NullPointerException c) {
                JOptionPane.showMessageDialog(this, "uno de los campos esta mal seleccionados\n" + rs);
            } catch (ClassNotFoundException cl) {
                JOptionPane.showMessageDialog(this, "Uno de los campos no es valido\n" + cl);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al crear nuevo producto\n" + e);
            } finally {
                JOptionPane.showMessageDialog(this, id + "|" + tipoProducto + "|" + marcaProducto + "|" + stock + "|" + nombre + "|" + vigencia);
            }
            //objProducto.registrarProducto(WIDTH, nombre, ABORT, rootPaneCheckingEnabled)
            listar("");
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (txtId.getText().isBlank()) {
            JOptionPane.showMessageDialog(this,"ESCOJA UN ID PARA ELIMINAR");
           return;
        }
                try {
            Integer.parseInt(txtId.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "ID incorrecto  \n" + e.getMessage());
        }
        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Realmente quiere eliminar este producto?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (respuesta == JOptionPane.YES_OPTION) {

            try {
                objProducto.eliminarProducto(Integer.parseInt(txtId.getText()));
                limpiar();
                listar("");
                JOptionPane.showMessageDialog(this, "PRODUCTO ELIMINADO");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error al Eliminar" + e);
            }

        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnTipoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTipoProductoActionPerformed
        JdGestionarTipoDeProducto obj = new JdGestionarTipoDeProducto(frmP, true, this);
        obj.setLocationRelativeTo(this);
        obj.setVisible(true);

    }//GEN-LAST:event_btnTipoProductoActionPerformed

    private void btnMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcaActionPerformed
        JdGestionarMarca obj = new JdGestionarMarca(frmP, true, this);
        obj.setLocationRelativeTo(this);
        obj.setVisible(true);

    }//GEN-LAST:event_btnMarcaActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        //cboMarcaProducto,cboTipoProducto,chkVigencia,txtId,spnStock,txtNombre
        int id = -1, tipoProducto = -1, marcaProducto = -1, stock = -1;
        String nombre = "";
        boolean vigencia = false;
        ResultSet rs = null;
        float precio = 0.0f;

        try {
            rs = objTipoProducto.buscarIdxNombre((String) cboTipoProducto.getSelectedItem());
            tipoProducto = rs.getInt("idtipoproducto");
            rs = null;
            rs = objMarca.buscaridxNombre((String) cboMarcaProducto.getSelectedItem());
            marcaProducto = rs.getInt("idmarcaproducto");
            stock = (Integer) spnStock.getValue();
            nombre = txtNombre.getText();
            vigencia = chkVigencia.isSelected();
            precio = Float.parseFloat(txtPrecio.getText());
            id = Integer.parseInt(txtId.getText());

            objProducto.modificarProducto(id, nombre, stock, vigencia, precio, tipoProducto, marcaProducto);
            JOptionPane.showMessageDialog(this, "PRODUCTO CORRECTAMENTE MODIFICADO");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar uno de los datos en la base de datos \n" + e);
        } catch (NullPointerException c) {
            JOptionPane.showMessageDialog(this, "uno de los campos esta mal seleccionados\n" + rs);
        } catch (ClassNotFoundException cl) {
            JOptionPane.showMessageDialog(this, "Uno de los campos no es valido\n" + cl);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al crear nuevo producto\n" + e);
        }
        //objProducto.registrarProducto(WIDTH, nombre, ABORT, rootPaneCheckingEnabled)
        listar("");

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnDarsebajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDarsebajaActionPerformed
        int id = -1;
               if (txtId.getText().isBlank()) {
            JOptionPane.showMessageDialog(this,"ESCOJA UN ID PARA ELIMINAR");
           return;
        }
        try {
            id = Integer.parseInt(txtId.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "ID incorrecto  \n" + e.getMessage());
        }

        try {
            objProducto.darbajaProducto(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error al dar de baja \n" + e.getMessage());
        }
        listar("");
    }//GEN-LAST:event_btnDarsebajaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDarsebaja;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnMarca;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnTipoProducto;
    private javax.swing.JComboBox<String> cboMarcaProducto;
    private javax.swing.JComboBox<String> cboTipoProducto;
    private javax.swing.JCheckBox chkVigencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JSpinner spnStock;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
