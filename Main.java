/**
 * Main
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;

public class Main extends Steganography {
    public static void main(String[] args) {
        JFileChooser fc = new JFileChooser();
        JFrame frame = new JFrame("Steganography & AES");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPnl = new JPanel();
        JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JTextArea tarea = new JTextArea(20, 50);
        
        Font font = new Font("Segoe Script", Font.BOLD, 20);
        
        tarea.setFont(font);

        JButton embedButton = new JButton("EMBED TEXT");
        JButton extractButton = new JButton("EXTRACT TEXT");
    
        btnPnl.add(embedButton);
        btnPnl.add(extractButton);
    
        btnPnl.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        mainPanel.add(topPnl, BorderLayout.NORTH);
        mainPanel.add(btnPnl, BorderLayout.SOUTH);
    
        topPnl.add(tarea);
        
        tarea.setText("============================\n"
                + "Welcome to GunmetalCypher v0.1\n"
                + "Stegosaurus Special Edition!\n"
                + "============================\n" + 
"                         .       .\n" +
"                        / `.   .' \\\n" +
"                .---.  <    > <    >  .---.\n" +
"                |    \\  \\ - ~ ~ - /  /    |\n" +
"                 ~-..-~             ~-..-~\n" +
"             \\~~~\\.'                    `./~~~/\n" +
"              \\__/                        \\__/\n" +
"               /                  .-    .  \\\n" +
"        _._ _.-    .-~ ~-.       /       }   \\/~~~/\n" +
"    _.-'q  }~     /       }     {        ;    \\__/\n" +
"   {'__,  /      (       /      {       /      `. ,~~|   .     .\n" +
"    `''''='~~-.__(      /_      |      /- _      `..-'   \\\\   //\n" +
"                / \\   =/  ~~--~~{    ./|    ~-.     `-..__\\\\_//_.-'\n" +
"               {   \\  +\\         \\  =\\ (        ~ - . _ _ _..---~\n" +
"               |  | {   }         \\   \\_\\\n" +
"              '---.o___,'       .o___,'");
        
        embedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                BufferedImage originalImageText = null; //Original unaltered image
                BufferedImage coverImageText = null; //Image to embed the secret message
                
                int returnVal = fc.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        originalImageText = ImageIO.read(file);
                        coverImageText = ImageIO.read(file);
                        
                        // [START] AES Part
                        String uKey = ""; // String to store the 16 character key given by the user
                        String msg = ""; // String to store the message to hide
                        String encData = ""; // String to store the hidden message (encrypted with AES)
                        
                        uKey = JOptionPane.showInputDialog("Please enter a 16 character key: "); // Input key
                        // Check if the key size is 16 character length
                        while (checkKey(uKey) != true) {
                            uKey = JOptionPane.showInputDialog("Please enter a 16 character key: ");
                        }
                        
                        // Create AES object passing the user defined key as a parameter
                        //AES aesEnc = new AES(uKey);
                        msg = JOptionPane.showInputDialog("Please write a message to hide: ");; // Input message
                        encData = MultiAES.encrypt(msg, uKey); // Encode data
                        JOptionPane.showMessageDialog(null, "Message was encrypted successfully! The message is: " + encData);
                        // [END] AES Part																
                        coverImageText = embedText(coverImageText, encData);  // embed the encrypted message to the image
                        JFrame frame = new JFrame("Text Steganography");
                        JPanel panel = new JPanel();
                        JLabel label1 = new JLabel(new ImageIcon(originalImageText));
                        JLabel label2 = new JLabel(new ImageIcon(coverImageText));
                        panel.add(label1);
                        panel.add(label2);
                        frame.add(panel);
                        frame.pack();
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Operation was CANCELLED");
                }
            }
        });
    
        extractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                BufferedImage originalImageText = null; //Original unaltered image
                BufferedImage coverImageText = null; //Image to embed the secret message
                
                String uKey = "";
                
                int returnVal = fc.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        try {
                            originalImageText = ImageIO.read(file);
                            coverImageText = ImageIO.read(file);

                            // extract and decrypt the message
                            uKey = JOptionPane.showInputDialog("Please enter the 16 character key: ");
                            while (checkKey(uKey) != true) {
                                uKey = JOptionPane.showInputDialog("Please enter the 16 character key: ");
                            }
                            extractText(ImageIO.read(file), uKey);		
                        } catch(IOException e) {		
                            JOptionPane.showMessageDialog(null, "Image not found");
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Operation was CANCELLED");
                }
            }
        });
        
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
