package jherreroestrada;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import ks.common.model.*;

public class MountOlympusDeckController extends MouseAdapter {

	MultiDeck deck;
	Column[] columns;
	MountOlympus theGame;
	
	public MountOlympusDeckController(MountOlympus mountOlympus, MultiDeck deck, Column[] columns){
		this.deck = deck;
		this.columns = columns;
		this.theGame = mountOlympus;
	}
	
	/*
	 * This function is moves a random card to the different columns when we click
	 * on the columns.
	 */
	public void mousePressed(MouseEvent me){	
		Move m = new DealCardMove(deck, columns);
			if (m.doMove(theGame)){
				theGame.pushMove(m);
				theGame.refreshWidgets();
			}
	}
}
