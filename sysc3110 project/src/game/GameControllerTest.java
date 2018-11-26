package game;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import model.SunFlower;

public class GameControllerTest {

	private GameViewController g;

	@Before
	public void setUp() throws Exception {
		g = new GameViewController();
		g.run();
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

	@After
	public void tearDown() throws Exception {

	}

}
