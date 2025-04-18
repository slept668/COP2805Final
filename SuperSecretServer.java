package cop2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SuperSecretServer {

	public static void main(String[] args) {
		boolean selfDestruct = false;
		Encrypt decoderRing = new Encrypt();
		
		try {
			ServerSocket mainServer = new ServerSocket(668);
			while (!selfDestruct) {
				System.out.println("Server Started, Awaiting Agents...");
				
				Socket fieldAgent = mainServer.accept();
				
				BufferedReader agentReader = new BufferedReader(new InputStreamReader(fieldAgent.getInputStream()));
				String input = agentReader.readLine();
				String decodedInput = decoderRing.Decrypter(input);
				
				if (decodedInput.toLowerCase().contains("shutdown") || input.toLowerCase().contains("shutdown")) {
					selfDestruct = true;
				}
				
				System.out.println("Client said: " + input);
				
				PrintWriter responder = new PrintWriter(new OutputStreamWriter(fieldAgent.getOutputStream()));
				responder.println(decodedInput);
				responder.flush();
				
				fieldAgent.close();
			}
			System.out.println("This system will self destruct in...");
			for (int i = 5; i > 0; i--) {
				System.out.println(i + "...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			mainServer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
