import java.security.Key;
import java.util.Base64;
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
        Base64.Decoder decoder = Base64.getDecoder();
        byte [] decodedValue = decoder.decode(encryptedData);
        byte [] decData = ManAES.decrypt(decodedValue, key.getEncoded());
        decryptedStrMessage  = new String(decData);
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
                decryptAES(encryptedData);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}