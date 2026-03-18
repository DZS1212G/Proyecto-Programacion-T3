package Agenda;

import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import java.util.TreeMap;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author zapsobdi
 */
public class GUIAgenda extends javax.swing.JFrame {

    private DefaultListModel<Contacto> modelo; //modelo creado para la jList
    private boolean aniadir; //boolean que indica que se ha activado el modo a?adir
    private boolean buscar; //boolean que indica que se ha activado el modo buscar
    private boolean modificar; //boolean que indica que se ha activado el modo modificar
    private boolean borrar; //boolean que indica que se ha activado el modo borrar
    private String nombreAMod; //Cadena que sive para seleccionar que contacto se va a editar
    Map<String, Integer> agenda; //Mapa que almacena nombres y numero de los contactos

    /**
     * Creates new form GUIAgenda
     */
    public GUIAgenda() {
        initComponents();
        agenda = new TreeMap<>(); //creo el modelo y el mapa
        modelo = new DefaultListModel<>();
        setFrame();
    }

    private void setFrame() {
        this.setTitle("Agenda 1 DAM GF");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.Contacto.setVisible(false); //desaparezco los campos para introducir y que se activen segun la funcion
        this.BarraDeEstado.setText("Bienvenido a la Agenda");

    }

    private void validNombre() throws Exception { //validacion del nombre
        if (this.jTextFieldNombre.getText().isBlank() || this.jTextFieldNombre.getText() == null) {
            throw new Exception("La casilla del nombre esta vacia");
        }
    }

    private void validNum() throws Exception {
        if (this.jTextFieldTelefono.getText().isBlank() || this.jTextFieldTelefono.getText() == null) {
            throw new Exception("La casilla del numero esta vacia");
        } else {
            if (!buscar) {
                if (!jTextFieldTelefono.getText().matches("\\d{9}")) { //valida si la cadena se trata de 9 numeros enteros
                    throw new Exception("Su telefono no contiene un formato adecuado (9 Numeros)");
                }
            }

        }
    }

    private void aniadirContacto() throws Exception { // metodo para a?adir contactos
        validNombre();
        validNum();
        for (String nombre : agenda.keySet()) {
            if (jTextFieldNombre.getText().equals(nombre)) { //si encuentra un nombre igual en el mapa te pregunta si quieres modificarlo
                if (JOptionPane.showConfirmDialog(this, "Este nombre ya esta registrado\n desea modificar los datos?", "Modificar", 0) == 0) {
                    nombreAMod = nombre;
                    modificarContacto();
                } else {
                    throw new Exception("No se a?adio ningun contacto");
                }
            }
        }
        agenda.put(jTextFieldNombre.getText(), Integer.valueOf(jTextFieldTelefono.getText())); //agrega al mapa
        this.BarraDeEstado.setText("Contacto " + jTextFieldNombre.getText() + " aniadido correctamente");
    }

    private void modificarContacto() throws Exception {
        validNombre();
        for (String nombre : agenda.keySet()) {
            if (nombreAMod.equals(nombre)) {
                agenda.replace(jTextFieldNombre.getText(), Integer.valueOf(jTextFieldTelefono.getText()));
                this.BarraDeEstado.setText("Nombre Modificado Correctamente");
                return;
            }
        }
        throw new Exception("Este nombre no esta registrado");
    }

    private void conseguirNombreaMod() {
        this.nombreAMod = JOptionPane.showInputDialog(this, "Introduzca el nombre que desea modificar");

        while (this.nombreAMod == null || this.nombreAMod.isBlank()) {
            this.nombreAMod = JOptionPane.showInputDialog(this, "Nombre no valido, introduzca de nuevo");
        }

        for (String nombre : agenda.keySet()) {
            if (nombreAMod.equals(nombre)) {
                return;
            }
            this.BarraDeEstado.setText("Este nombre no esta registrado");
            desactivarOpciones();
        }

    }

    private void eliminarContacto() throws Exception { //elimina el contacto que se ha elegido
        validNombre();
        for (String nombre : agenda.keySet()) {
            if (jTextFieldNombre.getText().equals(nombre)) {
                if (JOptionPane.showConfirmDialog(this, "Esta seguro de querer eliminar el contacto?", "Borrar", 0) == 0) {

                    agenda.remove(nombre);
                    this.BarraDeEstado.setText("Contacto " + nombre + " borrado correctamente");
                }
                return;

            }
        }

        throw new Exception("Este nombre no esta registrado");
    }

    private void buscarContacto() throws Exception {
        validNombre();

        for (String nombre : agenda.keySet()) {
            if (jTextFieldNombre.getText().equals(nombre)) {
                this.BarraDeEstado.setText("El numero de telefono asociado es: " + String.valueOf(agenda.get(nombre)));
                return;
            }
        }

        throw new Exception("Este nombre no esta registrado");
    }

    private void listar() {
        for (String nombre : agenda.keySet()) {
            Contacto c = new Contacto(nombre, agenda.get(nombre));
            modelo.addElement(c);
        }
        jList.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        BarraDeEstado = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Agenda = new javax.swing.JScrollPane();
        jList = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        Contacto = new javax.swing.JPanel();
        jButtonAceptar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabelNombre = new javax.swing.JLabel();
        jLabelTelefono = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldTelefono = new javax.swing.JTextField();
        jLabelCantidad = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenuContacto = new javax.swing.JMenu();
        jMenuItemAniadir = new javax.swing.JMenuItem();
        jMenuItemBuscar = new javax.swing.JMenuItem();
        jMenuItemModificar = new javax.swing.JMenuItem();
        jMenuItemBorrar = new javax.swing.JMenuItem();
        jMenuAgenda = new javax.swing.JMenu();
        jMenuItemListar = new javax.swing.JMenuItem();
        jMenuItemVaciar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSalir = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(BarraDeEstado, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Agenda.setViewportView(jList);

        Contacto.setBorder(javax.swing.BorderFactory.createTitledBorder("Contacto"));

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jLabelNombre.setText("Nombre:");

        jLabelTelefono.setText("Telefono:");

        javax.swing.GroupLayout ContactoLayout = new javax.swing.GroupLayout(Contacto);
        Contacto.setLayout(ContactoLayout);
        ContactoLayout.setHorizontalGroup(
            ContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContactoLayout.createSequentialGroup()
                .addGroup(ContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ContactoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar))
                    .addGroup(ContactoLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(ContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombre)
                            .addComponent(jLabelTelefono))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(jTextFieldTelefono))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        ContactoLayout.setVerticalGroup(
            ContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContactoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(ContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefono)
                    .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(ContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAceptar)
                    .addComponent(jButtonCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(Contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Agenda, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabelCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Agenda, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jMenuContacto.setText("Contacto");

        jMenuItemAniadir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemAniadir.setText("Anadir");
        jMenuItemAniadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAniadirActionPerformed(evt);
            }
        });
        jMenuContacto.add(jMenuItemAniadir);

        jMenuItemBuscar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemBuscar.setText("Buscar");
        jMenuItemBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBuscarActionPerformed(evt);
            }
        });
        jMenuContacto.add(jMenuItemBuscar);

        jMenuItemModificar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemModificar.setText("Modificar");
        jMenuItemModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemModificarActionPerformed(evt);
            }
        });
        jMenuContacto.add(jMenuItemModificar);

        jMenuItemBorrar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemBorrar.setText("Borrar");
        jMenuItemBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBorrarActionPerformed(evt);
            }
        });
        jMenuContacto.add(jMenuItemBorrar);

        jMenuBar2.add(jMenuContacto);

        jMenuAgenda.setText("Agenda");

        jMenuItemListar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemListar.setText("Listar");
        jMenuItemListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemListarActionPerformed(evt);
            }
        });
        jMenuAgenda.add(jMenuItemListar);

        jMenuItemVaciar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemVaciar.setText("Vaciar");
        jMenuItemVaciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVaciarActionPerformed(evt);
            }
        });
        jMenuAgenda.add(jMenuItemVaciar);
        jMenuAgenda.add(jSeparator1);

        jMenuItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemSalir.setText("Salir");
        jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalirActionPerformed(evt);
            }
        });
        jMenuAgenda.add(jMenuItemSalir);

        jMenuBar2.add(jMenuAgenda);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        try {
            if (aniadir) {
                aniadirContacto();
                this.Contacto.setVisible(false);
            } else if (borrar) {
                eliminarContacto();
                this.Contacto.setVisible(false);
            } else if (modificar) {
                modificarContacto();
                this.Contacto.setVisible(false);
            } else if (buscar) {
                buscarContacto();
                this.Contacto.setVisible(false);
            }
        } catch (Exception ex) {
            this.BarraDeEstado.setText(ex.getMessage());
        }

    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItemSalirActionPerformed

    private void jMenuItemAniadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAniadirActionPerformed
        desactivarOpciones();
        this.Contacto.setVisible(true);
        this.aniadir = true;
    }//GEN-LAST:event_jMenuItemAniadirActionPerformed

    private void jMenuItemBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBuscarActionPerformed
        desactivarOpciones();
        this.jLabelTelefono.setVisible(false);
        this.jTextFieldTelefono.setVisible(false);
        this.Contacto.setVisible(true);
        this.buscar = true;
    }//GEN-LAST:event_jMenuItemBuscarActionPerformed

    private void jMenuItemModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModificarActionPerformed
        desactivarOpciones();
        this.Contacto.setVisible(true);
        this.modificar = true;
        conseguirNombreaMod();
    }//GEN-LAST:event_jMenuItemModificarActionPerformed

    private void jMenuItemBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBorrarActionPerformed
        desactivarOpciones();
        this.Contacto.setVisible(true);
        this.jLabelTelefono.setVisible(false);
        this.jTextFieldTelefono.setVisible(false);
        this.borrar = true;
    }//GEN-LAST:event_jMenuItemBorrarActionPerformed

    private void jMenuItemListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemListarActionPerformed

        modelo.clear();
        listar();
        this.jLabelCantidad.setText("Hay " + String.valueOf(agenda.size()) + " contactos");
    }//GEN-LAST:event_jMenuItemListarActionPerformed

    private void jMenuItemVaciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVaciarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Estas seguro de querer eliminar la lista por completo?", "ELIMINAR", 0) == 0) {
            agenda.clear();
            modelo.clear();
        }
    }//GEN-LAST:event_jMenuItemVaciarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        desactivarOpciones();
        this.Contacto.setVisible(false);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void desactivarOpciones() {
        this.borrar = false;
        this.aniadir = false;
        this.modificar = false;
        this.buscar = false;
        this.jTextFieldTelefono.setFocusable(true);
        this.jTextFieldNombre.setText("");
        this.jTextFieldTelefono.setText("");
        this.jLabelTelefono.setVisible(true);
        this.jTextFieldTelefono.setVisible(true);
        this.nombreAMod = null;
        this.Contacto.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Agenda;
    private javax.swing.JLabel BarraDeEstado;
    private javax.swing.JPanel Contacto;
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JLabel jLabelCantidad;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelTelefono;
    private javax.swing.JList<Contacto> jList;
    private javax.swing.JMenu jMenuAgenda;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenu jMenuContacto;
    private javax.swing.JMenuItem jMenuItemAniadir;
    private javax.swing.JMenuItem jMenuItemBorrar;
    private javax.swing.JMenuItem jMenuItemBuscar;
    private javax.swing.JMenuItem jMenuItemListar;
    private javax.swing.JMenuItem jMenuItemModificar;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenuItem jMenuItemVaciar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldTelefono;
    // End of variables declaration//GEN-END:variables
}
