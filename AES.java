import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    //Clave de encriptación/desencriptación
    public SecretKeySpec createKey(String KEY) {
        try {
            // Transformar la llave en un arreglo de bytes
            byte [] strKey = KEY.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.digest(strKey);
            strKey = Arrays.copyOf(strKey, 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(strKey, "AES");
            return secretKeySpec;
        } catch (Exception e) {
            return null;
        }
    }
    public String encryptAes(String messageToEncrypt, String KEY) {
        try {
            SecretKeySpec secretKeySpec = createKey(KEY);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte [] strMessage = messageToEncrypt.getBytes("UTF-8");
            byte [] encrypted = cipher.doFinal(strMessage);
            Base64.Encoder encode = Base64.getEncoder();
            String encryptedStrMessage = encode.encodeToString(encrypted);
            System.out.println(encryptedStrMessage);
            return encryptedStrMessage;
        } catch (Exception e) {
            return "";
        }
    }
    public String decryptAes(String messageToDecrypt, String KEY) {
        try {
            SecretKeySpec secretKeySpec = createKey(KEY);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] encodedDecryptedMessage = decoder.decode(messageToDecrypt);
            byte [] decrypted = cipher.doFinal(encodedDecryptedMessage);
            String decryptedStrMessage = new String(decrypted);
            System.out.println(decryptedStrMessage);
            return decryptedStrMessage;
        } catch (Exception e) {
            return "";
        }
    }
}
