package backend;

public class Bomb implements GamePiece{
	public String toString(){
		return "Bomb";
	}
	
	public String toSymbol(){
		return "*";
	}
	
	public boolean isBomb(){
		return true;
	}

}
