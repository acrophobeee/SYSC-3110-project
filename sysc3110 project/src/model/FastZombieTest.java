package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FastZombieTest {
	FastZombie z;

	@Before
	public void setUp() throws Exception {
		z = new FastZombie();
	}

	@After
	public void tearDown() throws Exception {
	}

	// test constructor and ModelType
	@Test
	public void testFastZombie() {
		assert (z.getType() == ModelType.FAST_ZOMBIE);
	}

	// test that it can attack a plant
	@Test
	public void testAttack() {
		AbstractPlant s = new SunFlower();
		z.attack(s);
		assert (s.getHp() == 40);
	}

}
