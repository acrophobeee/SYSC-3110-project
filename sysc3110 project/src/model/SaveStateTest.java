package model;

import static org.junit.Assert.fail;

import java.util.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import game.Game;

public class SaveStateTest {

	SaveState s;

	@Before
	public void setUp() throws Exception {
		Game g = new Game();
		s = new SaveState(g, new Stack<Game>(), new Stack<Game>());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveState() {
		Game g = new Game();
		g.setSp(1);
		s = new SaveState(g, null, null);
		assert (s.getGame().getSp() == 1);
	}

	@Test
	public void testGetGame() {
		assert (s.getGame() != null);
	}

	@Test
	public void testSetGame() {
		Game g = new Game();
		g.setSp(1);
		s.setGame(g);
		assert (s.getGame().getSp() == 1);
	}

	@Test
	public void testGetUndoStack() {
		assert (s.getUndoStack() != null);
	}

	@Test
	public void testSetUndoStack() {
		s.setUndoStack(null);
		assert (s.getUndoStack() == null);
	}

	@Test
	public void testGetRedoStack() {
		assert (s.getRedoStack() != null);
	}

	@Test
	public void testSetRedoStack() {
		s.setRedoStack(null);
		assert (s.getRedoStack() == null);
	}

}
