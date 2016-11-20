package jherreroestrada;
import ks.common.model.MultiDeck;
import ks.common.model.Column;

public class DealCardMove extends ks.common.model.Move{

	/** The deck. */
	protected MultiDeck deck;

	/** the receiving columns */
	protected Column[] columns;

	public DealCardMove (MultiDeck deck, Column[] columns) {
		super();
		this.deck = deck;
		this.columns = columns;
	}

	/**
	 * This method deals at most 9 cards when we press on the deck.
	 */
	public boolean doMove (ks.common.games.Solitaire game) {
		// VALIDATE
		if (valid(game) == false)
			return false;

		for (int i = 0; i < columns.length && !deck.empty(); i++){
			columns[i].add(deck.get());
		}

		return true;
	}


	/**
	 * This method moves back from the columns to the deck.
	 */
	public boolean undo(ks.common.games.Solitaire game) {

		if(deck.empty()){ // this is just to deal with the last dealing that hands out only 7 cards.
			for(int i = 0; i < 7; i++){
				deck.add(columns[i].get());
			}
		} else {
			for (int i = 0; i < columns.length; i++){
				deck.add(columns[i].get());
			}
		}

		return true;
	}

	public boolean valid(ks.common.games.Solitaire game) {
		return !deck.empty();
	}
}
