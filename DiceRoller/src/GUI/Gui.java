package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/*
 * Beta
 */
public class Gui {
		
	private static final Integer DEFAUT_WEIGHT = 800;
	private static final Integer DEFAUT_HEIGHT = 600;
	
	private JFrame window;
	private Container container;
	private JPanel titlePanel, startButtonPanel, mainTextPanel, characterSprite;
	private JPanel playerPanel, mobPanel;
	private JLabel titleLabel;
	private Font titleFont = new Font("Yrsa", Font.PLAIN, 80);	//MODIFICARE
	private Font defaultFont = new Font("Yrsa", Font.PLAIN, 30);
	private JButton startButton;
	private JTextArea mainTextArea;
	
	private TitleScreenHandler titleScreenHandler = new TitleScreenHandler();

	public Gui() {
		
		/*
		 * Windows
		 */
		this.window = new JFrame();
		window.setSize(DEFAUT_WEIGHT, DEFAUT_HEIGHT);		//WxH
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.BLACK);
		window.setLayout(null);	//Layout default
		window.setVisible(true);	//JFrame visibile
		
		/*
		 * Container
		 */
		this.container = window.getContentPane();
		
		/*
		 * Title
		 */
		this.titlePanel = new JPanel();
		titlePanel.setBounds(100, 100, 600, 150);	//pixelX, pixelY, HxW
		titlePanel.setBackground(Color.BLACK);
		
		titleLabel = new JLabel("DICE ADVENTURE");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(titleFont);
		
		/*
		 * Start Button
		 */
		this.startButtonPanel = new JPanel();
		startButtonPanel.setBounds(300, 400, 200, 100);
		startButtonPanel.setBackground(Color.BLACK);
		
		this.startButton = new JButton("START");
		startButton.setBackground(Color.BLACK);
		startButton.setForeground(Color.WHITE);
		startButton.setFont(defaultFont);
		startButton.addActionListener(titleScreenHandler);
		
		
		/*
		 * Add
		 */
		titlePanel.add(titleLabel);	//add Title to Panel
		container.add(titlePanel);	//add Panel to Container
		container.add(startButtonPanel);
		startButtonPanel.add(startButton);	//add Button to ButtonPanel
						
	}
	
	public void createGameScreen() {
		
		/*
		 * Facciamo sparire titlePanel e startButton
		 */
		
		titlePanel.setVisible(false);
		startButtonPanel.setVisible(false);
		
		/*
		 * TextArea
		 */
		
		this.mainTextPanel = new JPanel();
		mainTextPanel.setBounds(100, 100, 600, 250);
		mainTextPanel.setBackground(Color.BLACK);
		
		this.mainTextArea = new JTextArea("Area di narrazione, incredibile come vada a capo con un'ottima qualità");
		mainTextArea.setBounds(100, 100, 600, 250);
		mainTextArea.setBackground(Color.BLACK);
		mainTextArea.setForeground(Color.WHITE);
		mainTextArea.setFont(defaultFont);
		mainTextArea.setLineWrap(true);	//It goes new line automatically
		
		/*
		 * Character Images
		 */
		
		this.characterSprite = new JPanel();
		characterSprite.setBounds(100, 350, 600, 350);
		characterSprite.setBackground(Color.RED);
		characterSprite.setLayout(new GridLayout (1, 2));
		
		/*
		 * Player Panel
		 */
		
		this.playerPanel = new JPanel();
		playerPanel.setBounds(100, 15, 600, 50);
		playerPanel.setBackground(Color.RED);
		playerPanel.setLayout(new GridLayout(1, 4));
		
		
		/*
		 * Add
		 */
		container.add(mainTextPanel);
		container.add(playerPanel);
		container.add(characterSprite);
		
		mainTextPanel.add(mainTextArea);
		
	}
	
	public class TitleScreenHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			
			createGameScreen();
		}

	}
}
