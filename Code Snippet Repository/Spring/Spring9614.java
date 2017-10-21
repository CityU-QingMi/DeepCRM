	private void cancelTask() {
		Future<?> future = this.taskFuture;
		if (future != null) {
			try {
				future.cancel(true);
			}
			catch (Throwable ex) {
				// Ignore
			}
		}
	}
