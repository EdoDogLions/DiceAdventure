package weapons;

/**
 * @author edoardodoglioni This is the interface of the Weapon
 */
public interface WeaponInterface {

	public Integer rollDmg();

	public Integer rollHit();

	public Integer rollCrit();

	public Integer rollDsv();

	public Integer rollAdv();

	public String getWeaponName();

}
