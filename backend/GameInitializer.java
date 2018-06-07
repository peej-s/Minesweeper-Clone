package backend;

import java.util.Random;

public class GameInitializer {
	
	protected void populateGameMap(GamePiece[][] gameMap, int numBombs){
		Random rand = new Random();
		int randomColumn;
		int randomRow;
		int totalBombs = numBombs;
		
		for (int i = 0; i < gameMap.length; i++){
		    for (int j = 0; j < gameMap[0].length; j++){
		        gameMap[i][j] = new EmptyLand();
		    }
		}
		
    	while(totalBombs != 0){
		    randomColumn = rand.nextInt(gameMap.length);
		    randomRow = rand.nextInt(gameMap[0].length);
		    if (!gameMap[randomColumn][randomRow].isBomb()){
		    	gameMap[randomColumn][randomRow] = new Bomb();
	    		totalBombs--;
	    		countSurroundings (gameMap, randomColumn, randomRow, gameMap.length, gameMap[0].length);
		    }
        } // End while
	}
	
	protected void populateSurfaceGrid(GamePiece[][] surfaceGrid){
		for (int i = 0; i < surfaceGrid.length; i++){
		    for (int j = 0; j < surfaceGrid[0].length; j++){
		        surfaceGrid[i][j] = null;
		    }
		}
	}
	
	protected void countSurroundings(GamePiece[][] gameMap, int column, int row, int maxColumn, int maxRow){
		for (int i = column-1; i <= column+1; i++){
    		for (int j = row-1; j <= row+1; j++){
    			if ((!(i == column && row == j)) 
    					&& (!(i < 0 || j < 0)) 
    					&& (!(i >= maxColumn || j >= maxRow))){
    				if(!(gameMap[i][j].isBomb())){
    					((EmptyLand) gameMap[i][j]).surroundings += 1;
    				}
    			}
    		}
    	}
	}
}
