package Network;

import java.io.IOException;

public class TcpServerTest {
	public static void main(String[] args) {
		TcpServer tcpServer;
		try {
			tcpServer = new TcpServer(7777);
			tcpServer.runServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
