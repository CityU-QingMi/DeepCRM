	protected boolean applyBackOffTime(BackOffExecution execution) {
		if (this.recovering && this.interrupted) {
			// Interrupted right before and still failing... give up.
			return false;
		}
		long interval = execution.nextBackOff();
		if (interval == BackOffExecution.STOP) {
			return false;
		}
		else {
			try {
				synchronized (this.lifecycleMonitor) {
					this.lifecycleMonitor.wait(interval);
				}
			}
			catch (InterruptedException interEx) {
				// Re-interrupt current thread, to allow other threads to react.
				Thread.currentThread().interrupt();
				if (this.recovering) {
					this.interrupted = true;
				}
			}
			return true;
		}
	}
