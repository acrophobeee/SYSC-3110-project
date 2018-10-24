package model;

public abstract class AbstractZombies extends Model{

	private int speed;
	
	public AbstractZombies() {
		super();
	}

	public AbstractZombies(String name, int hp, int atk, int speed, int cd) {
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
