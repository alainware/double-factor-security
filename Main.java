import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Main
 */

public class Main extends AES{
    public static void main(String[] args) throws IOException {
        String msg = "";
        String msgToEncrypt = "";
        String KEY = "";
        Main main = new Main();
        msgToEncrypt = JOptionPane.showInputDialog("Ingresa la cadena a encriptar con AES 128");
        KEY = JOptionPane.showInputDialog("Ingresa la llave a encriptar con AES 128");
        msg = main.encryptAes(msgToEncrypt, KEY);
        JOptionPane.showMessageDialog(null, msg);
        JOptionPane.showMessageDialog(null, main.decryptAes(msg, KEY));
        openSpam();
    }
    public void menu() {
        System.out.println("Welcome to GunmetalCypher v0.1");
        System.out.println();
    }
    public static void openSpam() throws IOException {
        Desktop desktop = java.awt.Desktop.getDesktop();
		try {
			//specify the protocol along with the URL
			URI oURL = new URI(
					"https://www.caliente.mx");
			desktop.browse(oURL);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    }
}