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

          } else if (model instanceof PeanShooter) {
            peanShooterAction((PeanShooter) model, i, j);

          } else if (model instanceof Bullet) {
        	  //Check if the bullet hit zombie
        	  // - Attack
        	  //Otherwise, move forward
        	  bulletAction((Bullet)model,i,j);

          } else if (model instanceof AbstractZombies) {
            // TODO impliment this
            // Check if the zombie should attack
            //   - Attack
            // Otherwise
            //   - Zombies shoudl move forward
            //   - this.grid.shiftModel(model, i, j)
        	  zombieAction((FastZombies)model,i,j);
          }
        }
      }

      spawnZombie();
    }
  }


  private void sunFlowerAction(SunFlower s) {
    this.sp += s.generateSun();
  }

  private void peanShooterAction(PeanShooter s, int i, int j) {
    for (int k = j ; k < this.board.getLength(); k++) {
      // Damage the first zombie it finds
      if (this.board.getModel(i, k) instanceof AbstractZombies) {
        // TODO
    	  s.shoot();
      }
    }
  }

  private void bulletAction(Bullet b, int i, int j) {
	  if (this.board.getModel(i, j+1) instanceof AbstractZombies) {
		  b.attack(this.board.getModel(i, j+1));
	  }
	  this.board.shiftModel(b, i, j);
  }

  private void zombieAction(FastZombies z, int i, int j) {
	  if (this.board.getModel(i, j-1) instanceof AbstractPlants) {
		  z.attack(this.board.getModel(i, j-1));
	  }
	  this.board.shiftModel(z, i, j);
  }

  private void spawnZombie() {
    int r = (new Random()).nextInt(4)+1;
    FastZombies fz = new FastZombies();
    this.board.addModel(fz, r, 9);
  }

  private boolean isGameOver() {
    for (int i =0 ; i<this.board.getHeight(); i++) {
      if (this.board.getModel(i, 0) instanceof AbstractZombies) {
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
