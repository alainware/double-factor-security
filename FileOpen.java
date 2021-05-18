/**
 *
 * @author randy
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FileOpen {
    public static void main(String[] args) throws Exception {
        AtomicReference<String> filePath = new AtomicReference<>();
        
        JFileChooser fc = new JFileChooser();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPnl = new JPanel();
        JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JTextArea tarea = new JTextArea(40, 50);

        JButton readButton = new JButton("OPEN FILE");
        JButton saveButton = new JButton("SAVE CHANGES");
    
        btnPnl.add(readButton);
        btnPnl.add(saveButton);
    
        btnPnl.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        mainPanel.add(topPnl, BorderLayout.NORTH);
        mainPanel.add(btnPnl, BorderLayout.SOUTH);
    
        topPnl.add(tarea);
    
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                int returnVal = fc.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    filePath.set(file.getPath());
                    try {
                        BufferedReader input = new BufferedReader(new InputStreamReader(
                                new FileInputStream(file)));
                        tarea.read(input, "READING FILE");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Operation is CANCELLED");
                }
            }
        });
    
        saveButton.addActionListener(ev -> {
            String file = filePath.get();
            
            try {
                FileWriter fw = new FileWriter(file);
                tarea.write(fw);
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(FileOpen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
