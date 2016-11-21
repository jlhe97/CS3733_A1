package jherreroestrada;

import java.awt.event.MouseEvent;

import ks.client.gamefactory.GameWindow;
import ks.common.model.Deck;
import ks.launcher.Main;
import ks.tests.KSTestCase;

public class TestPileController extends KSTestCase {

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
	
	public void testPileController() {

		DealCardMove dcm1 = new DealCardMove(mountOlympus.deck, mountOlympus.columns);
		dcm1.doMove(mountOlympus);
		
		// first create a mouse event
		MouseEvent pr = createPressed (mountOlympus, mountOlympus.columnViews[0], 0, 0);
		mountOlympus.columnViews[0].getMouseManager().handleMouseEvent(pr);

		// drop on the first column
		MouseEvent rel = createReleased (mountOlympus, mountOlympus.pileViews[7], 0, 0);
		mountOlympus.pileViews[7].getMouseManager().handleMouseEvent(rel);

		assertEquals (2, mountOlympus.piles[7].count());
		
	}
}