package model;

public class SunFlower extends AbstractPlant {

	private int sp = 0;

	public SunFlower() {
		//name,cost,hp,damage,cd
		super(ModelType.SUN_FLOWER, 50, 50, 0, 3);
		this.sp = sp;
		// TODO Auto-generated constructor stub
	}

	public boolean generate() {
		int i = this.getCd();
		if (i == 3) {
			i = 0;
			return true;
		}
		i++;
		return false;
	}

	public int generateSun() {
		if (generate()) {
			sp += 25;
		}
		return sp;
	}

	public int getSp() {
		return sp;
	}
}
