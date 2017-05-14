

public class Door {
	public int code;
	public String status;
	public Door(int doorCode){
		status = "open";
		code=doorCode ;
	}
	public void useDoor(Char user){
		switch(code){
		case 0:
			user.currScreen = 1;
			user.coordX = 9;
			user.coordY = 5;
			break;
		case 1:
			user.currScreen = 0;
			user.coordX = 1;
			user.coordY = 5;
			break;
		case 2:
			user.currScreen = 2;
			user.coordX = 5;
			user.coordY = 1;
			break;
		case 3:
			user.currScreen = 3;
			user.coordX = 5;
			user.coordY = 9;
			break;
		case 4:
			user.currScreen = 1;
			user.coordX = 5;
			user.coordY = 9;
			break;
		case 5:
			user.currScreen = 1;
			user.coordX = 5;
			user.coordY = 1;
			break;
		}
	}
}
