package model;

public abstract class Model {

	private String name; // name of plants/zombies
	private int hp; // heath point for plants / zombies
	private int atk; // attack damage
	private int cd; // waiting time for plants / zombies' next attack

	public Model() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Model(String name, int hp, int atk, int cd) {
		super();
		this.name = name;
		this.hp = hp;
		this.atk = atk;
		this.cd = cd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
