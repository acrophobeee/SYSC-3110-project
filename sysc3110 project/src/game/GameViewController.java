package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;

import javax.swing.JOptionPane;

import model.AbstractPlant;
import model.Bomb;
import model.Model;
import model.Nut;
import model.PeaShooter;
import model.RePeater;
import model.SunFlower;

public class GameViewController implements ActionListener, Serializable {

	// Models
	private Game game;
	private Stack<Game> undoStack;
	private Stack<Game> redoStack;

	// Views
	private GameView gameView;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

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
		// Button action.
		// (add five new buttons nut, rep,tnt, redo and undo button for milestone 3
		String action = e.getActionCommand();
		switch (action) {
		case "pea":
		case "sun":
		case "nut":
		case "rep":
		case "tnt":
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
		case "undo": // use stack to implement the redo and undo
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
		case "save":
			// save the current state
			String path = JOptionPane.showInputDialog(null, "Enter location to save to:");
			if (path == null || path.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Path must be non empty string");
				break;
			}
			saveToDisk(path);
			break;
		case "load":
			// load the state from the file
			path = JOptionPane.showInputDialog(null, "Enter location to load from:");
			if (path == null || path.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Path must be non empty string");
				break;
			}
			loadFromDisk(path);
			break;
		}
		// everytime the actionPerformed render it to the gui
		this.gameView.renderGrid(this.game.getGrid());
		this.gameView.renderSunPoints(this.game.getSp());

		if (gameOver) { // show when the game is over
			JOptionPane.showMessageDialog(null, "GAME OVER");
		}
	}

	// serialize the state and save it to disk
	// at the location provided 'path'
	private void saveToDisk(String path) {
		System.out.println("saving to: " + path);
		try {
			// write serialized data to file at path
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			// Warn the user saving failed
			i.printStackTrace();
			JOptionPane.showMessageDialog(null, "Saving failed, do you have proper file access?");
		}
	}

	// load the game view controller from disk
	// from the location provided
	// kill the old one, and start the newly imported one with run();
	private void loadFromDisk(String path) {
		System.out.println("loading from: " + path);
	}

	// add the model to the grid first
	// if no plant to place direct pass the turn
	private void userAction(String plantOption) {
		if (this.game.getSp() < 50) {
			JOptionPane.showMessageDialog(null, "You don't have enough Sun point, skip round");
			;
			return;
		}

		AbstractPlant plant = getPlant(plantOption);
		if (plant == null) {

			return;

		}
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

	// if sun points enough choose the plant, otherwise return null
	private AbstractPlant getPlant(String plantOption) {
		AbstractPlant plant = selectPlant(plantOption);
		if (plant.getCost() <= this.game.getSp()) {
			this.game.setSp(this.game.getSp() - plant.getCost());
			return plant;
		} else {
			JOptionPane.showMessageDialog(null, "Not enough sun points, Want to redo please undo step!");
			return null;
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
		case "rep":
			return new RePeater();
		case "tnt":
			return new Bomb();
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

	// Get the column to place plants.
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

	// copy temporary game and push to stack
	private void addUndo() {
		Game currentState = Game.copy(this.game);
		this.undoStack.push(currentState);
	}

	// copy the stack's game model ans set the game to this.
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

	// main function will display the gui
	public static void main(String[] args) {
		GameViewController g = new GameViewController();
		g.run();
	}
}
