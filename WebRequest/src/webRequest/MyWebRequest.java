package webRequest;

import java.util.regex.*;
import java.net.*;
import java.io.*;

public class MyWebRequest {

	public static void main(String[] args) {

		String webSite = "http://www.tangthuphathoc.net/thuvienhinh/42thunhananphap-";

		// Create a Pattern object
		String pattern = "src=\"([.a-zA-Z0-9-_/]*.jpg)\"";
		Pattern r = Pattern.compile(pattern);

		for (int i = 1; i <= 42; i++) {
			try {
				URL url = new URL(webSite + i + ".htm");
				URLConnection conn = url.openConnection();

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));

				String inputLine;
				while ((inputLine = reader.readLine()) != null) {
					// Create a Matcher object
					Matcher m = r.matcher(inputLine);
					if (m.find()) {
						System.out.println("http://www.tangthuphathoc.net/thuvienhinh/" + m.group(1));
					}
				}
				reader.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
