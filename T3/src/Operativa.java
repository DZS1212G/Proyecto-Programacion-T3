
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author DZS1212
 */
public class Operativa {

    Map<String, Integer> agenda = new HashMap<>();

    public void añadirContacto() {
        for (String nombre : agenda.keySet()) {
            if (jtextfiel.gettext == nombre) {
                if (JOptionPane.showConfirmDialog(this, "Este nombre ya esta registrado\n desea modificar los datos?", "Modificar", 0) == 0) {
                    modificarContacto();
                }
            }
        }
        agenda.put(jtextfiel.gettext, jtextfiel.gettext);
    }

    public void modificarContacto() {
        for (String nombre : agenda.keySet()) {
            if (jtextfiel.gettext == nombre) {
                agenda.replace(jtextfiel.gettext, jtextfiel.gettext);
                return;

            }
        }

        JOptionPane.showMessageDialog(this, "Este nombre no esta registrado", "Error", 0);
    }

    public void eliminarContacto() {
        for (String nombre : agenda.keySet()) {
            if (jtextfiel.gettext == nombre) {
                agenda.remove(jtextfiel.gettext, jtextfiel.gettext);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Este nombre no esta registrado", "Error", 0);
    }

    public void buscarContacto() {
        String nombreaBuscar = JOptionPane.showInputDialog(this, "Este nombre no esta registrado", "Error", 0);
        for (String nombre : agenda.keySet()) {
            if (nombreaBuscar == nombre) {
                this.jtextfield.setText(agenda.get(nombre));
            }
        }
        JOptionPane.showMessageDialog(this, "Este nombre no esta registrado", "Error", 0);
    }
}
