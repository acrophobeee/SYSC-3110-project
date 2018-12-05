package model;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import game.GameControllerTest;

/*
 * Test all test class.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  GameControllerTest.class,
  SunFlowerTest.class,
  ModelTest.class,
  PeaShooterTest.class,
  FastZombieTest.class,
  ModelTypeTest.class,
  NutTest.class,
  BombTest.class,
})

public class AllTests {
}
