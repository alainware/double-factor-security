import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.*;

public class Steganography {

  // embed secret information/TEXT into a "cover image"
	public static BufferedImage embedText(BufferedImage image, String text) {
		int bitMask = 0x00000001;	// define the mask bit used to get the digit
		int bit;				// define a integer number to represent the ASCII number of a character
		int x = 0;				// define the starting pixel x
		int y = 0;				// define the starting pixel y
		for(int i = 0; i < text.length(); i++) {			
			bit = (int) text.charAt(i);		// get the ASCII number of a character
			for(int j = 0; j < 8; j++) {
				int flag = bit & bitMask;	// get 1 digit from the character
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
			File outputfile = new File("textEmbedded.png");	
			ImageIO.write(image, "png", outputfile);	
		} catch (IOException e) {
			
		}		
		return image;
	}
	
	// extract secret information/Text from a "cover image"
	public static void extractText(BufferedImage image, int length) {
		System.out.print("Extracting: ");
		int bitMask = 0x00000001;	// define the mask bit used to get the digit
		int x = 0;					// define the starting pixel x
		int y = 0;					// define the starting pixel y
		int flag;
		char[] c = new char[length] ;	// define a character array to store the secret information
		for(int i = 0; i < length; i++) {	
			int bit = 0;
			
			// 8 digits form a character
			for(int j = 0; j < 8; j++) {				
				if(x < image.getWidth()) {
					flag = image.getRGB(x, y) & bitMask;	// get the last digit of the pixel
					x++;
				}
				else {
					x = 0;
					y++;
					flag = image.getRGB(x, y) & bitMask;	// get the last digit of the pixel
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
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		BufferedImage originalImageText = null;
		BufferedImage coverImageText = null;
		//C:\Users\randy\Documents\NetBeansProjects\Interfaces\src\main\main.java
		// show a user interface to display the original image and the cover image with the secret information included
		try {
                        URL path = Steganography.class.getResource("cover.png");
			originalImageText = ImageIO.read(new File(path.getFile()));
			coverImageText = ImageIO.read(new File(path.getFile()));
			
			String s;
			Scanner scan = new Scanner(System.in);
			System.out.print("Embedding: ");
			s = scan.nextLine();
			coverImageText = embedText(coverImageText, s);								// embed the secret information
			extractText(ImageIO.read(new File("textEmbedded.png")), s.length());		// extract the secret information
			JFrame frame = new JFrame("Text Steganography");
			JPanel panel = new JPanel();
			JLabel label1 = new JLabel(new ImageIcon(originalImageText));
			JLabel label2 = new JLabel(new ImageIcon(coverImageText));
			panel.add(label1);
			panel.add(label2);
			frame.add(panel);
			frame.pack();
			frame.setVisible(true);		
		} catch(IOException e) {		
			System.out.println("Image not found");
		}		
	}
}
