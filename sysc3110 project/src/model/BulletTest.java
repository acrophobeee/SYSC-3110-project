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

	// Test constructor and ModelType
	@Test
	public void testBullet() {
		assert (b.getType() == ModelType.BULLET);
	}

	// Test other constructor
	@Test
	public void testBulletIntInt() {
		b = new Bullet(1, 2);
		assert (b.getAtk() == 1);
		assert (b.getSpeed() == 2);
	}

	// Test that it can damage a zombie
	@Test
	public void testAttack() {
		FastZombie z = new FastZombie();
		b.attack(z);
		assert (z.getHp() == 90);
	}

	// test getters and setters
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
