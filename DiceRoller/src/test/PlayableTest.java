package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fightStyles.FightWithAWeapon;
import fightStyles.FightWithTwoWeapons;
import playable.Player;

import static org.junit.jupiter.api.Assertions.*;

public class PlayableTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(100, "TestName");
    }

    @Test
    void testSetUp() {
    	
        assertEquals(player.getName().get(), "TestName");
        assertTrue(player.getInitiative() > 0 && player.getInitiative() <= 20);
        assertTrue(player.getArmorClass() > 10);
        assertEquals(player.getHealthPoints(), player.getMaxHP());
        assertTrue(player.getFightStyle().getClass().equals(new FightWithAWeapon().getClass()) || player.getFightStyle().getClass().equals(new FightWithTwoWeapons().getClass() ));
    }
    
    @Test
    void testHp() {
    	player.setDamage(20);
        assertEquals(player.getHealthPoints(), 80);
        player.restoreFullHp();
        assertEquals(player.getHealthPoints(), 100);
        player.setDamage(90);
        player.restoreHp();	//ripristina il 20% dei PF
        assertEquals(player.getHealthPoints(), 20);


    }

}
