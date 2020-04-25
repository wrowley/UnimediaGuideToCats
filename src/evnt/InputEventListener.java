package evnt;

import java.util.EventListener;

public interface InputEventListener extends EventListener {

	public void userInputOccurred(InputEvent e);

}
