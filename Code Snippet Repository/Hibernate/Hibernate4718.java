	public double getExecutionAvgTimeAsDouble() {
		// We write lock here to be sure that we always calculate the average time
		// with all updates from the executed applied: executionCount and totalExecutionTime
		// both used in the calculation
		writeLock.lock();
		try {
			double avgExecutionTime = 0;
			if ( executionCount.get() > 0 ) {
				avgExecutionTime = totalExecutionTime.get() / (double) executionCount
						.get();
			}
			return avgExecutionTime;
		}
		finally {
			writeLock.unlock();
		}
	}
