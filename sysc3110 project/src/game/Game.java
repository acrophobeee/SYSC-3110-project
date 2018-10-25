package game;

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
					} 
					else if (model instanceof PeaShooter) {
						peaShooterAction((PeaShooter) model, i, j);

					} else if (model instanceof Bullet) {
						// Check if the bullet hit zombie
						// - Attack
						// Otherwise, move forward
						bulletAction((Bullet) model, i, j);

					} else if (model instanceof AbstractZombie) {
						// TODO implement this
						// Check if should attack
						// - Attack
						// Otherwise
						// should move forward
						// - this.grid.shiftModel(model, i, j)
						zombieAction((FastZombie) model, i, j);
						System.out.println("fast zombie is at ["+ i +","+ j+ "]");			
					}
					if (model instanceof SunFlower) {
					    System.out.println("SunFlower" + "["+i+j+"]  ");
					    }
					if (model instanceof PeaShooter) {
					    System.out.println("PeaShooter" + "["+i+j+"]  ");
					    }
				}
			
			}
	      
      System.out.println("Current sun points: " + this.sp);
      System.out.println("");
      
      userAction();

		}
	}

	private void sunFlowerAction(SunFlower s) {
		this.sp += s.generateSun();
	}

	private void peaShooterAction(PeaShooter s, int i, int j) {
		for (int k = j; k < this.board.getLength(); k++) {
			// Damage the first zombie it finds
			if (this.board.getModel(i, k) instanceof AbstractZombie) {
				// TODO
				s.shoot();
			}
		}
	}

	private void bulletAction(Bullet b, int i, int j) {
		if (this.board.getModel(i, j + 1) instanceof AbstractZombie) {
			b.attack((AbstractZombie) this.board.getModel(i, j + 1));
		}
		this.board.shiftModel(b, i, j);
	}

	private void zombieAction(FastZombie z, int i, int j) {
		if (this.board.getModel(i, j - 1) instanceof AbstractPlant) {
			z.attack(this.board.getModel(i, j - 1));
		}
		this.board.shiftModel(z, i, j);
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

    AbstractPlant plant = getPlantFromUser();
    int row, column;

    while (true) {
      row = getRowFromUser();
      column = getColumnFromUser();

      Model m = this.board.getModel(row, column);
      if (m == null) {
        break;
      }
      System.out.println("Location isn't available, try a different one");
    }

    this.board.addModel(plant, row, column);
 }

  private AbstractPlant getPlantFromUser() {
    while (true) {
      AbstractPlant plant = selectPlant();
      if (plant.getCost() < this.sp) {
        this.sp -= plant.getCost();
        return plant;
      }
      System.out.println("Not enough sun points for selected plant, try again");
    }
  }

  private AbstractPlant selectPlant() {
    System.out.println("Pick plant to place:");
    System.out.println("1. Sun plant    |  50 sp");
    System.out.println("2. Pea shooter  | 100 sp");

    int option = this.consoleInputAsInt();

    switch (option) {
      case 1:
        return new SunFlower();
      case 2:
        return new PeaShooter();
      case 3:
      default:
        System.out.println(option + " isn't a valid option, try again");
        return selectPlant();
    }
  }

  private int getRowFromUser() {
    System.out.println("Select row to place plant on grid (indexed from 0)");

    while (true) {
      int row = consoleInputAsInt();
      if (row >= 0 && row < this.board.getHeight()) {
        return row;
      }
      System.out.println("Invalid row, try again");
    }
  }

  private int getColumnFromUser() {
    System.out.println("Select column to place plant on grid (indexed from 0)");

    while (true) {
      int column = consoleInputAsInt();
      if (column >= 0 && column < this.board.getLength()) {
        return column;
      }
      System.out.println("Invalid column, try again");
    }
  }

  private int consoleInputAsInt() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      String input = reader.readLine();
      return Integer.parseInt(input);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (NumberFormatException e) {
      System.out.println("Input isn't a number, enter again");
      return this.consoleInputAsInt();
    }
    return 0;
  }

  public static void main(String[] args) {
    Game g = new Game();
    g.gameLoop();
  }
}
