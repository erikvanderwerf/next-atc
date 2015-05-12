package server;

import java.io.PrintStream;
import java.util.ArrayList;
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
	List<Type> types;
	
	Map<String, Object> settings;
	
	private Server(List<String> strings) {
		settings = new HashMap<>();
		setDefaultSettings();
		
		for (int i=1; i + 1 < strings.size(); i += 2) {
			settings.put(strings.get(i), strings.get(i + 1));
		}
		
		types = Type.loadTypes((String) settings.get("location"));
	}
	
	private void setDefaultSettings() {
		settings.put("location", "");
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

	public Map<? extends String, ? extends UIAction> getCommands() {
		Map<String, ? extends UIAction> commands = new HashMap<>();
		
		return commands;
	}

	public static Server getServer() {
		return server;
	}
	
	private class Add implements UIAction {
		@Override
		public void execute(PrintStream output, List<String> words) {
			
		}
		
	}
}
