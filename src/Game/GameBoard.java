package Game;

import java.util.ArrayList;

import prog.GameProgram;

public class GameBoard {
	
	static final int NX = 20;
	static final int NY = 20;
	
	private Tile[][] backBoard;
	private Tile[][] frontBoard;
	private CatTile cat;
	private MouseTile mouse;
	private ArrayList<DogTile> dogs;
	
	private Integer turnCount;
	public Integer score;
	private GameProgram parent;

	
	public GameBoard() {
		
//		this.cat = cat;
    	
        this.init();
	}
	
	
	public GameBoard(GameProgram gameProgram) {
		// TODO Auto-generated constructor stub
		this();
		this.parent = gameProgram;
	}


	public void init() {
		
		
		backBoard = createDeepCopyBackBoard();
		frontBoard = createDeepCopyBackBoard();

		
		//Choose the mouse position
		mouse = generateMouse();
		frontBoard[mouse.getxPos()][mouse.getyPos()] = mouse;
		
		//Generate dog array list
		dogs = new ArrayList<DogTile>();
		
		turnCount = new Integer(0);
		score = 0;
		
		
	}
	
	
	public Tile[][] createDeepCopyBackBoard() {
		
		Tile[][] temp = new Tile[NX][NY];
		
		for (int x = 0; x < NX; x++) {
	    	for (int y = 0; y <  NY; y++) {
    		    temp[x][y] =  new GrassTile(this,x,y);
			}
		}
		
		//Make the wall
		for (int x = 0; x < NX; x+= NX - 1) {
			for (int y = 0; y <  NY; y++) {
				temp[x][y] = new StoneTile(this,x,y);
			}
		}
		for (int x = 0; x < NX; x++) {
			for (int y = 0; y <  NY;  y += NY - 1) {
				temp[x][y] = new StoneTile(this,x,y);
			}
		}
		
		return temp;
		
	}
	
	public void placeCat(CatTile cat) {
		
		//Own the cat
		this.cat = cat;
		//Choose the cat position
		int xStart = (int) java.lang.Math.round(java.lang.Math.random()*(NX-4)) + 1;
		int yStart = (int) java.lang.Math.round(java.lang.Math.random()*(NY-4)) + 1;
		cat.goToPos(xStart, yStart);
		
		System.out.println(cat.getxPos() + "" + cat.getyPos());
	}
	
	
	public String getBoardString() {
		
		String lineString;
		
		String boardString = "";
		
		for (int y = NY - 1; y >= 0 ; y--) {
			lineString = new String("");
			for (int x = 0; x <  NX; x++) {
				
				String icon = new String(frontBoard[x][y].getIcon());
				lineString = new String(lineString+icon);
			}
			
			
			boardString = new String(boardString + lineString + '\n');
		}
		
		
		return boardString;
	}
	
	public String getScoreString() {
		
		return Integer.toString(score);
		
	}
	
	
	public MouseTile generateMouse() {
		
		int xStart = (int) java.lang.Math.round(java.lang.Math.random()*(NX-4)) + 1;
		int yStart = (int) java.lang.Math.round(java.lang.Math.random()*(NY-4)) + 1;
		mouse = new MouseTile(this, xStart, yStart);
		
		return mouse;
		
	}
	
	
	public DogTile generateDog() {
		
		int xStart = 0;
        int yStart = 0;	
        
        while (!validSpawnPosition(xStart,yStart)) {
    		xStart = (int) java.lang.Math.round(java.lang.Math.random()*(NX-4)) + 1;
	    	yStart = (int) java.lang.Math.round(java.lang.Math.random()*(NY-4)) + 1;
        }
		
        DogTile dog = new DogTile(this, xStart, yStart);
		
		return dog;
		
	}
	
	public boolean validSpawnPosition(int desiredX, int desiredY) {
		
		//Outside x bounds?
		if ((desiredX >= NX) || (desiredX < 0)){
			return false;
		}
		
		//Outside y bounds?
		if ((desiredY >= NY) || (desiredY < 0)){
			return false;
		}
		
		//Is it passable?
		return (this.isPassable(desiredX,desiredY));
	}


	public void update() {
		//Initialise the frontboard
		frontBoard = createDeepCopyBackBoard();
		
		implementMouseAI();
		
		implementDogsAndAI();
		
		frontBoard[mouse.getxPos()][mouse.getyPos()] = mouse;
		frontBoard[cat.getxPos()][cat.getyPos()] = cat;
		
		DogTile dog;
		for (int i = 0; i < dogs.size(); i++) {
			dog = dogs.get(i);
			frontBoard[dog.getxPos()][dog.getyPos()] = dog;
		}

		
		for (int i = 0; i < dogs.size(); i++) {
			dog = dogs.get(i);
			if ((dog.getxPos() == cat.getxPos() && (dog.getyPos() == cat.getyPos()))){
			    parent.quit("You were eaten by a dog!!!");
			    return;
			}
		}

		
		
		if (cat.sharesLocation(mouse)) {
			score++;
			mouse = generateMouse();
		}
		
		turnCount++;
		
	}
	
	
	private void implementDogsAndAI() {
		// TODO Auto-generated method stub
		DogTile dog;
		
		if (turnCount%40 == 0){
			
			dog = generateDog();
			dogs.add(dog);
			
		}
		
		for (int i = 0; i < dogs.size(); i++) {
			dogs.get(i).moveRandom();
		}
		
	}


	public void implementMouseAI(){
		
		int xMove = mouse.getxPos() - cat.getxPos();
		int yMove = mouse.getyPos() - cat.getyPos();
		
		if (turnCount%2 == 0) {
			if (java.lang.Math.abs(yMove) > java.lang.Math.abs(xMove)) {
				if (yMove > 0){
					mouse.moveUp();
				} else {
					mouse.moveDown();
				}
			} else {
				if (xMove > 0) {
					mouse.moveRight();
				} else {
					mouse.moveLeft();
				}
			}
			
		}
		
		
	}
	
	public boolean isPassable(int x, int y) {
		return frontBoard[x][y].passable;
	}
	

	
}

