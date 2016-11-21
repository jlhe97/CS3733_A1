package jherreroestrada;

import java.awt.event.MouseEvent;

import ks.client.gamefactory.GameWindow;
import ks.common.model.Deck;
import ks.launcher.Main;
import ks.tests.KSTestCase;

public class TestDeckController extends KSTestCase {
	
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
	
	public void testPressLogic() {
		// create mouse press at (0,0) within the deckview; should deal card
		MouseEvent press = this.createPressed(mountOlympus, mountOlympus.deckView, 25, 25);
		mountOlympus.deckView.getMouseManager().handleMouseEvent(press);
		
		// what do we know about the game after press on deck? Card dealt!
		assertEquals ("4S", mountOlympus.columns[0].peek().toString()); 
	}
	
}