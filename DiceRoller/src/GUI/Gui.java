package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import playable.Player;
import utilities.Combat;
import utilities.RandomNarrationGenerator;

/*
 * Far fare un loop in più per far apparire il resoconto dell'ultimo fight e poi QUIT
 */
/*
 * Beta
 */
public class Gui {

	private static final Integer DEFAUT_WEIGHT = 900;
	private static final Integer DEFAUT_HEIGHT = 600;

	private JFrame window;

	private Container container;

	private JPanel titlePanel, startButtonPanel, mainTextPanel, adventureBottomPanel, playerPanel, creditTextPanel,
			backButtonPanel, gameModeTextPanel, gameModeButtonPanel;

	private JLabel titleLabel, gameModeTextArea, subtitleLabel;

	private Font titleFont = new Font("Dyuthi", Font.PLAIN, 60), defaultFont = new Font("Dyuthi", Font.PLAIN, 20),
			buttonFont = new Font("Dyuthi", Font.PLAIN, 30), subtitleFont = new Font("Dyuthi", Font.PLAIN, 40),
			creditsFont = new Font("Dyuthi", Font.PLAIN, 25), narrationFont = new Font("Dyuthi", Font.ITALIC, 15);

	private JButton startButton, creditButton, quitButton, backButton, createAdventureButton, createPartyButton,
			startAdventureButton;

	private JTextArea narrationTextArea, creditTextArea, playerStatsTextArea, mobStatsTextArea, combatTextArea;;

	private JProgressBar lifeBar;

	private Timer timer;

	private Integer typeWriteIndex = 0;

	private Boolean isEndGame = false;
	/*
	 * Handlers
	 */
	private TitleScreenHandler titleScreenHandler = new TitleScreenHandler();
	private CreditsScreenHandler creditsScreenHandler = new CreditsScreenHandler();
	private MainMenuHandler mainMenuHandler = new MainMenuHandler();
	private AdventureHandler adventureHandler = new AdventureHandler();
	private QuitHandler quitHandler = new QuitHandler();
	private BackMainMenu backHandler = new BackMainMenu();

	/*
	 * Object used
	 */
	private Player player;
	private Combat combat;
	private RandomNarrationGenerator rng;
	private String creditString;
	private ActionListener createPartyHandler;

	public Gui(Player player, Combat combat) {

		this.player = player;
		this.combat = combat;
		this.rng = new RandomNarrationGenerator(player);
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
	}

	/*
	 * METODO DI CREAZIONE MENU - DONE
	 */
	public void createMainMenu() {

		/*
		 * Main Widow, not resizable
		 */
		this.window = new JFrame();
		window.setSize(DEFAUT_WEIGHT, DEFAUT_HEIGHT); // WxH
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false); // Layout non modificabile

		/*
		 * Container
		 */
		this.container = window.getContentPane();
		container.setLayout(new BorderLayout(10, 10)); // This creates the BorderLayout, which manages the layout of

		/*
		 * Title Panel
		 */
		this.titlePanel = new JPanel(new GridLayout(0, 1));
		titleLabel = new JLabel("Welcome in DiceAdventure");
		titleLabel.setBorder(BorderFactory.createEmptyBorder(150, 130, 20, 20));
		titleLabel.setFont(titleFont);
		subtitleLabel = new JLabel("a Dungeons & Dragons Simulator");
		subtitleLabel.setBorder(BorderFactory.createEmptyBorder(-130, 180, 20, 20));
		subtitleLabel.setFont(subtitleFont);

		/*
		 * Button Panel
		 */

		this.startButtonPanel = new JPanel(new GridLayout(0, 1));

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

		creditString = "DiceAdventure is a Java project made by Edoardo Doglioni\nThis project wants to simulate"
				+ " a Dungeons and Dragons Adventure.\n" + "The simulation is based around the concept of dice.\n"
				+ "Rolling dices the player can simulate a fight against an enemyes\n"
				+ "Once he surveved every fight the simulation is completed succesfully\n"
				+ "Otherwise the game will end with a lose"
				+ "This project have been developed for the Programmazione e Modellazione ad Oggetti exam\n"
				+ "Last update: Urbino, 13/07/2023";

		this.creditTextArea = new JTextArea();

		timer = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (typeWriteIndex < creditString.length()) {
					creditTextArea.append(String.valueOf(creditString.charAt(typeWriteIndex)));
					typeWriteIndex++;
				} else {
					timer.stop();
				}
			}
		});

		creditTextPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		creditTextArea.setEditable(false);
		creditTextArea.setFont(creditsFont);
		creditTextArea.setLineWrap(true); // It goes new line automatically

		/*
		 * BackButton
		 */
		this.backButtonPanel = new JPanel();
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

		this.createAdventureButton = new JButton("ADVENTURE MODE");
		createAdventureButton.setPreferredSize(new Dimension(150, 60));
		createAdventureButton.setFont(buttonFont);
		createAdventureButton.addActionListener(adventureHandler);

		this.createPartyButton = new JButton("CREATE YOUR PARTY");
		createPartyButton.setPreferredSize(new Dimension(150, 60));
		createPartyButton.setFont(buttonFont);
		createPartyButton.addActionListener(createPartyHandler);

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
		/*
		 * Testo narrativo
		 */

		this.combatTextArea = new JTextArea();
		combatTextArea.setEditable(false);
		combatTextArea.setFont(narrationFont);
		combatTextArea.setLineWrap(true); // It goes new line automatically
		combatTextArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		/*
		 * Creazione di uno JScrollPane che conterrà la JTextArea
		 */

		this.narrationTextArea = new JTextArea();
		narrationTextArea.setEditable(false);
		narrationTextArea.setFont(defaultFont);
		narrationTextArea.setLineWrap(true); // It goes new line automatically
		narrationTextArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JScrollPane combatDescriptionScrollPane = new JScrollPane(combatTextArea);
		combatDescriptionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		combatDescriptionScrollPane.setBorder(null);

		/*
		 * Character Stats
		 */

		this.adventureBottomPanel = new JPanel(new GridLayout(1, 3));

		playerStatsTextArea = new JTextArea();
		playerStatsTextArea.setEditable(false);
		playerStatsTextArea.getWrapStyleWord();
		playerStatsTextArea.setLineWrap(true);
		playerStatsTextArea.setFont(defaultFont);

		mobStatsTextArea = new JTextArea();
		mobStatsTextArea.setEditable(false);
		mobStatsTextArea.getWrapStyleWord();
		mobStatsTextArea.setLineWrap(true);
		mobStatsTextArea.setFont(defaultFont);

		/*
		 * Player Panel
		 */

		this.playerPanel = new JPanel();
		playerPanel.setPreferredSize(new Dimension(DEFAUT_WEIGHT, 50));
		playerPanel.setFont(subtitleFont);
		lifeBar = new JProgressBar(0, 100);
		lifeBar.setPreferredSize(playerPanel.getPreferredSize());
		lifeBar.setStringPainted(true);
		lifeBar.setOpaque(true);
		lifeBar.setFont(subtitleFont);

		/*
		 * StartAdventure Button
		 */

		this.startAdventureButton = new JButton("START ADVENTURE");
		startAdventureButton.setPreferredSize(new Dimension(50, 70));
		startAdventureButton.setFont(buttonFont);
		startAdventureButton.addActionListener(adventureHandler);

		/*
		 * Add
		 */

		playerPanel.add(lifeBar, BorderLayout.CENTER);
		mainTextPanel.add(narrationTextArea);
		mainTextPanel.add(combatDescriptionScrollPane);
		container.add(mainTextPanel, BorderLayout.CENTER);
		container.add(playerPanel, BorderLayout.NORTH);
		container.add(adventureBottomPanel, BorderLayout.SOUTH);
		adventureBottomPanel.add(playerStatsTextArea);
		adventureBottomPanel.add(startAdventureButton);
		adventureBottomPanel.add(mobStatsTextArea);

		/*
		 * Funzioni
		 */

		fightUpdate();

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
			typeWriteIndex = 0;
			timer.start();

		}
	}

	public class MainMenuHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			createMainMenu();

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

			} else {

				fightUpdate();
			}

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
	 * A function who update the status of the fight
	 */
	private void fightUpdate() {

		updateLifeBar();

		if (combat.getMobFighted() < combat.getLastMob() && player.isAlive() && !isEndGame) {

			if (combat.getMobFighted() > 0) {

				startAdventureButton.setText("EXPLORE MORE");
				narrationTextArea.setText(rng.generateRandomNarration() + " " + combat.getMobFighted());
				combatTextArea.setText(combat.getOutputString());
				playerStatsTextArea.setText(combat.getPlayerStats());
				mobStatsTextArea.setText(combat.getMobStats());
				combat.fight();

			} else {
				/*
				 * First run
				 */

				narrationTextArea.setText(rng.generateRandomNarration() + " " + combat.getMobFighted());
				combatTextArea.setText(combat.getOutputString());
				playerStatsTextArea.setText(combat.getPlayerStats());
				mobStatsTextArea.setText("MOB STATS:");
				combat.fight();
			}

			/*
			 * Fine avventura
			 */

		} else if (!isEndGame) {

			if (player.isAlive()) {

				/*
				 * Resoconto ultimo fight
				 */
				narrationTextArea.setText(rng.generateRandomNarration() + " " + combat.getMobFighted());
				combatTextArea.setText(combat.getOutputString());
				playerStatsTextArea.setText(combat.getPlayerStats());
				mobStatsTextArea.setText(combat.getMobStats());
				narrationTextArea.setFont(defaultFont);
				combatTextArea.setFont(narrationFont);

			} else {

				narrationTextArea.setText(rng.generateRandomNarration() + " " + combat.getMobFighted());
				combatTextArea.setText(combat.getOutputString());
				playerStatsTextArea.setText(combat.getPlayerStats());
				mobStatsTextArea.setText(combat.getMobStats());
				narrationTextArea.setFont(defaultFont);
				combatTextArea.setFont(narrationFont);
			}

			isEndGame = true;
			startAdventureButton.setText("RECAP");

		} else {

			if (player.isAlive()) {
				narrationTextArea.setText("HAI SPACCATO BRO" + " " + combat.getMobFighted());
				combatTextArea.setText("YOU WON");

			} else {

				narrationTextArea.setText("SEI MORTO BRO" + " " + combat.getMobFighted());
				combatTextArea.setText("YOU LOSE");

			}

			startAdventureButton.setText("QUIT");
			startAdventureButton.addActionListener(quitHandler);

		}
	}
}
