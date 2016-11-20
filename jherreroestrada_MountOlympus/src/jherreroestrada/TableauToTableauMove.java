package jherreroestrada;
import ks.common.model.*;


public class TableauToTableauMove extends ks.common.model.Move{

	protected Column src; // the source column
	protected Column dest; // the destination column
	protected Column movedColumn; // the moved column
	protected int size; // size of the column

	public TableauToTableauMove(Column dest, Column movedColumn, Column src, int size){
		super();
		this.dest = dest;
		this.movedColumn = movedColumn;
		this.src = src;
		this.size = size;
	}

	/**
	 * Do Move
	 * @param theGame ks.games.Solitaire 
	 */
	public boolean doMove (ks.common.games.Solitaire game) {

		if (valid(game) == false)
			return false;

		Stack temp = new Stack();
		while (!movedColumn.empty()){
			temp.add(movedColumn.get());
		}
		
		while (!temp.empty()){
			dest.add(temp.get());
		}

		return true;	
	}

	/**
	 * This method returns the column that was moved to the dest.
	 */
	public boolean undo(ks.common.games.Solitaire game) {
		while(movedColumn.count() < size){
			movedColumn.add(dest.get());
		}
		
		while (!movedColumn.empty()){
			src.add(movedColumn.get());
		}

		return true;
	}

	public boolean valid(ks.common.games.Solitaire game) {
		
		// if the column moved is not all same color and suit, then not valid
		if (!movedColumn.sameSuit()){ 
			return false;
		}
		
		// If column not same color and suit as top card from column, then not valid
		if(!movedColumn.peek(movedColumn.count() - 1).sameSuit(dest.peek())){
			return false;
		}
		
		// The bottom most card of the column moved has to be 2 ranks lower than 
		// the top card of the destination column.
		// movedColumn.peek(0) returns the bottom most card.
		if (!(movedColumn.peek(0).getRank() == (dest.peek(dest.count() - 1).getRank() - 2)))
			return false;
		
		// this part of the method checks if all of the cards in the column being moved
		// are 2 ranks smaller than the next one.
		Card topRef = movedColumn.peek(0);
		for(int i = 1; i < movedColumn.count() - 1 ; i++){
			if(!movedColumn.peek(i).sameSuit(topRef) && 
				!(movedColumn.peek(i).getRank() == (topRef.getRank() - (i*2))))
  				 return false;
		}
		
		return true;
	}
}
