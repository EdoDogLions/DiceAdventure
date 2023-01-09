package weapons;

public interface WeaponsFactory {

	Weapons createWeapon();

	Weapons createWeapon(Integer value); //For generic Weapons Only
}
