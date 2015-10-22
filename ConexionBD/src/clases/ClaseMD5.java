
package clases;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase para encriptar cadenas con algoritmo MD5
 * @author Pinell
 */
public class ClaseMD5 {
    /**
     * 
     * @param input Parametro tipo string que recibe la cadena que deseamos convertir a MD5
     * @return retorna la cadena encriptada a MD5
     */
    public String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
 
    public void main(String[] args) throws NoSuchAlgorithmException {
        //System.out.println(getMD5("Javarmi.com"));
    }
}
