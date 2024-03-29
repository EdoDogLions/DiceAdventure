package fightStyles;

/**
 * @author edoardodoglioni Interface for FightStyle
 */

public interface FightStyleInterface {

	public void useWeapon(Integer armorClass);

	public Integer getLastRollHit();

	public Integer getLastDmgHit();

	public void setAdvantage();

	public void setDisadvantage();

	public void resetAdDisvantage();
}
