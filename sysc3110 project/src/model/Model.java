package model;

public abstract class Model {

	private ModelType type; // name of plants/zombies
	private int hp; // heath point for plants / zombies
	private int atk; // attack damage
	private int cd; // waiting time for plants / zombies' next attack

	public Model() {
		super();
	}

	public Model(ModelType type, int hp, int atk, int cd) {
		super();
		this.type = type;
		this.hp = hp;
		this.atk = atk;
		this.cd = cd;
	}

	public ModelType getType() {
		return type;
	}

	public void setType(ModelType type) {
		this.type = type;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getCd() {
		return cd;
	}

	public void setCd(int cd) {
		this.cd = cd;
	}

}
