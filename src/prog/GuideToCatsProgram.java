package prog;

import java.io.File;

import prog.TextFileReader;
import term.TermPanel;
import evnt.InputEvent;
import evnt.InputEventListener;
import evnt.InputEventSource;

public class GuideToCatsProgram implements InputEventListener {

	static final int ROOT_MENU = -1;
	static final int LEARN = 0;
	static final int GAME = 1;
	static final int EXIT = 2;

	int current_state = ROOT_MENU;

	static final String versionNum = "0.6a";
	static final String author = "William Rowley";
	static String splashString;

	private int highScore = 0;

	String message = null;

	// Parent frame
//	GuideToCatsApplication myFrame;
//	GuideToCatsApplet myApplet;

	// Component parent..?

	// Terminal
	TermPanel myTerminal;

	// Child programs
	LearningProgram learningProgram;
	GameProgram gameProgram;

	// For sending quit signal
	public InputEventSource inputSource = new InputEventSource();

	public GuideToCatsProgram(TermPanel terminal) {
		// TODO Auto-generated constructor stub
		myTerminal = terminal;

		this.init();

		learningProgram = new LearningProgram(this, terminal);
//		gameProgram = new GameProgram(this);

	}

	private void init() {
		// TODO Auto-generated method stub

		myTerminal.inputSource.addUserInputEventListener(this);
//		textSource.addUserInputEventListener(this);

	}

	public void start() {
		// TODO Auto-generated method stub
		File splashFile = new File("Splash", "title");
		splashString = TextFileReader.getContents(splashFile);
		sendOutput(splashString);
		sendOutput("Version " + versionNum);
		sendOutput("by  " + author);
		printMenu();
	}

	public int cmd_string(String input) {

		// If we're in the learning program
		if (current_state == LEARN) {
			// By this point learn has hopefully been told its parent and how to send...
			current_state = learningProgram.cmd_string(input);
		}

		// If we are still exploring the root menu...
		if (current_state == ROOT_MENU) {
//			System.out.print("\""+input+"\" \""+Integer.toString(LEARN)+"\"\n");
			if (input.equals(Integer.toString(LEARN))) {
				clearTerminal();
				sendOutput("You have chosen to learn!");
				learningProgram.printMenu();
				current_state = LEARN;
				message = null;
			} else if (input.equals(Integer.toString(GAME))) {
				clearTerminal();
				if (gameProgram == null) {
					gameProgram = new GameProgram(this);
					System.out.println("New game created: " + gameProgram);
				}
//				send("You have chosen to play a game!");
				// String newString = new String();
				// send(Integer.toString(newString.length()));
				sendOutput(gameProgram.getBoardString());
				sendOutput("");
				sendOutput("Score: " + gameProgram.getScoreString());
				sendOutput("");
				sendOutput(gameProgram.getControlString());
				sendOutput("");
				sendOutput(gameProgram.getKeyString());
				current_state = GAME;
				message = null;
			} else if (input.equals(Integer.toString(EXIT))) {
				clearTerminal();
				sendOutput("You have chosen to quit!");
				current_state = EXIT;
				message = null;
				// Signal to quit
				inputSource.fireInputEvent(new InputEvent(this, "quit", InputEvent.SIGNAL));
//				myFrame.quit();
			}
		}

		if (current_state == ROOT_MENU) {
			clearTerminal();
			if (message != null) {
				sendOutput(message);
//				message = null;
			}
			sendOutput(splashString);

			sendOutput("Version " + versionNum);
			sendOutput("by  " + author);

//			send("Main menu");
			this.printMenu();
		}

		if (current_state == EXIT) {
			return this.EXIT;
		}

		return this.ROOT_MENU;

	}

	public void clearTerminal() {
		myTerminal.clear();
	}

	public int cmd_key(String keyPressed) {

		System.out.println(keyPressed + " pressed");
		if (current_state == GAME) {

//			if (gameProgram == null) {
			// gameProgram = new GameProgram(this);
			// System.out.println("New game created: " + gameProgram);
			// }

//By this point game has hopefully been told its parent and how to send...
			current_state = gameProgram.cmd_key(keyPressed);
			clearTerminal();
			// String newString = new String();
			// send(Integer.toString(newString.length()));
			sendOutput(gameProgram.getBoardString());
			sendOutput("");
			sendOutput("Score: " + gameProgram.getScoreString());
			sendOutput("");
			sendOutput(gameProgram.getControlString());
			sendOutput("");
			sendOutput(gameProgram.getKeyString());

//			current_state = GAME;
			if (gameProgram.QUIT_CONDITION) {
				quitGame(gameProgram.getScore());
			}
		}
//		System.out.println(current_state);

		if (current_state == ROOT_MENU) {

			clearTerminal();
			if (message != null) {
				sendOutput(message);
//				message = null;
			}
			sendOutput(splashString);

			sendOutput("Version " + versionNum);
			sendOutput("by  " + author);

//			send("Main menu");
			this.printMenu();
		}

		return 0;
	}

	public void printMenu() {

		sendOutput("");
		sendOutput("(" + LEARN + ") " + "Learn about cats");
		sendOutput("(" + GAME + ") " + "Play a cat game (High Score: " + highScore + ")");
		sendOutput("(" + EXIT + ") " + "Exit");

	}

	public void sendOutput(String input) {
//		textSource.fireInputEvent(new InputEvent(this,input));
		myTerminal.write(input);
	}

	public void quitGame(int score) {

		sendOutput("Quitting Game");
		if (score > highScore) {
			highScore = score;
		}
		message = new String(gameProgram.quitString);
		current_state = this.ROOT_MENU;
		gameProgram = null;
	}

	@Override
	public void userInputOccurred(InputEvent e) {

		// If it's a text command
		if (e.getType() == (InputEvent.TEXT)) {

			// TODO Auto-generated method stub
			String input = new String(e.getInput());
			// If it's a clear command
			if (e.getInput().equals("/clear")) {
				clearTerminal();
			} else {
				if (e.getSource() == myTerminal) {
					// System.out.println("Terminal says: "+input);
					this.cmd_string(input);
				} else if (e.getSource() == this) {
					myTerminal.write(input);
				} else {
					System.out.println("Unknown src detected: " + e.getSource());
				}
			}

			if (current_state == EXIT) {
				quit();
			}

			// Otherwise it's a up/down/left/right/esc command
		} else if (e.getType() == (InputEvent.KEY_PRESS)) {

			this.cmd_key(e.getInput());

		}

	}

	private void quit() {
		// TODO Auto-generated method stub

	}

}
