package model;

public class SunFlower extends AbstractPlant {

	private int sp = 0;

	public SunFlower() {
		// name,cost,hp,damage,cd
		super(ModelType.SUN_FLOWER, 50, 50, 0, 3);
		this.sp = sp;
	}

	public int generateSun() {
		return sp = 25;
	}

	public int getSp() {
		return sp;
	}
}
