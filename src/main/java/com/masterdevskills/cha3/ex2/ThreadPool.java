package com.masterdevskills.cha3.ex2;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

//TODO: Implement this thread pool using BlockingQueue
public class ThreadPool {

	private ThreadGroup threadGroup = new ThreadGroup("ThreadGroup");
	private LinkedBlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
	private volatile boolean keepRunning = true;


	public ThreadPool(int poolSize) {
		for (int i = 0; i < poolSize; i++) {
			Job job = new Job(threadGroup, String.valueOf(i));
			job.start();
		}
	}

	private Runnable take() throws InterruptedException {
//		return blockingQueue.take();
		return blockingQueue.poll();
	}

	public void submit(Runnable job) {
		blockingQueue.add(job);
	}

	public int getRunQueueLength() {
		return blockingQueue.size();
	}

	public void shutdown() {
		this.keepRunning = false;
		this.threadGroup.interrupt();
//		this.threadGroup.stop();
	}

	private class Job extends Thread {
		public Job(ThreadGroup group, String name) {
			super(group, name);
		}

		public void run() {
			//TODO
			// we run in an infinite loop:
			// remove the next job from the linked list using take()
			// we then call the run() method on the job

			while (keepRunning){
				try {
					take().run();
				} catch (InterruptedException e) {
					e.printStackTrace();
					currentThread().interrupt();
				}
			}
		}
	}
}


