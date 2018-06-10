package backend;

/* Every grid index is a Clickable GamePiece (Bomb or EmptyLand)
 * The reset button is a Clickable
 */

// Print: Winning Algorithm, incorrect input

public class Minesweeper {

	GamePiece[][] gameMap;
	GamePiece[][] surfaceGrid;
	public int columns;
	public int rows;
	int bombs;
	int cleared = 0;
	boolean status = true; // True if game is active (user has not died)

	public Minesweeper(int columns, int rows, int bombs){
		// Initialize Game
		this.columns = columns;
		this.rows = rows;
	    this.bombs = bombs;
	    play();
	}

	// End Initializers

	private void setGrid(GamePiece[][] gameMap){
		this.gameMap = gameMap;
	}

	private void setSurfaceGrid(GamePiece[][] surfaceGrid){
		this.surfaceGrid = surfaceGrid;
	}

    private void setStatus(boolean status) {
		this.status = status;
	}

	public GamePiece[][] getGameMap(){
		return this.gameMap;
	}

	public GamePiece[][] getSurfaceGrid(){
		return this.surfaceGrid;
	}

	public boolean getStatus() {
		return this.status;
	}

	public boolean getWin() {
		return (this.columns * this.rows - this.bombs) == this.cleared;
	}

	// End Getters/Setters

	private void updateSurfaceGrid(GamePiece piece, int column, int row){
		this.surfaceGrid[column][row] = piece;
	}

	public String makeMove(int column, int row){
		// Compares a coordinate on a grid to the coordinate on the gamemap.
		// If there is no surrounding bombs, then automatically "clicks" on surrounding spaces

		if (getSurfaceGrid()[column][row] != null){
			return "Already Selected\n";
		}
		GamePiece piece = getGameMap()[column][row];
		int maxColumn = getGameMap().length;
		int maxRow = getGameMap()[0].length;

	    updateSurfaceGrid(piece, column, row);

		if(piece.isBomb()){
			setStatus(false);
			return "Game Over";
		}
		else{
			this.cleared++;
			if (((EmptyLand) piece).surroundings == 0){
				for (int i = column-1; i <= column+1; i++){
					for (int j = row-1; j <= row+1; j++){
						if ((!(i == column && row == j))
								&& (!(i < 0 || j < 0))
								&& (!(i >= maxColumn || j >= maxRow))){
							makeMove(i, j); // Makes a move on all surrounding spots (that are within the grid)
						}
					}
				}
			}
			return "Nice!";

		}
	}

	public String resetGame(){
		this.gameMap = null;
		this.surfaceGrid = null;
		this.setStatus(true);
		play();
		return "Game Reset!";
	}

	public String play(){

    	GamePiece[][] gameMap = new GamePiece[this.columns][this.rows];
    	GamePiece[][] surfaceGrid = new GamePiece[this.columns][this.rows];
    	GameInitializer initializer = new GameInitializer();

    	initializer.populateGameMap(gameMap, this.bombs);
    	initializer.populateSurfaceGrid(surfaceGrid);

    	setGrid(gameMap);
    	setSurfaceGrid(surfaceGrid);

    	return "Game Started!";
    }

}
