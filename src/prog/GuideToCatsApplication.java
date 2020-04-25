package prog;

import javax.swing.JFrame;

import evnt.InputEvent;
import evnt.InputEventListener;

import term.TermPanel;

public class GuideToCatsApplication extends JFrame implements InputEventListener {

	static TermPanel terminal;
	static GuideToCatsProgram guide;

	public GuideToCatsApplication(String name) {
		// Calls the constructor of Frame with 'name'
		super(name);

	}

	public static void start(String title) {

		// Open the application (this)
		GuideToCatsApplication frame = new GuideToCatsApplication(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Initialise a terminal
		terminal = new TermPanel();

		// Add the terminal to the frame and take care of focus
		frame.add(terminal);
		frame.setResizable(false);
		frame.pack();
		terminal.requestInputFocus();
		frame.setVisible(true);

		// Start the program and give it the terminal
		guide = new GuideToCatsProgram(terminal);
		// Listen for quitting
		guide.inputSource.addUserInputEventListener(frame);

		// Start the guide!
		guide.start();

	}

	public static void main(String args[]) {
		// Give it a boring title
		GuideToCatsApplication.start("UNIMEDIA Guide To Cats");

	}

	public void quit() {
		// Presumably means I have received a close signal
		this.dispose();

	}

	@Override
	public void userInputOccurred(InputEvent e) {
		// TODO Auto-generated method stub
		if (e.getType() == InputEvent.SIGNAL) {
			if (e.getInput().equals("quit")) {
				this.quit();
			}
		}

	}

}
