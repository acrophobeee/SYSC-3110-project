package model;

public abstract class AbstractZombie extends Model {

	private int speed;

	public AbstractZombie() {
		super();
	}

	public AbstractZombie(ModelType type, int hp, int atk, int speed, int cd) {
		super(type, hp, atk, cd);
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void attack(Model plant) {
		int tempHp = plant.getHp() - this.getAtk();
		plant.setHp(tempHp);
	}
}
