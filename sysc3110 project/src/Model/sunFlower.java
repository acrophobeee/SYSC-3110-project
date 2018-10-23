package Model;
public class sunFlower extends abstractPlants{
	
	private int sp;//sun points

	public sunFlower() {
		//name,cost,hp,damage,cd
		super("sun flower", 3, 50, 0, 3);
		// TODO Auto-generated constructor stub
	}
	
	public int generate() {
		int i = this.getCd();
		if (i == 3) {
			sp ++;
			i=0;
		}
		i++;
		return sp;
	}
}
