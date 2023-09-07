package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.plaf.basic.BasicProgressBarUI;

import feature.PartyPg;
import playable.Player;
import utilities.Combat;
import utilities.NarrationGenerator;

/*
 * Beta
 */
public class Gui {

	private static final Integer DEFAUT_WEIGHT = 900;
	private static final Integer DEFAUT_HEIGHT = 600;
	private static final Integer NARRATION_SPEED = 30;
	private static final Integer COMBAT_SPEED = 10;
	private static final Integer CREDIT_SPEED = 50;


	private JFrame window;

	private Container container;

	private JPanel titlePanel, startButtonPanel, mainTextPanel, adventureBottomPanel, playerPanel, creditTextPanel,
			backButtonPanel, gameModeTextPanel, gameModeButtonPanel, partyPanel, partyListPanel, partyButtonPanel,
			partyWelcomePanel, adventureButtonPanel;

	private JLabel titleLabel, gameModeTextArea, subtitleLabel, partyLabel;

	private Font titleFont = new Font("Press Start 2P", Font.BOLD, 30), defaultFont = new Font("Press Start 2P", Font.PLAIN, 16),
			buttonFont = new Font("Press Start 2P", Font.PLAIN, 15), subtitleFont = new Font("Press Start 2P", Font.PLAIN, 20),
			creditsFont = new Font("Press Start 2P", Font.PLAIN, 20), combatFont = new Font("Press Start 2P", Font.ITALIC, 12),
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

	/*
	 * Object used
	 */
	private Player player;
	private Combat combat;
	private NarrationGenerator rng;
	private String creditString, narrationString, combatString;
	private Integer[] numPlayerOptions = { 1, 2, 3, 4};
	private Integer partyMember = 0;

	public Gui(Player player, Combat combat) {

		this.player = player;
		this.combat = combat;
		this.rng = new NarrationGenerator(player);
		initializeGUI();
		createMainMenu();

	}

	private void initializeGUI() {

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
	 * METODO DI CREAZIONE MENU - DONE
	 */
	public void createMainMenu() {

		/*
		 * Main Widow, not resizable
		 */
		this.window = new JFrame();
		window.setTitle("DiceAdventure");
		window.setSize(DEFAUT_WEIGHT, DEFAUT_HEIGHT); // WxH
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false); // Layout non modificabile

		/*
		 * Container
		 */
		this.container = window.getContentPane();
		container.setLayout(new BorderLayout(10, 10)); // This creates the BorderLayout, which manages the layout of
		container.setBackground(Color.WHITE);

		/*
		 * Title Panel
		 */
		this.titlePanel = new JPanel(new GridLayout(0, 1));
		titlePanel.setBackground(Color.WHITE);
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
		startButtonPanel.setBackground(Color.WHITE);

		/*
		 * Start, Credit, Quit Button
		 */

		this.startButton = new JButton("PLAY");
		startButton.setFont(buttonFont);
		startButton.addActionListener(titleScreenHandler);
		startButton.setPreferredSize(new Dimension(150, 60));

		this.creditButton = new JButton("CREDITS");
		creditButton.setFont(buttonFont);
		creditButton.addActionListener(creditsScreenHandler);
		creditButton.setPreferredSize(new Dimension(150, 60));

		this.quitButton = new JButton("QUIT");
		quitButton.setFont(buttonFont);
		quitButton.addActionListener(quitHandler);
		quitButton.setPreferredSize(new Dimension(150, 60));

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

		window.setVisible(true); // JFrame visibile

	}

	/*
	 * METODO DI CREAZIONE CREDITS - DONE
	 */
	public void createCreditsScreen() {

		/*
		 * Facciamo sparire tutto
		 */
		setInvisible();

		/*
		 * TextArea
		 */

		this.creditTextPanel = new JPanel(new BorderLayout());
		creditTextPanel.setBackground(Color.WHITE);

		creditString = "DiceAdventure is a Java project made by Edoardo Doglioni\nThis project wants to simulate"
				+ " a Dungeons and Dragons Adventure.\n" + "The simulation is based around the concept of dice.\n"
				+ "Rolling dices the player can simulate a fight against an enemyes\n"
				+ "Once he survived every fight the simulation is completed succesfully\n"
				+ "Otherwise the game will end with a lose\n"
				+ "This project have been developed for the Programmazione e Modellazione ad Oggetti exam\n"
				+ "Last update: Urbino, 13/07/2023";

		this.creditTextArea = new JTextArea();
		creditTextArea.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));


		creditTimer = new Timer(CREDIT_SPEED, new CreditTimeHandler());

		creditTextArea.setEditable(false);
		creditTextArea.setFont(creditsFont);
		creditTextArea.setLineWrap(true); // It goes new line automatically
		creditTextArea.setWrapStyleWord(true);

		/*
		 * BackButton
		 */
		this.backButtonPanel = new JPanel(new GridLayout());
		backButtonPanel.setBackground(Color.WHITE);
		this.backButton = new JButton("BACK");
		backButton.setFont(buttonFont);
		backButton.addActionListener(backHandler);
		backButton.setPreferredSize(new Dimension(150, 60));

		/*
		 * Add
		 */
		backButtonPanel.add(backButton);
		creditTextPanel.add(creditTextArea, BorderLayout.CENTER);

		container.add(creditTextPanel, BorderLayout.CENTER);
		container.add(backButtonPanel, BorderLayout.SOUTH);

	}

	/*
	 * METODO DI SCELTA GAME MODE - DONE
	 */
	public void createGameModeScreen() {

		setInvisible();
		/*
		 * TextArea
		 */

		this.gameModeTextPanel = new JPanel(new BorderLayout());
		gameModeTextPanel.setBackground(Color.WHITE);

		this.gameModeTextArea = new JLabel("Choose your Game Mode:");
		gameModeTextArea.setFont(titleFont);
		gameModeTextPanel.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 0));

		/*
		 * Buttons for Game Modes
		 */
		/*
		 * Panel contenente createAdventureButton e createPartyButton
		 */
		this.gameModeButtonPanel = new JPanel(new GridLayout(3, 1));
		gameModeButtonPanel.setBackground(Color.WHITE);

		this.createAdventureButton = new JButton("ADVENTURE MODE");
		createAdventureButton.setPreferredSize(new Dimension(150, 60));
		createAdventureButton.setFont(buttonFont);
		createAdventureButton.addActionListener(adventureHandler);

		this.createPartyButton = new JButton("CREATE YOUR PARTY");
		createPartyButton.setPreferredSize(new Dimension(150, 60));
		createPartyButton.setFont(buttonFont);
		createPartyButton.addActionListener(createAParty);

		this.backButton = new JButton("BACK");
		backButton.setPreferredSize(new Dimension(150, 60));
		backButton.setFont(buttonFont);
		backButton.addActionListener(backHandler);

		/*
		 * Da fare createPartyHandler
		 */
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
	 * METODO DI AVVIO AVVENTURA - DONE
	 */
	public void createAdventureScreen() {

		/*
		 * Facciamo sparire tutto ciò che è presente nella finestra precedente
		 */

		setInvisible();
		/*
		 * TextArea primo avvio
		 */

		this.mainTextPanel = new JPanel(new GridLayout(1, 2));
		mainTextPanel.setBackground(Color.WHITE);
		mainTextPanel.setBorder(BorderFactory.createEtchedBorder());
		/*
		 * Testo narrativo
		 */

		this.combatTextArea = new JTextArea();
		combatTextArea.setBackground(Color.WHITE);
		combatTextArea.setEditable(false);
		combatTextArea.setFont(combatFont);
		combatTextArea.setLineWrap(true); // It goes new line automatically
		combatTextArea.setWrapStyleWord(true);

		/*
		 * Creazione di uno JScrollPane che conterrà la JTextArea
		 */

		this.narrationTextArea = new JTextArea();
		narrationTextArea.setEditable(false);
		narrationTextArea.setFont(defaultFont);
		narrationTextArea.setLineWrap(true); // It goes new line automatically
		narrationTextArea.setWrapStyleWord(true);
		narrationTextArea.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));

		JScrollPane combatDescriptionScrollPane = new JScrollPane(combatTextArea);
		combatDescriptionScrollPane.setBackground(Color.WHITE);
		combatDescriptionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		combatDescriptionScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 0));

		/*
		 * Character Stats
		 */

		adventureButtonPanel = new JPanel(new GridLayout());
		adventureButtonPanel.setBackground(Color.WHITE);
		adventureButtonPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

		adventureBottomPanel = new JPanel(new GridLayout(1, 3));
		adventureBottomPanel.setSize(new Dimension(DEFAUT_HEIGHT/3, DEFAUT_WEIGHT));
		adventureBottomPanel.setBackground(Color.WHITE);
		adventureBottomPanel.setBorder(BorderFactory.createEtchedBorder());

		playerStatsTextArea = new JTextArea();
		playerStatsTextArea.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
		playerStatsTextArea.setEditable(false);
		playerStatsTextArea.setWrapStyleWord(true);
		playerStatsTextArea.setLineWrap(true);
		playerStatsTextArea.setFont(statsFont);

		mobStatsTextArea = new JTextArea();
		mobStatsTextArea.setEditable(false);
		mobStatsTextArea.setWrapStyleWord(true);
		mobStatsTextArea.setLineWrap(true);
		mobStatsTextArea.setFont(statsFont);

		startAdventureButton = new JButton("START ADVENTURE");
		startAdventureButton.setPreferredSize(new Dimension(50, 70));
		startAdventureButton.setFont(buttonFont);
		startAdventureButton.addActionListener(adventureHandler);

		/*
		 * TOP Player Panel
		 */

		this.playerPanel = new JPanel();
		playerPanel.setPreferredSize(new Dimension(DEFAUT_WEIGHT, 50));
		playerPanel.setFont(subtitleFont);
		playerPanel.setBackground(Color.WHITE);

		lifeBar = new JProgressBar(0, 100);
		lifeBar.setBackground(Color.WHITE);
		lifeBar.setUI((ProgressBarUI) BasicProgressBarUI.createUI(lifeBar));
		lifeBar.setBorder(BorderFactory.createEtchedBorder());
		lifeBar.setPreferredSize(playerPanel.getPreferredSize());
		lifeBar.setStringPainted(true);
		lifeBar.setOpaque(false);
		lifeBar.setFont(subtitleFont);

		/*
		 * Add
		 */

		adventureButtonPanel.add(startAdventureButton);

		playerPanel.add(lifeBar, BorderLayout.CENTER);
		mainTextPanel.add(narrationTextArea);
		mainTextPanel.add(combatDescriptionScrollPane);
		container.add(mainTextPanel, BorderLayout.CENTER);
		container.add(playerPanel, BorderLayout.NORTH);
		container.add(adventureBottomPanel, BorderLayout.SOUTH);
		adventureBottomPanel.add(playerStatsTextArea);
		adventureBottomPanel.add(adventureButtonPanel);
		adventureBottomPanel.add(mobStatsTextArea);

		/*
		 * Funzioni
		 */

		fightUpdate();

	}

	/*
	 * METODO DI CREAZIONE PARTY
	 */
	public void createAPartyScreen() {

		setInvisible();

		partyTextAreas = new ArrayList<>();

		partyPanel = new JPanel(new BorderLayout());
		partyPanel.setBackground(Color.WHITE);
		
		partyWelcomePanel = new JPanel(new FlowLayout()); // TOP
		partyWelcomePanel.setBackground(Color.WHITE);
		partyWelcomePanel.setBorder(BorderFactory.createEmptyBorder(100, 20, 50, 20));

		
		partyListPanel = new JPanel(new GridLayout()); // MID
		partyListPanel.setBackground(Color.WHITE);
		
		partyButtonPanel = new JPanel(new GridLayout(0, 1)); // BOTTOM
		partyButtonPanel.setBackground(Color.WHITE);

		partyLabel = new JLabel("How many people in your D&D party?");
		partyLabel.setFont(subtitleFont);
		
		numPlayersComboBox = new JComboBox<>(numPlayerOptions);
		numPlayersComboBox.setFont(buttonFont);
		
		backButton = new JButton("MAIN MENU");
		backButton.setFont(buttonFont);
		backButton.setPreferredSize(new Dimension(150, 60));
		backButton.addActionListener(backHandler);
		
		generateButton = new JButton("GENERATE A PARTY");
		generateButton.setFont(buttonFont);
		generateButton.setPreferredSize(new Dimension(150, 60));
		generateButton.addActionListener(generatePartyHandler);
		

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
	 * HANDLER
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
			titlePanel.setVisible(true);
			startButtonPanel.setVisible(true);

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

	public class GeneratePartyHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			generatePlayers();
		}
	}

	/*
	 * Utility Methods
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
	 * The main function of the Adventure, this function update the status of every
	 * combat choosing what's going on
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
			 * Fine avventura
			 */

		} else if (!isEndGame) {

			cleanTextArea();

			if (player.isAlive()) {

				/*
				 * Resoconto ultimo fight
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
			startAdventureButton.setText("The End");

		} else {

			if (player.isAlive()) {

				narrationString = ("Exhausted but victorious, " + player.getName()
						+ "finally returned to his castle, where the cheers of his subjects resounded through the land. He had faced nine different enemies in nine different times, and through each trial, his indomitable spirit and unyielding courage had prevailed. The hero had returned, his castle and kingdom safe once more, but he knew that his adventures were far from over, for the call of the next quest awaited on the horizon, and the heart of a hero never rests."
						+ " " + combat.getMobFighted());
				narrationTimer = new Timer(NARRATION_SPEED, new NarrationTimeHandler());

				combatString = "Your adventure comes to an end. Thanks for playing this game, I hope you enjoyed it. You did it! You won the game, will you have the same luck next time?";
				combatTimer = new Timer(NARRATION_SPEED, new CombatTimeHandler());
				combatTextArea.setFont(defaultFont);

			} else {

				narrationString = player.getName().get()
						+ " crumpled to the floor, his life force ebbing away. His vision blurred, and he heard the mournful whispers of the ancient trees. As he lay there, moments from death, he reflected on his many battles, the countless lives he had saved, and the kingdom he loved."
						+ "In his final moments, he whispered a prayer to the gods, hoping for salvation. It was then that a radiant figure, an angelic being, descended from the heavens. The celestial being, with wings of pure light, touched "
						+ player.getName().get() + "'s forehead, and a warm, healing light enveloped him.";

				narrationTimer = new Timer(NARRATION_SPEED, new NarrationTimeHandler());
				
				combatString = "Your adventure comes to an end. Thanks for playing this game, I hope you enjoyed it. Unfortunatly you lost the game, but you can try again to be more lucky next time";
				combatTimer = new Timer(NARRATION_SPEED, new CombatTimeHandler());
				combatTextArea.setFont(defaultFont);

			}

			startAdventureButton.setText("QUIT");
			startAdventureButton.addActionListener(quitHandler);

		}
	}

	private void cleanTextArea() {

		narrationString = new String();
		narrationTextArea.setText(new String());
		combatString = new String();
		combatTextArea.setText(new String());
	}

	private void generatePlayers() {

		partyTextAreas.clear();
		partyListPanel.removeAll();

		partyMember = (int) numPlayersComboBox.getSelectedItem();

		for (int i = 0; i < partyMember; i++) {

			JTextArea partyMemberTextArea = new JTextArea(new PartyPg().toString());
			partyMemberTextArea.setFont(defaultFont);
			partyMemberTextArea.setLineWrap(true);
			partyMemberTextArea.setWrapStyleWord(true);
			partyMemberTextArea.setEditable(false);
			partyMemberTextArea.setBorder(BorderFactory.createEtchedBorder());
			partyTextAreas.add(partyMemberTextArea);
		}

		for (JTextArea textArea : partyTextAreas) {
			partyListPanel.add(textArea);
		}

		partyListPanel.revalidate(); // Refresh the layout.

	}
}
