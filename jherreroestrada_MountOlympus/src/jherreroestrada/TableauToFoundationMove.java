package jherreroestrada;

import ks.common.games.Solitaire;
import ks.common.model.*;

public class TableauToFoundationMove extends Move{

	Pile dest;
	Card cardDragged;
	Column src;
	
	public TableauToFoundationMove(Column src, Card cardDragged, Pile dest) {
		this.src = src;
		this.cardDragged = cardDragged;
		this.dest = dest;
	}
	
	@Override
	public boolean doMove(Solitaire game) {
		if (!valid(game)) { 
			return false; 
			}
		if (cardDragged != null) {
			dest.add(cardDragged);
		} else {
			dest.add(src.get());
		}
		
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
		Card c = cardDragged;
		if (cardDragged == null) {
			if (wastePile.empty()) { return false; }
			c = wastePile.peek();
		}
		
		if (c.getSuit() == Card.CLUBS) { draggingCardRed = false; }
		if (c.getSuit() == Card.SPADES) { draggingCardRed = false; }
		
		if (isRed && draggingCardRed) {
			return true;
		}
		if (!isRed && !draggingCardRed) {
			return true;
		}
		
		// not a match!
		return false;
	}

}
