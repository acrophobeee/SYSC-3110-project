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
    for (int i =0; i<this.getHeight(); i++) {
      for (int j = 0; j<this.getLength(); j++) {
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
		// TODO validate the i, j
		return this.map[i][j];
	}

	public void shiftModel(Model model, int i, int j) {
		// TODO shift model to the left
		// Used to move zombies forward
		// Used to move bullet forward
		 
			if (model instanceof AbstractZombie) {
				removeModel(model,i,j);
				if(j-((AbstractZombie) model).getSpeed()>0) {
				j = j - ((AbstractZombie) model).getSpeed();
				addModel(model, i, j);	
				}
				else {
					addModel(model,i,0);
				}
			}
			if (model instanceof Bullet) {
				removeModel(model,i,j);
				if(j+((Bullet) model).getSpeed()<10)
				j = j + ((Bullet) model).getSpeed();
				addModel(model, i, j);		
			}
			
		
	}
}
