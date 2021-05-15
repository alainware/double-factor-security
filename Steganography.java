import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Steganography extends AES {

  public Steganography(String key) {
		super(key);
	}

	// embed secret information/TEXT into a "cover image"
	public static BufferedImage embedText(BufferedImage image, String text) {
		int bitMsk = 0x00000001;	// define the mask bit used to get the digit
		int bit;				// define a integer number to represent the ASCII number of a character
		int x = 0;				// define the starting pixel x
		int y = 0;				// define the starting pixel y
                int txtLen = text.length();
		for(int i = 0; i < txtLen; i++) {			
			bit = (int) text.charAt(i);		// get the ASCII number of a character
			for(int j = 0; j < 8; j++) {
				int flag = bit & bitMsk;	// get 1 digit from the character
				if(flag == 1) {	
					if(x < image.getWidth()) {
						image.setRGB(x, y, image.getRGB(x, y) | 0x00000001); 	// store the bit which is 1 into a pixel's last digit
						x++;
					}
					else {
						x = 0;
						y++;
						image.setRGB(x, y, image.getRGB(x, y) | 0x00000001); 	// store the bit which is 1 into a pixel's last digit
					}
				} 
				else {	
					if(x < image.getWidth()) {
						image.setRGB(x, y, image.getRGB(x, y) & 0xFFFFFFFE);	// store the bit which is 0 into a pixel's last digit
						x++;
					}
					else {
						x = 0;
						y++;
						image.setRGB(x, y, image.getRGB(x, y) & 0xFFFFFFFE);	// store the bit which is 0 into a pixel's last digit
					}
				}
				bit = bit >> 1;				// get the next digit from the character
			}			
		}
		
		// save the image which contains the secret information to another image file
		try {
			File outFile = new File("textEmbedded.png");	
			ImageIO.write(image, "png", outFile);	
		} catch (IOException e) {
			
		}		
		return image;
	}
	
	// extract secret information/Text from a "cover image"
	public static void extractText(BufferedImage image, int length) {
		System.out.print("Extracting: ");
		int bitMsk = 0x00000001;	// define the mask bit used to get the digit
		int x = 0;					// define the starting pixel x
		int y = 0;					// define the starting pixel y
		int flag;
		char[] c = new char[length] ;	// define a character array to store the secret information
		for(int i = 0; i < length; i++) {	
			int bit = 0;
			
			// 8 digits form a character
			for(int j = 0; j < 8; j++) {				
				if(x < image.getWidth()) {
					flag = image.getRGB(x, y) & bitMsk;	// get the last digit of the pixel
					x++;
				}
				else {
					x = 0;
					y++;
					flag = image.getRGB(x, y) & bitMsk;	// get the last digit of the pixel
				}
				
				// store the extracted digits into an integer as a ASCII number
				if(flag == 1) {					
					bit = bit >> 1;	
					bit = bit | 0x80;
				} 
				else {					
					bit = bit >> 1;
				}				
			}
			c[i] = (char) bit;	// represent the ASCII number by characters
			System.out.print(c[i]);
		}
                System.out.println("");
	}
	
	public static void main(String[] args) {
		AtomicReference<String> s = new AtomicReference<>();
		Scanner sc = new Scanner(System.in);
		String uKey = "";
		String msg = "";
		String encData = "";
		//String decData = "";
		int op;	
                
		BufferedImage originalImageText = null;
		BufferedImage coverImageText = null;
                
                try {
                    do {
                        menu();
                        op = sc.nextInt();
                        sc.nextLine();
                        switch (op) {
                            case 1:
                                try {
                                    URL path = Steganography.class.getResource("cover.jpg");
                                    originalImageText = ImageIO.read(new File(path.getFile()));
                                    coverImageText = ImageIO.read(new File(path.getFile()));
									System.out.print("Embedding: ");
									// [START] AES Part
									System.out.println("Please enter a 16 character key: ");
									uKey = sc.nextLine();
									while (checkKey(uKey) != true) {
										uKey = sc.nextLine();
									}
									AES aesEnc = new AES(uKey);
									System.out.println("Please write a message to hide: ");
									msg = sc.nextLine();
									encData = aesEnc.encryptAES(msg);
									System.out.println("Message was encrypted succesfully!");
									System.out.println("The message is: " + encData);
									// [END] AES Part																
                                    s.set(encData);
                                    coverImageText = embedText(coverImageText, s.get());  // embed the secret information
                                    JFrame frame = new JFrame("Text Steganography");
                                    JPanel panel = new JPanel();
                                    JLabel label1 = new JLabel(new ImageIcon(originalImageText));
                                    JLabel label2 = new JLabel(new ImageIcon(coverImageText));
                                    panel.add(label1);
                                    panel.add(label2);
                                    frame.add(panel);
                                    frame.pack();
                                    frame.setVisible(true);
                                    System.out.println("Message embedded!");
                                } catch(IOException e) {		
                                    System.out.println("Image not found");
                                }
                                break;
                            case 2:
                                try {
                        
                                    URL path = Steganography.class.getResource("cover.jpg");
                                    originalImageText = ImageIO.read(new File(path.getFile()));
                                    coverImageText = ImageIO.read(new File(path.getFile()));

					// extract the secret information
                                    JFrame frame = new JFrame("Text Steganography");
                                    JPanel panel = new JPanel();
                                    JLabel label1 = new JLabel(new ImageIcon(originalImageText));
                                    extractText(ImageIO.read(new File("textEmbedded.png")), s.get().length());
                                    JLabel label2 = new JLabel(new ImageIcon(coverImageText));
                                    panel.add(label1);
                                    panel.add(label2);
                                    frame.add(panel);
                                    frame.pack();
                                    frame.setVisible(true);		
                                } catch(IOException e) {		
                                    System.out.println("Image not found");
                                }
                                break;                
                            default:
                                System.exit(0);
                        }
                    } while (op != 3);

                } catch (Exception e) {
                    System.out.println("System error!");
                }
		sc.close();	
	}
        
        public static void menu() {
            System.out.println("==============================");
            System.out.println("Welcome to GunmetalCypher v0.1");
            System.out.println("Stegosaurus Special Edition!");
            System.out.println("==============================");
            System.out.println("\n");
            System.out.println("1. Embed message");
            System.out.println("2. Extract message");
            System.out.println("3. Exit");
            System.out.print("Option: ");
        }
		public static boolean checkKey(String uKey) {
			if (uKey.length() == 16) {
				System.out.println("Key - OK!");
				return true;
			} else {
				return false;
			}
		}
}
