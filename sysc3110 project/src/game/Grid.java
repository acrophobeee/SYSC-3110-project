package game;

import java.util.Arrays;
import java.util.Random;

import model.*;

public class Grid {

  @Override
public String toString() {
	return "at[	";
}

final private int gridHeight = 5;
  final private int gridLength = 10;
  private Model[][] map;

  public Grid() {
    map = new Model[this.gridHeight][this.gridLength];
  }

  public void clearMap() {
    for (int i =0; i<5; i++) {
      for (int j = 0; j<10; j++) {
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

  public void addModel(Model model,int i, int j) {
    map[i][j] = model;
  }

  public void removeModel(Model model, int i, int j) {
    map[i][j] = null;
  }

  public Model getModel(int i, int j) {
    // TODO validate the i, j
    return this.map[i][j];
  }

  public void shiftModel(Model model, int i, int j) {
    // TODO shift model to the left
    // Used to move zombies forward
	// Used to move bullet forward
	  for (int a = j; a>=0; a--) {
		  if (model instanceof AbstractZombies) {
			  shiftModel(model,i,a-1);
		  }
	  }
	  for (int b = j; b<this.gridLength; b++) {
		  if (model instanceof Bullet) {
			  shiftModel(model,i,b+1);
		  }
	  }
  }
}
