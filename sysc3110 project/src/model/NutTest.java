package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NutTest {

	private Nut n;
	@Before
	public void setUp() throws Exception {
		n = new Nut();
	}

	@After
	public void tearDown() throws Exception {
	}

	// Test constructor and ModelType
	@Test
	public void testNut() {
		assert (n.getType() == ModelType.NUT);
	}

}
