//TODO NEXT STEP IS TO WRITE INVENTORY SCREEN SPECIFIC CONTROLS ON THE KEYEVENT CLASS CMON BOIIIIII
import java.awt.*;
import javax.swing.*;
public class GameWindow extends JPanel{
	//private GraphicsDevice gd;
	public int gridMult = 41;
	public int charCoordX;
	public int charCoordY;
	public int screen;
	public int lastKeyPressed;
	public Char hero;
	public static String display;
	public Font selected = new Font("Arial", Font.BOLD, 26);
	public Font unselected = new Font("Arial", Font.PLAIN, 24);

	// 0 = right, 1 = left, 2 = down, 3 = up
	public void paintCurrentStats(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("Ariel", Font.PLAIN, 14));
		g.drawString("Name:" + Game.hero.getcName(), 50, 50);
		switch(Game.hero.charClass){
		case 1:
			g.drawString("Class: Mage", 50, 80);
			break;
		case 2:
			g.drawString("Class: Fighter", 50, 80);
			break;
		case 3:
			g.drawString("Class:  Tank", 50, 80);
			break;
		}
		g.drawString("Current Physical Attack: " + Game.hero.physAtt ,50, 110);
		g.drawString("Current Magical Attack: " + Game.hero.magAtt ,50, 140);
		g.drawString("Currently Equipped Weapon: " + Game.hero.currWpn.name, 50, 170);
		g.drawString("Attack:  " + Game.hero.currWpn.attVal, 80, 200);
		g.drawString("Magic Attack:  " + Game.hero.currWpn.magVal, 80, 230);
		g.drawString("Current Level: " + Game.hero.level, 50, 260);
		g.drawString("press z to return to game", 50, 290);

	}
	public void paintInventory(Graphics g){
		g.setFont(new Font("Arial", Font.BOLD, 24));
		g.setColor(Color.BLUE);
		int l = Game.hero.inventory.length;
		int drawLength = 0;
		for(int x = 0;x<l; x++){
			if(Game.hero.inventory[x] != null){
				drawLength++;
			}
		}
		for(int x = 0; x < Game.gameMenus[3].mText.length; x++){
			g.setColor(Color.BLUE);
			g.setFont(unselected);
			if(x == Game.currMenuOptionSelected){
				g.setFont(selected);
				g.setColor(Color.CYAN);
			}
			g.drawString(Game.gameMenus[3].mText[x], 1*42, (x+1)*41);
			g.setFont(new Font("Arial", Font.PLAIN, 24));
		}
		g.setColor(Color.BLUE);
		for(int x = 0; x < drawLength; x++){
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.drawString(Game.hero.inventory[x].name, 6*41, (1+x)*41);
		}
	}
	public void paintEncounter(Graphics g){
		this.setBackground(Color.WHITE);
		g.setColor(Color.RED);
		g.fillRect(4*gridMult, 5*gridMult, 40, 40);
		g.setColor(Color.BLACK);
		g.fillRect(6*gridMult, 5*gridMult, 40, 40);
		g.setColor(Color.BLACK);
		//g.drawString(Game.genMess(Game.currMess), 10, 7*gridMult);
		for(int x = 0; x < Game.gameMenus[Game.currMenu].mText.length; x++){
			g.setColor(Color.BLUE);
			g.setFont(unselected);
			if(x == Game.currMenuOptionSelected){
				g.setFont(selected);
				g.setColor(Color.CYAN);
			}
			g.drawString(Game.gameMenus[Game.currMenu].mText[x], 100, (x+1)*41);
			g.setFont(new Font("Arial", Font.PLAIN, 24));
			
		}
	}
	public void paintComponent(Graphics g){
		hero = Game.hero;
		screen = hero.currScreen;
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		System.out.println("testing");
		if(display == "fight"){
			paintEncounter(g);
		}
		else if(display == "menu"){
			if(display == "menu" && Game.currMenu == 3){
				paintInventory(g);
			}
			else if(display =="menu" && Game.currMenu == 4){
				paintCurrentStats(g);
			}
			else{
				g.setFont(unselected);
				for(int x = 0; x < Game.gameMenus[Game.currMenu].mText.length; x++){
					g.setColor(Color.BLUE);
					g.setFont(unselected);
					if(x == Game.currMenuOptionSelected){
						g.setFont(selected);
						g.setColor(Color.CYAN);
					}
					g.drawString(Game.gameMenus[Game.currMenu].mText[x], 100, (x+1)*41);
					g.setFont(new Font("Arial", Font.PLAIN, 24));
				}

			}
			System.out.println(Integer.toString(Game.currMenuOptionSelected));
			/*switch(menuNum){
			case 0:
				for(int x = 0; x < Game.gameMenus[menuNum].mText.length;x++){
					if(Game.gameMenus[menuNum].mText.length > m.mPos){
						g.setColor(Color.BLUE);
						if(m.mPos == x){
							g.setColor(Color.CYAN);
							g.setFont(new Font("Arial", Font.BOLD, 24));
						}

						g.drawString(Game.gameMenus[menuNum].mText[x], 2*gridMult, (1+x) * gridMult);
						g.drawString(Integer.toString(m.mPos),300, 300 );
					}
				}
				System.out.println(Integer.toString(Game.currMenuOptionSelected));

			}*/
		}
		else if(display == "play"){
			for(int x=0; x<11; x++){
				g.setColor(Game.game[screen][x][0].tyleColor);
				g.fillRect(x*gridMult, 0, 40, 40);
				g.setColor(Game.game[screen][x][10].tyleColor);
				g.fillRect(x*gridMult, 410, 40, 40);

			}
			for(int y=0; y<11; y++){
				g.setColor(Game.game[screen][0][y].tyleColor);
				g.fillRect(0, (gridMult*y), 40, 40);
				g.setColor(Game.game[screen][10][y].tyleColor);
				g.fillRect(410, gridMult*y, 40, 40);
			}
			for(int x =1;x<10;x++){
				for(int y =1;y<10;y++){
					g.setColor(Game.game[screen][x][y].tyleColor);
					g.fillRect(x*gridMult, y*gridMult, 40, 40);
				}
			}
			//for(int y=0; y<440; y++){
			//for(int x =0;x<440;x++){
			//g.setColor(Color.WHITE);
			//g.fillRect((gridMult *x), (gridMult*y), 1, 1);
			//}
			//}
			g.setColor(Color.RED);
			g.fillRect(charCoordX*gridMult, charCoordY*gridMult,40, 40);

			if(lastKeyPressed == 0){
				g.setColor(Color.WHITE);
				g.fillRect(charCoordX*gridMult + 30, charCoordY*gridMult + 18, 10, 2);
			}
			if(lastKeyPressed == 1){
				g.setColor(Color.WHITE);
				g.fillRect(charCoordX*gridMult, charCoordY*gridMult + 18, 10, 2);
			}
			if(lastKeyPressed == 3){
				g.setColor(Color.WHITE);
				g.fillRect(charCoordX*gridMult + 20, charCoordY*gridMult, 2, 10);
			}
			if(lastKeyPressed == 2){
				g.setColor(Color.WHITE);
				g.fillRect(charCoordX*gridMult + 20, charCoordY*gridMult + 30, 2, 10);
			}
		}
	}
	public void paintCharacter(Char hero, int screen, int x, int y){
		charCoordX = x;
		charCoordY = y;
		repaint();

	}

	/*public void setScreen(DisplayMode dm, JFrame window){
		window.setUndecorated(true);
		window.setResizable(false);
		if(dm != null && gd.isDisplayChangeSupported()){
			try{
				gd.setDisplayMode(dm);
			}catch(Exception e){}
		}
	}
	public Window getFullScreenWindow(){
		return gd.getFullScreenWindow();
	}
	public void closeWindow(){
		Window w = gd.getFullScreenWindow();
		if(w != null){
			w.dispose();
		}
		gd.setFullScreenWindow(null);

	}*/
}
