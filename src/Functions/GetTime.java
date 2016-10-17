package functions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTime {

	public String currentTime() {
		Date cur_tm = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String currentTime = dateFormat.format(cur_tm);
		return currentTime;

	}
}
