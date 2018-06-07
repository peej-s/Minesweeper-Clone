package ui;

import backend.*;
import java.util.Scanner;

// Converts a Minesweeper Backend Game into a playable text version 

public class PrintMinesweeper {
	
	public static void printGrid(GamePiece[][] grid){
		//Needs to be modified for two-digit dimensions
		System.out.print("   ");
		for (int k = 0; k < grid.length; k++){
			System.out.print(k + " ");
		}
		System.out.println(" X\n");    
		
		for (int j = 0; j < grid[0].length; j++){
			System.out.print(j + "  ");
			for (int i = 0; i < grid.length; i++){
		    	if (grid[i][j] == null){
		    		System.out.print("? ");
		    	}
		        else{
		        	System.out.print(grid[i][j].toSymbol() + " ");
		        }
		    }
		    System.out.println();
		}
		System.out.println("\nY\n");
	}

	public static void playGame(Minesweeper game){

		int column, row;
		
		String move = null;
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		
		System.out.println(game.play()); // Start Game
		
		printGrid(game.getGameMap()); System.out.println(); //Remove this line for Production
		
		do{ // Every turn
			printGrid(game.getSurfaceGrid());
			
			try{
			    System.out.println("Enter a move (X): ");
			    move = reader.nextLine(); // Scans the next token of the input as an int.
			    column = Integer.parseInt(move);
			    
				System.out.println("Enter a move (Y): ");
			    move = reader.nextLine(); // Scans the next token of the input as an int.
			    row = Integer.parseInt(move);
			    
			    System.out.println(game.makeMove(column, row));
			}
			catch (NumberFormatException e){
				if(move.toLowerCase().startsWith("q")){
					System.out.println("Thanks for Playing!");
					return;
				}
				else if(move.toLowerCase().startsWith("r")){
					System.out.println(game.resetGame());
				}
				else{				
					System.out.println("Incorrect input!\n");
				}
			}
			catch (ArrayIndexOutOfBoundsException e) {
			    System.out.println("Input out of Range!\n");
			}
		    
		} while(game.getStatus() && !game.getWin());
		
		printGrid(game.getSurfaceGrid());
		
		if(game.getWin()){
			System.out.println("You win!");	
		}
		else{
			System.out.println('\n' + "nice try!");
		}
		
	    reader.close();

	}
	
	public static void main(String[] args){
		int columns = 5;
		int rows = 8;
		int bombs = 0;
		Minesweeper game = new Minesweeper(columns, rows, bombs);
		
		playGame(game);
		
		}
}
