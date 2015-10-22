/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;



/**
 *
 * @author Agustin
 */
public class CambioDolar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
       ConexionBD.conectar(args[5], args[4], args[0], args[6], args[1]);
       frmCambioDolar cd=new frmCambioDolar(args);
       cd.setSize(400, 500);
       cd.setLocationRelativeTo(null);
       cd.setDefaultCloseOperation(cd.EXIT_ON_CLOSE);
       cd.setVisible(true);
       
    }
    
}
