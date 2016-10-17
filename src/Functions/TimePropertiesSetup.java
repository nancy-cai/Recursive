package functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TimePropertiesSetup {

	public String stime = currentTime();

	public String currentTime() {
		Date cur_tm = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String currentTime = dateFormat.format(cur_tm);
		return currentTime;

	}

	public void saveParamChanges() {
		try {
			Properties props = new Properties();
			props.setProperty("time", stime);

			File f = new File("server.properties");
			OutputStream out = new FileOutputStream(f);
			props.store(out, currentTime());
			System.out.println(stime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readFile() {
		Properties props = new Properties();
		InputStream is = null;

		// First try loading from the current directory
		try {
			File f = new File("server.properties");
			is = new FileInputStream(f);
		} catch (Exception e) {
			is = null;
		}

		try {
			if (is == null) {
				// Try loading from classpath
				is = props.getClass().getResourceAsStream("server.properties");
			}

			// Try loading properties from the file (if found)
			props.load(is);
		} catch (Exception e) {
		}

		stime = new String(props.getProperty("time", currentTime()));
		System.out.println(stime);
	}

	public String time() {

		return stime;
	}

	public static void main(String[] args) throws InterruptedException {
		TimePropertiesSetup sss = new TimePropertiesSetup();
		sss.saveParamChanges();
		Thread.sleep(4000);
		sss.readFile();
		Thread.sleep(4000);
		System.out.println(sss.time());
	}

}
