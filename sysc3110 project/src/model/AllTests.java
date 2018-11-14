package model;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
 * Test all test class.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  SunFlowerTest.class,
  ModelTest.class,
  PeaShooterTest.class,
  FastZombieTest.class,
  ModelTypeTest.class,
})

public class AllTests {
}
