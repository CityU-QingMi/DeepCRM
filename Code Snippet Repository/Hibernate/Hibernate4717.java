	void queryExecuted(long time) {
		// read lock is enough, concurrent updates are supported by the underlying type AtomicLong
		// this only guards executed(long, long) to be called, when another thread is executing getExecutionAvgTime()
		this.readLock.lock();
		try {
			// Less chances for a context switch
			//noinspection StatementWithEmptyBody
			for ( long old = this.executionMinTime.get(); time < old && !this.executionMinTime.compareAndSet( old, time ); old = this.executionMinTime.get() ) {
			}
			//noinspection StatementWithEmptyBody
			for ( long old = this.executionMaxTime.get(); time > old && !this.executionMaxTime.compareAndSet( old, time ); old = this.executionMaxTime.get() ) {
			}
			this.executionCount.getAndIncrement();
			this.totalExecutionTime.addAndGet( time );
		}
		finally {
			this.readLock.unlock();
		}
	}
