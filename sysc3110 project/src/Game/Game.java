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

  public void start() {
    boolean gameOver = false;
    while(!gameOver) {



    }
  }

  public static void main(String[] args) {
    Game g = new Game();
    g.start();
  }
}
