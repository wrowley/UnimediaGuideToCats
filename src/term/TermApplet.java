package term;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

import evnt.InputEvent;
import evnt.InputEventListener;

public class TermApplet extends JApplet implements InputEventListener {

	TermPanel terminal;

	// Called when this applet is loaded into the browser.
	public void init() {
		// Execute a job on the event-dispatching thread; creating this applet's GUI.
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					createGUI();
				}
			});
		} catch (Exception e) {
			System.err.println("createGUI didn't complete successfully");
		}
	}

	private void createGUI() {
		// Create and set up the content pane.
		terminal = new TermPanel();
		terminal.setOpaque(true);
		setContentPane(terminal);

		this.setSize(terminal.getPreferredSize());
		terminal.inputSource.addUserInputEventListener(this);

	}

	@Override
	public void userInputOccurred(InputEvent e) {
		String input = new String(e.getInput());
		System.out.println(input);
		terminal.write(input);
	}
}
