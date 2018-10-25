package model;

public abstract class AbstractPlant extends Model{

	private int cost;
	
	public AbstractPlant() {
		super();
	}

	public AbstractPlant(ModelType type, int cost, int hp, int atk, int cd) {
		super(type,hp,atk,cd);
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
