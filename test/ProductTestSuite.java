package test;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CoffeBaseTest.class,
	CoffeDecoratedTest.class,
	DrinkBaseTest.class,
	DrinkDecoratedTest.class
})

public class ProductTestSuite {
	
}
