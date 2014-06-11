package gueei.binding.collections;

import gueei.binding.IObservableCollection;

public interface LazyLoadRowModel {
	/**
	 * Display the model. It is possible that the model is already displaying, 
	 * the check of whether the model should be displayed required the 
	 * implementer to handle
	 * @param collection The collection reference of the model belongs to, provided for convenience
	 * @param index The index of this row model belongs to, the collection, for convenience only
	 */
	public void display(IObservableCollection<?> collection, int index);
	public void hide(IObservableCollection<?> collection, int index);
}