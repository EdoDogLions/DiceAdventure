package weapons;

public class GenericWeaponFactory implements WeaponsFactory{

	@Override
	public Weapons createWeapon(Integer value) {
		// TODO Auto-generated method stub
		return new GenericWeapon(value);
	}

	@Override
	public Weapons createWeapon() {
		// TODO Auto-generated method stub
		return new Sword(); 	//Senza campi crea una spada
	}

}
