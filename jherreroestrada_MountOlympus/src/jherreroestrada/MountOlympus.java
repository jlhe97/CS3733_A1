package jherreroestrada;
import java.awt.Dimension;

import ks.client.gamefactory.GameWindow;
import ks.common.games.Solitaire;
import ks.common.model.*;
import ks.common.view.*;
import ks.launcher.Main;

public class MountOlympus  extends Solitaire{

	// ---- my attributes --------------
	MultiDeck deck;
	DeckView deckView;
	IntegerView scoreView;
	IntegerView numLeftView;
	Column[] columns = new Column[9];
	ColumnView[] columnViews = new ColumnView[9];
	Pile[] piles = new Pile[16];
	PileView[] pileViews = new PileView[16];
	// ------------------------------------
	
	// this method sets the dimensions of the screen
	// so all cards fit in the game.
	public Dimension getPreferredSize(){
		return new Dimension(1000, 1000);
	}
	
	@Override
	public String getName() {
		return "Mount Olympus";
	}

	@Override
	public boolean hasWon() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void initialize() {
		initializeModel(getSeed());
		initializeView();
		initializeControllers();
		
		// handling the first hand of random cards to the tableau.
		for(int i = 0; i < columnViews.length; i++){
			Card c = deck.get();
			columns[i].add(c);
		}
	}

	private void initializeView() {
		CardImages ci = getCardImages();
		int w = ci.getWidth();
		int h = ci.getHeight();
		int delta = ci.getOverlap();
		
		
		// the MultiDeck view and elts
		deckView = new DeckView(deck);
		deckView.setBounds(20, 20, w, h);
		container.addWidget(deckView);
		// ----------------------------
		
		// Score and NumOfCardsLeft views
		scoreView = new IntegerView(getScore());
		scoreView.setFontSize(16);
		scoreView.setBounds(20, 40+h, w, h/2);
		container.addWidget(scoreView);
		
		numLeftView = new IntegerView(getNumLeft());
		numLeftView.setFontSize(16);
		numLeftView.setBounds(20, 100 + (3/2 * h), w, h/2);
		container.addWidget(numLeftView);
		// -------------------------------------
		
		// ---- for loop that makes all of the columnViews ----
		for(int i=0; i < columnViews.length; i++){
			columnViews[i] = new ColumnView(columns[i]);
			columnViews[i].setBounds((20*(i+1)) + (i*w) , 100+(3*h), w, h+12*delta);
			container.addWidget(columnViews[i]);
		}
		// ------------------------------------------------------
		
		// ---- for loop that makes all of the pileViews --------
		for(int i = 0; i < pileViews.length; i++){
			pileViews[i] = new PileView(piles[i]);
			if (i < (pileViews.length/2))
				pileViews[i].setBounds((40+i*20) + ((i+1) * w), 20, w, h);
			else
				pileViews[i].setBounds((40+((i-8)*20)) + ((i-7) * w), 40+h, w, h);
			container.addWidget(pileViews[i]);
		}
			
		// ------------------------------------------------------

		
	}

	private void initializeControllers() {
		deckView.setMouseAdapter(new MountOlympusDeckController(this, deck, columns));
	}

	private void initializeModel(int seed) {
		deck = new MultiDeck("d",2);
		deck.create(seed);
		model.addElement(deck);
		
		// for loop that creates all of the columns
		for(int i = 0; i < columns.length; i++){
			columns[i] = new Column("col" + (i+1));
			model.addElement(columns[i]);
		}
		// -----------------------------------------------------
		
		// ---- for loop that makes all of the piles --------
		for(int i = 0; i < piles.length; i++){
			piles[i] = new Pile("pile" + (i+1));
			model.addElement(piles[i]);
		}
			
		// ------------------------------------------------------
		
	}
	
	
	public static void main (String [] args){
		GameWindow gw = Main.generateWindow(new MountOlympus(), Deck.OrderBySuit);
	}

}
