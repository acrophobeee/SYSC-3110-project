package game;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

import javax.swing.JOptionPane;

import model.AbstractPlant;
import model.Bomb;
import model.Model;
import model.Nut;
import model.PeaShooter;
import model.RePeater;
import model.SaveState;
import model.SunFlower;

public class GameViewController implements ActionListener {

	// Models
	private Game game;
	private Stack<Game> undoStack;
	private Stack<Game> redoStack;

	// Views
	private Dialog dialog;
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

	public void setDialog(Dialog d) {
		this.dialog = d;
	}

	GameViewController() {
		this.game = new Game(0);
		this.undoStack = new Stack<Game>();
		this.redoStack = new Stack<Game>();

		int rows = this.game.getGrid().getHeight();
		int columns = this.game.getGrid().getLength();

		this.dialog = new GUIDialog();

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
		try {
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
				this.game = new Game(0);
				while(this.undoStack.empty() == false) {
				this.undoStack.pop();
				}
				while(this.redoStack.empty() == false) {
				this.redoStack.pop();
				}
				break;
			case "undo": // use stack to implement the redo and undo
				if (this.undoStack.empty()) {
					this.dialog.showMessageDialog(null, "Nothing to undo");
					return;
				} else {
					undo();
				}
				break;
			case "redo":
				if (this.redoStack.empty()) {
					this.dialog.showMessageDialog(null, "Nothing to redo");
					return;
				} else {
					redo();
				}
				break;
			case "save":
				// save the current state
				String path = this.dialog.showInputDialog(null, "Enter location to save to:");
				if (path == null || path.isEmpty()) {
					this.dialog.showMessageDialog(null, "Path must be non empty string");
					break;
				}
				saveToDisk(path);
				break;
			case "easy":
				this.game = new Game(0);
				while(this.undoStack.empty() == false) {
				this.undoStack.pop();
				}
				while(this.redoStack.empty() == false) {
				this.redoStack.pop();
				}
				break;
			case "medium":
				this.game = new Game(1);
				while(this.undoStack.empty() == false) {
				this.undoStack.pop();
				}
				while(this.redoStack.empty() == false) {
				this.redoStack.pop();
				}
				break;
			case "hard":
				this.game = new Game(2);
				while(this.undoStack.empty() == false) {
				this.undoStack.pop();
				}
				while(this.redoStack.empty() == false) {
				this.redoStack.pop();
				}
				break;	
			case "load":
				// load the state from the file
				path = this.dialog.showInputDialog(null, "Enter location to load from:");
				if (path == null || path.isEmpty()) {
					this.dialog.showMessageDialog(null, "Path must be non empty string");
					break;
				}
				loadFromDisk(path);
				break;
			}
		} catch (UserCancelledAction ex) {
			// Didn't run game turn on action cancelled.
			// Remove from cancelled state from undo stack.
			this.undoStack.pop();
			// Continue at the same state.
			return;

		}
		// everytime the actionPerformed render it to the gui
		this.gameView.renderGrid(this.game.getGrid());
		this.gameView.renderSunPoints(this.game.getSp());

		if (gameOver) { // show when the game is over
			this.dialog.showMessageDialog(null, "GAME OVER");
		}
	}

	// serialize the state and save it to disk
	// at the location provided 'path'
	public void saveToDisk(String path) {
		System.out.println("saving to: " + path);
		SaveState s = new SaveState(game, undoStack, redoStack);
		try {
			// write serialized data to file at path
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(s);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			// Warn the user saving failed
			i.printStackTrace();
			this.dialog.showMessageDialog(null, "Saving failed, do you have proper file access?");
		}
	}

	// load the game view controller from disk
	// from the location provided
	// kill the old one, and start the newly imported one with run();
	public void loadFromDisk(String path) {
		System.out.println("loading from: " + path);
		SaveState s = null;
		try {
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fileIn);
			s = (SaveState) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			this.dialog.showMessageDialog(null, "Loading failed, do you have proper file access?");
		}
		// s is now populated
		this.game = s.getGame();
		this.undoStack = s.getUndoStack();
		this.redoStack = s.getRedoStack();
	}

	// add the model to the grid first
	// if no plant to place direct pass the turn
	private void userAction(String plantOption) throws UserCancelledAction {
		if (this.game.getSp() < 50) {
			this.dialog.showMessageDialog(null, "You don't have enough Sun point, skip round");
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
			this.dialog.showMessageDialog(null, "Location unavailable, please select another location");
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
			this.dialog.showMessageDialog(null, "Not enough sun points, Want to redo please undo step!");
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
	private int getRowFromUser() throws UserCancelledAction {
		System.out.println("Select row to place plant on grid (indexed from 0)");

		while (true) {
			String rows = this.dialog.showInputDialog(null, "Input place row");
			if (rows == null) {
				throw new UserCancelledAction();
			}
			int row = Integer.parseInt(rows);
			if (row >= 0 && row < this.game.getGrid().getHeight()) {
				return row;
			} else {
				this.dialog.showMessageDialog(null, "Invalid row, try again");
			}
		}
	}

	// Get the column to place plants.
	private int getColumnFromUser() throws UserCancelledAction {
		System.out.println("Select column to place plant on grid (indexed from 0)");

		while (true) {
			String columns = this.dialog.showInputDialog(null, "Input place column");
			if (columns == null) {
				throw new UserCancelledAction();
			}
			int column = Integer.parseInt(columns);

			if (column >= 0 && column < this.game.getGrid().getLength()) {
				return column;
			} else {
				this.dialog.showMessageDialog(null, "Invalid column, try again");
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

class UserCancelledAction extends Exception {
}

interface Dialog {
	String showInputDialog(Component parentComponent, Object message);

	void showMessageDialog(Component parentComponent, Object message);
}

class GUIDialog implements Dialog {
	public String showInputDialog(Component parentComponent, Object message) {
		return JOptionPane.showInputDialog(parentComponent, message);
	}

	public void showMessageDialog(Component parentComponent, Object message) {
		JOptionPane.showMessageDialog(parentComponent, message);
		return;
	}
}
