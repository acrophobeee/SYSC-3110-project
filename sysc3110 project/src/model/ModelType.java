package model;

public enum ModelType {
	FAST_ZOMBIE, PEA_SHOOTER, SUN_FLOWER;

	public String toString() {
		return "" + name().charAt(0);
	}
}
