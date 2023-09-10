package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.plaf.basic.BasicProgressBarUI;

import feature.PartyPg;
import playable.Player;
import utilities.Combat;
import utilities.NarrationGenerator;

/**
 * @author edoardodoglioni This is the GUI class who design and create the
 *         entire Graphic User Interface
 */
public class View implements ViewInterface {

	private static final Integer DEFAUT_WEIGHT = 900;
	private static final Integer DEFAUT_HEIGHT = 600;
	private static final Integer NARRATION_SPEED = 35;
	private static final Integer COMBAT_SPEED = 20;
	private static final Integer CREDIT_SPEED = 50;
	private static final String PATH = "background.jpg";

	private JFrame window;

	private Container container;

	private JPanel titlePanel, startButtonPanel, mainTextPanel, adventureBottomPanel, playerPanel, creditTextPanel,
			backButtonPanel, gameModeTextPanel, gameModeButtonPanel, partyPanel, partyListPanel, partyButtonPanel,
			partyWelcomePanel, adventureButtonPanel;

	private JLabel titleLabel, gameModeTextArea, subtitleLabel, partyLabel;

	private Font titleFont = new Font("Press Start 2P", Font.BOLD, 30),
			defaultFont = new Font("Press Start 2P", Font.PLAIN, 16),
			buttonFont = new Font("Press Start 2P", Font.PLAIN, 15),
			subtitleFont = new Font("Press Start 2P", Font.PLAIN, 20),
			creditsFont = new Font("Press Start 2P", Font.PLAIN, 20),
			combatFont = new Font("Press Start 2P", Font.ITALIC, 12),
			statsFont = new Font("Press Start 2P", Font.PLAIN, 12);

	private JButton startButton, creditButton, quitButton, backButton, createAdventureButton, createPartyButton,
			startAdventureButton, generateButton;

	private JTextArea narrationTextArea, creditTextArea, playerStatsTextArea, mobStatsTextArea, combatTextArea;

	private JProgressBar lifeBar;

	private JComboBox<Integer> numPlayersComboBox;

	private List<JTextArea> partyTextAreas;

	private Timer creditTimer, combatTimer, narrationTimer;

	private Integer creditTypeWriteIndex = 0, combatTypeWriteIndex = 0, narrationTypeWriteIndex = 0;

	private Boolean isEndGame = false;
	/*
	 * Handlers
	 */
	private TitleScreenHandler titleScreenHandler = new TitleScreenHandler();
	private CreditsScreenHandler creditsScreenHandler = new CreditsScreenHandler();
	private AdventureHandler adventureHandler = new AdventureHandler();
	private QuitHandler quitHandler = new QuitHandler();
	private BackMainMenu backHandler = new BackMainMenu();
	private CreateAPartyHandler createAParty = new CreateAPartyHandler();
	private GeneratePartyHandler generatePartyHandler = new GeneratePartyHandler();
	private RestartGameHandler restartGameHandler = new RestartGameHandler();

	/*
	 * Object used to update the GUI
	 */
	private Player player;
	private Combat combat;
	private NarrationGenerator rng;
	private String creditString, narrationString, combatString;
	private Integer[] numPlayerOptions = { 1, 2, 3, 4 };
	private Integer partyMember = 0;
	ImageIcon backgroundImageIcon;
	ImagePanel imagePanel;

	public View(Player player) {

		this.player = player;
		build();

	}

	public void build() {

		initializeGUI(player);
		/*
		 * Main Widow, not resizable
		 */
		this.window = new JFrame();
		window.setTitle("DiceAdventure");
		window.setSize(DEFAUT_WEIGHT, DEFAUT_HEIGHT); // WxH
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setResizable(false); // Layout non modificabile
		setUpBackground(window);
		// window.setContentPane(imagePanel);

		/*
		 * Container
		 */
		this.container = window.getContentPane();
		container.setLayout(new BorderLayout(10, 10)); // This creates the BorderLayout, which manages the layout of

		createMainMenu();

		window.setVisible(true); // JFrame visibile
	}

	/*
	 * This method initialize all the Panel of the GUI
	 */
	private void initializeGUI(Player player) {

		this.player = player;
		this.player.restoreFullHp();
		this.combat = new Combat(player);
		this.rng = new NarrationGenerator(player);
		this.isEndGame = false;

		titlePanel = new JPanel();
		startButtonPanel = new JPanel();
		mainTextPanel = new JPanel();
		adventureBottomPanel = new JPanel();
		playerPanel = new JPanel();
		creditTextPanel = new JPanel();
		backButtonPanel = new JPanel();
		gameModeTextPanel = new JPanel();
		gameModeButtonPanel = new JPanel();
		partyPanel = new JPanel();
	}

	private void setUpBackground(JFrame window) {

		try {

			ClassLoader classLoader = this.getClass().getClassLoader();
			URL imageURL = classLoader.getResource(PATH);
			backgroundImageIcon = new ImageIcon(imageURL);
			imagePanel = new ImagePanel(backgroundImageIcon.getImage());
			window.setContentPane(imagePanel);

		} catch (Exception e) {
			System.out.println("Image cannot be found");

		}

	}

	/*
	 * This method set all the Panel of the GUI not Visible
	 */

	private void setInvisible() {

		titlePanel.setVisible(false);
		startButtonPanel.setVisible(false);
		mainTextPanel.setVisible(false);
		adventureBottomPanel.setVisible(false);
		playerPanel.setVisible(false);
		creditTextPanel.setVisible(false);
		backButtonPanel.setVisible(false);
		gameModeTextPanel.setVisible(false);
		gameModeButtonPanel.setVisible(false);
		partyPanel.setVisible(false);
	}

	/*
	 * This method creates the MainMenu
	 */

	public void createMainMenu() {

		setInvisible();

		/*
		 * Title Panel
		 */
		this.titlePanel = new JPanel(new GridLayout(0, 1));
		titlePanel.setOpaque(false);

		titleLabel = new JLabel("Welcome in DiceAdventure");
		titleLabel.setBorder(BorderFactory.createEmptyBorder(150, 80, 20, 20));
		titleLabel.setFont(titleFont);
		subtitleLabel = new JLabel("a Dungeons & Dragons Simulator");
		subtitleLabel.setBorder(BorderFactory.createEmptyBorder(-130, 140, 20, 20));
		subtitleLabel.setFont(subtitleFont);

		/*
		 * Button Panel
		 */

		this.startButtonPanel = new JPanel(new GridLayout(0, 1));
		startButtonPanel.setOpaque(false);

		/*
		 * Start, Credit, Quit Button
		 */

		this.startButton = new JButton("PLAY");
		startButton.setFont(buttonFont);
		startButton.addActionListener(titleScreenHandler);
		startButton.setPreferredSize(new Dimension(150, 60));
		startButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		startButton.setFocusPainted(false);
		startButton.setContentAreaFilled(false);

		this.creditButton = new JButton("CREDITS");
		creditButton.setFont(buttonFont);
		creditButton.addActionListener(creditsScreenHandler);
		creditButton.setPreferredSize(new Dimension(150, 60));
		creditButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		creditButton.setFocusPainted(false);
		creditButton.setContentAreaFilled(false);

		this.quitButton = new JButton("QUIT");
		quitButton.setFont(buttonFont);
		quitButton.addActionListener(quitHandler);
		quitButton.setPreferredSize(new Dimension(150, 60));
		quitButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		quitButton.setFocusPainted(false);
		quitButton.setContentAreaFilled(false);

		/*
		 * Add everything
		 */
		titlePanel.add(titleLabel); // add Title to Panel
		titlePanel.add(subtitleLabel);

		startButtonPanel.add(startButton); // add StartButton to startButtonpanel
		startButtonPanel.add(creditButton); // add CreditButton to startButtonpanel
		startButtonPanel.add(quitButton); // add QuitButton to startButtonpanel

		container.add(titlePanel, BorderLayout.CENTER); // add TitlePanel to Container
		container.add(startButtonPanel, BorderLayout.SOUTH);

	}

	/*
	 * This method creates the CreditScreen
	 */
	public void createCreditsScreen() {

		setInvisible();

		/*
		 * TextArea
		 */

		this.creditTextPanel = new JPanel(new BorderLayout());
		creditTextPanel.setOpaque(false);

		creditString = "DiceAdventure is a Java project made by Edoardo Doglioni\nThis project wants to simulate"
				+ " a Dungeons and Dragons Adventure.\n" + "The simulation is based around the concept of dice.\n"
				+ "Rolling dices the player can simulate a fight against an enemyes\n"
				+ "Once he survived every fight the simulation is completed succesfully\n"
				+ "Otherwise the game will end with a lose\n"
				+ "This project have been developed for the Programmazione e Modellazione ad Oggetti exam\n"
				+ "Last update: Urbino, 13/07/2023";

		this.creditTextArea = new JTextArea();
		creditTextArea.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		creditTextArea.setOpaque(false);

		creditTimer = new Timer(CREDIT_SPEED, new CreditTimeHandler());

		creditTextArea.setEditable(false);
		creditTextArea.setFont(creditsFont);
		creditTextArea.setLineWrap(true); // It goes new line automatically
		creditTextArea.setWrapStyleWord(true);

		/*
		 * BackButton
		 */
		this.backButtonPanel = new JPanel(new GridLayout());
		backButtonPanel.setOpaque(false);

		this.backButton = new JButton("BACK");
		backButton.setFont(buttonFont);
		backButton.addActionListener(backHandler);
		backButton.setPreferredSize(new Dimension(150, 60));
		backButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		backButton.setFocusPainted(false);
		backButton.setContentAreaFilled(false);

		/*
		 * Add
		 */
		backButtonPanel.add(backButton);
		creditTextPanel.add(creditTextArea, BorderLayout.CENTER);

		container.add(creditTextPanel, BorderLayout.CENTER);
		container.add(backButtonPanel, BorderLayout.SOUTH);

	}

	/*
	 * This method creates the GameModeScreen
	 */
	public void createGameModeScreen() {

		setInvisible();
		initializeGUI(player);
		/*
		 * TextArea
		 */

		this.gameModeTextPanel = new JPanel(new BorderLayout());
		gameModeTextPanel.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 0));
		gameModeTextPanel.setOpaque(false);

		this.gameModeTextArea = new JLabel("Choose your Game Mode:");
		gameModeTextArea.setFont(titleFont);

		/*
		 * Panel with createAdventureButton e createPartyButton
		 */
		this.gameModeButtonPanel = new JPanel(new GridLayout(3, 1));
		gameModeButtonPanel.setOpaque(false);

		/*
		 * Buttons for Game Modes
		 */

		this.createAdventureButton = new JButton("ADVENTURE MODE");
		createAdventureButton.setPreferredSize(new Dimension(150, 60));
		createAdventureButton.setFont(buttonFont);
		createAdventureButton.addActionListener(adventureHandler);
		createAdventureButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		createAdventureButton.setFocusPainted(false);
		createAdventureButton.setContentAreaFilled(false);

		this.createPartyButton = new JButton("CREATE YOUR PARTY");
		createPartyButton.setPreferredSize(new Dimension(150, 60));
		createPartyButton.setFont(buttonFont);
		createPartyButton.addActionListener(createAParty);
		createPartyButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		createPartyButton.setFocusPainted(false);
		createPartyButton.setContentAreaFilled(false);

		this.backButton = new JButton("BACK");
		backButton.setPreferredSize(new Dimension(150, 60));
		backButton.setFont(buttonFont);
		backButton.addActionListener(backHandler);
		backButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		backButton.setFocusPainted(false);
		backButton.setContentAreaFilled(false);

		/*
		 * Add
		 */
		gameModeTextPanel.add(gameModeTextArea);
		gameModeButtonPanel.add(createPartyButton);
		gameModeButtonPanel.add(createAdventureButton);
		gameModeButtonPanel.add(backButton);

		container.add(gameModeTextPanel, BorderLayout.CENTER);
		container.add(gameModeButtonPanel, BorderLayout.SOUTH);
	}

	/*
	 * This method creates the AdventureScreen
	 */
	public void createAdventureScreen() {

		setInvisible();

		/*
		 * TextArea first start
		 */

		this.mainTextPanel = new JPanel(new GridLayout(1, 0));
		mainTextPanel.setOpaque(false);
		/*
		 * Narrative text
		 */

		this.combatTextArea = new JTextArea();
		combatTextArea.setOpaque(false);
		combatTextArea.setEditable(false);
		combatTextArea.setFont(combatFont);
		combatTextArea.setLineWrap(true); // It goes new line automatically
		combatTextArea.setWrapStyleWord(true);

		this.narrationTextArea = new JTextArea();
		narrationTextArea.setOpaque(false);
		narrationTextArea.setEditable(false);
		narrationTextArea.setFont(defaultFont);
		narrationTextArea.setLineWrap(true); // It goes new line automatically
		narrationTextArea.setWrapStyleWord(true);
		narrationTextArea.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));

		/*
		 * creation of a JScrollPane for the combatTextArea
		 */

		JScrollPane combatDescriptionScrollPane = new JScrollPane(combatTextArea);
		combatDescriptionScrollPane.setOpaque(false);
		combatDescriptionScrollPane.getViewport().setOpaque(false);
		combatDescriptionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		combatDescriptionScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 0));

		/*
		 * Character Panel
		 */

		adventureButtonPanel = new JPanel(new GridLayout());
		adventureButtonPanel.setOpaque(false);
		adventureButtonPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

		adventureBottomPanel = new JPanel(new GridLayout(1, 3));
		adventureBottomPanel.setSize(new Dimension(DEFAUT_HEIGHT / 3, DEFAUT_WEIGHT));
		adventureBottomPanel.setOpaque(false);

		playerStatsTextArea = new JTextArea();
		playerStatsTextArea.setOpaque(false);
		playerStatsTextArea.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
		playerStatsTextArea.setEditable(false);
		playerStatsTextArea.setWrapStyleWord(true);
		playerStatsTextArea.setLineWrap(true);
		playerStatsTextArea.setFont(statsFont);

		mobStatsTextArea = new JTextArea();
		mobStatsTextArea.setOpaque(false);
		mobStatsTextArea.setEditable(false);
		mobStatsTextArea.setWrapStyleWord(true);
		mobStatsTextArea.setLineWrap(true);
		mobStatsTextArea.setFont(statsFont);

		startAdventureButton = new JButton("START ADVENTURE");
		startAdventureButton.setPreferredSize(new Dimension(50, 70));
		startAdventureButton.setFont(buttonFont);
		startAdventureButton.addActionListener(adventureHandler);
		startAdventureButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		startAdventureButton.setFocusPainted(false);
		startAdventureButton.setContentAreaFilled(false);

		/*
		 * Lifebar Player Panel
		 */

		this.playerPanel = new JPanel();
		playerPanel.setPreferredSize(new Dimension(DEFAUT_WEIGHT, 50));
		playerPanel.setFont(subtitleFont);
		playerPanel.setOpaque(false);

		lifeBar = new JProgressBar(0, 100);
		lifeBar.setOpaque(false);
		lifeBar.setUI((ProgressBarUI) BasicProgressBarUI.createUI(lifeBar));
		lifeBar.setPreferredSize(playerPanel.getPreferredSize());
		lifeBar.setStringPainted(true);
		lifeBar.setFont(subtitleFont);

		/*
		 * Add
		 */

		adventureButtonPanel.add(startAdventureButton);
		adventureBottomPanel.add(playerStatsTextArea);
		adventureBottomPanel.add(adventureButtonPanel);
		adventureBottomPanel.add(mobStatsTextArea);

		playerPanel.add(lifeBar, BorderLayout.CENTER);

		mainTextPanel.add(narrationTextArea);
		mainTextPanel.add(combatDescriptionScrollPane);

		container.add(mainTextPanel, BorderLayout.CENTER);
		container.add(playerPanel, BorderLayout.NORTH);
		container.add(adventureBottomPanel, BorderLayout.SOUTH);

		/*
		 * Funzioni
		 */

		fightUpdate();

	}

	/*
	 * This method creates a PartyScreen
	 */
	public void createAPartyScreen() {

		setInvisible();

		partyTextAreas = new ArrayList<>();

		partyPanel = new JPanel(new BorderLayout());
		partyPanel.setOpaque(false);

		partyWelcomePanel = new JPanel(new FlowLayout()); // TOP
		partyWelcomePanel.setOpaque(false);
		partyWelcomePanel.setBorder(BorderFactory.createEmptyBorder(100, 20, 50, 20));

		partyListPanel = new JPanel(new GridLayout()); // MID
		partyListPanel.setOpaque(false);

		partyButtonPanel = new JPanel(new GridLayout(0, 1)); // BOTTOM
		partyButtonPanel.setOpaque(false);

		partyLabel = new JLabel("How many people in your D&D party?");
		partyLabel.setFont(subtitleFont);

		numPlayersComboBox = new JComboBox<>(numPlayerOptions);
		numPlayersComboBox.setFont(buttonFont);

		backButton = new JButton("MAIN MENU");
		backButton.setFont(buttonFont);
		backButton.setPreferredSize(new Dimension(150, 60));
		backButton.addActionListener(backHandler);
		backButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		backButton.setFocusPainted(false);
		backButton.setContentAreaFilled(false);

		generateButton = new JButton("GENERATE A PARTY");
		generateButton.setFont(buttonFont);
		generateButton.setPreferredSize(new Dimension(150, 60));
		generateButton.addActionListener(generatePartyHandler);
		generateButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		generateButton.setFocusPainted(false);
		generateButton.setContentAreaFilled(false);

		partyWelcomePanel.add(partyLabel);
		partyWelcomePanel.add(numPlayersComboBox);
		partyButtonPanel.add(generateButton);
		partyButtonPanel.add(backButton);

		partyPanel.add(partyButtonPanel, BorderLayout.SOUTH);
		partyPanel.add(partyListPanel, BorderLayout.CENTER);
		partyPanel.add(partyWelcomePanel, BorderLayout.NORTH);

		container.add(partyPanel);
	}

	/*
	 * Handler for the buttons
	 */
	public class TitleScreenHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			createGameModeScreen();
		}
	}

	public class CreditsScreenHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			createCreditsScreen();
			creditTypeWriteIndex = 0;
			creditTimer.start();

		}
	}

	public class BackMainMenu implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			setInvisible();
			createMainMenu();

		}
	}

	public class QuitHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			System.exit(0);
		}

	}

	public class AdventureHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			if (combat.getMobFighted() == 0) {

				createAdventureScreen();
				narrationTypeWriteIndex = 0;
				combatTypeWriteIndex = 0;

			} else {

				fightUpdate();
				narrationTimer.start();
				combatTimer.start();
				narrationTypeWriteIndex = 0;
				combatTypeWriteIndex = 0;
			}

		}
	}

	public class CreateAPartyHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			createAPartyScreen();
		}

	}

	public class GeneratePartyHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			generatePlayers();
		}
	}

	public class RestartGameHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			createMainMenu();

		}

	}

	/*
	 * Handler for the TypeWriteStyle narration
	 */
	public class NarrationTimeHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (narrationTypeWriteIndex < narrationString.length()) {
				narrationTextArea.append(String.valueOf(narrationString.charAt(narrationTypeWriteIndex)));
				narrationTypeWriteIndex++;
			} else {
				narrationTimer.stop();
			}
		}
	}

	public class CombatTimeHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (combatTypeWriteIndex < combatString.length()) {
				combatTextArea.append(String.valueOf(combatString.charAt(combatTypeWriteIndex)));
				combatTypeWriteIndex++;
			} else {
				combatTimer.stop();
			}
		}

	}

	public class CreditTimeHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (creditTypeWriteIndex < creditString.length()) {
				creditTextArea.append(String.valueOf(creditString.charAt(creditTypeWriteIndex)));
				creditTypeWriteIndex++;
			} else {
				creditTimer.stop();
			}
		}
	}

	/*
	 * ImagePanel Class
	 */

	public class ImagePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private Image backgroundImage;

		public ImagePanel(Image backgroundImage) {
			this.backgroundImage = backgroundImage;
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (backgroundImage != null) {
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}
		}
	}
	/*
	 * Methods to update the GUI
	 */

	/*
	 * This method update the status of the lifebar in the AdventureScreen
	 */
	private void updateLifeBar() {

		lifeBar.setValue(player.getHealthPoints());

		if (player.getHealthPoints() <= 30) {

			lifeBar.setForeground(Color.RED);

		} else {

			lifeBar.setForeground(Color.GREEN);
		}
		lifeBar.setString(player.getName().get() + " PF: " + player.getHealthPoints() + "%");
	}

	/*
	 * This method is the main function of the Adventure, it updates the status of
	 * every combat choosing what's going on
	 */
	private void fightUpdate() {

		updateLifeBar();
		cleanTextArea();

		if (combat.getMobFighted() < combat.getLastMob() && player.isAlive() && !isEndGame) {

			if (combat.getMobFighted() > 0) {

				startAdventureButton.setText("EXPLORE MORE");

				narrationString = rng.generateNarration(combat.getActualMob());
				narrationTimer = new Timer(NARRATION_SPEED, new NarrationTimeHandler());

				combatString = combat.getOutputString();
				combatTimer = new Timer(COMBAT_SPEED, new CombatTimeHandler());

				playerStatsTextArea.setText(combat.getPlayerStats());
				mobStatsTextArea.setText(combat.getMobStats());
				combat.fight();

			} else {
				/*
				 * First run
				 */

				narrationString = rng.generateNarration(combat.getActualMob());
				narrationTimer = new Timer(NARRATION_SPEED, new NarrationTimeHandler());
				narrationTimer.start();
				combatTextArea.setText(combat.getOutputString());
				playerStatsTextArea.setText(combat.getPlayerStats());
				mobStatsTextArea.setText("MOB STATS:");
				combat.fight();
			}

			/*
			 * End of the adventure
			 */

		} else if (!isEndGame) {

			cleanTextArea();

			if (player.isAlive()) {

				/*
				 * Lat fight recap
				 */
				narrationString = rng.generateNarration(combat.getActualMob());
				narrationTimer = new Timer(NARRATION_SPEED, new NarrationTimeHandler());

				combatString = combat.getOutputString();
				combatTimer = new Timer(COMBAT_SPEED, new CombatTimeHandler());

				playerStatsTextArea.setText(combat.getPlayerStats());
				mobStatsTextArea.setText(combat.getMobStats());
				narrationTextArea.setFont(defaultFont);
				combatTextArea.setFont(combatFont);

			} else {

				narrationString = rng.generateNarration(combat.getActualMob());
				narrationTimer = new Timer(NARRATION_SPEED, new NarrationTimeHandler());

				combatString = combat.getOutputString();
				combatTimer = new Timer(COMBAT_SPEED, new CombatTimeHandler());

				playerStatsTextArea.setText(combat.getPlayerStats());
				mobStatsTextArea.setText(combat.getMobStats());
				narrationTextArea.setFont(defaultFont);
				combatTextArea.setFont(combatFont);
			}

			isEndGame = true;
			startAdventureButton.setText("THE END");

		} else {

			if (player.isAlive()) {

				narrationString = ("Exhausted but victorious, " + player.getName()
						+ "finally returned to his castle, where the cheers of his subjects resounded through the land. He had faced nine different enemies in nine different times, and through each trial, his indomitable spirit and unyielding courage had prevailed. The hero had returned, his castle and kingdom safe once more, but he knew that his adventures were far from over, for the call of the next quest awaited on the horizon, and the heart of a hero never rests."
						+ " " + combat.getMobFighted());
				narrationTimer = new Timer(NARRATION_SPEED, new NarrationTimeHandler());

				combatString = "Your adventure comes to an end. Thanks for playing this game, I hope you enjoyed it.\n\nYou did it! You won the game, will you have the same luck next time?";
				combatTimer = new Timer(NARRATION_SPEED, new CombatTimeHandler());
				combatTextArea.setFont(defaultFont);

			} else {

				narrationString = player.getName().get()
						+ " crumpled to the floor, his life force ebbing away. His vision blurred, and he heard the mournful whispers of the ancient trees. As he lay there, moments from death, he reflected on his many battles, the countless lives he had saved, and the kingdom he loved."
						+ "In his final moments, he whispered a prayer to the gods, hoping for salvation. It was then that a radiant figure, an angelic being, descended from the heavens. The celestial being, with wings of pure light, touched "
						+ player.getName().get() + "'s forehead, and a warm, healing light enveloped him.";

				narrationTimer = new Timer(NARRATION_SPEED, new NarrationTimeHandler());

				combatString = "Your adventure comes to an end. Thanks for playing this game, I hope you enjoyed it.\n\nUnfortunatly you lost the game, but you can try again to be more lucky next time.\n\nYou are dead fighting your "
						+ combat.getMobFighted() + "° enemy.";
				combatTimer = new Timer(NARRATION_SPEED, new CombatTimeHandler());
				combatTextArea.setFont(defaultFont);

			}

			startAdventureButton.setText("MAIN MENU");
			startAdventureButton.addActionListener(restartGameHandler);

		}
	}

	/*
	 * This function clean the TextArea for the combatTextArea and for the
	 * narrationTextArea
	 */
	private void cleanTextArea() {

		narrationString = new String();
		narrationTextArea.setText(new String());
		combatString = new String();
		combatTextArea.setText(new String());
	}

	/*
	 * This method generate players for the createAParty Handler
	 */
	private void generatePlayers() {

		partyTextAreas.clear();
		partyListPanel.removeAll();

		partyMember = (int) numPlayersComboBox.getSelectedItem();

		for (int i = 0; i < partyMember; i++) {

			JTextArea partyMemberTextArea = new JTextArea(new PartyPg().toString());
			partyMemberTextArea.setOpaque(false);
			partyMemberTextArea.setFont(defaultFont);
			partyMemberTextArea.setLineWrap(true);
			partyMemberTextArea.setWrapStyleWord(true);
			partyMemberTextArea.setEditable(false);
			partyMemberTextArea.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
			partyTextAreas.add(partyMemberTextArea);
		}

		for (JTextArea textArea : partyTextAreas) {
			partyListPanel.add(textArea);
		}

		partyListPanel.revalidate(); // Refresh the layout.

	}
}
