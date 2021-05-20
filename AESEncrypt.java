import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
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
    /*void setMessage(String encryptedValue) {
        this.encryptedValue = encryptedValue;
    }*/
    String getEncryptedValue(){
        return encryptedValue;
    }
    // Encrypt AES
    public void encryptAES (String messageData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte [] encVal = c.doFinal(messageData.getBytes("UTF-8"));
        Base64.Encoder encoder = Base64.getEncoder();
        encryptedValue = encoder.encodeToString(encVal);
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
                Thread.sleep(1000);
                encryptAES(messageData);
                System.out.println(Thread.currentThread().getName() + " is completed--" + counter ++);             
            }
        } catch (Exception e) {
            System.out.println("Ha fallao la wea, weon culiao!");
            e.printStackTrace();
        }
    }
}