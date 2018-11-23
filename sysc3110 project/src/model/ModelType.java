package model;

public enum ModelType {
	FAST_ZOMBIE, PEA_SHOOTER, SUN_FLOWER, NUT, CONEHEAD_ZOMBIE, RE_PEATER, Bomb;

	public String toString() {
		return "" + name().charAt(0);
	}
}
