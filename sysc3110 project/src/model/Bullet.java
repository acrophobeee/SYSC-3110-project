package model;

public class Bullet extends Model{
	
	private AbstractPlant plant;
	private int speed;
	
	public Bullet() {
		super();
	}
	
	public Bullet(int atk, int speed) {
		super(ModelType.BULLET,0,atk,0);
		this.speed = speed;
	}
	
	public void attack(AbstractZombie zombie) {
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
