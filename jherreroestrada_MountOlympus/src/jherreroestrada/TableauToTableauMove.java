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
			dest.add(temp.get());
		}

		return true;	
	}

	/**
	 * This method returns the column that was moved to the dest.
	 */
	public boolean undo(ks.common.games.Solitaire game) {
		//Stack temp = new Stack();
		while(movedColumn.count() < size){
			movedColumn.add(dest.get());
			src.add(movedColumn.get());
		}

		return true;
	}

	public boolean valid(ks.common.games.Solitaire game) {

		Card topRef = movedColumn.peek(movedColumn.count() - 1);
		for(int i = (movedColumn.count() - 2); i > 0; i++){
			if(!movedColumn.peek(i).sameSuit(topRef) && 
					!(movedColumn.peek(i).getRank() == (topRef.getRank() - (i*2))))
				return false;
		}

		if (!dest.peek().sameColor(topRef) && 
				!(dest.peek().getRank() == (topRef.getRank() + 2))){
			return false;
		}


		return true;
	}
}
