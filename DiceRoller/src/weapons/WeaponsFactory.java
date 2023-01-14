package weapons;

public interface WeaponsFactory {

	WeaponInterface createWeapon();

	WeaponInterface createWeapon(Integer value); //For generic Weapons Only
}
