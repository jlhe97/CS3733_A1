package jherreroestrada;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ks.common.model.Column;
import ks.common.model.Move;
import ks.common.model.Stack;
import ks.common.view.ColumnView;
import ks.common.view.Container;
import ks.common.view.Widget;

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
			c.releaseDraggingObject();		
			return;
		}

		//ColumnView cw = (ColumnView) w;

		/** Recover the from BuildablePile OR waste Pile */
		Widget fromWidget = c.getDragSource();
		if (fromWidget == null) {
			System.err.println ("ColumnController::mouseReleased(): somehow no dragSource in container.");
			c.releaseDraggingObject();
			return;
		}
		
		// ------------ the move that allows to move cards from one column to another ---------
		
		Column dest = (Column) columnView.getModelElement();
		Column src = (Column) fromWidget.getModelElement();

		ColumnView temp = (ColumnView) draggingWidget;
		Column movedColumn = (Column) temp.getModelElement();
		int size = dest.count();
		
		Move move = new TableauToTableauMove(dest, movedColumn, src, size);
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


