package com.masterdevskills.cha3.ex1;


import java.util.LinkedList;
import java.util.Queue;

//TODO: Create a thread group field
// Create a LinkedList field containing Runnable
// Hint: Since LinkedList is not thread-safe, we need to synchronize it
public class ThreadPool {

	private ThreadGroup threadGroup = new ThreadGroup("ThreadGroup");
	private Queue<Runnable> queue = new LinkedList<>();
	private Object lock = new Object();
	private int poolSize;

	public ThreadPool(int poolSize) {
		this.poolSize = poolSize;
		for (int i = 0; i < poolSize; i++) {
			Worker worker = new Worker(threadGroup, "Thread"+i);
			worker.run();
		}
	}

	private synchronized Runnable take() throws InterruptedException {
		//TODO if the LinkedList is empty, we wait
		// remove the first job from the LinkedList and return it
//		throw new UnsupportedOperationException("not implemented");

//		synchronized (lock){
//			while (queue.isEmpty()) {
//				try {
//					lock.wait();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			lock.notifyAll();
//			return queue.poll();

		synchronized (queue){
			while (queue.isEmpty()) {
				try {
					queue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			queue.notifyAll();
			return queue.poll();
		}

	}

	public void submit(Runnable job) {
		//TODO Add the job to the LinkedList and notifyAll

//		System.out.println("submit invoked!");
//		synchronized (lock){
//			while (queue.size() == poolSize) {
//				try {
//					lock.wait();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			queue.add(job);
//			lock.notifyAll();
//		}

		while (queue.size() == poolSize) {
			try {
				queue.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		queue.add(job);
		lock.notifyAll();

	}

	public int getRunQueueLength() {
		//TODO return the length of the LinkedList
		// remember to also synchronize!
//		throw new UnsupportedOperationException("not implemented");

		synchronized (lock){
			return queue.size();
		}
	}

	public void shutdown() {
		//TODO this should call stop() on the ThreadGroup.

		threadGroup.stop();
	}

	private class Worker extends Thread {
		public Worker(ThreadGroup group, String name) {
			super(group, name);
		}

		public void run() {
			//TODO
			// we run in an infinite loop:
			// remove the next job from the linked list using take()
			// we then call the run() method on the job

			while (true){
				try {
					take().run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}
}


