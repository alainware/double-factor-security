/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author randy
 */
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES {
    // Algorithm 
    private static final String ALGORITHM = "AES";
    // User defined key byte array
    private byte [] uKey;
    // Constructor
    public AES (String key) {
        uKey = key.getBytes();
    }
    // Encrypt AES
    public String encryptAES (String messageData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte [] encVal = c.doFinal(messageData.getBytes("UTF-8"));
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedValue = encoder.encodeToString(encVal);
        return encryptedValue;
    }
    // Decrypt AES
    public String decryptAES (String encryptedData) throws Exception {
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
    // Generate key for AES
    private Key generateKey() throws Exception {
        Key key = new SecretKeySpec(uKey, ALGORITHM);
        return key;
    }
}
