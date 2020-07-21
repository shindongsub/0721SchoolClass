package Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd HH:mm:ss");
		Date cal = new Date();
		String cal1 = sdf.format(cal);
		System.out.println(cal1);
		return cal1;

	}

}
