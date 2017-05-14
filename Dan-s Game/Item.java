

public class Item {
	public String name;
	public int worth;
	public String code;
	public int attVal;
	public int magVal;
	public boolean usable;
	public Item(){
		name = "";
		worth = 0;
		code = "";
		attVal = 0;
		magVal = 0;
		usable = false;
	}
	public static Item makeKey(String doorMatch){
		Item key = new Item();
		key.name = doorMatch + "Key";
		key.worth = 0;
		key.code = doorMatch;
		key.usable = false;
		return key;
	}
	public static Item makePhysWeapon(String name, int attack){
		Item wpn = new Item();
		wpn.worth = 10 * attack;
		wpn.name = name;
		wpn.attVal = attack;
		wpn.usable = true;
		return wpn;
	}
	public static Item makeMagWeapon(String name, int attack){
		Item wpn = new Item();
		wpn.worth = 10 * attack;
		wpn.name = name;
		wpn.magVal = attack;
		wpn.usable = true;
		return wpn;
	}
}
