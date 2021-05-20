import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESDecrypt implements Runnable{
    Thread other;
    String encryptedData;
    String decryptedStrMessage = "";

    //Algorithm
    private static final String ALGORITHM = "AES";
    public static int counter = 0;

    //User defined key byte array
    private byte [] uKey;

    //Constructor
    public AESDecrypt(String key, String encData){
        uKey = key.getBytes();
        encryptedData = encData;
    }

    void setOther(Thread other){
        this.other = other;
    }

    String getDecryptedStrMessage(){
        return decryptedStrMessage;
    }

    // Decrypt AES
    public void decryptAES (String encryptedData) throws Exception{
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        Base64.Decoder decoder = Base64.getDecoder();
        byte [] decodedValue = decoder.decode(encryptedData);
        byte [] decoderMessage = c.doFinal(decodedValue);
        decryptedStrMessage = new String(decoderMessage);
    }

    // Generate key for AES
    private Key generateKey() throws Exception {
        Key key = new SecretKeySpec(uKey, ALGORITHM);
        return key;
    }

    @Override
    public void run(){
        try{
            synchronized(this){
                Thread.sleep(2000);
                decryptAES(encryptedData);
                System.out.println(Thread.currentThread().getName() + " is completed--" + counter++);
            }
        } catch(Exception e){
            System.out.println("Ha fallao la wea, weon culiao!");
            e.printStackTrace();
        }
    }
}