
package clases;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 * Libreria que permite acceder a funciones comunes donde se simplifica el codigo
 * Clase principal de nombre A para simplificar el llamado de sus metodos
 * @author Pinell
 */
public class A {

    /**
     * Funcion que permite mostrar mensajes en pantalla
     * La funcion recibe un parametro tipo String
     * @param mensaje de tipo String
     */
    
    public static void mensaje(String mensaje){
         JOptionPane.showMessageDialog(null, mensaje);
     }
    
    /**
     * Funcion que retorna la respuesta de una ventana de confirmacion
     * @param pregunta de tipo String
     * @return 0 si la opcion es SI y 1 si la opcion es NO
     */
    public static int confirma(String pregunta){
        return JOptionPane.showConfirmDialog(null, pregunta, null, JOptionPane.YES_NO_OPTION);
    }
    
    /**
     * Funcion que convierte el formato de fecha DD/MM/AAAA al formato MM/DD/AAAA
     * @param fechaDMA en el formato con barras /
     * @return Retorna la fecha en formato MM/DD/AAAA
     */
 
    
    public static String convierte_DMA_a_MDA_barra(String fechaDMA){
        String deliveryDate=fechaDMA;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
         try {
             date = sdf.parse(deliveryDate);
               sdf.applyPattern("MM/dd/yyyy");
                return sdf.format(date);
         } catch (ParseException ex) {
             A.mensaje(ex.getMessage());
         }
        return null;
    }
    
    
    /**
     * Funcion que convierte el formato de fecha MM/DD/AAAA al formato DD/MM/AAAA
     * @param fechaMDA en el formato con barras /
     * @return Retorna la fecha en formato DD/MM/AAAA
     */
    public static String convierte_MDA_a_DMA_barra(String fechaMDA){
        String deliveryDate=fechaMDA;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date date;
         try {
             date = sdf.parse(deliveryDate);
               sdf.applyPattern("dd/MM/yyyy");
                return sdf.format(date);
         } catch (ParseException ex) {
             A.mensaje(ex.getMessage());
         }
        return null;
    }
    
    
     /**
     * Funcion que convierte el formato de fecha DD-MM-AAAA al formato MM-DD-AAAA
     * @param fechaDMA en el formato con guiones -
     * @return Retorna la fecha en formato MM-DD-AAAA
     */
    
    public static String convierte_DMA_a_MDA_guion(String fechaDMA){
        String deliveryDate=fechaDMA;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
         try {
             date = sdf.parse(deliveryDate);
               sdf.applyPattern("MM-dd-yyyy");
                return sdf.format(date);
         } catch (ParseException ex) {
             A.mensaje(ex.getMessage());
         }
        return null;
    }
    
     /**
     * Funcion que convierte el formato de fecha MM-DD-AAAA al formato DD-MM-AAAA
     * @param fechaMDA en el formato con guiones -
     * @return Retorna la fecha en formato DD-MM-AAAA
     */
    public static String convierte_MDA_a_DMA_guion(String fechaMDA){
        String deliveryDate=fechaMDA;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date date;
         try {
             date = sdf.parse(deliveryDate);
               sdf.applyPattern("dd-MM-yyyy");
                return sdf.format(date);
         } catch (ParseException ex) {
             A.mensaje(ex.getMessage());
         }
        return null;
    }
    
    /**
     * Funcion que convierte el formato de fecha AAAA-MM-DD al formato DD-MM-AAAA
     * @param fechaAMD en el formato con guiones -
     * @return Retorna la fecha en formato DD-MM-AAAA
     */
    public static String convierte_AMD_a_DMA_guion(String fechaAMD){
        String deliveryDate=fechaAMD;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
         try {
             date = sdf.parse(deliveryDate);
               sdf.applyPattern("dd-MM-yyyy");
                return sdf.format(date);
         } catch (ParseException ex) {
             A.mensaje(ex.getMessage());
         }
        return null;
    }
    
    /**
     * Funcion que convierte el formato de fecha AAAA-MM-DD al formato DD/MM/AAAA
     * @param fechaAMD en el formato con guiones -
     * @return Retorna la fecha en formato MM/DD/AAAA
     */
    public static String convierte_AMD_a_MDA_guion(String fechaAMD){
        String deliveryDate=fechaAMD;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
         try {
             date = sdf.parse(deliveryDate);
               sdf.applyPattern("MM/dd/yyyy");
                return sdf.format(date);
         } catch (ParseException ex) {
             A.mensaje(ex.getMessage());
         }
        return null;
    }
    
    
     /**
     * Funcion que convierte el formato de fecha AAAA-MM-DD al formato DD/MM/AAAA
     * @param fechaAMD en el formato con guiones -
     * @return Retorna la fecha en formato DD/MM/AAAA
     */
    public static String convierte_AMD_a_DMA_barra(String fechaAMD){
        String deliveryDate=fechaAMD;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
         try {
             date = sdf.parse(deliveryDate);
               sdf.applyPattern("dd/MM/yyyy");
                return sdf.format(date);
         } catch (ParseException ex) {
             A.mensaje(ex.getMessage());
         }
        return null;
    }
    
    
    
    /**
     * Funcion que obtiene el DIA de una fecha ingresada en formato MM/DD/AAAA o MM-DD-AAAA
     * @param fechaMDA tipo String con formato MM/DD/AAAA o MM-DD-AAAA
     * @return Retorna el DIA de la fecha ingresada
     */
    public static String obtieneDia_fechaMDA(String fechaMDA){
        String dia="";
        dia=fechaMDA.substring(3, 5);
        return dia;
    }
    
     /**
     * Funcion que obtiene el MES de una fecha ingresada en formato MM/DD/AAAA o MM-DD-AAAA
     * @param fechaMDA tipo String con formato MM/DD/AAAA o MM-DD-AAAA
     * @return Retorna el MES de la fecha ingresada
     */
    public static String obtieneMes_fechaMDA(String fechaMDA){
        String mes="";
        mes=fechaMDA.substring(0, 2);
        return mes;
    }
    
    /**
     * Funcion que obtiene el ANIO de una fecha ingresada en formato MM/DD/AAAA o MM-DD-AAAA
     * @param fechaMDA tipo String con formato MM/DD/AAAA o MM-DD-AAAA
     * @return Retorna el ANIO de la fecha ingresada
     */
    public static String obtieneAnio_fechaMDA(String fechaMDA){
        String anio="";
        anio=fechaMDA.substring(6, 10);
        return anio;
    }
    
    /**
     * Funcion para convertir un numero SIN separador de miles "," a 2 decimales(no forzados) con punto "."
     * @param numero parametro tipo String con formato ######.## o ######
     * @return retorna un numero con 2 decimales separado por punto "."
     */
    public static String convDecimalPunto(String numero){
        DecimalFormatSymbols simbolo_decimal = new DecimalFormatSymbols();
        simbolo_decimal.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#######.##",simbolo_decimal);
        try{
            return df.format(Double.parseDouble(numero));
        }catch(Exception ex){
            A.mensaje(ex.getMessage());
        }
        return null;
        
    }
    
    
    /**
     * Funcion para convertir un numero SIN separador de miles "," a 2 decimales(forzados) con punto "."
     * @param numero parametro tipo String con formato ######.## o ######
     * @return retorna un numero con 2 decimales separado por punto "."
     */
    public static String convDecimalPuntoForzado(String numero){
        DecimalFormatSymbols simbolo_decimal = new DecimalFormatSymbols();
        simbolo_decimal.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#######.00",simbolo_decimal);
        try{
            return df.format(Double.parseDouble(numero));
        }catch(Exception ex){
            A.mensaje(ex.getMessage());
        }
        return null;
        
    }
    
    /**
     * Funcion que permite validar un campo para que solo acepte numeros y el caracter punto '.'
     * @param c Parametro tipo char que se obtiene del objeto evt.getKeyChar() dentro de algun evento
     * programado por el desarrollador Ejemplos: KeyTyped,KeyPressed,MouseClicked, etc.
     * @return Retorna TRUE si se ha ingresado un valor numerico o punto '.', retorna FALSE si 
     * se introduce cualquier otro caracter
     */
    public static boolean esNumeroConPunto(char c){
        if(!Character.isDigit(c) && c!='.' && c!='\b'){
           return false;
         }else{
            return true;
        }
    }
    
    /**
     * Funcion que permite validar un campo para que solo acepte numeros enteros
     * @param c Parametro tipo char que se obtiene del objeto evt.getKeyChar() dentro de algun evento
     * programado por el desarrollador Ejemplos: KeyTyped,KeyPressed,MouseClicked, etc.
     * @return Retorna TRUE si se ha ingresado un valor numerico,retorna FALSE si 
     * se introduce cualquier otro caracter
     */
    public static boolean esNumeroEntero(char c){
        if(!Character.isDigit(c)){
           return false;
         }else{
            return true;
        }
    }
    
    /**
     * Funcion que retorna el NUMERO de dia de la semana de la fecha ingresada siendo 1=Domingo,2=Lunes...etc.
     * @param fecha Parametro tipo String que acepta una fecha en formato DD/MM/AAAA
     * @return Retorna el numero de dia de la semana de la fecha ingresada
     */
    public static Integer obtieneDiaSemana(String fecha){
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = fmt.parse(fecha);
             GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(date);
            return cal.get(Calendar.DAY_OF_WEEK);
        } catch (ParseException ex) {
             A.mensaje("Existio un error en obtieneDiaSemana: "+ ex.getMessage());
        }
        return null;
    }
    
    /**
     * Funcion que suma o resta dias a una fecha ingresada
     * @param fecha Parametro tipo String en formato DD/MM/YYYY como fecha inicial
     * @param dias Cantidad de dias que se quiere sumar o restar a la fecha introducida, para restar poner numero en negativo
     * @return Retorna la fecha con los dias que se hayan sumado o restado.
     */
    public static String fechaSumaResta(String fecha,int dias){
          String dt = fecha;  // Fecha enviada por parametro
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
            c.add(Calendar.DATE, dias);  // numero de dias para incrementar o restar(-)
            dt = sdf.format(c.getTime());  // dt es la fecha incrementada o reducida
            return dt;
        } catch (ParseException ex) {
           A.mensaje("Ocurrio un error en fechaSumaResta: "+ex.getMessage());
        }
        return null;
           
    }
}
