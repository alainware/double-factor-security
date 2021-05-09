import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

/**
 * Main
 */

public class Main extends AES{
    public static void main(String[] args) {
        String msg = "";
        String msgToEncrypt = "";
        String KEY = "";
        Main main = new Main();
        msgToEncrypt = JOptionPane.showInputDialog("Ingresa la cadena a encriptar con AES 128");
        KEY = JOptionPane.showInputDialog("Ingresa la llave a encriptar con AES 128");
        msg = main.encryptAes(msgToEncrypt, KEY);
        JOptionPane.showMessageDialog(null, msg);
        JOptionPane.showMessageDialog(null, main.decryptAes(msg, KEY));
    }
}