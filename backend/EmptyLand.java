package backend;


public class EmptyLand implements GamePiece{
	public int surroundings = 0;

	public String toString(){
		return "Empty Land";
	}
	
	public String toSymbol(){
		return ((Integer) surroundings).toString();
	}
	
	public boolean isBomb(){
		return false;
	}

}
