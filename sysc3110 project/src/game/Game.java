package game;

// produce by weihongshen, xinyuchen, jacky chiu, kirin
import java.lang.Integer;
import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.*;

public class Game {

	private Grid board;
	private int sp; // sun points.

	public Game() {
		this.board = new Grid();
		this.sp = 200;
	}

	public void gameLoop() {
		spawnZombie();
		while (true) {
			// Check if zombies have hit
			if (this.isGameOver()) {
				System.out.println("Game is Over");
				return;
			}

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 10; j++) {
					Model model = this.board.getModel(i, j);

					if (model instanceof SunFlower) {
						sunFlowerAction((SunFlower) model);
					} else if (model instanceof PeaShooter) {
						peaShooterAction((PeaShooter) model, i, j);

					} else if (model instanceof Bullet) {
						bulletAction((Bullet) model, i, j);
						System.out.println("bullet[" + i + "," + j + "]");

					} else if (model instanceof AbstractZombie) {
						if (model.getHp() <= 0) {
							this.board.removeModel(model, i, j);
							System.out.println("zombie died, new zombie coming");
							spawnZombie();
						} else {
							zombieAction((FastZombie) model, i, j);
							System.out.println("zombie at [" + i + "," + j + "], Hp = " + model.getHp());
						}
					}
					if (model instanceof SunFlower) {
						System.out.println("SunFlower" + "[" + i + "," + j + "], Hp = " + model.getHp());
					}
					if (model instanceof PeaShooter) {
						System.out.println("PeaShooter" + "[" + i + "," + j + "], Hp = " + model.getHp());
					}
				}

			}

			System.out.println("Current sun points: " + this.sp);
			System.out.println("");
			System.out.println(board);
			userAction();
		}
	}

	private void sunFlowerAction(SunFlower s) {
		this.sp += s.generateSun();
	}

	private void peaShooterAction(PeaShooter s, int i, int j) {
		if (s.getHp() < 0) {
			this.board.removeModel(s, i, j);
		} else {
			for (int k = j; k < this.board.getLength(); k++) {
				// Damage the first zombie it finds
				if (this.board.getModel(i, k) instanceof AbstractZombie && this.board.getModel(i, j + 1) == null) {
					this.board.addModel(s.shoot(), i, j + 1);
				}
				if (this.board.getModel(i, j + 1) instanceof AbstractZombie) {
					s.attack(this.board.getModel(i, j + 1));
				}
			}
		}
	}

	private void bulletAction(Bullet b, int i, int j) {
		if (this.board.getModel(i, j + 1) instanceof AbstractZombie) {
			b.attack((AbstractZombie) this.board.getModel(i, j + 1));
			System.out.println("bullet attack zombie");
			this.board.removeModel(b, i, j);
		} else {
			this.board.shiftModel(b, i, j);
		}
	}

	private void zombieAction(FastZombie z, int i, int j) {
		if (z.getHp() < 0) {
			this.board.removeModel(z, i, j);
		} else {
			if (this.board.getModel(i, j - 1) instanceof AbstractPlant) {
				z.attack(this.board.getModel(i, j - 1));
			} else if (this.board.getModel(i, j - 1) == null) {
				this.board.shiftModel(z, i, j);
			}
		}
	}

	public int getSp() {
		return sp;
	}

	private void spawnZombie() {
		int r = (new Random()).nextInt(4) + 1;
		FastZombie fz = new FastZombie();
		this.board.addModel(fz, r, 9);
	}

	private boolean isGameOver() {
		for (int i = 0; i < this.board.getHeight(); i++) {
			if (this.board.getModel(i, 0) instanceof AbstractZombie) {
				return true;
			}
		}
		return false;
	}

	private void userAction() {
		if (this.sp < 50) {
			System.out.println("Not enough sun points, skipping turn");
			return;
		}

	public static void main(String[] args) {
		Game g = new Game();
		View v = new View();
		g.gameLoop();
	}
}
