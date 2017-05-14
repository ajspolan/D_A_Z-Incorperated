
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Game extends JFrame implements KeyListener{
	public static GameTile[][][] game = new GameTile[10][11][11];
	public static GameWindow gw = new GameWindow();
	public static Menu[] gameMenus;
	public static int currMenu;
	public static Char hero;
	public static int currMenuOptionSelected;
	public static Monster currEnemy;
	public static int currMess;
	public static int eTurn;
	public static boolean attacked;
	public static void intializeGame(){
		Monster.setWorldMonsters();
		gameMenus = new Menu[15];
		hero = new Char("Hero", 0);
		gameMenus[0] = new Menu(0);
		gameMenus[1] = new Menu(1);
		gameMenus[2] = new Menu(2);
		gameMenus[3] = new Menu(3);
		gameMenus[4] = new Menu(4);
		gameMenus[13] = new Menu(13);
		currMenu = 0;
		for(int screenNum = 0; screenNum<9;screenNum++){
		for(int x = 0;x<11;x++){
			for(int y = 0;y<11;y++){
				game[screenNum][x][y] = new GameTile(1);
			}
		}
		for(int x=0; x<11; x++){
			game[screenNum][0][x] = new GameTile(0);
			game[screenNum][x][0] = new GameTile(0);
			game[screenNum][10][x] = new GameTile(0);
			game[screenNum][x][10] = new GameTile(0);

		}}
		GameTile door = GameTile.makeDoor(0);
		game[0][0][5] = door;
		GameTile door0t1 = GameTile.makeDoor(0);
		GameTile door1t0 = GameTile.makeDoor(1);
		GameTile door1t2 = GameTile.makeDoor(2);
		GameTile door1t3 = GameTile.makeDoor(3);
		GameTile door2t1 = GameTile.makeDoor(4);
		GameTile door3t1 = GameTile.makeDoor(5);
		game[0][0][5] = door0t1;
		game[1][10][5] = door1t0;
		game[1][5][10] = door1t2;
		game[1][5][0] = door1t3;
		game[2][5][0] = door2t1;
		game[3][5][10] = door3t1;
		game[2][9][9] =new GameTile(2);
		
		hero = new Char("Hero", 0);
		game[hero.currScreen][hero.coordX][hero.coordY] = new GameTile(1);
		game[hero.currScreen][hero.coordX][hero.coordY].hero = hero;
		Game.gameMenus[12] = new Menu();
		Game.gameMenus[12].mText[0] = hero.inventory[0].name;
		Game.gameMenus[12].mCommand[0] = 12;
		game[0][5][3] = GameTile.makeChest(new Chest(0, Item.makeMagWeapon("Uber Sword", 5000)));
		game[1][1][7] = GameTile.makeChest(new Chest(1, Item.makePhysWeapon("Clobber Stick", 4)));
		game[2][5][5] = GameTile.makeChest(new Chest(1, Item.makePhysWeapon("Kool Kids Klub", 15)));
		game[3][1][5] = GameTile.makeChest(new Chest(1, Item.makeMagWeapon("Staff Of Wonderment", 20)));
	}
	public static void main(String[] args){
		Game main = new Game();
		Game.intializeGame();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		hero.currScreen = 0;
		hero.coordX = 5;
		hero.coordY = 6;
		gw.paintCharacter(hero, hero.currScreen, 5, 6);
		main.add(gw);
		gw.display = "menu";


	}
	public Game(){
		addKeyListener(this);
		setSize(457, 480);
		setResizable(false);
		setVisible(true);

	}
	@Override
	public void keyTyped(KeyEvent e) {

	}
	@Override
	public void keyPressed(KeyEvent e) {
		int x = e.getKeyCode();
		if(x == e.VK_DOWN){
			if(gw.display == "play"){
				moveChar(1,"down", hero.coordX, hero.coordY);}
			else if(gw.display == "menu"){
				if(currMenuOptionSelected < Game.gameMenus[Game.currMenu].mText.length - 1){
					currMenuOptionSelected++;
				}
			}	
			else if(gw.display == "fight" && eTurn == 0){
				if(currMenuOptionSelected < Game.gameMenus[Game.currMenu].mText.length - 1){
					currMenuOptionSelected++;
				}
			}

		}
		else if(x == e.VK_UP){
			if(gw.display == "play"){
				moveChar(1,"up", hero.coordX, hero.coordY);}
			else if(gw.display == "menu"){
				if(currMenuOptionSelected > 0){
					currMenuOptionSelected--;
				}
			}
			else if(gw.display == "fight" && eTurn == 0){
				if(currMenuOptionSelected < Game.gameMenus[Game.currMenu].mText.length - 1){
					currMenuOptionSelected--;
				}
			}
		}
		else if(x == e.VK_LEFT){
			if(gw.display == "play"){
				moveChar(0,"left", hero.coordX, hero.coordY);
			}
		}
		else if(x == e.VK_RIGHT){
			if(gw.display == "play"){
				moveChar(0, "right", hero.coordX, hero.coordY);
			}
		}
		else if(x == e.VK_Z){
			if(gw.display == "play"){
				hero.interactFront(gw);}
			else if(gw.display == "menu"){
				Menu.menuInteract(gameMenus[currMenu].mCommand[currMenuOptionSelected]);
			}
			else if(gw.display == "fight" && eTurn == 0){
				Menu.menuInteract(gameMenus[currMenu].mCommand[currMenuOptionSelected]);
				currMess = 2;
				}
		}
		else if(x == e.VK_ENTER){
			if(gw.display == "play"){
				currMenu = 2;
				GameWindow.display = "menu";
			}
		}
		
		else if(x == e.VK_X){
			gw.display = "play";
		}
		e.consume();
		gw.repaint();
	}
	public void moveChar(int xy,String d, int x, int y){
		if(xy == 0 && d.equals("right")){
			if(game[hero.currScreen][hero.coordX+1][hero.coordY].occupant == null){
				gw.lastKeyPressed = 0;
				gw.paintCharacter(hero, hero.currScreen, x+1, y);
				hero.coordX++;
				game[hero.currScreen][hero.coordX][hero.coordY].hero = hero;
				game[hero.currScreen][x][y].hero = null;

			}
		}
		if(xy == 0&&d.equals("left")){
			if(game[hero.currScreen][hero.coordX-1][hero.coordY].occupant == null){
				gw.lastKeyPressed = 1;
				gw.paintCharacter(hero, hero.currScreen, x-1, y);
				hero.coordX--;
				game[hero.currScreen][hero.coordX][hero.coordY].hero = hero;
				game[hero.currScreen][x][y].hero = null;
			}
		}
		if(xy == 1&&d.equals("down")){
			if(game[hero.currScreen][hero.coordX][hero.coordY+1].occupant == null){
				gw.lastKeyPressed = 2;
				gw.paintCharacter(hero,hero.currScreen, x, y+1);
				hero.coordY++;
				game[hero.currScreen][hero.coordX][hero.coordY].hero = hero;
				game[hero.currScreen][x][y].hero = null;
			}
		}
		if(xy == 1&&d.equals("up")){
			if(game[hero.currScreen][hero.coordX][hero.coordY-1].occupant == null){
				gw.lastKeyPressed = 3;
				gw.paintCharacter(hero, hero.currScreen, x, y-1);
				hero.coordY--;
				game[hero.currScreen][hero.coordX][hero.coordY].hero = hero;
				game[hero.currScreen][x][y].hero = null;
			}
		}

		if(game[hero.currScreen][hero.coordX][hero.coordY].encChance > 0){
			Random r = new Random();
			if(game[hero.currScreen][hero.coordX][hero.coordY].encChance > r.nextInt(100)){
				System.out.println("MONSTER!");
				encounter();
				
			}


		}


	}
	public void encounter(){
		int x = 0;
		currEnemy = Monster.generateEncounter(hero.level);
		GameWindow.display = "fight";
		Game.currMenu = 13;
		//gw.repaint();
		currMess = 1;
		System.out.println("balls");
	}
	public void fight(){
		if(hero.getHealthP()>0 && currEnemy.getHealthP()>0){
			if(eTurn == 1){
				
			}
		}
	}
	public static String genMess(int x){
		System.out.println("testing2");
		String rtrn = "";
		switch(x){
		case 1:
			rtrn =  "A wild " + currEnemy.getcName() + " has Appeared!";
			break;
		case 2:
			rtrn = "Hero attacked for " + Double.toString(hero.physAttack(hero.physAtt, currEnemy));
			break;
		case 3:
			rtrn = "Hero attacked for " + Double.toString(hero.magAttack(hero.magAtt, currEnemy));
		}
		
		return rtrn;
	}
	public void mTurn(){

	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}	
}	
