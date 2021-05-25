import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Steganography extends MultiAES {
	// embed the message into an image
    public static BufferedImage embedText(BufferedImage image, String text) {
        StringBuilder word = new StringBuilder(text);
        //Add a delimiter for the message
        word.append("}");
        text = word.toString();
	int bitMsk = 0x00000001;	// bit mask to get the digits
	int bit;				// integer that represents the ASCII of a character
	int x = 0;				// starting x pixel
	int y = 0;				// starting y pixel
	int txtLen = text.length();
	for(int i = 0; i < txtLen; i++) {			
            bit = (int) text.charAt(i);		// get the ASCII of a character at position i
            for(int j = 0; j < 8; j++) {
                int flag = bit & bitMsk;	// get 1 digit from the character
		if(flag == 1) {	
                    if(x < image.getWidth()) {
                        image.setRGB(x, y, image.getRGB(x, y) | 0x00000001); 	// store the 1 bit into a pixel's last digit
                        x++;
                    } else {
                        x = 0;
                        y++;
                        image.setRGB(x, y, image.getRGB(x, y) | 0x00000001); 	// store the 1 bit into a pixel's last digit
                    }
		} else {	
                    if(x < image.getWidth()) {
                    image.setRGB(x, y, image.getRGB(x, y) & 0xFFFFFFFE);	// store the 0 bit into a pixel's last digit
                    x++;
                    } else {
                        x = 0;
			y++;
			image.setRGB(x, y, image.getRGB(x, y) & 0xFFFFFFFE);	// store the 0 bit into a pixel's last digit
                    }
		}
		bit = bit >> 1;				// get the next digit
            }			
	}
		
	// save the image which contains the text to a new image file
	try {
            File outFile = new File("textEmbedded.png");	
            ImageIO.write(image, "png", outFile);	
	} catch (IOException e) {
			
	}		
	return image;
    }
        
    // extract and decrypt the message from an image
    public static void extractText(BufferedImage image, String uKey) throws Exception {
	/**
	 * [START] Variables for extractText method
	 */
	// Integer variables
	int bitMsk = 0x00000001;	// bit mask to get the digits
	int x = 0;					// starting x pixel
	int y = 0;					// starting y pixel
	int flag;
	// Char variables
	List<Character> c = new ArrayList<Character>();	// ArrayList to store the message extracted from the image character by character
	// String variables
	StringBuilder word = new StringBuilder(""); // String to append the characters of variable 'c' in order to build the message
	String decode = ""; // String to parse the 'word' StringBuilder object
	String decData = ""; //String to store the decoded message generated by AES decryption
	/**
	 * [END] Variables for extractText method
	 */
                
        //A single digit is extracted for comparison
        int bit = 0;
			
	// 8 digits form a character
	for(int j = 0; j < 8; j++) {				
            if(x < image.getWidth()) {
                flag = image.getRGB(x, y) & bitMsk;	// get the last digit of the pixel
                x++;
            } else {
                x = 0;
                y++;
                flag = image.getRGB(x, y) & bitMsk;	// get the last digit of the pixel
            }
				
            // store the extracted digits into an integer as ASCII
            if(flag == 1) {					
                bit = bit >> 1;	
                bit = bit | 0x80;
            } else {					
                bit = bit >> 1;
            }				
	}
	c.add((char) bit);	// Parse the ASCII to characters
	word.append(c.get(0)); // Append characters to the word StringBuilder
                     
        //The rest of the string is extracted
        int i = 0;
	while(word.toString().charAt(i) != '}') {	
            for(int j = 0; j < 8; j++) {				
		if(x < image.getWidth()) {
                    flag = image.getRGB(x, y) & bitMsk;
                    x++;
		} else {
                    x = 0;
                    y++;
                    flag = image.getRGB(x, y) & bitMsk;
		}
				
		if(flag == 1) {					
                    bit = bit >> 1;	
                    bit = bit | 0x80;
		} 
		else {					
                    bit = bit >> 1;
		}				
	}
	c.add((char) bit);
	word.append(c.get(i));
        i++;
	}
                
	//Delete the duplicate character
	word.deleteCharAt(0);
	//Delete the delimiter
	word.deleteCharAt(word.length()-1);
                
	decode = word.toString(); // Convert StringBuilder object to String type
	//decData = aesDec.decryptAES(decode); // Decrypt message using AES
	decData = MultiAES.decrypt(decode, uKey); // Decrypt message using AES
	JOptionPane.showMessageDialog(null, "Message was decrypted successfully!\nThe message is:\n " + decData);
    }
        
    public static boolean checkKey(String uKey) {
        if (uKey.length() == 16) {
            return true;
        } else {
            return false;
        }
    }
}