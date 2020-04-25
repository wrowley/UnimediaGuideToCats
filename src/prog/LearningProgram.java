package prog;

import java.io.File;
import java.util.HashMap;

import term.TermPanel;

import Learn.Cat;

public class LearningProgram {

	GuideToCatsProgram parent = null;

	final int DORMANT = -1;
	final int ACTIVE = 0;

	public int num_cats;
	HashMap<Integer, String> menuChoices;
	HashMap<Integer, Cat> cats;

	private int state = DORMANT;

	private TermPanel myTerminal;

	public LearningProgram(GuideToCatsProgram parent) {
		super();
		this.parent = parent;
		this.init();
	}

	public LearningProgram(GuideToCatsProgram parent, TermPanel terminal) {
		// TODO Auto-generated constructor stub
		this(parent);
		this.myTerminal = terminal;

	}

	private void init() {

		// Figure out the current directory
		File currentDir = new File("");
		String currentDirString = currentDir.getAbsolutePath();

		// Figure out where cat directory is
		File catDir = new File(currentDirString, "Cats");
//		String catDirPath = catDir.getAbsolutePath();

		// Initialise the cat choices
		menuChoices = new HashMap<Integer, String>();

		File[] catFileList = catDir.listFiles();
		for (int i = 0; i < catFileList.length; i++) {
			menuChoices.put(i, catFileList[i].getName());
		}
		num_cats = catFileList.length;
		menuChoices.put(num_cats, "Back to main menu...");

		// Initialise all the cats
		cats = new HashMap<Integer, Cat>();
		for (int i = 0; i < num_cats; i++) {
			Cat cat = new Cat();
			cat.initialiseCat(catFileList[i]);
			cats.put(i, cat);

		}

	}

	public int cmd_string(String command) {

		/*
		 * if (state == DORMANT) {
		 *
		 * // printMenu(); int choice = Integer.parseInt(command);
		 *
		 *
		 * if (cats.keySet().contains(choice)) { Cat cat = cats.get(choice);
		 * send("/clear"); send(cat.getString()); printMenu(); } else if (choice ==
		 * num_cats) { state = DORMANT; return parent.ROOT_MENU; } else {
		 * System.out.println("Cat not found!!!"); } state = ACTIVE;
		 *
		 *
		 * } else if (state == ACTIVE) {
		 */

		int choice = new Integer(0);

		try {
			choice = Integer.parseInt(command);
		} catch (NumberFormatException e) {
			choice = new Integer(0);
		}

		if (cats.keySet().contains(choice)) {
			Cat cat = cats.get(choice);
			clearTerminal();
			sendOutput(cat.getString());
			printMenu();
		} else if (choice == num_cats) {
			state = DORMANT;
			return parent.ROOT_MENU;
		} else {
			sendOutput("Cat not found!!!");
		}
		state = ACTIVE;

//		}

		return 0;

	}

	public void printMenu() {
		this.sendOutput("\nPlease choose one of the following to learn about: (default 0)");
		this.sendOutput("");
		for (int i = 0; i < menuChoices.size(); i++) {
			this.sendOutput("(" + i + ") " + menuChoices.get(i));
		}
	}

	private void sendOutput(String text) {
		myTerminal.write(text);
	}

	public void clearTerminal() {
		myTerminal.clear();
	}

}
