/**
 *
 * @author irvingpercam
 */
import java.util.Arrays;
public class MultiAES extends AES{

    public MultiAES(String key) {
        super(key);
    }

    public static String[] addToArray(String[] arr, String string) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = string; // Assign 40 to the last element
        return arr;
    }

    public static String encrypt(String msg, String uKey) {
        /*
        [START] Encrypting loop
        */
        // Create AES Object
        AES aesEnc = new AES(uKey);

        // Step 1. Convert the message to a String array

        String[] messageArray = msg.split(" ");

        // Step 2. Create a new String Array for storing the encrypted words

        String[] encryptedMessageArray = {};

        // Step 3. Encrypt each word inside messageArray

        for (String s : messageArray) {
            try {
                encryptedMessageArray = addToArray(encryptedMessageArray, aesEnc.encryptAES(s));
            } catch (Exception e) {
                System.out.println("Encryption failed for MultiAES class!");
                e.printStackTrace();
            }
        }
        System.out.println("Message was encrypted succesfully!");

        // Step 4. Append the ecnrypted strings to a StringBuilder

        StringBuilder finalMessage = new StringBuilder("");
        for (String string : encryptedMessageArray) {
            finalMessage.append(string);
        }
        return finalMessage.toString();
        /*
        [END] Encrypting loop
        */
    }
    public static String decrypt(String msg, String uKey) {
        /*
        [START] Decrypting loop
        */
        // Create AES Object
        AES aesDec = new AES(uKey);
        
        // Step 1. Convert the message to a String Array

        String[] encMessageArray = msg.split("==");

        // Step 2. Create a new String Array for storing the decrypted words

        String[] decryptedMessageArray = {};

        // Step 3. Encrypt each word inside messageArray

        for (String string : encMessageArray) {
            try {
                decryptedMessageArray = addToArray(decryptedMessageArray, aesDec.decryptAES(string));
            } catch (Exception e) {
                System.out.println("Decryption failed for MultiAES class!");
                e.printStackTrace();
            }
        }
        System.out.println("Message was decrypted succesfully!");

        // Step 4. Append the decrypted strings to a StringBuilder

        StringBuilder finalDecryptedMessage = new StringBuilder("");

        for (int i = 0; i < decryptedMessageArray.length; i++) {
            if (i == decryptedMessageArray.length - 1) {
                finalDecryptedMessage.append(decryptedMessageArray[i] + ".");
            } else {
                finalDecryptedMessage.append(decryptedMessageArray[i] + " ");
            }
        }
        return finalDecryptedMessage.toString();
        /*
        [END] Decrypting loop
        */
    }
}
