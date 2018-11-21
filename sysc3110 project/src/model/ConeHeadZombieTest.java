package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConeHeadZombieTest {

	ConeHeadZombie z;

	@Before
	public void setUp() throws Exception {
		z = new ConeHeadZombie();
	}

	@After
	public void tearDown() throws Exception {
	}

	// test constructor and ModelType
	@Test
	public void testConeHeadZombie() {
		assert (z.getType() == ModelType.CONEHEAD_ZOMBIE);
	}

	// test that it can attack a plant
	@Test
	public void testAttack() {
		AbstractPlant s = new SunFlower();
		z.attack(s);
		assert (s.getHp() == 40);
	}
}
