
import java.awt.Dimension;
import java.util.Scanner;

import javax.swing.JFrame;
public class Tester {

	public static void main(String[] args) {	
		Monster rawr = new Monster("startingpoint");
		rawr.setWorldMonsters();
		Char hero = new Char("Hero", 1);
		hero.level = 4;
		hero.levelUp();
		Char sidekick = new Char("Sidekick", 2);
		sidekick.level = 3;
		sidekick.levelUp();
		Monster testFight = rawr.generateEncounter(hero.level+sidekick.level);
		
		System.out.println("Monster" + testFight.getcName() + " \nHP: "+ testFight.getHealthP() + "\n");
		//eventually I will modify the character toString method to do this automatically, but for the sake of seeing the monsters cr level, i wanted to print out the index of the monster
		//to see the generate encounter method in action. 
		//It works well, if you run multiple times and mess with character level values, it can produce a random encounter that is sufficiently difficult
		System.out.println(hero.toString());
		hero.magAttack(hero.magAtt, testFight);
		System.out.println("Monster 1" + " index " +testFight.toString());
		sidekick.physAttack(sidekick.physAtt, testFight);
		System.out.println("Monster " + testFight.toString());
		System.out.println(hero.getcName() + "\nHP: " +hero.getHealthP());
		testFight.physAttack(testFight.physAtt, hero);
		System.out.println(hero.getcName() + "\nHP: " +hero.getHealthP());
		testFight.physAttack(testFight.physAtt, hero);
		System.out.println(hero.toString());
	}
	

}
