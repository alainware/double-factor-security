import java.security.Key;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
class AESEncrypt implements Runnable {
    Thread other;
    String messageData;
    String encryptedValue = "";
    // Algorithm 
    private static final String ALGORITHM = "AES";
    public static int counter = 0;
    // User defined key byte array
    private byte [] uKey;
    // Constructor
    public AESEncrypt (String key, String msgData) {
        uKey = key.getBytes();
        messageData = msgData;
    }
    void setOther(Thread other) {
        this.other = other;
    }

    String getEncryptedValue(){
        return encryptedValue;
    }
    // Encrypt AES
    public void encryptAES (String messageData) throws Exception {
        Key key = generateKey();
        byte [] encData = ManAES.encrypt(messageData.getBytes("UTF-8"), key.getEncoded());
        Base64.Encoder encoder = Base64.getEncoder();
        encryptedValue = encoder.encodeToString(encData);
    }
    // Generate key for AES
    private Key generateKey() throws Exception {
        Key key = new SecretKeySpec(uKey, ALGORITHM);
        return key;
    }
    @Override
    public void run() {
        try {
            synchronized(this) {
                encryptAES(messageData);            
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}