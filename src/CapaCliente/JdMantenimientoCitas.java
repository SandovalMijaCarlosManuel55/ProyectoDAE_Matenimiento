/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package CapaCliente;

import CapaLogica.clsCita;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author piero
 */
public class JdMantenimientoCitas extends javax.swing.JDialog {

    public JdMantenimientoCitas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        panelServicios.setLayout(new GridLayout(0, 3, 20, 20));
        cargarCitas("Todos");
        panelServicios.revalidate();
        panelServicios.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtBuscarServicio = new javax.swing.JTextField();
        jScrollPanel = new javax.swing.JScrollPane();
        panelServicios = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        cboEstado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setText("Gestión de Citas");

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel2.setText("Administra las citas");

        jButton1.setBackground(new java.awt.Color(31, 41, 55));
        jButton1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Nueva Cita");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtBuscarServicio.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        panelServicios.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        javax.swing.GroupLayout panelServiciosLayout = new javax.swing.GroupLayout(panelServicios);
        panelServicios.setLayout(panelServiciosLayout);
        panelServiciosLayout.setHorizontalGroup(
            panelServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1465, Short.MAX_VALUE)
        );
        panelServiciosLayout.setVerticalGroup(
            panelServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 787, Short.MAX_VALUE)
        );

        jScrollPanel.setViewportView(panelServicios);

        jButton2.setBackground(new java.awt.Color(31, 41, 55));
        jButton2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        cboEstado.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Realizado", "En Proceso", "Pendiente" }));
        cboEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEstadoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel3.setText("Buscar Por Código (ID):");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel4.setText("Listar Por Estado:");

        jButton3.setBackground(new java.awt.Color(31, 41, 55));
        jButton3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
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
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtBuscarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton2))
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton3))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBuscarServicio)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboEstado)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    
    private clsCita objCita = new clsCita(); 

    private void cargarCitas(String filtro) {
        panelServicios.removeAll();
        try {
            ResultSet rs = objCita.listarCitas();
            if (filtro == null || filtro.equals("Todos")) {
            rs = objCita.listarCitas(); 
            } else {
                rs = objCita.listarCitasPorEstado(filtro); 
            }
            while (rs.next()) {
                int idCita = rs.getInt("IDCITA");
                Date fecha = rs.getDate("FECHA");
                String hora = rs.getString("HORA");
                String estado = rs.getString("ESTADO");
                String comentario = rs.getString("COMENTARIO");
                Date fechaRecojo = rs.getDate("FECHARECOJO");
                String clienteNombre = rs.getString("CLIENTE_NOMBRE");
                String placa = rs.getString("PLACA"); 
                String modelo = rs.getString("TIPOVEHICULO"); 
                String servicio = rs.getString("SERVICIO");
                String mecanico = rs.getString("TRABAJADOR");

                panelServicios.add(crearTarjetaCita(
                    idCita, clienteNombre, modelo + " - " + placa,
                    servicio, fecha, hora, estado, mecanico, comentario
                ));
            }
            panelServicios.revalidate();
            panelServicios.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar citas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel crearTarjetaCita(int idCita, String cliente, String vehiculo, String servicio, Date fecha, String hora, String estado, String mecanico, String comentario) {
    JPanel card = new JPanel() {
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int arc = 20; // radio de borde
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int arc = 20;
            g2.setColor(new Color(230, 230, 230));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
            g2.dispose();
        }
    };

    // --- Propiedades base ---
    card.setOpaque(false);
    card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
    card.setPreferredSize(new Dimension(300, 230));
    card.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
    
    // --- Encabezado ---
    JPanel header = new JPanel(new BorderLayout());
    header.setOpaque(false);
    JLabel lblCliente = new JLabel("<html><b>" + cliente + "</b></html>");
    lblCliente.setFont(new Font("Segoe UI", Font.BOLD, 16));

    JLabel lblEstado = new JLabel(estado, SwingConstants.CENTER);
    lblEstado.setOpaque(true);
    lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 12));
    lblEstado.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));

    switch (estado) {
        case "Confirmada" -> {
            lblEstado.setBackground(new Color(200, 230, 255));
            lblEstado.setForeground(new Color(0, 70, 160));
        }
        case "Pendiente" -> {
            lblEstado.setBackground(new Color(255, 245, 200));
            lblEstado.setForeground(new Color(130, 90, 0));
        }
        case "En Proceso" -> {
            lblEstado.setBackground(new Color(255, 210, 210));
            lblEstado.setForeground(Color.RED);
        }
        case "Realizado" -> {
            lblEstado.setBackground(new Color(210, 255, 210));
            lblEstado.setForeground(new Color(0, 110, 0));
        }
        default -> {
            lblEstado.setBackground(Color.LIGHT_GRAY);
            lblEstado.setForeground(Color.DARK_GRAY);
        }
    }

    header.add(lblCliente, BorderLayout.WEST);
    header.add(lblEstado, BorderLayout.EAST);
    card.add(header);
    card.add(Box.createVerticalStrut(8));

    // --- Detalles ---
    JPanel detalles = new JPanel();
    detalles.setOpaque(false);
    detalles.setLayout(new BoxLayout(detalles, BoxLayout.Y_AXIS));

    SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");

    JLabel lblVehiculo = new JLabel("🚗  " + vehiculo);
    JLabel lblServicio = new JLabel("🔧  " + servicio);
    JLabel lblFecha = new JLabel("📅  " + sdf.format(fecha) + "   ⏰  " + hora);
    JLabel lblMecanico = new JLabel("👨‍🔧  Mecánico: " + mecanico);

    lblVehiculo.setForeground(Color.GRAY);
    lblServicio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblMecanico.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 13));

    detalles.add(lblVehiculo);
    detalles.add(lblServicio);
    detalles.add(lblFecha);
    detalles.add(lblMecanico);
    card.add(detalles);

    // --- Comentario (opcional) ---
    if (comentario != null && !comentario.trim().isEmpty()) {
        JLabel lblComentario = new JLabel("<html><i>" + comentario + "</i></html>");
        lblComentario.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        lblComentario.setBackground(new Color(245, 245, 245));
        lblComentario.setOpaque(true);
        card.add(Box.createVerticalStrut(8));
        card.add(lblComentario);
    }

    // --- Botones ---
    JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 10));
    panelBotones.setOpaque(false);
    
    JButton btnEditar = crearBoton("️ Editar", new Color(241, 196, 15));
    JButton btnEliminar = crearBoton("️ Eliminar", new Color(231, 76, 60));

    btnEditar.setVisible(!"Realizado".equals(estado));
    
    btnEditar.addActionListener(e -> {
        try {
            JdGestionarCitas objJd = new JdGestionarCitas(null, true,idCita);
            objJd.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(JdMantenimientoCitas.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
    
    btnEliminar.addActionListener(e -> {
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Seguro que deseas eliminar esta cita?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                objCita.eliminarServicio(idCita);
                cargarCitas("Todos");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al eliminar: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });

    panelBotones.add(btnEditar);
    panelBotones.add(btnEliminar);
    card.add(Box.createVerticalStrut(10));
    card.add(panelBotones);

    return card;
}
    
    private void mostrarResultados(ResultSet rs) throws SQLException {
    panelServicios.removeAll(); // Limpiar lo anterior
    boolean hayResultados = false;

    while (rs.next()) {
        hayResultados = true;
        // 1. Obtener datos
        int idCita = rs.getInt("IDCITA");
        Date fecha = rs.getDate("FECHA");
        String hora = rs.getString("HORA");
        String estado = rs.getString("ESTADO");
        String comentario = rs.getString("COMENTARIO");
        String clienteNombre = rs.getString("CLIENTE_NOMBRE");
        String placa = rs.getString("PLACA");
        String modelo = rs.getString("TIPOVEHICULO");
        String servicio = rs.getString("SERVICIO");
        String mecanico = rs.getString("TRABAJADOR");

        // 2. Agregar la tarjeta
        panelServicios.add(crearTarjetaCita(
            idCita, clienteNombre, modelo + " - " + placa,
            servicio, fecha, hora, estado, mecanico, comentario
        ));
    }
    
    if (!hayResultados) {
        JOptionPane.showMessageDialog(this, "No se encontraron citas con ese código.");
    }

    panelServicios.revalidate();
    panelServicios.repaint();
}

    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setForeground(Color.WHITE);
        boton.setBackground(colorFondo);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(6, 15, 6, 15));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto hover (ligeramente más oscuro)
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorFondo.darker());
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorFondo);
            }
        });
        return boton;
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            JdGestionarCitas objJd = new JdGestionarCitas(null, true);
            objJd.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(JdMantenimientoCitas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cboEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboEstadoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String estado = cboEstado.getSelectedItem().toString();
            cargarCitas(estado);
        } catch (Exception ex) {
            Logger.getLogger(JdMantenimientoCitas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String textoId = txtBuscarServicio.getText().trim();
        if (textoId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor escribe un código (ID) para buscar.");
            return;
        }

        try {
            int idBuscado = Integer.parseInt(textoId);

            ResultSet rs = objCita.buscarCitaPorCodigo(idBuscado);

            mostrarResultados(rs);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido.", "Error de formato", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            Logger.getLogger(JdMantenimientoCitas.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "Error al buscar: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPanel;
    private javax.swing.JPanel panelServicios;
    private javax.swing.JTextField txtBuscarServicio;
    // End of variables declaration//GEN-END:variables
}
