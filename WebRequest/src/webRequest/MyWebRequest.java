package webRequest;

import java.util.regex.*;
import java.net.*;
import java.io.*;

public class MyWebRequest {

	public static void main(String[] args) {

		String webSite = "http://vnexpress.net";

		// Create a Pattern object
		String pattern = "(src|href)=\"((http|/).*?)\"";
		Pattern r = Pattern.compile(pattern);

		try {
			URL url = new URL(webSite);
			URLConnection conn = url.openConnection();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				// Create a Matcher object
				Matcher m = r.matcher(inputLine);
				if (m.find()) {
					// System.out.println(inputLine);
					if (m.group(2).startsWith("http"))
						System.out.println(m.group(2));
					else
						System.out.println(webSite + m.group(2));
				}
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
