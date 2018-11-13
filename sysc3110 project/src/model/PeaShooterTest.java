package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PeaShooterTest {
	PeaShooter p;

	@Before
	public void setUp() throws Exception {
		p = new PeaShooter();
	}

	@After
	public void tearDown() throws Exception {
	}

	// Test constructor and ModelType
	@Test
	public void testPeaShooter() {
		assert (p.getType() == ModelType.PEA_SHOOTER);
	}

	// test that a pea shooter can attack a zombie, and change its health
	@Test
	public void testAttack() {
		FastZombie z = new FastZombie();
		p.attack(z);
		assert (p.getCd() == 1);
		assert (z.getHp() == 75);
		p.attack(z);
		assert (z.getHp() == 75);
	}
}
