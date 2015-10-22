
package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 * Clase para conexiones a Base de datos, y consultas Insert,Update,Delete,Select
 * @author Pinell
 */
public class ConexionBD {
   
    
    public static Connection cnn;
    public static Connection cnn2;
    
  
    /**
     * Funcion para conectar a una base de datos Interbase/FireBird, la conexion se llama cnn
     * @param servidor parametro tipo String que recibe el IP no nombre del servidor al que queremos conectar
     * @param instancia parametro tipo String que recibe el nombre de la instancia donde está la base de datos
     * @param baseDatos parametro tipo String que recibe el nombre de la base de datos con extensión .fdb
     * @param usuariodb parametro tipo String que recibe el nombre de usuario para conectar a la base de datos
     * @param passdb parametro tipo String que recibe la contraseña para conectar a la base de datos
     */
    public static void conectar(String servidor,String instancia,String baseDatos,String usuariodb,String passdb){
        
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            String db2 = "jdbc:firebirdsql:" + servidor + "/3050:/www/root/" + instancia + "/db/" + baseDatos;
            cnn = DriverManager.getConnection(db2,usuariodb,passdb);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Ocurrio un error en conectar: " +ex.getMessage());
            //Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    
    /**
     * Funcion para conectar a una base de datos Interbase/FireBird, la conexion se llama cnn2
     * @param servidor parametro tipo String que recibe el IP no nombre del servidor al que queremos conectar
     * @param instancia parametro tipo String que recibe el nombre de la instancia donde está la base de datos
     * @param baseDatos parametro tipo String que recibe el nombre de la base de datos con extensión .fdb
     * @param usuariodb parametro tipo String que recibe el nombre de usuario para conectar a la base de datos
     * @param passdb parametro tipo String que recibe la contraseña para conectar a la base de datos
     */
    public static void conectar2(String servidor,String instancia,String baseDatos,String usuariodb,String passdb){
        
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            String db2 = "jdbc:firebirdsql:" + servidor + "/3050:/www/root/" + instancia + "/db/" + baseDatos;
            cnn2 = DriverManager.getConnection(db2,usuariodb,passdb);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Ocurrio un error en conectar: " +ex.getMessage());
            //Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    
    /**
     * Funcion que permite validar el acceso de un usuario al sistema
     * @param usuario Parametro tipo String nombre de usuario para ingresar al sistema
     * @param clave Parametro tipo string clave de usuario para ingresar al sistema
     * @return Retorna True si se encontro un registro con el usuario y clave enviados, False si no se encontro nada 
     */
    public static boolean validarUsuario(String usuario, String clave){
        ClaseMD5 llamaMD5 = new ClaseMD5();
       String claveMD5 = llamaMD5.getMD5(clave);
        
        try {
            String sql = "Select (1) from cousuario where login='"
                    + usuario +"' and pass = '"+ claveMD5 +"'";
            Statement st=cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException ex) {
            //Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Ocurrio un error en validarUsuario: " +ex.getMessage());
            return false;
        }
        
     }
    
     /**
      * Funcion que cierra la conexión de nombre cnn
      */
    public static void cierraConexion(){
        try {
            cnn.close();
        } catch (SQLException ex) {
            //Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Ocurrio un error en cierraConexion: " +ex.getMessage());
        }
    }
     
     /**
      * Funcion que cierra la conexión de nombre cnn2
      */
    public static void cierraConexion2(){
        try {
            cnn2.close();
        } catch (SQLException ex) {
            //Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Ocurrio un error en cierraConexion2: " +ex.getMessage());
        }
    }
     
     /**
      * Función que ejecuta una consulta SQL de tipo SELECT con la conexion cnn
      * @param consulta Parametro de tipo String que recibe la consulta(query) en lenguaje SQL
      * @return Retorna un objeto tipo ResultSet que contiene el resultado de la consulta
      */
     public static ResultSet querySelect(String consulta){
        try {
            
            Statement st=cnn.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
                return rs;
           
            
        } catch (SQLException ex) {
            //Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Ocurrio un error en querySelect: " +ex.getMessage());
        }
        return null;

   }
     
     
     /**
      * Función que ejecuta una consulta SQL de tipo SELECT con la conexion cnn2
      * @param consulta Parametro de tipo String que recibe la consulta(query) en lenguaje SQL
      * @return Retorna un objeto tipo ResultSet que contiene el resultado de la consulta
      */
     public static ResultSet querySelect2(String consulta){
        try {
            
            Statement st2=cnn2.createStatement();
            ResultSet rs2 = st2.executeQuery(consulta);
            
                return rs2;
           
            
        } catch (SQLException ex) {
            //Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Ocurrio un error en querySelect: " +ex.getMessage());
        }
        return null;

   }
    
    /**
     * Funcion que ejecuta una instruccion SQL para Insert,Update y Delete con conexion cnn
     * @param consulta Parametro tipo String que recibe la instrucción SQL 
     * @throws SQLException Si existe error y autoCommit=FALSE hace rollback y finaliza la ejecución del programa
     * Si existe error y autoCommit=TRUE, solo despliega el error en pantalla
     */ 
    public static void queryInsertUpdateDelete(String consulta) throws SQLException{
        try {
            
            Statement st=(Statement) cnn.createStatement();
            st.execute(consulta);
            
                st.close();
                 
        } catch (SQLException ex) {
            if(!cnn.getAutoCommit()){
                cnn.rollback();
                cnn.setAutoCommit(true);
                JOptionPane.showMessageDialog(null,"Ocurrio un error en queryInsertUpdateDelete,No se guardará ningun registro: "+ex.getMessage());
                System.exit(0);
            }else{
                JOptionPane.showMessageDialog(null,"Ocurrio un error en queryInsertUpdateDelete,No se guardará el último registro: "+ex.getMessage());
            }
          
            
        }
        

   }
    
   
    /**
     * Funcion que ejecuta una instruccion SQL para Insert,Update y Delete con conexion cnn2
     * @param consulta Parametro tipo String que recibe la instrucción SQL 
     * @throws SQLException Si existe error y autoCommit=FALSE hace rollback y finaliza la ejecución del programa
     * Si existe error y autoCommit=TRUE, solo despliega el error en pantalla
     */ 
    public static void queryInsertUpdateDelete2(String consulta) throws SQLException{
        try {
            
            Statement st2=(Statement) cnn2.createStatement();
            st2.execute(consulta);
            
                st2.close();
                 
        } catch (SQLException ex) {
            if(!cnn2.getAutoCommit()){
                cnn2.rollback();
                cnn2.setAutoCommit(true);
                JOptionPane.showMessageDialog(null,"Ocurrio un error en queryInsertUpdateDelete2,No se guardará ningun registro: "+ex.getMessage());
                System.exit(0);
            }else{
                JOptionPane.showMessageDialog(null,"Ocurrio un error en queryInsertUpdateDelete2,No se guardará el último registro: "+ex.getMessage());
            }
          
            
            
        }
        

   }
    
    /**
     * Funcion que retorna la fecha actual del servidor en formato AAAA-MM-DD
     * @return Fecha actual en formato AAAA-MM-DD
     */
    public static String fechaActualServidor(){
        ResultSet rst;
        String query="SELECT current_date FROM rdb$database";
        
        try {
            rst=ConexionBD.querySelect(query);
            while(rst.next()){
                return rst.getString("current_date");
            }
            rst.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en fechaActual: "+ex.getMessage());
            
        }
        return null;
    }
     
    /**
     * Funcion que retorna el Tipo De Cambio Dolar de una fecha que se ingrese
     * @param fecha Parametro tipo String para la fecha en formato MM/DD/YYYY como recibe FireBird
     * @return Retorna el tipo de cambio de la fecha introducida. Retorna 0 si no encuentra.
     */
    public static String obtieneDolar(String fecha){
        ResultSet rst;
        String dolar;
        String query="SELECT CAMBIO FROM CODOLAR WHERE FECHA='"+fecha+"'";
        try {
            rst=ConexionBD.querySelect(query);
            if(rst.next()){
                dolar=rst.getString("CAMBIO");
                rst.close();
                return dolar;
            }else{
                dolar="0";
                rst.close();
                return dolar;
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en obtieneDolar: "+ex.getMessage());
            
        }
        return null;
        
    }
    
    /**
     * Funcion que retorna el Tipo De Cambio UFV de una fecha que se ingrese
     * @param fecha Parametro tipo String para la fecha en formato MM/DD/YYYY como recibe FireBird
     * @return Retorna el tipo de cambio UFV de la fecha introducida. Retorna 0 si no encuentra.
     */
    public static String obtieneUFV(String fecha){
        ResultSet rst;
        String ufv;
        String query="SELECT CAMUFV FROM CODOLAR WHERE FECHA='"+fecha+"'";
        try {
            rst=ConexionBD.querySelect(query);
            if(rst.next()){
                ufv=rst.getString("CAMUFV");
                rst.close();
                return ufv;
            }else{
                ufv="0";
                rst.close();
                return ufv;
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en obtieneUFV: "+ex.getMessage());
            
        }
        return null;
        
    }
    
    /**
     * Esta funcion retorna el codigo y los productos de la tabla prproducto
     * @return retorna un objeto de tipo result set con los datos de los productos coigo y descripcion
     */
     public static ResultSet VEobtieneProducto(){
    
        ResultSet rs;
      String query="SELECT PRE_CODIGO,PRE_DESCRIP FROM PRPRODUCTO";
      rs=ConexionBD.querySelect(query);
      return rs;
                  
    }
     
    /**
     * Esta funcion Muestra los Datos que estan en la tabla ALMATERIAL campos MAT_CODIGO y MAT_DESCRIP
     * @return Esta Funcion retorna un Objeto de tipo ResultSet 
     */
    public static ResultSet obtieneAlmacen(){
        
            String mostrar = "SELECT MAT_CODIGO,MAT_DESCRIP FROM ALMATERIAL";
            ResultSet rs;
            rs=ConexionBD.querySelect(mostrar);
        
            return rs;
       
           }  
    
     /** 
     * Selecciona tipo de precio de producto
     * @param tipprecio Tipo de precio
     * @return retorna una consulta
     */
    public static ResultSet precioventa(int tipprecio){
        ResultSet consultaprecio = null;
        String sql="select * from prprecios where pre_codpretip='"+tipprecio+"'";
        consultaprecio=ConexionBD.querySelect(sql);
        //PreparedStatement stt= ConexionBD.querySelect(sql);
        //ResultSet rs = stt.executeQuery();
        return consultaprecio;
    }



}
