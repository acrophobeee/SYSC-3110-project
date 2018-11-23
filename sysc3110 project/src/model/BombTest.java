package model;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BombTest {


	Bomb z;

	@Before
	public void setUp() throws Exception {
		z = new Bomb();
	}

	@After
	public void tearDown() throws Exception {
	}

	// test constructor and ModelType
	@Test
	public void testBomb() {
		assert (z.getType() == ModelType.BOMB);
	}
}
