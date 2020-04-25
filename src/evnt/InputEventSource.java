package evnt;

public class InputEventSource {

	private javax.swing.event.EventListenerList listOfListeners = new javax.swing.event.EventListenerList();

	public void addUserInputEventListener(InputEventListener listener) {
		listOfListeners.add(InputEventListener.class, listener);
	}

	public void removeUserInputEventListener(InputEventListener listener) {
		listOfListeners.remove(InputEventListener.class, listener);
	}

	public void fireInputEvent(InputEvent event) {
		Object[] listeners = listOfListeners.getListenerList();
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == InputEventListener.class) {
				((InputEventListener) listeners[i + 1]).userInputOccurred(event);
			}
		}
	}
}
