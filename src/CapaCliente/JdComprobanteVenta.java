/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package CapaCliente;

import CapaLogica.clsCliente;
import CapaLogica.clsComprobante;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Graphics2D;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Josselyn
 */
public class JdComprobanteVenta extends javax.swing.JDialog {

    FondoLogo logo = new FondoLogo();
    clsCliente objCliente = new clsCliente();
    clsComprobante objComprobante = new clsComprobante();
    public JTable tabla;
    String Nomcliente, Nomfecha, nomHora, NomtipoComprobante;
    LocalDate fechaa;
    int codigo;

    public JdComprobanteVenta(java.awt.Dialog parent, boolean modal, Object[][] datos, String[] columnas, String cliente, String fecha, String hora, int cod, String tipoComprobante, boolean servicio1, String vehiculo) {
        super(parent, modal);
        initComponents();
        this.tabla = tblDatosComprobante;
        tblDatosComprobante.setModel(new DefaultTableModel(datos, columnas));
        tblDatosComprobante.setRowHeight(22);
        Nomcliente = cliente;
        Nomfecha = fecha;
        NomtipoComprobante = tipoComprobante;
        nomHora = hora;
        codigo = cod;
        mostrarDatos();
        listarMedioPago();
    }

    public static double sumarColumna(JTable tabla, int indiceColumna) {
        TableModel modelo = tabla.getModel();
        double suma = 0.0;

        for (int fila = 0; fila < modelo.getRowCount(); fila++) {
            Object valor = modelo.getValueAt(fila, indiceColumna);

            if (valor == null) {
                continue;
            }

            try {
                if (valor instanceof Number number) {
                    suma += number.doubleValue();
                } else {
                    suma += Double.parseDouble(valor.toString().trim());
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor inválido en la fila " + fila + ": \"" + valor + "\"\nNo se pudo convertir a número.", "Error al calcular Subtotal", JOptionPane.ERROR_MESSAGE);
            }
        }
        return suma;
    }

    public static void exportarPanelAPdf(JPanel panel) {
        // 1. Mostrar diálogo de guardar archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar PDF");
        // Filtro para asegurar la extensión .pdf
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF (*.pdf)", "pdf");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(panel);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();

            // Aseguramos que la extensión .pdf esté presente
            if (!path.toLowerCase().endsWith(".pdf")) {
                path += ".pdf";
            }

            // 2. Definir el tamaño del documento PDF
            int panelWidth = panel.getWidth();
            int panelHeight = panel.getHeight();
            Rectangle pagesize = new Rectangle(panelWidth, panelHeight);

            Document document = new Document(pagesize);

            try {
                // 3. Crear el escritor PDF
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
                document.open();

                // 4. Obtener el contexto gráfico del PDF
                PdfContentByte cb = writer.getDirectContent();
                Graphics2D g2 = cb.createGraphics(panelWidth, panelHeight);

                // 5. Pintar el contenido del JPanel en el contexto gráfico del PDF
                panel.printAll(g2); // Usamos printAll() para dibujar todos los componentes hijos

                g2.dispose(); // Liberar recursos
                document.close();

                System.out.println("PDF creado exitosamente en: " + path);
                javax.swing.JOptionPane.showMessageDialog(panel,
                        "¡PDF guardado con éxito!",
                        "Éxito",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(panel,
                        "Error al crear el PDF: " + e.getMessage(),
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void exportarPanelAImagen(JPanel panel) {

        // 1. Mostrar diálogo de guardar archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Imagen");

        // Filtros de extensión para PNG y JPG
        FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("Imagen PNG (*.png)", "png");
        FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter("Imagen JPEG (*.jpg)", "jpg", "jpeg");
        fileChooser.addChoosableFileFilter(pngFilter);
        fileChooser.addChoosableFileFilter(jpgFilter);
        fileChooser.setFileFilter(pngFilter); // Establecer PNG como opción por defecto

        int userSelection = fileChooser.showSaveDialog(panel);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            // 2. Determinar el formato y asegurar la extensión
            String formatName = "png"; // PNG por defecto
            String path = fileToSave.getAbsolutePath();

            // Determinar el formato basándose en el filtro seleccionado
            if (fileChooser.getFileFilter() == jpgFilter) {
                formatName = "jpg";
            }

            // Asegurar que la extensión sea correcta
            if (!path.toLowerCase().endsWith("." + formatName)) {
                fileToSave = new File(path + "." + formatName);
            }

            // 3. Crear el buffer de imagen con el tamaño del panel
            Dimension d = panel.getSize();
            // Creamos un BufferedImage de tipo RGB que representará la imagen final
            BufferedImage image = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);

            // 4. Obtener el contexto gráfico y dibujar el panel
            Graphics g = image.getGraphics();
            panel.printAll(g); // 'printAll' dibuja el JPanel y sus componentes
            g.dispose(); // Liberar recursos del contexto gráfico

            try {
                // 5. Guardar el buffer de imagen en el archivo
                ImageIO.write(image, formatName, fileToSave);

                System.out.println("Imagen creada exitosamente en: " + fileToSave.getAbsolutePath());
                javax.swing.JOptionPane.showMessageDialog(panel,
                        "¡Imagen guardada con éxito!",
                        "Éxito",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException e) {
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(panel,
                        "Error al crear la imagen: " + e.getMessage(),
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarDatos() {
        lblTipoComprobante.setText(NomtipoComprobante.toUpperCase());
        lblFechaEmision.setText(Nomfecha);
        lblCodVentaCita.setText(String.valueOf(codigo));
        lblCliente.setText(Nomcliente);

        Double subtotal = sumarColumna(tabla, 3);
        lblSubTotal.setText(String.format("%.2f", subtotal));

        Double igv = subtotal * 0.18;
        lblIgv.setText(String.format("%.2f", igv));

        Double total = subtotal + igv;
        lblTotal.setText(String.format("%.2f", total));

        String montoLetras = objComprobante.numeroALetras(total);
        lblPrecioLetras.setText(montoLetras);

        try {
            String nroDocumento = objCliente.obtenerNumeroDocumento(Nomcliente);
            lblDocumentoIdentidad.setText(nroDocumento);

            String nroComprobante = objComprobante.mostrarNuevoNumeroComprobante(NomtipoComprobante);
            lblNumComprobante.setText(nroComprobante);
        } catch (Exception ex) {
            Logger.getLogger(JdComprobanteVenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        lblTrabajador.setText("Ricardo Torres Vega");   //PRUEBAAAAAAAAAAAAAAAAAAAAA

    }

    private void listarMedioPago() {
        try {
            ResultSet rs = objComprobante.listarMedioPago();
            while (rs.next()) {
                String mediopago = rs.getString("mediopago");
                cboMedioPago.addItem(mediopago);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar medio de pago -> " + e.getMessage(), "Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calcularVueltoAutomatico() {
        try {
            // 1. Obtener los valores de los campos de texto
            String strTotal = lblTotal.getText().trim();
            String strPagado = txtMontoPagado.getText().trim();

            // Asegurarse de que los campos no estén vacíos
            if (strTotal.isEmpty() || strPagado.isEmpty()) {
                lblVuelto.setText("0.00");
                return;
            }

            // 2. Convertir los textos a números (usando double para precisión monetaria)
            double totalAPagar = Double.parseDouble(strTotal);
            double montoPagado = Double.parseDouble(strPagado);

            // 3. Realizar la resta
            double vuelto = montoPagado - totalAPagar;

            // 4. Mostrar el resultado
            // Usamos String.format para mostrar el vuelto con dos decimales (formato de moneda)
            if (vuelto < 0) {
                // Si es negativo, significa que el cliente aún debe dinero (o pagó menos del total)
                lblVuelto.setText("FALTA: " + String.format("%.2f", Math.abs(vuelto)));
            } else {
                lblVuelto.setText(String.format("%.2f", vuelto));
            }

        } catch (NumberFormatException e) {
            // Manejar la excepción si el usuario ingresa un texto no válido
            lblVuelto.setText("Error");
            System.err.println("Error al convertir el monto: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel18 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        JpExportar = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblTipoComprobante = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblNumComprobante = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblTrabajador = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblFechaEmision = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblCliente = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblDocumentoIdentidad = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatosComprobante = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        lblSubTotal = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblIgv = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lblVuelto = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtMontoPagado = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lblPrecioLetras = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnExportarPdf = new javax.swing.JButton();
        btnExportarImg = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lblCodVentaCita = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        cboMedioPago = new javax.swing.JComboBox<>();

        jLabel18.setText("jLabel18");

        jLabel29.setFont(new java.awt.Font("Verdana", 3, 36)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("\"WASHYCAR\"");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        JpExportar.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setText("R.U.C. 10268574236");

        jPanel3.setBackground(new java.awt.Color(31, 41, 55));

        lblTipoComprobante.setFont(new java.awt.Font("Verdana", 1, 22)); // NOI18N
        lblTipoComprobante.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoComprobante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(lblTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblTipoComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel3.setText("N°");

        lblNumComprobante.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblNumComprobante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(33, 33, 33))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblNumComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblNumComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel5.setText("MANTENIMIENTO DE VEHICULOS");

        jLabel6.setFont(new java.awt.Font("Verdana", 3, 36)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("\"WASHYCAR\"");

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Av. Miguel Grau 892 - La Victoria - Chiclayo");

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Jr. Callao 134 - Miraflores - Chiclayo");

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Telf. 265-6091 / 998657423 / 968754123");

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Trabajador:");

        jPanel4.setBackground(new java.awt.Color(31, 41, 55));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        lblTrabajador.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblTrabajador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Fecha Emisión:");

        jPanel6.setBackground(new java.awt.Color(31, 41, 55));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 321, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        lblFechaEmision.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblFechaEmision.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Señor (es):");

        jPanel7.setBackground(new java.awt.Color(31, 41, 55));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        lblCliente.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel20.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("DNI / RUC:");

        jPanel9.setBackground(new java.awt.Color(31, 41, 55));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        lblDocumentoIdentidad.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblDocumentoIdentidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tblDatosComprobante.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tblDatosComprobante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblDatosComprobante);

        jLabel22.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("SubTotal:");

        lblSubTotal.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblSubTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel24.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("IGV:");

        lblIgv.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblIgv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIgv.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel26.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Total:");

        lblTotal.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel28.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Monto Pagado:");

        lblVuelto.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblVuelto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVuelto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel31.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Vuelto:");

        txtMontoPagado.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        txtMontoPagado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMontoPagado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoPagadoActionPerformed(evt);
            }
        });
        txtMontoPagado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoPagadoKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Son:");

        lblPrecioLetras.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblPrecioLetras.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/logo (1).png"))); // NOI18N

        javax.swing.GroupLayout JpExportarLayout = new javax.swing.GroupLayout(JpExportar);
        JpExportar.setLayout(JpExportarLayout);
        JpExportarLayout.setHorizontalGroup(
            JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpExportarLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpExportarLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(46, 46, 46)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JpExportarLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPrecioLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpExportarLayout.createSequentialGroup()
                                    .addComponent(jLabel22)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpExportarLayout.createSequentialGroup()
                                    .addComponent(jLabel24)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(JpExportarLayout.createSequentialGroup()
                                    .addComponent(jLabel28)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtMontoPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpExportarLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(JpExportarLayout.createSequentialGroup()
                        .addGap(860, 860, 860)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblVuelto, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpExportarLayout.createSequentialGroup()
                        .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpExportarLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(JpExportarLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(JpExportarLayout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(JpExportarLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDocumentoIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(1, 1, 1)))
                .addGap(38, 38, 38))
        );
        JpExportarLayout.setVerticalGroup(
            JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpExportarLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JpExportarLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(JpExportarLayout.createSequentialGroup()
                            .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, 0)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(JpExportarLayout.createSequentialGroup()
                            .addComponent(lblFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpExportarLayout.createSequentialGroup()
                        .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JpExportarLayout.createSequentialGroup()
                        .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDocumentoIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(0, 0, 0)
                .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIgv, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addGroup(JpExportarLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblPrecioLetras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(0, 0, 0)
                .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMontoPagado)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(JpExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(20, 20, 20))
        );

        jPanel5.setBackground(new java.awt.Color(31, 41, 55));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar.setBackground(new java.awt.Color(31, 41, 55));
        btnGuardar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar Comprobante");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnExportarPdf.setBackground(new java.awt.Color(31, 41, 55));
        btnExportarPdf.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnExportarPdf.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarPdf.setText("Exportar a PDF");
        btnExportarPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarPdfActionPerformed(evt);
            }
        });

        btnExportarImg.setBackground(new java.awt.Color(31, 41, 55));
        btnExportarImg.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnExportarImg.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarImg.setText("Exportar a JPG/PNG");
        btnExportarImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarImgActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Código Venta/Cita:");

        jLabel32.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Detalle");

        jPanel10.setBackground(new java.awt.Color(31, 41, 55));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        lblCodVentaCita.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblCodVentaCita.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel34.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Estado:");

        cboEstado.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pagado", "Pendiente" }));

        jLabel35.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Medio de Pago:");

        cboMedioPago.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboMedioPago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel35)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExportarPdf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExportarImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCodVentaCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCodVentaCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboMedioPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(311, 311, 311)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExportarPdf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExportarImg)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JpExportar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpExportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMontoPagadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoPagadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoPagadoActionPerformed

    private void txtMontoPagadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoPagadoKeyReleased
        calcularVueltoAutomatico();
    }//GEN-LAST:event_txtMontoPagadoKeyReleased

    private void btnExportarPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarPdfActionPerformed
        JPanel panelAExportar = this.JpExportar;
        exportarPanelAPdf(panelAExportar);

    }//GEN-LAST:event_btnExportarPdfActionPerformed

    private void btnExportarImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarImgActionPerformed
        JPanel panelAExportar = this.JpExportar;
        exportarPanelAImagen(panelAExportar);
    }//GEN-LAST:event_btnExportarImgActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        if (txtMontoPagado.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un monto a pagar", "Sistema", JOptionPane.WARNING_MESSAGE);
        } else if (Float.parseFloat(txtMontoPagado.getText()) < Float.parseFloat(lblTotal.getText())) {
            JOptionPane.showMessageDialog(this, "El monto pagado debe ser mayor al total", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else if (cboEstado.getSelectedItem().toString().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un estado del comprobante", "Sistema", JOptionPane.WARNING_MESSAGE);
        } else if (cboMedioPago.getSelectedItem().toString().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un medio de pago", "Sistema", JOptionPane.WARNING_MESSAGE);
        } else {
            // Ejemplo de obtención de datos del encabezado en tu JFrame
            String fecha = Nomfecha.toString();
            String hora = nomHora;
            String numComprobante = lblNumComprobante.getText();
            String estado = cboEstado.getSelectedItem().toString(); // "Pagado" o "Pendiente"
            String tipoComprobante = NomtipoComprobante;
            int idCita = Integer.parseInt(lblCodVentaCita.getText()); // Asumiendo que es un campo de texto
            String medioPago = cboMedioPago.getSelectedItem().toString();
            String trabajador = lblTrabajador.getText();
            String cliente = lblCliente.getText();

            LocalDate fechaCorta;
            fechaCorta = LocalDate.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd", new Locale("es", "ES"));
            
            String fechaFormateada = fechaCorta.format(formato);

            DefaultTableModel modelo = (DefaultTableModel) tblDatosComprobante.getModel();

            List<Object[]> listaDetalles = new ArrayList<>();

            for (int i = 0; i < modelo.getRowCount(); i++) {
                try {
                    String nombreProducto = modelo.getValueAt(i, 1).toString();
                    int cantidad = Integer.parseInt(modelo.getValueAt(i, 0).toString());
                    float valorVenta = Float.parseFloat(modelo.getValueAt(i, 4).toString());

                    Object[] filaDetalle = {nombreProducto, cantidad, valorVenta};

                    listaDetalles.add(filaDetalle);

                } catch (NumberFormatException e) {
                    System.err.println("Error de formato en la fila " + (i + 1) + ": " + e.getMessage());
                    return;
                }
            }

            try {
                objComprobante.transaccion(
                        fechaFormateada, hora, numComprobante, estado,
                        tipoComprobante, idCita, medioPago, trabajador, cliente,
                        listaDetalles
                );

                JOptionPane.showMessageDialog(this, "Transacción registrada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en la transacción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpExportar;
    private javax.swing.JButton btnExportarImg;
    private javax.swing.JButton btnExportarPdf;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JComboBox<String> cboMedioPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblCodVentaCita;
    private javax.swing.JLabel lblDocumentoIdentidad;
    private javax.swing.JLabel lblFechaEmision;
    private javax.swing.JLabel lblIgv;
    private javax.swing.JLabel lblNumComprobante;
    private javax.swing.JLabel lblPrecioLetras;
    private javax.swing.JLabel lblSubTotal;
    private javax.swing.JLabel lblTipoComprobante;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTrabajador;
    private javax.swing.JLabel lblVuelto;
    private javax.swing.JTable tblDatosComprobante;
    private javax.swing.JTextField txtMontoPagado;
    // End of variables declaration//GEN-END:variables
}
