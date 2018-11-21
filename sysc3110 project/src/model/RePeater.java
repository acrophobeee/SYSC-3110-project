package model;

public class RePeater extends AbstractPlant{

	public RePeater() {
		// name, cost, hp, atk, cd
		super(ModelType.RE_PEATER, 150, 100, 50, 2);
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
