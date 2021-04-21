package Network;

import java.io.IOException;
import java.net.Socket;
import java.rmi.UnknownHostException;
 
public class TcpClient {
	public static void main(String[] args) {
		try (Socket socket = new Socket("127.0.0.1", 7777)) {
			Thread writer = new Thread((new TcpWriter(socket)));
			writer.start();

			Thread reader = new Thread((new TcpReader(socket)));
			reader.start();

			System.out.println("서버접속");
			writer.join();  

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("종료");
		}
	}
}
