package saleforce.testauto.scripts;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ValidateSalesforceIntegrationTC.class,RecordSalesforceTicketTC.class,
		VerifyTicketAtSalesforceInbox.class })
public class SalesforceAutoTestScript {
	public static void main(String[] args) throws Exception {                    
	       /*JUnitCore.main(
	         "com.stackoverflow.MyTestSuite");  */   
		JUnitCore jCore = new JUnitCore();
        jCore.run();
	}
}
