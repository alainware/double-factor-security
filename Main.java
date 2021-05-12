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
    public void menu() {
        System.out.println("Welcome to GunmetalCypher v0.1");
        System.out.println();
    }
}