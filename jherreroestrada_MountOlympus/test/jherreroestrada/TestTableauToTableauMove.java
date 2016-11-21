package jherreroestrada;

import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Column;
import ks.common.model.Deck;
import ks.launcher.Main;

public class TestTableauToTableauMove extends TestCase {


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

	// This method is to test the TableauToTableauMove
	// Move a subcolumn from one column to another.
	public void testTableauToTableauMove() {
		Column destinationColumn = mountOlympus.columns[0];
		Column sourceColumn = mountOlympus.columns[2];
		Column movedColumn = new Column();
		movedColumn.add(sourceColumn.get());
		int size = movedColumn.count();

		TableauToTableauMove tTtMove= new TableauToTableauMove(destinationColumn, movedColumn, sourceColumn, size);
		assertTrue (tTtMove.valid(mountOlympus));

		tTtMove.undo(mountOlympus);

		assertEquals(destinationColumn.count(), sourceColumn.count());

	}
	// -------------------------------------------------
}
