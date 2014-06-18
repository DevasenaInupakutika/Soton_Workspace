package com.example.ssitestapp;

/**
 * A listener to pass actions from view to adapter
 */

public interface InfiniteScrollListPageListener {
	public abstract void endOfList();
	public abstract void hasMore();

}
