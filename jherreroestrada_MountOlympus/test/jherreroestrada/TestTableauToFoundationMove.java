package jherreroestrada;

import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Column;
import ks.common.model.Deck;
import ks.common.model.Pile;
import ks.launcher.Main;

public class TestTableauToFoundationMove extends TestCase {


	MountOlympus mountOlympus;
	GameWindow gw;

	@Override
	protected void setUp() {
		mountOlympus = new MountOlympus();
		gw = Main.generateWindow(mountOlympus, Deck.OrderBySuit);
	}

	@Override
	protected void tearDown() {
		gw.dispose();
	}

	// this method is to test the Tableau to Foundation Method
	public void testTableauToFoundationMove(){
		DealCardMove dcm = new DealCardMove(mountOlympus.deck, mountOlympus.columns);
		dcm.doMove(mountOlympus);

		Pile destinationPile = mountOlympus.piles[7];
		Column sourceColumn = mountOlympus.columns[0];
		Column draggedElt = new Column();
		draggedElt.add(sourceColumn.get());

		// -------------- TESTING VALIDITY -------------------
		int valueBefore = mountOlympus.getScore().getValue();
		TableauToFoundationMove tTfMove = new TableauToFoundationMove(sourceColumn, draggedElt, destinationPile);

		assertTrue(tTfMove.valid(mountOlympus));

		tTfMove.doMove(mountOlympus);
		int valueAfter = mountOlympus.getScore().getValue();

		assertEquals((valueBefore + 1), valueAfter);
		// ---------------------------------------------------


		// --------------- UNDO TESTING ---------------------
		valueBefore = mountOlympus.getScore().getValue();
		tTfMove.undo(mountOlympus);
		valueAfter = mountOlympus.getScore().getValue();
		assertEquals(valueBefore - 1, valueAfter);
		// --------------------------------------------------

	}

	// second test case for tableau to foundation method
	public void testTableauToFoundationMove2(){
		Pile destinationPile = mountOlympus.piles[7];
		Column sourceColumn = mountOlympus.columns[0];
		Column draggedElt = new Column();
		draggedElt.add(sourceColumn.get());

		TableauToFoundationMove tTfMove = new TableauToFoundationMove(sourceColumn, draggedElt, destinationPile);
		assertTrue(!tTfMove.valid(mountOlympus));

	}
	
	

}
