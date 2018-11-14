package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModelTest {
	Model m;

	@Before
	public void setUp() throws Exception {
		m = new SunFlower();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetType() {
		assert (m.getType() == ModelType.SUN_FLOWER);
	}

	@Test
	public void testSetType() {
		m.setType(ModelType.FAST_ZOMBIE);
		assert (m.getType() == ModelType.FAST_ZOMBIE);
	}

	// Test all getters and setters
	@Test
	public void testGetHp() {
		assert (m.getHp() == 50);
	}

	@Test
	public void testSetHp() {
		m.setHp(69);
		assert (m.getHp() == 69);
	}

	@Test
	public void testGetAtk() {
		assert (m.getAtk() == 0);
	}

	@Test
	public void testSetAtk() {
		m.setAtk(69);
		assert (m.getAtk() == 69);
	}

	@Test
	public void testGetCd() {
		assert (m.getCd() == 3);
	}

	@Test
	public void testSetCd() {
		m.setCd(4);
		assert (m.getCd() == 4);
	}

}
