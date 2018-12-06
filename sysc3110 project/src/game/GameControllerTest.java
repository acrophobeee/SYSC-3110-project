package game;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.Component;
import model.SunFlower;

class MockDialog implements Dialog {
	public String input;

	public String showInputDialog(Component parentComponent, Object message) {
		return this.input;
	}

	public void showMessageDialog(Component parentComponent, Object message) {
		return;
	}
}

public class GameControllerTest {

	private GameViewController g;

	@Before
	public void setUp() throws Exception {
		g = new GameViewController();
	}

	@Test
	public void testHeight() {
		int gg = g.getGame().getGrid().getHeight();
		assertEquals(5, gg);
	}

	@Test
	public void testSp() {
		int gg = g.getGame().getSp();
		assertEquals(200, gg);
		g.getGame().setSp(100);
		assertEquals(100, g.getGame().getSp());
	}

	@Test
	public void testButton() {
		assertEquals("sun", g.getGameView().getSun());
	}

	@Test
	public void testgrid() {
		SunFlower s = new SunFlower();
		g.getGame().board.addModel(s, 1, 9);
		assertEquals(s, g.getGame().board.getModel(1, 9));

	}

	@Test
	public void testgame() {
		SunFlower s = new SunFlower();
		g.getGame().board.addModel(s, 1, 9);
		g.getGame().sunFlowerAction(s, 1, 9);
		int sp = g.getGame().getSp();
		assertEquals(sp, 225);

	}

	@Test
	public void testgrid2() {
		SunFlower s = new SunFlower();
		g.getGame().board.addModel(s, 1, 9);
		assertEquals(null, g.getGame().board.getModel(1, 8));
	}

	@Test
	public void testUserActionDontCrashOnCancel() {
		ActionEvent action = new ActionEvent(this, 0, "pea");
		g.setDialog(new MockDialog());
		g.actionPerformed(action);
	}

	@Test
	public void testUserActionPlacesPlant() {
		MockDialog mock = new MockDialog();
		mock.input = Integer.toString(1);
		g.setDialog(mock);

		ActionEvent action = new ActionEvent(this, 0, "sun");
		g.actionPerformed(action);

		Boolean isSun = g.getGame().getGrid().getModel(1, 1) instanceof SunFlower;
		assert (isSun);
	}

	@Test
	public void testSaveToDiskWritesToAFile() {
		String path = "testfile.ser";
		g.saveToDisk(path);
		File f = new File(path);
		assert (f.exists() && !f.isDirectory());
	}

	@Test
	public void testLoadFromFileUpdatesState() {
		String path = "testfile.ser";
		Game tmp = new Game(0);
		tmp.setSp(69);
		g.setGame(tmp);
		g.saveToDisk(path);
		g.getGame().setSp(1);
		g.loadFromDisk(path);
		assert (g.getGame().getSp() == 69);
	}

	@After
	public void tearDown() throws Exception {

	}

}
