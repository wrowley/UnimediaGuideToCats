package term;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import evnt.InputEvent;
import evnt.InputEventSource;

public class TermPanel extends JPanel implements ActionListener, KeyListener {

	public static String INPUT_CMD = "input";

	private int lc = 0;

	final int NUM_COLS = 90;
	final int NUM_ROWS = 50;

	private final String newline = System.getProperty("line.separator");

	public String latestInput = null;

	private OutputTextArea output;
	private JTextField input;

	// Generates input events
	public InputEventSource inputSource;

	// Some keypress stuff...
	final static Integer KEY_UP = 38;
	final static Integer KEY_DOWN = 40;
	final static Integer KEY_LEFT = 37;
	final static Integer KEY_RIGHT = 39;
	final static Integer KEY_ESC = 27;
	final static Integer KEY_F1 = 112;

	public static Map<Integer, String> kMap;

	public TermPanel(Component parentComponent) {

		this();

	}

	public TermPanel() {
		super(new BorderLayout());

		kMap = new HashMap<Integer, String>();
		kMap.put(KEY_UP, "up");
		kMap.put(KEY_DOWN, "down");
		kMap.put(KEY_LEFT, "left");
		kMap.put(KEY_RIGHT, "right");
		kMap.put(KEY_ESC, "esc");
		kMap.put(KEY_F1, "f1");

		output = new OutputTextArea(null, null, NUM_ROWS, NUM_COLS);
		output.setEditable(false);

		input = new JTextField(NUM_COLS);
		input.setEditable(true);
		input.setActionCommand(INPUT_CMD);
		input.addActionListener(this);
		input.addKeyListener(this);

		output.setBackground(Color.BLACK);
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
		output.setFont(font);
		output.setForeground(Color.LIGHT_GRAY);

		input.setBackground(Color.BLACK);
		input.setFont(font);
		input.setForeground(Color.LIGHT_GRAY);

		add(output, BorderLayout.NORTH);
		add(input, BorderLayout.SOUTH);

		inputSource = new InputEventSource();

	}

	public void requestInputFocus() {

		input.requestFocusInWindow();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();

		System.out.println(e);

		if (INPUT_CMD.equals(command)) {
			// something occurs in input box maybe..?
			inputSource.fireInputEvent(new InputEvent(this, input.getText()));
			input.setText(null);
		}

	}

	private void writeln(String text) {
		try {
			output.addLine(text);
		} catch (BadLocationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	public void write(String text) {

		String lines[] = text.split("\\r?\\n");

		for (int i = 0; i < lines.length; i++) {
			writeln(lines[i]);
		}
	}

	public void clear() {
		try {
			output.clear();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
//		System.out.println(e);
		if (kMap.containsKey(e.getKeyCode())) {
			inputSource.fireInputEvent(new InputEvent(this, kMap.get(e.getKeyCode()), InputEvent.KEY_PRESS));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
//		System.out.println(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
//		System.out.println(e);
	}

}
