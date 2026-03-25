package Agenda;

import java.awt.Color;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.LinkedHashMap;
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
    Map<String, Contacto> agenda; //Mapa que almacena nombres y numero de los contactos

    /**
     * Creates new form GUIAgenda
     */
    public GUIAgenda() {
        initComponents();
        agenda = new LinkedHashMap<>(); //creo el modelo y el mapa
        modelo = new DefaultListModel<>();
        setFrame();
    }

    private void setFrame() { //metodo inicial para dar algo de estructura a la ventana
        this.setTitle("Agenda 1 DAM GF");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.jPanelContacto.setVisible(false); //desaparezco los campos para introducir y que se activen segun la funcion
        this.jLabelBarraDeEstado.setText("Bienvenido a la Agenda");
    }

    private void validNombre() throws Exception { //validacion del nombre
        if (this.jTextFieldNombre.getText().isBlank() || this.jTextFieldNombre.getText() == null) { //valida que los datos no esten vacios
            throw new Exception("La casilla del nombre esta vacia");
        }
    }

    private void validNum() throws Exception { //validacion del numero de telefono
        if (this.jTextFieldTelefono.getText().isBlank() || this.jTextFieldTelefono.getText() == null) { //valida que los datos no esten vacios
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

        if (agenda.containsKey(jTextFieldNombre.getText())) { //si encuentra un nombre igual en el mapa te pregunta si quieres modificarlo
            if (JOptionPane.showConfirmDialog(this, "Este nombre ya esta registrado\n desea modificar los datos?", "Modificar", 0) == 0) {
                nombreAMod = jTextFieldNombre.getText();
                modificarContacto();
            } else {
                throw new Exception("No se modifico ningun contacto");
            }
        }
        Contacto c = new Contacto(jTextFieldNombre.getText(), Integer.parseInt(jTextFieldTelefono.getText()));
        agenda.put(jTextFieldNombre.getText(), c); //agrega al mapa
        this.jLabelBarraDeEstado.setText("Contacto " + jTextFieldNombre.getText() + " aniadido correctamente"); //mensaje que muestra que fue exitoso
    }

    private void modificarContacto() throws Exception { //metodo que modifica el contacto una vez se ha conseguido el nombre
        validNombre();
        validNum();
        Contacto newContact = new Contacto(jTextFieldNombre.getText(), Integer.parseInt(jTextFieldTelefono.getText()));
        for (Contacto cont : agenda.values()) {
            if (cont.getNombre().equals(nombreAMod)) {
                newContact.setFechaRegistro(cont.getFechaRegistro());
                agenda.replace(nombreAMod, cont, newContact);
                return;
            }
        }
        this.jLabelBarraDeEstado.setText("Nombre Modificado Correctamente");
    }

    private void conseguirNombreaMod() { //metodo que lanza un jpane para conseguir aquel contacto que se quiera modificar y se a?ade a la variable nombreAMod
        this.nombreAMod = JOptionPane.showInputDialog(this, "Introduzca el nombre que desea modificar", "Modificar", 3);

        while (this.nombreAMod == null || this.nombreAMod.isBlank()) {
            this.nombreAMod = JOptionPane.showInputDialog(this, "Nombre no valido, introduzca de nuevo", "Modificar", 3);
        }

        if (!agenda.containsKey(nombreAMod)) {
            this.jLabelBarraDeEstado.setForeground(Color.red);
            this.jLabelBarraDeEstado.setText("Este nombre no esta registrado");
            this.jPanelContacto.setVisible(false);
        } else {
            this.jTextFieldNombre.setText(nombreAMod);
            this.jTextFieldTelefono.setText(String.valueOf(agenda.get(nombreAMod).getTelefono()));
        }
    }

    private void eliminarContacto() throws Exception { //elimina el contacto que se ha elegido
        validNombre();
        if (agenda.containsKey(jTextFieldNombre.getText())) { //compruebo si el contacto existe
            if (JOptionPane.showConfirmDialog(this, "Esta seguro de querer eliminar el contacto?", "Borrar", 0) == 0) { //confirmo que si quiere borrarlo
                agenda.remove(jTextFieldNombre.getText());
                this.jLabelBarraDeEstado.setText("Contacto " + jTextFieldNombre.getText() + " borrado correctamente");
            }
            return;

        }

        throw new Exception("Este nombre no esta registrado");
    }

    private void buscarContacto() throws Exception { //metodo que recoge el nombre y te devuelve el numero
        validNombre();

        if (agenda.containsKey(jTextFieldNombre.getText())) { //si lo encuentra en la agenda que nos devuelva el nombre
            this.jLabelBarraDeEstado.setText("El numero de telefono asociado es: " + String.valueOf(agenda.get(jTextFieldNombre.getText())));

        } else {
            throw new Exception("Este nombre no esta registrado");
        }
    }

    private void mostrarAgenda() { //metodo que sirve para imprimir la agenda en el jlist limpiando el modelo
        modelo.clear(); //limpio el modelo para iniciar de 0
        for (Contacto nombre : agenda.values()) { //un bule for each que almacena todos los conactos y los a?ade al modelo 
            modelo.addElement(nombre);
        }

        jListAgenda.setModel(modelo);
        this.jLabelCantidad.setText("Hay " + String.valueOf(agenda.size()) + " contactos");
    }

    private void listar() { //metodo que ordena el mapa convirtiendolo en un tree map que por defecto viene ordenado
        Map<String, Contacto> mapaOrdenado = new TreeMap<>(agenda); //creo un tree map debido a que estos se ordenan automaticamente por caracter ascii de la key en este caso el nombre
        agenda = new LinkedHashMap<>(mapaOrdenado); //sobrescribo el mapa original por el ordenado
        mostrarAgenda();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelBarraDeEstado = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPaneAgenda = new javax.swing.JScrollPane();
        jListAgenda = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        jPanelContacto = new javax.swing.JPanel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelBarraDeEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabelBarraDeEstado, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jListAgenda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jListAgenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListAgendaMouseClicked(evt);
            }
        });
        jScrollPaneAgenda.setViewportView(jListAgenda);

        jPanelContacto.setBorder(javax.swing.BorderFactory.createTitledBorder("Contacto"));

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

        javax.swing.GroupLayout jPanelContactoLayout = new javax.swing.GroupLayout(jPanelContacto);
        jPanelContacto.setLayout(jPanelContactoLayout);
        jPanelContactoLayout.setHorizontalGroup(
            jPanelContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContactoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelContactoLayout.createSequentialGroup()
                        .addGroup(jPanelContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombre)
                            .addComponent(jLabelTelefono))
                        .addGroup(jPanelContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelContactoLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelContactoLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jTextFieldTelefono))))
                    .addGroup(jPanelContactoLayout.createSequentialGroup()
                        .addComponent(jButtonAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanelContactoLayout.setVerticalGroup(
            jPanelContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContactoLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanelContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefono)
                    .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addComponent(jPanelContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanelContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jLabelCantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPaneAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jScrollPaneAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jMenuContacto.setText("Contacto");

        jMenuItemAniadir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
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
//boton aceptar que segun que este seleccionado ejecuta un metodo u otro
//en cada uno se devuelve el color original a la barra de estado
        try { //segun que metodo este activado ejecuta una cosa u otra
            this.jLabelBarraDeEstado.setForeground(Color.BLACK);
            if (aniadir) {
                aniadirContacto();
                this.jPanelContacto.setVisible(false);
                mostrarAgenda();
            } else if (borrar) {
                eliminarContacto();
                this.jPanelContacto.setVisible(false);
                mostrarAgenda();
            } else if (modificar) {
                modificarContacto();
                this.jPanelContacto.setVisible(false);
                mostrarAgenda();
            } else if (buscar) {
                buscarContacto();
                this.jPanelContacto.setVisible(false);
            }
        } catch (Exception ex) {
            this.jLabelBarraDeEstado.setForeground(Color.red); //siempre que pille un error salta y lo pone en rojo
            this.jLabelBarraDeEstado.setText(ex.getMessage()); //recoge la excepcion que haya salido en ese momento y la imprime en color rojo
        }

    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed
        this.dispose(); //cierra la ventana
    }//GEN-LAST:event_jMenuItemSalirActionPerformed

    private void jMenuItemAniadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAniadirActionPerformed
        desactivarOpciones(); //activa la funcion aniadir
        this.aniadir = true;
    }//GEN-LAST:event_jMenuItemAniadirActionPerformed

    private void jMenuItemBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBuscarActionPerformed
        desactivarOpciones(); //activa la funcion buscar desactivando los campos de telefono al ser ineccesarios
        this.jLabelTelefono.setVisible(false);
        this.jTextFieldTelefono.setVisible(false);
        this.buscar = true;
    }//GEN-LAST:event_jMenuItemBuscarActionPerformed

    private void jMenuItemModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModificarActionPerformed
        desactivarOpciones(); //activa la funcion modificar y llama al metodo que consigue el contacto que se desea modificar
        this.modificar = true;
        conseguirNombreaMod();
    }//GEN-LAST:event_jMenuItemModificarActionPerformed

    private void jMenuItemBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBorrarActionPerformed
        desactivarOpciones(); //activa la funcion borrar desactivando los campos de telefono al ser ineccesarios
        this.jLabelTelefono.setVisible(false);
        this.jTextFieldTelefono.setVisible(false);
        this.borrar = true;
    }//GEN-LAST:event_jMenuItemBorrarActionPerformed

    private void jMenuItemListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemListarActionPerformed
        desactivarOpciones();
        this.jPanelContacto.setVisible(false);
        listar(); //accede al metodo listar
    }//GEN-LAST:event_jMenuItemListarActionPerformed

    private void jMenuItemVaciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVaciarActionPerformed
        desactivarOpciones(); //primero confirma si se quiere eliminar, si es correcta elimina el mapa
        this.jPanelContacto.setVisible(false);
        if (JOptionPane.showConfirmDialog(this, "Estas seguro de querer eliminar la lista por completo?", "ELIMINAR", 0) == 0) {
            agenda.clear();
            mostrarAgenda();
            this.jLabelBarraDeEstado.setForeground(Color.GREEN);
            this.jLabelBarraDeEstado.setText("Agenda eliminada exitosamente");
        }
    }//GEN-LAST:event_jMenuItemVaciarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        desactivarOpciones();
        this.jPanelContacto.setVisible(false);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jListAgendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListAgendaMouseClicked
        if (evt.getClickCount() == 2) {
            Contacto select = (this.jListAgenda.getSelectedValue());
            DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.MEDIUM);
            JOptionPane.showMessageDialog(this, "Contacto creado el " + dtf.format(select.getFechaRegistro()), "Creacion", 1);
        }
    }//GEN-LAST:event_jListAgendaMouseClicked

    private void desactivarOpciones() {
        //metodo global el cual deja todas las opciones por defecto para que no haya problemas con los booleans que activan los metodos
        this.borrar = false; //desactivo los cuatro booleanos de los modos
        this.aniadir = false;
        this.modificar = false;
        this.buscar = false;
        this.jTextFieldNombre.setText(""); //limpio los text fielda
        this.jTextFieldTelefono.setText("");
        this.jLabelTelefono.setVisible(true); //reactivo las dos de telefono por que ambas se ocultan en ciertos metodos
        this.jTextFieldTelefono.setVisible(true);
        this.nombreAMod = null; //borro la variable nombre a mod para que no interfiera en metodos como modificar o aniadir
        this.jPanelContacto.setVisible(true); //vuelvo visible conctacto para que funcione en los metodos
        this.jLabelBarraDeEstado.setText(""); //limpio la barra de estado
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JLabel jLabelBarraDeEstado;
    private javax.swing.JLabel jLabelCantidad;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelTelefono;
    private javax.swing.JList<Contacto> jListAgenda;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelContacto;
    private javax.swing.JScrollPane jScrollPaneAgenda;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldTelefono;
    // End of variables declaration//GEN-END:variables
}
