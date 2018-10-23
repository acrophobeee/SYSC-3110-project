package Model;

public class bullet extends model{
	
	private abstractPlants plant;
	private int speed;
	
	public bullet() {
		super();
	}
	
	public bullet(int atk, int speed) {
		super("bullet",0,atk,0);
		this.speed = speed;
	}
	
	public void attack(model zombie) {
		int tempHp = zombie.getHp() - plant.getAtk();
		zombie.setHp(tempHp);
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
