package dynamics.testauto.scripts;


import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ValidateDynamicsIntegrationTS.class, MicroSoftDynamicsRecTS.class,  VerifyReplayRecAtDynamics.class })

public class DynamicsReplayUIAutomation extends DynamicsKBUIHTMLReport{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JUnitCore jCore = new JUnitCore();
	     jCore.run();
	     
	     /*System.out.println("Opening the report generated");
	     String url_open ="C:/Users/lstr/workspace/SeleniumTest/junit/index.html";
	     try {
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
