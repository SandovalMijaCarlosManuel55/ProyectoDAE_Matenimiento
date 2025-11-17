/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package CapaCliente;

import CapaLogica.clsTipoTrabajador;
import CapaLogica.clsTrabajador;
import CapaLogica.clsUbigeo;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class JdGestionarTrabajador extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JdGestionarTrabajador.class.getName());

    private String modo = "nuevo"; // o "editar"
    private int idSeleccionado = -1;
    private clsTrabajador objTrabajador = new clsTrabajador();
    private clsTipoTrabajador objTipoTrabajador = new clsTipoTrabajador();
    private clsUbigeo objUbigeo = new clsUbigeo();
    private boolean cargandoProvincia = false;
    private boolean cargandoDistrito = false;



    public JdGestionarTrabajador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        lblTitulo.setText("Registrar Trabajador");
        btnDarDeBaja.setEnabled(false);
        cargarcbo();
        try {
            int nuevoID = objTrabajador.obtenerSiguienteID();
            txtCodigo.setText(String.valueOf(nuevoID));
            txtCodigo.setEnabled(false); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar código: " + e.getMessage());
        }
    }

    // 🔹 Constructor para editar
    public JdGestionarTrabajador(java.awt.Frame parent, boolean modal, int idTrabajador) {
        super(parent, modal);
        initComponents();
        lblTitulo.setText("Editar Trabajador");
        modo = "editar"; 
        idSeleccionado = idTrabajador;
        cargarcbo();
        cargarDatosTrabajador(idTrabajador);
        btnRegistrar.setText("Actualizar");
        txtCodigo.setEnabled(false); 
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDni.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtUsuario.setText("");
        txtContrasena.setText("");
        cboSexo.setSelectedIndex(-1);
        cboDepartamento.setSelectedIndex(-1);
        cboDistrito.setSelectedIndex(-1);
        cboProvincia.setSelectedIndex(-1);
        cboTipoTrabajador.setSelectedIndex(-1);
        txtPregunta.setText("");
        txtRespuesta.setText("");
    }
    
    public void cargarcbo(){
        DefaultComboBoxModel distritos = new DefaultComboBoxModel();
        DefaultComboBoxModel departamentos = new DefaultComboBoxModel();
        DefaultComboBoxModel provincias = new DefaultComboBoxModel();
        DefaultComboBoxModel tipos = new DefaultComboBoxModel();
        try {
            ResultSet rs1 = objUbigeo.listarDistritos();
            while (rs1.next()) {
                distritos.addElement(rs1.getString("distrito"));
            }
            ResultSet rs2 = objUbigeo.listarDepartamentos();
            while (rs2.next()) {
                departamentos.addElement(rs2.getString("departamento"));
            }
            ResultSet rs3 = objUbigeo.listarProvincias();
            while (rs3.next()) {
                provincias.addElement(rs3.getString("provincia"));
            }
            ResultSet rs4 = objTipoTrabajador.listarTipoTrabajadores();
            while (rs4.next()) {
                tipos.addElement(rs4.getString("tipotrabajador"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar cbos"+ e.getMessage());
        }
        cboDistrito.setModel(distritos);
        cboDepartamento.setModel(departamentos);
        cboProvincia.setModel(provincias);
        cboTipoTrabajador.setModel(tipos);
    }
    
    public void cargarDatosTrabajador(int idTrabajador) {
        try {
            clsTrabajador obj = new clsTrabajador();
            ResultSet rs = obj.buscarTrabajadorporID(idTrabajador);

            if (rs.next()) {
                txtCodigo.setText(String.valueOf(rs.getInt("idtrabajador")));
                txtNombre.setText(rs.getString("trabajador"));
                txtTelefono.setText(rs.getString("telefono"));
                txtDni.setText(rs.getString("dni"));
                txtCorreo.setText(rs.getString("correo"));
                txtUsuario.setText(rs.getString("usuario"));
                txtContrasena.setText(rs.getString("contraseña"));
                txtPregunta.setText(rs.getString("pregunta"));
                txtRespuesta.setText(rs.getString("respuesta"));
                chkEstado.setSelected(rs.getBoolean("estado"));
                int idDistrito = rs.getInt("iddistrito");
                int idTipoTrabajador = rs.getInt("idtipotrabajador");
                ResultSet ubigeo = objUbigeo.ubigeo(idDistrito);
                cboTipoTrabajador.getModel().setSelectedItem(objTipoTrabajador.buscarPorID(idTipoTrabajador));
                if (ubigeo.next()) {
                    cboDistrito.getModel().setSelectedItem(ubigeo.getString("distrito"));
                    cboDepartamento.getModel().setSelectedItem(ubigeo.getString("departamento"));
                    cboProvincia.getModel().setSelectedItem(ubigeo.getString("provincia"));
                }
                String sexo = rs.getString("sexo");
                if (sexo != null) {
                    if (sexo.equalsIgnoreCase("M")) {
                        cboSexo.setSelectedItem("Masculino");
                    } else {
                        cboSexo.setSelectedItem("Femenino");
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btnDarDeBaja = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        cboTipoTrabajador = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JTextField();
        txtPregunta = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtRespuesta = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        chkEstado = new javax.swing.JCheckBox();
        cboDepartamento = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboProvincia = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        cboDistrito = new javax.swing.JComboBox<>();
        cboSexo = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        panel.setBackground(new java.awt.Color(255, 255, 255));

        btnRegistrar.setBackground(new java.awt.Color(31, 41, 55));
        btnRegistrar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        lblTitulo.setText("Registrar Trabajador");

        jButton2.setBackground(new java.awt.Color(31, 41, 55));
        jButton2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnDarDeBaja.setBackground(new java.awt.Color(31, 41, 55));
        btnDarDeBaja.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnDarDeBaja.setForeground(new java.awt.Color(255, 255, 255));
        btnDarDeBaja.setText("Dar de baja");
        btnDarDeBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDarDeBajaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel8.setText("Código");

        txtCodigo.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel20.setText("Tipo de trabajador:");

        cboTipoTrabajador.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jButton1.setBackground(new java.awt.Color(31, 41, 55));
        jButton1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Gestionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel15.setText("Usuario:");

        txtUsuario.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel16.setText("Contraseña");

        txtContrasena.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        txtPregunta.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel17.setText("Pregunta de recuperación");

        jLabel18.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel18.setText("Respuesta de recuperación:");

        txtRespuesta.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel14.setText("Estado");

        chkEstado.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        chkEstado.setText("Activo");

        cboDepartamento.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        cboDepartamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDepartamentoItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel3.setText("Departamento");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel4.setText("Provincia");

        cboProvincia.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        cboProvincia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboProvinciaItemStateChanged(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel19.setText("Distrito");

        cboDistrito.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        cboSexo.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        cboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel12.setText("Sexo:");

        txtCorreo.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel13.setText("Correo");

        txtDni.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel11.setText("DNI");

        txtNombre.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel6.setText("Nombre");

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel7.setText("Telefono");

        txtTelefono.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jPanel3.setForeground(new java.awt.Color(31, 41, 55));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 445, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(btnRegistrar)
                .addGap(27, 27, 27)
                .addComponent(btnDarDeBaja)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(98, 98, 98)))
                            .addComponent(jLabel14)
                            .addComponent(chkEstado)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtUsuario)
                                .addComponent(txtContrasena, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addGap(133, 133, 133)))
                            .addComponent(jLabel15)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17)
                            .addComponent(txtPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(cboTipoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(cboDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel13)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(cboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addComponent(txtCorreo)
                    .addComponent(txtNombre))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(239, 239, 239))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblTitulo)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkEstado))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboTipoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDarDeBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            String nombre = txtNombre.getText();
            String telefono = txtTelefono.getText();
            String dni = txtDni.getText();
            String sexo = cboSexo.getSelectedItem().toString().substring(0, 1); 
            String correo = txtCorreo.getText();
            String usuario = txtUsuario.getText();
            String contrasena = txtContrasena.getText();
            boolean estado = chkEstado.isSelected();
            String pregunta = txtPregunta.getText();
            String respuesta = txtRespuesta.getText();
            String tipoTrabajador = cboTipoTrabajador.getSelectedItem().toString();
            String distrito = cboDistrito.getSelectedItem().toString();
            String provincia = cboProvincia.getSelectedItem().toString();
            String departamento = cboDepartamento.getSelectedItem().toString();
            int idTipo = objTipoTrabajador.buscarPorNombre(tipoTrabajador);
            int idDistrito = objUbigeo.buscarIdDistrito(distrito, provincia, departamento);

            if (nombre.isEmpty() || telefono.isEmpty() || dni.isEmpty() || correo.isEmpty() || usuario.isEmpty()
                || contrasena.isEmpty() || pregunta.isEmpty() || respuesta.isEmpty() ) {
                JOptionPane.showMessageDialog(this, "Por favor complete los campos");
                
                if (dni.length() != 8) {
                    JOptionPane.showMessageDialog(this, "El dni debe tener 8 digitos");
                    return;
                }
                
                if (telefono.length() != 9) {
                    JOptionPane.showMessageDialog(this, "El dni debe tener 9 digitos");
                    return;
                }
                
                return;
            }

            if (modo.equals("nuevo")) {
                int codigo = Integer.parseInt(txtCodigo.getText());
                objTrabajador.registrar(
                        codigo, nombre, telefono, dni, sexo, correo,
                        usuario, contrasena, estado, pregunta, respuesta,
                        idDistrito, idTipo
                );
                JOptionPane.showMessageDialog(this, "Trabajador registrado correctamente.");
                
            } else if (modo.equals("editar")) {
                objTrabajador.modificar(
                        idSeleccionado, nombre, telefono, dni, sexo, correo,
                        usuario, contrasena, estado, pregunta, respuesta,
                        idTipo, idDistrito
                );
                JOptionPane.showMessageDialog(this, "Trabajador actualizado correctamente.");
            }

            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnDarDeBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDarDeBajaActionPerformed
        try{
            int idTrabajador = Integer.parseInt(txtCodigo.getText());
            objTrabajador.eliminarTrabajador(idTrabajador);
            JOptionPane.showMessageDialog(this, "Trabajador dado de baja");
            this.dispose();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al dar de baja " + e.getMessage());
        }
    }//GEN-LAST:event_btnDarDeBajaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JdGestionarTipoTrabajador obj = new JdGestionarTipoTrabajador( null, false);
        obj.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    private void cboDepartamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDepartamentoItemStateChanged
        if (evt.getStateChange() != ItemEvent.SELECTED) return;
        try {
            cargandoProvincia = true;
            cboProvincia.removeAllItems();
            cboDistrito.removeAllItems();
            int id = objUbigeo.buscarDepartamento(cboDepartamento.getSelectedItem().toString());
            ResultSet rs = objUbigeo.listarProvinciaPorDep(id);
            while (rs.next()) {
                String provincia = rs.getString("provincia");
                cboProvincia.addItem(provincia);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar provincias -> " + e.getMessage(), "Sistema", JOptionPane.ERROR_MESSAGE);
        }finally {
        cargandoProvincia = false;
    }
    }//GEN-LAST:event_cboDepartamentoItemStateChanged

    private void cboProvinciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboProvinciaItemStateChanged
        if (cargandoProvincia) return;
        if (evt.getStateChange() != ItemEvent.SELECTED) return;
        try {
            cboDistrito.removeAllItems();
            int id = objUbigeo.buscarProvincia(cboProvincia.getSelectedItem().toString());
            ResultSet rs = objUbigeo.listarDistritosPorProv(id);
            while (rs.next()) {
                String distrito = rs.getString("distrito");
                cboDistrito.addItem(distrito);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar distritos -> " + e.getMessage(), "Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_cboProvinciaItemStateChanged

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDarDeBaja;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cboDepartamento;
    private javax.swing.JComboBox<String> cboDistrito;
    private javax.swing.JComboBox<String> cboProvincia;
    private javax.swing.JComboBox<String> cboSexo;
    private javax.swing.JComboBox<String> cboTipoTrabajador;
    private javax.swing.JCheckBox chkEstado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtContrasena;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPregunta;
    private javax.swing.JTextField txtRespuesta;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
