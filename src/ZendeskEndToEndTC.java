package zendesk.testauto.scripts;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ValidateZendeskIntegrationTC.class, RecordZendeskTicketTC.class, VerifyZendeskTicket.class })
public class ZendeskEndToEndTC {
	public static void main(String[] args) throws Exception {
		JUnitCore.main("zendesk.testauto.scripts.ZendeskEndToEndTC");
		/*
		 * JUnitCore jCore = new JUnitCore(); jCore.run();
		 */

	}

}
