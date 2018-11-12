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

    this.spawnZombie();
  }

  // runTurn runs one turns worth of actions per entity on the board.
  // Returns if the game is over or not.
  public boolean runTurn() {
    // Check if zombies have hit
    for (int i = 0; i < this.board.getHeight(); i++) {
      for (int j = 0; j < this.board.getLength(); j++) {
        Model model = this.board.getModel(i, j);

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
    System.out.println(this.board);

    return this.isGameOver();
  }
  
  void reStart() {
	  this.board.clearMap();
  }

  private void sunFlowerAction(SunFlower s, int i, int j) {
    this.sp += s.generateSun();
    if (s.getHp()<=0) {
    	this.board.removeModel(s, i, j);
    }
  }

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

  private void zombieAction(FastZombie z, int i, int j) {
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

  public int getSp() {
    return sp;
  }

  public void setSp(int sp) {
    this.sp = sp;
  }

  public Grid getGrid() {
    return this.board;
  }

  private void spawnZombie() {
    int r = (new Random()).nextInt(5);
    FastZombie fz = new FastZombie();
    this.board.addModel(fz, r, this.board.getLength()-1);
  }

  private boolean isGameOver() {
    for (int i = 0; i < this.board.getHeight(); i++) {
      if (this.board.getModel(i, 0) instanceof AbstractZombie) {
        return true;
      }
    }
    return false;
  }
}
