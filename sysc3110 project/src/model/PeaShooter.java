package model;

public class PeaShooter extends AbstractPlant {

	public PeaShooter() {
		// name, cost, hp, atk, cd
		super(ModelType.PEA_SHOOTER, 100, 100, 50, 2);
		// TODO Auto-generated constructor stub
	}

	public Bullet shoot() {
		Bullet bullet = null;
		int i = this.getCd();
		if (i == 2) {
			i = 0;
			bullet = new Bullet(this.getAtk(), 1);
		}
		i++;
		return bullet;
	}

	public void attack(Model zombie) {
		int i = this.getCd();
		if (i == 2) {
			int tempHp = zombie.getHp() - this.getAtk();
			i = 0;
			zombie.setHp(tempHp);
		}
		i++;
	}
}
