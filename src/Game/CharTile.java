package Game;

public class CharTile extends Tile {
	
	GameBoard parent;
	private int xPos;
	public int getxPos() {
		return xPos;
	}
	private int yPos;
	public int getyPos() {
		return yPos;
	}


	
	public CharTile(GameBoard gameBoard, int x, int y) {
		
		parent = gameBoard;
		xPos = x;
		yPos = y;
		
	}
	
	public void moveRandom() {
		
		double xRandDouble = 2*(java.lang.Math.random()-0.5);
		double yRandDouble = 2*(java.lang.Math.random()-0.5);
		
		int xRand = (int) java.lang.Math.round(xRandDouble);
		int yRand = (int) java.lang.Math.round(yRandDouble);
		
		int newY = yPos + xRand;
		int newX = xPos + yRand;
		
		if (parent.isPassable(newX,newY)) {
			if (((newY < GameBoard.NY) && (newY >= 0)) && ((newX < GameBoard.NX) && (newX >= 0))) {
				xPos = newX;
				yPos = newY;
			}
		}
		
//		System.out.println("randomx..." + xRandDouble + " " + xRand);
	}
	
	public void moveUp() {
		
		int newY = yPos + 1;
		
		if ((newY < GameBoard.NY) && (parent.isPassable(xPos,newY))){
			yPos += 1;
		}
		
	}
	
    public void moveDown() {
		
    	int newY = yPos - 1;
    	
		if ((newY >= 0) && (parent.isPassable(xPos,newY))) {
			yPos -= 1;
		}
		
	}
    
    public void moveLeft() {
		
    	int newX = xPos - 1;
    	
    	if ((newX >= 0) && (parent.isPassable(newX,yPos))) {
        	xPos -= 1;
    	}
		
		
	}
    
    public void moveRight() {
    	
    	int newX = xPos + 1;
    	
    	if ((newX < GameBoard.NX) && (parent.isPassable(newX,yPos))) {
        	xPos += 1;
    	}
    }
    
    public void goToPos(int x, int y) {
    	
    	xPos = x;
        yPos = y;
    	
    }
    
    
    public boolean sharesLocation(CharTile otherTile) {
    	return ((this.xPos == otherTile.xPos) && (this.yPos == otherTile.yPos));
    }



}
