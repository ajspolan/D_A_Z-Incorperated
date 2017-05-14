
public class Char {
	private double intel;
	private double stren;
	private double vital;
	public double maxHP;
	private double healthP;
	private String cName;
	public int level;
	public int charClass;
	public double magAtt;
	public double physAtt;
	public String status;
	public Item currWpn;
	public int currScreen;
	public int coordX;
	public int coordY;
	public Item[] inventory;
	public int itemsInInv;
	public void addToInv(Item i){
		for(int x = 0; x <this.inventory.length; x++){
			if(inventory[x] == null){
				inventory[x] = i;
				itemsInInv++;
				break;
			}
		}
		Menu store = Game.gameMenus[12];
		Game.gameMenus[12] = new Menu();
		Game.gameMenus[12].mText = new String[store.mText.length+1];
		Game.gameMenus[12].mCommand = new int[store.mText.length+1];
		for(int x =0;x<store.mText.length;x++){
			Game.gameMenus[12].mText[x] = store.mText[x];
			Game.gameMenus[12].mCommand[x] = store.mCommand[x];
		}
		Game.gameMenus[12].mText[Game.gameMenus[12].mText.length - 1] = i.name;
		Game.gameMenus[12].mCommand[Game.gameMenus[12].mText.length - 1] = 10;
		
	}
	public void removeFromInv(Item i){
		for(int y = 0;y<this.inventory.length; y++){
			if(i == inventory[y]){
				inventory[y] = null;
				Game.gameMenus[12].mText[y] = null;
				}
			}
		for(int x = 1; x < inventory.length-1; x++){
			if(inventory[x] == null){
				inventory[x] = inventory[x + 1];
				inventory[x + 1] = null;
			}
		}
		for(int x = 1; x < Game.gameMenus[12].mText.length-1; x++){
			if(Game.gameMenus[12].mText[x] == null){
				Game.gameMenus[12].mText[x] = Game.gameMenus[12].mText[x + 1];
				Game.gameMenus[12].mText[x + 1] = null;
			}
		}
		int hold[] = new int[Game.gameMenus[12].mCommand.length - 1];
		String holdS[] = new String[Game.gameMenus[12].mText.length - 1];
		for(int x = 0; x < holdS.length; x++){
			holdS[x] = Game.gameMenus[12].mText[x];
			hold[x] = 10;
		}
		Game.gameMenus[12].mText = holdS;
		Game.gameMenus[12].mCommand = hold;
	}
	public void interact(GameTile target){
		if(target.type == "door"){
			((Door) target.obj).useDoor(this);
			System.out.println("kinda");
		}
		else if(target.type == "chest"){
			if(((Chest)target.obj).contained!= null){
			((Chest) target.obj).takeItem(this);
			System.out.println("kinda");
			}
		}
	}
	public void interactFront(GameWindow gw){
		if(gw.lastKeyPressed == 0){
			interact(Game.game[currScreen][coordX+1][coordY]);
		}
		else if(gw.lastKeyPressed == 1){
			interact(Game.game[currScreen][coordX-1][coordY]);
		}
		else if(gw.lastKeyPressed == 2){
			interact(Game.game[currScreen][coordX][coordY+1]);
		}
		else if(gw.lastKeyPressed == 3){
			interact(Game.game[currScreen][coordX][coordY-1]);
		}
		gw.paintCharacter(this, currScreen, coordX, coordY);
	}
	public void equipWpn(Item wpn){
		Item stored = currWpn;
		Item stored2 = wpn;
		currWpn = stored2;
		removeFromInv(wpn);
		addToInv(stored);
		Game.currMenu = 3;
		System.out.println("you");
	}
	public Item getcurrWpn(){
		return this.currWpn;
	}
	@Override
	public String toString(){
		return String.format(
				this.cName +"\n\nHP: " + this.getHealthP() + "\nCurrent Level: " + this.level + "\nCurrent Physical Attack: " + this.physAtt + "\n"
						+ "Current Magical Attack: " + this.magAtt + "\n"
				);	
	}
	public double getIntel() {
		return intel;
	}
	public void setIntel(int intel) {
		this.intel = intel;
	}
	public double getStren() {
		return stren;
	}
	public void setStren(int stren) {
		this.stren = stren;
	}
	public double getVital() {
		return vital;
	}
	public void setVital(int vital) {
		this.vital = vital;
	}
	public double getHealthP() {
		return healthP;
	}
	public void setHealthP(double healthP) {
		this.healthP = healthP;
	}
	public void setcName(String cName){
		this.cName = cName;
	}
	public String getcName(){
		return this.cName;
	}
	public double physAttack(double val,Char target){
		if(currWpn.attVal == 0){
			double att = val * (1 + (stren/100));
			target.healthP -= att;
			return att;
		}
		else{
			double att = (currWpn.attVal + val) * (1 + (stren/100));
			target.healthP -= att;
			return att;
		}

	}
	public double magAttack(double val, Char target){
		if(currWpn.magVal ==0){
			double att = val * (1 + (intel/100));
			target.healthP -= att;
			return att;
		}
		else{
			double att = (currWpn.magVal + val) * (1 + (intel/100));
			target.healthP -= att;
			return att;
		}
	}
	public Char(String name, int clasNum){
		this.cName = name;
		this.level = 1;
		this.status = "alive";
		currScreen = 0;
		coordX = 5;
		coordY = 6;
		inventory = new Item[20];
		if(clasNum == 0){
			this.inventory[0] = Item.makeMagWeapon("Ignore Me", 100000);
		}
		else if(clasNum==1){
			this.setClassMage();
		}
		else if(clasNum==2){
			this.setClassFighter();
		}
		else if(clasNum==3){
			this.setClassTank();
		}
		
	}
	public Char(String name) {
		// TODO Auto-generated constructor stub
		this.cName = name;
	}
	public void setClassMage(){
		this.maxHP = 80;
		this.healthP = 80;
		this.intel = 25;
		this.stren = 10;
		this.vital = 10;
		this.charClass = 1;
		this.magAtt = 1;
		this.physAtt = 1;
		this.currWpn = Item.makeMagWeapon("Loser Wand", 15);
	}
	public void setClassFighter(){
		this.maxHP = 150;
		this.healthP = 150;
		this.intel = 10;
		this.stren = 25;
		this.vital = 15;
		this.charClass = 2;
		this.magAtt = 1;
		this.physAtt = 1;
		this.currWpn = Item.makePhysWeapon("Loser Sword", 15);
	}
	public void setClassTank(){
		this.maxHP = 175;
		this.healthP = 175;
		this.intel = 10;
		this.stren = 10;
		this.vital = 30;
		this.charClass = 3;
		this.magAtt = 1;
		this.physAtt = 1;
		this.currWpn = Item.makePhysWeapon("Kool Kid Klub", 13);
	}
	public void levelUp(){
		int lvl = this.level;
		int ifState = this.charClass;
		//MAGE
		if(ifState == 1){
			switch(lvl){
			case 1: 
			magAtt = 2;
			physAtt = 1;
			break;
			case 2: 
			magAtt = 4;	
			break;
			case 3: 
			magAtt = 6;
			physAtt = 3;
			break;
			case 4: 
			magAtt = 20;
			break;
			case 5: 
			magAtt = 10;
			physAtt = 5;
			break;
			case 6: 
			magAtt = 12;
			break;
			case 7: 
			magAtt = 16;
			physAtt = 7;
			break;
			case 8: 
			magAtt = 18;
			
			break;
			case 9: 
			magAtt = 20;
			physAtt = 9;
			break;
			case 10: 
			magAtt = 22;
			
			break;
			case 11: 
			magAtt = 24;
			physAtt = 11;
			break;
			case 12: 
			magAtt = 26;
			
			break;
			case 13: 
			magAtt = 28;
			physAtt = 13;
			break;
			case 14: 
			magAtt = 30;
			break;
			case 15: 
			magAtt = 32;
			physAtt = 15;
			break;
			case 16: 
			magAtt = 34;
			
			break;
			case 17: 
			magAtt = 36;
			physAtt = 17;
			break;
			case 18: 
			magAtt = 38;
			
			break;
			case 19: 
			magAtt = 40;
			physAtt = 19;
			break;
			case 20: 
			magAtt = 42;
			
			break;
			}
		}
		//fighter
		else if(ifState==2){
			switch(lvl){
			case 1: 
			magAtt = 1;
			physAtt = 2;
			break;
			case 2: 
			
			physAtt = 4;
			break;
			case 3: 
			magAtt = 3;
			physAtt = 20;
			break;
			case 4: 
			
			physAtt = 8;
			break;
			case 5: 
			magAtt = 5;
			physAtt = 10;
			break;
			case 6: 
			
			physAtt = 12;
			break;
			case 7: 
			magAtt = 7;
			physAtt = 14;
			break;
			case 8: 
			
			physAtt = 16;
			break;
			case 9: 
			magAtt = 9;
			physAtt = 18;
			break;
			case 10: 
			
			physAtt = 20;
			break;
			case 11: 
			magAtt = 11;
			physAtt = 22;
			break;
			case 12: 
			
			physAtt = 24;
			break;
			case 13: 
			magAtt = 13;
			physAtt = 26;
			break;
			case 14: 
			
			physAtt = 28;
			break;
			case 15: 
			magAtt = 15;
			physAtt = 30;
			break;
			case 16: 
			
			physAtt = 32;
			break;
			case 17: 
			magAtt = 17;
			physAtt = 34;
			break;
			case 18: 
			
			physAtt = 36;
			break;
			case 19: 
			magAtt = 19;
			physAtt = 38;
			break;
			case 20: 
			magAtt = 20;
			physAtt = 40;
			break;
			}
	}
		//tank
		else if(ifState == 3){
			switch(lvl){
			case 1: 
			magAtt = 2;
			physAtt = 1;
			break;		
			case 2: 
			magAtt = 4;
			physAtt = 2;
			break;
			case 3: 
			magAtt = 6;
			physAtt = 3;
			break;
			case 4: 
			magAtt = 8;
			physAtt = 4;
			break;
			case 5: 
			magAtt = 10;
			physAtt = 5;
			break;
			case 6: 
			magAtt = 12;
			physAtt = 6;
			break;
			case 7: 
			magAtt = 16;
			physAtt = 7;
			break;
			case 8: 
			magAtt = 18;
			physAtt = 8;
			break;
			case 9: 
			magAtt = 20;
			physAtt = 9;
			break;
			case 10: 
			magAtt = 22;
			physAtt = 10;
			break;
			case 11: 
			magAtt = 24;
			physAtt = 11;
			break;
			case 12: 
			magAtt = 26;
			physAtt = 12;
			break;
			case 13: 
			magAtt = 28;
			physAtt = 13;
			break;
			case 14: 
			magAtt = 30;
			physAtt = 14;
			break;
			case 15: 
			magAtt = 32;
			physAtt = 15;
			break;
			case 16: 
			magAtt = 34;
			physAtt = 16;
			break;
			case 17: 
			magAtt = 36;
			physAtt = 17;
			break;
			case 18: 
			magAtt = 38;
			physAtt = 18;
			break;
			case 19: 
			magAtt = 40;
			physAtt = 19;
			break;
			case 20: 
			magAtt= 22;
			physAtt = 20;
			break;
			}
		}
		else{switch(lvl){
		case 1: 
		magAtt = 1;
		physAtt = 2;
		break;
		case 2: 
		magAtt = 2;
		physAtt = 4;
		break;
		case 3: 
		magAtt = 3;
		physAtt = 6;
		break;
		case 4: 
		magAtt = 4;
		physAtt = 8;
		break;
		case 5: 
		magAtt = 5;
		physAtt = 10;
		break;
		case 6: 
		magAtt = 6;
		physAtt = 12;
		break;
		case 7: 
		magAtt = 7;
		physAtt = 14;
		break;
		case 8: 
		magAtt = 8;
		physAtt = 16;
		break;
		case 9: 
		magAtt = 9;
		physAtt = 18;
		break;
		case 10: 
		magAtt = 10;
		physAtt = 20;
		break;
		case 11: 
		magAtt = 11;
		physAtt = 22;
		break;
		case 12: 
		magAtt = 12;
		physAtt = 24;
		break;
		case 13: 
		magAtt = 13;
		physAtt = 26;
		break;
		case 14: 
		magAtt = 14;
		physAtt = 28;
		break;
		case 15: 
		magAtt = 15;
		physAtt = 30;
		break;
		case 16: 
		magAtt = 16;
		physAtt = 32;
		break;
		case 17: 
		magAtt = 17;
		physAtt = 34;
		break;
		case 18: 
		magAtt = 18;
		physAtt = 36;
		break;
		case 19: 
		magAtt= 19;
		physAtt = 38;
		break;
		case 20: 
		magAtt = 20;
		physAtt = 40;
		break;
		}}
	}
}

	
	



