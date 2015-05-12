package aircraft;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Type {
	
	public Type() {
		
	}
	
	/**
	 * Load the predefined types into the simulation.
	 * @param root The root directory of the simulation files.
	 * @return List of types that can be used in the simulation.
	 * @throws IOException 
	 */
	public static List<Type> loadTypes(String root) throws IOException {
		List<Type> types = new ArrayList<>();
		
		Path path = Paths.get(root).resolve("res/Types");
		
		for (Path file: Files.newDirectoryStream(path)) {				
			for (String line: Files.readAllLines(file)) {
				
			}
		}
		
		return types;
	}
}
