package game;

import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameViewController implements ActionListener {

	// Models
	private Game game;

	// Views
	private GameView gameView;

	private ImageIcon image;

	GameViewController() {
		this.game = new Game();

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
			userAction(action);
			gameOver = this.game.runTurn();
			break;
		case "skip":
			gameOver = this.game.runTurn();
			break;
		case "quit":
			System.exit(0);
		case "restart":
			this.game = new Game();
			break;
		}

		this.gameView.renderGrid(this.game.getGrid());
		this.gameView.renderSunPoints(this.game.getSp());

		if (gameOver) {
			JOptionPane.showMessageDialog(null, "GAME OVER");
		}
	}

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

	public static void main(String[] args) {
		GameViewController g = new GameViewController();
		g.run();
	}
}
