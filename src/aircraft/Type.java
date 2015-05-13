package aircraft;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Type {
	private String icao;
	private int thrust;

	public Type() {
		
	}
	
	/**
	 * Load the predefined types into the simulation.
	 * @param root The root directory of the simulation files.
	 * @return List of types that can be used in the simulation.
	 * @throws IOException 
	 */
	public static Map<String, Type> loadTypes(String root) throws IOException {
		Map<String, Type> types = new HashMap<>();
		Type current = null;
		
		Path path = Paths.get(root).resolve("res/Types");
		
		for (Path file: Files.newDirectoryStream(path)) {				
			for (String line: Files.readAllLines(file)) {
				String[] words = line.split("\\s+");
				if (words[0].equals("T1")) {
					if (current != null)
						types.put(current.icao, current);
					current = new Type();
					current.icao = words[1];
				} else if (words[0].equals("T2")) {
					current.thrust = Integer.parseInt(words[1]) * Integer.parseInt(words[2]);
				}
			}
		}
		if (current != null)
			types.put(current.icao, current);
		return types;
	}
}
