package com.masterdevskills.cha3.ex3;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

//TODO: Implement this thread pool using ExecutorService
public class ThreadPool {

	ExecutorService executorService;
	ReentrantLock lock;

	public ThreadPool(int poolSize) {
		this.executorService = Executors.newFixedThreadPool(poolSize);
	}

	private Runnable take() throws InterruptedException {
		throw new UnsupportedOperationException("not implemented");
	}

	public void submit(Runnable job) {
		executorService.submit(() -> {
			lock.lock();
			try {
				job.run();
			} finally {
				lock.unlock();
			}
		});
	}

	public int getRunQueueLength() {

		throw new UnsupportedOperationException("not implemented");
	}

	public void shutdown() {
		executorService.shutdown();
	}

}


