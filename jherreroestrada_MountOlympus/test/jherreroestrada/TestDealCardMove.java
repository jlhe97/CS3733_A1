package jherreroestrada;



import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.model.*;
import ks.launcher.Main;


public class TestDealCardMove extends TestCase {


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


	// This method is to test the DealCardMove
	public void testDealCardMove1() {

		Card topCard1 = mountOlympus.deck.peek(mountOlympus.deck.count() - 1);
		Card topCard2 = mountOlympus.deck.peek(mountOlympus.deck.count() - 2);
		Card topCard3 = mountOlympus.deck.peek(mountOlympus.deck.count() - 3);
		Card topCard4 = mountOlympus.deck.peek(mountOlympus.deck.count() - 4);
		Card topCard5 = mountOlympus.deck.peek(mountOlympus.deck.count() - 5);
		Card topCard6 = mountOlympus.deck.peek(mountOlympus.deck.count() - 6);
		Card topCard7 = mountOlympus.deck.peek(mountOlympus.deck.count() - 7);
		Card topCard8 = mountOlympus.deck.peek(mountOlympus.deck.count() - 8);
		Card topCard9 = mountOlympus.deck.peek(mountOlympus.deck.count() - 9);

		DealCardMove dcm = new DealCardMove(mountOlympus.deck, mountOlympus.columns);

		assertTrue(dcm.valid(mountOlympus));

		dcm.doMove(mountOlympus);

		assertEquals(70, mountOlympus.deck.count());


		assertEquals(topCard1, mountOlympus.columns[0].peek());
		assertEquals(topCard2, mountOlympus.columns[1].peek());
		assertEquals(topCard3, mountOlympus.columns[2].peek());
		assertEquals(topCard4, mountOlympus.columns[3].peek());
		assertEquals(topCard5, mountOlympus.columns[4].peek());
		assertEquals(topCard6, mountOlympus.columns[5].peek());
		assertEquals(topCard7, mountOlympus.columns[6].peek());
		assertEquals(topCard8, mountOlympus.columns[7].peek());
		assertEquals(topCard9, mountOlympus.columns[8].peek());



		int value = mountOlympus.getNumLeft().getValue();

		assertEquals(70, value);

		dcm.undo(mountOlympus);
		value = mountOlympus.getNumLeft().getValue();
		assertEquals(value, mountOlympus.deck.count());
	}

	public void testDealCardMove2() {
		DealCardMove dcm1 = new DealCardMove(mountOlympus.deck, mountOlympus.columns);
		
		for(int i=0; i < 9; i++){
			dcm1.doMove(mountOlympus);
		}
		
		dcm1.undo(mountOlympus);
		
		assertEquals(7, mountOlympus.deck.count());
		
	}


}
