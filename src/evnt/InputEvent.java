package evnt;

import java.util.EventObject;

public class InputEvent extends EventObject {

	public final static int TEXT = 0;
	public final static int KEY_PRESS = 1;
	public final static int SIGNAL = 2;

	private String text;
	private int type;

	public InputEvent(Object source) {
		this(source,"",TEXT);
	}

	public InputEvent(Object source, String text) {
		this(source,text,TEXT);
	}

	public InputEvent(Object source, String text, int type) {
		super(source);
		this.text = text;
		this.type = type;
	}

	public String getInput() {
		return text;
	}

	public int getType() {
		return type;
	}

}
