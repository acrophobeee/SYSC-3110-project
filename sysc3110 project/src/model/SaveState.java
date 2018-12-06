package model;

import java.io.Serializable;
import java.util.Stack;

import game.Game;

public class SaveState implements Serializable {

	private static final long serialVersionUID = 4189277419812760436L;
	private Game game;
	private Stack<Game> undoStack;
	private Stack<Game> redoStack;

	public SaveState(Game g, Stack<Game> undo, Stack<Game> redo) {
		game = g;
		undoStack = undo;
		redoStack = redo;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Stack<Game> getUndoStack() {
		return undoStack;
	}

	public void setUndoStack(Stack<Game> undoStack) {
		this.undoStack = undoStack;
	}

	public Stack<Game> getRedoStack() {
		return redoStack;
	}

	public void setRedoStack(Stack<Game> redoStack) {
		this.redoStack = redoStack;
	}
}
