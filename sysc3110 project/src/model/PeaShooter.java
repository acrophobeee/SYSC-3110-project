package model;

public class PeaShooter extends AbstractPlant {

	public PeaShooter() {
		// name, cost, hp, atk, cd
		super(ModelType.PEA_SHOOTER, 100, 100, 25, 2);
	}

	public void attack(Model zombie) {
		int i = this.getCd();
		if (i == 2) {
			int tempHp = zombie.getHp() - this.getAtk();
			i = 0;
			zombie.setHp(tempHp);
		}
		this.setCd(i + 1);
	}
}
