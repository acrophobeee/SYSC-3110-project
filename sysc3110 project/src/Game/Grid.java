package Game;

import java.util.Random;
import Model.*;

public class Grid {

	private model[][] map;

	public Grid() {
		map = new model[5][10];
	}

	public void clearMap() {
		for (int i =0; i<5; i++) {
			for (int j = 0; j<10; j++) {
				map[i][j] = null;
			}
		}
	}

	public void addModel(model model,int x, int y) {
		map[x][y] = model;
	}

	public void removeModel(model model, int x, int y) {
		map[x][y] = null;
	}

	public void spawnZombie() {
		int r = (new Random()).nextInt(4)+1;
		fastZombies fz = new fastZombies();
		addModel(fz, 9, r);
	}

	public boolean isGameOver() {
		for (int i =0 ; i<5; i++) {
			if (map[i][0] instanceof abstractZombies) {
				return true;
			}
		}
		return false;
	}

	public void updateGrid() {
		if (!isGameOver()) {
			for(int i = 0; i<5; i++) {
				for (int j = 0; j<10; j++) {
					model model = map[i][j];
					if (model instanceof sunFlower) {
						sunFlower s = (sunFlower)model;
						s.generateSun();
					}
				}
			}
		}
	}
}
