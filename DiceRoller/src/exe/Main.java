package exe;

import javax.swing.SwingUtilities;

import GUI.Gui;
import playable.*;
import utilities.Combat;

/**
 * @author edoardodoglioni This is the main class of the project. The game is
 *         setupped with a Player named Owyn and a Combat
 */
public class Main {

	public static void main(String[] args) {
		Player player = new Player(100, "Owyn");
		Combat combat = new Combat(player);

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new Gui(player, combat);
			}
		});

	}
}
