
public class peanShooter extends abstractPlants{

	public peanShooter() {
		//name, cost, hp, atk, cd
		super("pean shooter",5,100,25,2);
		// TODO Auto-generated constructor stub
	}
	
	public bullet shoot() {
		bullet bullet = null;
		int i = this.getCd();
		if (i == 2) {
			i = 0;
			bullet = new bullet(this.getAtk(),1);
		}
		i++;
		return bullet;
	}
}
