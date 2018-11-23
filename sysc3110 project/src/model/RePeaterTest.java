package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RePeaterTest {
	RePeater p;

	@Before
	public void setUp() throws Exception {
		p = new RePeater();
	}

	@After
	public void tearDown() throws Exception {
	}

	// Test constructor and ModelType
	@Test
	public void testPeaShooter() {
		assert (p.getType() == ModelType.RE_PEATER);
	}

	// test that a pea shooter can attack a zombie, and change its health
	@Test
	public void testAttack() {
		FastZombie z = new FastZombie();
		p.attack(z);
		assert (p.getCd() == 1);
	}
}
