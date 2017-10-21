	private void awaitTerminationIfNecessary(ExecutorService executor) {
		if (this.awaitTerminationSeconds > 0) {
			try {
				if (!executor.awaitTermination(this.awaitTerminationSeconds, TimeUnit.SECONDS)) {
					if (logger.isWarnEnabled()) {
						logger.warn("Timed out while waiting for executor" +
								(this.beanName != null ? " '" + this.beanName + "'" : "") + " to terminate");
					}
				}
			}
			catch (InterruptedException ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("Interrupted while waiting for executor" +
							(this.beanName != null ? " '" + this.beanName + "'" : "") + " to terminate");
				}
				Thread.currentThread().interrupt();
			}
		}
	}
