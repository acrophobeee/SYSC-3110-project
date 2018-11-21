package game;

import java.util.Arrays;
import java.util.Random;
import java.io.Serializable;

import model.*;

public class Grid implements Serializable {

	final private int gridHeight = 5;
	final private int gridLength = 10;
	private Model[][] map;

	public Grid() {
		map = new Model[this.gridHeight][this.gridLength];
	}

	public void clearMap() {
		for (int i = 0; i < this.getHeight(); i++) {
			for (int j = 0; j < this.getLength(); j++) {
				map[i][j] = null;
			}
		}
	}

	public int getHeight() {
		return gridHeight;
	}

	public int getLength() {
		return gridLength;
	}

	public void addModel(Model model, int i, int j) {
		map[i][j] = model;
	}

	public void removeModel(Model model, int i, int j) {
		map[i][j] = null;
	}

	public Model getModel(int i, int j) {
		return this.map[i][j];
	}

	public void shiftModel(Model model, int i, int j) {
		if (model instanceof AbstractZombie) {
			removeModel(model, i, j);
			if (j - ((AbstractZombie) model).getSpeed() > 0) {
				j = j - ((AbstractZombie) model).getSpeed();
				addModel(model, i, j);
			} else {
				addModel(model, i, 0);
			}
		}
	}

	@Override
	public String toString() {
		String grid = "";
		for (int row = 0; row < getHeight(); row++) {
			grid += "[";
			for (int col = 0; col < getLength(); col++) {
				if (map[row][col] == null) {
					grid += " ";
				} else {
					grid += map[row][col].getType();
				}
				if (col < getLength() - 1) {
					grid += ",";
				}
			}
			grid += "]\n";
		}
		return grid;
	}
}
