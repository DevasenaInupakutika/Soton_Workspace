package gueei.binding.cursor;

import gueei.binding.IObservableCollection;

/**
 * User: =ra=
 * Date: 11.10.11
 * Time: 21:05
 */
public class RowModel implements IRowModel {
	@Override public void onInitialize() {}

	@Override public long getId(int proposedId) {
		return proposedId;
	}

	private boolean displaying = false;
	
	@Override public final void display(IObservableCollection<?> collection, int index) {
		if (displaying) return;
		displaying = true;
		onDisplay();
	}

	@Override public final void hide(IObservableCollection<?> collection, int index) {
		if (!displaying) return;
		displaying = false;
		onHide();
	}

	public void onDisplay() {
	}

	public void onHide() {
	}
}