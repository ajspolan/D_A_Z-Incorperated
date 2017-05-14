
public class Chest {
	public Item contained;
	public int chestNum;
	public Chest(int x, Item y){
		this.chestNum = x;
		this.contained = y;
	}
	public void takeItem(Char c){
		c.addToInv(contained);
		contained = null;
		System.out.println("It Worked!");
	}
	
}
