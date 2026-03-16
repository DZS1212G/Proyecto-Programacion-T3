
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

    public void añadirContacto(Map<String, Integer> inventario) {
        for (String nombre : inventario.keySet()) {
            if (jtextfiel.gettext == nombre) {
                if (JOptionPane.showConfirmDialog(this, "Este nombre ya esta registrado\n desea modificar los datos?", "Modificar", 0) == 0) {
                    modificarContacto();
                    return;
                } else {
                    return;
                }
            }
        }
        agenda.put();
    }
}
