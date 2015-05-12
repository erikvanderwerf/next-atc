package ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import server.Server;
import tools.StringTools;

public class CommandLineInterface implements Runnable {
	private boolean running;
	private InputStream input;
	private PrintStream output;
	private Map<String, UIAction> commands;
	
	public CommandLineInterface(InputStream in, PrintStream out) {
		input = in;
		output = out;
		commands = new HashMap<>();
		setInternalCommands();
	}

	@Override
	public void run() {
		if (input == null)
			input = System.in;
		if (output == null)
			output = System.out;
		
		Scanner scanner = new Scanner(input);
		
		running = true;
		while (running) {
			output.print(">>> ");
			String input = scanner.nextLine();
			List<String> words = StringTools.splitIgnoreQuotes(input);
			commands.getOrDefault(words.get(0), new InvalidAction()).execute(output, words);
		}
		scanner.close();
	}
	
	private void setInternalCommands() {
		commands.put("exit", new Exit());
		commands.put("echo", new Echo());
		commands.put("start", new Start());
		commands.put("close", new Close());
	}
	
	private class Echo implements UIAction {
		@Override
		public void execute(PrintStream output, List<String> strings) {
			output.println(String.join(" ", strings));
		}
	}
	
	private class Exit implements UIAction {
		@Override
		public void execute(PrintStream output, List<String> strings) {
			Server.destroy();
			CommandLineInterface.this.running  = false;
			output.println("NextATC has successfully shut down");
		}
	}
	
	private class Start implements UIAction {
		@Override
		public void execute(PrintStream output, List<String> strings) {
			short status = Server.create(strings);
			commands.putAll(Server.getServer().getCommands());
		}
	}
	
	private class Close implements UIAction {
		@Override
		public void execute(PrintStream output, List<String> strings) {
			Server.destroy();
		}
	}
	
	private class InvalidAction implements UIAction {
		@Override
		public void execute(PrintStream output, List<String> strings) {
			output.println(String.format("Command %s was not recognized.", strings.get(0)));
		}
	}
}
