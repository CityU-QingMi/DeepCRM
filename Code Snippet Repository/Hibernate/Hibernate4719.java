	void executed(long rows, long time) {
		// read lock is enough, concurrent updates are supported by the underlying type AtomicLong
		// this only guards executed(long, long) to be called, when another thread is executing getExecutionAvgTime()
		readLock.lock();
		try {
			// Less chances for a context switch
			for (long old = executionMinTime.get(); (time < old) && !executionMinTime.compareAndSet(old, time); old = executionMinTime.get()) {}
			for (long old = executionMaxTime.get(); (time > old) && !executionMaxTime.compareAndSet(old, time); old = executionMaxTime.get()) {}
			executionCount.getAndIncrement();
			executionRowCount.addAndGet(rows);
			totalExecutionTime.addAndGet(time);
		}
		finally {
			readLock.unlock();
		}
	}
