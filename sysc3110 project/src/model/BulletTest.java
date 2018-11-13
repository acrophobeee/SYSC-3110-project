package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BulletTest {
	Bullet b;

	@Before
	public void setUp() throws Exception {
		b = new Bullet(10, 10);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBullet() {
		assert (b.getType() == ModelType.BULLET);
	}

	@Test
	public void testBulletIntInt() {
		b = new Bullet(1, 2);
		assert (b.getAtk() == 1);
		assert (b.getSpeed() == 2);
	}

	@Test
	public void testAttack() {
		FastZombie z = new FastZombie();
		b.attack(z);
		assert (z.getHp() == 90);
	}

	@Test
	public void testGetSpeed() {
		assert (b.getSpeed() == 10);
	}

	@Test
	public void testSetSpeed() {
		b.setSpeed(69);
		assert (b.getSpeed() == 69);
	}

}
