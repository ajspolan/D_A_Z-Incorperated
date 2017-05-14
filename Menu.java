
import java.awt.event.*;
import javax.swing.*;

public class Menu{
	public String[] mText;
	public int mPos;
	public int mNum;
	public int[] mCommand;

	/*
	 * 0 = go to main menu
	 * 1 = go to char create screen
	 * 2 = open in game menu
	 * 3 = inventory screen
	 * 4 = character stats
	 * 5 = set mode = play
	 * 6 = quit game
	 * 7 = set char class mage
	 * 8 = set char class fighter
	 * 9 = set char class tank
	 * 10 = equip item
	 * 11 = unequip item
	 * 12 = equip screen
	 * 13 = fight screen
	 * 14 = attack phys
	 * 15 = attack magic
	 */
	public Menu(){
		mText = new String[1];
		mCommand = new int[1];
	}
	public Menu(int mNum){
		switch(mNum){
		case 0:
			mText = new String[2];
			mText[0] = "Start Your Adventure!";
			mText[1] = "Quit Game";
			mCommand = new int[2];
			mCommand[0] = 1;
			mCommand[1] = 6;
			break;
		case 1: 
			mText = new String[4];
			mCommand = new int[4];
			mText[0] = "Mage";
			mText[1] = "Fighter";
			mText[2] = "Tank";
			mText[3] = "Go Back";
			mCommand[0] = 7;
			mCommand[1] = 8;
			mCommand[2] = 9;
			mCommand[3] = 0;	
			break;
		case 2:
			mText = new String[4];
			mCommand = new int[4];
			mText[0] = "Inventory";
			mText[1] = "Character Stats";
			mText[2] = "Return To Game";
			mText[3] = "Quit Game";
			mCommand[0] = 3;
			mCommand[1] = 4;
			mCommand[2] = 5;
			mCommand[3] = 6;
			break;
		case 3:
			mText = new String[2];
			mCommand = new int[2];
			mText[0] = "Equip Item";
			mText[1] = "Go Back";
			mCommand[0] = 12;
			mCommand[1] = 2;
			break;
		case 4:
			mText = new String[1];
			mCommand = new int[1];
			mText[0] = " ";
			mCommand[0] = 2;
			break;
		case 13:
			mText = new String[2];
			mCommand = new int[2];
			mText[0] = "Physical Attack";
			mText[1] = "Magical Attack";
			mCommand[0] = 14;
			mCommand[1] = 15;
		}
		
			
			
			
	}
	public static void menuInteract(int cmd){
		if(cmd == 99){
			
		}
		else if(cmd < 5){
			Game.currMenu = cmd;
		}
		else if(cmd == 5){
			GameWindow.display = "play";
		}
		else if(cmd == 6){
			System.exit(0);
		}
		else if(cmd == 7 || cmd == 8 || cmd ==9){
			if(cmd == 7){
				Game.hero.setClassMage();
			}
			else if(cmd == 8){
				Game.hero.setClassFighter();
			}
			else if(cmd == 9){
				Game.hero.setClassTank();
			}
			Game.hero.levelUp();
			GameWindow.display = "play";
		}
		else if(cmd == 10){
				Game.hero.equipWpn(Game.hero.inventory[Game.currMenuOptionSelected]);
		}
		else if(cmd == 12){
			Game.currMenu =12;
		}
		else if(cmd == 14){
			Game.hero.physAttack(Game.hero.physAtt, Game.currEnemy);
			Game.currMess = 2;
			Game.eTurn = 1;
		}
		else if(cmd == 15){
			Game.hero.magAttack(Game.hero.magAtt, Game.currEnemy);
			Game.currMess = 3;
			Game.eTurn = 1;
		}

		
		Game.currMenuOptionSelected = 0;
	}


	public void moveUp(){
		mPos--;
	}
	public void moveDown(){
		mPos++;
	}
}
