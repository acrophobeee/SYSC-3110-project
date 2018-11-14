package model;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  SunFlowerTest.class,
  ModelTest.class,
  BulletTest.class,
  PeaShooterTest.class,
  FastZombieTest.class,
  ModelTypeTest.class,
})

public class AllTests {
}
