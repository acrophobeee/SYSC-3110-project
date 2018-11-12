package model;

public class FastZombie extends AbstractZombie {

	public FastZombie() {
		// name,hp,atk,speed,cd
		super(ModelType.FAST_ZOMBIE, 100, 10, 1, 2);

	}

	public void attack(Model plant) {
		int tempHp = plant.getHp() - this.getAtk();
		plant.setHp(tempHp);
	}
}
