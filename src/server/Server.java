package server;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ui.UIAction;
import aircraft.Aircraft;
import aircraft.Type;

public class Server implements Runnable {
	private static Server server;
	private volatile boolean running;
	
	List<Aircraft> aircraft;
	
	Map<String, Object> settings;
	Map<String, Type> types;
	
	private Server(List<String> strings) {
		settings = new HashMap<>();
		setDefaultSettings();
		
		for (int i=1; i + 1 < strings.size(); i += 2) {
			settings.put(strings.get(i), strings.get(i + 1));
		}
		
		try {
			types = Type.loadTypes((String) settings.get("location"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setDefaultSettings() {
		settings.put("location", "");
		settings.put("timestep", 1f);
	}

	public static short create(List<String> strings) {
		short status;
		if (server != null)
			status = 1;
		else {
			server = new Server(strings);
			new Thread(server).start();
			status = 0;
		}
		return status;
	}
	
	public static short destroy() {
		System.out.println("Destroy");
		if (server != null) {
			server.running = false;
			server = null;
		}
		return 0;
	}
	
	@Override
	public void run() {
		running = true;
		while(running) {
			running = false;
		}
	}
	
	
	public Map<? extends String, UIAction> getCommands() {
		Map<String, UIAction> commands = new HashMap<>();
		commands.put("add", new Add());
		return commands;
	}

	public static Server getServer() {
		return server;
	}
	
	private class Add implements UIAction {
		@Override
		public void execute(PrintStream output, List<String> words) {
			output.println(words);
			if (words.size() == 1) {
				//TODO Print instructions
			} else if (words.get(1).equals("aircraft")) {
				Type type = types.get(words.get(2));
				Aircraft aircraft = new Aircraft(type);
				Server.this.aircraft.add(aircraft);
			} else {
				output.println("Add Command not valid");
			}
		}
		
	}
}
