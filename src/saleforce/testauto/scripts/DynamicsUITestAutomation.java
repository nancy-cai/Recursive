package saleforce.testauto.scripts;


import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import dynamics.testauto.scripts.MicroSoftDynamicsRecTS;
import dynamics.testauto.scripts.ValidateDynamicsIntegrationTS;
import dynamics.testauto.scripts.VerifyReplayRecAtDynamics;

@RunWith(Suite.class)
@SuiteClasses({ ValidateDynamicsIntegrationTS.class, MicroSoftDynamicsRecTS.class,  VerifyReplayRecAtDynamics.class })
public class DynamicsUITestAutomation {
	public static void main(String[] args) throws Exception {                    
	       /*JUnitCore.main(
	         "com.stackoverflow.MyTestSuite");  */   
		JUnitCore jCore = new JUnitCore();
     jCore.run();
	}
}
