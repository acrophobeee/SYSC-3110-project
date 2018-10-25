package game;

import java.util.Random;
import java.util.Scanner;

import model.*;

public class Game {

  private Grid board;
  private int sp; // sun points.

  public Game() {
    this.board = new Grid();
    this.sp = 0;
  }

  public void gameLoop() {
    while(true) {
      // Check if zombies have hit
      if (this.isGameOver()) {
        return;
      }

      for(int i = 0; i<5; i++) {
        for (int j = 0; j<10; j++) {
          Model model = this.board.getModel(i, j);

          if (model instanceof SunFlower) {
            sunFlowerAction((SunFlower) model);

          } else if (model instanceof PeaShooter) {
            peaShooterAction((PeaShooter) model, i, j);

          } else if (model instanceof Bullet) {
        	  //Check if the bullet hit zombie
        	  // - Attack
        	  //Otherwise, move forward
        	  bulletAction((Bullet)model,i,j);

          } else if (model instanceof AbstractZombie) {
            // TODO impliment this
            // Check if the zombie should attack
            //   - Attack
            // Otherwise
            //   - Zombies shoudl move forward
            //   - this.grid.shiftModel(model, i, j)
        	  zombieAction((FastZombie)model,i,j);
          }
        }
      }

      spawnZombie();
    }
  }


  private void sunFlowerAction(SunFlower s) {
    this.sp += s.generateSun();
  }

  private void peaShooterAction(PeaShooter s, int i, int j) {
    for (int k = j ; k < this.board.getLength(); k++) {
      // Damage the first zombie it finds
      if (this.board.getModel(i, k) instanceof AbstractZombie) {
        // TODO
    	  s.shoot();
      }
    }
  }

  private void bulletAction(Bullet b, int i, int j) {
	  if (this.board.getModel(i, j+1) instanceof AbstractZombie) {
		  b.attack((AbstractZombie)this.board.getModel(i, j+1));
	  }
	  this.board.shiftModel(b, i, j);
  }

  private void zombieAction(FastZombie z, int i, int j) {
	  if (this.board.getModel(i, j-1) instanceof AbstractPlant) {
		  z.attack(this.board.getModel(i, j-1));
	  }
	  this.board.shiftModel(z, i, j);
  }

  private void spawnZombie() {
    int r = (new Random()).nextInt(4)+1;
    FastZombie fz = new FastZombie();
    this.board.addModel(fz, r, 9);
  }

  private boolean isGameOver() {
    for (int i =0 ; i<this.board.getHeight(); i++) {
      if (this.board.getModel(i, 0) instanceof AbstractZombie) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Game g = new Game();
    g.gameLoop();
  }
}
