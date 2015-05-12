package ui;

import java.io.PrintStream;
import java.util.List;

public interface UIAction {
	public void execute(PrintStream output, List<String>words);
}
