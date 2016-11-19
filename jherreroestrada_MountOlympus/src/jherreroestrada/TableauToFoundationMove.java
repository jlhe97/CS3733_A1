package jherreroestrada;

import ks.common.games.Solitaire;
import ks.common.model.*;

public class TableauToFoundationMove extends Move{

	Pile dest;
	Column src;
	Column draggedElt;
	
	public TableauToFoundationMove(Column src, Column draggedElt, Pile dest) {
		this.src = src;
		this.dest = dest;
		this.draggedElt = draggedElt;
		
	}

	@Override
	public boolean doMove(Solitaire game) {
		if (!valid(game)) { 
			return false; 
		}
		dest.push(draggedElt);
		game.updateScore(+1);
		return true;
	}

	@Override
	public boolean undo(Solitaire game) {
		src.add(dest.get());
		game.updateScore(-1);
		return true;
	}

	@Override
	public boolean valid(Solitaire game) {
		if (draggedElt.count() != 1) // you can only move one card at a time from the tableau to the foundation
			return false;
		if(!draggedElt.peek().sameSuit(dest.peek())) // they have to be the same color and suit (if they aren't same suit, then not same color)
			return false;
		if(!(draggedElt.peek().getRank() - 2 == dest.peek().getRank())) // the incoming card has to be 2 ranks larger than the top of the pile.
			return false;


		return true;
	}

}
