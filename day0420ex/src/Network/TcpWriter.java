package Network;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpWriter implements Runnable {
	private Socket socket;

	public TcpWriter(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try (BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(socket.getOutputStream()))) {
			Scanner sc = new Scanner(System.in);
			while (true) {
				bw.write(sc.nextLine());
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}