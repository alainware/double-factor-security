/**
 * Main
 */
import java.util.Scanner;
public class Main extends AES{
    public Main(String key) {
        super(key);
    }
    private static Scanner sc = new Scanner(System.in);
    private static String uKey = "";
    private static String msg = "";
    private static String encData = "";
    private static String decData = "";
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
                    AES aesEnc = new AES(uKey);
                    System.out.println("Please write a message to hide: ");
                    msg = sc.nextLine();
                    while(msg.length() == 0){
                        System.out.println("Please write a valid message: ");
                        msg = sc.nextLine();
                    }
                    encData = aesEnc.encryptAES(msg);
                    System.out.println("Message was encrypted succesfully!");
                    System.out.println("The message is: " + encData); 
                        break;
                    case 2:
                    System.out.println("Please enter a 16 character key: ");
                    uKey = sc.nextLine();
                    while (checkKey(uKey) != true) {
                        uKey = sc.nextLine();
                    }
                    AES aesDec = new AES(uKey);
                    System.out.println("Please write the secret message: ");
                    msg = sc.nextLine();
                    while(msg.length() == 0){
                        System.out.println("Please write a valid message: ");
                        msg = sc.nextLine();
                    }
                    decData = aesDec.decryptAES(msg);
                    System.out.println("Message was decrypted succesfully!"); 
                    System.out.println("...");            
                    System.out.println("The message is: " + decData);       
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
    public static boolean checkKey(String uKey) {
        if (uKey.length() == 16) {
            System.out.println("Key - OK!");
            return true;
        } else {
            System.out.println("Key ERROR!\nThe key length must be of 16 characters!");
            return false;
        }
    }
}