package prog;

import Game.CatTile;
import Game.GameBoard;

public class GameProgram {

	private CatTile cat;
	private GameBoard gameBoard;

	private GuideToCatsProgram parent;

	public boolean QUIT_CONDITION = false;
	private int score = 0;

	public String quitString = null;

	public GameProgram() {

		this.init();

	}

	public GameProgram(GuideToCatsProgram guideToCatsProgram) {
		// TODO Auto-generated constructor stub
		this();
		this.parent = guideToCatsProgram;

	}

	private void init() {

		gameBoard = new GameBoard(this);
		cat = new CatTile(gameBoard);
		gameBoard.placeCat(cat);
		gameBoard.update();

	}

	public String getBoardString() {

		return (new String(gameBoard.getBoardString()));

	}

	public String getScoreString() {

		return (new String(gameBoard.getScoreString()));

	}

	public int cmd_key(String keyCmd) {

		if (keyCmd.equals("up")) {
			cat.moveUp();
		} else if (keyCmd.equals("down")) {
			cat.moveDown();
		} else if (keyCmd.equals("right")) {
			cat.moveRight();
		} else if (keyCmd.equals("left")) {
			cat.moveLeft();
		} else if (keyCmd.equals("esc")) {

			System.out.println("quitting");
			quit("You quit!!!");
//			parent.quitGame();

			return GuideToCatsProgram.ROOT_MENU;

		} else if (keyCmd.equals("f1")) {
			System.out.println("restarting");

			this.init();

			return GuideToCatsProgram.GAME;
		}

		gameBoard.update();

		return GuideToCatsProgram.GAME;

	}

	public String getControlString() {

		String contString = new String("Controls: \n" + "Arrow keys (up,down,left,right) - move" + "\n" + "ESC - quit");

		return contString;

	}

	public String getKeyString() {

		String objective = new String("Goal: \n" + "Eat the delicious but wiley mice to get points!" + "\n"
				+ "Avoid the dangerous but stupid dogs!" + "\n\n");
		String entities = new String("Entities: \n" + "@ - cat" + "\n" + "m - mouse" + "\n" + "D - dog (careful!)"
				+ "\n" + "m - mouse" + "\n\n");
		String tiles = new String("Non-entities: \n" + ". - grass" + "\n" + "s - stone");

		return objective + entities + tiles;

	}

	public void quit(String string) {

		score = gameBoard.score;
		QUIT_CONDITION = true;
		quitString = new String(string);
//		parent.quitGame();

	}

	public int getScore() {
		return score;
	}

}
