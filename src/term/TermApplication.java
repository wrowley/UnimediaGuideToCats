package term;

import javax.swing.JFrame;

import evnt.InputEvent;
import evnt.InputEventListener;

public class TermApplication extends JFrame implements InputEventListener {

	static TermPanel terminal;

	public static void start(String title) {

		TermApplication frame = new TermApplication(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		terminal = new TermPanel();
		// TermPanel ta2 = new TermPanel();

		frame.add(terminal);
		frame.pack();

		frame.setVisible(true);

		terminal.inputSource.addUserInputEventListener(frame);

	}

	public TermApplication(String name) {
		// Calls the constructor of Frame with 'name'
		super(name);

	}

	public static void main(String args[]) {
		// Give it a boring title
		TermApplication.start("William's Application");
	}

	@Override
	public void userInputOccurred(InputEvent e) {
		String input = new String(e.getInput());
		System.out.println(input);
		terminal.write(input);

	}

}
