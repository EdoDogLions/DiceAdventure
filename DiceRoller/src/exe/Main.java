package exe;

import javax.swing.SwingUtilities;

import GUI.Gui;
import playable.*;
import utilities.Combat;

public class Main {

	public static void main(String[] args) {

		/*
		 * GAME START
		 */

		Player player = new Player(100, "Owyn");
		Combat combat = new Combat(player);
		
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				new Gui(player, combat);
			}
		});

	}
}
