package model;

import org.junit.Test;

public class ModelTypeTest {
	@Test
	public void testToString() {
		assert (ModelType.BULLET.toString().equalsIgnoreCase("b"));
	}

}
