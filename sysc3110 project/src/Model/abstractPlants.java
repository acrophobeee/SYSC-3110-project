package Model;

public abstract class abstractPlants extends Model{

	private int cost;
	
	public abstractPlants() {
		super();
	}

	public abstractPlants(String name, int cost, int hp, int atk, int cd) {
		super(name,hp,atk,cd);
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
