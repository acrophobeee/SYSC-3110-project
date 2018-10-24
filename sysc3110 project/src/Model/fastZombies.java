package Model;

public class fastZombies extends abstractZombies{

	public fastZombies() {
		//name,hp,atk,speed,cd
		super("fast zombie",100,10,2,2);
	}
	
	public void attack(Model plant) {
		int i = this.getCd();
		if (i == 2) {
			int tempHp = plant.getHp() - this.getAtk();
			i = 0;
			plant.setHp(tempHp);
		}
		i++;
	}
}
