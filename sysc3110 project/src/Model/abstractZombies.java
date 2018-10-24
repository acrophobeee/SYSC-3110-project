package Model;

public abstract class abstractZombies extends Model{

	private int speed;
	
	public abstractZombies() {
		super();
	}

	public abstractZombies(String name, int hp, int atk, int speed, int cd) {
		super(name,hp,atk, cd);
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
