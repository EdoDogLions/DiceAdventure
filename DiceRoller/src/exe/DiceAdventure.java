package exe;

import javax.swing.SwingUtilities;

import GUI.View;
import playable.Player;

/**
 * @author edoardodoglioni This is the main class of the project. The game is
 *         setupped with a Player named Owyn and a Combat
 */
public class DiceAdventure {

	public static void main(String[] args) {
		Player player = new Player(100, "Owyn");

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new View(player);
			}
		});

	}
}
