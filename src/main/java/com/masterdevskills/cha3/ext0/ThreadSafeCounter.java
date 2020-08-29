package com.masterdevskills.cha3.ext0;



//TODO make it threadsafe
public class ThreadSafeCounter {
	private int count;

	private Object object = new Object();

	public void increment() {
		synchronized (object){
			count = count + 1;
		}
	}

	public int getCount() {
		return count;
	}
}
