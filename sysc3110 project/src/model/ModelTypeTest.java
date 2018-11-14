package model;

import org.junit.Test;

public class ModelTypeTest {
	// Test toString uses first letter
	@Test
	public void testToString() {
		assert (ModelType.FAST_ZOMBIE.toString().equalsIgnoreCase("f"));
	}

}
