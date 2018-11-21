package game;

import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Stack;

public class GameViewController implements ActionListener {

	// Models
	private Game game;
  private Stack<Game> undoStack;
  private Stack<Game> redoStack;

	// Views
	private GameView gameView;

	private ImageIcon image;

	GameViewController() {
		this.game = new Game();
    this.undoStack = new Stack<Game>();
    this.redoStack = new Stack<Game>();

		int rows = this.game.getGrid().getHeight();
		int columns = this.game.getGrid().getLength();

		this.gameView = new GameView(this, rows, columns);
		this.gameView.renderGrid(this.game.getGrid());
		this.gameView.renderSunPoints(this.game.getSp());
	}

	public void run() {
		this.gameView.display();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Boolean gameOver = false;

		//Button action.
		String action = e.getActionCommand();
		switch (action) {
		case "pea":
		case "sun":
		case "nut":
      addUndo();
			userAction(action);
			gameOver = this.game.runTurn();
			break;
		case "skip":
      addUndo();
			gameOver = this.game.runTurn();
			break;
		case "quit":
			System.exit(0);
		case "restart":
			this.game = new Game();
			break;
    case "undo":
      if (this.undoStack.empty()) {
        JOptionPane.showMessageDialog(null, "Nothing to undo");
        return;
      } else {
        undo();
      }
      break;
    case "redo":
      if (this.redoStack.empty()) {
        JOptionPane.showMessageDialog(null, "Nothing to redo");
        return;
      } else {
        redo();
      }
      break;
		}

       // everytime the actionPerformed render it to the gui
		this.gameView.renderGrid(this.game.getGrid());
		this.gameView.renderSunPoints(this.game.getSp());

		if (gameOver) { // show when the game is over
			JOptionPane.showMessageDialog(null, "GAME OVER");
		}
	}

   // add the model to the grid first
	private void userAction(String plantOption) {
		if (this.game.getSp() < 50) {
			JOptionPane.showMessageDialog(null, "You don't have enough Sun point, skip round");
			;
			return;
		}

		AbstractPlant plant = getPlant(plantOption);
		int row, column;

		while (true) {
			row = getRowFromUser();
			column = getColumnFromUser();

			Model m = this.game.getGrid().getModel(row, column);
			if (m == null) {
				break;
			}
			JOptionPane.showMessageDialog(null, "Location unavailable, please select another location");
		}

		this.game.getGrid().addModel(plant, row, column);
	}

	private AbstractPlant getPlant(String plantOption) {
		while (true) {
			AbstractPlant plant = selectPlant(plantOption);
			if (plant.getCost() <= this.game.getSp()) {
				this.game.setSp(this.game.getSp() - plant.getCost());
				return plant;
			}
			System.out.println("Not enough sun points for selected plant, try again");
		}
	}

	private AbstractPlant selectPlant(String plantOption) {
		switch (plantOption) {
		case "sun":
			return new SunFlower();
		case "pea":
			return new PeaShooter();
		case "nut":
			return new Nut();
		default:
			System.out.println(plantOption + " isn't a valid option");
			throw new IllegalArgumentException("bad plant option");
		}
	}

	// Get the row to place plants.
	private int getRowFromUser() {
		System.out.println("Select row to place plant on grid (indexed from 0)");

		while (true) {
			String rows = JOptionPane.showInputDialog(null, "Input place row", JOptionPane.QUESTION_MESSAGE);
			if (rows == null) {
				System.exit(0);
			}
			int row = Integer.parseInt(rows);
			if (row >= 0 && row < this.game.getGrid().getHeight()) {
				return row;
			} else {
				JOptionPane.showMessageDialog(null, "Invalid row, try again");
			}
		}
	}

	//Get the column to place plants.
	private int getColumnFromUser() {
		System.out.println("Select column to place plant on grid (indexed from 0)");

		while (true) {
			String columns = JOptionPane.showInputDialog(null, "Input place column", JOptionPane.QUESTION_MESSAGE);
			if (columns == null) {
				System.exit(0);
			}
			int column = Integer.parseInt(columns);

			if (column >= 0 && column < this.game.getGrid().getLength()) {
				return column;
			} else {
				JOptionPane.showMessageDialog(null, "Invalid column, try again");
			}
		}
	}

  private void addUndo() {
    Game currentState = Game.copy(this.game);
    this.undoStack.push(currentState);
  }

  private void undo() {
    addRedo();
    Game previousState = this.undoStack.pop();
    this.game = previousState;
  }

  private void addRedo() {
    Game currentState = Game.copy(this.game);
    this.redoStack.push(currentState);
  }

  private void redo() {
    addUndo();
    Game previousState = this.redoStack.pop();
    this.game = previousState;
  }

	//main function will display the gui
	public static void main(String[] args) {
		GameViewController g = new GameViewController();
		g.run();
	}
}
