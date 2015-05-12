import ui.CommandLineInterface;


public class NextATC {
	static CommandLineInterface cli;
	
	public static void main(String[] args) {
		cli = new CommandLineInterface(System.in, System.out);
		cli.run();
	}
}
