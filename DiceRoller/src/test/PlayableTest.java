package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fightStyles.FightWithAWeapon;
import fightStyles.FightWithTwoWeapons;
import playable.Mob;
import playable.Player;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;


public class PlayableTest {

    private Player player;
    private Mob mob;

    @BeforeEach
    void setUp() {
        player = new Player(100, "TestName");
        mob = new Mob(10, 10);
    }

    @Test
    void testSetUp() {
    	
    	 assertEquals(player.getName().get(), "TestName");
         assertTrue(player.getInitiative() > 0 && player.getInitiative() <= 20);
         assertTrue(player.getArmorClass() > 10);
         assertEquals(player.getHealthPoints(), player.getMaxHP());
         assertTrue(player.getFightStyle().getClass().equals(new FightWithAWeapon().getClass()) || player.getFightStyle().getClass().equals(new FightWithTwoWeapons().getClass() ));
         assertFalse(mob.getName().isEmpty());
         assertTrue(mob.getInitiative() > 0 && player.getInitiative() <= 20);
         assertTrue(mob.getArmorClass() >= 10);
         assertEquals(mob.getHealthPoints(), mob.getMaxHP());
         assertTrue(mob.getFightStyle().getClass().equals(new FightWithAWeapon().getClass()) || player.getFightStyle().getClass().equals(new FightWithTwoWeapons().getClass() ));
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
    
    @Test
    void mobTest() {
        assertTrue(mob.isAlive());
        mob.setDamage(20);
        assertFalse(mob.isAlive());
    }

}
