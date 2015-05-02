package socketTutorial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;

public class MySocket {

	public static void main(String[] args) {
		//SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
		String host = "google.com";
		Socket socket;
		try {
			//socket = ssf.createSocket(host, 443);
			socket = new Socket(host, 80);

			boolean autoflush = true;
			// writer for socket
			PrintWriter out = new PrintWriter(socket.getOutputStream(),
					autoflush);

			// reader for socket
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			// send an HTTP request to the web server
			out.println("GET / HTTP/1.1");
			out.println("Accept: application/xml");
			out.println("\n");

			// read the response
			String response;
			while ((response = in.readLine()) != null) {
				System.out.println(response);
			}

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
