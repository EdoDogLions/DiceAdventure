package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypewriterGUI extends JFrame {
	
    private static final long serialVersionUID = 1L;
	private JTextArea textArea;
    private Timer timer;
    private String textToType;
    private int currentIndex;

    public TypewriterGUI(String textToType) {
        this.textToType = textToType;
        this.currentIndex = 0;
        
        setTitle("Typewriter GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        /*
         * Parte da analizzare per fare una classe che mi typewritizza l'area di narrazione
         */
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < textToType.length()) {
                    textArea.append(String.valueOf(textToType.charAt(currentIndex)));
                    currentIndex++;
                } else {
                    timer.stop();
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    public void startTypewriter() {
        timer.start();
    }
}
