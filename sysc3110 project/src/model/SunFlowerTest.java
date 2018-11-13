package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SunFlowerTest {
	SunFlower s;

	@Before
	public void setUp() throws Exception {
		s = new SunFlower();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSunFlower() {
		assert (s.getType() == ModelType.SUN_FLOWER);
	}

	@Test
	public void testGenerateSun() {
		assert (s.getSp() == 0);
		s.generateSun();
		assert (s.getSp() == 25);
	}

	@Test
	public void testGetSp() {
		assert (s.getSp() == 0);
	}

}
