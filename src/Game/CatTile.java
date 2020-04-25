package Game;

public class CatTile extends CharTile {

	public CatTile(GameBoard gameBoard, int x, int y) {
		super(gameBoard, x, y);
		// TODO Auto-generated constructor stub
		this.icon = " @ ";
		
	}

	public CatTile(GameBoard gameBoard) {
		// TODO Auto-generated constructor stub
		this(gameBoard,1,1);
	}

	
	
}
