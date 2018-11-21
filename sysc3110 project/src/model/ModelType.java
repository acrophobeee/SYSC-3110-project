package model;

public enum ModelType {
	FAST_ZOMBIE, PEA_SHOOTER, SUN_FLOWER, NUT, CONEHEAD_ZOMBIE, RE_PEATER;

	public String toString() {
		return "" + name().charAt(0);
	}
}
