package tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileParser<T extends FileDefined > {
	private Path path;
	private Class<T> type;
	
	public FileParser(Class<T> type) {
		
	}
	
	public FileParser(Class<T> type, Path path) {
		this.path = path;
	}
	
	public FileParser(Class<T> type, String path) {
		this.path = Paths.get(path);
	}
	
	public List<T> parse() throws IOException {
		return parse(path);
	}
	
	public List<T> parse(Path path) throws IOException {
		List<T> parsed_objects = new ArrayList<T>();
		
		try {
			T current = type.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		for (String line: Files.readAllLines(path)) {
			System.out.println(line);
		}
		
		return parsed_objects;
	}
}
