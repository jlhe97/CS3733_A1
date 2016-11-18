package jherreroestrada;

import ks.common.games.Solitaire;
import ks.common.model.*;

public class TableauToFoundationMove extends Move{

	Pile dest;
	Column src;

	public TableauToFoundationMove(Column src, Pile dest) {
		this.src = src;
		this.dest = dest;
	}

	@Override
	public boolean doMove(Solitaire game) {
		if (!valid(game)) { 
			return false; 
		}
		dest.push(src);
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
		if (src.count() != 1)
			return false;
		
		return true; // change this later
	}

}
