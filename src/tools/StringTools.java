package tools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTools {
	public static List<String> splitIgnoreQuotes(String string) {
		List<String> strings = new ArrayList<String>();
		Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(string);
		while (m.find())
			strings.add(m.group(1).replace("\"", ""));
		return strings;
	}
}
