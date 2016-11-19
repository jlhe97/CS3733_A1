package jherreroestrada;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ks.common.model.*;
import ks.common.view.*;

/*
 * This controller is in charge of moving columns in the tableau to other columns.
 */
public class MountOlympusColumnController extends MouseAdapter {

	MountOlympus theGame;
	ColumnView columnView;

	public MountOlympusColumnController(MountOlympus mountOlympus, ColumnView columnView){
		this.theGame = mountOlympus;
		this.columnView = columnView;
	}

	/*
	 * This method allows the player to drag a card to and from a column
	 */
	public void mousePressed(MouseEvent me){
		Container c = theGame.getContainer();

		ColumnView cards = columnView.getColumnView(me);

		// Tell container which object is being dragged, and where in that widget the user clicked.
		c.setActiveDraggingObject (cards, me);

		// Tell container which BuildablePileView is the source for this drag event.
		c.setDragSource (columnView);

		// we simply redraw our source pile to avoid flicker,
		// rather than refreshing all widgets...
		columnView.redraw();
	}

	/*
	 * This method allows the columns to receive cards.
	 */
	public void mouseReleased(MouseEvent me) {
		Container c = theGame.getContainer();

		/** Return if there is no card being dragged chosen. */
		Widget draggingWidget = c.getActiveDraggingObject();
		if (draggingWidget == Container.getNothingBeingDragged()) {
			System.err.println ("FoundationController::mouseReleased() unexpectedly found nothing being dragged.");
			c.releaseDraggingObject();		
			return;
		}
		
		ColumnView cw = (ColumnView) draggingWidget; // the column from which we drag the widget.

		/** Recover the from BuildablePile OR waste Pile */
		Widget fromWidget = c.getDragSource();
		if (fromWidget == null) {
			System.err.println ("FoundationController::mouseReleased(): somehow no dragSource in container.");
			c.releaseDraggingObject();
			return;
		}

		// Determine the To Pile
		Column dest = (Column) columnView.getModelElement(); // DESTINATION COLUMN
		ColumnView srcView = (ColumnView) fromWidget;  // SOURCE COLUMN VIEW
		Column src = (Column) srcView.getModelElement(); // SOURCE COLUMN
		Column draggedElt = (Column) cw.getModelElement();    // DRAGGED ELEMENT
		int size = draggedElt.count();
		
		Move move = new TableauToTableauMove(dest, draggedElt, src, size);
		if (move.doMove(theGame)) {
			theGame.pushMove (move);
		} else {
			fromWidget.returnWidget(draggingWidget);
		}
		
		// ---------------------------------------------------------------------------------------

		// Tell container which object is being dragged, and where in that widget the user clicked.
		c.releaseDraggingObject();

		// we simply redraw our source pile to avoid flicker,
		// rather than refreshing all widgets...
		columnView.redraw();

		// done
		c.repaint();
	}
}


