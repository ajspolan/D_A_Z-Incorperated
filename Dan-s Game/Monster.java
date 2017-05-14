
import java.util.Random;

public class Monster extends Char {
	public Monster(String name, int clasNum) {
		super(name, clasNum);
		// TODO Auto-generated constructor stub
	}
	public int cr;
	private static Monster[][] types = new Monster[20][5];
	//cr = challenge rating aka the difficulty of a monster. The cr of all the monsters will add up to create
	//a cr equal to players level.	
	public Monster(String name){
		super(name);
	}
	public static void setWorldMonsters(){		
		for(int i = 0;i<20; i++){
			for(int x =0; x<5;x++){
				types[i][x]= new Monster((i+1) + "-" + (x+1));
				types[i][x].setHealthP(i+100);
				types[i][x].setIntel(i+10);
				types[i][x].setStren(i+10);
				types[i][x].setVital(i+10);
				types[i][x].magAtt = 1;
				types[i][x].physAtt = 1;
				types[i][x].level = types[i][x].cr;
				types[i][x].levelUp();
				types[i][x].currWpn = Item.makePhysWeapon("test", 2*x);
//these values will be changed individually later for balance purposes, but for now we just want to test concepts			
			}
		}
		{
		//I realized a little late that it would be easier to use a for loop t set the names, oh well
		//for now the first number in the name reflects the challenge rating, and the second number is one of the 5 monsters using that cr	
		/*types[0][0].setcName("1-1");
		types[0][1].setcName("1-2");
		types[0][2].setcName("1-3");
		types[0][3].setcName("a4");
		types[0][4].setcName("a5");
		types[1][0].setcName("b1");
		types[1][1].setcName("b2");
		types[1][2].setcName("b3");
		types[1][3].setcName("b4");
		types[1][4].setcName("b5");
		types[2][0].setcName("c1");
		types[2][1].setcName("c2");
		types[2][2].setcName("c3");
		types[2][3].setcName("c4");
		types[2][4].setcName("c5");
		types[3][0].setcName("d1");
		types[3][1].setcName("d2");
		types[3][2].setcName("d3");
		types[3][3].setcName("d4");
		types[3][4].setcName("d5");
		types[4][0].setcName("e1");
		types[4][1].setcName("");
		types[4][2].setcName("");
		types[4][3].setcName("");
		types[4][4].setcName("");
		types[5][0].setcName("");
		types[5][1].setcName("");
		types[5][3].setcName("");
		types[5][4].setcName("");
		types[6][0].setcName("");
		types[6][1].setcName("");
		types[6][2].setcName("");
		types[6][3].setcName("");
		types[6][4].setcName("");
		types[7][0].setcName("");
		types[7][1].setcName("");
		types[7][2].setcName("");
		types[7][3].setcName("");
		types[7][4].setcName("");
		types[8][0].setcName("");
		types[8][1].setcName("");
		types[8][2].setcName("");
		types[8][3].setcName("");
		types[8][4].setcName("");
		types[9][0].setcName("");
		types[9][1].setcName("");
		types[9][2].setcName("");
		types[9][3].setcName("");
		types[9][4].setcName("");
		types[10][0].setcName("");
		types[10][1].setcName("");
		types[10][2].setcName("");
		types[10][3].setcName("");
		types[10][4].setcName("");
		types[11][0].setcName("");
		types[11][1].setcName("");
		types[11][2].setcName("");
		types[11][3].setcName("");
		types[11][4].setcName("");
		types[12][0].setcName("");
		types[12][1].setcName("");
		types[12][2].setcName("");
		types[12][3].setcName("");
		types[12][4].setcName("");
		types[13][0].setcName("");
		types[13][1].setcName("");
		types[13][2].setcName("");
		types[13][3].setcName("");
		types[13][4].setcName("");
		types[14][0].setcName("");
		types[14][1].setcName("");
		types[14][2].setcName("");
		types[14][3].setcName("");
		types[14][4].setcName("");
		types[15][0].setcName("");
		types[15][1].setcName("");
		types[15][2].setcName("");
		types[15][3].setcName("");
		types[15][4].setcName("");
		types[16][0].setcName("");
		types[16][1].setcName("");
		types[16][2].setcName("");
		types[16][3].setcName("");
		types[16][4].setcName("");
		types[17][0].setcName("");
		types[17][1].setcName("");
		types[17][2].setcName("");
		types[17][3].setcName("");
		types[17][4].setcName("");
		types[18][0].setcName("");
		types[18][1].setcName("");
		types[18][2].setcName("");
		types[18][3].setcName("");
		types[18][4].setcName("");
		types[19][0].setcName("");
		types[19][1].setcName("");
		types[19][2].setcName("");
		types[19][3].setcName("");
		types[19][4].setcName("");*/	
		}
	}
	public static Monster generateEncounter(int parLvl){
		Random ranNum = new Random();
		int monsterNum = 1+ranNum.nextInt(6);
		Monster encounter = types[parLvl][ranNum.nextInt(4)];
		return encounter;
	}
}



