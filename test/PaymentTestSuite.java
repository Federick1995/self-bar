package test;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	PaymentCashTest.class,
	PaymentCreditCardTest.class,
	PaymentDebitCardTest.class
})

public class PaymentTestSuite {

}
