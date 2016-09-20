package dynamics.testauto.scripts;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ValidateDynamicsIntegration.class, MicroSoftDynamicsTicket.class, VerifyReplayDynamicsTicket.class })

public class DynamicsTicketEndToEnd extends DynamicsKBUIHTMLReport {

	public static void main(String[] args) {

		JUnitCore jCore = new JUnitCore();
		jCore.run();

	}

}
