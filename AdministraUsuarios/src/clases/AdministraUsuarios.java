
package clases;

import javax.swing.JOptionPane;

/**
 *
 * @author PINELL
 */
public class AdministraUsuarios {

   
    
    public static void main(String[] args) {
        // TODO code application logic here
        //JOptionPane.showMessageDialog(null,"Parametros: "+args[0]+"-"+args[1]+"-"+args[2]+"-"+args[3]+"-"+args[4]+"-"+args[5]+"-"+args[6]);
        
        
        //ConexionBD.conectar(args[5], args[4], args[0], args[6], args[1]);
        
        ConexionBD.conectar(args[5], args[4], "sistemas.fdb", args[6], args[1]);
        //frmAdministra fad=new frmAdministra(args[3],args[2]);
        frmAdministra fad=new frmAdministra(args);
        
        fad.setVisible(true);
    }
    
}
