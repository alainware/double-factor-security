import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class Rijndael {
    private static final String ALGORITHM = "AES";
    private byte [] keyValue;
    public Rijndael (String key) {
        keyValue = key.getBytes();
    }
    public String encrypt (String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte [] encVal = c.doFinal(Data.getBytes("UTF-8"));
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedValue = encoder.encodeToString(encVal);
        return encryptedValue;
    }
    public String decrypt (String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        Base64.Decoder decoder = Base64.getDecoder();
        byte [] decodedValue = decoder.decode(encryptedData);
        byte [] decoderMessage = c.doFinal(decodedValue);
        String decryptedStrMessage = new String(decoderMessage);
        System.out.println(decryptedStrMessage);
        return decryptedStrMessage;
    }
    private Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String key = "";
        String msg = "";
        try {
            System.out.println("Please enter a 16 byte key");
            key = sc.nextLine();
            Rijndael rj = new Rijndael(key);
            msg = sc.nextLine();
            String encdata = rj.encrypt(msg);
            System.out.println("The ecncrypted data is: " + encdata);
            String decdata = rj.decrypt(encdata);
            System.out.println("The decrypted data is: " + decdata);
        } catch (Exception e) {
            System.out.println("The key length must be of 16 characters!");
        }
        sc.close();
    }
}