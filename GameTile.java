

import java.awt.*;

public class GameTile {
	public PhysObj occupant;
	public int encChance;
	public Color tyleColor;
	public Char hero;
	public Object obj;
	public String type;
	public GameTile(int x){
		if(x == 0){
			occupant = new PhysObj();
			type = "wall";
			tyleColor = Color.GRAY;
		}
		if(x == 1){
			encChance = 0;
			type = "monsterTile";
			tyleColor = Color.GREEN;
		}
		if(x == 2){
			encChance = 90;
			type = "monsterTile";
			tyleColor = Color.WHITE;
		}
	}
	public GameTile(){
		occupant = new PhysObj();
	}
	public static GameTile makeDoor(int x){
		GameTile rtrn = new GameTile();
		rtrn.obj = new Door(x);
		rtrn.type = "door";
		rtrn.tyleColor = Color.CYAN;
		return rtrn;
	}
	public static GameTile makeChest(Chest contained){
		GameTile rtrn = new GameTile();
		rtrn.type = "chest";
		rtrn.obj = contained;
		rtrn.tyleColor =Color.ORANGE;
		return rtrn;
	}
	
	
}
