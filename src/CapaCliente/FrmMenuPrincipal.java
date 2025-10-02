 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CapaCliente;


import java.awt.Graphics;
import java.awt.Image;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Josselyn
 */
public class FrmMenuPrincipal extends javax.swing.JFrame {

    FondoPanel fondo = new FondoPanel();
    FondoLogo logo = new FondoLogo();
    
    public FrmMenuPrincipal() {
      
        this.setContentPane(logo);
        //setExtendedState(MAXIMIZED_BOTH);
          SwingUtilities.invokeLater(() -> setExtendedState(JFrame.MAXIMIZED_BOTH));
        initComponents();
        pnlMenu.setLayout(new BoxLayout(pnlMenu, BoxLayout.Y_AXIS));
       
        //cambiar el tamaño exacto para que ocupe todo el panel de cada boton
        btnInicio.add(Box.createGlue());
        btnVehiculos.add(Box.createGlue());
        btnServicios.add(Box.createGlue());
        btnClientes.add(Box.createGlue());
        btnProductos.add(Box.createGlue());
        btnVentas.add(Box.createGlue());
        btnTrabajadores.add(Box.createGlue());
        btnReportes.add(Box.createGlue());
        btnAcercaDe.add(Box.createGlue());

        pnlMenu.add(btnInicio);
        pnlMenu.add(btnVehiculos);
        pnlMenu.add(btnServicios);
        pnlMenu.add(btnClientes);
        pnlMenu.add(btnProductos);
        pnlMenu.add(btnVentas); 
        pnlMenu.add(btnTrabajadores); 
        pnlMenu.add(btnReportes);
        pnlMenu.add(btnAcercaDe);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        JpLogo = new FondoLogo();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnlMenu = new javax.swing.JPanel();
        btnReportes = new javax.swing.JButton();
        btnAcercaDe = new javax.swing.JButton();
        btnVehiculos = new javax.swing.JButton();
        btnServicios = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        btnTrabajadores = new javax.swing.JButton();
        JpFondo = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 700));

        jPanel2.setBackground(new java.awt.Color(31, 41, 55));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 100));

        JpLogo.setPreferredSize(new java.awt.Dimension(116, 72));

        javax.swing.GroupLayout JpLogoLayout = new javax.swing.GroupLayout(JpLogo);
        JpLogo.setLayout(JpLogoLayout);
        JpLogoLayout.setHorizontalGroup(
            JpLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        JpLogoLayout.setVerticalGroup(
            JpLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SISTEMA DE MANTENIMIENTO DE VEHÍCULOS");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Y VENTA DE PRODUCTOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(JpLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(1077, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(JpLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pnlMenu.setBackground(new java.awt.Color(223, 218, 214));
        pnlMenu.setPreferredSize(new java.awt.Dimension(300, 600));
        pnlMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnReportes.setBackground(new java.awt.Color(223, 218, 214));
        btnReportes.setFont(new java.awt.Font("Verdana", 0, 22)); // NOI18N
        btnReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Reportes.png"))); // NOI18N
        btnReportes.setText("  Reportes");
        btnReportes.setBorderPainted(false);
        btnReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnReportes.setDefaultCapable(false);
        btnReportes.setFocusable(false);
        btnReportes.setHideActionText(true);
        btnReportes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReportes.setIconTextGap(7);
        btnReportes.setOpaque(true);
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });
        pnlMenu.add(btnReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 610, 340, 50));

        btnAcercaDe.setBackground(new java.awt.Color(223, 218, 214));
        btnAcercaDe.setFont(new java.awt.Font("Verdana", 0, 22)); // NOI18N
        btnAcercaDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/acercaDe.png"))); // NOI18N
        btnAcercaDe.setText("  Acerca De");
        btnAcercaDe.setBorderPainted(false);
        btnAcercaDe.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAcercaDe.setDefaultCapable(false);
        btnAcercaDe.setFocusable(false);
        btnAcercaDe.setHideActionText(true);
        btnAcercaDe.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAcercaDe.setIconTextGap(7);
        btnAcercaDe.setOpaque(true);
        pnlMenu.add(btnAcercaDe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 680, 340, 50));

        btnVehiculos.setBackground(new java.awt.Color(223, 218, 214));
        btnVehiculos.setFont(new java.awt.Font("Verdana", 0, 22)); // NOI18N
        btnVehiculos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Vehiculos.png"))); // NOI18N
        btnVehiculos.setText(" Vehículos");
        btnVehiculos.setBorderPainted(false);
        btnVehiculos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVehiculos.setDefaultCapable(false);
        btnVehiculos.setFocusable(false);
        btnVehiculos.setHideActionText(true);
        btnVehiculos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVehiculos.setIconTextGap(7);
        btnVehiculos.setOpaque(true);
        btnVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVehiculosActionPerformed(evt);
            }
        });
        pnlMenu.add(btnVehiculos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 340, 50));

        btnServicios.setBackground(new java.awt.Color(223, 218, 214));
        btnServicios.setFont(new java.awt.Font("Verdana", 0, 22)); // NOI18N
        btnServicios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Mantenimiento.png"))); // NOI18N
        btnServicios.setText("  Servicios");
        btnServicios.setBorderPainted(false);
        btnServicios.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnServicios.setDefaultCapable(false);
        btnServicios.setFocusable(false);
        btnServicios.setHideActionText(true);
        btnServicios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnServicios.setIconTextGap(7);
        btnServicios.setOpaque(true);
        btnServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServiciosActionPerformed(evt);
            }
        });
        pnlMenu.add(btnServicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 340, 50));

        btnClientes.setBackground(new java.awt.Color(223, 218, 214));
        btnClientes.setFont(new java.awt.Font("Verdana", 0, 22)); // NOI18N
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Cliente.png"))); // NOI18N
        btnClientes.setText("  Clientes");
        btnClientes.setBorderPainted(false);
        btnClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnClientes.setDefaultCapable(false);
        btnClientes.setFocusable(false);
        btnClientes.setHideActionText(true);
        btnClientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnClientes.setIconTextGap(7);
        btnClientes.setOpaque(true);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        pnlMenu.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 340, 50));

        btnProductos.setBackground(new java.awt.Color(223, 218, 214));
        btnProductos.setFont(new java.awt.Font("Verdana", 0, 22)); // NOI18N
        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Productos.png"))); // NOI18N
        btnProductos.setText("  Productos");
        btnProductos.setBorderPainted(false);
        btnProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProductos.setDefaultCapable(false);
        btnProductos.setFocusable(false);
        btnProductos.setHideActionText(true);
        btnProductos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnProductos.setIconTextGap(7);
        btnProductos.setOpaque(true);
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });
        pnlMenu.add(btnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 340, 50));

        btnVentas.setBackground(new java.awt.Color(223, 218, 214));
        btnVentas.setFont(new java.awt.Font("Verdana", 0, 22)); // NOI18N
        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Ventas.png"))); // NOI18N
        btnVentas.setText("  Ventas");
        btnVentas.setBorderPainted(false);
        btnVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVentas.setDefaultCapable(false);
        btnVentas.setFocusable(false);
        btnVentas.setHideActionText(true);
        btnVentas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVentas.setIconTextGap(7);
        btnVentas.setOpaque(true);
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });
        pnlMenu.add(btnVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 340, 50));

        btnInicio.setBackground(new java.awt.Color(223, 218, 214));
        btnInicio.setFont(new java.awt.Font("Verdana", 0, 22)); // NOI18N
        btnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Inicio.png"))); // NOI18N
        btnInicio.setText("  Inicio");
        btnInicio.setBorderPainted(false);
        btnInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnInicio.setDefaultCapable(false);
        btnInicio.setDisabledSelectedIcon(null);
        btnInicio.setFocusable(false);
        btnInicio.setHideActionText(true);
        btnInicio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnInicio.setIconTextGap(7);
        btnInicio.setOpaque(true);
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        pnlMenu.add(btnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 340, 50));

        btnTrabajadores.setBackground(new java.awt.Color(223, 218, 214));
        btnTrabajadores.setFont(new java.awt.Font("Verdana", 0, 22)); // NOI18N
        btnTrabajadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Trabajadores.png"))); // NOI18N
        btnTrabajadores.setText("  Trabajadores");
        btnTrabajadores.setBorderPainted(false);
        btnTrabajadores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnTrabajadores.setDefaultCapable(false);
        btnTrabajadores.setFocusable(false);
        btnTrabajadores.setHideActionText(true);
        btnTrabajadores.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTrabajadores.setIconTextGap(7);
        btnTrabajadores.setOpaque(true);
        btnTrabajadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrabajadoresActionPerformed(evt);
            }
        });
        pnlMenu.add(btnTrabajadores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 340, 50));

        javax.swing.GroupLayout JpFondoLayout = new javax.swing.GroupLayout(JpFondo);
        JpFondo.setLayout(JpFondoLayout);
        JpFondoLayout.setHorizontalGroup(
            JpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JpFondoLayout.setVerticalGroup(
            JpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JpFondo))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1850, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                    .addComponent(JpFondo)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1850, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//cerrar los internalframeAbiertos
    
    private void close(JDesktopPane desktopPane){
    // Obtener el primer (y único) internal frame en el DesktopPane
    JInternalFrame internalFrame = desktopPane.getAllFrames().length > 0 ? desktopPane.getAllFrames()[0] : null;

    // Si existe un internal frame, cerrarlo
    if (internalFrame != null) {
        internalFrame.dispose();
    }};
    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVehiculosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVehiculosActionPerformed

    private void btnServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServiciosActionPerformed

IFMantenimientoServicios obj = new IFMantenimientoServicios();
   close(JpFondo); 
// Obtener el tamaño del JDesktopPane
int width = JpFondo.getWidth();
int height = JpFondo.getHeight()+30;

// Establecer el tamaño del JInternalFrame para que ocupe todo el espacio del JDesktopPane
obj.setSize(width, height);
obj.setLocation(0, -30); 
obj.setResizable(false);
obj.setClosable(false);
obj.setMaximizable(false);
obj.setIconifiable(false);

obj.setVisible(true);
 
// Agregar el JInternalFrame (obj) al JDesktopPane (JpFondo)
JpFondo.add(obj);  // Aquí JpFondo es tu JDesktopPane
// Asegúrate de que el JDesktopPane repinte correctamente
JpFondo.revalidate();
JpFondo.repaint();
 
    }//GEN-LAST:event_btnServiciosActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
          close(JpFondo); 
IfMantenimientoCliente obj = new IfMantenimientoCliente();
// Obtener el tamaño del JDesktopPane
int width = JpFondo.getWidth();
int height = JpFondo.getHeight()+30;

// Establecer el tamaño del JInternalFrame para que ocupe todo el espacio del JDesktopPane
obj.setSize(width, height);
obj.setLocation(0, -30); 
obj.setResizable(false);
obj.setClosable(false);
obj.setMaximizable(false);
obj.setIconifiable(false);

obj.setVisible(true);
 
// Agregar el JInternalFrame (obj) al JDesktopPane (JpFondo)
JpFondo.add(obj);  // Aquí JpFondo es tu JDesktopPane
// Asegúrate de que el JDesktopPane repinte correctamente
JpFondo.revalidate();
JpFondo.repaint();
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
     
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed

    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnTrabajadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrabajadoresActionPerformed
    close(JpFondo); 
IfMantenimientoTrabajador obj = new IfMantenimientoTrabajador();
// Obtener el tamaño del JDesktopPane
int width = JpFondo.getWidth();
int height = JpFondo.getHeight()+30;

// Establecer el tamaño del JInternalFrame para que ocupe todo el espacio del JDesktopPane
obj.setSize(width, height);
obj.setLocation(0, -30); 
obj.setResizable(false);
obj.setClosable(false);
obj.setMaximizable(false);
obj.setIconifiable(false);

obj.setVisible(true);
 
// Agregar el JInternalFrame (obj) al JDesktopPane (JpFondo)
JpFondo.add(obj);  // Aquí JpFondo es tu JDesktopPane
// Asegúrate de que el JDesktopPane repinte correctamente
JpFondo.revalidate();
JpFondo.repaint();
    }//GEN-LAST:event_btnTrabajadoresActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        close(JpFondo); 
    }//GEN-LAST:event_btnInicioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane JpFondo;
    private javax.swing.JPanel JpLogo;
    private javax.swing.JButton btnAcercaDe;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnServicios;
    private javax.swing.JButton btnTrabajadores;
    private javax.swing.JButton btnVehiculos;
    private javax.swing.JButton btnVentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel pnlMenu;
    // End of variables declaration//GEN-END:variables
}


class FondoPanel extends JPanel {

    private Image imagen;

    public void paint(Graphics g) {
        imagen = new ImageIcon(getClass().getResource("/Recursos/fondo.jpg")).getImage();
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paint(g);
    }
}

class FondoLogo extends JPanel {

    private Image imagen;

    public void paint(Graphics g) {
        imagen = new ImageIcon(getClass().getResource("/Recursos/logo.png")).getImage();
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paint(g);
    }
}