	@Override
	public void destroy() {
		if (this.forkJoinPool != null) {
			// Ignored for the common pool.
			this.forkJoinPool.shutdown();

			// Wait for all tasks to terminate - works for the common pool as well.
			if (this.awaitTerminationSeconds > 0) {
				try {
					this.forkJoinPool.awaitTermination(this.awaitTerminationSeconds, TimeUnit.SECONDS);
				}
				catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}
