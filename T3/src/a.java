
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DZS1212
 */
public class a {
    public static void main(String[] args) {
//         int p=JOptionPane.showConfirmDialog(null, "Este nombre ya esta registrado\n desea modificar los datos?", "Modificar", 0);
//         System.out.println(p);
         
         if(JOptionPane.showConfirmDialog(null, "Este nombre ya esta registrado\n desea modificar los datos?", "Modificar", 0)==0){
               System.out.println("si");
            } else{
             System.out.println("puta");
         }
    }
}
