package Game;

public class MouseTile extends CharTile {

	public MouseTile(GameBoard gameBoard, int x, int y) {
		super(gameBoard, x, y);
		// TODO Auto-generated constructor stub
		this.icon = " m ";
		this.passable = true;
		
	}

}
