package fr.discobee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fr.discobee.interaction.command.CommandContext;
import fr.discobee.interaction.command.environement.CommandEnvironement;
import fr.discobee.interaction.command.environement.ConsoleEnvironement;
import fr.discobee.interaction.source.ConsoleSource;
import fr.discobee.message.ConsoleMessage;

public class ConsoleListener {

	private static Thread t;
	private static DiscoBee discoBee;
	private static ConsoleSource SOURCE;
	private static CommandEnvironement environement;
	
	public static void start(DiscoBee discoBeeIn) {
		environement = new ConsoleEnvironement();
		ConsoleListener.discoBee = discoBeeIn;
		SOURCE = new ConsoleSource();
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!Thread.interrupted()) {
					listen();
				}
			}
		}, "ConsoleListener");
		t.start();
	}

	public static void stop() {
		DiscoBee.logger.info("[Console] Interrupting thread..");
		t.interrupt();
		DiscoBee.logger.info("[Console] Console listener stopped.");
	}

	private static void listen() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String command = reader.readLine();
			if(!command.trim().isEmpty()) discoBee.onCommand(new CommandContext(environement, SOURCE, new ConsoleMessage(command), command));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
