
public class peanShooter extends abstractPlants{

	public peanShooter() {
		//name, cost, hp, atk, cd
		super("pean shooter",5,100,25,2);
		// TODO Auto-generated constructor stub
	}

	public int attack(model zombie) {
		int i = this.getCd();
		if (i == 2) {
			int tempHp = zombie.getHp() - this.getAtk();
			i = 0;
			zombie.setHp(tempHp);
		}
		i++;
		return zombie.getHp();
	}
}
