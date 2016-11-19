package jherreroestrada;
import ks.common.model.Column;


public class TableauToTableauMove extends ks.common.model.Move{

	protected Column src; // the source column
	protected Column dest; // the destination column
	
	public TableauToTableauMove(Column src, Column dest){
		super();
		this.src = src;
		this.dest = dest;
	}
	
	/**
	 * Do Move
	 * @param theGame ks.games.Solitaire 
	 */
	public boolean doMove (ks.common.games.Solitaire game) {
		// VALIDATE
		if (valid(game) == false)
			return false;
		
		dest.push(src);
		
		return true;	
	}
	
	/**
	 * This method returns the column that was moved to the dest.
	 */
	public boolean undo(ks.common.games.Solitaire game) {
		src.add(dest.get());
		return true;
	}
	
	public boolean valid(ks.common.games.Solitaire game) {
		return true; // change this later.
	}
}
