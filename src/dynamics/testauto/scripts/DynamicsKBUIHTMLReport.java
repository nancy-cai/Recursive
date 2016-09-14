package dynamics.testauto.scripts;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class DynamicsKBUIHTMLReport {
  static File junitReport;
  static BufferedWriter junitWriter;
  
  @Before
  public void setUp() throws Exception {
	  String junitReportFile = System.getProperty("user.dir")
				+ "\\DynamicsUIKBReport.html";
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date date = new Date();
		junitReport = new File(junitReportFile);
		junitWriter = new BufferedWriter(new FileWriter(junitReport, true));
		junitWriter.write("<html><body>");
		junitWriter.write("<h1>Test Execution Summary - " + dateFormat.format(date)
				+ "</h1>");
  }

  @After
  public void tearDown() throws Exception {
	  junitWriter.write("</body></html>");
		junitWriter.close();
		Desktop.getDesktop().browse(junitReport.toURI());
  }

  @Rule
	public TestRule watchman = new TestWatcher() {

		@Override
		public Statement apply(Statement base, Description description) {
			return super.apply(base, description);
		}

		@Override
		protected void succeeded(Description description) {
			try {
				junitWriter.write(description.getDisplayName() + " "
						+ "success!");
				junitWriter.write("<br/>");
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
		}

		@Override
		protected void failed(Throwable e, Description description) {
			try {
				junitWriter.write(description.getDisplayName() + " "
						+ e.getClass().getSimpleName());
				junitWriter.write("<br/>");
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	};
  
}
