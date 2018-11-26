package game;
/*
produce by weihongshen, xinyuchen, jacky chiu, kirin
*/

import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import model.*;

public class Game implements Serializable {

	public Grid board;
	private int sp; // sun points.

	//Create a new game.
	public Game() {
		this.board = new Grid();
		this.sp = 200;

		this.spawnZombie();
	}

	// runTurn runs one turns worth of actions per entity on the board.
	// Returns if the game is over or not.
	public boolean runTurn() {
		// Check model in board.
		for (int i = 0; i < this.board.getHeight(); i++) {
			for (int j = 0; j < this.board.getLength(); j++) {
				Model model = this.board.getModel(i, j);

				// check what model it is and do action.
				if (model instanceof SunFlower) {
					sunFlowerAction((SunFlower) model, i, j);
				} else if (model instanceof PeaShooter) {
					peaShooterAction((PeaShooter) model, i, j);
				} else if (model instanceof AbstractZombie) {
					if (model.getHp() <= 0) {
						this.board.removeModel(model, i, j);
						System.out.println("zombie died, new zombie coming");
						spawnZombie();
					} else {
						zombieAction((AbstractZombie) model, i, j);
						System.out.println("zombie at [" + i + "," + j + "], Hp = " + model.getHp());
					}
				} else if (model instanceof Nut) {
					nutAction((Nut)model,i,j);
					System.out.println("Nut" + "[" + i + "," + j + "], Hp = " + model.getHp());
				} else if (model instanceof RePeater) {
					RePeaterAction((RePeater)model,i,j);
					System.out.println("RePeater" + "[" + i + "," + j + "], Hp = " + model.getHp());
				}
				if (model instanceof SunFlower) {
					System.out.println("SunFlower" + "[" + i + "," + j + "], Hp = " + model.getHp());
				}
				if (model instanceof PeaShooter) {
					System.out.println("PeaShooter" + "[" + i + "," + j + "], Hp = " + model.getHp());
				}
				else if (model instanceof Bomb) {
					BombAction((Bomb)model,i,j);
					System.out.println("Bomb" + "[" + i + "," + j + "], Hp = " + model.getHp());
			}
		}

		System.out.println("Current sun points: " + this.sp);
		System.out.println("");
		System.out.println(this.board);

		
	}
		return this.isGameOver();
	}
	/*
	 * sun flower action to generate sun point and remove model if the flower get killed.
	 */
	public void sunFlowerAction(SunFlower s, int i, int j) {
		this.sp += s.generateSun();
		if (s.getHp() <= 0) {
			this.board.removeModel(s, i, j);
		}
	}

	/*
	 * action for pea shooter to attck zombie and remove while killed.
	 */
	private void peaShooterAction(PeaShooter s, int i, int j) {
		if (s.getHp() <= 0) {
			this.board.removeModel(s, i, j);
		} else {
			for (int k = j; k < this.board.getLength(); k++) {
				if (this.board.getModel(i, k) instanceof AbstractZombie) {
					s.attack(this.board.getModel(i, k));
				}
			}
		}
	}
	
	/*
	 * action for Repeater to attck zombie and remove while killed.
	 */
	private void RePeaterAction(RePeater r, int i, int j) {
		if (r.getHp()<=0) {
			this.board.removeModel(r, i, j);
		}else {
			for (int k = j; k < this.board.getLength(); k++) {
				if (this.board.getModel(i, k) instanceof AbstractZombie) {
					r.attack(this.board.getModel(i, k));
				}
			}
		}
	}
	
	/*
	 * action for nut to remove while killed.
	 */
	private void nutAction(Nut n, int i, int j) {
		if (n.getHp()<=0) {
			this.board.removeModel(n, i, j);
		}
	}

	/*
	 * action for zombie to attck plants and remove while killed.
	 */
	private void zombieAction(AbstractZombie z, int i, int j) {
		if (z.getHp() <= 0) {
			this.board.removeModel(z, i, j);
		} else {
			if (this.board.getModel(i, j - 1) instanceof AbstractPlant) {
				z.attack(this.board.getModel(i, j - 1));
			} else if (this.board.getModel(i, j - 1) == null) {
				this.board.shiftModel(z, i, j);
			}
		}
	}

	/*
	 * action for nut to remove while killed.
	 */
	private void BombAction(Bomb n, int i, int j) {
		Model model = this.board.getModel(i, j+1);
		if (model instanceof AbstractZombie) {
			this.board.removeModel(n, i, j);
			model.setHp(-1);
			
		}
	}
	
	public int getSp() {
		return sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}

	public Grid getGrid() {
		return this.board;
	}

	/*
	 * spawn a zombie in column 10 and in random row.
	 */
	private void spawnZombie() {
		//spawn one zombie at once.
		int r = (new Random()).nextInt(5);
		int r1 = (new Random()).nextInt(2);
		ArrayList<AbstractZombie> z = new ArrayList();
		ConeHeadZombie cz = new ConeHeadZombie();
		FastZombie fz = new FastZombie();
		z.add(cz);
		z.add(fz);
		AbstractZombie a = z.get(r1);
		this.board.addModel(a, r, this.board.getLength()-1);
		
		//spawn two zombies at once.
//		int r = (new Random()).nextInt(5);
//		int r1 = (new Random()).nextInt(5);
//		ConeHeadZombie cz = new ConeHeadZombie();
//		FastZombie fz = new FastZombie();
//		if (r != r1) {
//			this.board.addModel(fz, r, this.board.getLength()-1);
//			this.board.addModel(cz, r1, this.board.getLength()-1);
//		}
//		else {
//			this.board.addModel(fz, r, this.board.getLength()-1);
//			r1 =(new Random()).nextInt(5);
//			this.board.addModel(cz, r1, this.board.getLength()-1);
//		}
	}

	/*
	 * boolean to check if the zombie reach the first column. true for game over.
	 */
	private boolean isGameOver() {
		for (int i = 0; i < this.board.getHeight(); i++) {
			if (this.board.getModel(i, 0) instanceof AbstractZombie) {
				return true;
			}
		}
		return false;
	}


  public static Game copy(Game original) {
    Game newGame = null;
    try {
      // Write the object out to a byte array
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream out = new ObjectOutputStream(bos);
      out.writeObject(original);
      out.flush();
      out.close();

      // Make an input stream from the byte array and read
      // a copy of the object back in.
      ObjectInputStream in = new ObjectInputStream(
          new ByteArrayInputStream(bos.toByteArray()));
      newGame = (Game) in.readObject();
    }
    catch(IOException e) {
      e.printStackTrace();
    }
    catch(ClassNotFoundException e) {
      e.printStackTrace();
    }

    return newGame;
  }
}
