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
	 * Do Move
	 * @param theGame ks.games.Solitaire 
	 */
	public boolean doMove (ks.common.games.Solitaire game) {
		// VALIDATE
		if (valid(game) == false)
			return false;

		for (int i = 0; i < columns.length || (deck.empty()); i++){
			columns[i].add(deck.get());
		}
		
		/* FIX THIS
		if(deck.count() == 7){
			for(int i = 0; i < deck.count(); i++){
				columns[i].add(deck.get());
				} 
			}else{
				for (int i = 0; i < columns.length; i++){
					columns[i].add(deck.get());
				}
			}
			*/
			return true;
		}

		/**
		 * To undo this move, we move the cards from the wastePile back to the Deck.
		 * @param theGame ks.games.Solitaire 
		 */
		public boolean undo(ks.common.games.Solitaire game) {

			for (int i = 0; i < columns.length; i++){
				deck.add(columns[i].get());
			}
			
			/* FIX THIS
			if (deck.empty()){
				for (int i = 0; i < 7; i++){
					deck.add(columns[i].get());
				}
			} else{
				for (int i = 0; i < columns.length; i++){
					deck.add(columns[i].get());
				}
			}
			*/
			return true;
		}

		public boolean valid(ks.common.games.Solitaire game) {
			return !deck.empty();
		}
	}
