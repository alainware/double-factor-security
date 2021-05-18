import java.util.Arrays;
import java.util.Scanner;
public class MultiAES extends AES{

    public MultiAES(String key) {
        super(key);
    }
    private static Scanner sc = new Scanner(System.in);
    private static String uKey = "";
    private static String msg = "";
    public static void main(String[] args) {
        int option = 0;
        try {
            do {
                menu();
                option = sc.nextInt();
                switch (option) {
                    case 1:
                    System.out.println("Please enter a 16 character key: ");
                    uKey = sc.nextLine();
                    while (checkKey(uKey) != true) {
                        uKey = sc.nextLine();
                    }
                    System.out.println("Please write a message to hide: ");
                    msg = sc.nextLine();
                    while(msg.length() == 0){
                        System.out.println("Please write a valid message: ");
                        msg = sc.nextLine();
                    }
                    System.out.println("\n\nTop secret message: " + encrypt(msg, uKey));
                        break;
                    case 2:
                    System.out.println("Please enter a 16 character key: ");
                    uKey = sc.nextLine();
                    while (checkKey(uKey) != true) {
                        uKey = sc.nextLine();
                    }
                    System.out.println("Please write the secret message: ");
                    msg = sc.nextLine();
                    while(msg.length() == 0){
                        System.out.println("Please write a valid message: ");
                        msg = sc.nextLine();
                    }
                    System.out.println("\n\nTop secret message: " + decrypt(msg, uKey));
                        break;                
                    default:
                        break;
                }
            } while (option != 3);

        } catch (Exception e) {
            System.out.println("System error!");
        }
        sc.close();

    }

    public static String[] addToArray(String[] arr, String string) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = string; // Assign 40 to the last element
        return arr;
    }
    
    public static boolean checkKey(String uKey) {
        if (uKey.length() == 16) {
            System.out.println("Key - OK!");
            return true;
        } else {
            System.out.println("Key ERROR!\nThe key length must be of 16 characters!");
            return false;
        }
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
    public static void menu() {
        System.out.println("==============================");
        System.out.println("Welcome to GunmetalCypher v0.1");
        System.out.println("==============================");
        System.out.println("\n");
        System.out.println("1. Encrypt message");
        System.out.println("2. Decrypt message");
        System.out.println("3. Exit");
        System.out.print("Option: ");
    }
}
