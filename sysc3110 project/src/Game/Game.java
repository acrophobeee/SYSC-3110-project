package Game;

import java.util.Scanner;
import Model.*;

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
      if (this.board.isGameOver()) {
        return;
      }

      for(int i = 0; i<5; i++) {
        for (int j = 0; j<10; j++) {
          Model model = this.board.getModel(i, j);

          if (model instanceof sunFlower) {
            sunFlowerAction((sunFlower) model);

          } else if (model instanceof peanShooter) {
            peanShooterAction((peanShooter) model, i, j);

          } else if (model instanceof abstractZombies) {
            // TODO impliment this
            // Check if the zombie should attack
            //   - Attack
            // Otherwise
            //   - Zombies shoudl move forward
            //   - this.grid.shiftModel(model, i, j)
          }
        }
      }

      spawnZombie();
    }
  }


  private void sunFlowerAction(sunFlower s) {
    this.sp += s.generateSun();
  }

  private void peanShooterAction(peanShooter s, int i, int j) {
    for (int j = j ; j < this.board.getLength(); j++) {
      // Damage the first zombie it finds
      if (this.board.getModel(i, j) instanceof abstractZombies) {
        // TODO
      }
    }
  }

  private void spawnZombie() {
    int r = (new Random()).nextInt(4)+1;
    fastZombies fz = new fastZombies();
    this.board.addModel(fz, 9, r);
  }

  private boolean isGameOver() {
    for (int i =0 ; i<this.board.getHeight(); i++) {
      if (this.board.getModel(i, 0) instanceof abstractZombies) {
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
